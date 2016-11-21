import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {
	private JTextArea jtaDescription;
	private JScrollPane scrollPane;

	public DescriptionPanel(String greeting) {
		// 初始化文本框和滚动条
		jtaDescription = new JTextArea();
		scrollPane = new JScrollPane(jtaDescription);

		// 设置文本框
		jtaDescription.setFont(new Font("Serif", Font.PLAIN, 14));
		jtaDescription.setLineWrap(true);
		jtaDescription.setWrapStyleWord(true);
		jtaDescription.setEditable(false);

		// 设置初次打开的欢迎文字
		this.setDescription(greeting);

		// 布置位置
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	}

	// 根据输入的text修改文本框显示的内容
	private void setDescription(String text) {
		jtaDescription.setText(text);
	}

	// 找到单词情况
	public void setDescription(String[] result) {
		String out;
		if (result.length == 4) {
			// 找到单词的情况
			out = ("单词： " + result[0] + "\n音标： " + result[1] + "\n意思： " + result[2]);
		} else {
			// 没有找到的情况
			out = "没有找到对应单词，您是否在查找如下单词:\n";
			for (int i = 0; i < (result.length + 1) / 3; i++) {
				out += (i + 1 + ". 单词：" + result[3 * i] + "\n   音标：" + result[3 * i + 1] + "\n   意思："
						+ result[3 * i + 2] + "\n\n");
			}
		}
		jtaDescription.setText(out);
	}
}
