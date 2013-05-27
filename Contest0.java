package techgig;

import java.util.Scanner;

public class Contest0 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of judges (n): ");
		int n = scanner.nextInt();
		if(n == 0) {
			System.out.println("Total score = 0");
		} else if(n > 0 && n < 20 && n % 2 != 0) {	
			System.out.println("Enter max score (m): ");
			int m = scanner.nextInt();

			int[] scores = new int[n];
			for(short i = 0; i < n; i++) {
				do {
					System.out.println("Enter " + (i + 1) + " judge score: ");
					scores[i] = scanner.nextInt();
				} while(scores[i] < 0 || scores[i] > m);
			}

			System.out.println("Total = " + calculateTotal(scores));
		} else { 
			System.out.println("Entered wrong number of judges");
		}
	}

	private static int calculateTotal(int[] scores) {
		int total = 0, nextL = 0, nextR = 0;
		int midPoint = (int) Math.floor(scores.length/2);
		total += scores[midPoint];
		nextL = midPoint - 1;
		nextR = midPoint + 1;
		while(nextL >= 0 && nextR < scores.length) {
			if(scores[nextL] == scores[nextR]) {
				total += scores[nextL];
			}
			nextL--; nextR++;
		}
		return total;
	}
}
