import java.io.IOException;

public class NumberServer {
    public static void main(String[] args) throws IOException {
        //takes the first input parameter as the port number
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        //parse the input string to an integer
        int port = Integer.parseInt(args[0]);

        //start the server
        Server.start(port, new Handler());
    }
}