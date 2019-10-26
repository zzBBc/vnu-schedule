package combination;

import java.util.ArrayList;

public class GeneratePermutations {
	public static void generatePermutation(ArrayList<ArrayList<String>> lists, ArrayList<String> result, int depth, String string) {
		
		//lists.forEach(e -> System.out.println(e));
		
		if(depth == lists.size()) {
			result.add(string);
			return;
		}
		
		int listsSize = lists.get(depth).size();
		for(int i = 0; i < listsSize; i++) {
			generatePermutation(lists, result, depth + 1, string + "-" + lists.get(depth).get(i));
		}
	}
}
