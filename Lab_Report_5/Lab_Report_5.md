# [Turn in] Lab Report 5

Created: March 8, 2023 7:19 AM
Tags: lab report

**********This lab report is about the `[grader.sh](http://grader.sh)` file in Lab 6**

[view all reports](https://kiminus.github.io/cse15l-lab-reports/)

[view this lab report](https://kiminus.github.io/cse15l-lab-reports/Lab_Report_5/Lab_Report_5) 

download this grader repository here: https://github.com/kiminus/grader-review-kiminus

### The `[grader.sh](http://grader.sh)` file with full explanations:

```bash
#modify this environment variable based on the local operating system
CPATH='.;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar'
SFOLDER='student_submission'

# check if the student submission folder exists
if [ -d $SFOLDER ]
then
    rm -rf $SFOLDER # clean the student-submission folder
else 
    mkdir $SFOLDER # create the student-submission folder
fi 
    echo '===========clean up finished================'

git clone --quiet $1 $SFOLDER #clone into the student-submission folder
echo '===========Finished cloning================'

# first check if the student has ListExamples.java
if [[ -f $SFOLDER/ListExamples.java ]]
then
    echo 'Found ListExamples.java file'
else
    echo 'No ListExamples.java file found'
    echo "grade: 0"
    exit 1;
fi

echo '========Sanity check Done============'

# copy the checker and lib into the student's folder
cp TestListExamples.java $SFOLDER
cp -r lib $SFOLDER

cd $SFOLDER

# compile them, and store error message in the compile.txt file
javac -cp $CPATH *.java 2> compile.txt
cat compile.txt

# check if the compile.txt file is empty, use compile.txt as input
if [[ `wc -w < compile.txt` = 0 ]]
then
    echo 'No compile errors'
else
    echo '===========failed to compile============'
    echo "grade: 0"
    exit 1;
fi
echo '========Compile success============'

# run the checker and store the result in the result.txt file, and output in the output.txt file
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples 1>result.txt 0>output.txt

# if th result.txt contains OK, then the test passed
if [[ `grep "OK" result.txt | cut -d '(' -f 1 ` = 'OK ' ]]
then
    echo 'Test passed'
    echo "Grade: 100"
    exit 0;
else
    echo 'Test failed'
fi

echo "`grep "Tests run" result.txt`"
# get the Test Count and Failure Count
# grep the last line of the result.txt file, and retain the second part delimited by ':'
# then retain the first part delimited by ','
TEST_COUNT=`grep "Tests run" result.txt | cut -d ':' -f 2 | cut -d ',' -f 1`

# grep the last line of the result.txt file, and retain the second part delimited by ','
# then retain the second part delimited by ':'
# declare FAIL_COUNT as an arithmatic integer when doing operations 
declare -i FAIL_COUNT=`grep "Failures:" result.txt | cut -d ',' -f 2 | cut -d ':' -f 2`

# calculate the grade
echo "Grade: ` expr $(( 100-$FAIL_COUNT*10 )) `"
echo '========Grade evaluated============'

if [[ $FAIL_COUNT = 0 ]]
then
    exit 0;
else 
    echo '========Failed Cases:==========='
fi

# display each failed case that matched the pattern [1..9]) in the output.txt file
# grep the output.txt file, and retain the lines that matched the pattern [1..9])
# then retain the second part delimited by ')'
CASES=`grep -E "^([0-9])*\)" result.txt`

for CASE in $CASES
do
    echo $CASE
done
exit 0;
```

## run examples:

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled.png)

---

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%201.png)

---

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%202.png)

---

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%203.png)

---

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%204.png)

---

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%205.png)

---

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%206.png)

## Custom submissions

### wrong but is likely to get full scores

The `ListExamples.java`:

```java
import java.util.ArrayList;
import java.util.List;

interface StringChecker {
  boolean checkString(String s);
}

class ListExamples {
	//NOTE HERE the result is static
  static List<String> result = new ArrayList<>();

  // Returns a new list that has all the elements of the input list for which
  // the StringChecker returns true, and not the elements that return false, in
  // the same order they appeared in the input list;
  static List<String> filter(List<String> list, StringChecker sc) {
    result.clear();
    for (String s : list) {
      if (sc.checkString(s)) {
        result.add(s);
      }
    }
    return result;
  }

  // Takes two sorted list of strings (so "a" appears before "b" and so on),
  // and return a new list that has all the strings in both list in sorted order.
  static List<String> merge(List<String> list1, List<String> list2) {
    result.clear();
    int index1 = 0, index2 = 0;
    while (index1 < list1.size() && index2 < list2.size()) {
      if (list1.get(index1).compareTo(list2.get(index2)) < 0) {
        result.add(list1.get(index1));
        index1 += 1;
      } else {
        result.add(list2.get(index2));
        index2 += 1;
      }
    }
    while (index1 < list1.size()) {
      result.add(list1.get(index1));
      index1 += 1;
    }
    while (index2 < list2.size()) {
      result.add(list2.get(index2));
      index2 += 1;
    }
    return result;
  }

}
```

It passed all test cases: 

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%207.png)

However, this script will likely to fail if called multiple times in one method 

---

### One that is mostly correct but crashes the grader and doesn’t give a nice error back

The `[ListExamples.java](http://ListExamples.java)` : 

```java
import java.util.List;
import java.util.ArrayList;

interface StringChecker {
    boolean checkString(String s);
}

class ListExamples {
    static boolean printed = false;
    static List<String> filter(List<String> list, StringChecker sc) {
        return res();
    }

    static List<String> merge(List<String> list1, List<String> list2) {
        return res();
    }
    static List<String> res() {
        if (printed) return new ArrayList<>();
        printed = !printed;
        return List.of("\nOK (4 tests) ");
    }
}
```

it passed all the tests, but it cheated the tester, because the grader only look for the word `OK`

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%208.png)

## Server On!

1. in the `grade.sh`, modify the `CPATH` to `CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'`
2. then in the terminal, compile the server files: `javac [Server.java](http://Server.java) GradeServer.java`
3. run the server: `java GradeServer 5500`
4. open the server in the browser, and grade the submission: `.../grade?repo=<url>`

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%209.png)

![Untitled](%5BTurn%20in%5D%20Lab%20Report%205%20a7dcf54595d84b10b8299b42fb02be8a/Untitled%2010.png)