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


public class Pass {
	
	Stream<String> lines = Files.lines(Paths.get("data/paths.txt")); //produces a stream of lines
	
	
	List<CRecord> recordList = new ArrayList<CRecord>(); //set pointer to recordArr
	
	
	
	Stream<CRecord> recordStream = ArrayList.stream(recordList).map(((CRecord r) -> r)
								.filter(((CRecord f)->f)
								.isValid==true));
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*My solution:
		1. add all the lines in the text file to a list (I may need to make a new class for this)
		2. set up a stream that filters the results of the list based on 
		whether they fill certain prerequisites listed below
		3. print out the results at the end of this all.
		
		This project will require:
		stream api,
		regex api
		io api
		buffered reading api
		*/
	}

}
