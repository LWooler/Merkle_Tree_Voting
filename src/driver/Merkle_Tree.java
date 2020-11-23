package driver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Stack;


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
	private ArrayList<String> minimum_to_verify = new ArrayList<String>();
	
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
	
	public ArrayList<String> findHash(String hash) {
		Stack<String> dfs_stack = new Stack<String>();
		dfs(hash, this.rootNode, dfs_stack);
		ArrayList<String> temp = new ArrayList<String>();
		while(!(this.minimum_to_verify.isEmpty())) {
			temp.add(this.minimum_to_verify.remove(0));
		}
		return temp;
	}
	
	public String dfs(String hash, Node node, Stack<String> dfs_stack) {
		dfs_stack.push(node.getHash());
		//System.out.println(dfs_stack.toString());
		//System.out.println("Checking "+node.getHash()+" with "+hash);
		if (node.getHash().equals(hash)) {
			//System.out.println("found");
			return dfs_stack.toString();
		}
		if (node.getLeft() != null) {
			String s1 = dfs(hash, node.getLeft(),dfs_stack);
			String s2 = dfs(hash, node.getRight(),dfs_stack);
			dfs_stack.pop();
			if (!(s1.isEmpty())) {
				//System.out.print(node.getRight().getHash());
				minimum_to_verify.add("right");
				minimum_to_verify.add(node.getRight().getHash());
				return s1;
			}
			else if (!(s2.isEmpty())) {
				minimum_to_verify.add("left");
				minimum_to_verify.add(node.getLeft().getHash());
				return s2;
			}
			else {
				return "";
			}
		}
		else {
			dfs_stack.pop();
		}
		return "";
	}
	
	private String generateHash(String s1, String s2) {
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
				//System.out.println(newNode.getHash());
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
			Node newNode = new Node(voter.generateHash());
			//System.out.println(newNode.getHash());
			voterHashes.add(newNode);
		}
		generateTree(voterHashes);
		//hash the array of voters and stores root as string in rootNode
	}
}
