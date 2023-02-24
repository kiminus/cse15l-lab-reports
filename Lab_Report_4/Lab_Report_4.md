# [Turn in] Lab Report 4

[view all lab reports](https://kiminus.github.io/cse15l-lab-reports/)

[view this lab report](https://kiminus.github.io/cse15l-lab-reports/Lab_Report_4/Lab_Report_4)

# Steps:

### **Setup:** Delete any existing forks of the repository you have on your account

- We can delete an existing branch in the repository by using command
  `git push origin -d <branch_name>`

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled.png)

### Log into ieng6

- in the bash terminal, enter:
  `ssh [cs15lwi23akl@ieng6.ucsd.edu](mailto:cs15lwi23akl@ieng6.ucsd.edu)`
- Because we have already set up the bash credential in the remote server, we
  don’t need to type the password.

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%201.png)

### Clone your fork of the repository from your Github account

- Let’s first create a folder where we clone the repository to
  - In the terminal, type `mkdir draft`, then `cd draft`
  - Now we are in the `draft` folder
- Clone the repository from remote
  - Type `git clone`, then copy the ssh link of the remote repository and right
    click to paste it in the terminal (`git clone <right click>`)
    ![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%202.png)
- To make it a repository, type `git init`
- Add the local folder as a remote repository branch
  - Use
    `git remote add origin [git@github.com](mailto:git@github.com):kiminus/Kiminus.git`,
    that command connected this local repository to a remote one
- Create a new branch called `temp` : `git branch -m temp`
- We can confirm which branch are we on: `git status`

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%203.png)

- we conformed this repository is on branch temp

### Run the tests, demonstrating that they fail

- first cd into the `lab7` folder
- then we copy, paste, and run the following commands in order:

```bash
javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
```

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%204.png)

### Edit the code file to fix the failing test

- To inspect the code, we need to open the file in a text editor
- We can use `tab` to auto complete
- `nano L<tab>.j<tab>` to get `nano ListExamples.java`
- From previous knowledge, we know the bug is at `index1 += 1;`
  - We can quickly located it by press `ctrl + w`, enter `x2 +=`, then press
    `enter` key to search for first occurrence
  - The place we want to modify is at the third occurrence, so we need to
    repeat `ctrl+w` then `enter` 2 more times (`ctrl+w` `enter` `ctrl+w`
    `enter` )
    ![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%205.png)
  - Then replace 1 with 2 (`<right><right><right><right><right><delete>2`)
  - Then save and exit the file (`<ctrl+o><enter><ctrl+x>`)

### Run the tests, demonstrating that they now succeed

- Recompile and rerun the test

```bash
javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
```

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%206.png)

- All Tests Passed. The bug is fixed

### Commit and push the resulting change to your Github account (you can pick any commit message!)

- Copy and paste this command to the terminal:

```bash
git remote set-url origin git@github.com:kiminus/draft.git
git add *
git commit -m "message"
git push -u origin main
```

- We should get the following result, that shows all files have been
  successfully committed and pushed

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%207.png)

- In the Github, we can see these files occurred:

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%208.png)

# Speed Up!

- The quickest way to speed up is to execute a bash script.
- Let’s create a bash script called `correct_test.sh` under `~/src` folder

```bash
# rm the existing file
rm -rf lab7
# clone the test repository
git clone https://github.com/ucsd-cse15l-w23/lab7.git
# change the directory into the test repository
cd lab7
# compile all files and run tester
javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests

# the error message should be displayed
# print dividing line
echo "----------------------------------------"
# replace the last/3rd "index1 += 1" with "index2 += 2" in the file ListExamples.java in place
sed -i '43 s:index1 += 1:index2 += 1:' ListExamples.java
# compile all files and run tester
javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore ListExamplesTests
# at this point, all tests should pass

# connect the git remote repository
git remote set-url origin git@github.com:kiminus/draft.git
echo "the current remote repository address:" `git remote -v`
# delete the existing branch
git push -d origin temp
# create a new branch
git branch -m temp
# reinitialize the respotory
git init
# add all files
git add *
# commit the changes
git commit -m "correct the test"
# push the branch to the remote repository
git push -u origin temp
```

Then, in the working directory `src`, we just need to run
`bash correct_test.sh` to obtain this result, which is a lot faster than typing
all commands by hand:

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%209.png)

![Untitled](%5BTurn%20in%5D%20Lab%20Report%204%201a5dbda419cb4f98a5bdd65797918f97/Untitled%2010.png)
