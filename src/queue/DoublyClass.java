package queue;
/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class DoublyClass implements DoublyLinkedList {
	
	protected MyDoubleNode head = null;
	protected MyDoubleNode tail = null;
	
	public DoublyClass() {
		head = new MyDoubleNode();      
		tail = new MyDoubleNode();      
		head.prev = null; 
		head.next = tail;
		tail.prev = head;
		tail.next = null;
	}

	//Method inserts each new item to the head of list. Expected runtime of insert is constant.
	@Override
	public void insertFront(Object entry) {
		if (contains(entry)) return; 		//Prevents double entries
		MyDoubleNode link = new MyDoubleNode();
		link.data = entry;
		link.next = head;
		MyDoubleNode temp = head;
		head = link;
		head.prev = null;
		head.next = temp;
		temp.prev = head;
		(temp.next).prev = temp;
	}
	
	//Method inserts each new item to the tail of list.  Expected runtime of insert is constant.
	@Override
	public void insertBack(Object entry) {
		if (contains(entry)) return; 		//Prevents double entries
		MyDoubleNode link = new MyDoubleNode();
		link.data = entry;
		link.prev = tail;
		MyDoubleNode temp = tail;
		tail = link;
		tail.prev = temp;
		tail.next = null;
		temp.next = tail;
	}

	//Searches for the searchObj and deletes it from the list. If not found, returns.
	@Override
	public void deleteFront (Object searchObj) {
		MyDoubleNode current = head.next;
		current = head;
		current.prev = null;
	}

	//Searches for the searchObj and deletes it from the list. If not found, returns.
	@Override
	public void deleteBack (Object searchObj) {
		MyDoubleNode current = tail.prev;
		current = tail;
		tail.next = null;
		
	}
	
	//Method searches through entire list to find if the list contains the searchObj
	@Override
	public boolean contains(Object searchObj) {
		if (head == null) return false;
		MyDoubleNode current = head.next;
		//Loops through entire list to identify the equality of each entry with the searchObj
		while (current.data != null) {
			if (current.data.equals(searchObj)) return true;
			current = current.next;
		}
		return false;
	}

	//Finds the searchObj in the list and returns it. If not found, returns null. Identical to contains except returns the object not true.
	@Override
	public Object lookup(Object searchObj) {
		if (head == null) return null;
		MyDoubleNode current = head;
		//Loops through entire list to identify the equality of each entry with the searchObj
		while (current.data != null) {
			if (current.data.equals(searchObj)) return current.data;
			current = current.next;
		}
		return null;
	}

	//Method tests to see if the list has any data stored.
	@Override
	public boolean isEmpty() {
		if (head.data == null && tail.data == null) return true;
		else return false;
	}

	//Method prints each item of a list separated by tabs. Expected runtime of printList is n.
	@Override
	public void printList() {
		if (head == null) return;
		System.out.print(head.data.toString());		//Printing this first prevents extra separating tabs
		MyDoubleNode current = head.next;
		//Loops through entire list to print the next item.
		while (current.data != (null)) {
			System.out.printf("%d", current.data.toString());
			current = current.next;
		}		
	}

	//Method prints each item of a list in reverse separated by tabs. Expected runtime of printList is n.
	@Override
	public void printListRev() {
		if (tail.prev == null) return;
		MyDoubleNode current = tail.prev.prev;
		//Loops through entire list to print the prev item.
		while (current != (null)) {
			System.out.printf("%d", current.data.toString());
			current = current.prev;
		}		
	}

}
