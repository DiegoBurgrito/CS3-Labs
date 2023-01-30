import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author You
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private final TrieNode root;
    private int size;


    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
        size = 0;
    }


    /**
     * Insert a word into the trie.
     * You should ignore the word's case.
     * That is, you should convert the string to all lower case as you insert it.
     */
    public boolean addWord(String s) {
        if (s != null && !s.equals("") && !isWord(s)) {
            String word = s.toLowerCase();
            TrieNode node = root;
            int i = 0;
            while (i < word.length()) {
                if (!node.getValidNextCharacters().contains(word.charAt(i))) {
                    node = node.insert(word.charAt(i));
                } else if (node.getValidNextCharacters().contains(word.charAt(i))) {
                    node = node.getChild(word.charAt(i));
                }
                i++;
            }
            node.setEndsWord(true);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Return the number of words in the dictionary.  This is NOT necessarily the same
     * as the number of TrieNodes in the trie.
     */
    public int size() {
        return size;
    }


    /**
     * Returns whether the string is a word in the trie
     */
    @Override
    public boolean isWord(String s) {
        if (s != null && !s.equals("")) {
            int i = 0;
            String word = s.toLowerCase();
            TrieNode node = root;
            while (i < s.length()) {
                if (node.getValidNextCharacters().contains(word.charAt(i))) {
                    node = node.getChild(word.charAt(i));
                } else {
                    return false;
                }
                i++;
            }
            return node.endsWord();
        }
        return false;
    }

    /**
     * * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     *
     * @param text The text to use at the word stem
     * @param n    The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */
    @Override
    public List<String> predictCompletions(String text, int n) {
        // This method should implement the following algorithm:
        // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
        //    empty list
        // 2. Once the stem is found, perform a breadth first search to generate completions
        //    using the following algorithm:
        //    Create a queue (LinkedList) and add the node that completes the stem to the back
        //       of the list.
        //    Create a list of completions to return (initially empty)
        //    While the queue is not empty and you don't have enough completions:
        //       remove the first Node from the queue
        //       If it is a word, add it to the completions list
        //       Add all of its child nodes to the back of the queue
        // Return the list of completions

        if (findStem(text) != null) {
            TrieNode stem = findStem(text);
            LinkedList<TrieNode> queue = new LinkedList<>();
            List<String> completions = new ArrayList<>();
            queue.add(stem);
            while (!queue.isEmpty() && completions.size() < n) {
                TrieNode node = queue.poll();
                if (node.endsWord()) {
                    completions.add(node.getText());

                }
                for (Character c : node.getValidNextCharacters()) {
                    queue.add(node.getChild(c));
                }
            }
            return completions;
        }

        return new ArrayList<>();
    }

    private TrieNode findStem(String text) {
        if (text.equals("")) {
            return root;
        }

        String word = text.toLowerCase();
        //checks if first letter is in the trie
        if (!root.getValidNextCharacters().contains(word.charAt(0))) {
            return null;
        }

        TrieNode node = root;
        int i = 0;

        //checks if the rest of the letters are in the trie
        while (i < text.length()) {
            if (node.getValidNextCharacters().contains(word.charAt(i))) {
                node = node.getChild(word.charAt(i));
                i++;
            } else {
                break;
            }
        }
        return node;
    }

    // For debugging
    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null) return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }


}