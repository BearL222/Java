import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {
	private JTextArea jtaDescription;
	private JScrollPane scrollPane;

	public DescriptionPanel(String greeting) {
		// ��ʼ���ı���͹�����
		jtaDescription = new JTextArea();
		scrollPane = new JScrollPane(jtaDescription);

		// �����ı���
		jtaDescription.setFont(new Font("Serif", Font.PLAIN, 14));
		jtaDescription.setLineWrap(true);
		jtaDescription.setWrapStyleWord(true);
		jtaDescription.setEditable(false);

		// ���ó��δ򿪵Ļ�ӭ����
		this.setDescription(greeting);

		// ����λ��
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	}

	// ���������text�޸��ı�����ʾ������
	private void setDescription(String text) {
		jtaDescription.setText(text);
	}

	// �ҵ��������
	public void setDescription(String[] result) {
		String out;
		if (result.length == 4) {
			// �ҵ����ʵ����
			out = ("���ʣ� " + result[0] + "\n���꣺ " + result[1] + "\n��˼�� " + result[2]);
		} else {
			// û���ҵ������
			out = "û���ҵ���Ӧ���ʣ����Ƿ��ڲ������µ���:\n";
			for (int i = 0; i < (result.length + 1) / 3; i++) {
				out += (i + 1 + ". ���ʣ�" + result[3 * i] + "\n   ���꣺" + result[3 * i + 1] + "\n   ��˼��"
						+ result[3 * i + 2] + "\n\n");
			}
		}
		jtaDescription.setText(out);
	}
}
