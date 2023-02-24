rm -rf lab7
git clone https://github.com/ucsd-cse15l-w23/lab7.git
cd lab7
javac -cp ".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar" *.java
java -cp ".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar" org.junit.runner.JUnitCore ListExamplesTests
echo "------------------------------------"
sed -ip '43 s:index1 += 1:index2 += 1:' ListExamples.java
javac -cp ".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar" *.java
java -cp ".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar" org.junit.runner.JUnitCore ListExamplesTests
git init
git remote set-url origin git@github.com:kiminus/draft
git push -d origin temp
git add *
git branch -m temp
git push -u origin temp
echo "------------finished---------------"