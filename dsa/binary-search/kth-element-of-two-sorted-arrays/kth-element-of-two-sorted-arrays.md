[gfg](https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1#)

## Approach 1:

```java
public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
    if(n > m) {
        return kthElement(arr2, arr1, m, n, k);
    }
    int p1 = 0;
    int l1=0, l2=0, r1=0, r2=0;
    while(p1 <= n) {
        if((k - p1) > m) {
            p1++;
            continue;
        }
        int p2 = k - p1;
        
        l1 = (p1 == 0) ? Integer.MIN_VALUE : arr1[p1-1];
        l2 = (p2 == 0) ? Integer.MIN_VALUE : arr2[p2-1];
        
        r1 = (p1 == arr1.length) ? Integer.MAX_VALUE : arr1[p1];
        r2 = (p2 == arr2.length) ? Integer.MAX_VALUE : arr2[p2];
        
        if(l1 <= r2 && l2 <= r1) {
            break;
        } else if(l1 > r2) {
            p1--;
        } else {
            p1++;
        }
    }
    return Math.max(l1, l2);
}
```

## Approach 2:

```java
public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
    if(n > m) {
        return kthElement(arr2, arr1, m, n, k);
    }
    int low = Math.max(0, k-m);
    int high = Math.min(k,n);
    
    int l1=0, l2=0, r1=0, r2=0;
    while(low <= high) {
        int p1 = (low+high) / 2;
        int p2 = k - p1;
        
        l1 = (p1 == 0) ? Integer.MIN_VALUE : arr1[p1-1];
        l2 = (p2 == 0) ? Integer.MIN_VALUE : arr2[p2-1];
        
        r1 = (p1 == n) ? Integer.MAX_VALUE : arr1[p1];
        r2 = (p2 == m) ? Integer.MAX_VALUE : arr2[p2];
        
        if(l1 <= r2 && l2 <= r1) {
            break;
        } else if(l1 > r2) {
            high = p1 - 1;
        } else {
            low = p1 + 1;
        }
    }
    return Math.max(l1, l2);
}
```