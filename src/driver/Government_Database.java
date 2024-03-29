package driver;

import java.util.ArrayList;

/*****************************************
** File:    Government_Database.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Victor Villanueva & Lewis Wooler
** Date:    11/07/2020
** Section: 502
** E-mail:  vvillanueva11@tamu.edu
**
**   This file creates a large database of all voters that is used
** 	 to determine if a vote has been tampered with. The database is a
** 	 Merkle Tree that is used for comparison
**
**
***********************************************/

//generates Merkle tree of voter information
public class Government_Database {
	//data members
	private String rootHash;
	private Merkle_Tree voterDatabase;
	
	//methods
	//-------------------------------------------------------
	// Name: hashArray
	// PreCondition:  Voter hash exists within database
	// PostCondition: Returns minimum hashes needed to verify data
	//---------------------------------------------------------
	public ArrayList<String> getHashArray(String voter_hash) {
		//array of hashes to send to Polling Station to use for verification
		return voterDatabase.findHash(voter_hash);
	}
	
	//-------------------------------------------------------
	// Name: rootHash
	// PreCondition:  Voter hash exists in database
	// PostCondition: Returns the root hash of the Merkle Tree
	//---------------------------------------------------------
	public String rootHash() {
		//creates a merkle tree of all voters and return root hash
		return rootHash;
	}
	
	//constructors
	public Government_Database(ArrayList<Voter> voters) {
		this.voterDatabase = new Merkle_Tree(voters);
		this.rootHash = this.voterDatabase.getRoot();
	}
}
