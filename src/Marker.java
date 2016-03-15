import java.util.ArrayList;

public class Marker {
	private ArrayList<Integer> marker = new ArrayList<>();
	
	public Marker (int marker, Array<Integer>){
		this.marker.add(marker);
		this.marker.add(x);
		this.marker.add(y);
	}
	
	public ArrayList<Integer> getMarker() {
		return this.marker;
	}
	
	public void mark() {
		this.marker.add(0,this.marker.get(0)+1);
	}

}
