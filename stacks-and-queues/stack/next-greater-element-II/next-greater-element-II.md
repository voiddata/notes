[leetcode - 503](https://leetcode.com/problems/next-greater-element-ii/)

## Approach 1:

```java
public int[] nextGreaterElements(int[] nums) {
    Deque<Integer> stack = new ArrayDeque<>();
    int[] nge = new int[nums.length];
    Arrays.fill(nge, -1);
    
    for(int i=2*nums.length-1; i>=0; i--) {        
        while(!stack.isEmpty() && stack.peek() <= nums[i%nums.length]) {
            stack.pop();
        }
        if(i<nums.length && !stack.isEmpty()) {
            nge[i] = stack.peek();
        }
        stack.push(nums[i%nums.length]);
    }
    return nge;
}
```