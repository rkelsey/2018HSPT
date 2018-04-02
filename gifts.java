import java.util.Scanner;

public class gifts {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int a = scan.nextInt(), b = scan.nextInt(), c = scan.nextInt();
			System.out.println(Math.min(Math.abs(a - b), Math.min(Math.abs(a - c), Math.abs(b - c))));
		}
		scan.close();
	}
}