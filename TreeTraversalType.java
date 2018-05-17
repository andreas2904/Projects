package de.unistuttgart.dsass2014.ex04.p3;

public enum TreeTraversalType {
	PREORDER("pre"),
	INORDER("in"),
	POSTORDER("post"),
	LEVELORDER("level");
	
	public String type;
	
	private TreeTraversalType(String st) {
		type = st;
	}
	
	public String getType(){
		return type;
	}
}
