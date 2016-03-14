import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
	protected Image image;
	protected int left;
	protected int right;
	protected int down;
	protected int up;

	protected int x;
	protected int y;
	
	public Player(int cellSize) {
		left = cellSize;
		right =3/2 * cellSize;
		up = cellSize;
		down =3/2 * cellSize;

		x = cellSize/2;
		y = cellSize/2;
		
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
