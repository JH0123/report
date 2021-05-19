package ch11;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ColorChooserTest extends JFrame implements ChangeListener, ActionListener {

	private JColorChooser colorChooser = new JColorChooser();
	private JButton selectBtn = new JButton("색상선택 완료");
	private MyDrawingColorPanel drawingPanel = new MyDrawingColorPanel();
	private JPanel panel = new JPanel();
	
	public static void main(String[]args) {
		new ColorChooserTest();
	}
		
	public ColorChooserTest() {
		this.setTitle("색상 선텍기 테스트");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		colorChooser.getSelectionModel().addChangeListener(this);
		
		panel.add(colorChooser);
	
		this.add(panel, BorderLayout.CENTER);
		
		this.add(selectBtn, BorderLayout.SOUTH);
		selectBtn.addActionListener(this);
		this.pack();  //JFrame의 내용물에 알맞게 윈도우 크기를 조절
		
		this.setVisible(true);
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println(colorChooser.getColor());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("색상선택 완료")) {
			this.remove(panel);
			this.add(drawingPanel, BorderLayout.CENTER);
			drawingPanel.color = colorChooser.getColor();
			selectBtn.setText("색상 바꾸기");
	}else {
		this.remove(drawingPanel);
		this.add(panel,BorderLayout.CENTER);
		selectBtn.setText("색상선택 완료");
	}
	repaint();
	}
}
class MyDrawingColorPanel extends JPanel implements MouseMotionListener{
	Color color = Color.black;
	ArrayList<Point> points = new ArrayList<>();
	
	class Point{
		int x;
		int y;
		Color color;
	}
	
	public MyDrawingColorPanel() {
		this.addMouseMotionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		g.setColor(color);

		for(int i =0; i<points.size();i++) {
			Point p = points.get(i);
			g.setColor(p.color);
			g.drawRect(p.x,p.y,1,1);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = new Point();
		p.x = e.getX();
		p.y = e.getY();
        p.color = color;
        
		points.add(p);
		
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}