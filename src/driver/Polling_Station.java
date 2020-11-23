package driver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

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
	private ArrayList<Voter> voters = new ArrayList<Voter>();
	private int democratVotes;
	private int republicanVotes;
	private ArrayList<String> writeIns = new ArrayList<String>();
	public Voter voter;
	public Government_Database database;

	//methods
	//-------------------------------------------------------
	// Name: hashVoter
	// PreCondition:  Voter exists
	// PostCondition: Returns a hash of Voter data
	//---------------------------------------------------------
	public String hashVoter(Voter voter) {
		//creates a hash of the voters data
		return voter.generateHash();
	}
	
	private String hashVoters(String s1, String s2) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s_to_hash = s1 + s2;
		byte[] hash = digest.digest(s_to_hash.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);
		//return encoded;
		return "[" + s1 + s2 + "]";
	}

	//-------------------------------------------------------
	// Name: verifyEligibility
	// PreCondition:  Voter and Government Database exist
	// PostCondition: Returns true or false whether voter is in database
	//---------------------------------------------------------
	public boolean verifyEligibility(Voter voter) {
		//checks voter information and will verify with government database
		String voterHash = new String();
		voterHash = hashVoter(voter);
		//System.out.println(voterHash);
		ArrayList<String> hash_list = new ArrayList<String>();
		hash_list.clear();
		hash_list = database.getHashArray(voterHash);
		//System.out.println(hash_list);

		String newestHash = voterHash;
//		if (hash_list.get(0) == "left") {
//			newestHash = hashVoters(hash_list.get(1),voterHash);
//		}
//		else {
//			newestHash = hashVoters(voterHash,hash_list.get(1));
//		}

		for (int i = 0; i<=(hash_list.size()/2)+1;i=i+2) {
			if (hash_list.get(i) == "left") { //left case
				//hash i+1 on left side
				newestHash = hashVoters(hash_list.get(i+1),newestHash);
			} else { //right case
				//hash i+1 on right side
				newestHash = hashVoters(newestHash,hash_list.get(i+1));
			}
		}
		//System.out.println(newestHash);
		//System.out.println(database.rootHash());

		
		if (newestHash.equals(database.rootHash())) {
			return true;
		}
		else {
			return false;

		}
	}

	//-------------------------------------------------------
	// Name: vote
	// PreCondition:  
	// PostCondition: 
	//---------------------------------------------------------
	public String vote(Voter voter) {
		String vote;
		
		if (voters.contains(voter)) {
			System.out.println("Error: You have already casted your vote");
			vote = "";
		} else {
			voters.add(voter);
			
			Scanner myChoice = new Scanner(System.in);

			System.out.println("Please cast your vote:");
			System.out.println("1) Democrat\n" + "2) Republican\n" + "Any Number) Other");

			int choice = myChoice.nextInt();

			switch (choice) {
			case 1:
				vote = "Democrat";
				break;
			case 2:
				vote = "Republican";
				break;
			default: 
				System.out.println("Please write in a candidate:");
				Scanner myVote = new Scanner(System.in);
				vote = myVote.nextLine();
			}
			voter.setVote(vote);
		}
		return vote;
	}

	//-------------------------------------------------------
	// Name: countVote
	// PreCondition:  N/A
	// PostCondition: Counts the vote if voter is eligible
	//---------------------------------------------------------
	public void countVote(Voter voter) {
		//calls vote() and counts if vote is democratic or republican
		if (verifyEligibility(voter)) {
			String vote = vote(voter);
			if (vote == "Democrat") {
				this.democratVotes++;
			} else if (vote == "Republican") {
				this.republicanVotes++;
			} else if (vote.isBlank()){
				
			} else {
				writeIns.add(vote);
			}
		} else {
			System.out.println("Error: Voter may not be registered correctly");
		}
		
		System.out.println("Thank you for voting!");
	}
	
	public void displayResults() {
		System.out.println("\n*************************");
		System.out.println("Democratic Votes: " + democratVotes);
		System.out.println("Republican Votes: " + republicanVotes);
		System.out.println("Write-In candidates: " + writeIns.size());
		for (int i = 0; i < writeIns.size(); i++) {
			System.out.println("   -" + writeIns.get(i));
		}
		System.out.println("*************************");
	}

	//constructors
	public Polling_Station(String ID, String state, String county, Government_Database database) {
		this.ID = ID;
		this.state = state;
		this.county = county;
		this.database = database;
	}
}