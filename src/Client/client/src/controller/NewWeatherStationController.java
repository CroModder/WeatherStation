package controller;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
	
public class NewWeatherStationController {
	
	DbController dbController = new DbController();
	
	private Pattern pattern;
    private Matcher matcher;
	private Locale locale;
	private ResourceBundle bundle;
    private static final String IPADDRESS_PATTERN = 
    		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    @FXML
    private BorderPane mainPane;
 	@FXML
    private TextField txtIPAdress;	
    @FXML
    private TextField txtPort;
    @FXML
    private Button btnConfirm;	
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtWsName;
    @FXML
    private ImageView imageNew;
    @FXML
    private Pane paneNewTitle;
    @FXML
    private Label lblNewTitle;
    @FXML
    private Label lblWsName;
    @FXML
    private Label lblPort;
    @FXML
    private Label lblIpAddress;
    @FXML
    private Slider freqSlider;
    @FXML
    private Label lblFreq;
    @FXML
    private Label lblRealTimeFreq;
    @FXML
    private Pane freqPane;
    @FXML
    private RadioButton radioSeconds;
    @FXML
    private RadioButton radioMiliS;
    
    public void initialize() {
    	locale = new Locale(Language.getLanguage());
    	bundle = ResourceBundle.getBundle("language.lang", locale);
    	
    	lblIpAddress.setText(bundle.getString("IpaddressLbl"));
    	lblWsName.setText(bundle.getString("NewWslbl"));
    	lblPort.setText(bundle.getString("Portlbl"));
    	lblRealTimeFreq.setText(bundle.getString("lblEditFreq"));
    	btnConfirm.setText(bundle.getString("btnAdd"));
    	btnCancel.setText(bundle.getString("btnCancel"));
    	radioSeconds.setText(bundle.getString("radioSecondsRecord"));
    	radioMiliS.setText(bundle.getString("radioMiliSecondsRecord"));
    	
    	if(LogInController.getUserAdmin().equals(1)){
            initSlider(1);
            freqSlider.setShowTickMarks(false);
            freqSlider.setShowTickLabels(false);
            radioSeconds.setSelected(true);
    		freqPane.setDisable(false);
			freqSlider.setMin(1);
			freqSlider.setMax(60);
			freqSlider.setValue(5);
	        lblFreq.setText(Double.toString(freqSlider.getValue()) + " sec");
	        freqSlider.valueProperty().addListener(new ChangeListener<Number>() {
	                public void changed(ObservableValue<? extends Number> ov,
	                    Number old_val, Number new_val) {
	                        lblFreq.setText(String.format("%.0f" + " sec", new_val));
	                }
	            });
		} else{
			freqPane.setDisable(true);
		}
    	
    	Image image = new Image("/img/addNewIcon.png");
    	imageNew.setImage(image);
    	imageNew.setFitWidth(100);
    	imageNew.setPreserveRatio(true);
    	imageNew.setSmooth(true);
    	imageNew.setCache(true);
    	imageNew.setLayoutX(35);
    	imageNew.setLayoutY(8);
    	lblNewTitle.setText(bundle.getString("titleText"));
    	
    	paneNewTitle.setStyle(
                "-fx-background-color: #FFA000; " +
                "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
    	
    }

    @FXML
    private void addNewWs(ActionEvent event) throws SQLException {
    	Object source = event.getSource();
		IPAddressValidator();
    	int port = 0;
    	String result[] = {""};
		if (source instanceof Button) {
			if(txtWsName.getText().isEmpty() || txtIPAdress.getText().isEmpty() || txtPort.getText().isEmpty()){
				errorMessage(bundle.getString("warning"),bundle.getString("nullText"),4);
			}
			else if (validate(txtIPAdress.getText()) == false) {
				errorMessage(bundle.getString("titleAlert"), bundle.getString("ipAdressFormat"), 0);
			    txtIPAdress.requestFocus();
			}
			else {
				try {
					port = Integer.valueOf(txtPort.getText());
					try {
						if(LogInController.getUserAdmin().equals(1)){
							int i = (int) freqSlider.getValue();
							if(radioSeconds.isSelected()){
								result = dbController.newWs(txtWsName.getText(), txtIPAdress.getText(), port, LogInController.getLogUserId(),i*1000);
							}else{
								result = dbController.newWs(txtWsName.getText(), txtIPAdress.getText(), port, LogInController.getLogUserId(),i);
							}
						}else{
							result = dbController.newWs(txtWsName.getText(), txtIPAdress.getText(), port, LogInController.getLogUserId(),1000);
						}
					} catch (Exception e) {
						errorMessage(bundle.getString("titleAlert"), bundle.getString("WsExists"), 0);
					}
				} catch (Exception e) {
					errorMessage(bundle.getString("titleAlert"), bundle.getString("portNumber"), 0);
				}
			}
			if(result[0] == "true"){ 
				errorMessage(bundle.getString("alertTitleSuccess"), bundle.getString("newWSAdd"), 1);
			    txtWsName.setText("");
			    txtIPAdress.setText("");
			    txtPort.setText("");
			    dbController.allWS(LogInController.getLogUserId());
			}
		}
    }
    
    public void IPAddressValidator(){
    	pattern = Pattern.compile(IPADDRESS_PATTERN);
    }
    
    private void initSlider(int seconds){
    	if(seconds == 1){
    		freqSlider.setMin(1);
    		freqSlider.setMax(60);
    		freqSlider.setValue(25);
            freqSlider.setShowTickMarks(false);
            freqSlider.setShowTickLabels(false);
            lblFreq.setText(Double.toString(freqSlider.getValue()) + " sec");
            
            freqSlider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        lblFreq.setText(String.format("%.0f" + " sec", new_val));
                }
            });
    	}else{
    		freqSlider.setMin(100);
    		freqSlider.setMax(1000);
    		freqSlider.setValue(300);
            freqSlider.setShowTickMarks(false);
            freqSlider.setShowTickLabels(false);
            lblFreq.setText(Double.toString(freqSlider.getValue()) + " mili");
            
            freqSlider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        lblFreq.setText(String.format("%.0f" + " mili", new_val));
                }
            });
		}
    }
    
    @FXML
    private void radioFreq(ActionEvent event){
    	if(event.getSource().equals(radioSeconds)){
    		initSlider(1);
    		radioMiliS.setSelected(false);
    		radioSeconds.setSelected(true);
    	}else{
    		initSlider(2);
    		radioMiliS.setSelected(true);
    		radioSeconds.setSelected(false);
    	}
    }
    
    private void errorMessage(String title,String context, Integer id){
    	if(id == 0){
    		Alert alert = new Alert(AlertType.ERROR); 
    	    alert.setTitle(title);
    	    alert.setHeaderText(null);
    	    alert.setContentText(context);
    	    alert.showAndWait();
    	}else if(id == 1){
    		Alert alert = new Alert(AlertType.INFORMATION); 
    	    alert.setTitle(title);
    	    alert.setHeaderText(null);
    	    alert.setContentText(context);
    	    alert.showAndWait();
    	}else if(id == 2){
    		Alert alert = new Alert(AlertType.CONFIRMATION); 
    	    alert.setTitle(title);
    	    alert.setHeaderText(null);
    	    alert.setContentText(context);
    	    alert.showAndWait();
    	}else {
    		Alert alert = new Alert(AlertType.WARNING); 
    	    alert.setTitle(title);
    	    alert.setHeaderText(null);
    	    alert.setContentText(context);
    	    alert.showAndWait();
    	}
	}
   
     public boolean validate(final String ip){		  
 	  matcher = pattern.matcher(ip);
 	  return matcher.matches();	    	    
     }

    @FXML
    void cancel(ActionEvent event){
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
}