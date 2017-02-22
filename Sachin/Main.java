import java.util.*;

public class Main {

	public static class Graph {

		public Node[] nodes = new Node[26];
		public int[] isVisited = new int[26];
		private String orderedString;
		private Stack<Character> stack = new Stack<>();

		public Graph() {

			for(int i = 0; i < 26; i++) {

				nodes[i] = new Node((char) ('a' + i), i);
				isVisited[i] = 0;
			}
		}

		public boolean getTopologicalOrderOfNodesIfPossible() {

			for(int i = 0; i < 26; i++) {

				if(isVisited[nodes[i].identifier] == 0) {

					// System.out.println("dfs for " + nodes[i].identifier);
					boolean isCycle = dfs(nodes[i], stack);

					if(isCycle) {
						// System.out.println("cycle detected for " + nodes[i].identifier);
						return false;
					}

				}

			}

			return true;
		}

		public boolean dfs(Node root, Stack<Character> stack) {

			isVisited[root.identifier] = 1;

			for(Node child : root.children) {

				if(isVisited[child.identifier] == 0) {

					boolean isCycle = dfs(child, stack);

					if(isCycle) return isCycle;

				} else if (isVisited[child.identifier] == 1) {

					return true;
				}
			}

			isVisited[root.identifier] = 2;
			stack.push(root.ch);
			
			return false;
		}

		public String getOrderedString() {

			StringBuilder sb = new StringBuilder();
			while(!stack.empty()) {

				sb.append(stack.pop());
			}
			return sb.toString();
		}

	}

	public static class Node {

		public char ch;
		public int identifier;
		public List<Node> children = new ArrayList<>();

		public Node(char ch, int identifier) {

			this.ch = ch;
			this.identifier = identifier;
		}
	}

	public static void main(String[] args) {

		int N;
		Scanner sc = new Scanner(System.in);
		

		N = sc.nextInt();
		String[] words = new String[N];

		for(int i = 0; i < N; i++) {

			words[i] = sc.next();
		}

		Graph g = new Graph();

		for(int i = 0; i < N; i++) {

			for(int j = i+1; j < N; j++) {

				String word1 = words[i];
				String word2 = words[j];

				int len1 = word1.length(), len2 = word2.length();

				for(int k = 0; k < Math.min(len1, len2); k++) {

					if(word1.charAt(k) == word2.charAt(k)) {
						continue;
					} else {

						char ch1 = word1.charAt(k), ch2 = word2.charAt(k);
						// System.out.println(ch1 + "   " + ch2);
						g.nodes[ch1 - 'a'].children.add(g.nodes[ch2 - 'a']);
						break;
					}

				}
			}
		}

		if(g.getTopologicalOrderOfNodesIfPossible()) {

			System.out.println(g.getOrderedString());
		} else {
			System.out.println("Impossible");
		}
	}
}