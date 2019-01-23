import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static void main(String args[]) throws IOException{
        try{
            System.out.println("Your first argument is: "+args[0]);
            String field=args[0];
        } catch(ArrayIndexOutOfBoundsException exception) {
            System.out.println("ERROR: No Field Name Specified!\n\nWhile running the program you must pass field name as an argument");
            System.exit(1);
        }

        //TODO: Check the validity of Field Name

        BufferedReader in = new BufferedReader(new FileReader("metadata.txt"));
        String line;
        ArrayList<String[]> metaD = new ArrayList<String[]>(); 

        while ((line = in.readLine()) != null) {
//            System.out.println(line);
            String[] tokens = line.split(",");
            assert tokens.length==3: "Metadata file-format not valid. \nIn one of the rows, found "+tokens.length+" entries instead of 3";
            metaD.add(tokens);
        }
        
//        System.out.println("MetaD has "+metaD.size());
        in.close();
    }
}
