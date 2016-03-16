import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CPUPlayer extends Player {
	private char wall = '#';
	private Stack<Move> stack = new Stack<>();
	boolean returnCross = false;
	private int[] init = {0,0};
	private boolean cross = false;


	
	
	public CPUPlayer(int cellSize, int width, int height, ArrayList<String> matrix) {
		super(cellSize, width, height, matrix);
		stack.push(new Move(init, false));
	}
	
	public void checkAndGo() {
		boolean leftWall = matrix.get(position[1]).charAt(position[0]-1) == wall;
		boolean rightWall = matrix.get(position[1]).charAt(position[0]+1) == wall;
		boolean upWall = matrix.get(position[1]-1).charAt(position[0]) == wall;
		boolean downWall = matrix.get(position[1]+1).charAt(position[0]) == wall;
		int numberOfWalls = ((leftWall)?1:0)+((rightWall)?1:0)+((upWall)?1:0)+((downWall)?1:0);
		
		if (numberOfWalls == 3 && cross) {
			returnCross = true;

		}
		else if (numberOfWalls < 2) {
			returnCross = false;
			System.out.println("true");

		}		

		if (returnCross) {
			int[] nextMove = {-stack.peek().getDirection()[0], -stack.peek().getDirection()[1]};
			stack.pop();
			move(nextMove);
		}
		else if (numberOfWalls < 2) {
			if (!leftWall && !Arrays.equals(stack.peek().getDirection(), rightVector)) {
				cross = true;
				stack.push(new Move(leftVector, cross));
				move(leftVector);
			}
			else if (!rightWall && !Arrays.equals(stack.peek().getDirection(), leftVector)) {
				cross = true;
				stack.push(new Move(rightVector, cross));
				move(rightVector);

			}
			else if (!upWall && !Arrays.equals(stack.peek().getDirection(), downVector)) {
				cross = true;
				stack.push(new Move(upVector, cross));
				move(upVector);

			}
			else if (!downWall && !Arrays.equals(stack.peek().getDirection(), upVector)) {
				cross = true;
				stack.push(new Move(downVector, cross));
				move(downVector);

			}
		}
		else if (!leftWall && !Arrays.equals(stack.peek().getDirection(), rightVector)) {
			move(leftVector);
			stack.push(new Move(leftVector, false));
		}
		else if (!rightWall && !Arrays.equals(stack.peek().getDirection(), leftVector)) {
			move(rightVector);
			stack.push(new Move(rightVector, false));
		}
		else if (!upWall && !Arrays.equals(stack.peek().getDirection(), downVector)) {
			move(upVector);
			stack.push(new Move(upVector, false));
		}
		else if (!downWall && !Arrays.equals(stack.peek().getDirection(), upVector)) {
			move(downVector);
			stack.push(new Move(downVector, false));
		}
		
		if (numberOfWalls == 3 && cross) {
			returnCross = true;
			System.out.println("true");

		}
		else if (numberOfWalls < 2) {
			returnCross = false;
		}
	}
}
