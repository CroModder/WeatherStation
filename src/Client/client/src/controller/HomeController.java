package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import model.WeatherStationModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HomeController implements Initializable, ControlledScreen  {

	private ScreensController myController;
	private Locale locale;
	private ResourceBundle bundle;
	private Tooltip toolTip;
    @FXML
    private Label btnConnect;
    @FXML
    private Label btnHistorical;
    @FXML
    private ImageView btnLanguage;
    @FXML
    private MenuItem MenuItemLogOut;
    @FXML
    private MenuBar MenuBarHome;
    @FXML
    private AnchorPane AnchoPaneHome;
    @FXML
    private MenuItem menuOptions;
    @FXML
    private ImageView help;
    @FXML
    private GridPane iconPaneConnect;
    @FXML
    private GridPane iconPaneHistorical;
    @FXML
    private ImageView homeViewBackground;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	MainPanelController.setHomeController(this);
    	LanguageController.setHomeController(this);
    	System.out.println("\nHomenije Kurac" + Language.getLanguage());
    	LogInController.setHomeController(this);
    	language();
    	/*iconPaneConnect.setStyle("-fx-effect: dropshadow(three-pass-box, #8d8d8d, 3, 0, 0, 0); " + "-fx-border-radius:0 0 15 15");
    	iconPaneHistorical.setStyle("-fx-effect: dropshadow(three-pass-box, #8d8d8d, 3, 0, 0, 0); " + "-fx-border-radius:0 0 15 15");*/
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    public void language(){
		System.out.println("\nHomenije Kurac" + Language.getLanguage());
		locale = new Locale(Language.getLanguage());
    	bundle = ResourceBundle.getBundle("language.lang", locale);
    	btnConnect.setText(bundle.getString("btnConnect"));
    	btnHistorical.setText(bundle.getString("btnHistorical"));
    	//btnLanguage.setText(bundle.getString("btnLanguage"));
    	toolTip = new Tooltip(bundle.getString("btnLanguage"));
        Tooltip.install(btnLanguage, toolTip);
        toolTip = new Tooltip(bundle.getString("help"));
        Tooltip.install(help, toolTip);
    	menuOptions.setText(bundle.getString("options"));
    	MenuItemLogOut.setText(bundle.getString("logOut"));
    }

	@FXML
    private void startConnect(MouseEvent event) throws IOException {
    	AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MainPanelView.fxml"));
	    Scene scene = new Scene(page);
	    Stage stage = new Stage();
	    stage.getIcons().add(new Image("img/WeatherStationLogo.png"));
	    stage.setTitle("Main panel");
	    stage.setScene(scene);
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
	    stage.show();
	    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  for(int i=0;i<DbController.weatherStationData.size();i++){
	      			if(DbController.weatherStationData.get(i).selectedProperty().get() == true){
	      				DbController.weatherStationData.get(i).selectedProperty().set(false);
	      			}
	        	  }
	          }
	      });        
    }

    @FXML
    private void startHistorical(MouseEvent event) throws IOException {
    	AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/HistoricalView.fxml"));
	    Scene scene = new Scene(page);
	    Stage stage = new Stage();
		Locale locale = new Locale(Language.getLanguage());
    	ResourceBundle bundle = ResourceBundle.getBundle("language.lang", locale);
    	stage.getIcons().add(new Image("img/WeatherStationLogo.png"));
    	stage.setTitle(bundle.getString("btnHistorical"));
	    stage.setScene(scene);
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
	    stage.show();
    }
    
    @FXML
    private void mouseEntered(MouseEvent event){
    	if(event.getEventType().toString().equals("MOUSE_ENTERED")){
    		if(event.getSource().equals(iconPaneConnect)){
    			iconPaneConnect.setStyle("-fx-background-color: rgba(255, 255, 255, .7);"  + "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
    		}else{
    			iconPaneHistorical.setStyle("-fx-background-color: rgba(255, 255, 255, .7);"  + "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
    		}
    	}else{
    		iconPaneConnect.setStyle("-fx-background-color: transparent;"  +
    				"-fx-underline: false;");
    		iconPaneHistorical.setStyle("-fx-background-color: transparent;"  +
    				"-fx-underline: false;");
    	}
    }

    @FXML
    private void startLanguage(MouseEvent event) throws IOException {
    	AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/LanguageView.fxml"));
	    Scene scene = new Scene(page);
	    Stage stage = new Stage();
		Locale locale = new Locale(Language.getLanguage());
    	ResourceBundle bundle = ResourceBundle.getBundle("language.lang", locale);
    	stage.getIcons().add(new Image("img/WeatherStationLogo.png"));
	    stage.setTitle(bundle.getString("btnLanguage"));
	    stage.setScene(scene);
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.show();
    }
    
    @FXML
    private void startHelp(MouseEvent event) throws IOException {
    	if (Desktop.isDesktopSupported()) {
            File file = new File("UsersManual.pdf");
            if (!file.exists()) {
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("help/UsersManual.pdf");
                OutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.close();
                inputStream.close();
            }
            Desktop.getDesktop().open(file);
        }    
	}
    
    @FXML
    public void logOut() throws IOException{
		WeatherStationModel.deviceSocketList.clear();
    	myController.setScreen(ScreensFramework.screen1ID);
    }
}
