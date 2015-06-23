package model;

public class UsersModel {

	private Integer id;
	private String name;
    private String email;
    private Integer role;
    
    public UsersModel(Integer id, String name, String email , Integer role) {
    
    	this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
    
    
}
