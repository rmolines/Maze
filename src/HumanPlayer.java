import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class HumanPlayer extends Player{

	public HumanPlayer(int cellSize, int height, int width, ArrayList<String> matrix) {
		super(cellSize, height, width, matrix);
		}
	
	public void move(int e) {
    	int key = e;

    	if(key == KeyEvent.VK_LEFT) {
    		move(leftVector);
    	}
    		
        if(key == KeyEvent.VK_RIGHT) {
        	move(rightVector);
        }
        
    	if(key == KeyEvent.VK_UP) {
    		move(upVector);
    	}
    		
        if(key == KeyEvent.VK_DOWN) {
        	move(downVector);
        }
	}

}
	