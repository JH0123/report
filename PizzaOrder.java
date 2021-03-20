package ch12;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class PizzaOrder extends JFrame implements ActionListener{
	private int sum, temp1, temp2, temp3;
	private JButton orderBtn, cancleBtn;
	private JPanel downPanel;
	private JTextField text;
	
	WelcomePanel welcomPanel = new WelcomePanel();
	TypePanel TypePanel = new TypePanel();
	ToppingPanel ToppingPanel = new ToppingPanel();
	SizePanel SizePanel = new SizePanel();
	
	public PizzaOrder() {
		this.setSize(500,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("���� �ֹ�");
		
		this.orderBtn = new JButton("�ֹ�");
		this.orderBtn.addActionListener(this);
		this.cancleBtn = new JButton("���");
		this.cancleBtn.addActionListener(this);
		
		this.text = new JTextField();
		text.setEditable(false);
		text.setColumns(6);
		
		downPanel = new JPanel();
		downPanel.add(this.orderBtn);
		downPanel.add(this.cancleBtn);
		downPanel.add(this.text);
		
		this.setLayout(new BorderLayout());
		
		this.add(welcomPanel, BorderLayout.EAST);
		this.add(downPanel, BorderLayout.SOUTH);
		this.add(SizePanel, BorderLayout.EAST);
		this.add(TypePanel, BorderLayout.WEST);
		this.add(ToppingPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.orderBtn) {
			this.text.setText(" "+20000);
		}
		if(e.getSource()==this.cancleBtn) {
			temp1 =0;
			temp2 =0;
			temp3 =0;
			sum =0;
			this.text.setText(" "+ sum);
		}
	}
	class WelcomePanel extends JPanel{
		private JLabel message;
		
		public WelcomePanel() {
			message = new JLabel("�� ���ڿ� ���Ű��� ȯ���մϴ�");
			add(message);
		}
	}
	class TypePanel extends JPanel{
		private JRadioButton combo, potato, bulgogi;
		private ButtonGroup bg;
		
		public TypePanel() {
			setLayout(new GridLayout(3,1));
			
			combo =new JRadioButton("�޺�",true);
			potato =new JRadioButton("��������");
			bulgogi =new JRadioButton("�Ұ��");
			
			bg= new ButtonGroup();
			bg.add(combo);
			bg.add(potato);
			bg.add(bulgogi);
			
			setBorder(BorderFactory.createTitledBorder("����"));
			
			add(combo);
			add(potato);
			add(bulgogi);
		}
	}
	class ToppingPanel extends JPanel{
		private JRadioButton pepper, cheese, peperoni, bacon;
		private ButtonGroup bg;
		
		public ToppingPanel() {
			setLayout(new GridLayout(4,1));
			
			pepper = new JRadioButton("�Ǹ�",true);
			cheese = new JRadioButton("ġ��");
			peperoni = new JRadioButton("���зδ�");
			bacon = new JRadioButton("������");
			
			bg = new ButtonGroup();
			bg.add(pepper);
			bg.add(cheese);
			bg.add(peperoni);
			bg.add(bacon);
			
			setBorder(BorderFactory.createTitledBorder("�߰�����"));
			
			add(pepper);
			add(cheese);
			add(peperoni); 
		}
	}
	class SizePanel extends JPanel{
		private JRadioButton samll, medium, large;
		private ButtonGroup bg;
		
		public SizePanel() {
			setLayout(new GridLayout(3,1));
			
			samll = new JRadioButton("Small", true);
			medium= new JRadioButton("Meduyn");
			large= new JRadioButton("Large");
			
			bg = new ButtonGroup();
			bg.add(samll);
			bg.add(medium);
			bg.add(large);
			
			setBorder(BorderFactory.createTitledBorder("ũ��"));
			
			add(samll);
			add(medium);
			add(large);
		}
	}
	public static void main(String args[]) {
		PizzaOrder pizzaOrder = new PizzaOrder();
	}
}

