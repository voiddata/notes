[leetcode - 496](https://leetcode.com/problems/next-greater-element-i/)

## Approach 1:

```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {   
    Deque<Integer> stack = new ArrayDeque<>();
    Map<Integer, Integer> mp = new LinkedHashMap<>();
    
    for(int n : nums1) {
        mp.put(n, -1);
    }
    for(int i=nums2.length-1; i>=0; i--) {        
        while(!stack.isEmpty() && stack.peek() < nums2[i]) {
            stack.pop();
        }
        if(mp.containsKey(nums2[i]) && !stack.isEmpty()) {
            mp.put(nums2[i], stack.peek());
        }
        stack.push(nums2[i]);
    }
    return mp.values().stream().mapToInt(Integer::intValue).toArray();
}
```