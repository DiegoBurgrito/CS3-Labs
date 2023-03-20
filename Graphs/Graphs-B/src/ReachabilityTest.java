/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryce
 */
public class ReachabilityTest {

    ArrayList<Integer>[] adj;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(name));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            adj = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                int x, y;
                x = scanner.nextInt();
                y = scanner.nextInt();
                adj[x].add(y);
                adj[y].add(x);
            }
            //System.out.println(Reachability.reach(adj, x, y));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReachabilityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testTiny() {
        loadFile("tinyG.txt");
        assertEquals(1, Reachability.reach(adj, 0, 4));
        assertEquals(1, Reachability.reach(adj, 2, 6));
        assertEquals(1, Reachability.reach(adj, 3, 5));
        assertEquals(1, Reachability.reach(adj, 7, 8));
        assertEquals(1, Reachability.reach(adj, 9, 12));
        assertEquals(1, Reachability.reach(adj, 10, 11));
        assertEquals(0, Reachability.reach(adj, 0, 9));
        assertEquals(0, Reachability.reach(adj, 2, 7));
        assertEquals(0, Reachability.reach(adj, 8, 11));
        assertEquals(0, Reachability.reach(adj, 4, 12));
        assertEquals(0, Reachability.reach(adj, 8, 5));
    }

    @Test
    public void testMedium() {
        loadFile("mediumG.txt");
        assertEquals(0, Reachability.reach(adj, 0, 250));
        assertEquals(0, Reachability.reach(adj, 10, 251));
        assertEquals(0, Reachability.reach(adj, 33, 252));
        assertEquals(0, Reachability.reach(adj, 156, 253));
        assertEquals(1, Reachability.reach(adj, 0, 249));
        assertEquals(1, Reachability.reach(adj, 1, 245));
        assertEquals(1, Reachability.reach(adj, 0, 242));
        assertEquals(1, Reachability.reach(adj, 0, 221));
        assertEquals(1, Reachability.reach(adj, 0, 200));
        assertEquals(1, Reachability.reach(adj, 123, 201));
        assertEquals(1, Reachability.reach(adj, 3, 25));
        assertEquals(1, Reachability.reach(adj, 62, 91));
        assertEquals(1, Reachability.reach(adj, 4, 25));
        assertEquals(1, Reachability.reach(adj, 10, 50));
        assertEquals(1, Reachability.reach(adj, 44, 150));
        assertEquals(1, Reachability.reach(adj, 77, 88));
    }

    @Test
    public void testLarge() {
        loadFile("largeG.txt");
        assertEquals(1, Reachability.reach(adj, 0, 999999));
        assertEquals(1, Reachability.reach(adj, 0, 25044));
        assertEquals(1, Reachability.reach(adj, 1022, 26251));
        assertEquals(1, Reachability.reach(adj, 33333, 25662));
        assertEquals(1, Reachability.reach(adj, 156666, 253111));
        assertEquals(1, Reachability.reach(adj, 964440, 249));
        assertEquals(1, Reachability.reach(adj, 345, 245445));
        assertEquals(1, Reachability.reach(adj, 454, 242));
        assertEquals(1, Reachability.reach(adj, 33330, 25421));
        assertEquals(1, Reachability.reach(adj, 35440, 345200));
        assertEquals(1, Reachability.reach(adj, 123, 985201));
        assertEquals(1, Reachability.reach(adj, 344, 33325));
        assertEquals(1, Reachability.reach(adj, 11162, 11491));
        assertEquals(1, Reachability.reach(adj, 144, 45425));
        assertEquals(1, Reachability.reach(adj, 2210, 5330));
        assertEquals(1, Reachability.reach(adj, 4422, 43150));
        assertEquals(1, Reachability.reach(adj, 7722, 883));
    }
}
