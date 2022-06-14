[codingninjas](https://www.codingninjas.com/codestudio/problems/reverse-stack-using-recursion_631875)

## Approach 1 - using two extra stacks:

```java
public static void reverseStack(Stack<Integer> stack) {    
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> temp = new Stack<>();
    
    while(!stack.empty()) {
        if(s1.empty()) {
            s1.push(stack.pop());
        } else {
            int top = stack.pop();
            
            while(!s1.empty()) {
                temp.push(s1.pop());
            }
            s1.push(top);
            while (!temp.isEmpty()) {
                s1.push(temp.pop());
            }
        }
    }
    while (!s1.empty()) {
        stack.push(s1.pop());
    }
}
```

## Approach 2 - using recursion:

```java
public static void reverseStack(Stack<Integer> stack) {
    if(stack.isEmpty()) {
        return;
    }
    int top = stack.pop();
    reverseStack(stack);
    insertBottom(stack, top);
}

private static void insertBottom(Stack<Integer> stack, int top) {    
    if(stack.isEmpty()) {
        stack.push(top);
        return;
    }
    int bottom = stack.pop();
    insertBottom(stack, top);
    stack.push(bottom);
}
```