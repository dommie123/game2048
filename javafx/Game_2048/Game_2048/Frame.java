import java.awt.*;
import javax.swing.*;

import com.game.player.Player;

import java.awt.event.*;
import java.util.Random;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2387064293105922354L;

	private static Player player = new Player("Player 1");
	
	// original
	private static int[][] block_Data = new int[4][4];
	// reverse and transpose
	private static int[][] newData = new int[4][4];
	
	private static int[][] newData2 = new int[4][4];

	private static JLabel[][] block_Lable = new JLabel[4][4]; 
	
	private static Label lbPlayer = new Label(player.toString());

	public Frame() {
		initBasic();
		initEmptyBlocks();
		initData();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				int k = event.getKeyCode();
				for (int m = 0; m < 4; m++) {
					for (int n = 0; n < 4; n++) {
						newData2[m][n] = block_Data[m][n];
					}
				}
				switch (k) {
				case 37:
					left();
					break;
				case 38:
					up();
					break;
				case 39:
					right();
					break;
				case 40:
					down();
					break;
				}
				// yesornoMove();
				boolean havemove = yesornoMove();
				if (havemove == true) {
					createOneRandomNumber();
				}
				upDateUI(block_Data);
				gamestate();
			}
		});
		setVisible(true);
	}

	private void initBasic() {
		this.setTitle("Game2048");
		this.setSize(750, 820); 
		this.setLocation(700, 100); 
		getContentPane().setLayout(null); 
		
		lbPlayer.setBounds(326, 739, 377, 36);
		getContentPane().add(lbPlayer);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
	}

	private void initEmptyBlocks() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				block_Lable[j][i] = new JLabel(" ", JLabel.CENTER);
				Font font = new Font("Verdana", Font.BOLD, 55); 
				block_Lable[j][i].setFont(font); 
				block_Lable[j][i].setForeground(Color.darkGray); 
				block_Lable[j][i].setBounds(10 + i * 180, 10 + j * 180, 160, 160);
				block_Lable[j][i].setOpaque(true);
				block_Lable[j][i].setBackground(Color.lightGray);
				getContentPane().add(block_Lable[j][i]);
			}
		}
	}

	public static void initData() {
		for (int n = 0; n < 2; n++) {
			createOneRandomNumber();
		}
	}

	public static void createOneRandomNumber() {
		int i, j;
		Random random = new Random(); 
		i = random.nextInt(4);
		j = random.nextInt(4);
		while (true) {
			if (block_Data[i][j] == 0) {
				break;
			} else {
				i = random.nextInt(4);
				j = random.nextInt(4);
			}
		}
		block_Data[i][j] = 2;
		if (block_Data[i][j] != 0)
			block_Lable[i][j].setText(block_Data[i][j] + ""); 
		else
			block_Lable[i][j].setText(" ");
	}

	public static void upDateUI(int[][] block_Data) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block_Data[i][j] != 0)
					block_Lable[i][j].setText(block_Data[i][j] + "");
				else
					block_Lable[i][j].setText(" ");
			}
		}
	}

	public static void reSetBlocks() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				block_Data[i][j] = 0;
				if (block_Data[i][j] != 0)
					block_Lable[i][j].setText(block_Data[i][j] + "");
				else
					block_Lable[i][j].setText(" ");
			}
		}
	}

	
	public void gamestate() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block_Data[i][j] == 2048) {
					int result = JOptionPane.showConfirmDialog(null, "You win! Your score was " + player.getScore() + "!", "Result",
							JOptionPane.YES_NO_OPTION);
					if (result == 0) {
						Frame.reSetBlocks();
						Frame.initData();
					}
					else {
						System.exit(0);
					}
				}
			}
		}
		if (block_DataIsFull() && !canMove()) {
			int result = JOptionPane.showConfirmDialog(null, "You lose. Your score was " + player.getScore() + ". Play again?", "Result", JOptionPane.YES_NO_OPTION);
			if (result == 0) {
				Frame.reSetBlocks();
				Frame.initData();
			} else {
				System.exit(0);
			}
		}
	}

	private boolean block_DataIsFull() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block_Data[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean canMove() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (block_Data[i][j] == block_Data[i + 1][j] || block_Data[i][j] == block_Data[i][j + 1])
					return true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (block_Data[3][i] == block_Data[3][i + 1])
				return true;
			if (block_Data[i][3] == block_Data[i + 1][3])
				return true;
		}
		return false;
	}

	private static boolean yesornoMove() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block_Data[i][j] != newData2[i][j])
					return true;
			}
		}
		return false;
	}

	public static void reverse() {
		for (int i = 0; i <= 3; i++)
			for (int j = 0; j <= 3; j++)
				newData[i][j] = block_Data[3 - i][3 - j];

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				block_Data[i][j] = newData[i][j];
	}

	public static void transpose() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				newData[i][j] = block_Data[j][i];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				block_Data[i][j] = newData[i][j];
	}

	public static void coverup() {
		for (int k = 0; k <= 2; k++)
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 3; j++) 
					if (block_Data[i][j] == 0) {
						block_Data[i][j] = block_Data[i][j + 1];
						block_Data[i][j + 1] = 0;
					}

	}

	public static void merge() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
				if (block_Data[i][j] == block_Data[i][j + 1] && block_Data[i][j] != 0) {
					player.setScore(player.getScore() + block_Data[i][j] * 10);
					lbPlayer.setText(player.toString());
					block_Data[i][j] = block_Data[i][j] * 2;
					block_Data[i][j + 1] = 0;
				}
	}

	public static void up() {
		transpose();
		coverup();
		merge();
		coverup();
		transpose();
	}

	public static void down() {
		transpose();
		reverse();
		coverup();
		merge();
		coverup();
		reverse();
		transpose();
	}

	public static void left() {
		coverup();
		merge();
		coverup();
	}

	public static void right() {
		reverse();
		coverup();
		merge();
		coverup();
		reverse();
	}
}
