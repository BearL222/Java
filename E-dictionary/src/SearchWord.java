import java.util.ArrayList;
import java.util.Arrays;

public class SearchWord {
	private String[] word;

	SearchWord(String[] word) {
		this.word = word;
	}

	int[] LCSCount(String x) {
		// Ŀ�굥���볤������С�ڵ���2�ĵ��ʼ���LCS
		int[] result = new int[word.length];
		for (int i = 0; i < word.length; i++) {
			if (Math.abs(x.length() - this.word[i].length()) <= 2) {
				result[i] = this.calc(x, this.word[i]);
			}
		}
		result = getMax(result);
		// ɸѡ֮�����г��Ȳ�С�ڵ���1������Щѡ����
		int[] reSelect = new int[word.length];
		int indexRe = 0;
		int j = 0;
		for (int i = 0; i < result.length; i++) {
			if (Math.abs(this.word[result[i]].length() - x.length()) <= 1) {
				reSelect[j++] = result[i];
				indexRe++;
			}
		}
		// ɸѡ֮�����г��Ȳ�Ϊ0������Щѡ����
		if (indexRe != 0) {
			result = Arrays.copyOfRange(reSelect, 0, indexRe);
			reSelect = new int[indexRe];
			indexRe = 0;
			j = 0;
			for (int i = 0; i < result.length; i++) {
				if (Math.abs(this.word[result[i]].length() - x.length()) == 0) {
					reSelect[j++] = result[i];
					indexRe++;
				}
			}
		}
		return indexRe == 0 ? result : Arrays.copyOfRange(reSelect, 0, indexRe);
	}

	// ���ֲ��ҵ���
	public int findWord(String x) {
		int p = 0;
		int q = word.length;
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
		if (p == word.length - 1) {
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

	// LCS
	public int calc(String a, String b) {
		int[][] matrix = new int[50][50];
		int lenA = a.length();
		int lenB = b.length();
		for (int i = 0; i < lenA; i++) {
			for (int j = 0; j < lenB; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					matrix[i + 1][j + 1] = matrix[i][j] + 1;
				} else {
					if (matrix[i + 1][j] >= matrix[i][j + 1]) {
						matrix[i + 1][j + 1] = matrix[i + 1][j];
					} else {
						matrix[i + 1][j + 1] = matrix[i][j + 1];
					}
				}
			}
		}
		return matrix[lenA][lenB];
	}

	// �������е���LCS�Ľ����������Щ
	int[] getMax(int[] x) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		int[] y = x.clone();
		Arrays.sort(x);
		for (int i = 0; i < x.length; i++) {
			if (y[i] == x[x.length - 1]) {
				tmp.add(i);
			}
		}
		int[] result = new int[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			result[i] = (int) tmp.get(i);
		}
		return result;
	}
}
