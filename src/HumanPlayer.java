import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class HumanPlayer extends Player{
	private boolean movement = true;

	public HumanPlayer(int cellSize, int height, int width, boolean[][]nodes, int[] prize) {
		super(cellSize, height, width, nodes, prize);
		}
	
	public void playerMove(int e) {
    	int key = e;
    	int x = position[0];
    	int y = position[1];

    	if(key == KeyEvent.VK_LEFT && nodes[x-1][y] && movement) {
    		move(leftVector);
    	}
    		
        if(key == KeyEvent.VK_RIGHT && nodes[x+1][y] && movement) {
        	move(rightVector);
        }
        
    	if(key == KeyEvent.VK_UP && nodes[x][y-1] && movement) {
    		move(upVector);
    	}
    		
        if(key == KeyEvent.VK_DOWN && nodes[x][y+1] && movement) {
        	move(downVector);
        }
	}
	
	public void stopMoving() {
		movement = false;
	}
	
	public void goToPrize(int[] prize) {
		x = prize[0];
		y = prize[1];
	}
}
	