package driver;
/*****************************************
** File:    Merkle_Tree.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Victor Villanueva & Lewis Wooler
** Date:    11/07/2020
** Section: 502
** E-mail:  vvillanueva11@tamu.edu
**
**   This file contains all of the methods to create a Merkle Tree
**
**
***********************************************/

public class Merkle_Tree {
	//data members
	private Voter[] voters;
	private Node rootNode;
	
	//methods
	//-------------------------------------------------------
	// Name: getRoot
	// PreCondition:  Root node must exist
	// PostCondition: Returns root node as string
	//---------------------------------------------------------
	public String getRoot() {
		//returns the root
		return rootNode.hash;
	}
	
	//constructors
	public Merkle_Tree(Voter[] voters) {
		this.voters = voters;
		//hash the array of voters and stores root as string in rootNode
	}
}
