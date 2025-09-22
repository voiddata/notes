[leetcode - 410](https://leetcode.com/problems/split-array-largest-sum/)

## Approach 1:

```java
public int splitArray(int[] nums, int m) {
    int low = 0;
    int high = 0;
    for(int num : nums) {
        low = Math.max(num, low);
        high += num;
    }
    while(low <= high) {
        int mid = (low+high) / 2;
        
        if(chunks(nums, mid) <= m) {
            high = mid-1;
        } else {
            low = mid + 1;
        }
    }
    return low;
}

private static int chunks(int[] nums, int mid) {
    int sum = 0;
    int count = 0;
    
    for(int i=0; i<nums.length; i++) {
        if(sum + nums[i] <= mid) {
            sum += nums[i];
        } else {
            count++;
            sum = nums[i];
        }
    }
    return count+1;
}
```