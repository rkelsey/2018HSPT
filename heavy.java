import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class heavy {
	public static int[][] grid;
	public static int limit, dir, startR, startC, pathIndex;
	public static List<Path>[][][] paths;
	
	public static final int[] DR = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static final int[] DC = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			limit = scan.nextInt();
			grid = new int[8][8];
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++)
					grid[i][j] = scan.nextInt();
			
			if(limit == 0) {
				int min = Integer.MAX_VALUE;
				for(int i = 0; i < 8; i++)
					for(int j = 0; j < 8; j++)
						min = Math.min(min, grid[i][j]);
				System.out.printf("Crypt #%d: %d%n", z, min);
				continue;
			}
			
			paths = new List[2][8][8];
			for(int i = 0; i < 2; i++)
				for(int j = 0; j < 8; j++)
					for(int k = 0; k < 8; k++)
						paths[i][j][k] = new ArrayList<>();
			
			for(startR = 0; startR < 8; startR++) {
				for(startC = 0; startC < 8; startC++) {
					if(grid[startR][startC] >= limit / 2) {
						dir = -1;
						pathIndex = 0;
						bruteForce(startR, startC, limit / 2 - 1, grid[startR][startC] - limit / 2, turnOn(0L, startR, startC));
					}
					
					if(grid[startR][startC] > limit / 2) {
						dir = 1;
						pathIndex = 1;
						bruteForce(startR, startC, limit / 2 + 2, grid[startR][startC] - limit / 2 - 1, turnOn(0L, startR, startC));
					}
				}
			}
			
			for(int i = 0; i < 2; i++)
				for(int j = 0; j < 8; j++)
					for(int k = 0; k < 8; k++)
						Collections.sort(paths[i][j][k]);
			
			List<Path>[][] start = paths[0], end = paths[1];
			int ans = Integer.MAX_VALUE;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					for(int k = 0; k < 8; k++) {
						int ii = i + DR[k], jj = j + DC[k];
						if(ii < 0 || ii >= 8 || jj < 0 || jj >= 8)
							continue;
						
						for(Path a : start[i][j]) {
							for(Path b : end[ii][jj]) {
								if(a.cost + b.cost >= ans)
									break;
								
								if((a.mask & b.mask) == 0L) {
									ans = Math.min(ans, a.cost + b.cost);
									break;
								}
							}
						}
					}
				}
			}
			
			System.out.printf("Crypt #%d: %d%n", z, ans);
		}
		scan.close();
	}
	
	public static void bruteForce(int r, int c, int h, int cost, long mask) {
		if(h < 0 || h > limit) {
			paths[pathIndex][startR][startC].add(new Path(mask, cost));
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			int ddr = r + DR[i], ddc = c + DC[i];
			if(ddr < 0 || ddr >= 8 || ddc < 0 || ddc >= 8 || isOn(mask, ddr, ddc) || grid[ddr][ddc] < h)
				continue;
			
			bruteForce(ddr, ddc, h + dir, cost + grid[ddr][ddc] - h, turnOn(mask, ddr, ddc));
		}
	}
	
	public static long turnOn(long mask, int r, int c) {
		int pos = r * 8 + c;
		return mask | (1L << pos);
	}
	
	public static boolean isOn(long mask, int r, int c) {
		int pos = r * 8 + c;
		return (mask & (1L << pos)) != 0;
	}
	
	public static class Path implements Comparable<Path> {
		public long mask;
		public int cost;
		
		public Path(long mask, int cost) {
			this.mask = mask;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Path other) {
			return Integer.compare(cost, other.cost);
		}
	}
}