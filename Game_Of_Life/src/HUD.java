import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HUD extends MouseAdapter {
	//Set up important values
	//38 = reduction factor for y, assuming width = 1100
	//16 = reduction factor for x, assuming width = 1100
	private int sideLength = Game.HEIGHT - 38;
	private int side;
	private int widthOfHUD = (Game.WIDTH - sideLength) - 16;
	
	private Game game;
	private Handler handler;
	
	public HUD(Game game, Handler handler, int size) {
		this.game = game;
		this.handler = handler;
		side = size;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == STATE.Game) {
			
			if (mouseOver(mx, my, widthOfHUD, 0, sideLength, sideLength)) {
				Box tempObject = (Box) handler.object.get((mx - widthOfHUD) / side + my / side * (sideLength / side));
				if (tempObject.isOn()) {
					tempObject.turnOff();
				} else {
					tempObject.turnOn();
				}
			} else if (mouseOver(mx, my, widthOfHUD / 4, 100, widthOfHUD / 2, 70)) {
				//Hit Start Button
				game.gameState = STATE.Start;
			} else if (mouseOver(mx, my, widthOfHUD / 4, 200, widthOfHUD / 2, 70)) {
				//Reset
				for (int i = 0; i < handler.object.size(); i++) {
					Box tempObject = (Box) handler.object.get(i);
					tempObject.turnOff();
					tempObject.setCounter(0);
				}
				game.generations = 0;
			}
		} else {
			if (mouseOver(mx, my, widthOfHUD / 4, 100, widthOfHUD / 2, 70)) {
				//Hit Stop Button
				game.gameState = STATE.Game;
			} 
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//Draw Game Buttons
		Font fnt = new Font("arial", 1, 25);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawRect(widthOfHUD / 4, 100, widthOfHUD / 2, 70);
		g.setColor(Color.black);
		g.fillRect(widthOfHUD / 4 + 1, 101, widthOfHUD / 2 - 1, 69);
		g.setColor(Color.white);
		if (game.gameState == STATE.Game) {
			g.drawString("Start", widthOfHUD / 4 + 45, 143);
			
			g.drawRect(widthOfHUD / 4, 200, widthOfHUD / 2, 70);
			g.setColor(Color.black);
			g.fillRect(widthOfHUD / 4 + 1, 201, widthOfHUD / 2 - 1, 69);
			g.setColor(Color.white);
			g.drawString("Reset", widthOfHUD / 4 + 45, 243);
		} else {
			g.drawString("Stop", widthOfHUD / 4 + 45, 143);
		}

		//Draw Game Square
		g.setColor(Color.green);
		g.drawRect(widthOfHUD, 0, sideLength, sideLength);
		g.setColor(Color.black);
		g.fillRect(widthOfHUD + 1, 1, sideLength - 1, sideLength - 1);
	}
	
	public int getSideLength() {
		return sideLength;
	}
	
	public int getWidthOfHUD() {
		return widthOfHUD;
	}
}
