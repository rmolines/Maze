import java.util.ArrayList;
import java.util.Stack;

public class CPUPlayer extends Player {
	private ArrayList<String> matrix = new ArrayList<String>();
	private int matrixX = 1;
	private int matrixY = 1;
	private char wall = '#';
	private Stack<Marker> stack = new Stack<>();
	
	public CPUPlayer(int cellSize, int width, int height, ArrayList<String> matrix) {
		super(cellSize, width, height);
		this.matrix = matrix;
		stack.push(new Marker(1, matrixX, matrixY));
	}
	
	public void move () {
		if (matrix.get(matrixY).charAt(matrixX+1) != wall) {
			moveRight();
			
		}
		else if (matrix.get(matrixY+1).charAt(matrixX) != wall && ) {
			moveDown();
		}
		else if (matrix.get(matrixY).charAt(matrixX-1) != wall) {
			moveLeft();
		}
		else if (matrix.get(matrixY-1).charAt(matrixX) != wall) {
			moveUp();
		}
	}
	
	private void moveUp () {
		y -= up;
		matrixY -= 1;
		stack.push(new Marker(1, matrixX, matrixY));
		
	}
	
	private void moveDown () {
		y += down;
		matrixY += 1;
		stack.push(new Marker(1, matrixX, matrixY));
	}
	
	private void moveRight () {
		x += right;
		matrixX += 1;
		stack.push(new Marker(1, matrixX, matrixY));
	}
	
	private void moveLeft () {
		x -= left;
		matrixX -= 1;
		stack.push(new Marker(1, matrixX, matrixY));
	}
}
