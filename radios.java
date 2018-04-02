import java.util.Arrays;
import java.util.Scanner;

public class radios {
	public static int[] x, y;
	public static boolean[] visited;
	public static int n;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			n = scan.nextInt();
			x = new int[n];
			y = new int[n];
			for(int i = 0; i < n; i++) {
				x[i] = scan.nextInt();
				y[i] = scan.nextInt();
			}
			
			visited = new boolean[n];
			
			int lo = 0, hi = (int)Math.ceil(20000 * Math.sqrt(2.0));
			while(hi > lo + 1) {
				int mid = (lo + hi) / 2;
				if(works(mid))
					hi = mid;
				else
					lo = mid + 1;
			}
			
			System.out.printf("Island #%d: %d%n", z, works(lo) ? lo : hi);
		}
		scan.close();
	}
	
	public static boolean works(int r) {
		Arrays.fill(visited, false);
		dfs(0, r);
		for(int i = 0; i < n; i++)
			if(!visited[i])
				return false;
		return true;
	}
	
	public static void dfs(int node, int r) {
		visited[node] = true;
		for(int i = 0; i < n; i++) {
			if(!visited[i] && dist(node, i) <= r + 1e-9)
				dfs(i, r);
		}
	}
	
	public static double dist(int a, int b) {
		double xx = x[a] - x[b], yy = y[a] - y[b];
		return Math.sqrt(xx * xx + yy * yy);
	}
}