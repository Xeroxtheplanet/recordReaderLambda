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


public class Pass { //By Lachlan Miller
	String answer = "";
	int recordsValidated = 0;
	
	class Record { //inner class Record with fields transcribed from map.
		
		String recordStr = "-".repeat(132)+"\n{";
		int fieldsValidated = 0;
		
		/*construct and validate the record here by going through map
		pairs and determining if valid. if the number of fields validated is 7 or more, go it is fine
		*/

		public Record(Map<String,String> recordKVToValidate) throws NumberFormatException {  
			recordKVToValidate.forEach((k, v) -> { //accepts key and value to do whatever, less verbose than constructing iterators
				Integer vVal = 0; //used to 
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
						(k.equals("expires") && Integer.parseInt(v)>2024 && Integer.parseInt(v)<=2034) || //ensure it has not expired and not over ten years into the future
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
						(k.equals("usmca") && (v.matches("^[0-9_]+$") && //only digits
												   	v.length()==9)
						)
					)
					{
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

			recordStr += "\b\b}\n"; //complete the record once each pair has been dealt with and 2 backslash to reduce comma and space at end
			

		}

		String getRecordStr(){ //getter for recordStr
			if(fieldsValidated>=7){ //if the fields validated is seven or more than return the string
				recordsValidated+=1;
				return recordStr;
			}
			return ""; //return nothing if invalid record
		}
	}
	

	public static void main(String[] args) {
		
		try{
			Pass c = new Pass(); // have to make a class to have an inner class to fit submission
			
			Stream<String> lines = Files.lines(Paths.get("data/paths.txt"));
			Stream<String> myStreamOfLines = Arrays.stream(lines.collect(Collectors.joining("\n")).split("\n{2}")); 
			// Parse input into records on basis regex basis \n{2} (two consecutive \n) (note: joining on \n just joins them together so they are treated as one element in the stream)

			lines.close(); //no longer needed so close memory leak
			
			myStreamOfLines.forEach(str -> {
				Map<String,String> obj = Arrays.stream(str.split(" |\n")) //for each string, split on regex twice first on the spaceor a new line then on the kvpair
											.map(kvPair -> kvPair.split(":"))
											.filter(Arr -> Arr.length==2) //in case duplicates for whatever reason eg. : is in a key or value for some reason
											.collect(Collectors.toMap(arr -> arr[0], arr -> arr[1])); //buffer for validation
				Record recordToCheck = c.new Record(obj);
				c.answer+=recordToCheck.getRecordStr();
				
				obj.clear(); //clear for next record

  			});
			

			System.out.println(c.answer + "=".repeat(132) + "\nValid records: "+ c.recordsValidated); //end it with this

		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		


	}

}
