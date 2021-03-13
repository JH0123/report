package ch12;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

public class TextAreaDemo extends JFrame implements ActionListener{
	private JTextField textField;
	private JTextArea textArea;
	
	public TextAreaDemo() {
		this.setTitle("Text Area Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField(30);
		textField.addActionListener(this);
		
		textArea = new JTextArea(10,30);
		textArea.setEditable(false);
		
		this.add(textField, BorderLayout.NORTH);
		
		JScrollPane scoJScrollPane = new JScrollPane(textArea);
		
		this.add(scoJScrollPane,BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		textArea.append(text + "\n");
		
		textField.selectAll();
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	public static void main(String args[]) {
		new TextAreaDemo();
	}
}
