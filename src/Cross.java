import java.util.ArrayList;

public class Cross {
	private ArrayList<Integer> marker = new ArrayList<>();
	private ArrayList<int[]> direction;
	
	public Cross (int [] position){
		this.marker.add(position[0]);
		this.marker.add(position[1]);
	}
	
	public ArrayList<Integer> getCross() {
		return this.marker;
	}
	
	public ArrayList<int[]> getDirection() {
		return this.direction;
	}
	
	public void addDirection(int[] direction) {
		this.direction.add(direction);
	}
}
