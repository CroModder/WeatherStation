package controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailPinController{

	private Session session;
	private DbController dbController = new DbController();
	public static Stage emailPinStage = new Stage();
	public static BorderPane EmailPinPane = new BorderPane();
	private Locale locale = new Locale(Language.getLanguage());
	private ResourceBundle bundle = ResourceBundle.getBundle("language.lang", locale);
	
	public static void setEmailPinPane(BorderPane emailPinPane) {
		EmailPinPane = emailPinPane;
	}

	public static void setEmailPinStage(Stage emailPinStage) {
		EmailPinController.emailPinStage = emailPinStage;
	}

	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPin;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private Pane titlePane;
	@FXML
	private ImageView imageView;
	@FXML
	private Label lblTitleText;
	@FXML
	private Button btnEmailCheck;
	@FXML
	private ImageView imageViewEmailCheck;
	@FXML
	private ImageView imageViewPinCheck;
	
    public void initialize() {
    	btnCancel.setText(bundle.getString("btnCancel"));
    	btnEmailCheck.setText(bundle.getString("btnEmailCheck"));
    	txtPin.setDisable(true);
    	titlePane.setStyle(
    			"-fx-background-color: #BCAAA4; " +
                "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
    	lblTitleText.setText(bundle.getString("lblTitleText"));
    	Image image = new Image("/img/forgotPasswdIcon.png");
    	imageView.setImage(image);
    	imageView.setFitWidth(100);
    	imageView.setPreserveRatio(true);
    	imageView.setSmooth(true);
    	imageView.setCache(true);
    	imageView.setLayoutX(10);
    	imageView.setLayoutY(5);
    	
		final String username = "t13swp5@gmail.com";
		final String password = "ss15_swp5";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	}
	
	@FXML
	private void sendPin(ActionEvent event) throws SQLException{
		String email = null;
		try {
			email = txtEmail.getText();
		} catch (Exception e) {
			errorMessage(bundle.getString("titleAlert"),bundle.getString("sendPinWrongEmail"));
		    txtEmail.requestFocus();
		    checkImage(false,0);
		}
		try {
			if(isValidEmailAddress(email)){
				int gen = randPasswdCode();
				if(dbController.emailCheck(email,gen) == true){
					Alert alert = new Alert(AlertType.INFORMATION); 
				    alert.setTitle("Email");
				    alert.setHeaderText(null);
				    alert.setContentText(bundle.getString("sendPinCorrect"));
				    alert.showAndWait();
				    
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("t13swp5@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email));
					message.setSubject(bundle.getString("emailSubject"));
					message.setText(bundle.getString("emailText") + gen + ".\n");

					Transport.send(message);
					session.getTransport().close();
					txtPin.setDisable(false);
					txtPin.requestFocus();
				    checkImage(true,0);
				}else{
					checkImage(false,0);
				}
			}else{
				errorMessage(bundle.getString("titleAlert"),bundle.getString("emailAdressWrong"));
			    checkImage(false,0);
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} 
	}
	
	private void checkImage(Boolean value,Integer id){
		if(value == true && id == 0){
		    Image image = new Image("/img/checkIcon.png");
		    imageViewEmailCheck.setImage(image);
	    	imageViewEmailCheck.setPreserveRatio(true);
	    	imageViewEmailCheck.setSmooth(true);
	    	imageViewEmailCheck.setCache(true);
		}else if (value == false && id == 0){
		    Image image = new Image("/img/notCheckIcon.png");
		    imageViewEmailCheck.setImage(image);
	    	imageViewEmailCheck.setPreserveRatio(true);
	    	imageViewEmailCheck.setSmooth(true);
	    	imageViewEmailCheck.setCache(true);
		}else if(value == true && id == 1){
			Image image = new Image("/img/checkIcon.png");
		    imageViewPinCheck.setImage(image);
		    imageViewPinCheck.setPreserveRatio(true);
	    	imageViewPinCheck.setSmooth(true);
	    	imageViewPinCheck.setCache(true);
		}else if (value == false && id == 1){
			Image image = new Image("/img/notCheckIcon.png");
		    imageViewPinCheck.setImage(image);
		    imageViewPinCheck.setPreserveRatio(true);
	    	imageViewPinCheck.setSmooth(true);
	    	imageViewPinCheck.setCache(true);
		}
	}
	
	private boolean isValidEmailAddress(String email) {
	   boolean result = true;
	   try {
	      InternetAddress emailAddr = new InternetAddress(email);
	      emailAddr.validate();
	   } catch (AddressException ex) {
	      result = false;
	   }
	   return result;
	}
	
	private int randPasswdCode(){
	    int START = 1000;
	    int END = 9999;
	    Random random = new Random();
	    if (START > END) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    long range = (long)END - (long)START + 1;
	    long fraction = (long)(range * random.nextDouble());
	    int randomNumber =  (int)(fraction + START);  
	    
	    return randomNumber;
	}
	
	@FXML
	private void forgotPasswodScreen(ActionEvent event) throws IOException{
		String email = null;
		Integer pin = null;
		if(txtEmail.getText().length() > 0){
			email = txtEmail.getText();
			try {
				pin = Integer.valueOf(txtPin.getText());
				if(isValidEmailAddress(email)){
					if(dbController.passwdCheck(email,pin) == true){
						Alert alert = new Alert(AlertType.INFORMATION); 
					    alert.setTitle("E-mail");
					    alert.setHeaderText(null);
					    alert.setContentText(bundle.getString("pinCodeCorrect"));
					    alert.show();
					    checkImage(true,1);
					    openForgoPasswdView();
					}else{
						errorMessage(bundle.getString("titleAlert"),bundle.getString("pinCodeInCorrect"));
					    checkImage(false,1);
					    txtPin.requestFocus();
					}
				}else{
				    checkImage(false,0);
				}
			}catch (Exception e) {
				errorMessage(bundle.getString("titleAlert"),bundle.getString("noPinCode"));
			    txtPin.requestFocus();
			    checkImage(false,1);
			    txtPin.requestFocus();
			}
		}else{
			errorMessage(bundle.getString("titleAlert"),bundle.getString("noEmailAdress"));
		    txtEmail.requestFocus();
		    checkImage(false,0);
		}
		
	}
	
	private void openForgoPasswdView(){
		FadeTransition ft1 = new FadeTransition(Duration.millis(450), EmailPinPane);
		ft1.setFromValue(1.0);
		ft1.setToValue(0.0);
		ft1.play();
		System.out.println("btnOk");
		ft1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        		emailPinStage.setTitle(bundle.getString("stageTitlePassword"));
        		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/ForgotPasswordView.fxml"));
        		BorderPane myPane = null;
				try {
					myPane = (BorderPane)myLoader.load();
					myPane.setOpacity(0.0);
				} catch (IOException e) {
					e.printStackTrace();
				}
        		FadeTransition ft = new FadeTransition(Duration.millis(450), myPane);
        		ft.setFromValue(0.0);
        		ft.setToValue(1.0);
        		ft.play();
        		ForgotPasswordController controller = (ForgotPasswordController) myLoader.getController();
        		controller.setPrevStage(emailPinStage);
        		Scene myScene = new Scene(myPane);        
        		emailPinStage.setScene(myScene);
        		emailPinStage.show();
            }
        });
	}
	
	private void errorMessage(String title,String context){
		Alert alert = new Alert(AlertType.ERROR); 
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(context);
	    alert.showAndWait();
	}
	
	@FXML
	private void cancel(ActionEvent event){
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}
