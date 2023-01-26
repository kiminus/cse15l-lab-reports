# [Turn in - Week 3] Lab Report 2

Created: January 25, 2023 2:00 PM
Tags: lab report

zilin liu

A17691286

Lab Report 2

1/27

visit all lab reports here:
https://kiminus.github.io/cse15l-lab-reports/ 

visit this lab report here: 
https://kiminus.github.io/cse15l-lab-reports/Lab_Report_Week_3

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

- the method `handleRequest(URI url)` is called
    - the provided argument `url` is [`http://localhost:5500/add-message?s=3sf`](http://localhost:5500/add-message?s=3sf)
- the method `url.getPath()` is called, which will return [`/add-message`](http://localhost:5500/add-message?s=3sf)
- `url.getQuery()` is called, which will return [`s=3s`](http://localhost:5500/add-message?s=3sf)
- finally append this text to the stringbuilder and append a new line symbol

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled%201.png)

- similarly, `handleRequest(URI url)` is called, and the argument provided is [http://localhost:5500/add-message?s=twice](http://localhost:5500/add-message?s=twice)
- the path does not change `/add-message`
- the query variable name does not change `s`
- the query value does change, it becomes `twice`
- domain does not change `localhost`
- port does not change `5500`
- then we append this text behind the string builder

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled%202.png)

- similarly, `handleRequest(URI url)` is called, and the argument provided is [http://localhost:5500/add-message?s=twice](http://localhost:5500/add-message?string=1321)
- the path does not change `/add-message`
- the query variable name does change to  `string`
- the query value does change, it becomes `twice`
- domain does not change `localhost`
- port does not change `5500`
- then we append this text behind the string builder

![Untitled](%5BTurn%20in%20-%20Week%203%5D%20Lab%20Report%202%209d5e4ba0797d472db80553ea965348bb/Untitled%203.png)

- similarly, `handleRequest(URI url)` is called, and the argument provided is [http://localhost:5500/add-message?s=twice](http://localhost:5500/add?s=32)
- the path changed to `/add`
- the query variable name does change to  `s`
- the query value changes to `32`
- domain does not change `localhost`
- port does not change `5500`
- then we append this text behind the string builder
- because we add is not a valid path, thus, we return undefined

# Part 2 - Reverse array

### Correct Tests:

```java
@Test
public void testReverseInPlace() {
    int[] input1 = {3}; //the input is 3
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{3}, input1); //passed 
}
```

### Null Array:

- we expect the method to return null if the input array is null
- however, in the `ArrayExample` class it dereference the parameter array without checking
- so we need to add a corner case checking before original implementation (shown in the final code block)

```java
@Test
public void testReverseInPlaceNull() {
  int[] input = null;
  ArrayExamples.reverseInPlace(input);
  assertNull(input); //actual: throw NPE
}
```

### Swap bug:

- the implementation does not actually swap the values, it merely overwrites the smaller index element with the corresponding larger index value.
- so if the input is a ********non-palindrome******** array, it will fail
- so we need to implement a `swap` method  (detail see below)

```java
@Test
public void testReverseInPlaceGeneral() {
    int[] input = {3, 4, 1};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[]{1, 4, 3}, input); //actual: input[2] is different
}
```

### For loop Termination condition bug

- the original implementation will swap values from i = 0 to the end element of the array, so each element is actually swapped twice, which maintained the original order
- so if the input is a ********non-palindrome******** array, it will fail
- so the for loop should terminates when `i<arr.length/2` (detail see below)

```java
@Test
public void testReverseInPlaceGeneralOdd() {
    int[] input = {3, 4, 1};
    ArrayExamples.reverseInPlace(input);
    System.out.println(Arrays.toString(input));
    assertArrayEquals(new int[]{1, 4, 3}, input); //actual 3,4,1
}

@Test
public void testReverseInPlaceGeneralEven() {
    int[] input = {3, 4, 1, 2};
    ArrayExamples.reverseInPlace(input);
    System.out.println(Arrays.toString(input));
    assertArrayEquals(new int[]{2, 1, 4, 3}, input); //actual 3,4,1,2
}
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

1. how to create, establish and run a server on the local machine or deploy it on the server
2. Git commands, Power shell commands, bash commands
3. file directory, path
4. URL components. what is a protocol, domain, port, path, query and anchor
5. How to use github to deploy a static web