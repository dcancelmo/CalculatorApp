package stack;

/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class MyStack extends ListClass implements Stack{
	
	//Constructor
	public MyStack() {
		ListClass linkedList = new ListClass();
		linkedList.head = null;
	}

	//Tests to see if the stack is empty.
	@Override
	public boolean isEmpty() {
		if (head == null) return true;
		else return false;
	}

	//Inserts a new entry to the head of the stack.
	@Override
	public void push(Object entry) {
		MyNode newNode = new MyNode();
		newNode.data = entry;
		newNode.next = head;
		head = newNode;
		
	}

	//Removes a data entry from the head of the stack and returns it.
	@Override
	public Object pop() {
		if (isEmpty()) return null;
		MyNode entry = head;
		head = entry.next;
		return entry.data;
	}

	//Returns the entry from the head of the stack without removing it.
	@Override
	public Object peek() {
		return head.data;
	}
	
	
	
}
