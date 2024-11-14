[leetcode - 4](https://leetcode.com/problems/median-of-two-sorted-arrays/)

## Approach 1:

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if(nums1.length > nums2.length) {
        return findMedianSortedArrays(nums2, nums1);
    }
    int total = nums1.length+nums2.length;
    int median = (total+1) / 2;
    
    int p1 = 0;
    int l1 = 0, l2 = 0, r1 = 0, r2 = 0;
    while(p1 <= nums1.length) {
        int p2 = median - p1;
        
        l1 = (p1 == 0) ? Integer.MIN_VALUE : nums1[p1-1];
        l2 = (p2 == 0) ? Integer.MIN_VALUE : nums2[p2-1];
        
        r1 = (p1 == nums1.length) ? Integer.MAX_VALUE : nums1[p1];
        r2 = (p2 == nums2.length) ? Integer.MAX_VALUE : nums2[p2];
        
        if(l1 <= r2 && l2 <= r1) {
            break;
        } else if(l1 > r2) {
            p1--;
        } else {
            p1++;
        }
    }
    if(total % 2 == 0) {
        return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
    }
    return Math.max(l1, l2);
}
```

## Approach 2:

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {    
    if(nums1.length > nums2.length) {
        return findMedianSortedArrays(nums2, nums1);
    }
    int total = nums1.length+nums2.length;
    int median = (total+1) / 2;
    
    int low = 0, high = nums1.length;
    int l1 = 0, l2 = 0, r1 = 0, r2 = 0;
    while(low <= high) {
        int p1 = (low+high) / 2;
        int p2 = median - p1;
        
        l1 = (p1 == 0) ? Integer.MIN_VALUE : nums1[p1-1];
        l2 = (p2 == 0) ? Integer.MIN_VALUE : nums2[p2-1];
        
        r1 = (p1 == nums1.length) ? Integer.MAX_VALUE : nums1[p1];
        r2 = (p2 == nums2.length) ? Integer.MAX_VALUE : nums2[p2];
        
        if(l1 <= r2 && l2 <= r1) {
            break;
        } else if(l1 > r2) {
            high = p1 - 1;
        } else {
            low = p1 + 1;
        }
    }
    if(total % 2 == 0) {
        return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
    }
    return Math.max(l1, l2);
}
```