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
import javax.swing.JFrame;

public class LabyrinthGenerator {
	private static final int WIDTH = 15;
	private static final int HEIGHT = 10;	

	private static boolean[][] nodes;
	private static boolean[][] nodes2;
	private static ArrayList<String> matrix = new ArrayList<>();
	private static ArrayList<int[]> randomList = new ArrayList<>();
	private static int[] prizeMatrix;
	private static int[] prize;
	
	private static File file = new File("src/maze/labyrinth.txt");

	private static void createMatrix() {
		try {
			int columns = 0;
			int rows = 0;
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = in.readLine()) != null)
			{
				columns = line.length();
				rows++;
				matrix.add("#"+line+"#");
			}
			String boundary = "#";
			
			for (int i = 0; i<matrix.get(0).length(); i++) {
				boundary = "#"+boundary;
			}
			matrix.add(0, boundary);
			matrix.add(boundary);
			
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		nodes = new boolean [matrix.get(0).length()][matrix.size()];
		nodes2 = new boolean [matrix.get(0).length()][matrix.size()];
		int y = 0;
		int x = 0;
		
		while(y < matrix.size())
		{
			if (matrix.get(y).charAt(x) == '#') {
				nodes[x][y] = false;
				nodes2[x][y] = false;		

			}
			else {
				nodes[x][y] = true;
				nodes2[x][y] = true;
				int[] p = {x, y};
				randomList.add(p);
			}
			x++;
			
			if (x == matrix.get(y).length()-1) {
				y++;
				x = 0;
			}
		}
		Random rand = new Random();
		int  n = rand.nextInt(randomList.size());
		prizeMatrix = new int[] {randomList.get(n)[0], randomList.get(n)[1]};
		prize = new int[] {(int) ((randomList.get(n)[0]*25)-12.5), (int) ((randomList.get(n)[1]*25)-12.5)};
	}
	
	

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
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Screen screen = new Screen(labyrinth, nodes, nodes2, prize, prizeMatrix);
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
