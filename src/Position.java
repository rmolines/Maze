import java.util.ArrayList;

public class Position {
	private int[] position;
	private int passes = 0;
	private ArrayList<int[]> directions = new ArrayList<>();
	
	public Position (int[] position) {
		this.position = position;
	}
	
	public void addPass() {
		passes++;
	}
	
	public int[] getPosition() {
		return position;
	}
	
	public int getPasses() {
		return passes;
	}
	
	public void addDirection(int[] direction) {
		directions.add(0, direction);
	}
	
	public ArrayList<int[]> getDirections() {
		return directions;
	}
}
