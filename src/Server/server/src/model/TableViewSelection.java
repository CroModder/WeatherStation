package model;


public class TableViewSelection {
	Boolean selected;
    String ip;
    Integer port;
    
    public TableViewSelection(boolean selected, Integer id, String ip,Integer port) {
    	this.selected = selected;
        this.ip = ip;
        this.port = port;
    }
    
	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
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