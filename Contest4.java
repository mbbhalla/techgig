package techgig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

public class Contest4 {

	private static class Pair {
		private int emp1;
		private int emp2;
		
		Pair(int emp1, int emp2) {
			this.emp1 = emp1;
			this.emp2 = emp2;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pair arg = (Pair)obj;
			if(Math.min(emp1, emp2) == Math.min(arg.emp1, arg.emp2)
			   && Math.max(emp1, emp2) == Math.max(arg.emp1, arg.emp2)) {
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public int hashCode() {
			return Integer.valueOf(Math.min(emp1, emp2)).hashCode();
		}
	}
	
	private static class Pairs {
		private Set<Pair> set = new HashSet<Pair>();
		
		public void add(Pair arg) {
			set.add(arg);
		}
		
		/* if emp1 and emp2 exist in the pairs i.e they have met before */
		public boolean hasMet(int emp1, int emp2) {
			Pair arg = new Pair(emp1, emp2);
			for(Pair p: set) {
				if(arg.equals(p)) {
					return true;
				}
			}
			return false;
		}
	}
	
	private static List<String> mapsState = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		String input = "{(1,9),(1,2),(3,4),(1,5)}";
		Pairs pairs = new Pairs();
		
		Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
		
		String[] splits = input.trim().replaceAll("\\),\\(", "--").replaceFirst("\\{\\(", "").replaceFirst("\\)\\}", "").split("--");
		for(String pair : splits) {
			/* pair = 1,2 */
			String[] emps = pair.split(",");
			pairs.add(new Pair(Integer.parseInt(emps[0]), Integer.parseInt(emps[1])));
			map1.put(Integer.parseInt(emps[0]), 0);
			map1.put(Integer.parseInt(emps[1]), 0);
		}

		int max1, cKey1, max2, cKey2;
		boolean tflag;

		do {
			tflag = true;
			
			/* for map1 */
			Integer[] keysArr1 = map1.keySet().toArray(new Integer[0]);
			for(int i = 0; i < keysArr1.length - 1; i++) {
				for(int j = i + 1; j < keysArr1.length; j++) {
					if(pairs.hasMet(keysArr1[i], keysArr1[j])) {
						map1.put(keysArr1[i], map1.get(keysArr1[i]) + 1);
						map1.put(keysArr1[j], map1.get(keysArr1[j]) + 1);
					}
				}
			}
			
			try {
				max1 = Collections.max(map1.values()); 
			} catch(NoSuchElementException e) {
				max1 = 0;
			}
			cKey1 = -1;
			List<Integer> cKey1List = new ArrayList<Integer>();
			for(int i = 0; i < keysArr1.length; i++) {
				if(map1.get(keysArr1[i]) == max1 && max1 != 0) {
					cKey1 = keysArr1[i];
					cKey1List.add(keysArr1[i]);
				}
			}
			
			/* for map2 */
			Integer[] keysArr2 = map2.keySet().toArray(new Integer[0]);
			for(int i = 0; i < keysArr2.length - 1; i++) {
				for(int j = i + 1; j < keysArr2.length; j++) {
					if(pairs.hasMet(keysArr2[i], keysArr2[j])) {
						map2.put(keysArr2[i], map2.get(keysArr2[i]) + 1);
						map2.put(keysArr2[j], map2.get(keysArr2[j]) + 1);
					}
				}
			}
			
			try {
				max2 = Collections.max(map2.values()); 
			} catch(NoSuchElementException e) {
				max2 = 0;
			}
			cKey2 = -1;
			List<Integer> cKey2List = new ArrayList<Integer>();
			for(int i = 0; i < keysArr2.length; i++) {
				if(map2.get(keysArr2[i]) == max2 && max2 != 0) {
					cKey2 = keysArr2[i];
					cKey2List.add(keysArr2[i]);
				}
			}

			if(cKey1 == -1 && cKey2 != -1) {
				//2 --> 1
				for(Integer acKey2 : cKey2List) {
					map1.put(acKey2, 0);
					map2.remove(acKey2);
					if(mapsState.contains(map1.keySet().toString() + "---" + map2.keySet().toString())) {
						//restore
						map1.remove(acKey2);
						map2.put(acKey2, 0);
						tflag = false;
					} else {
						tflag = true;
						mapsState.add(map1.keySet().toString() + "---" + map2.keySet().toString());
						break;
					}
				}
				if(!tflag)
					break;
			} else if (cKey1 != -1 && cKey2 == -1) {
				//1 --> 2
				//map2.put(cKey1, 0);
				//map1.remove(cKey1);
				for(Integer acKey1 : cKey1List) {
					map2.put(acKey1, 0);
					map1.remove(acKey1);
					if(mapsState.contains(map1.keySet().toString() + "---" + map2.keySet().toString())) {
						//restore
						map2.remove(acKey1);
						map1.put(acKey1, 0);
						tflag = false;
					} else {
						tflag = true;
						mapsState.add(map1.keySet().toString() + "---" + map2.keySet().toString());
						break;
					}
				}
				if(!tflag) 
					break;
			} else if(cKey1 != -1 && cKey2 != -1) {
				//1 --> 2
				//2 --> 1
				boolean exitfor = false;
				for(Integer acKey1 : cKey1List) {
					for(Integer acKey2 : cKey2List) {
						map2.put(acKey1, 0);
						map1.remove(acKey1);
						map1.put(acKey2, 0);
						map2.remove(acKey2);
						if(mapsState.contains(map1.keySet().toString() + "---" + map2.keySet().toString())) {
							//restore back
							map2.remove(acKey1);
							map1.put(acKey1, 0);
							map1.remove(acKey2);
							map2.put(acKey2, 0);
							tflag = false;
						} else {
							exitfor = true;
							tflag = true;
							mapsState.add(map1.keySet().toString() + "---" + map2.keySet().toString());
							break;
						}
					}
					if(exitfor) {
						break;
					}
				}
				if(!exitfor) {
					tflag = false;
					break;
				}
			}
			
			//reset all maps
			for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
				map1.put(entry.getKey(), 0);
			}
			for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
				map2.put(entry.getKey(), 0);
			}
			
		} while(!(cKey1 == -1 && cKey2 == -1));
		
		if(tflag) {
			System.out.println("Yes");
			System.out.println("Stable state");
			System.out.println(mapsState.get(mapsState.size() - 1));
		} else {
			System.out.println("No");
			System.out.println("Unstable state");
			System.out.println(mapsState.get(mapsState.size() - 1));
		}
		
	}

}
