package driver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
	private String vote;
	
	//methods
	public String getSSN() {return this.SSN;}
	public void setSSN(String SSN) {this.SSN = SSN;}
	
	public String getDL() {return this.DL;}
	public void setDL(String DL) {this.DL = DL;}
	
	public String getFirstName() {return this.firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	
	public String getLastName() {return this.lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getVote() {return this.vote;}
	public void setVote(String vote) {this.vote = vote;}
	
	public String generateHash() {
		String s_to_hash = this.SSN+this.DL+this.firstName+this.lastName+this.age+this.state+this.county+this.ethnicity;
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(s_to_hash.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);
		//return encoded;
		return this.firstName;
	}
	
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
