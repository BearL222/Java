import java.util.ArrayList;
import java.util.Arrays;

public class LCS {
	private String[] word;

	LCS(String[] word) {
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
		return getMax(result);
	}

	// LCS
	int calc(String a, String b) {
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
		tmp.clear();
		return result;
	}
}
