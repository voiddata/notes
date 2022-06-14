[codingninjas](https://www.codingninjas.com/codestudio/problems/sort-a-stack_985275)

## Approach 1 - using extra stack:

```java
public static void sortStack(Stack<Integer> stack) {
    Stack<Integer> s1 = new Stack<>();
    
    while(!stack.empty()) {
        if(s1.empty()) {
            s1.push(stack.pop());
        } else {
            int top = stack.pop();
            
            while(!s1.empty() && top > s1.peek()) {
                stack.push(s1.pop());
            }
            s1.push(top);
        }
    }
    while(!s1.empty()) {
        stack.push(s1.pop());
    }
}
```

## Approach 2 - using recursion:

```java
public static void sortStack(Stack<Integer> stack) {
    if(stack.empty()) {
        return;
    }
    int top = stack.pop();
    sortStack(stack);
    sortInsert(stack, top);
}

private static void sortInsert(Stack<Integer> stack, int top) {
    if(stack.empty()) {
        stack.push(top);
        return;
    }
    if(stack.peek() > top) {
        int t = stack.pop();
        sortInsert(stack, top);
        stack.push(t);
    } else {
        stack.push(top);
    }   
}
```