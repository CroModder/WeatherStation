package controller;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ForgotPasswordController {
	
	private DbController dbController = new DbController();
	public Stage prevStage = new Stage();
	public static Integer userId = null;
	private Locale locale = new Locale(Language.getLanguage());
	private ResourceBundle bundle = ResourceBundle.getBundle("language.lang", locale);
	
	public static void setUserId(Integer userId) {
		ForgotPasswordController.userId = userId;
	}

	public void setPrevStage(Stage prevStage) {
		this.prevStage = prevStage;
	}

	@FXML
    private ImageView imageView;
    @FXML
    private Label lblTitle;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRetype;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private Pane titlePane;
    
    public void initialize() {
    	txtPassword.setPromptText(bundle.getString("txtPasswordChange"));
    	txtRetype.setPromptText(bundle.getString("txtRetype"));
    	btnCancel.setText(bundle.getString("btnCancel"));
    	System.out.println("forgotclass "+userId);
    	titlePane.setStyle(
    			"-fx-background-color: #BCAAA4; " +
                "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
    	lblTitle.setText(bundle.getString("lblTitle"));
    	Image image = new Image("/img/forgotPasswdIcon.png");
    	imageView.setImage(image);
    	imageView.setFitWidth(100);
    	imageView.setPreserveRatio(true);
    	imageView.setSmooth(true);
    	imageView.setCache(true);
    	imageView.setLayoutX(10);
    	imageView.setLayoutY(5);
    	
		progressBar.setProgress(-0.1);
    	txtPassword.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable,
	                            String oldValue, String newValue) {
        		String passwd = observable.getValue().toString();
        		if(passwd.length() == 0){
    	            progressBar.setProgress(-0.1);
        		}
        		if(passwd.matches(".*\\d.*")){
        			if(passwd.length() > 0 && passwd.length() < 4){
        				Double percentage = 25/100.0;
        	            progressBar.setProgress(percentage);
        			}else if(passwd.length() > 4 && passwd.length() < 8){
        				Double percentage = 50/100.0;
        	            progressBar.setProgress(percentage);
        			}
        		}else if(passwd.matches("[a-zA-Z]+")){
        			if(passwd.length() > 0 && passwd.length() < 4){
        				Double percentage = 25/100.0;
        	            progressBar.setProgress(percentage);
        			}else if(passwd.length() > 4 && passwd.length() < 8){
        				Double percentage = 50/100.0;
        	            progressBar.setProgress(percentage);
        			}
        		}else{
        			if(passwd.length() > 0 && passwd.length() < 4){
        				Double percentage = 40/100.0;
        	            progressBar.setProgress(percentage);
        			}else if(passwd.length() > 4 && passwd.length() < 6){
        				Double percentage = 85/100.0;
        	            progressBar.setProgress(percentage);
        			}else if(passwd.length() > 6 && passwd.length() < 8){
        				progressBar.setProgress(1);
        			}
        		}
	        }
	    });
    }

    @FXML
    private void cancel(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void changePassword(ActionEvent event) throws SQLException {
    	if (txtPassword.getText() == null || txtRetype.getText() == null) {
    		errorMessage(bundle.getString("alertTitle"), bundle.getString("nullText"));
 	    }else if(txtPassword.getText().isEmpty() || txtRetype.getText().isEmpty()){
 	    	errorMessage(bundle.getString("alertTitle"), bundle.getString("nullText"));
 		}else if(txtPassword.getText().equals(txtRetype.getText())){
 			if(Integer.valueOf(txtPassword.getText().length()) < 4 && Integer.valueOf(txtRetype.getText().length()) < 4){
 				errorMessage(bundle.getString("alertTitle"), bundle.getString("txtPasswordShort"));
 			}else{
 				if(dbController.changePassword(userId, txtPassword.getText())){
 	 				Alert alert = new Alert(AlertType.INFORMATION); 
 	 			    alert.setTitle(bundle.getString("alertTitleSuccess"));
 	 			    alert.setHeaderText(null);
 	 			    alert.setContentText(bundle.getString("passwordSuccess"));
 	 			    alert.show();
 	 			    txtPassword.clear();
 	 			    txtPassword.requestFocus();
 	 			    txtRetype.clear();
 	 			    progressBar.setProgress(-0.1);
 	 			}
 			}
 		}else if (txtPassword.getText().equals(txtRetype.getText()) == false) {					
 			errorMessage(bundle.getString("alertTitle"), bundle.getString("passwordMatch"));	
 		}else {
 		}
    }
    
    private void errorMessage(String title,String context){
		Alert alert = new Alert(AlertType.ERROR); 
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(context);
	    alert.showAndWait();
	}
}
