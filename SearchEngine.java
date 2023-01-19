import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> queries = new ArrayList<String>();

    public String handleRequest(URI url) {

        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                queries.add(parameters[1]);
                return "Query added!";
            }
        } else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            ArrayList<String> results = new ArrayList<String>();
            for (int i = 0; i < queries.size(); i++) {
                if (queries.get(i).contains(parameters[1])) {
                    results.add(queries.get(i));
                }
            }
            return results.toString();
        } 
        return "404 error :(";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
