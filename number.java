import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class number {
	public static final int[] DR = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static final int[] DC = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static final char NULL = '\0';
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int rows = scan.nextInt(), cols = scan.nextInt();
			char[][] grid = new char[rows][];
			for(int i = 0; i < rows; i++)
				grid[i] = scan.next().toCharArray();
			
			Set<Long> set = new HashSet<>();
			
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					if(!Character.isDigit(grid[i][j]))
						continue;
					
					for(int k = 0; k < 8; k++) {
						long a = 0L;
						char op = NULL;
						long b = 0L;
						
						int ii = i, jj = j;
						while(ii >= 0 && ii < rows && jj >= 0 && jj < cols) {
							if(Character.isDigit(grid[ii][jj])) {
								int digit = grid[ii][jj] - '0';
								if(op == NULL) {
									a = a * 10L + digit;
									set.add(a);
								} else {
									b = b * 10L + digit;
									if(op == '+')
										set.add(a + b);
									else if(op == '-')
										set.add(a - b);
									else if(op == '*')
										set.add(a * b);
									else if(a % b == 0L)
										set.add(a / b);
								}
							} else if(op == NULL) {
								op = grid[ii][jj];
							} else {
								break;
							}
							
							ii += DR[k];
							jj += DC[k];
						}
					}
				}
			}

			System.out.printf("Number Search #%d:%n", z);
			int queries = scan.nextInt();
			for(int q = 1; q <= queries; q++)
				System.out.println(set.contains(scan.nextLong()) ? "Yes" : "No");
			System.out.println();
		}
		scan.close();
	}
}