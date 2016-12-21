package itm;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Project4 {

	public static void main(String args[]) throws FileNotFoundException{
		
		final int COUNTER_SIZE = 5;
		final int COUNTER_NUM = 32;
		BitSet[] counter = new BitSet[COUNTER_NUM];
		
		Scanner scanner = new Scanner(new File("C:/Users/venkateshmantha/Desktop/itm/new.csv"));
        scanner.useDelimiter(",");
        
        while(scanner.hasNext())
        {
        	int temp = scanner.nextInt();
        	int counter_index =0;
        	int max_set_bit =0;
        	for(int i=1;i<=temp;i++)
        	{
	        	Random rand = new Random(); 
	        	int rand_value = rand.nextInt(temp);		//packets
	        	String binaryStr = Integer.toBinaryString(rand_value);
	        	counter_index = rand_value%COUNTER_NUM;
	        	if(counter[counter_index] == null)
	            	counter[counter_index] = new BitSet(COUNTER_SIZE);
	        	int zeroes = CountLeadingZeros(binaryStr)+1;
	        	//System.out.print(rand_value + " ");
	        	//System.out.print(zeroes + " ");
	        	//System.out.println();
	        	counter[counter_index].set(zeroes);		//hash function
        	}
        	//System.out.println(counter);
        	double sum =0;
        	for(int i=0;i<COUNTER_NUM;i++){
        		//System.out.print(counter[i] + " ");
        		int highest_set_bit = counter[i].length()-1;	//counting the highest set bit
        		double exp = Math.pow(2, -highest_set_bit);
        		sum = sum +exp;
        		//System.out.print(exp + " ");
        		//System.out.println();
        		counter[i].clear();
        		
        	}
        	System.out.println(sum);
        	//System.out.println();
        	
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
