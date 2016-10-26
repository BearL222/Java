import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.*;

public class ListPanel extends JPanel {
	private JList<String> list;
	private JScrollPane scrollPane;

	public ListPanel(String[] listWord) {
		// 初始化单词列表和滚动条
		list = new JList<String>(listWord);
		scrollPane = new JScrollPane(list);

		// 布置
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.EAST);
	}

	public JList<String> getList() {
		return list;
	}

	// 将焦点移动到输入单词
	public void movePosition(int index, int length) {
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		scrollPane.getVerticalScrollBar()
				.setValue((int) (double) scrollPane.getVerticalScrollBar().getMaximum() / length * index);
	}
}
