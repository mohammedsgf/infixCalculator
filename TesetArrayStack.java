
public class TesetArrayStack {

	public static void main(String[] args) {

		ArrayStack<Integer> test=new ArrayStack(); 
		
		// test pushing
		System.out.println("Pushing from 0 to 9");
		for(int i=0;i<10;i++) {
			test.push(i);
			System.out.println(i+" pushed");
		}
		System.out.println("Finish Pushing........");
		
		//test size and isEmpty
		System.out.println("Stack size is "+ test.size());
		System.out.println("is stack empty ?  "+ test.isEmpty());
		
		// test pushing and peek
		System.out.println("Poping from 0 to 9");
		int size=test.size();
		for(int i=0;i<size;i++) {
			System.out.println("What is the peek ? "+test.peek());
			System.out.println(test.pop()+" Poped");
		}
		System.out.println("Finish Poping........");
		
		System.out.println("is stack empty ?  "+ test.isEmpty());
		
		//test clear
		System.out.println("Test Clear.....");
		test.push(1);
		test.push(2);
		System.out.println("1 and 2 was pushed....");

		System.out.println("is empty ? " +test.isEmpty());
		test.clear();
		System.out.println("Stack cleared....");
		System.out.println("is empty ? " +test.isEmpty());
	}

}
