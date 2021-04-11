package com.game.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8951666509636637803L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/img/logo.png")));
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 230);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][]", "[][][][][][][]"));
		
		JLabel lblTitle = new JLabel("2048 Game");
		lblTitle.setFont(new Font("Cinzel Black", Font.BOLD, 32));
		contentPane.add(lblTitle, "cell 2 0");
		
		JLabel lblSubtitle = new JLabel("Developed by SLCC Coders\r\n");
		contentPane.add(lblSubtitle, "cell 2 1");
		
		JButton btnNewGame = new JButton("New Game\r\n");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Frame loadGame = new Frame();
				loadGame.setVisible(true);
			}
		});
		contentPane.add(btnNewGame, "cell 2 4,growx");
		
		JButton btnHighScores = new JButton("High Scores\r\n");
		contentPane.add(btnHighScores, "cell 2 5,growx");
		
		JButton btnExit = new JButton("Exit Game\r\n");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.add(btnExit, "cell 2 6,growx");
	}

}
