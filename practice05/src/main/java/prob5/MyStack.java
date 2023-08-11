package prob5;

public class MyStack {
	private String[] buffer;
	private int top;
	
	public MyStack(int size) {
		buffer = new String[size];
		top = -1;
	}
	
	public void push(String s) {
		if(top == buffer.length-1) {
			String[] newBuffer = new String[buffer.length*2];
			for (int i = 0; i < buffer.length; i++) {
				newBuffer[i] = buffer[i];
			}
			buffer = newBuffer;
		}

		buffer[++top] = s;
	}
	
	public String pop() throws MyStackException {
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