[codingninjas](https://www.codingninjas.com/codestudio/problems/ayush-gives-ninjatest_1097574)

## Approach 1:

```java
public static long ayushGivesNinjatest(int n, int m, int[] time) {        
    long low = 0;
    long high = 0;
    
    for(int t : time) {
        low = Math.max(low, t);
        high += t;
    }
    while(low <= high) {   
        long mid = (low+high) / 2;
        
        if(check(mid, time) <= n) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return low;
}

private static int check(long mid, int[] time) {
    int count = 0;
    long sum = 0;
    
    for(int i=0; i<time.length; i++) {
        if(time[i] + sum <= mid) {
            sum += time[i];
        } else {
            count++;
            sum = time[i];
        }
    }
    return count+1;
}
```