import java.util.ArrayDeque;
/**
 * 
 * ArrayStack class implemented using the java API ArrayDeque<E>.
 * 
 * @author Mohammed A. Alsaggaf
 * @since 4-3-2020
 * @version 1.0 
 *
 */
public class ArrayStack<E> {
	
	private ArrayDeque<E> stack;
	private static final int CAPACITY=16;
	
	public ArrayStack() {
		this(CAPACITY);
	}
	
	public ArrayStack(int capacity) {
		stack=new ArrayDeque<E>(capacity);
	}
	
	public void push(E e) {
		stack.push(e);
	}
	
	public E pop() {
		return stack.pop();
	}
	
	public E peek() {
		return stack.peek();
	}
	
	public void clear() {
		stack.clear();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public int size() {
		return stack.size();
	}
}
