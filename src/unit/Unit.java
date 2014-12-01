package unit;

public abstract class Unit {

	private int speed;
	private int health;
	private int cost;
	private String direction;
	private Position position;
	private Position[] pathHistory 	// För att gubben inte skall gå bakåt
	private boolean reachedGoal;

	public void move(){
		
	}
	
	public void takeDamage(){
		
	}
	
	public boolean isAlive(){
		return true;
	}
	
	// Return true if health <= 0
	public position getPosition(){
		return position;
		
	}
	
	public boolean hasReachedGoal(){
		return true;
	}

	
	
}
