package driver;

import java.util.ArrayList;

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
	private Node rootNode;
	
	//methods
	//-------------------------------------------------------
	// Name: getRoot
	// PreCondition:  Root node must exist
	// PostCondition: Returns root node as string
	//---------------------------------------------------------
	public String getRoot() {
		//returns the root
		return rootNode.getHash();
	}
	
	public Node[] findHash() {
		return null;
	}
	
	private String generateHash(String s1, String s2) {
		return s1 + s2;
	}
	
	private String generateHash(Voter voter) {
		return voter.getfirstName();
	}
	
	private void generateTree(ArrayList<Node> hashes) {
		if (hashes.size() == 1) {
			this.rootNode = hashes.remove(0);
		}
		else {
			ArrayList<Node> nextLevel = new ArrayList<Node>();
			while (hashes.size() >= 1) {
				Node node1 = hashes.remove(0);
				Node node2;
				if (hashes.isEmpty()) {
					node2 = node1;
				}
				else {
					node2 = hashes.remove(0);
				}
				Node newNode = new Node(generateHash(node1.getHash(), node2.getHash()));
				System.out.println(newNode.getHash());
				newNode.setLeft(node1);
				newNode.setRight(node2);
				nextLevel.add(newNode);
			}
			generateTree(nextLevel);
		}
	}
	
	//constructors
	public Merkle_Tree(ArrayList<Voter> voters) {
		ArrayList<Node> voterHashes = new ArrayList<Node>();
		while (!(voters.isEmpty())) {
			Voter voter = voters.remove(0);
			Node newNode = new Node(generateHash(voter));
			voterHashes.add(newNode);
		}
		generateTree(voterHashes);
		//hash the array of voters and stores root as string in rootNode
	}
}
