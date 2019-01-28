package student_report;

import java.util.ArrayList;
import java.util.Random;

/*
 * This class handles combining all strings and information selected
 * to create the 150+ word reports about the student
 */

public class ReportMaker {
	
	private Random ranNum = new Random();
	
	private TextReader tr;
		
	private String report = "";
	private String hopeString = "";
	private String additionWord = "";
	private String conclusionWord = "";
	
	private ArrayList<String> randomShyComments = new ArrayList<String>();
	private ArrayList<String> randomNotShyComments = new ArrayList<String>();
	private ArrayList<String> randomBehaviorComments = new ArrayList<String>();
	private ArrayList<String> randomAcademicComments = new ArrayList<String>();
	private ArrayList<String> fillerComments = new ArrayList<String>();
	private ArrayList<String> hopeComments = new ArrayList<String>();
	private ArrayList<String> transitionWordsAddition = new ArrayList<String>();
	private ArrayList<String> transitionWordsConclusion = new ArrayList<String>();
	
	//Ensure repetition in comments aren't chosen these arrayLists keep track of used indices
	private ArrayList<Integer> usedRandomBehaviorComments = new ArrayList<Integer>();
	private ArrayList<Integer> usedRandomAcademicComments = new ArrayList<Integer>();
	private ArrayList<Integer> usedFillerComments = new ArrayList<Integer>();
		
	public ReportMaker(String studentName, boolean isMale, 
			String behavior, String academicPerformance) {
		
		tr = new TextReader(); //Initialize all files by reading text files
		
		//Set values in arrayList
		randomShyComments = tr.getArrayList("shy");
		randomNotShyComments = tr.getArrayList("notShy");
		randomBehaviorComments = tr.getArrayList(behavior);
		randomAcademicComments = tr.getArrayList(academicPerformance);
		fillerComments = tr.getArrayList("filler");
		hopeComments = tr.getArrayList("hope");
		transitionWordsAddition = tr.getArrayList("transitionWords_addition");
		transitionWordsConclusion = tr.getArrayList("transitionWords_conclusion");
		
		//Choose random transition word and set them for class
		Random ran = new Random();
		int n1 = ran.nextInt(transitionWordsAddition.size()); 
		int n2 = ran.nextInt(transitionWordsConclusion.size()); 

		additionWord = transitionWordsAddition.get(n1);
		conclusionWord = transitionWordsConclusion.get(n2);
		
		hopeString = makeHopeString(studentName);//generates hopeString
	}
	
	//For making string for hope comment
	public String makeHopeString(String name) {
		String r = "";
		Random ran = new Random();
		int randomIndex = ran.nextInt(hopeComments.size() - 1);
		String s = hopeComments.get(randomIndex);
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '%') {
				r = r + s.substring(0, i) + name + s.substring(i + 1, s.length() - 1);
			}
		}
		return r;
	}
	
	// General method for generating indices for random comments from previously made arraylists.
	private int generateComments(ArrayList<String> randomComments, ArrayList<Integer> usedRandomComments) {

		int randomIndex = ranNum.nextInt(randomComments.size());
		
		if(usedRandomComments.size() == randomComments.size()) usedRandomComments.clear(); //reset if filled
		
		while(true) {
			if(!usedRandomComments.contains(randomIndex)) {
				usedRandomComments.add(randomIndex);
				return randomIndex;
			} else {
				randomIndex = ranNum.nextInt(randomComments.size());
			}
		}
	}
	
	private int generateShyComments() {
		
		Random ran = new Random();
		int randomIndex = ran.nextInt(randomShyComments.size());
		return randomIndex;
	}
	
	private int generateNotShyComments() {
		
		Random ran = new Random();
		int randomIndex = ran.nextInt(randomNotShyComments.size());
		return randomIndex;
	}
	
	private String firstCharacterToLowerCase(String str) {
		if(str == null || str.length() == 0)
            return "";
        
        if(str.length() == 1)
            return str.toLowerCase();
        
        char[] chArr = str.toCharArray();
        chArr[0] = Character.toLowerCase(chArr[0]);
        
        return new String(chArr); 
	}
	
	public String generateReport(String intro, String studentName, boolean isMale, boolean isShy, String optionalComment) {
		
		String shyText = ""; // text holds whether or not student is shy
		
		// Check if student is shy
		if(isShy) 
			shyText = "Firstly, " + studentName + " " + randomShyComments.get(generateShyComments());
		else 
			shyText = "Firstly, " + studentName + " " + randomNotShyComments.get(generateNotShyComments());
		
		if(isMale) {
			int bc1_index = generateComments(randomBehaviorComments, usedRandomBehaviorComments); // behavior comments indices
			int bc2_index = generateComments(randomBehaviorComments, usedRandomBehaviorComments);
			
			int ac1_index = generateComments(randomAcademicComments, usedRandomAcademicComments); // academic comments indices
			int ac2_index = generateComments(randomAcademicComments, usedRandomAcademicComments);
			int ac3_index = generateComments(randomAcademicComments, usedRandomAcademicComments);
			int ac4_index = generateComments(randomAcademicComments, usedRandomAcademicComments);

			int fil0_index = generateComments(fillerComments, usedFillerComments); // filler comments indices
			int fil1_index = generateComments(fillerComments, usedFillerComments);
			int fil2_index = generateComments(fillerComments, usedFillerComments);
			int fil3_index = generateComments(fillerComments, usedFillerComments);
			
			String afterConclusionWordString 
					= firstCharacterToLowerCase(fillerComments.get(fil0_index));
			
			report = intro
					+ " " + shyText
					+ " He " + randomBehaviorComments.get(bc1_index)
					+ " He " + randomBehaviorComments.get(bc2_index)
					+ " " + studentName + " " + randomAcademicComments.get(ac1_index) 
					+ " He " + randomAcademicComments.get(ac2_index) 
					+ " He " + randomAcademicComments.get(ac3_index) 
					+ " " + additionWord 
					+ " he " + randomAcademicComments.get(ac4_index)
					+ " " + fillerComments.get(fil1_index) 
					+ " " + fillerComments.get(fil2_index)
					+ " " + fillerComments.get(fil3_index) 
					+ " " + conclusionWord 
					+ " " + afterConclusionWordString
					+ " " + hopeString + "."
					+ " " + optionalComment;
		}
		else {
			
			int bc1_index = generateComments(randomBehaviorComments, usedRandomBehaviorComments); // behavior comments indices
			int bc2_index = generateComments(randomBehaviorComments, usedRandomBehaviorComments);
			
			int ac1_index = generateComments(randomAcademicComments, usedRandomAcademicComments); // academic comments indices
			int ac2_index = generateComments(randomAcademicComments, usedRandomAcademicComments);
			int ac3_index = generateComments(randomAcademicComments, usedRandomAcademicComments);
			int ac4_index = generateComments(randomAcademicComments, usedRandomAcademicComments);

			int fil0_index = generateComments(fillerComments, usedFillerComments); // filler comments indices
			int fil1_index = generateComments(fillerComments, usedFillerComments);
			int fil2_index = generateComments(fillerComments, usedFillerComments);
			int fil3_index = generateComments(fillerComments, usedFillerComments);
			
			String afterConclusionWordString 
			= firstCharacterToLowerCase(fillerComments.get(fil0_index));
			report = intro
					+ " " + shyText
					+ " She " + randomBehaviorComments.get(bc1_index)
					+ " She " + randomBehaviorComments.get(bc2_index)
					+ " " + studentName + " " + randomAcademicComments.get(ac1_index) 
					+ " She " + randomAcademicComments.get(ac2_index) 
					+ " She " + randomAcademicComments.get(ac3_index) 
					+ " " + additionWord
					+ " she " + randomAcademicComments.get(ac4_index)
					+ " " + fillerComments.get(fil1_index) 
					+ " " + fillerComments.get(fil2_index)
					+ " " + fillerComments.get(fil3_index)
					+ " " + conclusionWord
					+ " " + afterConclusionWordString
					+ " " + hopeString + "."
					+ " " + optionalComment;
		}
		//Must clear arrayLists to avoid double choosing values and
		randomAcademicComments.clear();
		fillerComments.clear();
		hopeComments.clear();
		transitionWordsAddition.clear();
		transitionWordsConclusion.clear();
		
		return report;
		
	}
}
