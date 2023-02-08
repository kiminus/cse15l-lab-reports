# [Turn in - Week 3] Lab Report 2

Created: January 25, 2023 2:00 PM

zilin liu

A17691286

Lab Report 2

1/27



[Visit all lab reports here:](https://kiminus.github.io/cse15l-lab-reports/)



[Visit this lab report here:]( https://kiminus.github.io/cse15l-lab-reports/Lab_Report_2/Lab_Report_2 )


# The String Server

```java
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class StringHandler implements URLHandler {
    private static final String EMPTY_PATH = "\\";
    StringBuilder builder;

    public StringHandler() {
        builder = new StringBuilder();
    }

    public String handleRequest(URI url) {
        // nothing, list the
        if (url.getPath().equals(EMPTY_PATH)) {
            return builder.toString();
        }
        // add function
        else if (url.getPath().equals("/add-message")) {
            String[] queries = url.getQuery().split("=");
            String toAdd = queries[1]; // we dont care what the parameter variable name is
            builder.append(toAdd);
            builder.append("\n");

        }
        // undefined
        else {
            return "undefined";
        }
        return builder.toString();
    }

}

public class StringServer {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            Server.start(port, new StringHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled.png)

- The method `handleRequest(URI url)` is called
    - The provided argument `url` is [`http://localhost:5500/add-message?s=3sf`](http://localhost:5500/add-message?s=3sf)
- The method `url.getPath()` is called, which returns [`/add-message`](http://localhost:5500/add-message?s=3sf)
- `url.getQuery()` is called, which returns [`s=3s`](http://localhost:5500/add-message?s=3sf)
- Finally append the queries string to the stringbuilder, and append a new line symbol

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled%201.png)

- Similarly, `handleRequest(URI url)` is called, and the argument provided is [http://localhost:5500/add-message?s=twice](http://localhost:5500/add-message?s=twice)
- The path does not change `/add-message`
- The query variable name does not change `s`
- The query value does change, it becomes `twice`
- Domain does not change `localhost`
- Port does not change `5500`
- Then we append this text to the string builder

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled%202.png)

- Similarly, `handleRequest(URI url)` is called, and the argument provided is [http://localhost:5500/add-message?s=twice](http://localhost:5500/add-message?string=1321)
- The path does not change `/add-message`
- The query variable name does change to  `string`
- The query value does change, it becomes `twice`
- Domain does not change `localhost`
- Port does not change `5500`
- Then we append this text behind the string builder

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled%203.png)

- Similarly, `handleRequest(URI url)` is called, and the argument provided is [http://localhost:5500/add-message?s=twice](http://localhost:5500/add?s=32)
- The path changed to `/add`
- The query variable name does change to  `s`
- The query value changes to `32`
- Domain does not change `localhost`
- Port does not change `5500`
- Then we append this text behind the string builder
- Because we add is not a valid path, thus, we return undefined

# Part 2 - Reverse array

### Correct Test:

```java
@Test
public void testReverseInPlace() {
    int[] input1 = {3}; //the input is 3
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{3}, input1); //passed 
}
```

### Null Array:

- We expect this method to return null if the input array is null
- however, the method dereference the parameter array without checking
- so we need to add a *corner case checking* before original implementation (shown in the final code block)

```java
@Test
public void testReverseInPlaceNull() {
  int[] input = null;
  ArrayExamples.reverseInPlace(input);
  assertNull(input); //actual: throw NPE
}
```

The failure symptom: 
A `NullPointerException` is thrown
```
PS P:\Coding\Draft\lab3> java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore ArrayTests
JUnit version 4.13.2
...E
Time: 0.008
There was 1 failure:
1) testReverseInPlaceNull(ArrayTests)
java.lang.NullPointerException: Cannot read the array length because "<parameter1>" is null
        at ArrayExamples.reverseInPlace(ArrayExamples.java:7)
        at ArrayTests.testReverseInPlaceNull(ArrayTests.java:14)

FAILURES!!!
Tests run: 3,  Failures: 1
```

### Swap bug:

- The implementation does not actually swap the values, it merely overwrites the smaller index element with the corresponding larger index value.
- So if the input is a **non-palindrome** array, it will fail
- So we need to implement a `swap` method  (shown in the final code block)

```java
@Test
public void testReverseInPlaceGeneral() {
    int[] input = {3, 4, 1};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[]{1, 4, 3}, input); //actual: input[2] is different
}
```

Symptom/output: 
We expect 1, 4, 3, but was [1, 4, 1]
```
JUnit version 4.13.2
...E
Time: 0.008
There was 1 failure:
1) testReverseInPlaceGeneral(ArrayTests)
arrays first differed at element [2]; expected:<3> but was:<1>
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:78)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:28)
        at org.junit.Assert.internalArrayEquals(Assert.java:534)
        at org.junit.Assert.assertArrayEquals(Assert.java:418)
        at org.junit.Assert.assertArrayEquals(Assert.java:429)
        at ArrayTests.testReverseInPlaceGeneral(ArrayTests.java:23)
        ... 32 trimmed
Caused by: java.lang.AssertionError: expected:<3> but was:<1>
        at org.junit.Assert.fail(Assert.java:89)
        at org.junit.Assert.failNotEquals(Assert.java:835)
        at org.junit.Assert.assertEquals(Assert.java:120)
        at org.junit.Assert.assertEquals(Assert.java:146)
        at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:76)
        ... 38 more

FAILURES!!!
Tests run: 3,  Failures: 1
```
### For loop Termination condition bug

- The original implementation will swap values from i = 0 to the end element of the array, so each element is actually swapped twice, which maintained the original order
- So if the input is a **non-palindrome** array, it will fail
- So the for loop should terminates when `i<arr.length/2` (detail see below)
<aside> <br>Assume all other issues are resolved</br> </aside>

```java
@Test
public void testReverseInPlaceGeneralOdd() {
    int[] input = {3, 4, 1};
    ArrayExamples.reverseInPlace(input);
    System.out.println(Arrays.toString(input));
    assertArrayEquals(new int[]{1, 4, 3}, input); //actual [3, 4, 1]
}

@Test
public void testReverseInPlaceGeneralPalindrome() {
    int[] input = {1,2,2,1};
    ArrayExamples.reverseInPlace(input);
    System.out.println(Arrays.toString(input));
    assertArrayEquals(new int[]{1,2,2,1}, input); //passed 
}
```
Symptom: 
- Input: 3, 4, 1, expected: 1,4,3, but was [3, 4, 1]
```
JUnit version 4.13.2
...[1, 2, 2, 1]
.[1, 4, 1]
E
Time: 0.008
There was 1 failure:
1) testReverseInPlaceGeneralOdd(ArrayTests)
arrays first differed at element [2]; expected:<3> but was:<1>
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:78)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:28)
        at org.junit.Assert.internalArrayEquals(Assert.java:534)
        at org.junit.Assert.assertArrayEquals(Assert.java:418)
        at org.junit.Assert.assertArrayEquals(Assert.java:429)
        at ArrayTests.testReverseInPlaceGeneralOdd(ArrayTests.java:27)
        ... 32 trimmed
Caused by: java.lang.AssertionError: expected:<3> but was:<1>
        at org.junit.Assert.fail(Assert.java:89)
        at org.junit.Assert.failNotEquals(Assert.java:835)
        at org.junit.Assert.assertEquals(Assert.java:120)
        at org.junit.Assert.assertEquals(Assert.java:146)
        at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:76)
        ... 38 more

FAILURES!!!
Tests run: 4,  Failures: 1
```

### Correct implementation:

```java
static void reverseInPlace(int[] arr) {
  if (arr == null || arr.length == 0) return; //corner case checking
  for(int i = 0; i < arr.length / 2; i += 1) { //loop termination condition
    swap(i, arr.length - 1 - i, arr); //swap method
  }
}
static void swap(int a, int b, int[] arr) {
  int temp = arr[a];
  arr[a] = arr[b];
  arr[b] = temp;
}
```



# Part 3

1. How to create, establish and run a server on the local machine or deploy it on the server
2. Git commands, Power shell commands, bash commands
3. File directory, path
4. URL components. what is a protocol, domain, port, path, query and anchor
5. How to use github to deploy a static web
