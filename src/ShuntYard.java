/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 *
 *This file computes the expression passed in from the GUI class
 */

import queue.MyQueue;
import stack.MyStack;

public class ShuntYard {
	
	//Shunting-Yard Algorithm
	public static String shunt(String expression) {
		
		MyStack stack = new MyStack();
		MyQueue queue = new MyQueue();
		//Holds numbers temporarily
		String holder = "";
		String data = expression;
		data = data.replace(" ", "");
		String numString = "1234567890.";
		
		//Loops through the expression one character at a time
		for (int i = 0; i < data.length(); i++) {
			Character current = data.charAt(i);
			//( is pushed onto stack, ) causes stack to be popped and enqueued
			if (current.equals('(')) stack.push(current + "");
			else if (current.equals(')')) {
				if(!holder.equals("")){
					queue.enqueue(holder);
					holder = "";
				}
				while(!stack.peek().equals("(")) queue.enqueue(stack.pop());
				stack.pop();
				
			//Adds numbers and decimal point
			} else if (numString.contains(current.toString())) holder = holder + current;
			//If stack empty, push operator
			else if (stack.isEmpty()) {
				//Numbers enqueued
				if (!holder.equals("")){
					queue.enqueue(holder);
					holder = "";
				}
				stack.push(current + "");
			}
			//For operators
			else {
				//Numbers enqueued
				if(!holder.equals("")){
					queue.enqueue(holder);
					holder = "";
				}
				//Precedence calculated of current operator and next operator
				int prec1 = precedence(current.toString());
				int prec2 = precedence(stack.peek().toString());
				if (prec1 > prec2){
					stack.push(current + "");
				} 
				else if(prec2 > prec1 || prec1 == prec2){
					while (!stack.isEmpty()) {
						if(stack.peek().equals("(")) break;
						queue.enqueue(stack.pop());
					}
					stack.push(current + "");
				}
			}
			
		}
		//Numbers enqueued
		if(!holder.equals("")){
			queue.enqueue(holder);
			holder = "";
		}
		//Enqueues whatever is left on stack
		while(!stack.isEmpty()) queue.enqueue(stack.pop());
		try {
			//Calculates the postfix expressions and returns value to be printed and written to file
			String evalString = Double.toString(postFix(queue));
			System.out.print(evalString + "\n");
			return evalString;
		//Catch and safely handles invalid arithmetic or improper input by user
		} catch (ArithmeticException | NullPointerException | NumberFormatException e) {
			System.out.println("Invalid arithmetic. Cannot compute value.");
			return "Error";
		}
	}
	
	//Postfix calculator
	public static double postFix(MyQueue postQueue) {
		MyStack newStack = new MyStack();
		String dequeue = (String) postQueue.peek();
		postQueue.dequeue();
		double c = Double.parseDouble(dequeue);
		newStack.push(c);
		
		//Loops while the queue has data. Calculates the appropriate value and pushes onto the stack.
		while (postQueue.isEmpty()==false){
			if (postQueue.peek().equals("+")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				newStack.push(a + b);
			} else if (postQueue.peek().equals("-")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				newStack.push(b - a);
			} else if (postQueue.peek().equals("*")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				newStack.push(a * b);
			} else if (postQueue.peek().equals("/")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				//Handles dividing by zero
				if ((b / a) == Double.POSITIVE_INFINITY) throw new ArithmeticException();
				newStack.push(b / a);
			} else if (postQueue.peek().equals("%")) {
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				//Handles dividing by zero
				if ((b % a) == Double.POSITIVE_INFINITY) throw new ArithmeticException();
				newStack.push(b % a);
			} else if (postQueue.peek().equals("^")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				newStack.push(Math.pow(b, a));
			} else if (postQueue.peek().equals("!")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				if (a==0) newStack.push(1.0);
				else newStack.push(0.0);
			} else if (postQueue.peek().equals(">")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				if (b > a) newStack.push(1.0);
				else newStack.push(0.0);
			} else if (postQueue.peek().equals("<")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				if (b > a) newStack.push(0.0);
				else newStack.push(1.0);
			} else if (postQueue.peek().equals("=")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				if (b == a) newStack.push(1.0);
				else newStack.push(0.0);
			} else if (postQueue.peek().equals("&")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				if(b == 1.0 && a == 1.0) newStack.push(1.0);
				else newStack.push(0.0);
			} else if (postQueue.peek().equals("|")){
				postQueue.dequeue();
				double a = (double) newStack.pop();
				double b = (double) newStack.pop();
				if(b==1.0||a==1.0) newStack.push(1.0);
				else newStack.push(0.0);
			} else{
				//Pushes final value onto stack
				newStack.push(Double.parseDouble((String) postQueue.peek()));
				postQueue.dequeue();
			}
		}
		//Returns final value to shunt method to be printed
		return (double) newStack.pop();
	}
	
	//Switch-case statement to assign the precedence value for each valid operator and returns it
	public static int precedence(String obj) {
		int prec = 0;
		if (obj == null) return 0;
		switch (obj) {
			case "+": prec = 4;
				break;
			case "-": prec = 4;
				break;
			case "*": prec = 5;
				break;
			case "/": prec = 5;
				break;
			case "%": prec = 5;
				break;
			case "^": prec = 6;
				break;
			case "&": prec = 1;
				break;
			case "|": prec = 0;
				break;
			case "!": prec = 7;
				break;
			case "<": prec = 3;
				break;
			case ">": prec = 3;
				break;
			case "=": prec = 2;
				break;
		}
		return prec;
	}
	
}
	

