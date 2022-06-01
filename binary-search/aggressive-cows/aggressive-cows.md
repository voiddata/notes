[codingninjas](https://www.codingninjas.com/codestudio/problems/chess-tournament_981299)

## Approach 1:

```java
public static int chessTournament(int[] positions, int n,  int c) 
{
    int low = 1;
    int high = 0;
    
    Arrays.sort(positions);
    high = positions[n-1] - positions[0];

    while(low <= high) {
        int mid = (low+high) / 2;
        
        if(check(positions, mid, c)) {
            low = mid + 1; 
        } else {
            high = mid - 1;
        }
    }
    return high;
}

private static boolean check(int[] pos, int mid, int c) {
    int count = 0;
    int p = pos[0];
    
    for(int i=1; i<pos.length; i++) {
        if(pos[i] - p >= mid) {
            p = pos[i];
            count++;
        }
    }
    return count+1 >= c;
}
```