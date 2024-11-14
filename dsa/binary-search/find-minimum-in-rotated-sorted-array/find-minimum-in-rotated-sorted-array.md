[leetcode - 153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)

## **This is a special case problem**

## Approach 1:

```java
public int findMin(int[] nums) {
    int low = 0;
    int high = nums.length-1;

    while(low < high) {
        int mid = (low+high)/2;
                
        if(nums[high] < nums[mid]) {
            low = mid+1;
        } else {
            high = mid;
        }
    }
    return nums[low];
}
```