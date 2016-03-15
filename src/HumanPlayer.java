import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class HumanPlayer extends Player{

	public HumanPlayer(int cellSize, int height, int width) {
		super(cellSize, height, width);
		}
	
	public void move(int e) {
    	int key = e;
    	
    	if(key == KeyEvent.VK_LEFT) {
    		x -= left;
    	}
    		
        if(key == KeyEvent.VK_RIGHT) {
        	x += right;
        }
        
    	if(key == KeyEvent.VK_UP) {
    		y -=  up;
    	}
    		
        if(key == KeyEvent.VK_DOWN) {
        	y += down;
        }
	}

}
	