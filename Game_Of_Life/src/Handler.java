//Used for handling a bunch of different objects, to update and render them to screen
import java.awt.Graphics;
import java.util.ArrayList;
public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	Game game;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		//int count = 0;
		if (game.gameState == STATE.Start) {
			for (int i = 0; i < object.size(); i++) {
				((Box)object.get(i)).update(i);
			}
			for (int i = 0; i < object.size(); i++) {
				Box tempObject = (Box) object.get(i);
				if ((tempObject.getCounter() < 2 || tempObject.getCounter() > 3) && tempObject.isOn()) {
					tempObject.turnOff();
				} else if (tempObject.getCounter() == 3 && !tempObject.isOn()) {
					tempObject.turnOn();
				} 
				tempObject.setCounter(0);
				tempObject.render(g);
			}
		} else {
			for (int i = 0; i < object.size(); i++) {	
				Box tempObject = (Box) object.get(i);
				tempObject.render(g);
			}
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
