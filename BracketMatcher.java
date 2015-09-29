import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class BracketMatcher {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in);) {
			System.out.println("Tell? : ");
			String input = scanner.nextLine();
			
			Stack<String> stack = new Stack<>();
			
			Set<String> open = new HashSet<>(Arrays.asList("(", "{", "["));
			Set<String> close = new HashSet<>(Arrays.asList(")", "}", "]"));
			Map<String, String> map = new HashMap<String, String>() {{ 
				put(")", "(");
				put("}", "{");
				put("]", "[");
			}};
			Arrays.asList(input.split(""))
				.stream()
				.forEach(s -> {
					if(open.contains(s)) {
						stack.push(s);
					} else if(close.contains(s)) {
						String popped = stack.pop();
						if(!map.get(s).equals(popped)) {
							System.out.println("error");
							return;
						}
					}
				});
			
			System.out.println("matched");
		}
	}
}
