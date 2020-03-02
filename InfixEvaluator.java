import java.util.Scanner;

public class InfixEvaluator {

	public static void main(String[] args) {
		ArrayStack<String> opStack=new ArrayStack<String>();
		ArrayStack<Double> evaluter=new ArrayStack<Double>();
		
		Scanner input=new Scanner(System.in);
		System.out.print("Write Your expression : ");
		String inExp=input.nextLine();
		
		StringBuilder postfixExp=new StringBuilder();
		Scanner read=new Scanner(inExp);
		
		while(read.hasNext()) {
			String tempToken=read.next();
			System.out.println(tempToken);
		}
		
		
	}

}
