import java.util.ArrayList;
import java.util.Stack;

public class CPUPlayer extends Player {
	private char wall = '#';
	private Stack<Move> stack = new Stack<>();
	boolean returnCross = false;
	
	
	public CPUPlayer(int cellSize, int width, int height, ArrayList<String> matrix) {
		super(cellSize, width, height, matrix);
	}
	
	public void checkAndGo() {
		boolean leftWall = matrix.get(position[1]).charAt(position[0]-1) == wall;
		boolean rightWall = matrix.get(position[1]).charAt(position[0]+1) == wall;
		boolean upWall = matrix.get(position[1]-1).charAt(position[0]) == wall;
		boolean downWall = matrix.get(position[1]+1).charAt(position[0]) == wall;
		int numberOfWalls = ((leftWall)?1:0)+((rightWall)?1:0)+((upWall)?1:0)+((downWall)?1:0);
		if (numberOfWalls == 3) {
			returnCross = true;
		}
		else if (numberOfWalls < 2) {
			returnCross = false;
		}
		if (returnCross && !stack.isEmpty()) {
			int[] nextMove = {-stack.peek().getDirection()[0], -stack.peek().getDirection()[1]};
			stack.pop();
			move(nextMove);
		}
		else if (numberOfWalls < 2) {
			if (!leftWall && stack.peek().getDirection() != leftVector) {
				stack.push(new Move(leftVector, true));
				move(leftVector);
			}
			if (!rightWall && stack.peek().getDirection() != rightVector) {
				stack.push(new Move(rightVector, true));
				move(rightVector);
			}
			if (!upWall && stack.peek().getDirection() != upVector) {
				stack.push(new Move(upVector, true));
				move(upVector);
			}
			if (!downWall && stack.peek().getDirection() != downVector) {
				stack.push(new Move(downVector, true));
				move(downVector);
			}
		}
		else if (!leftWall) {
			move(leftVector);
			System.out.print(position[0]);
			System.out.println(position[1]);
			System.out.println("Left");
		}
		else if (!rightWall) {
			move(rightVector);
			System.out.print(position[0]);
			System.out.println(position[1]);
			System.out.println("Right");
		}
		else if (!upWall) {
			move(upVector);
			System.out.print(position[0]);
			System.out.println(position[1]);
			System.out.println("Up");
		}
		else if (!downWall) {
			move(downVector);
			System.out.print(position[0]);
			System.out.println(position[1]);
			System.out.println("Down");
		}
	}
}
