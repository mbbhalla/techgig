package techgig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prob1 {

	private static void output(List<Character> list, Set<Character> set) throws Exception {

		int listSize = list.size();

		try {
			if(listSize == 0) {
				throw new IllegalArgumentException("Cannot pass empty list parameter");
			}
			
			int setSize = set.size();
			
			Set<Character> tempset = new HashSet<Character>();
			tempset.addAll(list);
			
			if(setSize > listSize || setSize > tempset.size()) {
				throw new IllegalArgumentException("Not possible to calculate solution");
			}
	
			int minSize = list.size() + 1;
			List<Character> finalList = new ArrayList<Character>();
			int finalPos = 0;
			for(int i = 0; i <= listSize - setSize; i++) {
				for(int j = i + setSize - 1; j < listSize; j++) {
					List<Character> subList = list.subList(i,  j + 1);
					if(subList.containsAll(set)) {
						/* candidate list */
						if(minSize > subList.size()) {
							minSize = subList.size();
							finalList = subList;
							finalPos = i;
						}
					}
				}
			}
			
			if(finalList.size() == 0) {
				System.out.println("No such list");
			} else {
				System.out.println(finalList + " @ " + finalPos + " position");
			}
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	public static void main(String[] args) {
		List<Character> list = Arrays.asList('A','I','F','D','C','C','E','A','C','G','B');
		//List<Character> list = Arrays.asList('D','B','A');
		
		Set<Character> set = new HashSet<Character>();
		set.add('E');
		set.add('G');
		
		
		
		try {
			output(list, set);
		} catch (Exception e) {
			System.out.println("Exception caused: " + e.getMessage());
		}
	}

}
