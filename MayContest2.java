package techgig;

import java.util.ArrayList;
import java.util.List;

public class MayContest2 {
	
	private static int find(int[] input1, int input2) {
		List<Integer> houses = new ArrayList<Integer>();
		if(input1[0] != 1 && input1[0] != 2) {
			return -1;
		} else if(input1[0] == 1) {
			houses.add(1);
			houses.add(0);
		} else if(input1[0] == 2) {
			houses.add(1);
			houses.add(1);
		}
		
		for(int i = 1; i < input1.length - 1; i++) {
			if(input1[i] == 0 || input1[i] == 1 || input1[i] == 2 || input1[i] == 3) {
				int x = input1[i] - houses.get(i) - houses.get(i - 1);
				if(x == 0 || x == 1) {
					houses.add(x);
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
		
		int l = houses.size();
		if((houses.get(l - 1) + houses.get(l - 2)) != input1[input1.length - 1]) {
			return -1;
		}
		
		return houses.get(input2 - 1);
	}
	
	public static void main(String[] args) {
		int[] scores = {1,2,2,2,0};
		int house = 4;
		System.out.println(find(scores, house));
	}
}
