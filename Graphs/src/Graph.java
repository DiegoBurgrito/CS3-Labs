//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.TreeMap;

public class Graph {
    private TreeMap<String, String> map;
    private boolean yahOrNay;

    public Graph(String line) {
		map = new TreeMap<String, String>();
		for (String s : line.split(" ")) {
            add(s.charAt(0) + "", s.charAt(1) + "");
            add(s.charAt(1) + "", s.charAt(0) + "");
        }
        yahOrNay = false;
    }

    private void add(String first, String second){
        if(!map.containsKey(first)){
            map.put(first, second);
        } else {
            map.put(first, map.get(first) + second);
        }
    }

    public boolean contains(String letter) {
        return map.containsKey(letter);
    }

    public void check(String first, String second, String placesUsed) {
        if(!map.containsKey(first) || !map.containsKey(second)){
            return;
        }
        if (map.get(first).contains(second)) {
            yahOrNay = true;
        } else {
            for (int i = 0; i < map.get(first).length(); i++) {
                if (!placesUsed.contains(map.get(first).charAt(i) + "")) {
                    check(map.get(first).charAt(i) + "", second, placesUsed + map.get(first).charAt(i));
                }
            }
        }
    }

    public String toString() {
        return yahOrNay ? "yes" : "no";
    }
}