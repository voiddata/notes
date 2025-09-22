[leetcode - 20](https://leetcode.com/problems/valid-parentheses/)

## Approach 1:

```java
public boolean isValid(String s) {
    if(s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}') {
        return false;
    }
    
    Deque<Character> stack = new ArrayDeque<>();
    int i = 0;
    
    while(i < s.length()) {
        char c = s.charAt(i);
        
        switch(c) {
            case '(':
            case '[':
            case '{':
                stack.push(c); break;
                
            case ')':
                if(stack.isEmpty() || stack.pop() != '(')
                    return false;
                break;
            case ']':
                if(stack.isEmpty() || stack.pop() != '[')
                    return false;
                break;
            case '}':
                if(stack.isEmpty() || stack.pop() != '{')
                    return false;
                break;
        }
        i++;
    }
    if(stack.isEmpty()) {
        return true;
    }
    return false;
}
```