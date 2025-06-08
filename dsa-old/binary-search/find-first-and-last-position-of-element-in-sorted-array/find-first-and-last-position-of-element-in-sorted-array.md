[leetcode - 34](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

## Approach 1:

```java
public int[] searchRange(int[] nums, int target) {    
    if(nums.length == 0)
        return new int[] {-1, -1};
    int a = -1;
    int b = -1;

    int low = 0;
    int high = nums.length;
    while(low < high) {
        int mid = (low+high) / 2;
        
        if(nums[mid] >= target) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    a = low;

    low = 0;
    high = nums.length;
    while(low < high) {
        int mid = (low+high) / 2;
        
        if(nums[mid] <= target) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    b = low-1;
    
    if(a > b)
        return new int[] {-1,-1};
    
    return new int[] {a,b};
}
```

## Approach 2:

```java
public int[] searchRange(int[] nums, int target) {
    if(nums.length == 0)
        return new int[] {-1, -1};
    int a = -1;
    int b = -1;

    int low = 0;
    int high = nums.length-1;
    while(low <= high) {
        int mid = (low+high) / 2;
        
        if(nums[mid] >= target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    a = low;

    low = 0;
    high = nums.length-1;
    while(low <= high) {
        int mid = (low+high) / 2;
        
        if(nums[mid] <= target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    b = high;
    
    if(a > b)
        return new int[] {-1,-1};
    
    return new int[] {a,b};
}
```

## Approach 3:

```java
public int[] searchRange(int[] nums, int target) {
    int a = search(nums, target, true);
    int b = search(nums,target, false);
    
    return new int[] {a,b};
}

public int search(int[] nums, int target, boolean firstOrLast) {
    int low = 0;
    int high = nums.length-1;
    int ans = -1;

    while(low <= high) {
        int mid = (low+high)/2;
        
        if(nums[mid] < target) {
            low = mid + 1;
        } else if(nums[mid] > target) {
            high = mid - 1;
        } else {
            ans = mid;
            if(firstOrLast) {
                high = mid - 1;
            } else {
                low = mid + 1;
            } 
        }
    }
    return ans;
}
```

## Approach 4:

```java
public int[] searchRange(int[] nums, int target) {    
    int a = search(nums, target);
    int b = search(nums,target+1)-1;
    if(a > b) {
        return new int[] {-1, -1};
    }
    return new int[] {a,b};
}

public int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length-1;
    
    while(low <= high) {
        int mid = (low+high)/2;
        
        if(nums[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
}
```