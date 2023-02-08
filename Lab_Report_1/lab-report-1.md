# Lab Report 1, account setup, Windows

[home](index.md)

visit this page at https://kiminus.github.io/cse15l-lab-reports/Lab%20Report%201/lab-report-1

A17691286 zilin liu

# Environment

1. install Git (it is useful but not required in this lab)

[Git](https://git-scm.com/)

## VS Code or other text/markdown editor

First download the VS code use this link:
[Visual Studio Code - Code Editing. Redefined](https://code.visualstudio.com/)

download VS code version based on your OS. Click `download` button to download:

![Download VS](<Lab Report 1 sources/vs_download.png>)

Follow the instructions on the setup window and complete the installation.

![installation Image](<Lab Report 1 sources/installation.png>)

_I cannot change the installation language to English_

![complete](<Lab Report 1 sources/installation_complete.png>)

### Create a new Project

Try to use VS code to write your lab report!

### How to do it?

First create your lab report folder then open VS code, and use vs code to open
this folder

![open_folder](<Lab Report 1 sources/open_folder.png>)

then, right click the empty space in the explore section, and in the dropdown
menu select `New File`

![create file](<Lab Report 1 sources/create_file.png>)

finally, rename the file name to your preference, and change the file extension
to `.md`, which is the markdown file

![make file](<Lab Report 1 sources/make_file.png>)

## Find your CSE 15L course account

You can find your CSE 15L account here:

[Account Lookup](https://sdacs.ucsd.edu/~icc/index.php)

In that page, enter your username (the prefix of your email address, before
`@`), and your student ID

![Untitled](<Lab Report 1 sources/Untitled.png>)

After login, you can find your account shown here:

![Untitled](<Lab Report 1 sources/Untitled 1.png>)

### Forgot password?

- if you know your SSO password, don’t reset it
  - it will change all your existing passwords
- if you forgot your password, you may reset it follow the instructions on this
  page
- go to IT desk if you need additional help (they are very helpful)

[[TUTORIAL] How to Reset your Password](https://docs.google.com/document/d/1hs7CyQeh-MdUfM9uv99i8tqfneos6Y8bDU0uhn1wqho/edit)

# Connect to a Remote Server

1. open windows PowerShell (you can type `window powershell` in computer search
   bar and open it)
2. enter this
   command:`ssh _<the course specific account name>_ [@ieng6.ucsd.edu]`

- your terminal should look like this:
  `PS C:\Users\ <*your user name*> ssh <*the course specific account name*>[@ieng6.ucsd.edu]`

4. enter your course specific password (or your SSO password, by default)

> 💡 The terminal will not display the password you entered. **DONT paste your
> password use `control + v`, it will input ^V, not your clipboard. Use right
> click instead**

if you login successfully, your terminal should look like that:

![Untitled](<Lab Report 1 sources/Untitled 2.png>)

# Run some remote commands:

try to run some remote commands like these:

> `cd ~`

> `cd`

> `ls -lat`

> `ls -a`

> `ls <directory>` where `<directory>` is `/home/linux/ieng6/cs15lwi23/cs15lwi23abc`,
> where the `abc` is one of the other group members’ username

> `cp /home/linux/ieng6/cs15lwi23/public/hello.txt ~/`

> `cat /home/linux/ieng6/cs15lwi23/public/hello.txt`

### example: run ls -lat:

your terminal should look like this:

![Untitled](<Lab Report 1 sources/Untitled%203.png>)
- the `ls` means to list all files
   - `-l` means list one file per line
   - `-a` means all files
   - `-t` means sort by modification time
- so the command means: list all files in the current directory by the modification time, and list one file per line
### Example 2: copy file to remote server (_REMOTE CONNECTING_)

copy the file `HW1.pdf` located at the `~/downloads` folder to the
`~/Homeworks/cse-20/` folder at the remote server
**`cs15lwi23akl@ieng6.ucsd.edu`**

1. first, we need to know what is the current working directory, we get that
   information by typing `pwd`
2. then, we need to make sure the destination directory path exist,
   1. we can login to the remote server and use `mkdir -p <path>` to create a
      path, and copy the file regularly
   - we have to use -p here because the parent directory does not exist, and
     `-p` will create all directories even if they do not exist
3. then, we need to copy that file
   1. we have two ways to locate that file
      1. change the wd to downloads and reference the file with its name
      2. or reference the file use relative path
4. finally use the `scp` statement

<aside>
> Note we CANNOT use cp command to copy a file to a remote server. we have to use scp to copy to a remote server

</aside>

```powershell
#first get current local working directory
PS C:\Users\zilin> pwd

Path
----
C:\Users\zilin

# let's login to the remote server to see if the directory does exist
PS C:\Users\zilin> ssh cs15lwi23akl@ieng6.ucsd.edu
Password:

# use the ls command to see the directory
[cs15lwi23akl@ieng6-201]:~:59$ ls
perl5

# unfortunately the directory does not exit, so we need to create a new directory use mkdir -p <path>
[cs15lwi23akl@ieng6-201]:~:60$ mkdir -p ~/Homeworks/cse-20/
# we can cd into the directory to make sure it is created
[cs15lwi23akl@ieng6-201]:~:61$ cd ~/Homeworks/cse-20/
[cs15lwi23akl@ieng6-201]:cse-20:62$

# now lets log out and just do a normal copy
[cs15lwi23akl@ieng6-201]:cse-20:62$ exit
logout
Connection to ieng6.ucsd.edu closed.

#copy directory
PS C:\Users\zilin> scp downloads/HW1.pdf cs15lwi23akl@ieng6.ucsd.edu:~/Homeworks/cse-20/
Password:
HW1.pdf                                                                    100%  147KB  14.0KB/s   00:10

#check if the file exist
PS C:\Users\zilin> ssh cs15lwi23akl@ieng6.ucsd.edu
[cs15lwi23akl@ieng6-201]:~:51$ cd ~/Homeworks/cse-20/
[cs15lwi23akl@ieng6-201]:cse-20:52$ ls
HW1.pdf #the file exist!!!!!!!
```

![HW](<Lab Report 1 sources/upload_HW.png>)

### logout

you may use key combination `ctrl+D` or runtime command `exit` to logout

# Create Git Repository

1. first create your account here:

[GitHub: Let's build from here](https://github.com/)

1. create a new repository, and name it `cse15l-lab-reports`

![Untitled](<Lab Report 1 sources/Untitled%204.png>)

![Untitled](<Lab Report 1 sources/Untitled%205.png>)

## Make website

- render website with markdown language

[Week 1 lab report](https://kiminus.github.io/cse15l-lab-reports/lab-report-week1)
