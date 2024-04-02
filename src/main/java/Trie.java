/**
 * Implement a class Trie with insert(word), search(word), and startsWith(prefix) method.
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
public class Trie {
    Node root;

    class Node {
        boolean inDictionary;
        Node[] next;

        /**
         * Constructor for Trie Node
         */
        Node() {
            inDictionary = false;
            next = new Node[26];
        }
    } // end of class Node

    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     * @param word the string to be added
     */
    public void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = (int)ch - 'a';
            if (current.next[index] == null) {
                current.next[index] = new Node();
            }
            current = current.next[index];
        }
        current.inDictionary = true;
    }

    /**
     * Search for a word in the trie.
     * @param word the string to search for
     * @return true if word is in the trie, false otherwise
     */
    public boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = (int)ch - 'a';
            if (current.next[index] != null) {
                current = current.next[index];
            } else {
                return false;
            }
        }
        return current.inDictionary;
    }

    /**
     * Look for a prefix of a string that was inserted previously.
     * @param prefix the prefix to search
     * @return true if a word that starts with prefix is in the trie, false otherwise
     */
    public boolean startsWith(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = (int)ch - 'a';
            if (current.next[index] == null) {
                return false;
            } else {
                current = current.next[index];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        boolean s1 = trie.search("apple");
        boolean s2 = trie.search("app");
        boolean p1 = trie.startsWith("apple");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(p1);
    }
}
