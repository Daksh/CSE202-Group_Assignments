import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static void main(String args[]) throws IOException{
    	String inputField="";
    	try{
    		inputField = args[0];
            System.out.println("Input field is: "+inputField);
        } catch(ArrayIndexOutOfBoundsException exception) {
            System.out.println("ERROR: No Field Name Specified!\n\nWhile running the program you must pass field name as an argument");
            System.exit(1);
        }


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
        
        //Checking the validity of Field Name
        boolean presentInMeta = false;
        for(int i=0; i<metaD.size(); i++) {
        	if(inputField.equals(metaD.get(i)[0]))
        		presentInMeta = true;
        }
        if(!presentInMeta) {
        	System.out.println("The input field is not present in the Meta Data.(Field Name is case sensitive) \nPlease try again");
        	System.exit(1);
        }
        
        in = new BufferedReader(new FileReader("db.data"));
        
        in.close();
    }
}
