import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main{
    public static void main(String args[]) throws IOException{
        //System.out.println("Your first argument is: "+args[0]);
        //String field=args[0];

        BufferedReader reader;
        reader = new BufferedReader(new FileReader("metadata.txt"));
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();
    }
}
