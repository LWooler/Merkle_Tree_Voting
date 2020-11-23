package driver;

import java.util.ArrayList;

/*****************************************
** File:    Driver.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Victor Villanueva & Lewis Wooler
** Date:    11/07/2020
** Section: 502
** E-mail:  vvillanueva11@tamu.edu
**
**   This file contains the main driver program for Project 1.
** 	 It creates a series of voters and polling stations that are 
**	 used to verify eligibility with the government database of 
** 	 all registered voters
**
**
** Description: This program will verify if a given voter is listed in a 
** government database to verify that they are eligible and that their
** information is correct. Using a Merkle Tree, a polling station will
** query the database and compute the root hash. If this root hash matches
** will that of the government database, then the voter is valid and the 
** database is trusted.
***********************************************/


public class Driver {

	public static void main(String[] args) {
		Voter voter1 = new Voter("1112223333", "12345678", "John","Hancock", 50, "Texas", "Brazos", "White");
		Voter voter2 = new Voter("2223334444", "23456789", "Joe","Hancock", 40, "Texas", "Bexar", "Hispanic");
		Voter voter3 = new Voter("3334445555", "34567890", "Jeff","Jones", 30, "Texas", "Harris", "White");
		
		Polling_Station station1 = new Polling_Station("123", "Texas", "Brazos");
		
		Government_Database database = new Government_Database();
		
		ArrayList<Voter> voters = new ArrayList<Voter>();
		voters.add(voter1);
		voters.add(voter2);
		voters.add(voter3);
		
		Merkle_Tree tree = new Merkle_Tree(voters);
		
		Node node = new Node("23878438329G");
		
		System.out.println("GOOD");
	}

}
