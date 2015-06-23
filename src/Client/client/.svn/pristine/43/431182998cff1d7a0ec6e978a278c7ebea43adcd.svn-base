package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import model.UsersModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class UserSettingsController {
	
	private DbController dbController = new DbController();
	private Integer selectedUser = 0;
	private ResourceBundle bundle;
	private Locale locale;

    @FXML
    private TableView<UsersModel> tableUsers;
    @FXML
    private TableColumn<UsersModel, Integer> columnId;
    @FXML
    private TableColumn<UsersModel, String> columnName;
    @FXML
    private TableColumn<UsersModel, Date> columnDate;
    @FXML
    private TableColumn<UsersModel, Integer> columnRole;
    @FXML
    private TableColumn<UsersModel, Boolean> columnDelete;
    @FXML
    private Pane titlePane;
    @FXML
    private ImageView imageUsersPane;
    @FXML
    private Label lblUserPane;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnOk;
    @FXML
    private Label lblRole;
    @FXML
    private Label lblName;    
    @FXML
    private ComboBox<String> comboUserAdmin;
    @FXML
    private TextField txtNewName;

    public void initialize() throws SQLException {
    	locale = new Locale(Language.getLanguage());
    	bundle = ResourceBundle.getBundle("language.lang", locale);
    	
    	lblName.setText(bundle.getString("columnName"));
    	lblRole.setText(bundle.getString("columnRole"));
    	comboUserAdmin.setPromptText(bundle.getString("userAdmin"));
    	btnCancel.setText(bundle.getString("btnCancel"));
    	btnSave.setText(bundle.getString("save"));
    	
    	Image image = new Image("/img/editUserIcon.png");
    	imageUsersPane.setImage(image);
    	imageUsersPane.setFitWidth(100);
    	imageUsersPane.setPreserveRatio(true);
    	imageUsersPane.setSmooth(true);
    	imageUsersPane.setCache(true);
    	imageUsersPane.setLayoutX(35);
    	imageUsersPane.setLayoutY(10);
    	lblUserPane.setText(bundle.getString("lblUserPane"));
    	titlePane.setStyle(
    			"-fx-background-color: #69F0AE; " +
                "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
    
    	if(comboUserAdmin.getItems().isEmpty() == true){
        	comboUserAdmin.getItems().addAll(bundle.getString("menuBtnAdmin"), bundle.getString("menuBtnUser")); 
    	}
    	
		if(selectedUser.equals(0)){
			txtNewName.setDisable(true);
		}
    	columnName.setText(bundle.getString("columnName"));
    	columnRole.setText(bundle.getString("columnRole"));
    	columnDate.setText(bundle.getString("columnDate"));
    	columnDelete.setText(bundle.getString("columnDelete"));
		columnName.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("name"));
        columnId.setCellValueFactory(new PropertyValueFactory<UsersModel, Integer>("id"));
        columnRole.setCellValueFactory(new PropertyValueFactory<UsersModel, Integer>("role"));
        columnDate.setCellValueFactory(new PropertyValueFactory<UsersModel, Date>("date"));
        
        columnDelete.setSortable(false);
        columnDelete.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<UsersModel, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<UsersModel, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        columnDelete.setCellFactory(
                new Callback<TableColumn<UsersModel, Boolean>, TableCell<UsersModel, Boolean>>() {
 
            @Override
            public TableCell<UsersModel, Boolean> call(TableColumn<UsersModel, Boolean> p) {
                return new ButtonCell(tableUsers);
            }
         
        });
        tableUsers.setEditable(true);
        tableUsers.setItems(dbController.allUsers());
        columnDate.setStyle("-fx-alignment: CENTER;");
        columnId.setStyle("-fx-alignment: CENTER;");
        columnName.setStyle("-fx-alignment: CENTER;");
        columnRole.setStyle("-fx-alignment: CENTER;");
        columnDelete.setStyle("-fx-alignment: CENTER;");
    }
    
    private class ButtonCell extends TableCell<UsersModel, Boolean> {
        final Button cellButton = new Button();
         
        ButtonCell(@SuppressWarnings("rawtypes") final TableView tableWs){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex(); 
                    UsersModel usersModel = (UsersModel)tableWs.getItems().get(selectdIndex);
                    try {
                    	if(usersModel.getId().equals(LogInController.getLogUserId()) == false){
                    		Alert alert = new Alert(AlertType.CONFIRMATION);
                       	 alert.setTitle(bundle.getString("columnDelete"));
                       	 alert.setHeaderText(bundle.getString("delAction"));
                       	 alert.setContentText(bundle.getString("deleteQ"));

                       	 ButtonType buttonTypeOne = new ButtonType(bundle.getString("yes"));
                       	 ButtonType buttonTypeTwo = new ButtonType(bundle.getString("no"));
                       	 ButtonType buttonTypeCancel = new ButtonType(bundle.getString("btnCancel"), ButtonData.CANCEL_CLOSE);

                       	 alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                       	 Optional<ButtonType> result = alert.showAndWait();
                       	 if (result.get() == buttonTypeOne){
                       		 System.out.println(usersModel.getId());
                       		if(dbController.deleteUsers(usersModel.getId())){
                           		initialize();
                           	 }
                       	 } else if (result.get() == buttonTypeTwo) {
                       	     alert.close();
                       	 } else {
                       		 alert.close();
                       	 }
                    	} 
					} catch (SQLException e) {
						e.printStackTrace();
					}
                }
            });
        }
        
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
            	System.out.println("\nUserSettings" + tableUsers.getItems().get(super.getIndex()).getId() + " " + LogInController.getLogUserId());
            	if(tableUsers.getItems().get(super.getIndex()).getId().equals(LogInController.getLogUserId()) ==  true){
                	Image image = new Image(getClass().getResourceAsStream("/img/disableDeleteIcon.png"));
                	cellButton.setGraphic(new ImageView(image));
                	cellButton.setStyle("-fx-background-color: transparent;");
                    setGraphic(cellButton);
        		} else if(tableUsers.getItems().get(super.getIndex()).getId().equals(LogInController.getLogUserId()) == false){
        			Image image = new Image(getClass().getResourceAsStream("/img/deleteIcon.png"));
                	cellButton.setGraphic(new ImageView(image));
                	cellButton.setStyle("-fx-background-color: transparent;");
                    setGraphic(cellButton);
        		}
            }
        }
    }
    
    @FXML
    private void getSelectedUser(){
    	try {
    		if(tableUsers.getSelectionModel().getSelectedItem() != null){
    			UsersModel usersModel = tableUsers.getSelectionModel().getSelectedItem();
    			txtNewName.setDisable(false);
    			selectedUser = usersModel.getId();
    			txtNewName.setText(usersModel.getName());
    			if(usersModel.getRole().equals(1)){
    				comboUserAdmin.setValue(bundle.getString("menuBtnAdmin"));
    			}else if(usersModel.getRole().equals(2)){
    				comboUserAdmin.setValue(bundle.getString("menuBtnUser"));
    			}
    		}else{
    	    	txtNewName.setDisable(true);
        		txtNewName.setText("");
    		}
    	}catch (Exception e) {
    		selectedUser = 0;
    	}
    }
    
    @FXML
    private void saveChanges(ActionEvent event) throws SQLException{
    	Object source = event.getSource();
    	String result[] = {""};
    	if (source instanceof Button) {
			if(txtNewName.getText().isEmpty() && selectedUser.equals(0) == false){
				Alert alert = new Alert(AlertType.WARNING); 
			    alert.setTitle(bundle.getString("warning"));
			    alert.setHeaderText(bundle.getString("input"));
			    alert.setContentText(bundle.getString("nullText"));
			    alert.showAndWait();  
			}else if(txtNewName.getText().isEmpty() && selectedUser.equals(0)){
				Alert alert = new Alert(AlertType.WARNING); 
			    alert.setTitle(bundle.getString("warning"));
			    alert.setHeaderText(bundle.getString("selection"));
			    alert.setContentText(bundle.getString("selectUserFromTable"));
			    alert.showAndWait();  
			}else if (txtNewName.lengthProperty().getValue() > 20) {
				Alert alert = new Alert(AlertType.ERROR); 
			    alert.setTitle(bundle.getString("alertTitle"));
			    alert.setHeaderText(null);
			    alert.setContentText(bundle.getString("maxChar") + " " + txtNewName.lengthProperty().getValue());
			    alert.showAndWait();
			    txtNewName.setText("");
			    txtNewName.requestFocus();
			}else {
				try {
					System.out.println(comboUserAdmin.getSelectionModel().getSelectedItem());
					if(comboUserAdmin.getSelectionModel().getSelectedItem().equals(bundle.getString("menuBtnAdmin"))){
						result = dbController.editUser(txtNewName.getText(), 1, selectedUser);
					}else if(comboUserAdmin.getSelectionModel().getSelectedItem().equals(bundle.getString("menuBtnUser"))){
						result = dbController.editUser(txtNewName.getText(), 2, selectedUser);
					}
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR); 
				    alert.setTitle(bundle.getString("alertTitle"));
				    alert.setHeaderText(null);
				    alert.setContentText(bundle.getString("errorSaving"));
				    alert.showAndWait();
				}
			}
			if(result[0] == "true"){ 
				Alert alert = new Alert(AlertType.INFORMATION); 
			    alert.setTitle(bundle.getString("alertTitleSuccess"));
			    alert.setHeaderText(bundle.getString("editUser"));
			    alert.setContentText(bundle.getString("editUserSucces"));
			    alert.showAndWait();
			    selectedUser = 0;
			    comboUserAdmin.getSelectionModel().clearSelection();
			    txtNewName.setText("");
			    initialize();
			}
		}
    }

    @FXML
    void cancel(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void ok(ActionEvent event) throws SQLException {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
