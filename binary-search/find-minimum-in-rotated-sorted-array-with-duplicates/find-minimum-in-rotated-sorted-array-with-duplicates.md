[leetcode - 154](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)

## Approach 1:

```java
public int findMin(int[] nums) {
    int low = 0;
    int high = nums.length-1;
    while(low < high) {
        int mid = (low+high)/2;
        
        if(nums[mid] < nums[high]) {
            high = mid;
        } else if(nums[mid] > nums[high]) {
            low = mid + 1;
        } else {
            high--;
        }       
    }
    return nums[low];
}
```

Here even if we have `low <= high`, it works because we are already found the position of minimum element.