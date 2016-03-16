import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	protected Image image;
	protected int left, right, up, down, x, y;
	protected int[] position = {1,1};
	protected ArrayList<String> matrix;

	
	protected int [] leftVector = {-1, 0};
	protected int [] rightVector = {1, 0};
	protected int [] upVector = {0, -1};
	protected int [] downVector = {0, 1};
	
	protected int[] boundaries = new int [4];

	
	public Player(int cellSize, int width, int height, ArrayList<String> matrix) {
		left = cellSize;
		right =3/2 * cellSize;
		up = cellSize;
		down =3/2 * cellSize;

		x = cellSize/2;
		y = cellSize/2;
		
		boundaries = new int[] {cellSize/2, cellSize*(width-1), cellSize/2, cellSize*(height-1)};
		
		
		image = new ImageIcon(getClass().getResource("/img/example.png")).getImage();
		
		this.matrix = matrix;
		
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}	
	
	protected void move(int[] direction) {
		position[0] += direction[0];
		position[1] += direction[1];
		if (direction[0] == 1) {
			x += right;
		}
		if (direction[0] == -1) {
			x -= left;
		}
		if (direction[1] == 1) {
			y += down;
		}
		if (direction[1] == -1) {
			y -= up;
		}
	}
}
