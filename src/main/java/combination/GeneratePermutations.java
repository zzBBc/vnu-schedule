package combination;

import java.util.ArrayList;
import java.util.List;

import json.Subject;

public class GeneratePermutations {
	public static void generatePermutation(List<ArrayList<Subject>> codePerTime, List<String> result, int depth, String string) {

		//lists.forEach(e -> System.out.println(e));

		if(depth == codePerTime.size()) {
			result.add(string);
			return;
		}

		int size = codePerTime.get(depth).size();
		for(int i = 0; i < size; i++)
			generatePermutation(codePerTime, result, depth + 1, string + "-" + codePerTime.get(depth).get(i).getCode());
	}
}
