package itm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class VirtualBitmap {

	public static void main(String args[]) throws FileNotFoundException{

		final int MEGABITSETSIZE = 1000000;
		final int PERFLOW_BITSIZE = 250;

		BitSet mega_bitset = new BitSet(MEGABITSETSIZE);

        	int[] random_array = new int[PERFLOW_BITSIZE];
        	Random rand2 = new Random();
        	for(int i=0;i<PERFLOW_BITSIZE;i++){
        		random_array[i] = rand2.nextInt(Integer.MAX_VALUE)+1;
        	}


        	Scanner scanner = new Scanner(new File("C:/Users/venkateshmantha/Desktop/itm/Cardinality.csv"));
            scanner.useDelimiter(",");

            Random rand3 = new Random();
            int fid = 1;
            while(scanner.hasNext()){

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

            	ArrayList perflow_indices = new ArrayList(flow_size);

            	while(iterator.hasNext()){
            		int value = iterator.next().hashCode();
            		int perflow_index = value%PERFLOW_BITSIZE;
            		perflow_indices.add(perflow_index);
            		int Bindex = ((fid^(random_array[perflow_index]))%MEGABITSETSIZE);
            		mega_bitset.set(Bindex);
            	}

            	Iterator iter = perflow_indices.iterator();
            	int zero_bit_count =0;
            	while(iter.hasNext()){
            		int value = (int)iter.next();
            		if(mega_bitset.get(value)){
            			zero_bit_count++;
            		}
            	}
            	double V = (double)zero_bit_count/PERFLOW_BITSIZE;
            	System.out.println(V);

            	fid++;
            }


            System.out.println(MEGABITSETSIZE-mega_bitset.cardinality());
            scanner.close();

	}

}
