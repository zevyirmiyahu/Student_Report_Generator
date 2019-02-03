package student_report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/*
 * This program will generate a report of about 150 words for student report
 * cards. It takes it certain criteria of a student(name, gender, performance. etc.)
 * and returns a quality report based on randomly selected comments from arrays 
 * organized on performance. Proper pronouns are maintained throughout the report.
 * Additionally, random strings are chosen in a way to avoid a mechanical sounding 
 * report or redundancies in sentences. 
 * 
 * 
 * Author Zev Yirmiyahu
 * 
 * E-Mail: zy@zeyirmiyahu.com
 * 
 * GitHub: https://github.com/zevyirmiyahu 
 * 
 * Personal Website: www.zevyirmiyahu.com
 */

public class Viewer implements ActionListener, FocusListener {
	
	private static boolean isMale = false;
	private static boolean isShy = false;
	private String behavior = "";
	private String performance = "";
	
	private JLabel imageLabel; 
  
	private JFrame frame = new JFrame("Student Report Generator | Version 2.0");
	private JFrame helpFrame = new JFrame("Help");
	private JFrame reportFrame = new JFrame("Student Report");
	
	private JTextArea textArea = new JTextArea(6, 25); // for optional intro text
	private JTextArea answer = new JTextArea(35, 40);
	private JTextArea helpText = new JTextArea(11, 28);
	
	private JScrollPane scrollPane;
	
	private JPanel panel2 = new JPanel();
	
	private JTextField studentName = new JTextField("Enter name of student", 15);
	private JTextField extraCommentField = new JTextField("Optional Comment", 33);
	
	private JLabel student = new JLabel("Student's name:");
	private JLabel title = new JLabel("Student Report Generator");
	private JLabel helpTextTitle = new JLabel("  About this Program");
	
	private JLabel gender = new JLabel("   Gender: "); //Space to wrap to next line using flowLayout
	private JLabel shy = new JLabel("Is the student shy?");
	private JLabel behaviorPerformance = new JLabel("How was the student's attitude/behavior?");
	private JLabel academicPerformance = new JLabel("How was the student's academic performance?");
	private JLabel error = new JLabel("ERROR: Must select a field for each option ");
	private JLabel blank1 = new JLabel("     ");
	private JLabel blank2 = new JLabel("   "); //A barbaric way used for alignment
	
	private JCheckBox male = new JCheckBox("Male");
	private JCheckBox female = new JCheckBox("Female");
	private JCheckBox yesShy = new JCheckBox("Yes");
	private JCheckBox noShy = new JCheckBox("No");
	private JCheckBox excellentBoxBehavior = new JCheckBox("Very Respectful", false);
	private JCheckBox goodBoxBehavior = new JCheckBox("Respectful", false);
	private JCheckBox mediumBoxBehavior = new JCheckBox("Ok", false);
	private JCheckBox badBoxBehavior = new JCheckBox("Poor", false);
	private JCheckBox excellentBox = new JCheckBox("Excellent", false);
	private JCheckBox goodBox = new JCheckBox("Good", false);
	private JCheckBox mediumBox = new JCheckBox("Medium", false);
	private JCheckBox badBox = new JCheckBox("Bad", false);

	private JButton help = new JButton("Help");
	private JButton generate = new JButton("Generate Text");

	// main frame dimensions
	private int width = 405;
	private int height = 680;
	
	// help frame dimensions
	private int helpFrameWidth = 330;
	private int helpFrameHeight = 650;
	
	// output frame dimensions
	private int outputFrameWidth = 380;
	private int outputFrameHeight = 580;
	
	
	//private Font titleFont = new Font("Verdana", 1, 25);
	private Font textFont = new Font("Verdana", 0, 15);
	private Font checkBoxFont = new Font("Verdana", 0, 13);
	private Font buttonFont = new Font("Verdana", 1, 11);
	private Font fieldFont = new Font("Verdana", 0 , 11);
	
	public Viewer() {
	
		try{
			BufferedImage im = ImageIO.read(Viewer.class.getResource("/student_report/res/reports_image.png"));
			ImageIcon image = new ImageIcon(im);
			ImageIcon resizedImage = new ImageIcon(image.getImage().getScaledInstance(120, 120 - 50, Image.SCALE_DEFAULT));
			imageLabel = new JLabel(resizedImage);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		textArea.addFocusListener(this);
		studentName.addFocusListener(this);
		extraCommentField.addFocusListener(this);
		male.addActionListener(this);
		female.addActionListener(this);
		yesShy.addActionListener(this);
		noShy.addActionListener(this);
		excellentBoxBehavior.addActionListener(this);
		goodBoxBehavior.addActionListener(this);
		mediumBoxBehavior.addActionListener(this);
		badBoxBehavior.addActionListener(this);
		excellentBox.addActionListener(this);
		goodBox.addActionListener(this);
		mediumBox.addActionListener(this);
		badBox.addActionListener(this);
		
		textArea.setText("Optional Intro Text");
		
		title.setFont(new Font("Verdana", 1, 20));
		student.setFont(textFont);
		blank1.setFont(new Font("Verdana", 1, 20));
		blank2.setFont(new Font("Verdana", 1, 20));
		gender.setFont(textFont);
		shy.setFont(textFont);
		yesShy.setFont(textFont);
		noShy.setFont(textFont);
		behaviorPerformance.setFont(textFont);
		academicPerformance.setFont(textFont);
		male.setFont(textFont);
		female.setFont(textFont);
		error.setFont(textFont);
		excellentBoxBehavior.setFont(checkBoxFont);
		goodBoxBehavior.setFont(checkBoxFont);
		mediumBoxBehavior.setFont(checkBoxFont);
		badBoxBehavior.setFont(checkBoxFont);
		excellentBox.setFont(checkBoxFont);
		goodBox.setFont(checkBoxFont);
		mediumBox.setFont(checkBoxFont);
		badBox.setFont(checkBoxFont);

		helpTextTitle.setFont(new Font("Verdana", 0, 18));
		helpText.setBackground(Color.LIGHT_GRAY);

		helpText.setFont(textFont);
		helpText.setBackground(Color.LIGHT_GRAY);
		helpText.setEditable(false);
		helpText.setText("\n   This program will generate a high "
						+ " \n  quality 120 to 150 random word "
						+ " \n  text for the use of student report "
						+ " \n  cards. The random text is put together "
						+ " \n  in away to ensure a natural style text"
						+ " \n  and avoids an repetition in sentences. "
						+ "\n  The user has an option to add an "
						+ "\n  original text to be placed at the"
						+ "\n  beginning and add an optional "
						+ "\n  comment to the end of the generated"
						+ "\n  text."
						+ " \n"
						+ "\n Author: Zev Yirmiyahu"
						+ "\n E-mail: zy@zevyirmiyahu.com"
						+ "\n GitHub: https://github.com/zevyirmiyahu"
						+ "\n Personal Site: http://zevyirmiyahu.com/"
						+ "\n"
						+ " Version 2.0"
						+ "\n"
						+ "\n Updates:"
						+ "\n ~ Fixed null bug"
						+ "\n ~ Greatly increased sentence word banks"
						+ "\n ~ Added shy option"
						+ "\n ~ Split performance category"
						+ "\n ~ Enhanced code efficiency"
						+ "\n ~ Enhanced UI");  	
		
		textArea.setFont(textFont);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		textArea.setLineWrap(true);
		
		scrollPane = new JScrollPane(textArea);
		
		generate.setBackground(Color.LIGHT_GRAY);
		generate.setFont(buttonFont);
		generate.addActionListener(this);
		
		help.setBackground(Color.LIGHT_GRAY);
		help.setFont(buttonFont);
		help.addActionListener(this);
		
		studentName.setFont(fieldFont);
		extraCommentField.setFont(fieldFont);
		
		//Set JFrames
		frame.setSize(width, height);
		frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		helpFrame.setSize(helpFrameWidth, helpFrameHeight);
		helpFrame.setLayout(new BorderLayout());
		helpFrame.setBackground(Color.LIGHT_GRAY);
		helpFrame.setLocationRelativeTo(null);
		helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		reportFrame.setSize(outputFrameWidth, outputFrameHeight);
		reportFrame.setLocationRelativeTo(null);
		reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		answer.setFont(textFont);
		answer.setLineWrap(true);	
	
		//Add components to JFrame
		//frame.add(title);
	    frame.add(imageLabel);
		frame.add(scrollPane);
		frame.add(student);
		frame.add(studentName);
		frame.add(blank1);
		frame.add(gender);
		frame.add(male);
		frame.add(female);
		frame.add(shy);
		frame.add(yesShy);
		frame.add(noShy);
		frame.add(behaviorPerformance);
		frame.add(excellentBoxBehavior);
		frame.add(goodBoxBehavior);
		frame.add(mediumBoxBehavior);
		frame.add(badBoxBehavior);
		frame.add(academicPerformance);
		frame.add(excellentBox);
		frame.add(goodBox);
		frame.add(mediumBox);
		frame.add(badBox);
		frame.add(extraCommentField);
		frame.add(blank2);
		frame.add(help);
		frame.add(generate);
		frame.add(error);
		
		helpFrame.add(helpTextTitle, BorderLayout.NORTH);
		helpFrame.add(helpText, BorderLayout.CENTER);
		
		reportFrame.add(panel2.add(answer));
		
		error.setVisible(false);
		
		frame.setFocusable(true); //Starts program with focus on jFrame and not text area
		frame.setResizable(false);
		frame.setVisible(true);
		
		helpFrame.setResizable(false);
		helpFrame.setVisible(false);
		
		reportFrame.setResizable(false);
		reportFrame.setVisible(false);
	}
	
	//Method returns scale down factor for screen to
	//set proper size of app for high res and low res screens
	public boolean smallResDisplay() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		if(screenWidth < 1920 || screenHeight < 1080) {
			return true;
		}
		else return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand().equals("Male")) {
			female.setSelected(false);
			isMale = true;
		}
		
		if(ae.getActionCommand().equals("Female")) {
			male.setSelected(false);
			isMale = false;
		}
		
		if(ae.getActionCommand().equals("Yes")) {
			noShy.setSelected(false);
			isShy = true;
		}
		
		if(ae.getActionCommand().equals("No")) {
			yesShy.setSelected(false);
			isShy = false;
		}
		
		if(ae.getActionCommand().equals("Very Respectful")) {
			goodBoxBehavior.setSelected(false);
			mediumBoxBehavior.setSelected(false);
			badBoxBehavior.setSelected(false);
			behavior = "very respectful";
		}
		
		if(ae.getActionCommand().equals("Respectful")) {
			excellentBoxBehavior.setSelected(false);
			mediumBoxBehavior.setSelected(false);
			badBoxBehavior.setSelected(false);
			behavior = "respectful";
		}
		
		if(ae.getActionCommand().equals("Ok")) {
			excellentBoxBehavior.setSelected(false);
			goodBoxBehavior.setSelected(false);
			badBoxBehavior.setSelected(false);
			behavior = "ok";
		}
		
		if(ae.getActionCommand().equals("Poor")) {
			excellentBoxBehavior.setSelected(false);
			goodBoxBehavior.setSelected(false);
			mediumBoxBehavior.setSelected(false);
			behavior = "poor";
		}
		
		if(ae.getActionCommand().equals("Excellent")) {
			goodBox.setSelected(false);
			mediumBox.setSelected(false);
			badBox.setSelected(false);
			performance = "excellent";
		}
		
		if(ae.getActionCommand().equals("Good")) {
			excellentBox.setSelected(false);
			mediumBox.setSelected(false);
			badBox.setSelected(false);
			performance = "good";
		}
		
		if(ae.getActionCommand().equals("Medium")) {
			excellentBox.setSelected(false);
			goodBox.setSelected(false);
			badBox.setSelected(false);
			performance = "medium";
		}
		
		if(ae.getActionCommand().equals("Bad")) {
			excellentBox.setSelected(false);
			goodBox.setSelected(false);
			mediumBox.setSelected(false);
			performance = "bad";
		}
		
		if(ae.getActionCommand().equals("Help")) {
			helpFrame.setVisible(true);
		}
		if(ae.getActionCommand().equals("Generate Text")) {
			if(!male.isSelected() && !female.isSelected()) {
				error.setVisible(true);
			}
			else if(!yesShy.isSelected() && !noShy.isSelected()) {
				error.setVisible(true);
			}
			else if(!excellentBoxBehavior.isSelected() 
					&& !goodBoxBehavior.isSelected() && !mediumBoxBehavior.isSelected() 
					&& !badBoxBehavior.isSelected()) {
				error.setVisible(true);
				
			}
			else if(!excellentBox.isSelected() 
					&& !goodBox.isSelected() && !mediumBox.isSelected() 
					&& !badBox.isSelected()) {
				error.setVisible(true);
			}
			else {
				error.setVisible(false);
				String userIntroText = textArea.getText();
				String name = studentName.getText();
				String userOptionalComment = extraCommentField.getText();
				
				//Check correct fields
				if(userIntroText.equals("Optional Intro Text")) userIntroText = "";
				if(userOptionalComment.equals("Optional Comment")) userOptionalComment = "";
				if(name.equals("Enter name of student") || name.equals("")) {
					studentName.setFont(new Font("Verdana", 1, 14));
					studentName.setText(" ***MUST ENTER A NAME***");
					studentName.setForeground(Color.WHITE);
					studentName.setBackground(Color.RED);
				}
				else {
					ReportMaker rm = new ReportMaker(name, true, behavior, performance);
					String output = rm.generateReport(userIntroText, name, isMale, isShy, userOptionalComment);
					answer.setText(output);
					
					reportFrame.setVisible(true);
				}
			}
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
		if(e.getSource().equals(textArea)) {
			if(textArea.getText().equals("Optional Intro Text")) {				
				textArea.setText("");
			}
		}
		
		if(e.getSource().equals(studentName)) {
			if(studentName.getText().equals("Enter name of student") || studentName.getText().equals(" ***MUST ENTER A NAME***")) {
				studentName.setFont(new Font("Verdana", 0, 14));
				studentName.setText("");
				studentName.setForeground(Color.BLACK);
				studentName.setBackground(Color.WHITE);				
			}
		}
		
		if(e.getSource().equals(extraCommentField)) {
			if(extraCommentField.getText().equals("Optional Comment")) {
				extraCommentField.setText("");				
			}
		}
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		
		if(e.getComponent().equals(textArea)) {
			if(textArea.getText().equals("")) {				
				textArea.setText("Optional Intro Text");
			}
		}
		
		if(e.getSource().equals(studentName)) {
			if(studentName.getText().equals("")) {
				studentName.setText("Enter name of student");
			}
		}
		
		if(e.getSource().equals(extraCommentField)) {
			if(extraCommentField.getText().equals("")) {				
				extraCommentField.setText("Optional Comment");
			}
		}
	}
	
	public static void main(String args[]) {
		new Viewer();
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Viewer();
			}
		});
		*/
	}
}
