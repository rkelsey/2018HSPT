import java.util.Arrays;
import java.util.Scanner;

public class tic {
	public static int[][] grid = new int[4][4];
	public static int[] memo = new int[1 << 16];
	public static boolean[] threeInARow = new boolean[1 << 16];
	public static boolean[] fourInARow = new boolean[1 << 16];
	public static int three, four;
	
	public static final int[] DR = {0, 1, 1, -1};
	public static final int[] DC = {1, 1, 0, 1};
	
	public static void main(String[] args) {
		for(int mask = 7; mask < (1 << 16); mask++) {
			threeInARow[mask] = inARow(mask, 3);
			fourInARow[mask] = inARow(mask, 4);
		}
		
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			three = scan.nextInt();
			four = scan.nextInt();
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
					grid[i][j] = scan.nextInt();
			
			Arrays.fill(memo, -1);
			int res = go(0);
			System.out.printf("Game #%d: %s.%n", z, res > 0 ? "Alex wins" : res < 0 ? "Barb wins" : "It's a draw");
		}
		scan.close();
	}
	
	public static int go(int mask) {
		if(mask == (1 << 16) - 1)
			return 0;
		else if(memo[mask] > -1)
			return memo[mask];
		
		boolean alex = Integer.bitCount(mask) % 2 == 0;
		int res = alex ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(isOn(mask, i, j))
					continue;
				
				int next = turnOn(mask, i, j);
				int bonus = 0;
				if(threeInARow[next] && !threeInARow[mask])
					bonus += three;
				if(fourInARow[next] && !fourInARow[mask])
					bonus += four;
				
				if(alex)
					res = Math.max(res, go(next) + bonus + grid[i][j]);
				else
					res = Math.min(res, go(next) - bonus - grid[i][j]);
			}
		}
		
		return memo[mask] = res;
	}
	
	public static boolean inARow(int mask, int amt) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				loop: for(int k = 0; k < 4; k++) {
					int ii = i, jj = j;
					for(int m = 0; m < amt; m++) {
						if(ii < 0 || ii >= 4 || jj >= 4 || !isOn(mask, ii, jj))
							continue loop;
						ii += DR[k];
						jj += DC[k];
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static int turnOn(int mask, int r, int c) {
		int pos = r * 4 + c;
		return mask | (1 << pos);
	}
	
	public static boolean isOn(int mask, int r, int c) {
		int pos = r * 4 + c;
		return (mask & (1 << pos)) > 0;
	}
}