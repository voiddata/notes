[leetcode - 540](https://leetcode.com/problems/single-element-in-a-sorted-array/)

## Approach 1:

```java
public int singleNonDuplicate(int[] nums) {
    if(nums.length == 1){
        return nums[0];
    }
    int low = 0;
    int high = nums.length-2;

    while(low <= high) {        
        int mid = (low+high)/2;
        
        if(mid % 2 == 0) {            
            if(nums[mid] == nums[mid+1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }            
        } else {
            if(nums[mid] == nums[mid+1]) {
                high = mid - 1;                
            } else {
                low = mid+1;
            }
        }       
    }
    return nums[low];
}
```

## Approach 2:

```java
public int singleNonDuplicate(int[] nums) {
    if(nums.length == 1){
        return nums[0];
    }
    int low = 0;
    int high = nums.length-2;
    
    while(low <= high) {
        int mid = (low+high)/2;
        
        if(nums[mid] == nums[mid+1]) {
            if(mid % 2 == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } else {
            if(mid % 2 == 0) {
                high = mid - 1;
            } else {
                low = mid+1;
            }
        }
    }
    return nums[low];
}
```

We can first check whether the index is odd or even / we can check whether the `nums[mid] == nums[mid+1]`