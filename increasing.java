import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class increasing {
	public static final long MOD = (long)1e9 + 7L;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int n = scan.nextInt(), k = scan.nextInt();
			int[] arr = new int[n];
			for(int i = 0; i < n; i++)
				arr[i] = scan.nextInt();
			
			arr = coordinateCompress(arr);
			
			BinaryIndexedTree[] bits = new BinaryIndexedTree[k];
			for(int i = 0; i < k; i++)
				bits[i] = new BinaryIndexedTree(n);
			
			for(int i = 0; i < n; i++) {
				bits[0].add(arr[i], 1L);
				for(int j = 1; j < k; j++)
					bits[j].add(arr[i], bits[j-1].sum(arr[i] - 1));
			}
			
			System.out.printf("Class #%d: %d%n", z, bits[k-1].sum(n));
		}
		scan.close();
	}
	
	public static int[] coordinateCompress(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < arr.length; i++)
			map.put(arr[i], i);
		Arrays.sort(arr);
		int[] next = new int[arr.length];
		for(int i = 0; i < arr.length; i++)
			next[map.get(arr[i])] = i + 1;
		return next;
	}
	
	public static class BinaryIndexedTree {
		public long[] cf;
		public int n;
		
		public BinaryIndexedTree(int n) {
			this.n = n;
			cf = new long[n+1];
		}
		
		public void add(int i, long val) {
			while(i <= n) {
				cf[i] = (cf[i] + val) % MOD;
				i += i & -i;
			}
		}
		
		public long sum(int i) {
			long res = 0L;
			while(i > 0) {
				res = (res + cf[i]) % MOD;
				i -= i & -i;
			}
			return res;
		}
	}
}