import java.util.*;

public class Reachability {
    static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited= new HashSet<>();
        stack.push(x);
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (adj[curr].contains(y)) return 1;
            visited.add(curr);
            for (int num: adj[curr]) {
                if(!visited.contains(num)) {
                    visited.add(num);
                    stack.push(num);
                }

            }
        }
        if(adj[x].contains(y) || adj[y].contains(x)) {
            return 1;
        }



        return 0;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

