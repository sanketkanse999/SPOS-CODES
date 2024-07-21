import java.util.Scanner;

import java.util.*;
public class SJF {

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
		        		if( at[i] <= st && F[i] == 0 && bt[i] < min)
		        		{
		        			c = i;
		        			min = bt[i];
		        		}
		        	}
		        
		        	if(c == n)
		        	{
		        		st = st + 1;
		        	}
		        	else
		        	{
		        		ct[c] = st + bt[c];
		        		F[c] = 1;
		        		st = ct[c];
		        		total++;
		        	}
		        	
		        }
		        
		        for(int i = 0;i < n;i++)
		        {
		        	tat[i] = ct[i] - at[i];
		        	wt[i] = tat[i] - bt[i];
		        	atat = atat + tat[i];
			         awt = awt + wt[i];
		        }
		        System.out.println("PID \t AT \t BT \t CT \t TAT\t WT");
		        for (int i = 0; i < n;i++)
		        {
		        	System.out.println(pid[i] + "\t" + at[i]+ "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t"+ wt[i]);
		        }
		        System.out.println("Average TAT and WT are: ");
			     System.out.println("ATAT="+atat/n +"\t"+ "AWT"+awt/n);
			}
		}

