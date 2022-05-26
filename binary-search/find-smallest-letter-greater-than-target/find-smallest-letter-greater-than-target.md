[leetcode - 744](https://leetcode.com/problems/find-smallest-letter-greater-than-target/)

## Approach 1:

```java
public char nextGreatestLetter(char[] letters, char target) {
    int low = 0;
    int high = letters.length-1;   

    while(low <= high) {
        int mid = (low+high)/2;
       
        if(letters[mid] > target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    if(low == letters.length) {
        return letters[0];
    }
    return letters[low];
}
```

## Approach 2:

```java
public char nextGreatestLetter(char[] letters, char target) {    
    int low = 0;
    int high = letters.length;
    
    while(low < high) {
        int mid = (low+high)/2;
        
        if(letters[mid] > target) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    if(low == letters.length) {
        return letters[0];
    }
    return letters[low];
}
```