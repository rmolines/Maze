import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> matrix;
	private boolean[][] nodes;
	private boolean[][] humanNodes;
	private int[] prize;
	private boolean start = true;

	Image youLose = new ImageIcon(getClass().getResource("/img/youlose.png")).getImage();
	Image youWin = new ImageIcon(getClass().getResource("/img/youwin.png")).getImage();
	Image intro = new ImageIcon(getClass().getResource("/img/CATCH.PNG")).getImage();


	private final static int CELL_SIZE = 25;

	private int width;
	private int height;
	
	private int[] prizeMatrix;
	private boolean[][] labyrinth;
	private HumanPlayer humanPlayer;
	private CPUPlayer CPUPlayer;
	
	public Screen (boolean[][] labyrinth, boolean[][]nodes, boolean[][] nodes2, int[] prize, int[] prizeMatrix) {
		this.labyrinth = labyrinth;
		
		this.width = this.labyrinth[0].length;
		this.height = this.labyrinth.length;
		
		this.prizeMatrix = prizeMatrix;
		
		this.nodes = nodes;
		humanNodes =nodes2;
		CPUPlayer = new CPUPlayer(CELL_SIZE, width, height, this.nodes, prizeMatrix);
		humanPlayer = new HumanPlayer(CELL_SIZE, width, height, this.humanNodes, prizeMatrix);
		
		this.prize = prize;
	
		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
	}


	public void paintComponent(Graphics g) {

		if (humanPlayer.playerWins()) {	
			humanPlayer.goToPrize(prize);
			g.drawImage(youWin, 95, 170, 500 , 125 , null);
			humanPlayer.stopMoving();
			repaint();

		}
		
		else if (CPUPlayer.playerWins()) {			
			g.drawImage(youLose, 110, 170, 500 , 125 , null);

		}

		else {
		Image humanImage = humanPlayer.image;
		int humanSpriteX = humanPlayer.getX();
		int humanSpriteY = humanPlayer.getY();

		Image CPUImage = CPUPlayer.image;
		int CPUSpriteX = CPUPlayer.getX();
		int CPUSpriteY = CPUPlayer.getY();
		
		Image prizeImage = new ImageIcon(getClass().getResource("/img/pikachu.png")).getImage();
		prizeImage.getScaledInstance(72, 72, Image.SCALE_DEFAULT);
		
		for(int i = 0; i < this.height; i++) {
			int y = i * CELL_SIZE;

			for(int j = 0; j < this.width; j++) {
				int x = j * CELL_SIZE;
				

				
				if(labyrinth[i][j]) {
					g.setColor(Color.WHITE);					
				}
				else {
					g.setColor(Color.BLACK);				
				}
				
				g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
				
				g.drawImage(prizeImage, prize[0] - CELL_SIZE / 2, prize[1] - CELL_SIZE  / 2, CELL_SIZE , CELL_SIZE , null);
				g.drawImage(CPUImage, CPUSpriteX - CELL_SIZE / 2, CPUSpriteY - CELL_SIZE  / 2, CELL_SIZE , CELL_SIZE , null);
				g.drawImage(humanImage, humanSpriteX - CELL_SIZE / 2, humanSpriteY - CELL_SIZE  / 2, CELL_SIZE , CELL_SIZE , null);
				


			}


			}
		CPUPlayer.mazeSolver();
		
		try {
			TimeUnit.MILLISECONDS.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		repaint();
		
		}

	}
		
	public void keyPressed(KeyEvent e) {
		System.out.print(prizeMatrix[0]);
		System.out.println(prizeMatrix[1]);
		System.out.print(humanPlayer.getPosition()[0]);
		System.out.println(humanPlayer.getPosition()[1]);



	    int key = e.getKeyCode();

	   	humanPlayer.playerMove(key);
	   	repaint();

	   	getToolkit().sync();
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
