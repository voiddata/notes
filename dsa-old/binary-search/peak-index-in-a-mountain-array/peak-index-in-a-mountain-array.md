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

## Approach 2:

The below approach uses java functional interface `BiFunction<T, U, R>`.

```java
public int peakIndexInMountainArray(int[] arr) {    
    BiFunction<List<Integer>, Integer, Boolean> func = (a, x) -> a.get(x) < a.get(x+1);
    return search(arr, func);
}

private static int search(int[] arr, BiFunction<List<Integer>, Integer, Boolean> func) {
    int low = 0;
    int high = arr.length-1;
    
    List<Integer> list = Arrays.stream(arr)
                            .boxed()
                            .collect(Collectors.toList());
    
    while(low <= high) {
        int mid = (low+high)/2;
        
        if(func.apply(list, mid)) {
            low = mid+1;
        } else {
            high = mid-1;
        }
    }
    return low;
}
```