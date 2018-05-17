package de.unistuttgart.dsass2014.ex04.p3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
/**
 * DSA SS 2014 - Homework sheet 4- Exercise 3
 * 
 * This class return's different types of Iterators.
 * @author Andreas Senn(2853116), Fotios Karamitsos(2862424), Bianca Faßbender(2884059)
 * @version 1.0
 */
public class BinarySearchTree<T extends Comparable<T>> implements
		IBinarySearchTreeIterable<T> { 

	private volatile IBinaryTreeNode<T> root;

	public BinarySearchTree() {
		this.root = null;
	}

	@Override
	public void insert(T t) {
		this.root = this.insert(this.root, t);
	}

	private IBinaryTreeNode<T> insert(IBinaryTreeNode<T> node, T t) {
		if (node == null) {
			IBinaryTreeNode<T> newNode = new BinaryTreeNode<>();
			newNode.setValue(t);
			return newNode;
		}
		if (t.compareTo(node.getValue()) < 0) {
			node.setLeftChild(this.insert(node.getLeftChild(), t));
		} else if (t.compareTo(node.getValue()) > 0) {
			node.setRightChild(this.insert(node.getRightChild(), t));
		}
		return node;
	}

	@Override
	public IBinaryTreeNode<T> getRootNode() {
		return this.root;
	}

	@Override
	public Iterator<T> iterator(TreeTraversalType traversalType) {
		
		switch(traversalType){
		case INORDER:
			return new IterIn(root);
		case LEVELORDER:
			return new IterLevel(root);
		case POSTORDER:
			return new IterPost(root);
		case PREORDER:
			return new IterPre(root);
		default:
			break;
		}
		return null;
		
	}

	
	
	


	public class IterPre implements Iterator<T>{
		PriorityQueue<T> arr;
		
		public IterPre(){
			arr = new PriorityQueue<T>();
		}
		public IterPre(IBinaryTreeNode<T> node){
			pre(node);
		}
		public void pre(IBinaryTreeNode<T> node){
			arr.add(node.getValue());
			if(node.getLeftChild() != null){
				pre(node.getLeftChild());			
			}
			if(node.getRightChild() != null){
				pre(node.getRightChild());			
			}
			
			
		}
		
		public boolean hasNext() {
			return (!arr.isEmpty());
		}

		public T next() {
			
			return arr.poll();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		
	}
	public class IterIn implements Iterator<T>{
		PriorityQueue<T> arr;
		
		public IterIn(){
			arr = new PriorityQueue<T>();
		}
		
		public IterIn(IBinaryTreeNode<T> node){
			in(node);
		}
		
		public void in(IBinaryTreeNode<T> node){
			if(node.getLeftChild() != null){
				in(node.getLeftChild());			
			}
			arr.add(node.getValue());
			if(node.getRightChild() != null){
				in(node.getRightChild());			
			}
		}
		
		public boolean hasNext() {
			return (!arr.isEmpty());
		}

		public T next() {
			
			return arr.poll();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		
	}
	public class IterPost implements Iterator<T>{
		PriorityQueue<T> arr;
		
		public IterPost(){
			arr = new PriorityQueue<T>();
		}
		
		public IterPost(IBinaryTreeNode<T> node){
			post(node);
		}
		
		public void post(IBinaryTreeNode<T> node){
			if(node.getLeftChild() != null){
				post(node.getLeftChild());			
			}
			if(node.getRightChild() != null){
				post(node.getRightChild());			
			}
			arr.add(node.getValue());
		}

		public boolean hasNext() {
			return (!arr.isEmpty());
		}

		public T next() {
			
			return arr.poll();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		
	}
	
	
	public class IterLevel implements Iterator<T>{
		PriorityQueue<T> arr;
		
		public IterLevel(){
			arr = new PriorityQueue<T>();
		}

		public IterLevel(IBinaryTreeNode<T> node){
			level(node);
		}
		
		public void level(IBinaryTreeNode<T> node){
			PriorityQueue<IBinaryTreeNode<T>> pq = new PriorityQueue<IBinaryTreeNode<T>>();
			pq.add(node);
			while(!pq.isEmpty()){
				if(node.getLeftChild() != null){
					pq.add(node.getLeftChild());
				}
				else if(node.getRightChild() != null){
					pq.add(node.getRightChild());
				}
				
				arr.add(node.getValue());
				node = pq.poll();
			}
		}
		

		public boolean hasNext() {
			return (!arr.isEmpty());
		}

		public T next() {
			
			return arr.poll();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
}
