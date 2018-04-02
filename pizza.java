import java.util.Scanner;

public class pizza {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int n = scan.nextInt(), s = scan.nextInt();
			System.out.printf("Order #%d: %.3f%n", z, (s + s / 2.0 * (n - 1)) * s * Math.sqrt(3.0) / 2.0);
		}
		scan.close();
	}
}