[leetcode - 1095](https://leetcode.com/problems/find-in-mountain-array/submissions/)

```java
interface Property<A, B, C, D> {
    D check(A arr, B mid, C target);
}
class Solution {
    public int findInMountainArray(int target, MountainArray arr) {
        Property<MountainArray, Integer, Integer, Boolean> getPeak 
            = (a, x, key) -> a.get(x) < a.get(x+1);
        int peak = search(arr, getPeak, target, 0, arr.length()-1);
        
        Property<MountainArray, Integer, Integer, Boolean> findFirst 
            = (a, x, key) -> a.get(x) < key;
        int ans = search(arr, findFirst, target, 0, peak);
        
        if(arr.get(ans) == target) {
            return ans;
        }
        
        Property<MountainArray, Integer, Integer, Boolean> findSecond 
            = (a, x, key) -> a.get(x) > key;
        ans = search(arr, findSecond, target, peak+1, arr.length()-1);
        
        if(ans != -1 && arr.get(ans) == target) {
            return ans;
        }
        return -1;
    }
    
    private static int search(MountainArray arr, Property<MountainArray, Integer, Integer, Boolean> func, int target, int low, int high) {
        while(low <= high) {
            int mid = (low+high)/2;
            
            if(func.check(arr, mid, target)) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        if(low == arr.length())
            return -1;
        return low;
    }
}
```