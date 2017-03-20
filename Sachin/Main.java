import java.util.*;

public class Main {
	
	public static class Graph {

		public Node[] nodes;
		public int[] isVisited;
		public Map<Key, Boolean> doneKeys;
		public int N;
		public int K;

		public Graph(Map<Integer, Integer> nodeTypeMapping, Map<Integer, List<Integer>> edges, int N, int K) {

			doneKeys = new HashMap<>();
			isVisited = new int[N+1];
			this.N = N;
			this.K = K;

			for(int i = 1; i <= N; i++) {

				isVisited[i] = 0;
			}

			nodes = new Node[N+1];

			for(Map.Entry entry : nodeTypeMapping.entrySet()) {

				int key = (int)entry.getKey();
				int val = (int)entry.getValue();

				nodes[key] = new Key(val, key);
				nodes[val] = new Door(key, val);
			}

			for(int i = 1; i <= N; i++) {

				if(nodes[i] == null) {

					nodes[i] = new Empty(i);
				}
			}

			for(Map.Entry entry : edges.entrySet()) {

				int key = (int)entry.getKey();
				List<Integer> list = (List<Integer>)entry.getValue();

				for(Integer val : list) {

					nodes[key].children.add(nodes[val]);
					nodes[val].children.add(nodes[key]);
				}

			}
		}

		public void getNextUnlockedKeys(Node node) {

			if(node instanceof Key) {

				doneKeys.put((Key)node, true);
			}
			
			if(node instanceof Door && !doneKeys.containsKey(nodes[((Door)node).mappedKey])) {

				isVisited[node.num] = 5;
				return;
			}

			isVisited[node.num] = 1;

			for(Node child : node.children) {

				if(isVisited[child.num] == 0) {

					getNextUnlockedKeys(child);
				}
			}

			if(node instanceof Key && isVisited[((Key)node).mappedDoor] == 5) {

				getNextUnlockedKeys(nodes[((Key)node).mappedDoor]);
			}
		}

		public boolean checkPossibility() {

			boolean ans = true;

			getNextUnlockedKeys(nodes[1]);

			for(int i = 1; i <= N; i++) {

				if(nodes[i] instanceof Door && isVisited[i] != 1) {

					ans = false;
					break;
				}
			}

			return (ans && isVisited[N] == 1);
		}

	}

	public static class Door extends Node {

		public int mappedKey;

		public Door(int mappedKey, int num) {

			super(num);
			this.mappedKey = mappedKey;
		}
	}

	public static class Key extends Node {

		public int mappedDoor;
		public Key(int mappedDoor, int num) {

			super(num);
			this.mappedDoor = mappedDoor;
		}
	}

	public static class Empty extends Node {

		public Empty(int num) {
			super(num);
		}
	}

	public static class Node {

		public int num;
		public List<Node> children;

		public Node(int num) {

			this.num = num;
			children = new ArrayList<>();
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while(true) {

			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();

			if(N == -1) break;

			Map<Integer, Integer> nodeTypeMapping = new HashMap<>();
			Map<Integer, List<Integer>> edges = new HashMap<>();

			for(int i = 0; i < K; i++) {

				int key = sc.nextInt();
				int door = sc.nextInt();

				nodeTypeMapping.put(key, door);
			}

			for(int i = 0; i < M; i++) {

				int start = sc.nextInt();
				int end = sc.nextInt();

				if(!edges.containsKey(start)) {
					edges.put(start, new ArrayList<>());
				}

				edges.get(start).add(end);
			}

			Graph graph = new Graph(nodeTypeMapping, edges, N, K);

			if(graph.checkPossibility()) {

				System.out.println("Y");
			} else {
				System.out.println("N");
			}

		}

	}
}