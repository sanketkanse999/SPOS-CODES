import java.util.*;
public class fcfs {

	public static void main(String[] args) {
		
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of processes :");
		int n= sc.nextInt();


		int pid[]=new int[n]; 
		int at[]=new int[n]; 
		int bt[]=new int[n]; 
		int ct[]=new int[n]; 
		int tat[]=new int[n]; 
		int wt[]=new int[n]; 
		float atat = 0;
		float awt = 0;

		for(int i=0;i<n;i++)
		{
			System.out.println("Enter the process id");
			pid[i]=sc.nextInt();
			
			System.out.println("Enter the arrival time");
			at[i]=sc.nextInt();
			
			System.out.println("Enter the bus time");
			bt[i]=sc.nextInt();

		}
		for (int i = 0;i<n;i++) {
		    for (int j=0;j<n-1;j++) {
		        if (at[j]>at[j+1]) {
		            
		            int temp=at[j];
		            at[j]=at[j+1];
		            at[j+1]=temp;
		            
		           
		            int btemp=bt[j];
		            bt[j]=bt[j+1];
		            bt[j+1]=btemp;
		            
		            
		            int ptemp=pid[j];
		            pid[j]=pid[j+1];
		            pid[j+1]=ptemp;
		        }
		    }
		}

		for(int i=0;i<n;i++) 
			{
			
				if(i==0)
				{
				ct[i]=bt[i]+at[i];
				}
				else
				{
					if(at[i]>ct[i-1])
					{
					ct[i]=bt[i]+at[i];
					}
				else
				{
					ct[i]=ct[i-1]+bt[i];
				}
			
				}
			}	
			     
		     for (int i=0;i<n;i++)
		     {
		         tat[i]=ct[i]-at[i];
		         wt[i]=tat[i]-bt[i];
		         atat = atat + tat[i];
		         awt = awt + wt[i];
		     }

		     System.out.println("pid \t at \t bt \t ct \t tat \t wt ");
		     for (int i=0;i<n;i++) 
		     {
		         System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
		     }
		     
		     System.out.println("Average TAT and WT are: ");
		     System.out.println("ATAT="+atat/n +"\t"+ "AWT"+awt/n);

		 }

    
    public String toString() {
        return "fcfs []";
    }
		  

		}
