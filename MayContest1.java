package techgig;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class MayContest1 
{ 
    public static String partition(int[] input1)
    {		boolean validInput = true;
		for(int i = 0; i < input1.length; i++) {
			if(input1[i] <= 0) {
				validInput = false;
				break;
			}
		}
		if(validInput) {
				Set<Integer> s1 = new HashSet<Integer>();
				Set<Integer> s2 = new HashSet<Integer>();
				s1.add(0);
				for(int i = 0; i < input1.length; i++) {
					/* loop over s1 and add to s2 */
					for(Integer x : s1) {
						s2.add(Math.abs(input1[i] + x));
						s2.add(Math.abs(input1[i] - x));
					}
					s1.clear();
					s1.addAll(s2);
					s2.clear();
				}
				return s1.contains(0) ? "Yes" : "No";
		} else {
			return "Invalid";
		}
    }
    
    public static void main(String[] args) {
    	int[] array = {1,5,5,11,4,5,2,3454,4323,67,52,54677,232454,54,53454,5,4,5,7,3,2,4,6,6};
		System.out.println(partition(array));
	}
    
}