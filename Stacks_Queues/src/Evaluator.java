// 
//   Evaluator can evaluate the postfix expression
//	 Vyacheslav Gerashenko

import java.util.*;

public class Evaluator {
    private String postfix;
    public Evaluator(String postfix) {
        this.postfix = postfix;
    }

    public double evaluate() {
    	Stack <Double> s = new Stack<Double>();
		for(String t : postfix.split(" ")) {
			if (t.equals("+")) {s.push(calculate("+", (Double)s.pop(), (Double)s.pop()));}
			else if (t.equals("-")) {
				double a=(Double)s.pop();
				double b=(Double)s.pop();
				s.push(calculate("-", b, a));
			}
			else if (t.equals("*")) {s.push(calculate("*", (Double)s.pop(), (Double)s.pop()));}
			else if (t.equals("/")) {double a=(Double)s.pop();
				double b=(Double)s.pop();
				s.push(calculate("/", b, a));
			}
			else {s.push(Double.parseDouble(t));}
		}
		return s.pop();
    }

    private double calculate(String operator, double op1, double op2) {
        if (operator.equals("+")) {
            return op1 + op2;
        } else if (operator.equals("-")) {
            return op1 - op2;
        } else if (operator.equals("*")) {
            return op1 * op2;
        } else if (operator.equals("/")) {
            return op1 / op2;
        } else {
            // impossible
            return -9999.0;
        }
    }
}
