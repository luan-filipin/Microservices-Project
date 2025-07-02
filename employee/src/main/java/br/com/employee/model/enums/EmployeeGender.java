package br.com.employee.model.enums;

public enum EmployeeGender {

	HOMEM("homem"),
	MULHER("mulher");
	
	private String gender;
	
	EmployeeGender(String gender){
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
}
