package techgig;

import java.util.*;

/*
 * Rolling Dices
 */

public class Contest5 {

	public static void main(String[] args) {
	
		List<Integer> list1 = new ArrayList<Integer>();
	      List<Integer> list2 = new ArrayList<Integer>();
	      
	      String input1 = "{12,11,5,2,7,5,11}";
	      String input2 = "{12,11,5,7,2,5,11}";
	      
	      String[] split1 = input1.replaceAll("\\{","").replaceAll("\\}", "").split(",");
	      for(String s : split1) {
	       	list1.add(Integer.parseInt(s));
	      }
	                  
	      String[] split2 = input2.replaceAll("\\{","").replaceAll("\\}", "").split(",");
	      for(String s : split2) {
	       	list2.add(Integer.parseInt(s));
	      }
	                  
	       Collections.sort(list1);
	       Collections.sort(list2);
	                  
	       System.out.println(list1.equals(list2) ? "Lucky" : "Unlucky");           
	       
	                  
	}
}
