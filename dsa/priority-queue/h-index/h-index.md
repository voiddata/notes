[kickstart](https://codingcompetitions.withgoogle.com/kickstart/round/00000000008f4332/0000000000941e56)


## Approach 1:

Time = $O(n^{3})$  
Space = $Constant$

```java
private static boolean check(int a, int b, int arr[]) {
    int count = 0;
    for(int i=0;i<b; i++) {
        if(arr[i] >= a)
        count++;
    }
    if(count >= a) {
        return true;
    }
    return false;
}
public static int[] getHIndexScore(int[] citationsPerPaper) {
    int[] hIndex = new int[citationsPerPaper.length];
    int n = citationsPerPaper.length;
    for (int i = 1; i <= n; i++) {
        for (int j = i; j >= 1; j--) {
            if (check(j, i, citationsPerPaper)) {
                hIndex[i-1] = j;
                break;
            }
        }
    }
    return hIndex;
}
```

## Approach 2:

Time = $O(n\log(n))$  
Space = $O(n)$

```java
public static int[] getHIndexScore(int[] citationsPerPaper) {
    int[] hIndex = new int[citationsPerPaper.length];
    int h = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    
    for (int i = 0; i < citationsPerPaper.length; i++) {
        int ele = citationsPerPaper[i];
        if(ele > h) {
            queue.add(ele);
            while(queue.peek() > h && queue.size() > h) {
                h++;
            }
        }
        hIndex[i] = h;
        while(!queue.isEmpty() && queue.peek() <= h) {
            queue.poll();
        } 
    }
    return hIndex;
}
```