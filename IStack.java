package de.unistuttgart.dsass2014.ex01.p3;

public interface IStack<T> {
	
	/** Adds new element to the top */
	public void push(T t);
	/** Removes and returns the top element */
	public T pop();
	/** Returns the top element without removing */
	public T top();
	/** Checks if the stack is empty */
	public boolean isEmpty();
	
}
