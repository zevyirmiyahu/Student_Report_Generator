package student_report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * This class is responsible for loading the text file and 
 * reading it into an arrayList of strings.
 */

public class TextReader {
		
	// Exact names of text files
	public static String[] fileNames = {"shy", "notShy", "very respectful", "respectful", "ok", "poor",
										"excellent", "good", "medium", "bad",
										"filler", "hope", "transitionWords_addition", 
										"transitionWords_conclusion"};
	
	/*
	 * Original sizes of arraylist, helps to avoid recopying lines every time generate button is pressed
	 *  MUST UPDATE SIZES IF NEW WORDS/SENTENCES ARE ADDED TO WORD BANKS!
	 */
	private static int shyComments_SIZE = 27;
	private static int notShyComments_SIZE = 26;
	
	private static int veryRespectfulComments_SIZE = 11;
	private static int respectfulComments_SIZE = 6;
	private static int okComments_SIZE = 5;
	private static int poorComments_SIZE = 6;
	
	private static int excellentComments_SIZE = 30;
	private static int goodComments_SIZE = 26;
	private static int mediumComments_SIZE = 45;
	private static int badComments_SIZE = 30;
	
	private static int fillerComments_SIZE = 18;
	private static int hopeComments_SIZE = 35;
	
	private static int transitionWordsAddition_SIZE = 14;
	private static int transitionWordsConclusion_SIZE = 27;

	
	// Comments for shy / not shy student
	private static ArrayList<String> shyComments = new ArrayList<String>();
	private static ArrayList<String> notShyComments = new ArrayList<String>();
	
	// Comments for behavioral/attitude during class
	private static ArrayList<String> veryRespectfulComments = new ArrayList<String>(); //very Respectful comments
	private static ArrayList<String> respectfulComments = new ArrayList<String>(); // Respectful comments
	private static ArrayList<String> okComments = new ArrayList<String>(); // Ok comments
	private static ArrayList<String> poorComments = new ArrayList<String>(); // Poor comments
	 
	// Comments for academic performance
	private static ArrayList<String> excellentComments = new ArrayList<String>(); //Excellent comments
	private static ArrayList<String> goodComments = new ArrayList<String>(); //Good comments
	private static ArrayList<String> mediumComments = new ArrayList<String>(); //Medium comments
	private static ArrayList<String> badComments = new ArrayList<String>(); //Bad comments
	 
	private static ArrayList<String> fillerComments = new ArrayList<String>(); //Filler sentences 
	private static ArrayList<String> hopeComments = new ArrayList<String>(); //Used to present hopes of students success
	
	private static ArrayList<String> transitionWordsAddition = new ArrayList<String>();
	private static ArrayList<String> transitionWordsConclusion = new ArrayList<String>();
	

	public TextReader() {
		for(String e : fileNames) {
			try {
				readFile(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//Reads entire text in file
	public void readFile(String fileName) throws IOException {
		
		//MUST read as stream for jar file!!!!
		InputStream in = this.getClass().getResourceAsStream("res/" + fileName); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		//Below doesn't work in jar MUST  read as stream
		//Files.newBufferedReader((ClassLoader.getSystemResourceAsStream("src/student_report/res/" + fileName)); //searchResource("src/student_report/res/" + fileName);
		//Path p = Paths.get("src/student_report/res/" + fileName);
		//BufferedReader reader = Files.newBufferedReader(p);
		
		while(true) {
			String line = reader.readLine();
			
			if (line == null) break; // hits end of line
						
			if(fileName.equals("shy") && shyComments.size() != shyComments_SIZE) {
				shyComments.add(line);
			}
			else if(fileName.equals("notShy") && notShyComments.size() != notShyComments_SIZE) {
				notShyComments.add(line);
			}
			else if(fileName.equals("very respectful") && veryRespectfulComments.size() != veryRespectfulComments_SIZE) {
				veryRespectfulComments.add(line);
			}
			else if(fileName.equals("respectful") && respectfulComments.size() != respectfulComments_SIZE) {
				respectfulComments.add(line);
			}
			else if(fileName.equals("ok") && okComments.size() != okComments_SIZE) {
				okComments.add(line);
			}
			else if(fileName.equals("poor") && poorComments.size() != poorComments_SIZE) {
				poorComments.add(line);
			}
			else if(fileName.equals("excellent") && excellentComments.size() != excellentComments_SIZE) {
				excellentComments.add(line);
			}
			else if(fileName.equals("good") && goodComments.size() != goodComments_SIZE) {
				goodComments.add(line);
			}
			else if(fileName.equals("medium") && mediumComments.size() != mediumComments_SIZE) {
				mediumComments.add(line);
			}
			else if(fileName.equals("bad") && badComments.size() != badComments_SIZE) {
				badComments.add(line);
			}
			else if(fileName.equals("filler") && fillerComments.size() != fillerComments_SIZE) {
				fillerComments.add(line);
			}
			else if(fileName.equals("hope") && hopeComments.size() != hopeComments_SIZE) {
				hopeComments.add(line);
			}
			else if(fileName.equals("transitionWords_addition") && transitionWordsAddition.size() != transitionWordsAddition_SIZE) {
				transitionWordsAddition.add(line);
			}
			else if(fileName.equals("transitionWords_conclusion") && transitionWordsConclusion.size() != transitionWordsConclusion_SIZE) {
				transitionWordsConclusion.add(line);
			}
			else {
				return;
				//System.err.println("Improper file name used!");
			}
		}
	}
	
	public ArrayList<String> getArrayList(String arrayListName) {
		switch(arrayListName) {
			case "shy" : return shyComments;
			case "notShy" : return notShyComments;
			case "very respectful" : return veryRespectfulComments;
			case "respectful" : return respectfulComments;
			case "ok" : return okComments;
			case "poor" : return poorComments;
			case "excellent" : return excellentComments;
			case "good" : return goodComments;
			case "medium" : return mediumComments;
			case "bad" : return badComments;
			case "filler" : return fillerComments;
			case "hope" : return hopeComments;
			case "transitionWords_addition" : return transitionWordsAddition;
			case "transitionWords_conclusion" : return transitionWordsConclusion;
			default : return null; //if improper name is entered
		}
	}
}
