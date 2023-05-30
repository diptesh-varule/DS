import java.util.Scanner;

    public class Bully{

        static boolean state[]=new boolean[5];
     

        public static void down(int down){
            if(state[down-1]==false){
                System.out.println("Process "+down +" is already down");
            }
            else{
                state[down-1]=false;
            }
        }

        public static void up(int up){
            if(state[up-1]==true){
                System.out.println("Process "+up +" is already up");
            }
            else{
                state[up-1]=true;
                System.out.println("Process "+up+" helds and election");

                for(int i=up;i<5;i++){
                    System.out.println("Election message sent from "+up+" to process"+(i+1));
                }
                for(int i=up+1;i<5;i++){
                    if(state[i]==true){
                        System.out.println("Alive message sent from "+(i+1)+" to process"+up);
                        break;
                    }
                }
            }

        }


        public static void mess(int mess){
            if(state[mess-1]==false){
                System.out.println("Process "+mess+" is down itself.");
            }
            else{
                if(state[4]==true){
                    System.out.println("OK");
                }
                else{
                    System.out.println("Process "+mess+" holds an election");
                    for(int i=mess;i<5;i++){
                        System.out.println("Election message send from "+mess+" to "+(i+1));
                    }

                    for(int i=5;i>=mess;i--)
                    {
                        if(state[i-1]==true){
                            System.out.println("Coordinator : "+i+" is sending message to all");
                            break;
                        }
                    }                        
                }
            }

        }



        public static void main(String args[]){
            for(int i=0;i<5;i++){
                state[i]=true;
            }
            Scanner sc=new Scanner(System.in);
            System.out.println("Active processes are : 1 2 3 4 5");
            System.out.println("Process 5 is the coordinator.");
            int choice,process;
            
            do{ 

                System.out.println("Choose from the following");
                System.out.println("1. Bring up a process ...");
                System.out.println("2. Bring down a process ...");
                System.out.println("3. Send message ...");
                System.out.println("4. Exit ...");
                choice=sc.nextInt();

                switch(choice){
                    case 1: 
                            System.out.println("Enter the process number : ");
                            process=sc.nextInt();
                            up(process);
                            break;
                    case 2:
                            System.out.println("Enter the process number : ");
                            process=sc.nextInt();
                            down(process);
                            break;
                    case 3:
                            System.out.println("Enter the process number : ");
                            process=sc.nextInt();
                            mess(process);
                            break;

                    case 4:
                            System.exit(0);
                    default:
                            System.out.println("Enter a valid choice."); 
                }
                


            }while(choice!=4);



        }


    }

