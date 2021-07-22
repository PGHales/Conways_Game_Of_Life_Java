import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	private Game game;
	private HUD hud;
	private Handler handler;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, 400, 300, 300, 70)) {
				game.gameState = STATE.Game;
				game.removeMouseListener(this);
				game.addMouseListener(hud);
				
				int side = Game.GRID_SIZE;
				int widthOfHUD = hud.getWidthOfHUD();
				int numberOfSquaresInRow = hud.getSideLength() / side;
				for (int i = 0; i < numberOfSquaresInRow; i++) {
					for (int j = 0; j < numberOfSquaresInRow; j++) {
						handler.addObject(new Box(widthOfHUD + j * side, i * side, side, ID.Box, handler, false));
					}
				}
			} else if (mouseOver(mx, my, 400, 400, 300, 70)) {
				game.gameState = STATE.Help;
			} else if (mouseOver(mx, my, 400, 500, 300, 70)) {
				System.exit(1);
			}
		} else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 400, 500, 300, 70)) {
				game.gameState = STATE.Menu;
				return;
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
		if (game.gameState == STATE.Menu) { 
			Font fnt = new Font("arial", 1, 75);
			Font fnt2 = new Font("arial", 1, 35);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game of Life", 325, 200);
			
			g.setFont(fnt2);
			g.drawRect(400, 300, 300, 70);
			g.setColor(Color.black);
			g.fillRect(401, 301, 299, 69);
			g.setColor(Color.white);
			g.drawString("Play", 513, 346);
			
			g.drawRect(400, 400, 300, 70);
			g.setColor(Color.black);
			g.fillRect(401, 401, 299, 69);
			g.setColor(Color.white);
			g.drawString("Help", 513, 446);
			
			g.drawRect(400, 500, 300, 70);
			g.setColor(Color.black);
			g.fillRect(401, 501, 299, 69);
			g.setColor(Color.white);
			g.drawString("Quit", 513, 546);
		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 75);
			Font fnt2 = new Font("arial", 1, 35);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Rules", 450, 175);
			
			g.setFont(fnt3);
			g.drawString("1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.", 150, 275);
			g.drawString("2. Any live cell with two or three live neighbours lives on to the next generation.", 150, 325);
			g.drawString("3. Any live cell with more than three live neighbours dies, as if by overpopulation.", 150, 375);
			g.drawString("4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.", 150, 425);
			
			
			g.setFont(fnt2);
			g.drawRect(400, 500, 300, 70);
			g.setColor(Color.black);
			g.fillRect(401, 501, 299, 69);
			g.setColor(Color.white);
			g.drawString("Back", 510, 546);
		}
		
	}
}
