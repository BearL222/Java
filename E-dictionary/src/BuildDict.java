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

	private LCS lcs;

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
		lcs = new LCS(Arrays.copyOfRange(word, 0, length));
	}

	// 二分查找单词
	public int findWord(String x) {
		int p = 0;
		int q = length;
		while (p + 1 < q) {
			if (x.compareTo(word[(p + q) / 2]) < 0) {
				q = (p + q) / 2;
			} else if (x.compareTo(word[(p + q) / 2]) > 0) {
				p = (p + q) / 2;
			} else {
				return (p + q) / 2;
			}
		}
		// p是最后一个单词
		if (p == length - 1) {
			return p;
		}
		// 比较第p的单词和第p+1个单词哪个和x近
		int lengthTest = Math.min(x.length(), Math.min(word[p].length(), word[p + 1].length()));
		int dist1 = 0;
		int dist2 = 0;
		for (int i = 0; i < lengthTest; i++) {
			dist1 += Math.pow((x.charAt(i) - word[p].charAt(i)), 2);
			dist2 += Math.pow((x.charAt(i) - word[p + 1].charAt(i)), 2);
		}
		return dist1 <= dist2 ? -p : -(p + 1);
	}

	public int getLength() {
		return length;
	}

	// 根据输入找到对应单词，返回单词的拼写，音标，意思
	public String[] getWord(String x) {
		int index = Math.abs(findWord(x));
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	// 根据索引找到对应单词，返回单词的拼写，音标，意思
	public String[] getWord(int index) {
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	public String[] getAllWord() {
		return Arrays.copyOfRange(word, 0, length);
	}

	// 根据LCS，找到相近的单词，最多五个
	public String[] LCS(String x) {
		int[] LCSResult = lcs.LCSCount(x);
		String[] result = new String[LCSResult.length * 3];
		for (int i = 0; i < LCSResult.length; i++) {
			result[i * 3] = word[LCSResult[i]];
			result[i * 3 + 1] = ps[LCSResult[i]];
			result[i * 3 + 2] = meaning[LCSResult[i]];
		}
		return result;
	}
}
