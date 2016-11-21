import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BuildDict {
	// 下面分别用于存储单词，音标，意思
	private String[] word;
	private String[] ps;
	private String[] meaning;
	// length为单词数
	private int length;
	private int maxLength = 40000;

	private SearchWord searchWord;

	// 建立字典
	BuildDict(String path) throws FileNotFoundException {
		word = new String[maxLength];
		ps = new String[maxLength];
		meaning = new String[maxLength];
		// 开始读取词典文件
		java.io.File file = new java.io.File(path);
		Scanner input = new Scanner(file);
		input.nextLine();
		int index = 0;
		while (input.hasNext()) {
			String line = input.nextLine();
			// 若该行为空，直接进行下一行
			if (line.isEmpty()) {
				continue;
			}
			// 用tab分开每条item，item的1，2，3分别为单词，音标，意思
			String[] item = line.split("\t");
			word[index] = item[1];
			ps[index] = item[2];
			meaning[index] = item[3];
			index++;
		}
		// 记录单词数
		length = index;
		input.close();
		searchWord = new SearchWord(Arrays.copyOfRange(word, 0, length));
	}

	public SearchWord getsearchWord() {
		return searchWord;
	}

	public int getLength() {
		return length;
	}

	public String[] getAllWord() {
		return Arrays.copyOfRange(word, 0, length);
	}

	// 根据输入找到对应单词，返回单词的拼写，音标，意思
	public String[] getWord(String x) {
		int index = Math.abs(searchWord.findWord(x));
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	// 根据索引找到对应单词，返回单词的拼写，音标，意思
	public String[] getWord(int index) {
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	// 根据LCS，找到相近的单词个
	public String[] LCS(String x) {
		int[] LCSResult = searchWord.LCSCount(x);
		String[] result = new String[LCSResult.length * 3];
		for (int i = 0; i < LCSResult.length; i++) {
			result[i * 3] = word[LCSResult[i]];
			result[i * 3 + 1] = ps[LCSResult[i]];
			result[i * 3 + 2] = meaning[LCSResult[i]];
		}
		return result;
	}
}
