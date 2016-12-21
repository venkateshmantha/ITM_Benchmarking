package itm;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Project5 {

	public static void main(String args[]) throws FileNotFoundException{
		
		final int COUNTER_SIZE = 5;
		final int VIRTUAL_COUNTER_NUM = 32; //s
		final int PHYSICAL_COUNTER_NUM = 1000000; //m
		BitSet[] counter = new BitSet[PHYSICAL_COUNTER_NUM];
		for(int i=0;i<PHYSICAL_COUNTER_NUM;i++){
			counter[i] = new BitSet(COUNTER_SIZE);
		}
		
		int[] random_array = new int[VIRTUAL_COUNTER_NUM];
    	Random rand2 = new Random();
    	for(int i=0;i<VIRTUAL_COUNTER_NUM;i++){
    		random_array[i] = rand2.nextInt(PHYSICAL_COUNTER_NUM)+1;
    		//System.out.print(random_array[i] + " ");	
    	}
		
		Scanner scanner = new Scanner(new File("C:/Users/venkateshmantha/Desktop/itm/new.csv"));
        scanner.useDelimiter(",");
        int fid =1;
        
        while(scanner.hasNext())
        {
        	int temp = scanner.nextInt();
        	int counter_index =0;
        	int[] virtual_array_indices = new int[VIRTUAL_COUNTER_NUM];
        	
        	//populating the indices of virtual counter from the mega array	
        	
        	for(int i=0;i<VIRTUAL_COUNTER_NUM;i++){
        		virtual_array_indices[i] = (fid^random_array[i])%PHYSICAL_COUNTER_NUM; 
        	}
        	
        	
        	for(int i=1;i<=temp;i++)
        	{
	        	Random rand = new Random(); 
	        	int rand_value = rand.nextInt(temp);		//packets
	        	String binaryStr = Integer.toBinaryString(rand_value);
	        	
	        	//selecting the virtual counters using the populated array
	        	
	        	counter_index = virtual_array_indices[rand_value%VIRTUAL_COUNTER_NUM];
	        	/*if(counter[counter_index] == null)
	            	counter[counter_index] = new BitSet(COUNTER_SIZE);*/
	        	int zeroes = CountLeadingZeros(binaryStr)+1;
	        	//System.out.print(rand_value + " ");
	        	//System.out.print(zeroes + " ");
	        	//System.out.println();
	        	counter[counter_index].set(zeroes);		//setting the bit
        	}
        	//System.out.println(counter);
        	
        	/*
        	 * Repeating Hyperloglog algorithm for virtual counters 
        	 */
        	
        	double sum =0;
        	for(int i=0;i<VIRTUAL_COUNTER_NUM;i++){
        		//System.out.print(counter[i] + " ");
        		int	highest_set_bit = counter[virtual_array_indices[i]].length()-1;
            	double exp = Math.pow(2, -highest_set_bit);
        		sum = sum +exp;
        		//System.out.print(exp + " ");
        		//System.out.println();
        		
        		//counter[virtual_array_indices[i]].clear();
        		
        	}
        	System.out.println(sum);
        	//System.out.println();
        	fid++;
        }
        
        scanner.close();
		
	}
	
	public static int CountLeadingZeros(String s){
		int count=0;
		int strlen = s.length();
		if(strlen>5){
			String newStr = new StringBuilder(s.substring(0, strlen-5)).reverse().toString();
			for(int i=0;i<newStr.length();i++){
				if(newStr.charAt(i)=='0'){
					count++;
				}
				else
					break;
			}
			count++;
		}
		return count;
	}
	
}
