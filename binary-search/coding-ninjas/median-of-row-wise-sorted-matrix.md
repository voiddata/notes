[codingninjas - matrix median](https://www.codingninjas.com/codestudio/problems/873378?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website)

## Approach 1:

```java
public static int getMedian(ArrayList<ArrayList<Integer>> matrix) {
    int low = 1;
    int high = 100000;
    int elements = matrix.size() * matrix.get(0).size();
    
    while(low <= high) {
        int mid = (low + high) / 2;
        
        if(satisfy(matrix, mid) <= (elements/2)) {
            low = mid+1;
        } else {
            high = mid-1;
        }
    }
    return low;
}

private static int satisfy(ArrayList<ArrayList<Integer>> matrix, int mid) {
    int cnt = 0;
    for(ArrayList<Integer> row : matrix) {
        cnt += count(mid, row);
    }
    return cnt;
}

private static int count(int m, ArrayList<Integer> row) {
    int low = 0;
    int high = row.size() - 1;

    while(low <= high) {
        int mid = (low+high) / 2;

        if(row.get(mid) <= m) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
}
```

## Approach 2:

```java
public static int getMedian(ArrayList<ArrayList<Integer>> matrix)
{
    int low = 1;
    int high = 100000;
    int elements = matrix.size() * matrix.get(0).size();
    
    while(low < high) {
        int mid = (low + high) / 2;
        
        if(satisfy(matrix, mid) <= (elements/2)) {
            low = mid+1;
        } else {
            high = mid;
        }
    }
    return low;
}

private static int satisfy(ArrayList<ArrayList<Integer>> matrix, int mid) {
    int cnt = 0;
    for(ArrayList<Integer> row : matrix) {
        cnt += count(mid, row);
    }
    return cnt;
}

private static int count(int m, ArrayList<Integer> row) {
    int low = 0;
    int high = row.size();
    
    while(low < high) {
        int mid = (low+high) / 2;
        
        if(row.get(mid) <= m) {
            low = mid + 1;
        } else {
            high = mid;
        }   
    }
    return low;
}
```

In [Approach 2](#approach-2), we have `high = row.size()` and this is needed to check for the condition where `low = row.size()-1` when there is no smallest element greater than **m** in `count()` function.  

If we have `high = row.size()-1` instead, then we have to check the condition for `low == high` after the loop terminates.