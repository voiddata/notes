[leetcode - 502 IPO](https://leetcode.com/problems/ipo/description/?envType=study-plan-v2&envId=top-interview-150)

## Bruteforce - Time Limit Exceeded

Time = $O(n^{2})$  
Space = $O(n)$

maintaining a visited set to mark the projects done

looping for at most `k`

searching through `capital` array to find the project with `capital` lesser than or equal to the current capital `w` with maximal `profits`

once found add that project to the visited set to not visit it again.

```java
public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    Set<Integer> set = new HashSet<>();
    
    int ans = w;
    for (int i = 0; i < k; i++) {
        int j = 0;
        int max = 0;
        int index = -1;
        while (j < capital.length) {
            if (ans >= capital[j] && !set.contains(j) && max <= profits[j]) {
                max = profits[j];
                index = j;
            }
            j++;
        }
        if (index != -1) {
            set.add(index);
            ans += max;
        }
    }
    return ans;
}
```