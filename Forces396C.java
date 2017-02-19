import java.util.*;

public class Forces396C {

	public static int MODULO_INT = 1000000007;

	public static boolean canSubstringBeValid(String substr, int[] maxList) {

		int len = substr.length();
		for(int i = 0; i < len; i++) {

			if(len > maxList[substr.charAt(i) - 'a']) return false;
		}

		return true;
	}

	public static void processString(String message, int len, int[] maxList) {

		long[] dparray = new long[1002];
		int[] maxSubstringLen = new int[1002];
		int[] minSubstrings = new int[1002];

		dparray[len] = 1;minSubstrings[len] = 0;
		maxSubstringLen[len] = 0;

		for(int index = len - 1; index >= 0; index--) {

			dparray[index] = 0L;
			maxSubstringLen[index] = 0;
			minSubstrings[index] = 100000;

			for(int subindex = index + 1; subindex <= len; subindex++) {

				if(canSubstringBeValid(message.substring(index, subindex), maxList)) {

					dparray[index] = ((dparray[index] + dparray[subindex]) % MODULO_INT);
					maxSubstringLen[index] = Math.max(maxSubstringLen[index], (subindex - index));
					maxSubstringLen[index] = Math.max(maxSubstringLen[index], maxSubstringLen[subindex]);
					minSubstrings[index] = Math.min(minSubstrings[index], 1 + minSubstrings[subindex]);
				}
			}
		}

		System.out.println(dparray[0]);
		System.out.println(maxSubstringLen[0]);
		System.out.println(minSubstrings[0]);
	}


	public static void main(String[] args) throws Exception {

		int[] maxList = new int[26];

		Scanner sc = new Scanner(System.in);

		int len = sc.nextInt();
		String message = sc.next();

		for(int i = 0; i < 26; i++) {

			maxList[i] = sc.nextInt();
		}

		processString(message, len, maxList);

	}
}