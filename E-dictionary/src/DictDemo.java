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
	// 输入区，主显区，联想区，词典
	private static InputPanel inputPanel;
	private static DescriptionPanel descriptionPanel;
	private static ListPanel listPanel;
	private static BuildDict dict;

	public static void main(String[] args) throws FileNotFoundException {
		// 词典文件内容读取
		String path = "dictionary.txt";
		try {
			dict = new BuildDict(path);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Wrong dictionary file path!");
		}

		// 初始化
		inputPanel = new InputPanel();
		descriptionPanel = new DescriptionPanel("欢迎使用英汉词典");
		listPanel = new ListPanel(dict.getAllWord());

		// 界面
		DictDemo frame = new DictDemo();
		frame.setTitle("英汉词典");
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public DictDemo() {
		// 布置三个区域
		add(inputPanel, BorderLayout.NORTH);
		add(descriptionPanel, BorderLayout.CENTER);
		add(listPanel, BorderLayout.WEST);

		inputPanel.setFocusable(true);

		// 监听查询按钮
		inputPanel.getTranslate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output(3);
			}
		});
		// 监听单词列表
		listPanel.getList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				output(2);
			}
		});
		// 监听键盘
		inputPanel.getField().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				// 判断键盘输入是否为回车，空格退格或字母
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_SPACE
						|| (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z)) {
					output(1);
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					output(3);
				}
			}
		});
	}

	// 输出结果
	private void output(int type) {
		int index = -1;
		if (type == 3) {
			if (dict.getsearchWord().findWord(inputPanel.getWord()) < 0) {
				// 没有在字典中找到单词，LCS寻找最相近的单词
				String[] result = dict.LCS(inputPanel.getWord());
				descriptionPanel.setDescription(result);
				return;
			} else {
				type = 1;
			}
		}
		String[] result = new String[4];
		if (type == 1 || type == 0) {
			// 按键和空格退格情况
			index = Math.abs(dict.getsearchWord().findWord(inputPanel.getWord()));
			listPanel.movePosition(index, dict.getLength());
		} else if (type == 2) {
			// 直接从单词列表选择情况
			index = listPanel.getList().getSelectedIndex();
		}
		// 输出结果
		result = dict.getWord(index);
		descriptionPanel.setDescription(result);
		// 针对按键和空格退格情况，将单词列表的焦点移动到目标单词
		if (type == 1 || type == 0) {
			listPanel.getList().setSelectedIndex(Integer.parseInt(result[3]));
		}
	}
}
