import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainClass2048 extends Application{

	@Override
	public void start(Stage stage) throws Exception 
	{
	    Parent root = 
	       FXMLLoader.load(getClass().getResource("Layout2048.fxml"));

	    Scene scene = new Scene(root); // attach scene graph to scene 
	    stage.setTitle("2048"); // displayed in window's title bar
	    stage.getIcons().add(new Image("img/logo.png")); //set icon
	    stage.setScene(scene); // attach scene to stage
	    Grid.setKeyListeners(scene);
	    stage.show(); // display the stage
	    stage.setResizable(false);
	}

	public static void main(String[] args) 
	{
	   launch(args); 
	}
	
}
