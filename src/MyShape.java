import java.awt.Color;
import java.awt.Graphics2D;

abstract class MyShape {
	Color color = Color.BLACK;
	boolean selected = false;
	final Color border = Color.RED;
	
	abstract void draw(Graphics2D g);
	abstract void set_coordinate(int x_coordinate, int y_coordinate);
	abstract void set_coordinate2(int x_coordinate, int y_coordinate);
	abstract boolean isIn(int x_coordinate, int y_coordinate);
	abstract void move(int x_coordinate, int y_coordinate);
}