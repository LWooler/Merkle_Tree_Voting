package driver;
/*****************************************
** File:    Polling_Station.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Victor Villanueva & Lewis Wooler
** Date:    11/07/2020
** Section: 502
** E-mail:  vvillanueva11@tamu.edu
**
**   This file describes a polling station at which a voter
** 	 may cast a vote. The polling station verifies the voter's 
**	 eligibility.
**
**
***********************************************/

public class Polling_Station {
	//data members
	private String ID;
	private String state;
	private String county;
	private String[] voters;
	private int democratVotes;
	private int republicanVotes;
	Government_Database database;

	//methods
	//-------------------------------------------------------
	// Name: hashVoter
	// PreCondition:  Voter exists
	// PostCondition: Returns a hash of Voter data
	//---------------------------------------------------------
	public String hashVoter(Voter voter) {
		//creates a hash of the voters data
		return null;
	}
	
	//-------------------------------------------------------
	// Name: verifyEligibility
	// PreCondition:  Voter and Government Database exist
	// PostCondition: Returns true or false whether voter is in database
	//---------------------------------------------------------
	public boolean verifyEligibility(Voter voter, Government_Database database) {
		//checks voter information and will verify with government database
		return true;
	}
	
	//-------------------------------------------------------
	// Name: countVote
	// PreCondition:  N/A
	// PostCondition: Counts the vote if voter is eligible
	//---------------------------------------------------------
	public void countVote() {
		//calls vote() and counts if vote is democratic or republican
	}
	
	//constructors
	public Polling_Station(String ID, String state, String county) {
		this.ID = ID;
		this.state = state;
		this.county = county;
	}
}