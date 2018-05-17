package de.unistuttgart.dsass2014.ex06.p1;
/**
 * DSA SS 2014 - Homework sheet 6- Exercise 1d
 * 
 * This class is a max-heapsort.
 * @author Andreas Senn(2853116), Fotios Karamitsos(2862424), Bianca Faßbender(2884059)
 * @version 1.0
 */
public class Sorter {

	public static <T extends Comparable<T>> void heapSort(ISimpleList<T> list) {
		int lengthArr =list.size()-1;
		if(lengthArr%2==1){
			lengthArr -=1;
		}
		/*Every node which has children will be send to the separate method which lets the node drop down in the heap.*/
		for(int i = (lengthArr/2)-1; i>=0 ; --i){
			dropDown(i, list);
		}
		/*The first and the last node swap.
		Then the first node drops down.*/
		int node;
		for(int length = list.size()-1;length>0;){
			list.swap(0, length);
			--length;
			node = 0;
			int leftChild = node*2+1;
			int rightChild = leftChild+1;
			while(leftChild < length){
				int biggerChild = 0;
				/*selects the bigger child*/
				if(list.get(rightChild).compareTo(list.get(leftChild)) > 0){
					biggerChild = rightChild;
				}
				else{
					biggerChild = leftChild;
				}
				/*compares the fathernode with the bigger child and swaps if the child is bigger.*/
				if(list.get(node).compareTo(list.get(biggerChild)) < 0){
					list.swap(node, biggerChild);
					node = biggerChild;
				}
				else{
					break;
				}
				
				leftChild = (node*2)+1;
				rightChild = leftChild+1;
			}
		}
		



	}
	/*separate method to create the heap structure, not for sorting the heap*/
	public static <T extends Comparable<T>> void dropDown(int node, ISimpleList<T> list){
		int leftChild = node*2+1;
		int rightChild = leftChild+1;
		while(leftChild < list.size()){
			int biggerChild = 0;
			/*selects the bigger child*/
			if(list.get(rightChild).compareTo(list.get(leftChild)) > 0){
				biggerChild = rightChild;
			}
			else{
				biggerChild = leftChild;
			}
			/*compares the fathernode with the bigger child and swaps if the child is bigger.*/
			if(list.get(node).compareTo(list.get(biggerChild)) < 0){
				list.swap(node, biggerChild);
				node = biggerChild;
			}
			else{
					break;
			}
			
			leftChild = (node*2)+1;
			rightChild = leftChild+1;
		}
			
	}
}

