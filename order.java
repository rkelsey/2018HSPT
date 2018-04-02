import java.util.Scanner;

public class order {
	public static void main(String[] args) {
		final int LIM = 100000;
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int n = scan.nextInt();
			boolean[] arr = new boolean[LIM + 1];
			for(int i = 0; i < n; i++)
				arr[scan.nextInt()] = true;
			
			System.out.printf("Day #%d: ", z);
			boolean first = true;
			for(int i = 0; i <= LIM; i++) {
				if(!arr[i])
					continue;
				
				int start = i;
				while(i <= LIM && arr[i])
					i++;
				
				i--;
				
				if(first)
					first = false;
				else
					System.out.print(", ");
				
				if(start == i)
					System.out.printf("%d", start);
				else
					System.out.printf("%d-%d", start, i);
			}
			System.out.println("\n");
		}
		scan.close();
	}
}