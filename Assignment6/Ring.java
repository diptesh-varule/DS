import java.util.Scanner;

public class Ring {

	int n, inactive_count;
	int coordinator;
	boolean[] state;
	
	public Ring(int n) {
		this.n = n;
		this.inactive_count = 0;
		this.state = new boolean[n];
		//	State all processes as active		
		for(int i = 0; i < n; i++) {
			this.state[i] = true;
		}
		this.coordinator = n - 1;
		System.out.println("Process " + n + " is set as initial coordinator");
	}
	
	public void deactivate_process(int id) {

		 if(!state[id - 1]) {
		 	System.out.println("Process already inactive");
		 } else {
		 	state[id - 1] = false;
		 	System.out.println("Process " + id + " deactivated");
		 	inactive_count += 1;
		 }
	}
	
	public void view_ring() {
	
		 if(this.inactive_count == n) {
		 	System.out.println("All members inactive...");
		 	return;
		 }
		 System.out.println("Active Ring members");
		 for(int i = 0; i < n; i++) {
		 	if(state[i]) System.out.println((i + 1) + " ");
		 }
	}
	
	public void election(int id) {
		
		 if(this.inactive_count == this.n) {
		 	System.out.println("All members inactive...");
		 	System.out.println("Aborting election process...");
			this.coordinator = -1;
		 	return;
		 }
		 id = id - 1;		 
		 int current_coordinator = id;	 
		 int token = (id + 1) % n;
		 System.out.println("\nElection initiator : " + (id + 1));
		 
		 //	Election algorithm
		 while(token != id) {
			System.out.println("Token at process " + (token + 1));
			if(this.state[token]) {
				if(token > current_coordinator) {
					current_coordinator = token;
				}
			}
			token = (token + 1) % this.n;
		 }
		 System.out.println("Elected coordinator : " + (current_coordinator + 1));
		 this.coordinator = current_coordinator;
	}

	public void ping_coordinator(int id) {
		if(!this.state[id - 1]) {
			System.out.println("Process inactive...");
			System.out.println("Aborting...");
			return;
		}
		if(id == coordinator) {
			if(this.state[id - 1]) {
				System.out.println("Coordinator active");
			} else {
				System.out.println("Coordinator inactive!\nInitiate election from other process");
			}
		}
		System.out.println("Sending message from process " + id + " to " + (this.coordinator + 1));
		if(!this.state[this.coordinator]) {
			System.out.println("Coordinator process not responding");
			System.out.println("Conducting election...");
			this.election(id);
		} else {
			System.out.println("Coordinator alive");
		}
	}

	public void setCoordinator(int c) {
		this.coordinator = c;
	}

	public static void main(String[] args) {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes: ");
		int n = sc.nextInt();
		Ring ring = new Ring(n);
		
		while(choice < 5) {
			System.out.println("***********Menu***********");
			System.out.println("1. Deactivate a process");
			System.out.println("2. Ping coordinator");
			System.out.println("3. View Ring");
			System.out.println("4. Election");
			System.out.println("5. Exit");
			System.out.println("**************************");
			System.out.println("Enter Choice : ");
			choice = sc.nextInt();
			switch(choice) {
				case 1 : {
					int id;
					System.out.println("Enter process ID : ");
					id = sc.nextInt();
					ring.deactivate_process(id);
					System.out.println("");
					break;
				}
				case 2 : {
					int id;
					System.out.println("Enter process ID for sender");
					id = sc.nextInt();
					ring.ping_coordinator(id);
					System.out.println("");
					break;
				}
				case 3 : {
					ring.view_ring();
					System.out.println("");
					break;
				}
				case 4 : {
					int id;
					System.out.println("Enter process ID for election initiator");
					id = sc.nextInt();
					ring.election(id);
					System.out.println("");
					break;
				}
				case 5 :
				default : {
					System.out.println("");
					break;
				}
			}
		
		}			
		System.out.println("Program terminated..");	
		sc.close();
	}
}
