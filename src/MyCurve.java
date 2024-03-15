import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

class MyCurve extends MyShape {
	
	ArrayList<Point> list = new ArrayList();

	@Override
	void draw(Graphics2D g) {
		if(this.selected) {
			g.setColor(this.border);
			g.setStroke(new BasicStroke(5.0F));
			
			Point p_before = (Point)this.list.get(0);
			for(Point p : this.list) {
				g.draw(new Line2D.Float(p_before.x, p_before.y, p.x, p.y));
				p_before = p;
			}
		}
		g.setColor(this.color);
		g.setStroke(new BasicStroke(3.0F));
		
		Point p_before = (Point)this.list.get(0);
		for(Point p : this.list) {
			g.draw(new Line2D.Float(p_before.x, p_before.y, p.x, p.y));
			p_before = p;
		}
	}

	@Override
	void set_coordinate(int x_coordinate, int y_coordinate) {
		Point p = new Point(x_coordinate, y_coordinate);
		this.list.add(p);
	}

	@Override
	void set_coordinate2(int x_coordinate, int y_coordinate) {
		Point p = new Point(x_coordinate, y_coordinate);
		this.list.add(p);
	}

	@Override
     boolean isIn(int x_coordinate, int y_coordinate) {
		// MyLine과 유사, 단지 반복적으로 처리할 뿐
        int t = 5;
        Point pt1 = (Point)this.list.get(0);
        int r = (x_coordinate - pt1.x) * (x_coordinate - pt1.x) + (y_coordinate - pt1.y) * (y_coordinate - pt1.y);
        if (r < t * t) {
           return true;
        } else if (this.list.size() < 2) {
           return false;
        } else {
           Iterator var7 = this.list.iterator();

           while(var7.hasNext()) {
              Point pt2 = (Point)var7.next();
              if (pt1 != pt2) {
                 r = (x_coordinate - pt2.x) * (x_coordinate - pt2.x) + (y_coordinate - pt2.y) * (y_coordinate - pt2.y);
                 if (r < t * t) {
                    return true;
                 }

                 float ax = (float)(pt2.x - pt1.x);
                 float ay = (float)(pt2.y - pt1.y);
                 float bx = (float)(x_coordinate - pt1.x);
                 float by = (float)(y_coordinate - pt1.y);
                 float l = (float)Math.sqrt((double)(ax * ax + ay * ay));
                 if ((double)l >= 0.1D) {
                    ax /= l;
                    ay /= l;
                    float dot = ax * bx + ay * by;
                    if (dot >= 0.0F && dot <= l) {
                       float cross = ax * by - ay * bx;
                       if (Math.abs(cross) < (float)t) {
                          return true;
                       }

                       pt1 = pt2;
                    }
                 }
              }
           }

           return false;
        }
     }

	@Override
	void move(int x_coordinate, int y_coordinate) {
		for(Point p : this.list) {
			p.x += x_coordinate;
			p.y += y_coordinate;
		}
	}
}