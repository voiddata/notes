## Permutation:

Permutation - mutation of elements in a collection.  
**In permutation, the order of the elements matters.**


## Approach - Recursive:

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    func(nums, 0, res);   
    return res;
}

private static void func(int[] nums, int index, List<List<Integer>> res) {
    if(index == nums.length) {
        res.add(new ArrayList<Integer>(
            Arrays.stream(nums).boxed()
                .collect(Collectors.toList())));
        return;
    }
    for(int i=index; i<nums.length; i++) {
        swap(index, i, nums);
        func(nums, index+1, res);
        swap(index, i, nums);
    }   
}

private static void swap(int a, int b, int[] nums) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;   
}
```

Time Complexity : $O(n!)$

Number of permutations of an array of $n$ elements would be $n!$