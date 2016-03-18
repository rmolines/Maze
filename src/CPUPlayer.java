import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CPUPlayer extends Player {
	private Stack<Position> stack = new Stack<>();
	
	
	public CPUPlayer(int cellSize, int width, int height, boolean[][] nodes, int[] prize) {
		super(cellSize, width, height, nodes, prize);
		
		stack.push(new Position(position));
	}
	
	public void mazeSolver () {
		int x = position[0];
		int y = position[1];
		
		if (!stack.isEmpty()) {
			Position top = stack.peek();
			
			if(top.getPasses() == 0) {
				
				if (nodes[x-1][y]) {					
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(leftVector);
					top.addDirection(leftVector);
				}
				
				else if (nodes[x][y+1]) {
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(downVector);
					top.addDirection(downVector);
				}
				
				else if (nodes[x+1][y]) {
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(rightVector);
					top.addDirection(rightVector);
				}
				
				else if (nodes[x][y-1]) {					
						stack.push(new Position(position));
						nodes[x][y] = false;
						move(upVector);
						top.addDirection(upVector);
				}
				top.addPass();
			}
				
			else if(top.getPasses() == 1){	
				
				if (nodes[x][y+1]) {
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(downVector);
					top.addDirection(downVector);
				}		
				
				else if (nodes[x+1][y]) {
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(rightVector);
					top.addDirection(rightVector);
				}
				
				else if (nodes[x][y-1]) {					
						stack.push(new Position(position));
						nodes[x][y] = false;
						move(upVector);
						top.addDirection(upVector);
				}
				top.addPass();
			}
			else if (top.getPasses()==2) {
				
				if (nodes[x+1][y]) {					
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(rightVector);
					top.addDirection(rightVector);
				}
			
				else if (nodes[x][y-1]) {
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(upVector);
					top.addDirection(upVector);
				}
				top.addPass();
			}
			else if (top.getPasses()==3){
				if (nodes[x][y-1]) {
					stack.push(new Position(position));
					nodes[x][y] = false;
					move(upVector);
					top.addDirection(upVector);
				}
				top.addPass();
			}
			else {
				stack.pop();
				int[] vector = {-stack.peek().getDirections().get(0)[0], -stack.peek().getDirections().get(0)[1]};
				nodes[x][y] = false;
				move(vector);
				
			}		
		}		
	}
}
