public class Move {
	private int[] marker;
	private boolean isCross;

	public Move(int[] direction, boolean isCross) {
		marker = direction;
		this.isCross = isCross;
	}
	
	public int[] getDirection() {
		return marker;
	}
	
	public boolean getIsCross() {
		return isCross;
	}
}
