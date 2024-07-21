import java.util.Scanner;
public class prioritypreempt{
	public static void main(String[] args) {
		
Scanner sc = new Scanner(System.in);
		
        System.out.println("enter no. of processes:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int bttt[] = new int[n];
        int prio[] = new int [n];
        float atat = 0;
		float awt = 0;

        for(int i = 0;i < n;i++)
        {
        	System.out.println("Enter the process id:");
        	pid[i] = sc.nextInt();
        	
        	System.out.println("Enter the Arrival time:");
        	at[i] = sc.nextInt();
        	
        	System.out.println("Enter the Burst time:");
        	bt[i] = sc.nextInt();
        	bttt[i] = bt[i];
        	
        	System.out.println("Enter the priority time");
        	prio[i]=sc.nextInt();
        }
       
        int F[] = new int[n];
        for(int i = 0; i < n;i++)
        {
        	F[i] = 0;
        }
        int st = 0;
        int total = 0;
       
        while(true)
        {
        	 int min = 99;
             int c = n;
             if(total == n)
            	 break;
             
        	for(int i = 0;i <n;i++)
        	{
        		if( at[i] <= st && F[i] == 0 && prio[i] < min)
        		{
        			min = prio[i];
        			c = i;
        		}
        	}
        
        	if(c == n)
        	{
        		st = st + 1;
        	}
        	else
        	{
        		bt[c]--;
        		st++;
        		if(bt[c]==0) {
        			
        		ct[c]=st;
        		total++;
        		F[c] = 1;
        		}
        	}
        	
        }
        
        for(int i = 0;i < n;i++)
        {
        	tat[i] = ct[i] - at[i];
        	wt[i] = tat[i] - bttt[i];
        	atat = atat + tat[i];
	         awt = awt + wt[i];
        }
        
        for (int i = 0; i < n;i++)
        {
        	System.out.println(pid[i] + "\t" + at[i]+ "\t" + bttt[i] + "\t" + prio[i] + "\t" + ct[i] + "\t" + tat[i] + "\t"+ wt[i]);
        }
        System.out.println("Average TAT and WT are: ");
	     System.out.println("ATAT="+atat/n +"\t"+ "AWT"+awt/n);
	}
}
