import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Painter extends JFrame implements KeyListener{
	//constant ints used for grid.
	final int ROWS = 32;
	final int COLS = 32;
	final int GAP = 1;
	
	//panel for grid layout.
	private JPanel pane = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
	//2D array of panels
	private JPanel[][] tiles = new JPanel[ROWS][COLS];
	
	//2D array of Colors, used to hold color of each square.
	private Color[][] tileColor = new Color[ROWS][COLS];
	//Color variables to simplify square color assignment.
	private Color color1 = Color.WHITE;
	private Color color2 = Color.BLACK;
	private Color highlight = Color.YELLOW;
	//ints for user square location (row and column).
	private int hRow = 0;
	private int hCol = 0;
	
	public Painter() {
		//sets up JFrame
		super("Pixel Painter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//adds pane to frame. adds key listener.
		add(pane);
		addKeyListener(this);
		
		//nested for loop to initialize panels, set colors 2d array to white, 
		//sets panels color to white, then adds panels to the grid layout.  
		for (int i = 0 ; i < ROWS ; i++) {
			for (int j = 0 ; j < COLS ; j++) {
				tiles[i][j] = new JPanel();
				tileColor[i][j] = Color.WHITE;
				tiles[i][j].setBackground(tileColor[i][j]);
				pane.add(tiles[i][j]);
			}
		}
		//user's grid location highlighted. 
		tiles[hRow][hCol].setBackground(highlight);
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
		//not used.
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		//integer to store the key code. 
		int keyCode = ke.getKeyCode();
		//if user presses up...
		if (keyCode == KeyEvent.VK_UP) {
			//and there is still a row above...
			if((hRow-1) >= 0) {
				//pulls color for that square from color 2d array, then sets the
				//background color of the panel 2d array using that color. 
				tiles[hRow][hCol].setBackground(tileColor[hRow][hCol]);
				//sets the new square's color to the highlight. 
				tiles[hRow-1][hCol].setBackground(highlight);
				//changes user's position to one square up.
				hRow--;
			}
		}
		//if user presses down...
		if (keyCode == KeyEvent.VK_DOWN) {
			//and there is still a row below...
			if((hRow+1) < ROWS) {
				//pulls color for that square from color 2d array, then sets the
				//background color of the panel 2d array using that color. 
				tiles[hRow][hCol].setBackground(tileColor[hRow][hCol]);
				//sets the new square's color to the highlight. 
				tiles[hRow+1][hCol].setBackground(highlight);
				//change user's position one square down
				hRow++;
			}
		}
		//if user presses left...
		if (keyCode == KeyEvent.VK_LEFT) {
			//and there is still a column to the left...
			if((hCol-1) >= 0) {
				//pulls color for that square from color 2d array, then sets the
				//background color of the panel 2d array using that color. 
				tiles[hRow][hCol].setBackground(tileColor[hRow][hCol]);
				//sets the new square's color to the highlight. 
				tiles[hRow][hCol-1].setBackground(highlight);
				//change user's position one square left
				hCol--;
			}
		}
		//if user presses right...
		if (keyCode == KeyEvent.VK_RIGHT) {
			//and there is still a column to the right...
			if((hCol+1) < COLS) {
				//pulls color for that square from color 2d array, then sets the 
				//background color of the panel 2d array using that color. 
				tiles[hRow][hCol].setBackground(tileColor[hRow][hCol]);
				//sets the new square's color to the highlight.
				tiles[hRow][hCol+1].setBackground(highlight);
				//change user's position one square right
				hCol++;
			}
		}
		//if spacebar is pressed...
		if (keyCode == KeyEvent.VK_SPACE) {
			//if the current color of that square is white, swaps it for black. 
			if (tileColor[hRow][hCol] == color1) {
				tileColor[hRow][hCol] = color2;
			}
			//otherwise, the square is set to white. 
			else 
				tileColor[hRow][hCol] = color1;
		}
		if (keyCode == KeyEvent.VK_S) {
			//save screenshot.
			makeScreenshot(this);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		//not used.
	}

	public static final void makeScreenshot(JFrame argFrame) {
	    Rectangle rec = argFrame.getBounds();
	    BufferedImage bufferedImage = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
	    argFrame.paint(bufferedImage.getGraphics());
	
	    try {
	    	//file directory
	    	File directory = new File("C:\\Users\\frara\\Desktop\\");
	        // Create temp file
//	    	File fileName = new File("testScreenshot0101");
	        File temp = File. createTempFile("testScreenshot0101", ".png", directory); //   createTempFile("testScreenshot", ".png");
	
	        // Use the ImageIO API to write the bufferedImage to a temporary file
	        ImageIO.write(bufferedImage, "png", temp);
	
	        // Delete temp file when program exits
	        //temp.deleteOnExit();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
}
