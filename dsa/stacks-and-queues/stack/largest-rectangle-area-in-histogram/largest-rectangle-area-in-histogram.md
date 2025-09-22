[leetcode - 84](https://leetcode.com/problems/largest-rectangle-in-histogram/)

## Approach 1:

Time = $O(3n)$  
Space = $O(3n)$

```java
public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    
    int[] left = new int[n];
    int[] right = new int[n];
    
    Stack<Integer> stack = new Stack<>();
    for(int i=n-1; i>=0; i--) {
        while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
            stack.pop();
        }
        if(!stack.isEmpty()) {
            right[i] = stack.peek()-1;
        } else {
            right[i] = n-1;
        }
        stack.push(i);
    }    
    stack.clear();

    for(int i=0; i<n; i++) {
        while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
            stack.pop();
        }
        if(!stack.isEmpty()) {
            left[i] = stack.peek()+1;
        } else {
            left[i] = 0;
        }
        stack.push(i);
    }
    
    int ans = 0;
    for(int i=0; i<n; i++) {
        int max = (right[i] - left[i] + 1) * heights[i];
        
        if(ans < max)
            ans = max;   
    }
    return ans;
}
```

## Approach 2:

Time = $O(2n)$  
Space = $O(n)$

```java
public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    Stack<Integer> stack = new Stack<>();
    int ans = 0;
    int max = 0;

    for(int i=0; i<=n; i++) {
        while(!stack.isEmpty() && (i==n || heights[stack.peek()] >= heights[i])) {
            int index = stack.pop();
            
            if(!stack.isEmpty()) {
                max = Math.max((i-stack.peek()-1) * heights[index], max);
            } else {
                max = Math.max(i * heights[index], max);
            }
        }
        stack.push(i);
        if(ans < max) {
            ans = max;
        }   
    }   
    return ans;
}
```