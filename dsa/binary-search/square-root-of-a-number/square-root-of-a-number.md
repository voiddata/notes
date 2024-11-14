[square root of a number - leetcode](https://leetcode.com/problems/sqrtx/)

## Approach 1:

```java
public int mySqrt(int x) {
    double low = 1;
    double high = x;

    while((high - low) > 1e-6) {
        double mid = (low + high) / 2;
    
        if((long)(mid * mid) <= x) {
            low = mid;
        } else {
            high = mid;
        }
    }
    return (int) low;
}
```

In the above approach, we evaluate the difference between `low` and `high` to be more than $10^{-6} = 0.000001$.  
Once the difference is higher than $10^{-6}$, we can stop. The $\sqrt{x}$ is stored in `low`.

## Approach 2:

```java
public int mySqrt(int x) {
    double low = 1;
    double high = x;

    while((high - low) > 1e-6) {
        double mid = (low + high) / 2;
    
        if((long)(mid * mid) < x) {
            low = mid;
        } else if((long)(mid * mid) >= x) {
            high = mid;
        }
    }
    return (int) high;
}
```

In the above approach, we evaluate the difference between `low` and `high` to be more than $10^{-6} = 0.000001$.  
Once the difference is higher than $10^{-6}$, we can stop. The $\sqrt{x}$ is stored in `high`.

**From both [Approach 1](#approach-1) and [Approach 2](#approach-2), we can see that the $\sqrt{x}$ is stored either in `low` or `high` based on the condition `(long)(mid * mid) == x`.**

## Approach 3:

```java
public int mySqrt(int x) {
    double low = 1.0;
    double high = x;

    while(true) {
        double mid = (low + high) / 2;
        long sqr = (long) (mid * mid);

        if((int) sqr == x) {
            return (int) mid;
        } else if(sqr < x) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }   
    }
}
```

The above approach is somewhat faster than the approaches 1 and 2 because we update `low` and `high` by 1.  
But infinite loops are not recommended.