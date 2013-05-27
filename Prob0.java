package techgig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 *  To find the maximum sum from a list/array without considering consecutive numbers
 */

public class Prob0 {
	
	public static int maxSum(List<Integer> list) {
		int max = 0;
		if(list.size() == 0) {
			return 0;
		} else if(list.size() == 1) {
			return list.get(0);
		} else if(list.size() == 2) {
			return Math.max(list.get(0), list.get(1));
		} else if(list.size() == 3) {
			int z = list.get(0), o = list.get(1), t = list.get(2);
			return Math.max(Math.max(Math.max(z, t), (z + t)), o);
		}
		else if(list.size() > 3) {
			int sum;
			for(int i = 0; i < list.size(); i++) {
				if(i == 0) {
					sum = list.get(i) + maxSum(list.subList(i + 2, list.size()));
				} else if(i == list.size() - 1) { 
					sum = list.get(i) + maxSum(list.subList(0, list.size() - 2));
				} else if(i == 1) { 
					sum = list.get(i) + /*maxSum(Collections.<Integer> emptyList()) +*/ maxSum(list.subList(i + 2, list.size()));
				} else if(i == list.size() - 2) {
					sum = list.get(i) + maxSum(list.subList(0, i - 1)) /*+ maxSum(Collections.<Integer> emptyList())*/;
				} else {
					sum = list.get(i) + maxSum(list.subList(0, i - 1)) + maxSum(list.subList(i + 2, list.size()));
				}
				
				if(sum > max) {
					max = sum;
				}
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(100,50,50,100);
		System.out.println("Max Sum is = " + maxSum(list));
	}
	
}
