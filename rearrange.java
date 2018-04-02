import java.util.Scanner;

public class rearrange {
	public static char[] pos;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int runs = scan.nextInt();
		for(int z = 1; z <= runs; z++) {
			int n = scan.nextInt(), queries = scan.nextInt();
			Trie root = new Trie();
			for(int i = 0; i < n; i++)
				root.add(scan.next());
			
			pos = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			System.out.printf("Gift Exchange #%d:%n", z);
			
			for(int q = 1; q <= queries; q++) {
				if(scan.nextInt() == 1) {
					char a = scan.next().charAt(0), b = scan.next().charAt(0);
					int aPos = find(a), bPos = find(b);
					pos[aPos] = b;
					pos[bPos] = a;
				} else {
					System.out.println(root.find(scan.nextInt() - 1));
				}
			}
			System.out.println();
		}
		scan.close();
	}
	
	public static int find(char c) {
		for(int i = 0; i < 26; i++)
			if(pos[i] == c)
				return i;
		return -1;
	}
	
	public static class Trie {
		public Trie[] next;
		public boolean word;
		public int count;
		
		public Trie() {
			next = new Trie[26];
			count = 0;
			word = false;
		}
		
		public void add(String s) {
			add(s.toCharArray(), 0);
		}
		
		private void add(char[] ch, int p) {
			count++;
			if(p == ch.length) {
				word = true;
				return;
			}
			
			int i = ch[p] - 'a';
			if(next[i] == null)
				next[i] = new Trie();
			
			next[i].add(ch, p + 1);
		}
		
		public String find(int k) {
			if(k == 0) {
				if(word)
					return "";
				for(int i = 0; i < 26; i++) {
					int p = pos[i] - 'a';
					if(next[p] != null)
						return pos[i] + next[p].find(0);
				}
			}
			
			if(word)
				k--;
			
			for(int i = 0; i < 26; i++) {
				int p = pos[i] - 'a';
				if(next[p] == null)
					continue;
				if(next[p].count <= k)
					k -= next[p].count;
				else
					return pos[i] + next[p].find(k);
			}
			
			return null;
		}
	}
}