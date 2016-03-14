import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

import javax.swing.JFrame;

public class LabyrinthGenerator {
<<<<<<< HEAD
	private static final int WIDTH = 15;
	private static final int HEIGHT = 10;	

	private static ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>(20);
	
	private static File file = new File("C:/Users/Rafael/Documents/GitHub/Projeto1-Hashimoto/Labirinth Generator/labyrinth.txt");

	private static void createMatrix() {
		try {
			matriz.add(new ArrayList<String>());
			BufferedReader in = new BufferedReader(new FileReader(file));
			int p;
			int column = 0;
			int line = 0;
			while((p = in.read()) != -1)
			{
				matriz.get(line).add (Character.toString((char) p));
			    column ++;
				System.out.println(in.readLine());
				System.out.println("a");

			    if (in.readLine() != null && column == in.readLine().length() ) {
			    	column = 0;
			    	line ++;
					matriz.add(new ArrayList<String>());
					System.out.println("a");
			    }
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
=======
	protected static final int WIDTH = 15;
	protected static final int HEIGHT = 10;
>>>>>>> origin/master
	

    public static void main(String[] args) {
    	int i, j;
    	

    	Cell[][] cells = new Cell[HEIGHT][WIDTH];

		for(i = 0; i < HEIGHT; i++) {
			for(j = 0; j < WIDTH; j++) {
				cells[i][j] = new Cell(i, j);
			}			
		}

		Edge[] edges = new Edge[HEIGHT * (WIDTH - 1) + (HEIGHT - 1) * WIDTH];

		int k = 0;

		for(i = 0; i < HEIGHT; i++) {
			for(j = 0; j < WIDTH - 1; j++) {
				edges[k] = new Edge(cells[i][j], cells[i][j + 1]);
				k++;
			}			
		}

		for(i = 0; i < HEIGHT - 1; i++) {
			for(j = 0; j < WIDTH; j++) {
				edges[k] = new Edge(cells[i][j], cells[i + 1][j]);
				k++;
			}			
		}
		

		
		
		Random random = new Random();

		ArrayList<Edge> tree = new ArrayList<>();

		for(int length = edges.length; length > 0; length--) {
			int index = random.nextInt(length);

			Edge edge = edges[index];

			Cell u = edge.getU();
			Cell v = edge.getV();

			if(u.union(v)) {
				tree.add(edge);

				if(tree.size() == HEIGHT * WIDTH - 1) {
					break;
				}
			}

			edges[index] = edges[length - 1];
		}

    	boolean[][] labyrinth = new boolean[2 * HEIGHT - 1][2 * WIDTH - 1];

		for(i = 0; i < HEIGHT; i++) {
			for(j = 0; j < WIDTH; j++) {
				labyrinth[2 * i][2 * j] = true;
			}
		}

    	for(Edge edge: tree) {
			Cell u = edge.getU();
			Cell v = edge.getV();

			labyrinth[u.getI() + v.getI()][u.getJ() + v.getJ()] = true;
    	}

		Charset charset = Charset.forName("US-ASCII");

		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println("could not open file");
			System.exit(1);
		}
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, charset));

		for(i = 0; i < labyrinth.length; i++) {
			for(j = 0; j < labyrinth[0].length; j++) {
				if(labyrinth[i][j]) {
					writer.print(' ');
				}
				else {
					writer.print('#');
				}
			}
			writer.println();
		}

		writer.close();
		
		createMatrix();

		System.out.println(matriz);
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Screen screen = new Screen(labyrinth);
                JFrame frame = new JFrame("Labyrinth Generator");
                frame.addKeyListener(screen);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setContentPane(screen);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
