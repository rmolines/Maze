import java.sql.Array;
import java.util.ArrayList;

public class Cross {
	private ArrayList<Integer> marker = new ArrayList<>();
	
	public Cross (int [] position){
		this.marker.add(1);
		this.marker.add(position[0]);
		this.marker.add(position[1]);
	}
	
	public ArrayList<Integer> getMarker() {
		return this.marker;
	}
	
	public void mark() {
		this.marker.add(0,this.marker.get(0)+1);
	}

}
