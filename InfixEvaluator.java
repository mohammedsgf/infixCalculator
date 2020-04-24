import java.util.Scanner;
/**
 * 
 * This program accept ant correct infix expression and convert and evaluate that expression.
 * 
 * @author Mohammed A. Alsaggaf
 * @since 4-3-2020
 * @version 1.0 
 *
 */
public class InfixEvaluator {
	
	private static ArrayStack<String> opStack=new ArrayStack<String>(); //Operations Stack
	private static ArrayStack<Double> evaluator=new ArrayStack<Double>(); //Evaluation Stack
	private static StringBuilder postfixExp=new StringBuilder(); //Postfix expression container
	private static int rightprnth; //Right parenthesis flag
	private static Character chrs; //Expression tokens container
	
	public static void main(String[] args) {
		
		
		Scanner input=new Scanner(System.in); //Scanner object to read from the consol
		System.out.print("Write the infix expression : ");
		String inExp=input.nextLine().replaceAll(" ",""); //Read the full expression and remove all the spaces
		input.close(); //Close the Scanner
		
		boolean neg=true; //Negative values flag
		
		String digits="";//Digits container 
		
		for (int i = 0; i < inExp.length(); i++) { //Start to iterate the expression
             chrs = inExp.charAt(i); //Find the i character on the expression
            
            if (Character.isDigit(chrs) || chrs=='.') { //if char is digit or a dot
               
            	digits = digits+chrs; //add the char to the digit
                neg=false; //the negative signals after the digits will be operators,
                //so don't deal with them as a sign
                
                 if(i==inExp.length()-1) { //if i is in the last index 
                	 
                	evaluator.push(Double.parseDouble(digits)); //push the latest value of the digit
                	postfixExp.append(digits);//Build the postfix expression
                	digits=""; //reset the digits container
                	
                	while(!opStack.isEmpty()) { //while the operations stack is not empty
                		postfixEvaluator(opStack.peek()); //peek the operation stack on the prefix evaluator; 
                		//Internally it will pop also
                	}	
                }
            }
            else if(!Character.isDigit(chrs)){ //if the char is not a digit
            	
            	if(!digits.isEmpty() && !digits.equals("-")) { // if digits not empty and the char is not negative sign
            		
            	evaluator.push(Double.parseDouble(digits));//push the digit
            	postfixExp.append(digits);//Build the postfix expression
            	digits=""; //clear the digit
            	
            	}
            	
            	
            	if(chrs.equals('(')) { //if the char is "("
            		
            		rightprnth++; //increase the right parenthesis
            		neg=true; //make the negative flag true; 
            		//any negative sign directly after the right parenthesis will not be considered as an operator
            	}
            	else if(chrs.equals(')')) { // else if the char is ")"
            		
            		neg=false; //make the negative flag flase; 
            		//any negative sign directly after the left parenthesis will be considered as an operator
            		postfixEvaluator(opStack.peek()); //Do the evaluation for what inside the parenthesis
            		rightprnth--; //decrease the parenthesis
            		
            		if(i==inExp.length()-1) { //if i is in the last index 
                    	
            			while(!opStack.isEmpty()) { //while the operations stack is not empty
                    		postfixEvaluator(opStack.peek()); //peek the operation stack on the prefix evaluator; 
                    		//Internally it will pop also
                    	}	
                    }
            	}//end the else if
            	else if(chrs.equals('-') && neg) { //else if the char is "-" and the negative flag is true
            		 digits = chrs+digits; //append the negative sign to the digit
            	}
            	else if(opStack.isEmpty()) { //if the operation stack is empty
            		neg=true; //
            		opStack.push(""+chrs);// Must add more details about the input
            	}
            	else {
            		neg=true;//make the negative flag true; 
            		//any negative sign directly after any operation will be considered as a negative sign
            		int newOP=decodeOp(""+chrs); //decode the new char to have an integer value
            		int oldOP=decodeOp(opStack.peek()); //decode the old char to have an integer value
            		
            		if(newOP>oldOP) { // the new operation is grater than the oldest
            			opStack.push(""+chrs); //push the new operator
            		}
            		else {
            			if(rightprnth==0) { //if the right parentheses is zero, means we are outside the parentheses 
            				postfixEvaluator(opStack.peek()); //evaluate the expression
            			}
            			opStack.push(""+chrs); //push the operation 
            		}
            	}
            }  
        }
		
		
		//printing
		System.out.println("Infix Expression :"+inExp+" Result :"+evaluator.pop()+" Postfix Expression:"+postfixExp);
		
	}
	
	/**
	 * 
	 * decodeOP method is to give precedes values to the operations so it can be compared easily.
	 * 
	 * @param String op, the operation need to performed
	 * @return int vlaue of the opration
	 * @author Mohammed A. Alsaggaf
	 * @since 4-3-2020
	 * @version 1.0 
	 * 
	 */
	private static int  decodeOp(String op) {

		if(op.equals("^")) {
			return 3;
		}
		else if(op.equals("*")||op.equals("/")) {
			return 2;
		}
		else if(op.equals("+")||op.equals("-")) {
			return 1;
		}
		else
			return 0;
	}
	
	/**
	 * 
	 * evaluate method will evaluate the values and push in the evaluator stack
	 * 
	 * @param String op, the operation need to performed
	 * @param double First, the first value poped from the stack
	 * @param double second, the second value poped from the stack
	 * @author Mohammed A. Alsaggaf
	 * @since 4-3-2020
	 * @version 1.0 
	 * 
	 */
	private static void evaluate(double first,double second,String op) {
		if(op.equals("^")) {
			
			double calc=Math.pow(second,first);
			evaluator.push(calc);
			
		}
		else if(op.equals("*")) {
			
			double calc=second*first;
			evaluator.push(calc);
		}
		else if(op.equals("/")) {
			
			double calc=second/first;
			evaluator.push(calc);
		}
		else if(op.equals("+")) {
			
			double calc=first+second;
			evaluator.push(calc);
		}
		else if(op.equals("-")) {
			
			double calc=second-first;
			evaluator.push(calc);
		}
		
	}
	
	/**
	 * 
	 * postfixEvaluator method will pop the values and insert it into the evaluate method.
	 * 
	 * @param String op, the operation need to performed
	 * @author Mohammed A. Alsaggaf
	 * @since 4-3-2020
	 * @version 1.0 
	 * 
	 */
	private static void postfixEvaluator(String op) {
		
		postfixExp.append(opStack.pop());//append the operation into the postfix expression
		double firstValue=evaluator.pop();
		double secondValue=evaluator.pop();
		evaluate(firstValue,secondValue,op);
			
			}

}
