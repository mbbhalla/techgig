package techgig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contest1 {
	
	private static int minimum(int[] heights, int pos1, int pos2) {
		int length = heights.length;

		if(pos1 == pos2) {
			return 0;
		}
		if(pos1 < 0 || pos2 < 0 || pos1 >= length || pos2 >= length) {
			return -1;
		}
		
		int smaller = Math.min(pos1, pos2);
		int bigger = Math.max(pos1, pos2);
		
		//direction 1
		int d1 = heights[pos1] + heights[pos2] + (bigger - smaller); 
		//diection 2
		int d2 = heights[pos1] + heights[pos2] + 1 + (heights.length - 1 - bigger) + smaller;
		
		return Math.min(d1, d2);
	}
	
	public static void main(String[] args) {
		int[] heights = {1, 2, 3, 4};
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < heights.length - 1; i++) {
			for(int j = i + 1; j < heights.length; j++) {
				int min = minimum(heights, i, j);
				list.add(min);
			}
		}
		
		Collections.sort(list, Collections.reverseOrder());		
		System.out.println(list.get(0));
		
	}
}
