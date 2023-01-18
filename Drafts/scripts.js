class Person {
  constructor(name) {
    this.name = name;
  }
  sayHello() {
    console.log(`my name is ${this.name}`);
  }
  walk() {
    console.log(`${this.name} is walking`);
  }
}
//inheritance
class Student extends Person {
  constructor(name, age) {
    super(name);
    this.age = age;
  }
  sayHello() {
    let n = "sdf";
    console.log(`student named ${n}`); //we have to use backtick if we want to use template string
  }
}
let s = new Student("ketty", 34);
s.sayHello();
s.walk();

var genRan = function (max) {
  return Math.random() * max;
};
function generateRandomNumber_Debug(max) {
  alert(`random number(0 - ${max}): ` + genRan(max));
}
