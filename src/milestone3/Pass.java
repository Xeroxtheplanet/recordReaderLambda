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
import java.util.regex.*;
import java.lang.Object.*;


public class Pass {
	String answer = "-".repeat(132)+"\n";
	//List<CRecord> recordList = new ArrayList<CRecord>(); //set pointer to recordArr
	//Map<String,String> obj = new HashMap<String,String>();
	

	/*Stream<CRecord> recordStream = ArrayList.stream(recordList).map(((CRecord r) -> r)
								.filter(((CRecord f)->f)
								.isValid==true));*/
	//recommended snippets of code
	
	class Record { //inner class Record with fields transcribed from hashmap.
		
		String recordStr = "{";
		int fieldsValidated = 0; //if the value does not 
		//HashMap<String, String> recordkvToValidate; //pointer to hashmap
		
		/*construct and validate the record here by going through hashmap
		pairs and determining if valid. if the number of fields validated is 7
		*/

		public Record(Map<String,String> recordKVToValidate) throws NumberFormatException {  
			recordKVToValidate.forEach((k, v) -> { //accepts key and value to do whatever, less verbose than constructing iterators
				boolean KVCheck = false;
				Integer vVal = 0;
				String hairAccept = "123456789abcdef#"; //acceptable hex characters for hair
				String licenseAccept = "0123456789";
				if(v.contains("cm")){
					vVal = Integer.parseInt(v.replace("cm", "")); //strip of cm if it contains it
					vVal = vVal.intValue();//return int primitive value
				}
				if(v.contains("in")){
					vVal = Integer.parseInt(v.replace("in", "")); //strip of in if it contains it
					vVal = vVal.intValue(); //return int primitive value
				}
				try{
					if ((k.equals("born") && ((2024 - vVal)>21)) || //over age 21
						(k.equals("issued") && Integer.parseInt(v)<=2024 && (2024-Integer.parseInt(v))<=10) || //before or on 2024 and was issued less than 10 years ago
						(k.equals("expires") && Integer.parseInt(v)>2024) || //ensure it has not expired
						(k.equals("height") && ((vVal>150 && vVal<193) || (vVal>59 && vVal<76))) || //between 150cm and 193cm inclusive or height between 59 and 76 inches
						(k.equals("hair") && (
													v.startsWith("#") && 
													v.matches("^[#a-f0-9_]+$") && //contains the acceptable characters
													v.length()==7
													)) ||
						(k.equals("eyes") && (v.equals("amber") ||
												  	v.equals("blue") ||
												  	v.equals("brown") ||
												  	v.equals("gray") ||
												  	v.equals("green") ||
												  	v.equals("hazel") ||
												  	v.equals("other")
												  	)) ||
						(k.equals("usmca") && (licenseAccept.indexOf(v)!=-1 &&
												   	v.length()==9)
						)
					)
					{
						System.out.println(k+ " was valid key found value " + v); //TEST: delete when done
						KVCheck=true; //confirmed that key is valid for validation of pair
						fieldsValidated+=1;
					}
				} catch (NumberFormatException e){
					System.out.println("NumberformatException detected");
				}
				finally{
					recordStr += (k+"="); //add the key to the recordStr, regardless of what it is
					recordStr += (v+", "); //complete the kv pair
					
				}
			});

			recordStr += "\b\b}"; //complete the record once each pair has been dealt with and 2 backslash to reduce comma and space at end
			

		}

		String getRecordStr(){ //getter for recordStr
			if(fieldsValidated>=7){ //if the fields validated is seven or more than return the string
				return recordStr;
			}
			return recordStr+"\n"; //if null, it is not a valid record so add nothing
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
			Pass c = new Pass(); // have to make a class to have an inner class
			
			Stream<String> lines = Files.lines(Paths.get("data/paths.txt"));
			//lines.forEach(System.out::println);
			Stream<String> myStreamOfLines = Arrays.stream(lines.collect(Collectors.joining("\n")).split("\n{2}")); 
			// Parse input into records on basis regex basis \n{2} (two consecutive \n) (note: joining on \n just joins them together so they are treated as one element in the stream)

			
			
			myStreamOfLines.forEach(str -> {
				Map<String,String> obj = Arrays.stream(str.split(" |\n")) //for each string, split on regex twice first on the spaceor a new line then on the kvpair
											.map(kvPair -> kvPair.split(":"))
											.filter(Arr -> Arr.length==2)
											.collect(Collectors.toMap(arr -> arr[0], arr -> arr[1])); //buffer for validation
    			//String[] parts = str.split(":"); //split each section on basis of delimiter : not needed anymore
    			//obj.put(parts[0], parts[1]);
				Record recordToCheck = c.new Record(obj);
				c.answer+=recordToCheck.getRecordStr();
				c.answer+="TEST: Fields validated for record is " + recordToCheck.fieldsValidated+"\n";
				c.answer+="-".repeat(132)+"\n";
				//System.out.println(obj.get("hair"));
				//TEST:
				
				obj.clear(); //clear for next record

  			});
			

			System.out.println(c.answer);
			



		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		


	}

}
