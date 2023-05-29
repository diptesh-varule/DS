import java.util.Scanner;

public class TokenRing{
	public static void main(String args[]) throws Throwable{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of processes : ");
		int n=sc.nextInt();
		
		int token=0;
		int sender,receiver;
		String message;
		
		System.out.println("Initializing the Ring ...");

		for(int i=0;i<n;i++){
			System.out.println(i+" ");		
		}
		
		System.out.println("Enter sender : ");
		sender=sc.nextInt();
		
		System.out.println("Enter receiver : ");
		receiver=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter message : ");
		message=sc.nextLine();
		

		System.out.println("Token Circulation Started...");
		
		for(int i=token;i!=sender;i++){
			System.out.println("Token at process "+i);
		}		


		System.out.println("Sender : "+sender+" sending the message "+message);

		for(int i=sender+1;i!=receiver;i=(i+1)%n){
			System.out.println("Message forwarded by process "+i);
		}
		System.out.println("Message received by the receiver "+receiver+" is  : "+message);
		token=sender;
}

		
}

