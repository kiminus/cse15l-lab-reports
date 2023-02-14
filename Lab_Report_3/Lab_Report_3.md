# [Turn in] Lab Report 3

Created: February 11, 2023 2:16 PM
Tags: lab report

[view all lab reports](https://kiminus.github.io/cse15l-lab-reports/)

[view this lab report](https://kiminus.github.io/cse15l-lab-reports/Lab_Report_3/Lab_Report_3)

<aside>
💡 In this lab report I researched 2 commands and provide at least 4 examples for each command. Because I feel like there is not much to write about `less` command

</aside>

Work Cited:

[How to Use the less Command in Linux with Examples](https://phoenixnap.com/kb/less-command-in-linux)

[find command](https://alvinalexander.com/unix/edu/examples/find.shtml#:~:text=The%20%2Dtype%20f%20option%20here,the%20name%20pattern%20you%20specify)

# Researching Commands

## `less`

> The `less` command can display and navigate items in the console.
> 
- Press `enter` to display next line
- Enter `q` to quit view

Here are some command lines options:

### `-f`

> force to open any file in plain text and suppress any potential error messages
> 

---

In this example, we try to open a complied java file (`.class`)

- Here is the source `[Foo.java](http://Foo.java)` file:

```java
public class Foo {
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}
```

- If we try to open this file without using `-f`, the bash terminal will show a warning before open this file. We need to type `y` to proceed

![Untitled](%5BTurn%20in%5D%20Lab%20Report%203%205a5ee8edd6cb410e9a6ce9849faf3bf4/Untitled.png)

- If we use `less -f foo.class`, the bash terminal will directly open the file.

```bash
zilin@kiminus MINGW64 /p/Coding/Draft/skill-demo1-data/written_2/non-fiction/OUP/Abernathy (main)
$ less -f foo.class #force to display foo.class in plain text
 
<CA><FE><BA><BE>^@^@^@=^@^]
^@^B^@^C^G^@^D^L^@^E^@^F^A^@^Pjava/lang/Object^A^@^F<init>^A^@^C()V     ^@^H^@  ^G^@
^L^@^K^@^L^A^@^Pjava/lang/System^A^@^Cout^A^@^ULjava/io/PrintStream^@^N^A^@^KHello World
^@^P^@^Q^G^@^R^L^@^S^@^T^A^@^Sjava/io/PrintStream^A^@^Gprintln^A^@^U(Ljava/lang/String;)V^G^@^V^A^@^CFoo^A^@^DCode^A^@^OLineNumberTable^A^@^Dmain^A^@^V([Ljava/lang/String;)V^A^@
SourceFile^A^@^HFoo.java^@!^@^U^@^B^@^@^@^@^@^B^@^A^@^E^@^F^@^A^@^W^@^@^@^]^@^A^@^A^@^@^@^E*<B7>^@^A<B1>^@^@^@^A^@^X^@^@^@^F^@^A^@^@^@^A^@      ^@^Y^@^Z^@^A^@^W^@^@^@%^@^B^@^A^@^@^@      <B2>^@^G^R^M<B6>^@^O<B1>^@^@^@^A^@^X^@^@^@
^@^B^@^@^@^C^@^H^@^D^@^A^@ESC^@^@^@^B^@^\
foo.class (END)
```

- We can type `q` to quit the less view

<aside>
📌 Conclusion: `-f` forces to open a file in plain text mode

</aside>

### `-N`

> show line numbers
> 

---

The input: `less -N skill-demo1-data/written_2/non-fiction/OUP/Abernathy/Foo.java`, which shows the line number of the file

The output: 

```
1 public class Foo {
2     public static void main(String[] args){
3         System.out.println("Hello World");
4     }
5 
6 }
# Note the line numbers 
```

### Word Search / Pattern Search

- After enter the less view of a file, we can type `/<word>` in the command and hit enter to highlight the matched word in the file

---

For example, if we type `/inter` in the less view of file `skill-demo1-data/written_2/non-fiction/OUP/Abernathy/ch0.txt`, this will highlight all matching strings in the file. Here is one of the highlighted word: 

![Untitled](%5BTurn%20in%5D%20Lab%20Report%203%205a5ee8edd6cb410e9a6ce9849faf3bf4/Untitled%201.png)

---

### open multiple files:

`less [options] <file1> <file 2>....`

### `-m`, show info

> display more information in its prompt, including the percentage of the file that has been displayed and the total number of lines in the file.
> 

---

By calling the command `less -m ch1.txt ch2.txt`, which display the information about ch1.txt and ch2.txt, we get: 

```
ch1.txt (file 1 of 2) 8%
```

If we read more lines, the read percentage will increase: 

```
17%
```

---

---

## `Find` command

### Search a directory with a string pattern

- `find <path> -name "[name pattern matcher]"`
- It will return all file system objects **(include file and directory)** under this path or all subordinates with the name matched the given pattern.
- The name pattern is in `""`

---

Example: In this example, I create a **folder** called `.txt` under the path `written_2/non-fiction/OUP/abernathy`, so the Directory looks like this: 

![Untitled](%5BTurn%20in%5D%20Lab%20Report%203%205a5ee8edd6cb410e9a6ce9849faf3bf4/Untitled%202.png)

Then, I switched my working directory to the ****`abernathy`** folder: 

```bash
$ pwd
/p/Coding/Draft/skill-demo1-data/written_2/non-fiction/OUP/abernathy
```

Then, I called the `ls` command. ********NOTE THE `.txt` folder DOES NOT shown up**

```bash
$ ls
ch1.txt  ch14.txt  ch15.txt  ch2.txt  ch3.txt  ch6.txt  ch7.txt  ch8.txt  ch9.txt
```

This is the result of the `find` command: 

```bash
$ find ./ -name "*.txt" # search all objects with a '.txt' header under the current directory 
./.txt
./ch1.txt
./ch14.txt
./ch15.txt
./ch2.txt
./ch3.txt
./ch6.txt
./ch7.txt
./ch8.txt
./ch9.txt
```

NOTE the `.txt` folder **does** shown up in the find result

<aside>
💡 Conclusion: `find` command will return all file system objects **whose name matched the given pattern matcher**

</aside>

### Search for certain types of File System Objects

> we can use `-type` option to specify which type of directory we are searching for
> 
- `f` : search for files
- `d`: search for folders

---

In this example, Under the same directory `written_2/non-fiction/OUP/abernathy`, we want to search for all **folders**

- This is the file structure of the current working directory

![Untitled](%5BTurn%20in%5D%20Lab%20Report%203%205a5ee8edd6cb410e9a6ce9849faf3bf4/Untitled%203.png)

- We can specify the type as `-type d`

```bash
$ find ./ -type d # search for all folder types under the current directory, including itself
./
./.happy
./.txt
./A_Folder
```

- The command `find ./ -type d` only return the folder under the given path

---

Another example: we want to find all **file** names that contains **c**, regardless of case

- `-i` : case-insensitive
- so the input and output looks like:

```bash
$ find ./ -type f -iname "*c*" # find all files that contains c regardless of the case under the current directory 
./.txt/Cool.cmd
./ch1.txt
./ch14.txt
./ch15.txt
./ch2.txt
./ch3.txt
./ch6.txt
./ch7.txt
./ch8.txt
./ch9.txt
```

### Search for certain properties of a file

[](https://linuxconfig.org/how-to-use-find-command-to-search-for-files-based-on-file-size)

- Search by **modification time**: `-mtime [(sign) modified time in days]`
    - in min: `-mmin [time in minutes]`
- Search by **size**: `-size [min size]`
    - Display all files with size greater than the `min size`
    - Units:
        - b – 512-byte blocks (this is the default if no suffix is used)
        - c – bytes
        - w – two-byte words
        - k – Kilobytes
        - M – Megabytes
        - G – Gigabytes

---

Example: In the same directory, find all **files** that has a size greater than 40k

![Untitled](%5BTurn%20in%5D%20Lab%20Report%203%205a5ee8edd6cb410e9a6ce9849faf3bf4/Untitled%204.png)

- We can use this command to get the result:

```bash
$ find  ./  -size +40k #find all files in the current directory with a size greater than 40K

./A_Folder/image.jpg
./ch1.txt
./ch15.txt
./ch2.txt
./ch6.txt
./ch7.txt
./ch8.txt
```

---

What if we only want to find **all `txt` files with a size greater than 40k, and modified at least 30 min ago?**

- We can do this:

```bash
$ find  ./  -name "*.txt" -size +40k -mmin +30 # search all objects with ".txt" in the header and with a size greater than 40K and modified more than 30 min ago under the current directory
./ch1.txt
./ch15.txt
./ch2.txt
./ch6.txt
./ch7.txt
./ch8.txt
```

### Run a command on each file found

- We can use `-exec <command>` to specify which command will be run at every found file. Note the `-exec` command should be placed at last and end with `\;`, to mark the end of a command

---

Find all **`.txt` files** in the directory that contains the string “assemble”, regardless of the cases.  

- We can first use `find` method to locate every `.txt` file
- Then, we use the `grep` method to check if there is a matched string
    - We can use the command option `-l` to let the `grep` command only list the file that contains the string
    - We can also use `-i` to uses case-insensitive mode
- Hence, the command looks like:
    
    `$ find . -type f -name "*.txt" -exec grep -il assemble {} \;`
    
    - `.` : refer to the current directory
    - `-type f` : look for files
    - `-name "*.txt"`: look for txt files
    - `-exec ... \;`: start a command run on each found file
    - `grep -il assmeble` : try to locate the word `assemble` in each found files. If there is matched string regardless of case, print the file name.
    - `{}` : The place holder for each file that is found

The result looks like this: 

```bash
$ find . -type f -name "*.txt" -exec grep -il assemble {} \; # search all txt files that contains word assemble, ignore the case. Print the file headers that contain the word 
./ch1.txt
./ch14.txt
./ch15.txt
./ch2.txt
./ch7.txt
./ch8.txt
./ch9.txt
```

---

Another example: We want to find the **total number of occurrence** of the word `world` in all `txt` files under the current directory

Given the File directory:
![Untitled](%5BTurn%20in%5D%20Lab%20Report%203%205a5ee8edd6cb410e9a6ce9849faf3bf4/Untitled%205.png)

- we can use `grep` command `grep -wc "world" [files]`   to count the number of occurrence of a string in each file
    - `-wc` gives a count of occurrences in a set of files.
- so the command looks like:

```bash
$ find ./ -name "*.txt" -type f -exec grep -wc "world" {} \; #find all .txt files, and for each file count the number of occurance of the word world
4 #ch1.txt count
3 #ch2.txt count
2
1
3
2
4
3
1 #ch15.txt world count 
```
--- 

This command is equivalent to traverse each found file in a loop and print the number of ocuurance of `world` in a `.sh` script. Here is an example of this: 

In the `FileCount.sh`, which is located under the root folder: 

```bash
FILES=`find . -name "*.txt" -type f` # find all files and store in the files set
for FILE in $FILES # iterate through every file in the set
do
  echo "in $FILE: `grep -wc "world" $FILE`" # print the file name, and the word count
done # end of loop
```

To run this script, we use this bash command: 
```bash
# the current working directory is skill-demo1-data/written_2/non-fiction/OUP/Abernathy
$ bash ../../../../FileCount.sh #navigate to where the bash script located. 
in ./ch1.txt: 4 
in ./ch14.txt: 3
in ./ch15.txt: 2
in ./ch2.txt: 1
in ./ch3.txt: 3
in ./ch6.txt: 2
in ./ch7.txt: 4
in ./ch8.txt: 3
in ./ch9.txt: 1
```

