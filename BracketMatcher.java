import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class BracketMatcher {

	@SuppressWarnings("serial")
	private static boolean matcher(final String arg) {
		Stack<String> stack = new Stack<>();
		
		Set<String> open = new HashSet<>(Arrays.asList("(", "{", "["));
		Set<String> close = new HashSet<>(Arrays.asList(")", "}", "]"));
		Map<String, String> map = new HashMap<String, String>() {{ 
			put(")", "(");
			put("}", "{");
			put("]", "[");
		}};
		
		try {
			Arrays.asList(arg.split(""))
				.stream()
				.forEach(s -> {
					if(open.contains(s)) {
						stack.push(s);
					} else if(close.contains(s)) {
						String popped = stack.pop();
						if(!map.get(s).equals(popped)) {
							throw new RuntimeException();
						}
					}
				});
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in);) {
			System.out.println("Tell? : ");
			String input = scanner.nextLine();
			System.out.println(matcher(input) ? "balanced" : "unbalanced");
		}
	}
}
