//Use alt + shift + s to generate getters and setters!

import java.awt.Graphics;

//All objects in the game will be under GameObject
public abstract class GameObject {
	
	//Can only be accessed by an object that inherits it
	protected int x, y;
	protected ID id;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
		this.y = y;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ID getID() {
		return id;
	}
}
