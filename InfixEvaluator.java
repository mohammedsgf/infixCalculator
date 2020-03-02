import java.util.Scanner;

public class InfixEvaluator {

	public static void main(String[] args) {
		ArrayStack<String> opStack=new ArrayStack<String>();
		ArrayStack<Double> evaluter=new ArrayStack<Double>();
		
		Scanner input=new Scanner(System.in);
		System.out.print("Write Your expression : ");
		String inExp=input.nextLine().replaceAll(" ","");
		
		StringBuilder postfixExp=new StringBuilder();
		//Scanner read=new Scanner(inExp);
		
		String digits="";
		for (int i = 0; i < inExp.length(); i++) {
            char chrs = inExp.charAt(i);     
            
            if (Character.isDigit(chrs)||chrs=='.') {//if char is digit or a dot
                digits = digits+chrs; //add the char to the digit
                 if(i==inExp.length()-1) { //if i is in the last index 
                	evaluter.push(Double.parseDouble(digits)); //push the leatest value of the digit
                	digits="";
                }
            }
            else if(!Character.isDigit(chrs)){ //if the char is not a digit
            	evaluter.push(Double.parseDouble(digits));//push the digit
            	digits=""; //clear the digit
            	opStack.push(""+chrs);// Must add more details about the input
            }
            
            
        }
		
		
		
		
		int size=evaluter.size();;
		for(int i=0;i<size;i++) {
		System.out.println("Pop " +evaluter.pop());
		}
	}

}
