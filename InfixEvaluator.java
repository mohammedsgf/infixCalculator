import java.util.Scanner;

public class InfixEvaluator {
	private static ArrayStack<String> opStack=new ArrayStack<String>();
	private static ArrayStack<Double> evaluter=new ArrayStack<Double>();
	private static StringBuilder postfixExp=new StringBuilder();
	
	private static char chrs;
	public static void main(String[] args) {
		
		
		Scanner input=new Scanner(System.in);
		System.out.print("Write Your expression : ");
		String inExp=input.nextLine().replaceAll(" ","");
		
		
		//Scanner read=new Scanner(inExp);
		 //double total;
		String digits="";
		for (int i = 0; i < inExp.length(); i++) {
             chrs = inExp.charAt(i);     
            
            if (Character.isDigit(chrs)||chrs=='.') {//if char is digit or a dot
                digits = digits+chrs; //add the char to the digit
                 if(i==inExp.length()-1) { //if i is in the last index 
                	evaluter.push(Double.parseDouble(digits)); //push the latest value of the digit
                	postfixExp.append(digits);//Build the postfix expression
                	digits="";
                	while(!opStack.isEmpty()) {
                		
                		int oldOP=decodeOp(opStack.peek());
                		
                			if(oldOP==3) {
                				postfixExp.append(opStack.pop());
                				double firstValue=evaluter.pop();
                				double secondValue=evaluter.pop();
                				evaluate(firstValue,secondValue,"^");
                				
                			}
                			else if(oldOP==2)
                				if(opStack.peek().equals("*")) {
                					postfixExp.append(opStack.pop());
                    				double firstValue=evaluter.pop();
                    				double secondValue=evaluter.pop();
                    				evaluate(firstValue,secondValue,"*");
                    				
                				}
                				else {
                					postfixExp.append(opStack.pop());
                    				double firstValue=evaluter.pop();
                    				double secondValue=evaluter.pop();
                    				evaluate(firstValue,secondValue,"/");
                    				
                				}
                			else if(oldOP==1) {
                				if(opStack.peek().equals("+")) {
                					postfixExp.append(opStack.pop());
                    				double firstValue=evaluter.pop();
                    				double secondValue=evaluter.pop();
                    				evaluate(firstValue,secondValue,"+");
                    				
                				}
                				else {
                					postfixExp.append(opStack.pop());
                    				double firstValue=evaluter.pop();
                    				double secondValue=evaluter.pop();
                    				evaluate(firstValue,secondValue,"-");
                    				
                				}
                			}
                		}
                		
                	}
                }
            
            else if(!Character.isDigit(chrs)){ //if the char is not a digit
            	evaluter.push(Double.parseDouble(digits));//push the digit
            	postfixExp.append(digits);//Build the postfix expression
            	digits=""; //clear the digit
           //----------------------------------------------------------
            	
            	if(opStack.isEmpty()) {
            	opStack.push(""+chrs);// Must add more details about the input
            	}
            	else {
            		int newOP=decodeOp(""+chrs);
            		int oldOP=decodeOp(opStack.peek());
            		if(newOP>oldOP) {
            			opStack.push(""+chrs);
            			postfixExp.append(chrs);
            		}
            		else {
            			if(oldOP==3) {
            				postfixExp.append(opStack.pop());
            				double firstValue=evaluter.pop();
            				double secondValue=evaluter.pop();
            				evaluate(firstValue,secondValue,"^");
            				opStack.push(""+chrs);
            			}
            			else if(oldOP==2)
            				if(opStack.peek().equals("*")) {
            					postfixExp.append(opStack.pop());
                				double firstValue=evaluter.pop();
                				double secondValue=evaluter.pop();
                				evaluate(firstValue,secondValue,"*");
                				opStack.push(""+chrs);
            				}
            				else {
            					postfixExp.append(opStack.pop());
                				double firstValue=evaluter.pop();
                				double secondValue=evaluter.pop();
                				evaluate(firstValue,secondValue,"/");
                				opStack.push(""+chrs);
            				}
            			else if(oldOP==1) {
            				if(opStack.peek().equals("+")) {
            					postfixExp.append(opStack.pop());
                				double firstValue=evaluter.pop();
                				double secondValue=evaluter.pop();
                				evaluate(firstValue,secondValue,"+");
                				opStack.push(""+chrs);
            				}
            				else {
            					postfixExp.append(opStack.pop());
                				double firstValue=evaluter.pop();
                				double secondValue=evaluter.pop();
                				evaluate(firstValue,secondValue,"-");
                				opStack.push(""+chrs);
            				}
            			}
            		}
            	}
            }
            
            
        }
		
		
		
		System.out.println(postfixExp);
		int size=evaluter.size();;
		for(int i=0;i<size;i++) {
		System.out.println("Pop " +evaluter.pop());
		}
	}
	public static int  decodeOp(String op) {
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
	
	public static void evaluate(double first,double second,String op) {
		if(op.equals("^")) {
			
			double calc=Math.pow(second,first);
			evaluter.push(calc);
			
		}
		else if(op.equals("*")) {
			
			double calc=second*first;
			evaluter.push(calc);
		}
		else if(op.equals("/")) {
			
			double calc=second/first;
			evaluter.push(calc);
		}
		else if(op.equals("+")) {
			
			double calc=first+second;
			evaluter.push(calc);
		}
		else if(op.equals("-")) {
			
			double calc=second-first;
			evaluter.push(calc);
		}
		
	}

}
