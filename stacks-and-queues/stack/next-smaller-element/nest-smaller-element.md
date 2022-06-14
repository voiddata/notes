[codingninjas](https://www.codingninjas.com/codestudio/problems/1112581)

```java
static ArrayList<Integer> nextSmallerElement(ArrayList<Integer> arr, int n){
    int[] nse = new int[n];
    Arrays.fill(nse, -1);
    
    Stack<Integer> stack = new Stack<>();
    
    for(int i=n-1; i>=0; i--) {
        int ele = arr.get(i);        
        while(!stack.empty() && stack.peek() >= ele) {
            stack.pop();
        }
        if(!stack.empty()) {
            nse[i] = stack.peek();
        }
        stack.push(ele);
    }
    
    ArrayList<Integer> intList = new ArrayList<Integer>(nse.length);
    for (int i : nse)
    {
        intList.add(i);
    }
    return intList;
}
```