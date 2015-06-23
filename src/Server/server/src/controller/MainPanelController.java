package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import model.TableViewSelection;
import model.WeatherStationModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.TableCell;

public class MainPanelController implements Initializable{
	
	//private DbController dbController = new DbController();

	private Window window = null;
	private Circle c1 = new Circle(60);
	private Circle c2 = new Circle(60);
	private Circle c3 = new Circle(60);
	private Circle c4 = new Circle(60);
	private Circle c5 = new Circle(60);
	private Line line1 = new Line();
	private Line line2 = new Line();
	private Line line3 = new Line();
	private Line line4 = new Line();
	private Line line5 = new Line();

	private static List<TableViewSelection> listSelection = new LinkedList<TableViewSelection>();
	
	public static void setListSelection(TableViewSelection test) {
		Boolean duplicates = false;
		for(int i=0;i<MainPanelController.listSelection.size();i++){
			if(MainPanelController.listSelection.get(i).getIp() == test.getIp()){
				if(test.getSelected() == false){
					MainPanelController.listSelection.remove(i);
					duplicates = true;
				}
			}
		}
		if(duplicates == false || MainPanelController.listSelection.size() == 0){
			MainPanelController.listSelection.add(test);
		}
	}

	@FXML 
	private LineChart<Double, Double> graphTemp;
	@FXML
	private SplitPane splitPane;
	@FXML
	private Menu menuOptions;
	@FXML
	private MenuBar menuBarOptions;
	@FXML
	private MenuItem menuItemExit;
	@FXML
	private MenuItem menuItemLogOut;
	@FXML
	private Button btnRecord;
	@FXML
	private Button btnAddNewWs;
	@FXML
	private Button btnEditWs;
	@FXML
	private TableView<WeatherStationModel> tableWs;
	@FXML
	private TableColumn<WeatherStationModel, Boolean> tableColCheck;
	@FXML
	private TableColumn<WeatherStationModel, String> tableColWs;
	@FXML
	private MenuItem menuItemCelsius;
	@FXML
	private MenuItem menuItemFarenheit;
	@FXML
	private TabPane tabMainPanel;
	@FXML
	private Menu menuRecord;
	@FXML
	private StackPane movementPane1;
	@FXML
	private AnchorPane movementPaneBottom1;
	@FXML
	private StackPane movementPane2;
	@FXML
	private StackPane movementPane3;
	@FXML
	private StackPane movementPane4;
	@FXML
	private StackPane movementPane5;
	@FXML
	private RadioButton rbCelsius;
	@FXML
	private RadioButton rbFahrenheit;

	LineChart.Series<Double, Double> series1 = new LineChart.Series<Double, Double>();
    LineChart.Series<Double, Double> series2 = new LineChart.Series<Double, Double>();   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();
		        
	        series1.setName("Ws#1");
	        
	        series1.setName("Ws#1");
	        series1.getData().add(new XYChart.Data<Double, Double>(0.0, 1.6));
	        series1.getData().add(new XYChart.Data<Double, Double>(0.8, 0.4));
	        series1.getData().add(new XYChart.Data<Double, Double>(1.4, 2.9));
	        series1.getData().add(new XYChart.Data<Double, Double>(2.1, 1.3));
	        series1.getData().add(new XYChart.Data<Double, Double>(2.6, 0.9));
	        
	        lineChartData.add(series1);
	        
			series2.setName("Ws#2");
				        
			series2.setName("Ws#2");
			series2.getData().add(new XYChart.Data<Double, Double>(0.5, 1.0));
			series2.getData().add(new XYChart.Data<Double, Double>(0.5, 0.1));
			series2.getData().add(new XYChart.Data<Double, Double>(2.4, 2.0));
			series2.getData().add(new XYChart.Data<Double, Double>(3.1, 5.0));
			series2.getData().add(new XYChart.Data<Double, Double>(1.6, 2.0));
				        
			lineChartData.add(series2);
	        graphTemp.setData(lineChartData);
	     // graphTemp.createSymbolsProperty();
	        
	        //initTable();

	        tabMainPanel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {	
					movementCalc(newValue);
				}
	        	
	        }); 
    }
    
   /* public void initTable(){
    	ObservableList<WeatherStationModel> data = null;
		try {
			data = dbController.allWS(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		tableColCheck.setMinWidth(50);
		tableColCheck.setCellValueFactory(new PropertyValueFactory<WeatherStationModel, Boolean>("selected"));
		tableColCheck.setCellFactory(new Callback<TableColumn<WeatherStationModel, Boolean>, TableCell<WeatherStationModel, Boolean>>() {
		    public TableCell<WeatherStationModel, Boolean> call(TableColumn<WeatherStationModel, Boolean> p) {
		        return new CheckBoxTableCell<WeatherStationModel, Boolean>();
		    }
		});

		tableColWs.setCellValueFactory(new PropertyValueFactory<WeatherStationModel, String>("name"));
		tableWs.setItems(data);
		tableWs.setEditable(true);
    }*/
    
    private void movementCalc(Number newValue){
        if(newValue.intValue() == 3){
        	if( movementPane1.getChildren().size() == 0){
        	 	line1.setStartX(c1.getCenterX());
        		line1.setStartY(c1.getRadius()*2);
        		c1.setStroke(Color.BLACK);
        		c1.setFill(null);
        		c1.setStrokeWidth(3);
        		line1.setStrokeWidth(1.5);
         		movementPane1.getChildren().addAll(line1,c1);
        	 	line2.setStartX(c2.getCenterX());
        		line2.setStartY(c2.getRadius()*2);
        		c2.setStroke(Color.BLACK);
        		c2.setFill(null);
        		c2.setStrokeWidth(3);
        		line2.setStrokeWidth(1.5);
         		movementPane2.getChildren().addAll(line2,c2);
        	 	line3.setStartX(c3.getCenterX());
        		line3.setStartY(c3.getRadius()*2);
        		c3.setStroke(Color.BLACK);
        		c3.setFill(null);
        		c3.setStrokeWidth(3);
        		line3.setStrokeWidth(1.5);
         		movementPane3.getChildren().addAll(line3,c3);
        	 	line4.setStartX(c4.getCenterX());
        		line4.setStartY(c4.getRadius()*2);
        		c4.setStroke(Color.BLACK);
        		c4.setFill(null);
        		c4.setStrokeWidth(3);
        		line4.setStrokeWidth(1.5);
         		movementPane4.getChildren().addAll(line4,c4);
         		line5.setStartX(c5.getCenterX());
        		line5.setStartY(c5.getRadius()*2);
        		c5.setStroke(Color.BLACK);
        		c5.setFill(null);
        		c5.setStrokeWidth(3);
        		line5.setStrokeWidth(1.5);
         		movementPane5.getChildren().addAll(line5,c5);
        	}
    	}
       /* if(newValue.intValue() == 3){
        	if( movementPane1.getChildren().size() == 0){
        		movementPaneBottom1.getChildren().addAll(textField);
        	 	line.setStartX(c1.getCenterX());
        		line.setStartY(c1.getRadius()*2);
        		c1.setStroke(Color.BLACK);
        		c1.setFill(null);
        		c1.setStrokeWidth(3);
        		line.setStrokeWidth(1.5);
         		movementPane1.getChildren().addAll(line,c1);
            	textField.textProperty().addListener(new ChangeListener<String>() {
            		@Override
            		public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
 	                		if(oldValue.isEmpty() == false){
 	                			if(Double.valueOf(oldValue) > Double.valueOf(newValue)){
    	                	    	Double izr = 0.0;
    	                	    	izr = Double.valueOf(oldValue) - Double.valueOf(newValue);
    	                	    	System.out.println("\nNegativno" + Double.valueOf(oldValue) + " " + Double.valueOf(newValue));
    	                	    	rt.setByAngle(izr * -1 );
    	                	    	rt.play();
 	                			} else if(Double.valueOf(oldValue) <= Double.valueOf(newValue)){
    	                	    	Double izr = 0.0;
    	                	    	izr = Double.valueOf(newValue) - Double.valueOf(oldValue);
    	                	    	System.out.println("\nPozitivno " + izr + " " + rt.getByAngle() + " " + Double.valueOf(newValue));
    	                	    	rt.setByAngle(izr);
    	                	    	rt.play();
    	                	    }
 	                		}
                     	}
                 });
        	}
        }*/
    }
    
    @FXML
    private void getNode(MouseEvent event){
    	window = ((Node)(event.getSource())).getScene().getWindow();
    }
    
    @FXML
    private void addNewWs(ActionEvent event) throws IOException, SQLException{
    	BorderPane page = (BorderPane) FXMLLoader.load(getClass().getResource("/view/NewWeatherStationView.fxml"));
	    Scene scene = new Scene(page);
	    Stage stage = new Stage();
	    stage.getIcons().add(new Image("img/WeatherStationLogo.png"));
	    stage.setTitle("Window name");
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.setScene(scene);
	    stage.show();
    }
    
    @FXML
    private void editWs(ActionEvent event) throws IOException{
    	BorderPane page1 = (BorderPane) FXMLLoader.load(getClass().getResource("/view/EditWeatherStationView.fxml"));
	    Scene scene = new Scene(page1);
	    Stage stage = new Stage();
	    stage.getIcons().add(new Image("img/WeatherStationLogo.png"));
	    stage.setTitle("Window name");
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.setScene(scene);
	    stage.show();
    }
    
    @FXML
    private void exit(ActionEvent event) throws IOException{
    	if(window != null){
        	window.hide();
    	}
    }
    
    @FXML
    private void logOut(ActionEvent event) throws IOException{
    	if(window != null){
        	window.hide();
    	}
    }
    
    @FXML
    private void recordParam(ActionEvent event) throws IOException{
    	BorderPane page1 = (BorderPane) FXMLLoader.load(getClass().getResource("/view/RecordView.fxml"));
	    Scene scene = new Scene(page1);
	    Stage stage = new Stage();
	    stage.getIcons().add(new Image("img/WeatherStationLogo.png"));
	    stage.setTitle("New record parameters");
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.setScene(scene);
	    stage.show();
    }
    
    @FXML
    void celsiusFahrenheit(ActionEvent event) {
    	if(event.getSource().equals(rbCelsius)){
    		rbCelsius.setSelected(true);
    		rbFahrenheit.setSelected(false);
    	}
    	else if(event.getSource().equals(rbFahrenheit)){
    		rbFahrenheit.setSelected(true);
    		rbCelsius.setSelected(false);
    	}
    }
    
    public class CheckBoxTableCell<S, T> extends TableCell<S, T> {
        private final CheckBox checkBox;
        private ObservableValue<T> ov;

        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);
            setAlignment(Pos.CENTER);
            setGraphic(checkBox);
        } 
         
        @Override 
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(checkBox);
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
                }
                ov = getTableColumn().getCellObservableValue(getIndex());
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
                }
            }
        }
    }
}