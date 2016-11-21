import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class DictDemo extends JFrame {
	// �������������������������ʵ�
	private static InputPanel inputPanel;
	private static DescriptionPanel descriptionPanel;
	private static ListPanel listPanel;
	private static BuildDict dict;

	public static void main(String[] args) throws FileNotFoundException {
		// �ʵ��ļ����ݶ�ȡ
		String path = "dictionary.txt";
		try {
			dict = new BuildDict(path);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Wrong dictionary file path!");
		}

		// ��ʼ��
		inputPanel = new InputPanel();
		descriptionPanel = new DescriptionPanel("��ӭʹ��Ӣ���ʵ�");
		listPanel = new ListPanel(dict.getAllWord());

		// ����
		DictDemo frame = new DictDemo();
		frame.setTitle("Ӣ���ʵ�");
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public DictDemo() {
		// ������������
		add(inputPanel, BorderLayout.NORTH);
		add(descriptionPanel, BorderLayout.CENTER);
		add(listPanel, BorderLayout.WEST);

		inputPanel.setFocusable(true);

		// ������ѯ��ť
		inputPanel.getTranslate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output(3);
			}
		});
		// ���������б�
		listPanel.getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				output(2);
			}
		});
		// ��������
		inputPanel.getField().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				// �жϼ��������Ƿ�Ϊ�س����ո��˸����ĸ
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_SPACE
						|| (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z)) {
					output(1);
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					output(3);
				}
			}
		});
	}

	// ������
	private void output(int type) {
		int index = -1;
		if (type == 3) {
			if (dict.getsearchWord().findWord(inputPanel.getWord()) < 0) {
				// û�����ֵ����ҵ����ʣ�LCSѰ��������ĵ���
				String[] result = dict.LCS(inputPanel.getWord());
				descriptionPanel.setDescription(result);
				return;
			} else {
				type = 1;
			}
		}
		String[] result = new String[4];
		if (type == 1 || type == 0) {
			// �����Ϳո��˸����
			index = Math.abs(dict.getsearchWord().findWord(inputPanel.getWord()));
			listPanel.movePosition(index, dict.getLength());
		} else if (type == 2) {
			// ֱ�Ӵӵ����б�ѡ�����
			index = listPanel.getList().getSelectedIndex();
		}
		// ������
		result = dict.getWord(index);
		descriptionPanel.setDescription(result);
		// ��԰����Ϳո��˸�������������б�Ľ����ƶ���Ŀ�굥��
		if (type == 1 || type == 0) {
			listPanel.getList().setSelectedIndex(Integer.parseInt(result[3]));
		}
	}
}
