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
		this.setTitle("피자 주문");
		
		this.orderBtn = new JButton("주문");
		this.orderBtn.addActionListener(this);
		this.cancleBtn = new JButton("취소");
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
			message = new JLabel("쭈 피자에 오신것을 환영합니다");
			add(message);
		}
	}
	class TypePanel extends JPanel{
		private JRadioButton combo, potato, bulgogi;
		private ButtonGroup bg;
		
		public TypePanel() {
			setLayout(new GridLayout(3,1));
			
			combo =new JRadioButton("콤보",true);
			potato =new JRadioButton("포테이토");
			bulgogi =new JRadioButton("불고기");
			
			bg= new ButtonGroup();
			bg.add(combo);
			bg.add(potato);
			bg.add(bulgogi);
			
			setBorder(BorderFactory.createTitledBorder("종류"));
			
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
			
			pepper = new JRadioButton("피망",true);
			cheese = new JRadioButton("치즈");
			peperoni = new JRadioButton("패패로니");
			bacon = new JRadioButton("베이컨");
			
			bg = new ButtonGroup();
			bg.add(pepper);
			bg.add(cheese);
			bg.add(peperoni);
			bg.add(bacon);
			
			setBorder(BorderFactory.createTitledBorder("추가토핑"));
			
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
			
			setBorder(BorderFactory.createTitledBorder("크기"));
			
			add(samll);
			add(medium);
			add(large);
		}
	}
	public static void main(String args[]) {
		PizzaOrder pizzaOrder = new PizzaOrder();
	}
}

