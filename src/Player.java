import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
	protected Image image;
	protected int left, right, up, down, x, y, limitLeft, limitRight,limitUp, limitDown;
	
	public Player(int cellSize, int width, int height) {
		left = cellSize;
		right =3/2 * cellSize;
		up = cellSize;
		down =3/2 * cellSize;

		x = cellSize/2;
		y = cellSize/2;
		
		limitLeft = cellSize/2;
		limitRight = cellSize*(width - 1);
		limitUp = cellSize/2;
		limitDown = cellSize*(height - 1);
		
		image = new ImageIcon(getClass().getResource("/img/example.png")).getImage();

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
}
