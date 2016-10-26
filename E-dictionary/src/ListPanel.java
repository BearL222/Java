import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.*;

public class ListPanel extends JPanel {
	private JList<String> list;
	private JScrollPane scrollPane;

	public ListPanel(String[] listWord) {
		// ��ʼ�������б�͹�����
		list = new JList<String>(listWord);
		scrollPane = new JScrollPane(list);

		// ����
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.EAST);
	}

	public JList<String> getList() {
		return list;
	}

	// �������ƶ������뵥��
	public void movePosition(int index, int length) {
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		scrollPane.getVerticalScrollBar()
				.setValue((int) (double) scrollPane.getVerticalScrollBar().getMaximum() / length * index);
	}
}
