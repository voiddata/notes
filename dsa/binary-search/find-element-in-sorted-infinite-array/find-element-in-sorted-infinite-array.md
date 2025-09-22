[geeksforgeeks](https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/)

[leetcode](https://leetcode.com/discuss/interview-experience/1979273/infinite-sorted-array)

## Approach 1:

```java
int findPos(int arr[], int key)
{
    int l = 0, h = 1;

    while (arr[h] < key)
    {
        l = h;
        h = 2*h;
        val = arr[h];
    }
    return binarySearch(arr, l, h, key);
}
```

The above function assumes that the `arr` is infinite and hence we cannot use `arr.length` to find the end index.

Hence we exponentially find the `l` and `h` value until `arr[h]` is greater than or equal to `key`.

Once the `l` and `h` is found we can use binary search to find the target element.

