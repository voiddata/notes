[leetcode - 146](https://leetcode.com/problems/lru-cache/)

## Approach 1:

```java
class LRUCache {
    int[] arr;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    
    class Node {
        int key;
        int data;
        Node next;
        Node prev;
        
        Node(int key, int data) {
            this.key = key;
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        
        head.next = tail;
        tail.prev = head;
        
        this.capacity = capacity;
    }
    
    public int get(int key) {
        int value = -1;
        
        if(map.containsKey(key)) {
            Node node = delete(map.get(key));
            insert(node);
            value = node.data;
        }
        return value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = delete(map.get(key));
            map.remove(node.key);
        }    
        if(map.size() == capacity) {
            Node node = delete(tail.prev);
            map.remove(node.key);
        }
        Node node = new Node(key, value);
        insert(node);
        map.put(key, node);
    }
    
    public void insert(Node node) {
        Node next = head.next;
        
        node.prev = head;
        head.next = node;
        
        node.next = next;
        next.prev = node;
    }
    
    public Node delete(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
        
        node.next = null;
        node.prev = null;
        
        return node;
    }
}
```