package br.com.employee.model.enums;

public enum EmployeeRole {

	ADMIN("admin"),
	USER("user");
	
	private String role;
	
	EmployeeRole(String role){
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
