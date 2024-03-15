import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class MyRect extends MyShape {

	float x1, x2, y1, y2;
	
	@Override
	void draw(Graphics2D g) {
		if(this.selected) {
			g.setColor(this.border);
			g.setStroke(new BasicStroke(3.0F));
			g.draw(new Rectangle2D.Float(x1, y1, x2 - x1, y2 - y1));
		}
		g.setColor(this.color);
		g.setStroke(new BasicStroke(3.0F));
		g.fill(new Rectangle2D.Float(x1, y1, x2 - x1, y2 - y1));
		g.setColor(Color.black);
		g.draw(new Rectangle2D.Float(x1, y1, x2 - x1, y2 - y1));
	}

	@Override
	void set_coordinate(int x_coordinate, int y_coordinate) {
		this.x1 = x_coordinate; this.y1 = y_coordinate;
	}

	@Override
	void set_coordinate2(int x_coordinate, int y_coordinate) {
		this.x2 = x_coordinate; this.y2 = y_coordinate;
	}

	@Override
	boolean isIn(int x_coordinate, int y_coordinate) {
		if(x1 <= x_coordinate && x2 >= x_coordinate && y1 <= y_coordinate && y2 >= y_coordinate)
			return true;
		else
			return false;
	}

	@Override
	void move(int x_coordinate, int y_coordinate) {
		this.x1 += x_coordinate;
		this.x2 += x_coordinate;
		this.y1 += y_coordinate;
		this.y2 += y_coordinate;
	}
	
}