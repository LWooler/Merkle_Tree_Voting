package driver;
/*****************************************
** File:    Voter.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Victor Villanueva & Lewis Wooler
** Date:    11/07/2020
** Section: 502
** E-mail:  vvillanueva11@tamu.edu
**
**   This file creates a voter and contains getters and setters
**	 that help manipulate the voter's information
**
**
***********************************************/

public class Voter {
	//data members
	private String SSN;
	private String DL;
	private String firstName;
	private String lastName;
	private int age;
	private String state;
	private String county;
	
	private String ethnicity;
	
	//methods
	public String getSSN() {return this.SSN;}
	public void setSSN(String SSN) {this.SSN = SSN;}
	
	public String getDL() {return this.DL;}
	public void setDL(String DL) {this.DL = DL;}
	
	public String getfirstName() {return this.firstName;}
	public void setfirstName(String firstName) {this.firstName = firstName;}
	
	public String getlastName() {return this.lastName;}
	public void setlastName(String lastName) {this.lastName = lastName;}
	
	//constructors
	public Voter(String SSN, String DL, String firstName, String lastName, int age,
			String state, String county, String ethnicity) {
		this.SSN = SSN;
		this.DL = DL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.state = state;
		this.county = county;
		this.ethnicity = ethnicity;
	}
	
}
