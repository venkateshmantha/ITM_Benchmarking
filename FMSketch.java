package itm;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class FMSketch {

	public static void main(String args[]) throws FileNotFoundException{

		final int COUNTER_SIZE = 32;
		final int COUNTER_NUM = 32;
		BitSet[] counter = new BitSet[COUNTER_NUM];

		Scanner scanner = new Scanner(new File("C:/Users/venkateshmantha/Desktop/itm/new.csv"));
        scanner.useDelimiter(",");

        while(scanner.hasNext())
        {
        	int temp = scanner.nextInt();
        	int counter_index =0;
        	/*if(counter[counter_index] == null)
        	counter[counter_index] = new BitSet(COUNTER_SIZE);*/
        	for(int i=1;i<=temp;i++)
        	{
	        	Random rand = new Random();
	        	int rand_value = rand.nextInt(temp);		//packets
	        	String binaryStr = Integer.toBinaryString(rand_value);
	        	counter_index = rand_value%COUNTER_NUM;
	        	if(counter[counter_index] == null)
	            	counter[counter_index] = new BitSet(COUNTER_SIZE);
	        	int zeroes = CountLeadingZeros(binaryStr);
	        	//System.out.print(rand_value + " ");
	        	//System.out.print(zeroes);
	        	//System.out.println();
	        	counter[counter_index].set(zeroes);		//hash function
        	}
        	//System.out.println(counter);
        	int sum =0;
        	for(int i=0;i<COUNTER_NUM;i++){
        		//System.out.println(counter[i]);
        		int first_set_bit_index = counter[i].nextSetBit(1);
        		if(first_set_bit_index != -1){
		        	int zeroth_bit_index =counter[i].nextClearBit(first_set_bit_index);
		        	int sequence_length = zeroth_bit_index-first_set_bit_index;		//counting the consecutive set bits
		        	sum = sum + sequence_length;
	        		counter[i].clear();
        		}
        		else sum =0;
        	}

        	System.out.println(sum);

        }

        scanner.close();

	}

	public static int CountLeadingZeros(String s){
		int count=0;
		int strlen = s.length();
		if(strlen>5){
			String newStr = new StringBuilder(s.substring(0, strlen-5)).reverse().toString();	//as given in 4.5pdf
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
