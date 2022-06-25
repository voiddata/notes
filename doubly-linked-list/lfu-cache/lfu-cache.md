[leetcode - 460](https://leetcode.com/problems/lfu-cache/)

```java
class LFUCache {

    int[] arr;
    HashMap<Integer, Node> map;
    HashMap<Integer, Pair> freqMap;
    int capacity;
    int frequency;
    
    class Pair {
        Node head;
        Node tail;
        
        Pair(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    
    class Node {
        int key;
        int data;
        Node next;
        Node prev;
        int freq;
        
        Node(int key, int data) {
            this.key = key;
            this.data = data;
            this.next = null;
            this.prev = null;
            this.freq = 1;
        }
    }

    public LFUCache(int capacity) {
        
        map = new HashMap<>();
        freqMap = new HashMap<>();

        this.frequency = 1;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        
        int value = -1;
        
        if(map.containsKey(key)) {   
            Node node = delete(map.get(key));
            
            if(node.freq == frequency && (node.next.data == -1 && node.prev.data == -1))
                frequency++;
            
            int f = ++node.freq;
            
            if(freqMap.containsKey(f)) {
                Pair pair = freqMap.get(f);
                insert(node, pair.head);
            } else {
                Node head = new Node(-1, -1);
                Node tail = new Node(-1, -1);

                head.next = tail;
                tail.prev = head;

                Pair pair = new Pair(head, tail);
                
                insert(node, pair.head);
                freqMap.put(f, pair);
            }
            
            if(frequency > node.freq) {
                frequency = node.freq;
            }
            value = node.data;
        }
        return value;
    }
    
    public void put(int key, int value) {
        
        if(capacity <= 0)
            return;
        
        if(map.containsKey(key)) {
            Node node = delete(map.get(key));
            
            if(node.freq == frequency && (node.next.data == -1 && node.prev.data == -1))
                frequency++;
            
            int f = ++node.freq;
            
            if(freqMap.containsKey(f)) {
                Pair pair = freqMap.get(f);
                insert(node, pair.head);
            } else {
                Node head = new Node(-1, -1);
                Node tail = new Node(-1, -1);
                
                head.next = tail;
                tail.prev = head;

                Pair pair = new Pair(head, tail);
                
                insert(node, pair.head);
                freqMap.put(f, pair);
            }
            
            if(frequency > node.freq) {
                frequency = node.freq;
            }
            node.data = value;
            return;
        }
        
        if(map.size() == capacity) {
            
            Pair lfu = freqMap.get(frequency);
            Node node = delete(lfu.tail.prev);
            
            map.remove(node.key);            
        }
        
        Node node = new Node(key, value);

        if(freqMap.containsKey(node.freq)) {
            Pair pair = freqMap.get(node.freq);
            insert(node, pair.head);
        } else {
            Node head = new Node(-1, -1);
            Node tail = new Node(-1, -1);

            head.next = tail;
            tail.prev = head;

            Pair pair = new Pair(head, tail);

            insert(node, pair.head);
            freqMap.put(node.freq, pair);
        }
        
        if(frequency > node.freq) {
            frequency = node.freq;
        }
        map.put(key, node);
        
    }
    
    public void insert(Node node, Node head) {
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
        
        return node;
    }
}
```