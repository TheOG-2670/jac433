import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

public class Student_GUI extends Application {

    @Override
    public void start(Stage primaryStage)
    {
       try
       {
           FXMLLoader loader = new FXMLLoader();
           Parent root = loader.load(getClass().getResource("workshop_gui.fxml"));
           primaryStage.setTitle("Workshop 5");
           primaryStage.setScene(new Scene(root));
       }
       catch(IOException e)
       {
            //
       }

        primaryStage.setResizable(false);
        primaryStage.show();

        //set what happens when the user presses 'X' after information is saved
        //to file
        primaryStage.setOnCloseRequest( new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent windowEvent)
            {
                Platform.exit(); //close GUI application
                System.exit(0); //close JVM
            }
        });
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
