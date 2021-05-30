package ch10;

import javax.swing.*;

import ch10.Rectangle;

import java.awt.*;
import java.awt.event.*;


public class DrawRectangle2 extends JFrame{
	public DrawRectangle2() {
		this.setSize(300,300);
		this.setTitle("마우스로 사각형 그리기");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new MyPanel5());
		this.setVisible(true);
		}
		public static void main(String[]args) {
			JFrame frame = new DrawRectangle2();
		}

}
class MyPanel5 extends JPanel {

	Rectangle[] rectangles = new Rectangle[10000];
	int index = 0;

	
	
	public MyPanel5() {
		this.addMouseListener(new MyMouseListener());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Rectangle r : rectangles) {
			if(r==null) break;
			
			g.drawRect(r.x, r.y, r.w, r.h);
		}
	}
	
	class MyMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			
			if(index >= rectangles.length) return;
			
			rectangles[index++] = new Rectangle(e.getX(),e.getY(),50,50);

			
			repaint();
		}
		
	}
}	
	