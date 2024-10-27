package milestone3;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.regex.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


public class Pass {
	//List<CRecord> recordList = new ArrayList<CRecord>(); //set pointer to recordArr
	Map<String,String> obj = new HashMap<String,String>();
	

	/*Stream<CRecord> recordStream = ArrayList.stream(recordList).map(((CRecord r) -> r)
								.filter(((CRecord f)->f)
								.isValid==true));*/
	//recommended snippets of code
	
	class Record {
		String recordStr = "{";
		int fieldsValidated = 0; //if the value does not 
		HashMap<String, String> recordkvToValidate;
		
		/*construct and validate the record here by going through hashmap
		pairs and determining if valid. if the number of fields validated is 7
		*/

		public Record(HashMap<String,String> recordKVToValidate){  
			recordKVToValidate.forEach((k, v) -> { //accepts key and value to do whatever, less verbose than constructing iterators
				boolean KVCheck = false;
				Integer vVal = 0;
				String hairAccept = "123456789abcdef#"; //acceptable hex characters for hair
				String licenseAccept = "123456789";
				if(v.contains("cm")){
					vVal = Integer.parseInt(v.replace("cm", "")); //strip of cm if it contains it
				}
				if(v.contains("in")){
					vVal = Integer.parseInt(v.replace("in", "")); //strip of int if it contains it
				}
				if (k.equals("born") && ((2024 - vVal)>21) || //over age 21
					k.equals("issued") && vVal<=2024 && (2024-Integer.parseInt(v))<=10 || //before or on 2024 and was issued less than 10 years ago
					k.equals("expires") && vVal>2024 || //ensure it has not expired
					k.equals("height") && ((vVal>150 && vVal<193) || (vVal>59 && vVal<76)) || //between 150cm and 193cm inclusive or height between 59 and 76 inches
					k.equals("hair") && (
												v.startsWith("#") && 
												hairAccept.indexOf(v)!=-1 && //contains the acceptable characters, would return -1 if no
												v.length()==6
												) ||
					k.equals("eyes") && (v.equals("amber") ||
												  v.equals("blue") ||
												  v.equals("brown") ||
												  v.equals("gray") ||
												  v.equals("green") ||
												  v.equals("hazel") ||
												  v.equals("other")
												  ) ||
					k.equals("usmca") && (licenseAccept.indexOf(v)!=-1 &&
												   v.length()==9)
				)
				{
					System.out.println("valid key found"); //TEST: delete when done
					KVCheck=true; //confirmed that key is valid for validation of pair
					fieldsValidated+=1;
				}
				recordStr += (k+"="); //add the key to the recordStr, regardless of what it is
				recordStr += (v+","); //complete the kv pair
				
			});

			recordStr += "}"; //complete the record once each pair has been dealt with

			}
		}
	
	
	

	
	/* print each record separated by a dashed line

	.peek(str -> {
		System.out.println("-".repeat(132));
		System.out.println(str);  
	})*/
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*My solution:
		1. add all the lines in the text file to a list (I may need to make a new class for this)

		//set up a stream that filters the results of the list based on 
		whether they fill certain prerequisites listed below
		// print out the results at the end of this all.
		
		This project will require:
		stream api,
		regex api
		io api
		buffered reading api
		*/
		try{
			Stream<String> lines = Files.lines(Paths.get("data/paths.txt"));
			//lines.forEach(System.out::println);
			Stream<String> myStreamOfLines = Arrays.stream(lines.collect(Collectors.joining("\n")).split("\n{2}")); // Parse input into records on basis \n
			//System.out.println(myStreamOfStrings.findFirst().toString().charAt(0));
			myStreamOfLines.forEach(System.out::println);

			Map<String,String> obj = new HashMap<String,String>();
			myStreamOfLines.forEach(str -> {
    			String[] parts = str.split(":");
    			obj.put(parts[0], parts[1]);
  			});

		} catch (Exception e){
			e.printStackTrace();
		}//produces a stream of lines
		
		
		// Parse colon separated key/value pairs into a HashMap
		
		//The hashmap obj has the string elements delimited by : 
		
		
		


	}

}
