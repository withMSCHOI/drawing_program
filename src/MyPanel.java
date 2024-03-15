import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class MyPanel extends JPanel {
	
	MyButton[] btn = new MyButton[6];
	Color color = Color.BLACK;
	int drawWhat = 0;
	LinkedList<MyShape> list = new LinkedList<MyShape>();
	Point point = new Point(0, 0);
	
	MyPanel() {
		for(int i = 0; i < 6; i++) {
			btn[i] = new MyButton();
			btn[i].panel = this;
			btn[i].shape = i;
			btn[i].setPreferredSize(new Dimension(50, 50));
			btn[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for(int j = 0; j < 6; j++)
						MyPanel.this.btn[j].setSelected(false);
					((MyButton)e.getSource()).setSelected(true);
					if(((MyButton)e.getSource()).shape == 4) 
						color = JColorChooser.showDialog(null, "Color", Color.BLACK);
					else
						MyPanel.this.drawWhat = ((MyButton)e.getSource()).shape;
					
					if(MyPanel.this.drawWhat != 5) {
						for(MyShape s : MyPanel.this.list)
							s.selected = false;
					}
					setFocusable(true);
					requestFocus();
				}
				
			});
			add(btn[i]);
		}
		
		this.btn[0].setSelected(true);
		this.drawWhat = 0;
		
		addMouseListener(new MyPanel.MyMouseAdapter());
		addMouseMotionListener(new MyPanel.MyMouseMotionAdapter());
		addKeyListener(new MyPanel.MyKeyAdapter());
		
		setFocusable(true);
		requestFocus();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		for(MyShape s : MyPanel.this.list)
			s.draw(g2);
	}
	
	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				MyPanel.this.point = new Point(e.getX(), e.getY());
				int x;
				int y;
				if(MyPanel.this.drawWhat < 4) {
					x = e.getX();
					y = e.getY();
					MyShape s;
					switch(MyPanel.this.drawWhat) {
					case 0:
						s = new MyLine(); break;
					case 1:
						s = new MyRect(); break;
					case 2:
						s = new MyCircle(); break;
					case 3:
						s = new MyCurve(); break;
					default:
						return;
					}
					s.set_coordinate(x, y);
					s.color = MyPanel.this.color;
					MyPanel.this.list.add(s);
					MyPanel.this.repaint();
				}
				
				if(MyPanel.this.drawWhat == 5) {
					for(MyShape s : MyPanel.this.list)
						s.selected = false;
					MyShape choice = null;
					for(MyShape s : MyPanel.this.list) 
						if(s.isIn(e.getX(), e.getY()))
							choice = s;
					if(choice != null)
						choice.selected = true;
					MyPanel.this.repaint();
				}
			}
		}
	}
	
	class MyMouseMotionAdapter extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)) {
				if(MyPanel.this.drawWhat < 4) 
					MyPanel.this.list.getLast().set_coordinate2(e.getX(), e.getY());
				if(MyPanel.this.drawWhat == 5) {
					int move_x = e.getX() - MyPanel.this.point.x;
					int move_y = e.getY() - MyPanel.this.point.y;
					for(MyShape s : MyPanel.this.list)
						if(s.selected) 
							s.move(move_x, move_y);
					MyPanel.this.point.x = e.getX();
					MyPanel.this.point.y = e.getY();
				}
				MyPanel.this.repaint();
			}
		}
	}
	
	class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DELETE) {
				for(MyShape s : MyPanel.this.list) {
					if(s.selected) {
						MyPanel.this.list.remove(s);
						MyPanel.this.repaint();
					}
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				MyPanel.this.list.removeLast();
				MyPanel.this.repaint();
			}
		}
	}
}