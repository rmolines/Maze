import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> matrix;

	private final static int CELL_SIZE = 25;

	private int width;
	private int height;

	private boolean[][] labyrinth;
	private HumanPlayer humanPlayer = new HumanPlayer(CELL_SIZE, width, height, matrix);
	private CPUPlayer CPUPlayer;

	public Screen(boolean[][] labyrinth, ArrayList<String> matrix) {
		this.labyrinth = labyrinth;
		
		this.width = this.labyrinth[0].length;
		this.height = this.labyrinth.length;
		
		this.matrix = matrix;
		CPUPlayer = new CPUPlayer(CELL_SIZE, width, height, this.matrix);

	
		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
	}

	public void paintComponent(Graphics g) {
		Image humanImage = humanPlayer.image;
		int humanSpriteX = humanPlayer.getX();
		int humanSpriteY = humanPlayer.getY();

		Image CPUImage = CPUPlayer.image;
		int CPUSpriteX = CPUPlayer.getX();
		int CPUSpriteY = CPUPlayer.getY();
		
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
				
				g.drawImage(CPUImage, CPUSpriteX - CELL_SIZE / 2, CPUSpriteY - CELL_SIZE  / 2, CELL_SIZE , CELL_SIZE , null);
				g.drawImage(humanImage, humanSpriteX - CELL_SIZE / 2, humanSpriteY - CELL_SIZE  / 2, CELL_SIZE , CELL_SIZE , null);

			}
		}
		CPUPlayer.checkAndGo();
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
		

	}
		
	public void keyPressed(KeyEvent e) {
	    int key = e.getKeyCode();
	    	
	   	humanPlayer.move(key);
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
