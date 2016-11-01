package stack;

/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

public interface SimpleLinkedList<AnyType> {
	public void insert(AnyType x);
	public void delete();
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
}
