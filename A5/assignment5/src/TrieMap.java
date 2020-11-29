//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrieMap implements TrieMapInterface {

    TrieMapNode root;

    public TrieMap() {
        root = new TrieMapNode();
    }

    //Indirectly recursive method to meet definition of interface
    @Override
    public void put(String key, String value) {
        put(root, key.trim(), value.trim());
    }

    //Recursive method
    //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
    public void put(TrieMapNode current, String curKey, String value) {

        HashMap<Character, TrieMapNode> children = current.getChildren();
        char key = curKey.charAt(0);
        TrieMapNode node = new TrieMapNode();

        if (children.containsKey(key)) {
            node = children.get(key);
        } else {
            children.put(key, node);
        }

        if (curKey.length() == 1) {
            node.setValue(value);
            return;
        }
        put(node, curKey.substring(1), value);
    }

    //Indirectly recursive method to meet definition of interface
    @Override
    public String get(String key) {
        return get(root, key);
    }

    //Recursive method
    //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
    public String get(TrieMapNode current, String curKey) {
        HashMap<Character, TrieMapNode> children = current.getChildren();
        char key = curKey.charAt(0);

        if (curKey.length() == 1) {
            if (children.containsKey(key)) {
                TrieMapNode node = children.get(key);
                return node.getValue();
            }
        } else {
            if (children.containsKey(key)) {
                return get(children.get(key), curKey.substring(1));
            }
        }
        return "";
    }

    //Indirectly recursive method to meet definition of interface
    @Override
    public boolean containsKey(String key) {
        return containsKey(root, key);
    }

    //Recursive method
    //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
    public boolean containsKey(TrieMapNode current, String curKey) {

        HashMap<Character, TrieMapNode> children = current.getChildren();
        char key = curKey.charAt(0);

        if (curKey.length() == 1) {
            if (children.containsKey(key)) {
                TrieMapNode node = children.get(key);
                return node.getValue() != null;
            }
        } else {
            if (children.containsKey(key)) {
                return containsKey(children.get(key), curKey.substring(1));
            }
        }
        return false;

    }

    //Indirectly recursive method to meet definition of interface
    @Override
    public ArrayList<String> getKeysForPrefix(String prefix) {
        TrieMapNode findNode = findNode(root, prefix);
        return getSubtreeKeys(findNode);
    }

    //Recursive helper function to find node that matches a prefix
    //Note: only a suggestion, you may solve the problem is any recursive manner
    public TrieMapNode findNode(TrieMapNode current, String curKey) {

        HashMap<Character, TrieMapNode> children = current.getChildren();
        char key = curKey.charAt(0);

        if (curKey.length() == 1) {
            if (children.containsKey(key)) {
                TrieMapNode node = children.get(key);
                return node;
            }
        } else {
            if (children.containsKey(key)) {
                return findNode(children.get(key), curKey.substring(1));
            }
        }
        return null;
    }

    public ArrayList<String> getSubtreeKeys(TrieMapNode current) {
        ArrayList<String> keys = new ArrayList<>();
        if(current == null) {
            return keys;
        }
        
        if (current.getChildren().isEmpty()) {
            String value = current.getValue();
            if (value != null && !keys.contains(value)) {
                keys.add(current.getValue());
            }
        }
        getSubtreeKeys(current, keys);
        return keys;
    }

    //Recursive helper function to get all keys in a node's subtree
    //Note: only a suggestion, you may solve the problem is any recursive manner
    public void getSubtreeKeys(TrieMapNode current, ArrayList<String> list) {
        
        HashMap<Character, TrieMapNode> children = current.getChildren();
        for (Map.Entry<Character, TrieMapNode> entrySet : children.entrySet()) {
            TrieMapNode node = entrySet.getValue();
            getSubtreeKeys(node, list);
            if (node.getValue() != null) {
                list.add(node.getValue());
            }
        }

    }

    //Indirectly recursive method to meet definition of interface
    @Override
    public void print() {
        print(root);
    }

    //Recursive method to print values in tree
    public void print(TrieMapNode current) {
        HashMap<Character, TrieMapNode> children = current.getChildren();
        for (Map.Entry<Character, TrieMapNode> entrySet : children.entrySet()) {
            TrieMapNode node = entrySet.getValue();
            print(node);
            if (node.getValue() != null) {
                System.out.println(node.getValue());
            }
        }
    }

    public static void main(String[] args) {
        //You can add some code in here to test out your TrieMap initially
        //The TrieMapTester includes a more detailed test

        // create a tree map
        TrieMap map = new TrieMap();
        // define some words
        String[] words = new String[]{"abound", "abounds", "abundance", "abundant", "acclaim", "acclaimed"};

        // add words to the map
        for (String w : words) {
            map.put(w, w);
        }
       
        // print the map
        map.print();

        //
        System.out.println();
        System.out.println(map.containsKey("acclaim"));
        System.out.println(map.get("abundance"));
        System.out.println(map.findNode(map.root, "aboun").getValue());
        System.out.println(map.findNode(map.root, "abound").getValue());

    }
}
