var genRan = function (max) {
  return Math.random() * max;
};
function generateRandomNumber_Debug(max) {
  alert(`random number(0 - ${max}): ` + genRan(max))
}
