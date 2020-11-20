package driver;
/*****************************************
** File:    Node.java
** Project: CSCE 314 Project 1, Fall 2020
** Author:  Victor Villanueva & Lewis Wooler
** Date:    11/07/2020
** Section: 502
** E-mail:  vvillanueva11@tamu.edu
**
**   This file serves as a helper file to create each node for
**	 the Merkle Tree
**
**
***********************************************/

public class Node {
	//data members
	public String hash;
	private Node left;
	private Node right;
	
	//methods
	public void setLeft(Node node) {this.left = node;}
	public void setRight(Node node) {this.right = node;}
	
	//constructors
	public Node(String hash) {
		this.hash = hash;
		left = null;
		right = null;
	}
}
