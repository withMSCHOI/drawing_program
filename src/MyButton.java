import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;

import javax.swing.JToggleButton;

class MyButton extends JToggleButton {
	int shape = 0;
	MyPanel panel;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2.0F));
		
		if(this.shape == 0) {
			g.drawLine(10, 10, 40, 40);
		}
		else if(this.shape == 1) {
			g.drawRect(10, 10, 30, 30);
		}
		else if(this.shape == 2) {
			g.drawOval(10, 10, 30, 30);
		}
		else if(this.shape == 3) {
			CubicCurve2D c = new CubicCurve2D.Float();
			c.setCurve(10, 10, 10, 35, 40, 15, 40, 40);
			g2.draw(c);
		}
		else if(this.shape == 4) {
			g.setColor(this.panel.color);
			g.fillRect(10, 10, 30, 30);
		}
		else if(this.shape == 5) {
			int[] x = {25, 10, 20, 20, 30, 30, 40};
			int[] y = {10, 25, 25, 40, 40, 25, 25};
			g.drawPolygon(x, y, 7);
		}
	}
}