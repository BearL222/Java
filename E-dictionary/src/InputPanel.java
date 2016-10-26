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
		// �ı������Ͱ�ť��ʼ��
		inputText = new JTextField("");
		translateButton = new JButton("��ѯ");

		// ����λ��
		setLayout(new BorderLayout());
		add(inputText, BorderLayout.CENTER);
		add(translateButton, BorderLayout.EAST);
	}

	// ����JTextField
	public JTextField getField() {
		return inputText;
	}

	// ����JButton
	public JButton getTranslate() {
		return translateButton;
	}

	// ������������ݣ�����д��Сд
	public String getWord() {
		return inputText.getText().toLowerCase();
	}
}