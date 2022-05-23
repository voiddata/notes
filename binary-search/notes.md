# Binary Search

Used to find an element in $O(\log{n})$.  

## Standard Approach:  

Given a target **n** and an array, find whether **n** is present in the array or not.

```java
int binarySearch(int target, int[] arr) {

    int low = 0;
    int high = arr.length - 1;

    while(low <= high) {
        int mid = low + (high - low) / 2;

        if(arr[mid] == target) {
            return mid;
        } else if(arr[mid] > target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return -1;
}
```

**Input :** 
- target  
- array  

**Output :**  
- position of target if present  
- -1 if target not present


