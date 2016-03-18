import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CPUPlayer2 extends Player {
	private char wall = '#';
	private Stack<Move> stack = new Stack<>();
	boolean returnCross = false;
	private int[] initi = {0,0};
	private boolean cross = false;


	
	
	public CPUPlayer2(int cellSize, int width, int height, ArrayList<String> matrix) {
		super(cellSize, width, height, matrix);
		stack.push(new Move(initi, false));
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

		if (returnCross) {
			int[] nextMove = {-stack.peek().getDirection()[0], -stack.peek().getDirection()[1]};
			if (stack.peek().isCross()) {
				stack.pop();
			}
			move(nextMove);
		}
		
		else if (numberOfWalls < 2) {
			if(stack.peek().getDirections().size() > 1) {
				if (!leftWall && !stack.peek().getDirections().contains(leftVector)) {
					stack.push(new Move(leftVector, true));
					System.out.println("asda");
					move(leftVector);
				}
				if (!rightWall && !stack.peek().getDirections().contains(rightVector)) {
					stack.push(new Move(rightVector, true));
					move(leftVector);
				}
				if (!upWall && !stack.peek().getDirections().contains(upVector)) {
					stack.push(new Move(upVector, true));
					move(leftVector);
				}
				if (!downWall && !stack.peek().getDirections().contains(downVector)) {
					stack.push(new Move(downVector, true));
					move(leftVector);
				}
				
			}
			else if (!leftWall && !Arrays.equals(stack.peek().getDirection(), rightVector)
					&& !stack.peek().getDirections().contains(leftVector)) {
				cross = true;
				stack.push(new Move(leftVector, cross));
				move(leftVector);
			}
			else if (!rightWall && !Arrays.equals(stack.peek().getDirection(), leftVector)
					&& !stack.peek().getDirections().contains(rightVector)) {
				cross = true;
				stack.push(new Move(rightVector, cross));
				move(rightVector);

			}
			else if (!upWall && !Arrays.equals(stack.peek().getDirection(), downVector)
					&& !stack.peek().getDirections().contains(upVector)) {
				cross = true;
				stack.push(new Move(upVector, cross));
				move(upVector);

			}
			else if (!downWall && !Arrays.equals(stack.peek().getDirection(), upVector)
					&& !stack.peek().getDirections().contains(downVector)) {
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
		

	}
}
