
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import com.game.player.*;

public class Grid extends GridPane{

	//variables
	public static Player player = new Player("Player 1");
	public static StackPane[][] boxes;	//double array of Panes for the board
	int gridSize;
	int boxSize;
	public static int[][] block_Data = new int[4][4];
	public static int[][] newData = new int[4][4];
	public static int[][] newData2 = new int[4][4];
	static Text text = new Text("");
	
	//constructor
	public Grid(int gridSize, int boxSize) {
		    	
    	super();
    	this.gridSize = gridSize;
    	this.boxSize = boxSize;
        boxes = new StackPane[gridSize][gridSize]; //place where the pieces will go
        
        for (int i = 0; i < gridSize; i++)
            getRowConstraints().add( new RowConstraints(boxSize));
     
        for (int i = 0; i < gridSize; i++)
            getColumnConstraints().add( new ColumnConstraints(boxSize));
           
		initEmptyBlocks();
		initData();
	}
	
	public static void setKeyListeners(Scene scene) {
		scene.setOnKeyPressed(event ->{
        	for(int m=0; m<4; m++){
        		for(int n=0; n<4; n++){
		    	  	newData2[m][n]=block_Data[m][n];
		    	 }
		    }
    		switch (event.getCode()) {
				case UP:
	            	System.out.println("left");
	            	left();
	            	break;
				case LEFT:
	            	System.out.println("up");
	            	up();
	            	break;
				case DOWN:
	            	System.out.println("right");
	            	right();
	            	break;
				case RIGHT:
	            	System.out.println("down");
	            	down();
	            	break;
				default:
					System.out.println("nothing");
					break;
    		}
    		
    		boolean havemove = yesornoMove();
			if(havemove == true) {
				createRandomNumber();
			}
			updateUI(block_Data);
			gamestate();
		});
	}
		
	private void initEmptyBlocks()	{
		for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {//for each box in the board
            	StackPane box = new StackPane(); //create a pane (or a new box)
            	text.setStyle("-fx-font: 60 Arial;");
                text.setTextAlignment(TextAlignment.CENTER);
                box.getChildren().add(text);
                final String cssDefault = 
                        "-fx-border-width: 20px;\n"
                      + "-fx-background-color: #bf9469 ;";
                box.setStyle(cssDefault); 
                StackPane.setAlignment(text, Pos.CENTER);
                setBox(i, j, box); //set the box to the boxes double-array
                add(box, i, j); //add the box to the board*/
            }
        }
	}
	
	public static void initData() {
		for(int n=0; n<2; n++){
			createRandomNumber();
		}
	}
	
	public static void createRandomNumber()	{
		int i,j;
		Random random = new Random();					
		i = random.nextInt(4);
		j = random.nextInt(4);
		while(true)	{
			if(block_Data[i][j] == 0){
				break;
			}
			else {
				i = random.nextInt(4);	
				j = random.nextInt(4);
			}
		}
		block_Data[i][j]=2;
		while( !(boxes[i][j].getChildren().isEmpty()) ) {
			boxes[i][j].getChildren().remove(0);
		}
		if(block_Data[i][j]!=0) {
			Text newText = new Text(block_Data[i][j]+"");
			newText.setStyle("-fx-font: 60 Arial;");
			boxes[i][j].getChildren().add(newText);
		}
		else {
			boxes[i][j].getChildren().add(new Text(""));
		}
	}
	
	public static void updateUI(int[][] block_Data)	{
	
		for(int i=0; i<4; i++)	{
			for(int j=0; j<4; j++)	{
				while( !(boxes[i][j].getChildren().isEmpty()) ) {
					boxes[i][j].getChildren().remove(0);
				}
				if(block_Data[i][j]!=0) {
					Text newText = new Text(block_Data[i][j]+"");
					newText.setStyle("-fx-font: 60 Arial;");
					boxes[i][j].getChildren().add(newText);
				}
				else {
					boxes[i][j].getChildren().add(new Text(""));
				}
				
				switch(block_Data[i][j]) {
				case 4:
					boxes[i][j].setStyle("-fx-background-color: #FFE4C3 ;");
					break;
				case 8:
					boxes[i][j].setStyle("-fx-background-color: #fff4d3 ;");
					break;
				case 16:
					boxes[i][j].setStyle("-fx-background-color: #ffdac3 ;");
					break;
				case 32:
					boxes[i][j].setStyle("-fx-background-color: #e7b08e ;");
					break;
				case 64:
					boxes[i][j].setStyle("-fx-background-color: #e7bf8e ;");
					break;
				case 128:
					boxes[i][j].setStyle("-fx-background-color: #ffc4c3 ;");
					break;
				case 256:
					boxes[i][j].setStyle("-fx-background-color: #E7948e ;");
					break;
				case 512:
					boxes[i][j].setStyle("-fx-background-color: #be7e56 ;");
					break;
				case 1024:
					boxes[i][j].setStyle("-fx-background-color: #be5e56 ;");
					break;
				case 2048:
					boxes[i][j].setStyle("-fx-background-color: #9c3931 ;");
					break;
				default:
					boxes[i][j].setStyle("-fx-background-color: #bf9469 ;");
					break;
				}
			}
		}
	}
	
	public static void resetBlocks(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				block_Data[i][j] = 0;
				while( !(boxes[i][j].getChildren().isEmpty()) ) {
					boxes[i][j].getChildren().remove(0);
				}
				if(block_Data[i][j]!=0) {
					Text newText = new Text(block_Data[i][j]+"");
					newText.setStyle("-fx-font: 60 Arial;");
					boxes[i][j].getChildren().add(newText);
				}
				else {
					boxes[i][j].getChildren().add(new Text(""));
				}
				boxes[i][j].setStyle("-fx-background-color: #bf9469 ;");
			}
		}
	}
	
	public static void gamestate(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(block_Data[i][j] == 2048)
				{
					int result = JOptionPane.showConfirmDialog(null, "You win. Again？", "Result",JOptionPane.YES_NO_OPTION);
					if(result == 0) {
						resetBlocks();
						initData();
					}
					else{
						System.exit(0);
					}
				}
			}
		}
		
		if(block_DataIsFull() && !canMove()){
			int result = JOptionPane.showConfirmDialog(null, "You lose. Again？", "Result", JOptionPane.YES_NO_OPTION);
			if(result == 0)	{
				resetBlocks();
				initData();
			}
			else {
				System.exit(0);
			}
		}
	}
	
	private static boolean block_DataIsFull(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(block_Data[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean canMove(){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if( block_Data[i][j]==block_Data[i+1][j] || block_Data[i][j]==block_Data[i][j+1] )
					return true;
			}
		}
		for(int i=0; i<3; i++) {
			if(block_Data[3][i]==block_Data[3][i+1])
				return true;
			if(block_Data[i][3]==block_Data[i+1][3])
				return true;
		}
		return false;
	}
	
	public static boolean yesornoMove()	{
		for(int i=0; i<4; i++) {  
			for(int j=0; j<4; j++) {
				if(block_Data[i][j]!=newData2[i][j])
		        return true; 
		    }
	    }
		return false;
	}
	
	public  static void reverse(){
		for(int i=0; i<=3; i++)
			for(int j=0; j<=3; j++)
				newData[i][j]=block_Data[3-i][3-j];
		
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				block_Data[i][j]=newData[i][j];	
	}
	
	public static void transpose(){
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				newData[i][j]=block_Data[j][i];
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				block_Data[i][j]=newData[i][j];	
	}
	
	public static void coverup(){
	for(int k=0;k<=2;k++)				
		for(int i=0; i<4; i++)
			for(int j=0; j<3; j++)		
				if(block_Data[i][j]==0)
				{
					block_Data[i][j]=block_Data[i][j+1];
					block_Data[i][j+1]=0;
				}
	}
	
	public static void merge() {
		for(int i=0; i<4; i++)
			for(int j=0; j<3; j++)
			if(block_Data[i][j]==block_Data[i][j+1]&&block_Data[i][j]!=0){
				player.setScore(player.getScore() + block_Data[i][j]);
				System.out.println(player.getScore());
				block_Data[i][j]=block_Data[i][j]*2;
				block_Data[i][j+1]=0;
			}
	}
	
	public static void up() {
		transpose();
		coverup();
		merge();
		coverup();
		transpose();
	}
	
    public static void down(){
         transpose();
         reverse();
         coverup();
    	 merge();
    	 coverup();
    	 reverse();
		 transpose();
	}
    
    public static void left(){
    	coverup();
    	merge();
    	coverup();
	}
    
    public static void right(){
    	reverse();
    	coverup();
    	merge();
    	coverup();
    	reverse();
	}
	
	// getters
    StackPane getBox(int row, int column) {
        return boxes[row][column];
    }

    // setters
    public void setBox(int row, int column, StackPane box) {
        boxes[row][column] = box;
    }
    
}
