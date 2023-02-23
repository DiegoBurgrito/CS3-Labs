import java.util.TreeMap;

public class ShortestPathGraph {
    private TreeMap<String, String> map;
    private boolean yayOrNay;
    private int shortest;

    public ShortestPathGraph(String line) {
		map = new TreeMap<String, String>();
		for (String s : line.split(" ")) {
			if(!map.containsKey(s.charAt(0) + "")){
				map.put(s.charAt(0) + "", s.substring(1));
			} else {
				map.put(s.charAt(0) + "", map.get(s.charAt(0) + "") + s.substring(1));
			}
			if(!map.containsKey(s.charAt(1) + "")){
				map.put(s.charAt(1) + "", s.substring(0,1));
			} else {
				map.put(s.charAt(1) + "", map.get(s.charAt(1) + "") + s.charAt(0));
			}
		}
		yayOrNay = false;
		shortest = 0;
    }

    public boolean contains(String letter) {
        return map.containsKey(letter);
    }

    public void check(String first, String second, String placesUsed, int steps) {
		if(!map.containsKey(first) || !map.containsKey(second)){
			return;
		}
		if (map.get(first).contains(second)) {
			yayOrNay = true;
			if (shortest == 0 || steps < shortest) {
				shortest = ++steps;
			}
		} else {
			for (int i = 0; i < map.get(first).length(); i++) {
				if (!placesUsed.contains(map.get(first).charAt(i) + "")) {
					check(map.get(first).charAt(i) + "", second, placesUsed + map.get(first).charAt(i), steps + 1);
				}
			}
		}
    }

    public String toString() {
        return yayOrNay ? "yes" + " in " + shortest + " steps" : "no";
    }
}