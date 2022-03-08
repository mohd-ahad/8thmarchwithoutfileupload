package emp.portal.service;

public class EmployeeDTO {

private long id;

private String name;

private String designation;

private String  acc_Mapped;

private String  rep_Manager;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public String getAcc_Mapped() {
	return acc_Mapped;
}

public void setAcc_Mapped(String acc_Mapped) {
	this.acc_Mapped = acc_Mapped;
}

public String getRep_Manager() {
	return rep_Manager;
}

public void setRep_Manager(String rep_Manager) {
	this.rep_Manager = rep_Manager;
}
}
