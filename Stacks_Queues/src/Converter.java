// 
//   Converter can convert from infix notation to postfix notation
//	 Vyacheslav Gerashenko
//   
import java.io.*;

import java.text.DecimalFormat;
import java.util.*;

public class Converter {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO: change path to match where your input file is located
        File f = new File("/Users/slava_g/eclipse/Stacks_Queues/src/expressions.txt");
        Scanner s = new Scanner(f);

        while(s.hasNextLine()) {
            Converter c = new Converter(s.nextLine());
            System.out.println("infix:   " + c.getInfix());
            System.out.println("postfix: " + c.getPostfix());

            Evaluator e = new Evaluator(c.getPostfix());
            DecimalFormat df2 = new DecimalFormat ("#.##");
			System.out.println("result: " + df2.format(e.evaluate()));
            System.out.println("---");
        }
    }

    String infix;
    public Converter(String infix) {
        this.infix = infix;
    }

    public String getInfix() {
        return infix;
    }

    public String getPostfix() {
    	Stack <String> stack = new Stack <>();
    	String postfix = "";
    	
        Tokenizer t = new Tokenizer(infix);
        
        while(t.hasNextToken()) {
        	String a = t.nextToken();
        	boolean number = false;
        	try {
        		double d = Double.parseDouble(a);
        		number = true;
        	} catch (NumberFormatException nfe){
        	}
        	if(number){
        		postfix = postfix + a + " ";
        	} else if(a.equals("(")) {
        		stack.push(a);
        	} else if(a.equals(")")) {
        		while(!stack.isEmpty() && !(stack.peek().equals("("))) {
        			postfix = postfix + stack.pop() + " ";
        		}
        		stack.pop();
        	} else {
        		while (!stack.isEmpty() && !(stack.peek().equals("(")) && precedence(a) <= precedence(stack.peek())) {
        			postfix = postfix + stack.pop() + " ";
        		}
        		stack.push(a);
        	}
        }
        while(!stack.isEmpty()) {
        	if(stack.peek().equals("("))
        		return "Invalid Expression";
        	postfix = postfix + stack.pop() + " ";
        }
        return postfix;
    }

	private int precedence(String a) {
		if(a == "*" || a == "/") {
			return 1;
		} else {
			return 0;
		}
	}
}
