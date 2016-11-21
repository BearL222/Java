import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BuildDict {
	// ����ֱ����ڴ洢���ʣ����꣬��˼
	private String[] word;
	private String[] ps;
	private String[] meaning;
	// lengthΪ������
	private int length;
	private int maxLength = 40000;

	private SearchWord searchWord;

	// �����ֵ�
	BuildDict(String path) throws FileNotFoundException {
		word = new String[maxLength];
		ps = new String[maxLength];
		meaning = new String[maxLength];
		// ��ʼ��ȡ�ʵ��ļ�
		java.io.File file = new java.io.File(path);
		Scanner input = new Scanner(file);
		input.nextLine();
		int index = 0;
		while (input.hasNext()) {
			String line = input.nextLine();
			// ������Ϊ�գ�ֱ�ӽ�����һ��
			if (line.isEmpty()) {
				continue;
			}
			// ��tab�ֿ�ÿ��item��item��1��2��3�ֱ�Ϊ���ʣ����꣬��˼
			String[] item = line.split("\t");
			word[index] = item[1];
			ps[index] = item[2];
			meaning[index] = item[3];
			index++;
		}
		// ��¼������
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

	// ���������ҵ���Ӧ���ʣ����ص��ʵ�ƴд�����꣬��˼
	public String[] getWord(String x) {
		int index = Math.abs(searchWord.findWord(x));
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	// ���������ҵ���Ӧ���ʣ����ص��ʵ�ƴд�����꣬��˼
	public String[] getWord(int index) {
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	// ����LCS���ҵ�����ĵ��ʸ�
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
