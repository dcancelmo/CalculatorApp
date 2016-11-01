package stack;

/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 */

public interface Stack<AnyType> {
	public boolean isEmpty();
	public void push(AnyType x);
	public AnyType pop();
	public AnyType peek();
}
