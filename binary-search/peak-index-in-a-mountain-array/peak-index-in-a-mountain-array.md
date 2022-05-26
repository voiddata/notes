[leetcode - 852](https://leetcode.com/problems/peak-index-in-a-mountain-array/)

These kinds of arrays are also called as **Bitonic Arrays**.

## Approach 1:

```java
public int peakIndexInMountainArray(int[] arr) {
    int low = 0;
    int high = arr.length-1;
    
    while(low <= high) {
        int mid = (low+high)/2;
        
        if(arr[mid] < arr[mid+1]) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;   
}
```