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

	private LCS lcs;

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
		lcs = new LCS(Arrays.copyOfRange(word, 0, length));
	}

	// ���ֲ��ҵ���
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
		// p�����һ������
		if (p == length - 1) {
			return p;
		}
		// �Ƚϵ�p�ĵ��ʺ͵�p+1�������ĸ���x��
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

	// ���������ҵ���Ӧ���ʣ����ص��ʵ�ƴд�����꣬��˼
	public String[] getWord(String x) {
		int index = Math.abs(findWord(x));
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	// ���������ҵ���Ӧ���ʣ����ص��ʵ�ƴд�����꣬��˼
	public String[] getWord(int index) {
		String[] result = { word[index], ps[index], meaning[index], String.valueOf(index) };
		return result;
	}

	public String[] getAllWord() {
		return Arrays.copyOfRange(word, 0, length);
	}

	// ����LCS���ҵ�����ĵ��ʣ�������
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
