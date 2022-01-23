import java.util.ArrayList;


public class Stack211 <T>{
	public int stackTop;
	//static char[] myStack=new char[40];
	public ArrayList<T> myStack=new ArrayList<T>();
	
	Stack211() {
		stackTop=-1;
	}
	
	public void push(T c) {
		stackTop ++;
		myStack.add(c);
	}
	
	public T pop() {
		T a = myStack.get(stackTop);
		myStack.remove(stackTop);
		stackTop --;
		return a;
	}
	
	public boolean isEmpty() {
		if(stackTop<=-1) {
			return true;
		}else {
		return false;
		}
	}
	
	public T lastChar() {
		T a = myStack.get(stackTop);
		return a;
	}
	
	public int preference(char a) {
		if (a =='*' || a== '/') {return 1;}
		else {return 0;}
	}
}