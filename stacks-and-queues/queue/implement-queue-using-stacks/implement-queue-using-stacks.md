[leetcode - 232](https://leetcode.com/problems/implement-queue-using-stacks/)

## Approach 1

```java
class MyQueue {
    private Deque<Integer> s1;
    private Deque<Integer> s2;

    public MyQueue() {
        s1 = new ArrayDeque<Integer>();
        s2 = new ArrayDeque<Integer>();
    }
    
    public void push(int x) {
        if(s1.isEmpty()) {
            s1.push(x);
        } else {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(x);
            while(!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
    }
    
    public int pop() {
        return s1.pop();
    }
    
    public int peek() {
        return s1.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty();
    }
}
```