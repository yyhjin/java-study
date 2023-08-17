package prob5;

public class MyStack03<T> {
	private T[] buffer;
	private int top;
	
	public MyStack03(int size) {
		buffer = (T[])new Object[size];
		top = -1;
	}
	
	public void push(T s) {
		if(top == buffer.length-1) {
			T[] newBuffer = (T[])new Object[buffer.length*2];
			for (int i = 0; i < buffer.length; i++) {
				newBuffer[i] = buffer[i];
			}
			buffer = newBuffer;
		}

		buffer[++top] = s;
	}
	
	public T pop() throws MyStackException {
		if(top < 0)
			throw new MyStackException();
		return buffer[top--];
	}
	
	public boolean isEmpty() {
		if(top < 0) 
			return true;
		return false;
	}
}