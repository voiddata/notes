[leetcode - 4](https://leetcode.com/problems/median-of-two-sorted-arrays/)

## Bruteforce approach:

merge the two arrays into one temp array and find the median

Time - $O(m + n)$  
Space - $O(m + n)$

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] ans = new int[nums1.length + nums2.length];
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            ans[k++] = nums1[i++];
        } else {
            ans[k++] = nums2[j++];
        }
    }

    while (i < nums1.length) {
        ans[k++] = nums1[i++];
    }

    while (j < nums2.length) {
        ans[k++] = nums2[j++];
    }

    if (ans.length % 2 == 0) {
        int mid = ans.length / 2;
        return (ans[mid] + ans[mid-1]) / 2.0;
    } else {
        int mid = ans.length / 2;
        return ans[mid];
    }
}
```

Thoughts:

Dont have to use a temporary array to store the merged result

Instead have a counter and stop till the mid


## Reducing Space:

Time - $O(m + n)$  
Space - $O(1)$

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int i = 0, j = 0;
    int count = 0;
    int ind1 = ((nums1.length + nums2.length) / 2) - 1, ind2 = ((nums1.length + nums2.length) / 2);
    double m1 = 0, m2 = 0;

    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            if (count == ind1) m1 = nums1[i];
            if (count == ind2) m2 = nums1[i];
            i++;count++;
        } else {
            if (count == ind1) m1 = nums2[j];
            if (count == ind2) m2 = nums2[j];
            j++;count++;
        }
    }

    while (i < nums1.length) {
        if (count == ind1) m1 = nums1[i];
        if (count == ind2) m2 = nums1[i];
        i++;count++;
    }

    while (j < nums2.length) {
        if (count == ind1) m1 = nums2[j];
        if (count == ind2) m2 = nums2[j];
        j++;count++;
    }

    if ((nums1.length + nums2.length) % 2 == 0)
        return (m1 + m2) / 2.0;
    else
        return m2;
}
```

## Reducing Time:

Instead of looping through the entire array, we can stop once we get the median 1 and median 2 reducing the time by half

**Still the time is linear**

Time - $O((m + n)/2)$  
Space - $O(1)$

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int i = 0, j = 0;
    int count = 0;
    int ind1 = ((nums1.length + nums2.length) / 2) - 1, ind2 = ((nums1.length + nums2.length) / 2);
    double m1 = 10000000, m2 = 10000000;

    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            if (count == ind1) m1 = nums1[i];
            if (count == ind2) {
                m2 = nums1[i];break;
            }
            i++;count++;
        } else {
            if (count == ind1) m1 = nums2[j];
            if (count == ind2) {
                m2 = nums2[j];break;
            }
            j++;count++;
        }
    }

    while (m2 == 10000000 && i < nums1.length) {
        if (count == ind1) m1 = nums1[i];
        if (count == ind2) {
            m2 = nums1[i];break;
        }
        i++;count++;
    }

    while (m2 == 10000000 && j < nums2.length) {
        if (count == ind1) m1 = nums2[j];
        if (count == ind2) {
            m2 = nums2[j];break;
        }
        j++;count++;
    }
    if ((nums1.length + nums2.length) % 2 == 0)
        return (m1 + m2) / 2.0;
    else
        return m2;
}
```


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