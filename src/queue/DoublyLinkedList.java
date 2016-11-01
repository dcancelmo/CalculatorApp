package queue;
/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

public interface DoublyLinkedList<AnyType> {
	public void insertFront(AnyType x);
	public void insertBack(AnyType x);
	public void deleteFront(AnyType x);
	public void deleteBack(AnyType x);
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
}
