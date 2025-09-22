[leetcode - 225](https://leetcode.com/problems/implement-stack-using-queues/)

## Approach 1 - costly push operation:

```java
class MyStack {
    private Deque<Integer> q1;
    private Deque<Integer> q2;
    
    public MyStack() {
        q1 = new ArrayDeque<Integer>();
        q2 = new ArrayDeque<Integer>();
    }
    
    public void push(int x) {
        if(q1.isEmpty()) {
            q1.add(x);
        } else {
            q2.add(x);
            while(!q1.isEmpty()) {
                q2.add(q1.pop());
            }
            Deque<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }
    }
    
    public int pop() {
        return q1.pop();
    }
    
    public int top() {
        return q1.peek();
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}
```

## Approach 2 - only one queue

```java
public void push(int x) {
    if(q1.isEmpty()) {
        q1.add(x);
    } else {
        int size = q1.size();
        q1.push(x);
        
        while(size-- > 0) {
            q1.push(q1.pop());
        }
    }
}
```