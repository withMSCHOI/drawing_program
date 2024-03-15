import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	private MyPanel panel = new MyPanel();
	
	public MyFrame() {
			setTitle("Homework3");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setContentPane(panel);
			
			setSize(1000, 800);
			setVisible(true);
		
	}
		
	public static void main(String[] args) {
		new MyFrame();
	}
	
}