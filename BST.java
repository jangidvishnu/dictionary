package main;

import java.util.Dictionary;

public class BST {
	public Node headNode;
	
	public BST() {
		this.headNode = null;
	}
	
	public boolean addPair(String key, String value) throws AssertionError {
		if(key == null || value == null) {
			throw new AssertionError("Parameters Cannot be null");
		}
		return addNode(this.headNode, key, value);
	}
	
	private boolean addNode(Node root, String key, String value) {
		if(root == null) {
			root = new Node(key,value);
			return true;
		}
		else if(root.key.compareTo(key) > 0) {
			boolean isAdded  = addNode(root.leftChild,key,value);
			if(isAdded) {
				root.leftChild.parentNode = root;
			}
			return isAdded;
		}
		else {
			boolean isAdded  = addNode(root.rightChild,key,value);
			if(isAdded) {
				root.rightChild.parentNode = root;
			}
			return isAdded;
		}
	}
	
	public boolean deletePair(String key) {
		return deleteNode(this.headNode,key);
	}
	
	private boolean deleteNode(Node root,String key) {
		if(root == null) {
			return false;
		}
		if (key.compareTo(root.key) < 0)
            return deleteNode(root.leftChild, key);
        else if (key.compareTo(root.key) > 0)
             return deleteNode(root.rightChild, key);
        else {
			if(root.leftChild == null && root.rightChild == null) {
				if(root.parentNode.key.compareTo(root.key) > 0) {
					root.parentNode.leftChild = null;
				}
				else {
					root.parentNode.rightChild = null;
				}
			}
			else if(root.leftChild == null && root.rightChild != null) {
				if(root.parentNode.key.compareTo(root.key) > 0) {
					root.parentNode.leftChild = root.rightChild;
				}
				else {
					root.parentNode.rightChild = root.rightChild;
				}
				root.rightChild.parentNode = root.parentNode;
			}
			else if(root.leftChild != null && root.rightChild == null) {
				if(root.parentNode.key.compareTo(root.key) > 0) {
					root.parentNode.leftChild = root.leftChild;
				}
				else {
					root.parentNode.rightChild = root.leftChild;
				}
				root.leftChild.parentNode = root.parentNode;
			}
			else {
				Node nodeToReplace = minKey(root);
				if(nodeToReplace.parentNode.key.compareTo(nodeToReplace.key) > 0) {
					nodeToReplace.parentNode.leftChild = null;
				}
				else {
					root.parentNode.rightChild = null;
				}
				if(root.parentNode.key.compareTo(root.key) > 0) {
					root.parentNode.leftChild = nodeToReplace;
				}
				else {
					root.parentNode.rightChild = nodeToReplace;
				}
				nodeToReplace.parentNode = root.parentNode;
				
			}
			return true;
		}
		
	}
	
	private Node minKey(Node root)
    {
        while (root.leftChild != null) 
        {
            root = root.leftChild;
        }
        return root;
    }
	
}
