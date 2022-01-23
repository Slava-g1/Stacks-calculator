


import java.util.HashMap;


public class Stack {
static HashMap<Integer,String> errorMessage = new HashMap<>(); //error messages
static HashMap<Character, Character> pair = new HashMap<>();    //)==(, }=={

	public static void main(String[] args) {
		String[] Statement = {"88/44", "14+3)", "4-9*{1+2})", "{1+2}*35)", "34+5)*5(3*6)", "32+4-3*5)", "1+3*52/22"};
		setErrorMessage();
		setPair();
		
		boolean[] error= check(Statement);
		
		
		for(int i=0; i<Statement.length; i++) {
			if(error[i]) {
				int a = i+1;
				System.out.println("Statement: " + a);
				System.out.println("Infix: " + Statement[i]);
				String postfix = toPostfix(Statement[i]);
				System.out.println("Postfix: " + postfix);
				int result = calc(postfix);
				System.out.println("Result: " + result);
				System.out.println();
			}
		}
}
		
		
				
	public static void setErrorMessage() {
		errorMessage.put(1, "Syntax Error: Incomplete Expression : Unpaired)");
		errorMessage.put(2, "Syntax Error: ')' expected");
		errorMessage.put(3, "Syntax Error: '}' expected");
		errorMessage.put(4, "Syntax Error: unnecessary parenthese");
	}
	
	public static void setPair() {
		pair.put('(', ')');
		pair.put('{', '}');
	}
	
	public static void printError(int location, int errorNo) {
		for (int i=0; i<location;i++) {
			System.out.print(" ");
		}
		System.out.println("^");
		System.out.println(errorMessage.get(errorNo));
		System.out.println();
		
	}
	
	public static String toPostfix(String infix){
	//converts an infix expression to postfix
		Stack211 <Character> st = new Stack211<Character>();
		char symbol;
		String postfix = "";
		
		//while there is input to be read
		for(int i=0;i<infix.length();++i) {
		
			symbol = infix.charAt(i);
			
			
			
			//if it's an operand, add it to the string
            if(Character.isDigit(symbol)) {postfix = postfix + symbol;}
            
            //postfix=postfix+" ";
            //push (
            else if(symbol=='(' || symbol=='{'){st.push(symbol);}
			
            //push everything back to (
			else if (symbol==')' || symbol=='}'){
				while (st.lastChar() != '(' && st.lastChar()!='{'){
					postfix=postfix+" ";
					postfix = postfix + st.pop();}
				
				st.pop(); //remove '('
			}
            
            //print operators occurring before it that have greater precedence
			else {
				while (!st.isEmpty() && !(st.lastChar()=='(') && !(st.lastChar()=='{')&& st.preference(symbol) <= st.preference(st.lastChar()))
					{postfix=postfix+" "; postfix = postfix+ st.pop();}
				postfix=postfix+" ";
				st.push(symbol);
				
			}
            
		}
		
		while (!st.isEmpty()) {postfix=postfix+" ";
			postfix = postfix + st.pop();}
		
		return postfix;
	}
	
	public static boolean[] check(String[] statement) {
		boolean[] error = new boolean [statement.length];
		boolean er=true;
		boolean value = true;
		for (int i=0; i<statement.length;i++) {
			Stack211 <Character> st = new Stack211<Character>();
			er=true;
			int a = i+1;
			for(int j=0; j<statement[i].length();j++) {
				char c = statement[i].charAt(j);
				
				switch(c) {
					case '(':
					case '{':
						st.push(c);
						break;
					case ')':
						value=st.isEmpty();
						if(value==true) {
							System.out.println("Statement: " + a);
							System.out.println(statement[i]);
							printError(j,4);
							er=false;
						}
						else {
							char popC=st.pop();
							if (popC!='(') {
								System.out.println("Statement: " + a);
								System.out.println(statement[i]);
								printError(j, 3);
								er=false;
							}
						}
						break;
					case '}':
						value=st.isEmpty();
						if (value==true) {
							System.out.println("Statement: " + a);
							System.out.println(statement[i]);
							printError(j,4);
							er=false;
						}else {
							char popC=st.pop();
							if (popC!='{') {
								System.out.println("Statement: " + a);
								System.out.println(statement[i]);
								printError(j,2);
								er=false;
							}
							
						}
						break;
				}
				
			}
			error[i]=er;
		}
		return error;
	}

	public static int calc(String input) {
		Stack211<Integer> s = new Stack211<Integer>();
		for(String t : input.split(" ")) {
			if (t.equals("+")) {s.push((Integer)s.pop() + (Integer)s.pop());}
			else if (t.equals("-")) {
				int a=(Integer)s.pop();
				int b=(Integer)s.pop();
				s.push(b-a);
			}
			else if (t.equals("*")) {s.push((Integer)s.pop() * (Integer)s.pop());}
			else if (t.equals("/")) {int a=(Integer)s.pop();
				int b=(Integer)s.pop();
				s.push(b/a);
			}
			else {s.push(Integer.parseInt(t));}
		}
		return s.pop();
	}


}

