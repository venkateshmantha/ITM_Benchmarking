package itm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Project5ext {

	public static void main(String args[]) throws FileNotFoundException{
		
		final int BITSETSIZE = 1000000;
		BitSet bitset = new BitSet(BITSETSIZE);
		
		
        	int temp = 1013390;
        	
        	for(int i=1;i<=temp;i++)
        	{
	        	Random rand = new Random(); 
	        	int value = rand.nextInt(temp);		//packets
	        	bitset.set(value%BITSETSIZE);		//hash function
        	}
        	System.out.println(BITSETSIZE - bitset.cardinality());
        	bitset.clear();
        }
        

}
