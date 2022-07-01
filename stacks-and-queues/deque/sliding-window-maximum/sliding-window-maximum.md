[leetcode - 239](https://leetcode.com/problems/sliding-window-maximum/)

## Approach 1:

Time = $O(n^{2})$  
Space = $Constant$

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    int ansLength = 0;
    
    if(nums.length == k) {
        ansLength = 1;    
    } else {
        ansLength = nums.length-k+1;
    }
    
    int[] ans = new int[ansLength];
    int a = 0;
    for(int i=0; i<ansLength; i++) {
        int max = Integer.MIN_VALUE;
        for(int j=i; j<i+k; j++) {
            if(max < nums[j]) {
                max = nums[j];
            }
        }        
        ans[i] = max;
    }   
    return ans;
}
```

## Approach 2:

Time = $O(n)$  
Space = $O(k)$

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    int ansLength = 0;
    if(nums.length == k) {
        ansLength = 1;    
    } else {
        ansLength = nums.length-k+1;
    }

    int[] ans = new int[ansLength];
    Deque<Integer> queue = new ArrayDeque<>();
    int index = 0;

    for(int i=0; i<nums.length; i++) {
        if(!queue.isEmpty() && queue.getFirst() <= i-k) {
            queue.removeFirst();
        }
        while(!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
            queue.removeLast();
        }
        queue.addLast(i);
        if(i >= k-1) {
            ans[index++] = nums[queue.getFirst()];
        }   
    }   
    return ans;
}
```