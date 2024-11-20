## Bruteforce approach - Memory Limit Exceeded:

generating all pairs and storing in min heap to get the top K

```java
public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<Pair<Integer, Pair<Integer, Integer>>> list = new ArrayList<>();

    for (int i=0;i<nums1.length;i++) {
        for (int j=0;j<nums2.length;j++) {
            list.add(new Pair<>(nums1[i] + nums2[j], new Pair<>(nums1[i], nums2[j])));
        }
    }

    Collections.sort(list, (p1, p2) -> {
        return p1.getKey() - p2.getKey();
    });

    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < list.size() && k-- > 0; i++) {
        Pair<Integer, Pair<Integer, Integer>> pair = list.get(i);
        Pair<Integer, Integer> keyValue = pair.getValue();
        ans.add(List.of(keyValue.getKey(), keyValue.getValue()));
    }
    return ans;
}
```