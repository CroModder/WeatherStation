package model;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.LineChart;
import controller.DbController;
import controller.HistoricalController;
import controller.Language;
import controller.LogInController;

public class HistoricalWeatherStationModel {
	private LineChart.Series<String, Number> series = new LineChart.Series<String, Number>();
	private static HistoricalController historicalController = new HistoricalController();
	
	public static void setHistoricalController(
			HistoricalController historicalController) {
		HistoricalWeatherStationModel.historicalController = historicalController;
	}

	private DbController dbController = new DbController();
	BooleanProperty selected;
	private Integer id;
	private String name;
    private String ip;
    private Integer port;
    private Integer role;
    
	private Locale locale;
	private ResourceBundle bundle;
    
    public HistoricalWeatherStationModel(boolean selected, Integer id, String name, String ip,Integer port, Integer role) {
    	this.selected = new SimpleBooleanProperty(selected);
    	this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.role = role;
        this.selected = new SimpleBooleanProperty(selected);
        
        this.selected.addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
            	try {
            		if(t1 == false){
            			int index = historicalController.getTabIndex();
            			if(index == 0){
                			for(int i=0;i<historicalController.getLineChart().getData().size();i++){
        		    			System.out.println("name of series " + historicalController.getLineChart().getData().get(i).getName());
        		    			if(name.equals(historicalController.getLineChart().getData().get(i).getName())){
        		    				historicalController.getLineChart().getData().remove(i);
        		    			}
        		    		}
            			}else if(index == 1){
                			for(int i=0;i<historicalController.getLineChartAir().getData().size();i++){
        		    			System.out.println("name of series " + historicalController.getLineChartAir().getData().get(i).getName());
        		    			if(name.equals(historicalController.getLineChartAir().getData().get(i).getName())){
        		    				historicalController.getLineChartAir().getData().remove(i);
        		    			}
                			}
            			}else if(index == 2){
                			for(int i=0;i<historicalController.getLineChartMag().getData().size();i++){
        		    			System.out.println("name of series " + historicalController.getLineChartMag().getData().get(i).getName());
        		    			if(name.equals(historicalController.getLineChartMag().getData().get(i).getName())){
        		    				historicalController.getLineChartMag().getData().remove(i);
        		    			}
                			}
            			}
            		}else if(historicalController.getBeginDate() != null && historicalController.getEndDate() != null){
            			for(int k=0;k<DbController.historyWeatherStationData.size();k++){
            				if(DbController.historyWeatherStationData.get(k).getId() != id && DbController.historyWeatherStationData.get(k).getSelected().getValue() == true){
            					System.out.println(DbController.historyWeatherStationData.get(k).getSelected());
            					DbController.historyWeatherStationData.get(k).getSelected().setValue(false);
            					System.out.println(DbController.historyWeatherStationData.get(k).getSelected());
            				}
            			}
            			if(historicalController.getTabIndex() == 0){
            				series = dbController.historicalDataTemp(historicalController.getBeginDate(), historicalController.getEndDate(), id, LogInController.getLogUserId(),historicalController.getRadioSelection());
            				if(series.getData().size() > 0){
            					historicalController.setDateAxis(series, name, 0);
                			}else{
                    			HistoricalWeatherStationModel.this.selected.set(false);
                    			locale = new Locale(Language.getLanguage());
                    	    	bundle = ResourceBundle.getBundle("language.lang", locale);
                    			historicalController.warningMessage(bundle.getString("noData"), bundle.getString("noDataForWs"));
                			}
            			}else if(historicalController.getTabIndex() == 1){
            				series = dbController.historicalDataAirP(historicalController.getBeginDate(), historicalController.getEndDate(), id, LogInController.getLogUserId(),historicalController.getRadioSelection());
                			if(series.getData().size() > 0){
                				historicalController.setDateAxis(series, name, 1);
                			}else{
                    			HistoricalWeatherStationModel.this.selected.set(false);
                    			locale = new Locale(Language.getLanguage());
                    	    	bundle = ResourceBundle.getBundle("language.lang", locale);
                    			historicalController.warningMessage(bundle.getString("noData"), bundle.getString("noDataForWs"));
                			}
            			}else if(historicalController.getTabIndex() == 2){
            				series = dbController.historicalDataMag(historicalController.getBeginDate(), historicalController.getEndDate(), id, LogInController.getLogUserId());
                			if(series.getData().size() > 0){
                				historicalController.setDateAxis(series, name, 2);
                			}else{
                    			HistoricalWeatherStationModel.this.selected.set(false);
                    			locale = new Locale(Language.getLanguage());
                    	    	bundle = ResourceBundle.getBundle("language.lang", locale);
                    			historicalController.warningMessage(bundle.getString("noData"), bundle.getString("noDataForWs"));
                			}
            			}else {
            				
            			}
            		}else{
            			HistoricalWeatherStationModel.this.selected.set(false);
            			locale = new Locale(Language.getLanguage());
            	    	bundle = ResourceBundle.getBundle("language.lang", locale);
            			historicalController.errorMessage(bundle.getString("errorNoEndDate"), bundle.getString("selectEndDate"));
            		}
            	} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });            
    }
    
    public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public BooleanProperty selectedProperty() { return selected; }

	public BooleanProperty getSelected() {
		return selected;
	}

	public void setSelected(BooleanProperty selected) {
		this.selected = selected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
