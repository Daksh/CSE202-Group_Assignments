import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static void main(String args[]) throws IOException{
    	String inputField="";
    	try{
    		inputField = args[0];
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
            System.out.println("Field: "+tokens[0]+", type: "+tokens[1]+", size: "+tokens[2]);
            metaD.add(tokens);
        }
        in.close();
        System.out.println("Finish reading data description file....\n");
        
        //Checking the validity of Field Name
        boolean presentInMeta = false;
        for(int i=0; i<metaD.size(); i++) {
        	if(inputField.equals(metaD.get(i)[0]))
        		presentInMeta = true;
        }
        
        in = new BufferedReader(new FileReader("db.data"));
//        String line;
        //Maybe print a header (from MetaD) containing the field titles
        
        double maxVal=-1*Double.MAX_VALUE;
    	double minVal=Double.MAX_VALUE;
    	
    	System.out.println("The data file contains these records:");
        while ((line = in.readLine()) != null) {
        	int startIndex=0;
        	for(int i=0; i<metaD.size(); i++) {
            	String part = line.substring(startIndex, startIndex+Integer.parseInt(metaD.get(i)[2]));
            	System.out.print(part+"\t");
            	startIndex+=Integer.parseInt(metaD.get(i)[2]);
//            	System.out.println("'"+metaD.get(i)[1]+"'");
//            	System.out.println("'"+inputField+"'");
            	if(metaD.get(i)[0].equals(inputField)) {
            		double thisTime = Double.parseDouble(part);
//            		System.out.println("thisTime: "+thisTime+"\tmaxVal: "+maxVal);
            		if(thisTime<minVal)
            			minVal=thisTime;
            		if(thisTime>maxVal)
            			maxVal=thisTime;
            	}
            }
        	System.out.println();
        }
        in.close();
        System.out.println();
        
//        System.out.println("MaxVal: "+maxVal+"\t MinVal: "+minVal);
//        System.out.println("Max Diff: "+(maxVal-minVal));
        System.out.println("Find max value in the field "+inputField);
        if(!presentInMeta) {
        	System.out.println("--- Error: field name not found.");
        	System.exit(1);
        }
        System.out.println("Max = "+maxVal);
    }
}
