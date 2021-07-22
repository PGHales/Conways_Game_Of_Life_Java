//Use ctrl + shift + o  to easily import stuff you need

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -4409148208342753357L;
	//Grid_Size is the size of each square in grid, and must not be divisible by 3 and must be > 3
	public static final int GRID_SIZE = 7;
	//Max height is 1115
	public static final int WIDTH = getWidth(GRID_SIZE), HEIGHT = WIDTH / 12 * 9;
	//Create thread. For more complicated games, multiple threads are recommended
	private Thread thread;
	private boolean running = false;
	private HUD hud;
	private Handler handler;
	private Menu menu;
	private int fps;
	public int generations = 0;
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		//Create the game
		handler = new Handler(this);
		hud = new HUD(this, handler, GRID_SIZE);
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Game of Life", this);
		
	}
	
	public synchronized void start() {
		//Start the game
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		//Stop the game
		try {
			//kill of the thread (i.e. wait for it to stop)
			thread.join();
			running = false;
		} catch (Exception e) {
			//Print out the error
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//Request Focus makes it so your computer focuses on the window without having to click it
		this.requestFocus();
		//Create the game loop
		
		long lastTime = System.nanoTime();
		//Amount of ticks per second
		double amountOfTicks = 60.0;
		//Amount of nanoseconds per tick
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			//Calculate delta (change in time)
			delta += (now - lastTime) / ns;
			lastTime = now;
			//Once 1 second has passed, reset delta, and run tick()
			while (delta >= 1) {
				tick();
				delta--;
			}
			//Update the window
			if (running) {
				render();
			}
			//Increase number of frames by one
			frames++;
			//If another second has passed, print out the FPS (frames per second)
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//Print out fps
				fps = frames;
				System.out.println("FPS: " + fps);
				frames = 0;
			}
			
		}
		//Stop the game
		stop();
	}
	
	private void tick() {
		//handler.tick();
		if (gameState == STATE.Game || gameState == STATE.Start) {
			hud.tick();
		} else if (gameState == STATE.Menu) {
			menu.tick();
		} 
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			//3 is recommended. You don't want two, and create 3 buffers in game
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//Render Background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (gameState == STATE.Game || gameState == STATE.Start) {
			hud.render(g);
			handler.render(g);
			
			//List the generations
			if (gameState == STATE.Start) {
				generations++;
			}

			g.setColor(Color.white);
			g.setFont(new Font("arial", 1, 20));
			g.drawString("Generations: " + generations, 10, 89);
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {
			menu.render(g);
		}
		//Display FPS
		g.setColor(Color.white);
		g.setFont(new Font("arial", 1, 20));
		g.drawString((((HEIGHT - 38) / GRID_SIZE) * ((HEIGHT - 38) / GRID_SIZE)) + " squares", 10, 22);
		g.drawString(((HEIGHT - 38) / GRID_SIZE) + " x " + ((HEIGHT - 38) / GRID_SIZE), 10, 45);
		g.drawString("FPS: " + fps, 10, 68);
		
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game();
		System.out.println("Screen Width: " + WIDTH);
	}

	public static int getWidth(int x) {
		//This method returns the width of the screen based on the x value.
		//Essentially, I wanted it all to be dependent on x, or GRID_SIZE
		//This method returns the highest width value for a certain x such that ((x * 9 / 12) - 38) / x is an integer
		//Allows width to be compatible with a 12:9 width:height ratio, and also so that a perfect square grid can be created
		switch (x) {
			case 4:
				//16c + 8, c = 69
				return 1112;
			case 5:
				//20c + 4, c = 55
				return 1104;
			case 7:
				//28c + 4, c = 39 
				return 1096;
			case 8:
				//32c + 8, c = 34
				return 1096;
			default:
				if (x % 3 == 1) {
					if (x < 21) {
						for (int c = 0; true; c++) {
							if ((4 * x * c + 24 - (8 * ((x - 10) / 3))) > 1115) {
								return (4 * x * (c - 1) + 24 - (8 * ((x - 10) / 3)));
							}
						}
					} else {
						for (int c = 0; true; c++) {
							if ((4 * x * c + 80 + (4 * ((x - 22) / 3))) > 1115) {
								return (4 * x * (c - 1) + 80 + (4 * ((x - 22) / 3)));
							}
						}
					}
				} else {
					if (x < 39) {
						for (int c = 0; true; c++) {
							if ((4 * x * c + 36 - (4 * ((x - 11) / 3))) > 1115) {
								return (4 * x * (c - 1) + 36 - (4 * ((x - 11) / 3)));
							}
						}
					} else {
						for (int c = 0; true; c++) {
							if ((4 * x * c + 160 + (8 * ((x - 41) / 3))) > 1115) {
								return (4 * x * (c - 1) + 160 + (8 * ((x - 41) / 3)));
							}
						}
					}
				}
		}
	}

}
