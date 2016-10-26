import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class InputPanel extends JPanel {
	private JTextField inputText;
	private JButton translateButton;

	public InputPanel() {
		// 文本输入框和按钮初始化
		inputText = new JTextField("");
		translateButton = new JButton("查询");

		// 布置位置
		setLayout(new BorderLayout());
		add(inputText, BorderLayout.CENTER);
		add(translateButton, BorderLayout.EAST);
	}

	// 返回JTextField
	public JTextField getField() {
		return inputText;
	}

	// 返回JButton
	public JButton getTranslate() {
		return translateButton;
	}

	// 返回输入框内容，将大写变小写
	public String getWord() {
		return inputText.getText().toLowerCase();
	}
}