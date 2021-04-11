
import java.awt.event.KeyEvent;

import com.game.player.Player;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class Controller2048 {
	
	@FXML
    private Pane GamePane;
	
	@FXML
    private GridPane grid;
	static final int GRID_SIZE = 4;
	static final int BOX_SIZE = 117;
	
	private Player player;
	
	@FXML
	private Label lbPlayer;

	public void initializeGrid(){
		//*remove the grid I already have
    	while(grid.getRowConstraints().size() > 0){
    		grid.getRowConstraints().remove(0);
    	}

    	while(grid.getColumnConstraints().size() > 0){
    		grid.getColumnConstraints().remove(0);
    	}
    	//*/
    	
    	//create new board:
    	grid = new Grid(GRID_SIZE, BOX_SIZE);
    	grid.setVgap(20);
    	grid.setHgap(20);
    	final String cssDefault = 
    			  "-fx-border-color:  #522901;\n"
                + "-fx-border-width: 20px;\n"
                + "-fx-border-style: solid;\n"
                + "-fx-background-radius: 10px;\n"
                + "-fx-border-radius: 10px;\n";
    	grid.setStyle(cssDefault);
    	GamePane.getChildren().addAll(grid);
    	
    	player = ((Grid) grid).getPlayer();
    	lbPlayer = new Label(player.toString());
    	lbPlayer.setVisible(true);
	}
	
	public void initialize() {
		initializeGrid();
	}
	
	public void updateLabel() {
		lbPlayer.setText(player.toString());
	}
}
