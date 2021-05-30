package ch10;

import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class DrawRectangle extends JFrame {
	public DrawRectangle() {
	this.setSize(300,300);
	this.setTitle("마우스로 사각형 그리기");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(new MyPanel4());
	this.setVisible(true);
	}
	public static void main(String[]args) {
		JFrame frame = new DrawRectangle();
	}
}

class Rectangle {
	int x,y,w,h;
	public Rectangle(int x, int y, int w, int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
	}
	public Rectangle() {
		
	}
}

class MyPanel4 extends JPanel implements MouseListener, MouseMotionListener{

	Rectangle[] rectangles = new Rectangle[10000];
	int index = 0;

	Rectangle r = new Rectangle();
	
	
	public MyPanel4() {
//		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		super.paintComponents(g);
		for(Rectangle r : rectangles) {
			if(r==null) break;
			
			g.drawRect(r.x, r.y, 1, 1);
		}
//		g.drawRect(r.x, r.y, r.w, r.h);
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		if(index >= rectangles.length) return;
//		rectangles[index++] = new Rectangle(e.getX(),e.getY(),50,50);
//		r.x = e.getX();
//		r.y = e.getY();
//		r.w = 50;
//		r.h = 50;
//		
//		repaint();
		}

	@Override
	public void mouseReleased(MouseEvent e) {
//		if(index >= rectangles.length) return;
//		rectangles[index++] =new Rectangle(e.getX(),e.getY(),50,50);
//		repaint();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(index >= rectangles.length) return;
			rectangles[index++] = new Rectangle(e.getX(),e.getY(),50,50);
	
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
