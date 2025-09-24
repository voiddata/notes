[atcoder](https://atcoder.jp/contests/dp/tasks/dp_a)

## Bruteforce Recursive Approach

![approach](./pseudocode.png)

### Sample Input 1
4

10 30 40 20

### Sample Output 1
30

![sample_input_1_state_tree.png](sample_input_1_state_tree.png)


### Sample Input 2
2

10 10

### Sample Output 2
0

![sample_input_2_state_tree.png](sample_input_2_state_tree.png)

### Sample Input 3
6

30 10 60 10 60 50

### Sample Output 3
40

![sample_input_3_state_tree.png](sample_input_3_state_tree.png)


### Solution
```java
private static int minCost(int[] arr, int index) {
    int leftTreeMinCost = index + 1 < arr.length ? 
        Math.abs(arr[index] - arr[index + 1]) + minCost(arr, index + 1) : -1;
    int rightTreeMinCost = index + 2 < arr.length ? 
        Math.abs(arr[index] - arr[index + 2]) + minCost(arr, index + 2) : -1;

    if (leftTreeMinCost != -1 && rightTreeMinCost != -1) {
        return Math.min(leftTreeMinCost, rightTreeMinCost);
    } else if (leftTreeMinCost == -1 && rightTreeMinCost != -1) {
        return rightTreeMinCost;
    } else if (leftTreeMinCost != -1 && rightTreeMinCost == -1) {
        return leftTreeMinCost;
    } else {
        return 0;
    }
}
```

>as expected the recursive bruteforce solution is exceeding time limit.


## Optimzed Recursive Approach - Memoization - Top Down

Since calculations are getting repeated again and again, we can check if an index position is already calculated.

Use a memory buffer to store minimum cost at each index and fetch them if required.

```java
private static int memoizedRecursiveMinCost(int[] arr, int index, int[] buffer) {

    if (buffer[index] != -1) {
        return buffer[index];
    }

    int leftTreeMinCost = -1;
    if (index + 1 < arr.length) {
        if (buffer[index + 1] != -1) {
            leftTreeMinCost = Math.abs(arr[index] - arr[index + 1]) + buffer[index + 1];
        } else {
            leftTreeMinCost = Math.abs(arr[index] - arr[index + 1]) + memoizedRecursiveMinCost(arr, index + 1, buffer);
        }
    }

    int rightTreeMinCost = -1;
    if (index + 2 < arr.length) {
        if (buffer[index + 2] != -1) {
            rightTreeMinCost = Math.abs(arr[index] - arr[index + 2]) + buffer[index + 2];
        } else {
            rightTreeMinCost = Math.abs(arr[index] - arr[index + 2]) + memoizedRecursiveMinCost(arr, index + 2, buffer);
        }
    }

    if (leftTreeMinCost != -1 && rightTreeMinCost != -1) {
        int currentIndexMinCost = Math.min(leftTreeMinCost, rightTreeMinCost);
        buffer[index] = currentIndexMinCost;
        return currentIndexMinCost;
    } else if (leftTreeMinCost == -1 && rightTreeMinCost != -1) {
        buffer[index] = rightTreeMinCost;
        return rightTreeMinCost;
    } else if (leftTreeMinCost != -1 && rightTreeMinCost == -1) {
        buffer[index] = leftTreeMinCost;
        return leftTreeMinCost;
    } else {
        buffer[index] = 0;
        return 0;
    }
}
```

>this solution is time optimized and accepted. but there is an extra additional space being used.


### Recurrence relation

$$
a = |n - (n+1)| + minCost(n+1) \\
b = |n - (n+2)| + minCost(n+2) \\
minCost(n) = min(a, b)
$$

Since the above recurrence satisfies **optimal substructure**, this problem can be solved using dynamic programming.

With this recurrence in mind, we can implement the bottom up tabulation approach using a buffer.


## Optimized Iterative Approach - Tabulation - Bottom up

![tabulation](tabulation.png)


```java
private static int tabulationIterativeMinCost(int[] arr) {

    int n = arr.length;

    // base cases
    if (n == 1) return 0;
    if (n == 2) return Math.abs(arr[0] - arr[1]);

    
    int[] buffer = new int[n];
    buffer[n-1] = 0; // not required, but done for understanding
    buffer[n-2] = Math.abs(arr[n-1] - arr[n-2]);

    for (int i = n-3; i >= 0; i--) {
        buffer[i] = Math.min(
            Math.abs(arr[i] - arr[i+1]) + buffer[i+1],
            Math.abs(arr[i] - arr[i+2]) + buffer[i+2]
        );
    }

    return buffer[0];
}
```