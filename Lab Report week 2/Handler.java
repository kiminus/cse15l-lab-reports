import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    /**
     * handle an incoming url request
     * @param url the given url (in type URI)
     * @return the display string on the screen
     */
    public String handleRequest(URI url) {
        //if there is not path, display the stored number
        if (url.getPath().equals("/")) {
            return String.format("Number: %d", num);
        }
        //if the path is /increment, increment the number and display the result
        else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        }
        //if the path is add and the query has a variable named "count", increment the number by the count
        else {
            System.out.println("Path: " + url.getPath());
            //if the path has /add
            if (url.getPath().contains("/add")) {
                //get the first query variable name
                String[] parameters = url.getQuery().split("=");
                //if the variable name is count
                if (parameters[0].equals("count")) {
                    //parse the given value and increment the number by that number
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            //otherwise return error message
            return "404 Not Found!";
        }
    }
}