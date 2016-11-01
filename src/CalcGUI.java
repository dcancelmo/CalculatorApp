/*
 *Daniel Cancelmo
 *Calculator Application
 *I did not collaborate with anyone on this
 *
 *This file constructs a GUI that accepts button and key inputs and passes expressions to ShuntYard.java to calculate and return
 *
 *Known issues:
 *JTextField does not take up a full row at top.
 *Cannot handle negative number inputs, throws Error. Does allow negative output though
 *Keyboard input appears twice momentarily
 *
 *Features to be added: 
 *Ans button to recall whatever the last answer was so it can be used as more than just the first value
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalcGUI extends JFrame implements ActionListener, KeyListener {
	
	protected JButton one, two, three, four, five, six, seven, eight, nine, zero, decimal, plus, minus, multiply, divide, modulo, exponent, not, openPar, closePar, logAnd, logOr, lessThan, greaterThan, logEqual, equalSign, clear, nullButton;
	String expression = "";
	protected JTextField user;

	
	public CalcGUI() {
		setTitle("Calculator");
		setSize(433, 243);
		setLayout(new GridLayout(4,7));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		user = new JTextField(expression, 7);
		user.addActionListener(this);
		user.addKeyListener(this);
		add(user);
		
		one = new JButton("1");
		one.addActionListener(this);
		add(one);
		
		two = new JButton("2");
		two.addActionListener(this);
		add(two);
		
		three = new JButton("3");
		three.addActionListener(this);
		add(three);
		
		plus = new JButton("+");
		plus.addActionListener(this);
		add(plus);
		
		openPar = new JButton("(");
		openPar.addActionListener(this);
		add(openPar);
		
		logAnd = new JButton("&");
		logAnd.addActionListener(this);
		add(logAnd);
		
		lessThan = new JButton("<");
		lessThan.addActionListener(this);
		add(lessThan);
		
		four = new JButton("4");
		four.addActionListener(this);
		add(four);
		
		five = new JButton("5");
		five.addActionListener(this);
		add(five);
		
		six = new JButton("6");
		six.addActionListener(this);
		add(six);
		
		minus = new JButton("-");
		minus.addActionListener(this);
		add(minus);
		
		closePar = new JButton(")");
		closePar.addActionListener(this);
		add(closePar);
		
		logOr = new JButton("|");
		logOr.addActionListener(this);
		add(logOr);
		
		greaterThan = new JButton(">");
		greaterThan.addActionListener(this);
		add(greaterThan);
		
		seven = new JButton("7");
		seven.addActionListener(this);
		add(seven);
		
		eight = new JButton("8");
		eight.addActionListener(this);
		add(eight);
		
		nine = new JButton("9");
		nine.addActionListener(this);
		add(nine);
		
		multiply = new JButton("*");
		multiply.addActionListener(this);
		add(multiply);
		
		exponent = new JButton("^");
		exponent.addActionListener(this);
		add(exponent);
		
		not = new JButton("!");
		not.addActionListener(this);
		add(not);
		
		logEqual = new JButton("=");
		logEqual.addActionListener(this);
		add(logEqual);
		
		zero = new JButton("0");
		zero.addActionListener(this);
		add(zero);
		
		decimal = new JButton(".");
		decimal.addActionListener(this);
		add(decimal);
		
		equalSign = new JButton("Return");
		equalSign.addActionListener(this);
		add(equalSign);
		
		divide = new JButton("/");
		divide.addActionListener(this);
		add(divide);
		
		modulo = new JButton("%");
		modulo.addActionListener(this);
		add(modulo);
		
		clear = new JButton("Clear");
		clear.addActionListener(this);
		add(clear);
		
		nullButton = new JButton("");
		nullButton.addActionListener(this);
		add(nullButton);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String source = "";
		try {
			source = ((AbstractButton) e.getSource()).getText();
		} catch (ClassCastException e1) {
			return;
		}
		System.out.println(source);
		//If previous calculation was an error, clears it automatically upon trying to do a new expression.
		if (expression.equals("Error")) {
			expression = "";
			user.setText(expression);
		}
		//Calculates whatever is in the expression
		if (source.equals("Return")) {
			expression = ShuntYard.shunt(expression);
			user.setText(expression);
			return;
		}
		//Clears the expression
		if (source.equals("Clear")) {
			expression = "";
			user.setText(expression);
			return;
		}
		//Adds button values onto expression (except for 'Return' and 'Clear')
		expression += source;
		user.setText(expression);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		//Ensures only valid keys are accepted, along with delete, enter, and backspace
		String validString = "1234567890.+-*/=%()^&|!<>";
		Character source = e.getKeyChar();
		System.out.println(source);
		if (validString.contains(source.toString()) || source == KeyEvent.VK_DELETE || source == KeyEvent.VK_ENTER || source == KeyEvent.VK_BACK_SPACE) {
			//Functions as if 'Enter' was pressed on GUI
			if (source == KeyEvent.VK_ENTER) {
				System.out.println("Return");
				expression = ShuntYard.shunt(expression);
			//Functions as if 'Clear' was pressed on GUI
			} else if (source == KeyEvent.VK_DELETE || source == KeyEvent.VK_BACK_SPACE) {
				System.out.println("Clear");
				expression = "";
			//Puts the key value onto the expression
			} else {
				expression += source.toString();
				user.setText(expression);
			}
		} 
		//Displays new expression
		user.setText(expression);
	}
	




	@Override
	public void keyPressed(KeyEvent e) {
		//Ignore
	}


	@Override
	public void keyReleased(KeyEvent e) {
		//Prevents invalid key input (ie letters) from staying in the JTextField
		user.setText(expression);
	}
	
	
	public static void main(String[] args) {
		new CalcGUI().setVisible(true);
	}
	
}
