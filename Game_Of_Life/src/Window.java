import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {
	
	private static final long serialVersionUID = -3911434927321204623L;

	public Window (int width, int height, String title, Game game) {
		//Create window from JFrame JRE
		JFrame frame = new JFrame(title);
		
		//Set the size of the window
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//Terminates program when you press the "X" button on top right of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Makes it so you can't resize the window
		frame.setResizable(false);
		//Makes it so the window starts in the middle, and not top left corner
		frame.setLocationRelativeTo(null);
		//Add gameMain class to the window
		frame.add(game);
		//Allows you to see the window
		frame.setVisible(true);
		//Start the game
		game.start();
	}
}
