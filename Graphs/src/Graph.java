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
        yahOrNay = false;
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