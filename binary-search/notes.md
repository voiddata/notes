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


## Alternative Approach:

Given an array, find the boundary where a certain condition or property holds.

```java
// F F F T T T T

int binarySearch(int[] arr) {
    int low = 0;
    int high = arr.length;

    while(low < high) {
        int mid = low + (high - low) / 2;

        if(property(mid)) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    return low;
}
```

The above function is used to find the first `T` based on the `property` function. `high` stands on one part of the boundary where as `low` crosses the boundary.

**Input :** 
- array  

**Output :**  
- position of boundary if present