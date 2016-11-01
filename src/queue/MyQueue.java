package queue;
/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class MyQueue extends DoublyClass implements Queue {
	
	//Constructor
	public MyQueue() {
		DoublyClass doubleList = new DoublyClass();
	}
	
	//Tests to see if the stack is empty.
	@Override
	public boolean isEmpty() {
		try {
			if (head.data == null && tail.data == null) return true;
			else return false;
		} catch (NullPointerException e) {
			return true;
		}
	}

	//Inserts a new entry to the tail of the stack.
	@Override
	public void enqueue(Object entry) {
		if (head.data == null) head.data = entry;
		else insertBack(entry);
	}

	//Removes and returns an entry from the head of the stack.
	@Override
	public Object dequeue() {
		MyDoubleNode entry = head;
		while (entry.data == null) {
			entry = entry.next;
		}
		MyDoubleNode newHead = head.next;

		try {
			while (newHead.data == null) {
				newHead = newHead.next;
			}
			newHead.prev = null;
			
		} catch (NullPointerException e) {
			head.next = tail;
		}
		head = newHead;
		return entry.data;
	}

	//Returns the entry from the head of the stack without removing it.
	@Override
	public Object peek() {
		MyDoubleNode entry = head;
		while (entry.data == null) {
			entry = entry.next;
		}
		return entry.data;
	}
	
}
