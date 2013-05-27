package techgig;

import java.util.ArrayList;
import java.util.List;

/* simple node - this Trie stores strings of Characters */
class Node {
	Character data;
	boolean markedFinal = false;
	boolean isRoot = false;
	List<Node> references = new ArrayList<Node>();
	
	Node(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	Node(Character arg) {
		this.data = arg;
	}
	
	public Character getCharacter() {
		return data;
	}
	
}

interface StringTrie {
	public void add(String arg);
	public List<String> get(String arg);
}

class TreeTrie implements StringTrie {
	Node root;

	TreeTrie() {
		root = new Node(true);
	}
	
	public void add(String arg) {

	}

	public List<String> get(String arg) {
		return null;
	}
}

/* A Trie of Characters */
public class AppSimpleTrie {
	public static void main(String[] args) {
		StringTrie trie = new TreeTrie();
	}
}
