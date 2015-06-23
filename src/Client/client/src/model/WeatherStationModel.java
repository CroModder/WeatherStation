package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import socket.Client;
import controller.DbController;
import controller.MainPanelController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.json.JSONObject;

public class WeatherStationModel {
	public static HashMap<Integer, Client> deviceSocketList = new HashMap<Integer, Client>();
	public static MainPanelController mainPanelController = new MainPanelController();

	public static void setMainPanelController(
			MainPanelController mainPanelController) {
		WeatherStationModel.mainPanelController = mainPanelController;
	}

	private Client client;
	private JSONObject objectCommand = new JSONObject();
	public static String UserName = "";
	public static String PasswordHash = "";
	BooleanProperty selected;
	private Integer id;
	private String name;
    private String ip;
    private Integer port;
    private Integer role;
    private Integer interval;

	public WeatherStationModel(boolean selected, Integer id, String name, String ip,Integer port, Integer role,Integer interval) {
    	this.selected = new SimpleBooleanProperty(selected);
    	this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.role = role;
        this.interval = interval;
        this.selected = new SimpleBooleanProperty(selected);
        
        this.selected.addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                MainPanelController.setListSelection(new TableViewSelection(t1, id, ip, port));
            	System.out.println("Selection: " + t1 + " For Ws: " + id + " " + ip + " " + port + " " + interval);
               	BigDecimal sum = BigDecimal.ZERO;
               	int check = 0;
            	for(int i=0;i<DbController.weatherStationData.size();i++){
            		if(DbController.weatherStationData.get(i).selectedProperty().getValue() == true){
            			check++;
            			sum = sum.add(new BigDecimal(DbController.weatherStationData.get(i).getInterval()));
            		}
            	}
            	if(check > 0){
            	mainPanelController.setAverageInt(sum.longValue()/check);
            	}else{
            		if(mainPanelController.getTimer() != null){
                		mainPanelController.getTimer().cancel();
                		mainPanelController.setExec(false);
            		}
            	}
                if(t1 == true){
                	if(mainPanelController.wsNameDelete.contains(name)){
                		mainPanelController.wsNameDelete.remove(name);
                	}
                	mainPanelController.wsName = name;
	            	if(deviceSocketList.get(id) == null){
	            		client= new Client();
	            		objectCommand = new JSONObject();
	            		deviceSocketList.put(id, client);
	            		objectCommand.put("action", "auth");
	            		objectCommand.put("username", UserName);
	            		objectCommand.put("password", PasswordHash);
	            		objectCommand.put("wsName", name);
	            		try {
		            		if(deviceSocketList.get(id).initialize(ip,port,0) == false){
		            			DbController.weatherStationData.indexOf(name);
		            			for(int i= 0;i<DbController.weatherStationData.size();i++){
		            				if(DbController.weatherStationData.get(i).getId() == id){
		            					DbController.weatherStationData.get(i).selected.set(false);
        							}
		            			}
		            		}else{
		            			deviceSocketList.get(id).serverData(objectCommand);
		            		}
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
	            		System.out.println("\nAutentification and send data " + deviceSocketList.get(id));
	            	}else{
		            	objectCommand = new JSONObject();
		            	objectCommand.put("action", "auth");
	            		objectCommand.put("username", UserName);
	            		objectCommand.put("password", PasswordHash);
	            		objectCommand.put("wsName", name);
	            		System.out.println(objectCommand);
	            		try {
	            			if(deviceSocketList.get(id).initialize(ip,port,0) == false){
		            			DbController.weatherStationData.indexOf(name);
		            			for(int i= 0;i<DbController.weatherStationData.size();i++){
		            				if(DbController.weatherStationData.get(i).getId() == id){
		            					DbController.weatherStationData.get(i).selected.set(false);
        							}
		            			}
		            		}else{
		            			deviceSocketList.get(id).serverData(objectCommand);
		            		}
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
		                System.out.println("\nSend autentification, add device to hashmap and send data " + deviceSocketList);
	            	}
            }else if(t1 == false){
            	mainPanelController.wsNameDelete.add(name);
            	objectCommand = new JSONObject();
            	objectCommand.put("action", "stop");
        		try {
        			System.out.println(objectCommand);
					deviceSocketList.get(id).serverData(objectCommand);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
                System.out.println("\nStop send data ");
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
	
    public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

}
