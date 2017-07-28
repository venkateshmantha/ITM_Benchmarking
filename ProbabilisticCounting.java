package itm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ProbabilisticCounting {

	public static void main(String args[]) throws FileNotFoundException{

		final int BITSETSIZE = 250;
		BitSet bitset = new BitSet(BITSETSIZE);

		Scanner scanner = new Scanner(new File("C:/Users/venkateshmantha/Desktop/itm/Cardinality.csv"));
        scanner.useDelimiter(",");

        while(scanner.hasNext())
        {
        	int flow_size = scanner.nextInt();
        	HashSet random_numbers = new HashSet(flow_size);
        	int random_numbers_count =0;
        	while(true)
        	{
	        	Random rand = new Random();
	        	int value = rand.nextInt(Integer.MAX_VALUE);		//packets
	        	if(random_numbers.add(value))
	        		random_numbers_count++;
	        	if(random_numbers_count==flow_size)
	        		break;

        	}
        	Iterator iterator = random_numbers.iterator();
        	while(iterator.hasNext()){
        		int value = iterator.next().hashCode(); //hash function
        		bitset.set(value%BITSETSIZE);		//setting the bit
        	}

        	int zero_bit_count = BITSETSIZE - bitset.cardinality();
        	double V = (double) zero_bit_count/BITSETSIZE;
        	double estimator = -1*BITSETSIZE*(Math.log(V));
        	System.out.println(estimator);
        	bitset.clear();
        }

        scanner.close();

	}

}
