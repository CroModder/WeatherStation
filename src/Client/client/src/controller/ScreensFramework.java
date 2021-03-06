package controller;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScreensFramework extends Application {
	
	private DbController dbController = new DbController();
    
    public static String screen1ID = "LogIn";
    public static String screen1File = "/view/LogInView.fxml";
    public static String screen2ID = "HomeController";
    public static String screen2File = "/view/HomeView.fxml";
    public static String screen3ID = "HomeAdminController";
    public static String screen3File = "/view/HomeAdminView.fxml";
   
    private Locale locale = null;
    @SuppressWarnings("unused")
	private ResourceBundle bundle = null;
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        if(dbController.dbInit()){
	        ScreensController mainContainer = new ScreensController();
	        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
	        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
	        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);	      
	        
	        mainContainer.setScreen(ScreensFramework.screen1ID);
	        
	        Group root = new Group();
	        root.getChildren().addAll(mainContainer);

	        final Dimension d = new Dimension(525, 365);
	        Scene scene = new Scene(root, d.width, d.height);
	        scene.getStylesheets().add("/view/style.css");
	        primaryStage.setTitle("WeatherStation");
	        primaryStage.getIcons().add(new Image("img/WeatherStationLogo.png"));
	        primaryStage.setScene(scene);	        
			primaryStage.setResizable(false);
			primaryStage.setWidth(d.width);
		    primaryStage.setHeight(d.height);
	        primaryStage.show();
		}
        else {
        	Alert alert = new Alert(AlertType.WARNING);  
        	System.out.print(Locale.getDefault());
        	locale = Locale.getDefault();
        	System.out.print(locale);
        	if(locale.equals(Locale.ENGLISH) || locale.equals(Locale.US)){
        		alert.setTitle("Warning!");
    		    alert.setHeaderText("No connection to the database!");
    		    alert.setContentText("The application is not usable. \nPlease check your Internet connection  or \nconsult with the administrator.");
        	}
        	else if(locale.equals("hr_HR")){
        		alert.setTitle("Upozorenje!");
    		    alert.setHeaderText("Nema veze s bazom podataka!");
    		    alert.setContentText("Aplikaciju nije mogu\u0107e pokrenuti. \nProvjerite internetsku vezu ili \nse posavjetujte s administratorom.");
        	}
        	else{
        		alert.setTitle("Achtung!");
    		    alert.setHeaderText("Keine Verbindung zur Datenbank!");
    		    alert.setContentText("Die Anwendung ist nicht verwendbar. \nBitte überprüfen Sie Ihre Internetverbindung oder \nwenden Sie sich an den Administrator.");
        	}
		    
		    alert.showAndWait();
		}
    }

    public static void main(String[] args) {
        launch(args);
    }
}