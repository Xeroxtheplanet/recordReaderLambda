package milestone3;
import java.util.stream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex;
import java.io;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.Buffered;
import java.nio.files.Paths.*;


public class Pass {
	//List<CRecord> recordList = new ArrayList<CRecord>(); //set pointer to recordArr
	
	

	/*Stream<CRecord> recordStream = ArrayList.stream(recordList).map(((CRecord r) -> r)
								.filter(((CRecord f)->f)
								.isValid==true));*/
	//recommended snippets of code
	Stream<String> lines = Files.lines(Paths.get("data/paths.txt")); //produces a stream of lines

	// Parse input into records separataed by a blank line
	Arrays.stream(lines.collect(Collectors.joining("\n")).split("\n{2}"));

	// Parse colon separated key/value pairs into a HashMap
	Map<String,String> obj = new HashMap<String,String>();
	Arrays.stream(r.replace("\n", " ")
    .split(" "))
    .forEach(str -> {
    String[] parts = str.split(":");
    obj.put(parts[0], parts[1]);
    });

	// print each record separated by a dashed line

	.peek(str -> {
		System.out.println("-".repeat(132));
		System.out.println(str);  
	})
	

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
	}

}
