import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class tone {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int n = scan.nextInt();
			long[] arr = new long[n];
			for(int i = 0; i < n; i++)
				arr[i] = scan.nextLong();
			
			Map<Long, TreeSet<Integer>> map = new HashMap<>();
			for(int i = 0; i < n; i++) {
				if(!map.containsKey(arr[i]))
					map.put(arr[i], new TreeSet<>());
				map.get(arr[i]).add(i);
			}
			
			List<Integer>[] edgeList = new List[n];
			for(int i = 0; i < n; i++) {
				edgeList[i] = new ArrayList<>();
				for(long p = 1L; p <= (1L << 61); p <<= 1) {
					if(p < arr[i])
						continue;
					
					long candidate = p - arr[i];
					if(map.containsKey(candidate)) {
						Integer next = map.get(candidate).ceiling(i + 1);
						if(next != null)
							edgeList[i].add(next);
					}
				}
			}
			
			int[] dp = new int[n];
			int ans = 1;
			for(int i = 0; i < n; i++) {
				dp[i] = Math.max(dp[i], 1);
				for(int adj : edgeList[i])
					ans = Math.max(ans, dp[adj] = Math.max(dp[adj], dp[i] + 1));
			}
			
			System.out.printf("Song #%d: %d%n", z, ans);
		}
		scan.close();
	}
}