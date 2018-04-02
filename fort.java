import java.util.Scanner;

public class fort {
	public static void main(String[] args) {
		final String MATCH = "hey guys";
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt(); scan.nextLine();
		for(int z = 1; z <= runs; z++)
			System.out.println(scan.nextLine().equals(MATCH) ? MATCH : "buzz");
		scan.close();
	}
}