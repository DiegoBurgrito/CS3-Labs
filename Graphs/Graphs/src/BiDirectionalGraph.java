//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.TreeMap;
import java.util.TreeSet;

public class BiDirectionalGraph {
    private TreeMap<String, TreeSet<String>> map;
    private boolean yahOrNay;

    public BiDirectionalGraph(String line) {
		map = new TreeMap<>();
		String[] arr = line.split(" ");
		for (int i = 0; i < arr.length; i += 2) {
			add(arr[i], arr[i + 1]);
			add(arr[i + 1], arr[i]);
		}
		yahOrNay = false;
    }
	
	private void add(String first, String second) {
		TreeSet<String> set;
		if(!map.containsKey(first)){
			set = new TreeSet<>();
		} else {
			set = map.get(first);
		}
		set.add(second);
		map.put(first, set);
	}

    public boolean contains(String name) {
        return map.containsKey(name);
    }

    public void check(String first, String second, TreeSet<String> placedUsed) {
		if(!map.containsKey(first) || !map.containsKey(second)){
			return;
		}
		if (map.get(first).contains(second)) {
			yahOrNay = true;
		} else {
			for (String name : map.get(first)) {
				if (!placedUsed.contains(name)) {
					placedUsed.add(name);
					check(name, second, placedUsed);
				}
			}
		}
    }

    public String toString() {
        return yahOrNay ? "YAH" : "NAH";
    }
}