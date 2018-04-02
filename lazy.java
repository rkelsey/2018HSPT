import java.util.Scanner;

public class lazy {
	public static double paths;
	public static Road[] roads;
	public static boolean[] visited;
	public static int n, m, e;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			n = scan.nextInt();
			m = scan.nextInt();
			e = scan.nextInt();
			roads = new Road[e];
			
			for(int i = 0; i < e; i++)
				roads[i] = new Road(scan.nextInt() - 1, scan.nextInt() - 1, scan.nextInt());
			
			paths = 0.0;
			visited = new boolean[e];
			allPaths(0, 0);
			
			System.out.printf("City #%d: %.3f%%%n", z, 100.0 * (1.0 / (1.0 + paths)));
		}
		scan.close();
	}
	
	public static void allPaths(int node, int weight) {
		if(node == n - 1) {
			paths += (double)m / weight;
			return;
		}
		
		for(int i = 0; i < e; i++) {
			if(!visited[i] && roads[i].s == node) {
				visited[i] = true;
				allPaths(roads[i].e, weight + roads[i].w);
				visited[i] = false;
			}
		}
	}
	
	public static class Road {
		public int s, e, w;
		
		public Road(int a, int b, int c) {
			s = a;
			e = b;
			w = c;
		}
	}
}