package controller;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;


public class LanguageController implements ControlledScreen {
	
	@SuppressWarnings("unused")
	private ScreensController myController;
	
	private static HomeController homeController = new HomeController();
	private static LogInController logInController = new LogInController();
	private static HomeAdminController homeAdminController = new HomeAdminController();

	public static HomeAdminController getHomeAdminController() {
		return homeAdminController;
	}
	public static void setHomeAdminController(
			HomeAdminController homeAdminController) {
		LanguageController.homeAdminController = homeAdminController;
	}
	public static LogInController getLogInController() {
		return logInController;
	}
	public static void setLogInController(LogInController logInController) {
		LanguageController.logInController = logInController;
	}
	
	public static HomeController getHomeController() {
		return homeController;
	}	
	public static void setHomeController(HomeController homeController) {
		LanguageController.homeController = homeController;
	}
	
	private Locale locale = null;
	private ResourceBundle bundle = null;

	@FXML
    private Text txtSelectLanguage;
	@FXML
    private MenuButton menuBtnChooseLanguage;
    @FXML
    private Button btnOkLanguage;
    @FXML
    private Button btnCancel;
    @FXML
    private MenuItem menuItemDeutsch;
    @FXML
    private MenuItem menuItemEnglish;
    @FXML
    private MenuItem menuItemCroatian;
	
	public void initialize(){
		//=====================Language - setText=====================
    	locale = new Locale(Language.getLanguage());
    	bundle = ResourceBundle.getBundle("language.lang", locale);
    	if(Language.getLanguage().equals("de_de")){
    		menuBtnChooseLanguage.setText(menuItemDeutsch.getText());
    	}
    	else if(Language.getLanguage().equals("en_en")){
    		menuBtnChooseLanguage.setText(menuItemEnglish.getText());
    	}
    	else{
    		menuBtnChooseLanguage.setText(menuItemCroatian.getText());
    	}
    	
    	txtSelectLanguage.setText(bundle.getString("txtSelectLanguage"));
    	btnCancel.setText(bundle.getString("btnCancel"));
    	//=====================Language - setText=====================
    }
    
	//Language choice/Language of LanguageController
    @FXML
	private void chooseLanguage(ActionEvent event){   	
    	if(event.getSource().equals(menuItemDeutsch)){
			menuBtnChooseLanguage.setText(menuItemDeutsch.getText());
			Language.setLanguage("de_de");
			System.out.print(Language.getLanguage());
			locale = new Locale(Language.getLanguage());
	    	bundle = ResourceBundle.getBundle("language.lang", locale);
	    	txtSelectLanguage.setText(bundle.getString("txtSelectLanguage"));
	    	btnCancel.setText(bundle.getString("btnCancel"));
    	}else if(event.getSource().equals(menuItemEnglish)){
			menuBtnChooseLanguage.setText(menuItemEnglish.getText());
			Language.setLanguage("en_en");
			System.out.print(Language.getLanguage());
			locale = new Locale(Language.getLanguage());
	    	bundle = ResourceBundle.getBundle("language.lang", locale);
	    	txtSelectLanguage.setText(bundle.getString("txtSelectLanguage"));
	    	btnCancel.setText(bundle.getString("btnCancel"));
		}else{
			menuBtnChooseLanguage.setText(menuItemCroatian.getText());
			Language.setLanguage("hr_hr");
			System.out.print(Language.getLanguage());
			locale = new Locale(Language.getLanguage());	    
			bundle = ResourceBundle.getBundle("language.lang", locale);
			txtSelectLanguage.setText(bundle.getString("txtSelectLanguage"));
	    	btnCancel.setText(bundle.getString("btnCancel"));
		}
		
	}
	
    //Setting the language for the whole program/on OK
	public void selectLanguage(ActionEvent event){   	
		if(menuBtnChooseLanguage.getText().equals(menuItemEnglish.getText())){
			Language.setLanguage("en_en");
			homeController.language();
			homeAdminController.language();
			logInController.language();
			logInController.saveLanguage(LogInController.getLogUserId(), Language.getLanguage());
    		Locale.setDefault(Locale.ENGLISH);
		}
		else if(menuBtnChooseLanguage.getText().equals(menuItemDeutsch.getText())){
			Language.setLanguage("de_de");
			homeController.language();
			homeAdminController.language();
			logInController.language();
			logInController.saveLanguage(LogInController.getLogUserId(), Language.getLanguage());
			Locale.setDefault(Locale.GERMAN);
		}
		else{
			Language.setLanguage("hr_hr");
			homeController.language();
			homeAdminController.language();
			logInController.language();
			logInController.saveLanguage(LogInController.getLogUserId(), Language.getLanguage());
    		Locale.setDefault(new Locale("hr", "HR"));
		}
		((Node)(event.getSource())).getScene().getWindow().hide();
		System.out.print(Language.getLanguage());
	}
	//Setting the language for the whole program/on OK
    
    @FXML
	private void cancelLanguage(ActionEvent event) throws Exception{
		((Node)(event.getSource())).getScene().getWindow().hide();
	}    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
}