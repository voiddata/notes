[leetcode - 528](https://leetcode.com/problems/random-pick-with-weight/)

## Approach 1:

```java
class Solution {
    private int[] w;
    private int[] sum;
    
    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        
        for(int i=1; i<w.length; i++) {
            sum[i] = sum[i-1] + w[i];
        }
        this.w = w;
    }
    
    public int pickIndex() {
        int s = new Random().nextInt(this.sum[sum.length-1]) + 1;
        
        int low = 0;
        int high = sum.length - 1;
        
        while(low <= high) {
            int mid = (low+high) / 2;
            
            if(sum[mid] >= s) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
```

we take cumulative(prefix) sum of weights in order to get the probability of selecting that element.  

consider [1,3,2] the cumulative array will look like [1,4,6].  

using the above cumulative array, we can say that if randomly I select a number between **1** and the **sum of all weights** say 3.  

then it lies in the range [2-4] which comes under index 1 which is 4 in cumulative array. hence we select index 1 in the original array which is 3.