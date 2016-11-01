package queue;
/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

public interface Queue<AnyType> {
	public boolean isEmpty();
	public void enqueue(AnyType x);
	public AnyType dequeue();
	public AnyType peek();
}
