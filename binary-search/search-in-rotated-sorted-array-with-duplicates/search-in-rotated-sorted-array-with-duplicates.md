[leetcode - 81](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)

## Approach 1:

```java
public boolean search(int[] nums, int target) {
    int low = 0;
    int high = nums.length-1;
    while(low <= high) {
        int mid = (low+high) / 2;

        if(nums[mid] == target) {
            return true;  
        } else if(nums[mid] == nums[low] && nums[mid] == nums[high]) {
            low++;
            high--;
        } else if(nums[mid] <= nums[high]) {
            if(target > nums[mid] && target <= nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } else {
            if(target >= nums[low] && target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }
    return false;
}
```