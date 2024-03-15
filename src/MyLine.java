import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

class MyLine extends MyShape {
	
	Point p1, p2;

	@Override
	void draw(Graphics2D g) {
		if(this.selected) {
			g.setColor(this.border);
			g.setStroke(new BasicStroke(5.0F));
			g.draw(new Line2D.Float(this.p1.x, this.p1.y, this.p2.x, this.p2.y));
		}
		g.setColor(this.color);
		g.setStroke(new BasicStroke(3.0F));
		g.draw(new Line2D.Float(this.p1.x, this.p1.y, this.p2.x, this.p2.y));
	}

	@Override
	void set_coordinate(int x_coordinate, int y_coordinate) {
		this.p1 = new Point(x_coordinate, y_coordinate);
	}

	@Override
	void set_coordinate2(int x_coordinate, int y_coordinate) {
		this.p2 = new Point(x_coordinate, y_coordinate);
	}
	
	@Override
    boolean isIn(int x_coordinate, int y_coordinate) {
       int t = 5;
       // 허용 오차 설정
      
       int r = (x_coordinate - this.p1.x) * (x_coordinate - this.p1.x) + (y_coordinate - this.p1.y) * (y_coordinate - this.p1.y);
       if (r < t * t) {
          return true;
          // 시작점과의 거리가 허용 오차보다 작을 때 true 반환
       } else {
          r = (x_coordinate - this.p2.x) * (x_coordinate - this.p2.x) + (y_coordinate - this.p2.y) * (y_coordinate - this.p2.y);
          if (r < t * t) {
             return true;
             // 끝점과의 거리가 허용 오차보다 작을 때 true 반환
          } else {
             float ax = (float)(this.p2.x - this.p1.x);
             float ay = (float)(this.p2.y - this.p1.y);
             // (ax, ay) 벡터 생성
            
             float bx = (float)(x_coordinate - this.p1.x);
             float by = (float)(y_coordinate - this.p1.y);
             // (bx, by) 벡터 생성
            
             float l = (float)Math.sqrt((double)(ax * ax + ay * ay));
             if ((double)l < 0.1D) {
                return false;
                // 해당 선분의 길이가 0이면 false 반환
             } else {
                ax /= l;
                ay /= l;
                // 단위 벡터 생성
               
                float dot = ax * bx + ay * by;
                if (dot >= 0.0F && dot <= l) {
             	   // 내적 값이 양수면 같은 방향
                   float cross = ax * by - ay * bx;
                   return Math.abs(cross) < (float)t;
                   // 외적 값이 허용오차보다 작으면 true 반환
                } else {
                   return false;
                }
             }
          }
       }
    }

	@Override
	void move(int x_coordinate, int y_coordinate) {
		this.p1.x += x_coordinate;
		this.p2.x += x_coordinate;
		this.p1.y += y_coordinate;
		this.p2.y += y_coordinate;
	}
	
}