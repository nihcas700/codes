import java.util.*;

public class Main {

	public static int giveLastAffectedIndex(int start, int end, Bomb[] bombs, int impactTill, int baseIndex) {

		if(start > end) {

			return Integer.MAX_VALUE;
		}

		if(start == end) {

			if(bombs[start].location >= impactTill) {
				return start;
			}

			return (baseIndex);
		}

		int mid = start + ((end - start) / 2);

		if(bombs[mid].location >= impactTill) {

			return giveLastAffectedIndex(start, mid, bombs, impactTill, baseIndex);
		} else {

			return giveLastAffectedIndex(mid+1, end, bombs, impactTill, baseIndex);
		}
	}

	public static class Bomb{

		public int location;
		public int strength;
	}

	public static class BombComparator implements Comparator<Bomb> {

    	@Override
   		public int compare(Bomb a, Bomb b) {
        	return (a.location < b.location) ? -1 : 1;
    	}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		Bomb[] bombs = new Bomb[N];
		int[] cumLosses = new int[N];
		int answer = Integer.MAX_VALUE;

		for(int i = 0; i < N; i++) {

			bombs[i] = new Bomb();
			bombs[i].location = sc.nextInt();
			bombs[i].strength = sc.nextInt();
		}

		Collections.sort(Arrays.asList(bombs), new BombComparator());

		for(int i = 0; i < N; i++) {

			int lossIndex = giveLastAffectedIndex(0, i-1, bombs, bombs[i].location - bombs[i].strength, i);

			// System.out.println(lossIndex);
			int nextExplodeIndex = lossIndex - 1;

			if(i > 0) {

				if(nextExplodeIndex >= 0) {
					cumLosses[i] = cumLosses[nextExplodeIndex] + i - nextExplodeIndex - 1;
				} else {
					cumLosses[i] = i;
				}
			} else {
				cumLosses[0] = 0;
			}

			// System.out.println(cumLosses[i]);

			answer = Math.min(answer, ((N - 1 - i) + cumLosses[i]));
		}

		System.out.println(answer);

	}
}

