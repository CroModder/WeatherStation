package model;

public class WeatherStationModel {
	
	private Integer id;
	private String name;
    private String ip;
    private Integer port;
    private Integer interval;
    private String sensor;
    
    public WeatherStationModel(Integer id, String name, String ip, Integer port, Integer interval, String sensor) {
    	this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.interval = interval;
        this.sensor = sensor;
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

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

}
