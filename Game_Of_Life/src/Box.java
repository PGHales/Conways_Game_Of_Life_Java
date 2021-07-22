import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Box extends GameObject {
	
	Random r = new Random();
	private int size;
	boolean isOn = false;
	private Handler handler;
	private int counter = 0;

	public Box(int x, int y, int size, ID id, Handler handler, boolean isOn) {
		super(x, y, id);
		this.size = size;
		this.handler = handler;
		this.isOn = isOn;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect(x, y, size, size);
		if (!isOn) {
			g.setColor(Color.black);
		}
		g.fillRect(x + 1, y + 1, size - 1, size - 1);
	}
	
	public void update(int index) {
		//Basically, this method counts the number of squares that are alive around the square at index.
		//It is programmed to ensure that it doesn't go out of bounds
		int sideLength = Game.HEIGHT - 38;
		if ((index % (sideLength / size) != 0) && ((Box)handler.object.get(index - 1)).isOn()) {
			counter++;
		}
		if ((index % (sideLength / size) != sideLength / size - 1) && ((Box)handler.object.get(index + 1)).isOn()) {
			counter++;
		}
		if ((index / (sideLength / size) != 0) && (index % (sideLength / size) != 0) && ((Box)handler.object.get(index - 1 - sideLength / size)).isOn()) {
			counter++;
		}
		if ((index / (sideLength / size) != 0) && ((Box)handler.object.get(index - sideLength / size)).isOn()) {
			counter++;
		}
		if ((index / (sideLength / size) != 0) && (index % (sideLength / size) != sideLength / size - 1) && ((Box)handler.object.get(index + 1 - sideLength / size)).isOn()) {
			counter++;
		}
		if ((index / (sideLength / size) != sideLength / size - 1) && (index % (sideLength / size) != 0) && ((Box)handler.object.get(index - 1 + sideLength / size)).isOn()) {
			counter++;
		}
		if ((index / (sideLength / size) != sideLength / size - 1) && ((Box)handler.object.get(index + sideLength / size)).isOn()) {
			counter++;
		}
		if ((index / (sideLength / size) != sideLength / size - 1) && (index % (sideLength / size) != sideLength / size - 1) && ((Box)handler.object.get(index + 1 + sideLength / size)).isOn()) {
			counter++;
		}
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int x) {
		counter = x;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public void turnOn() {
		isOn = true;
	}
	
	public void turnOff() {
		isOn = false;
	}

}
