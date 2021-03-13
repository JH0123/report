package ch12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxFrame extends JFrame implements ActionListener{
	JLabel label;
	
	public ComboBoxFrame() {
		setTitle("콤보박스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		
		String[] animals = {"dog","lion","tiger"};
		JComboBox animalList = new JComboBox(animals);
		animalList.setSelectedIndex(0);
		animalList.addActionListener(this);
		
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		changePicture(animals[animalList.getSelectedIndex()]);
		add(animalList,BorderLayout.PAGE_START);
		add(label, BorderLayout.PAGE_END);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		String name = (String) cb.getSelectedItem();
		changePicture(name);
	}
	protected void changePicture(String name) {
		ImageIcon icon = new ImageIcon(name + ".gif");
		label.setIcon(icon);
		if(icon !=null) {
			label.setText(null);
		}
		else {
			label.setText("이미지가 발견되지 않았습니다");
			}
		}
		public static void main(String args[]) {
			ComboBoxFrame frame = new ComboBoxFrame();
	}
}