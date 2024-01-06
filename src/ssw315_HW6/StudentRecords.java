package ssw315_HW6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.stream.events.Namespace;

public class StudentRecords {
	
	public static class Student{
		private int id;
		private String firstName;
		private String middleName;
		private String lastName;
		
		public Student(int id, String firstName, String middleName, String lastName) {
			this.id = id;
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
		}
		
		public int getId() {
			return this.id;
		}
		
		public String getFirstName() {
			return this.firstName;
		}
		
		public String getMiddleName() {
			return this.middleName;
		}
		
		public String getLastName() {
			return this.lastName;
		}
		
		public String toString() {
			return "ID: " + id + ", Name: " + firstName + " " + middleName + " " + lastName;
		}
		
	}
	
	static final int STUDENTNUM = 100000;
	static final int TIME = 1000000;
	
	private static long readNames(File file, Collection<String> names) throws FileNotFoundException {
		long timeStart = System.nanoTime();
		
		Scanner fileContents = new Scanner(file);
		while (fileContents.hasNext()) {
			String contents = fileContents.nextLine();
			names.add(contents);
		}
		fileContents.close();
		
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	private static long generateStudentData(List<String> firstNames, List<String> lastNames, List<Student> students) {
		long timeStart = System.nanoTime();
		
		String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V" ,"W", "X", "Y", "Z"};
		int first = firstNames.size();
		int last = lastNames.size();
		
		for (int i = 1; i <= STUDENTNUM; i++) { //makes id creation simpler (is literally i)
			int fName = (int)(Math.random()*first); //picks between 0 and size-1 for index of first name
			int lName = (int)(Math.random()*last); //does the same as above but for last name			
			
			int chance = (int)(Math.random()*2);
			String initial = "";
			switch(chance) { //50% chance to choose between random letter and J
			case 0:
				int letter = (int)(Math.random()*26);
				initial = alphabet[letter];
				break;
			case 1:
				initial = "J";
				break;
			default:
				System.out.println("I'm not sure how this happened. Guess your middle initial is \"z\" now");
				initial = "z";
			}	
			
			students.add(new Student(i, firstNames.get(fName), initial, lastNames.get(lName)));
		}
		
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	private static long generateStudentData(Set<String> firstNames, Set<String> lastNames, Map<Long, Student> idMap) {
		long timeStart = System.nanoTime();
		
		String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V" ,"W", "X", "Y", "Z"};
		int first = firstNames.size();
		int last = lastNames.size();
		
		
		for (int i = 1; i <= STUDENTNUM; i++) { //makes id creation simpler (is literally i)
			int fName = (int)(Math.random()*first); //picks between 0 and size-1 for index of first name
			int lName = (int)(Math.random()*last); //does the same as above but for last name			
			
			int chance = (int)(Math.random()*2);
			String initial = "";
			switch(chance) { //50% chance to choose between random letter and J
			case 0:
				int letter = (int)(Math.random()*26);
				initial = alphabet[letter];
				break;
			case 1:
				initial = "J";
				break;
			default:
				System.out.println("I'm not sure how this happened. Guess your middle initial is \"z\" now");
				initial = "z";
			}	
			
			
			
			Iterator<String> iterFirst = firstNames.iterator();
			int index = 0;
			String element = null;
			String fNameStr = "";
			
			while(iterFirst.hasNext()) {
				element = iterFirst.next();
				if (index == fName) {
					fNameStr = element;
					break;
				}
				else index++;
			}
			
			Iterator<String> iterLast = lastNames.iterator();
			index = 0;
			element = null;
			String lNameStr = "";
			
			while(iterLast.hasNext()) {
				element = iterLast.next();
				if (index == lName) {
					lNameStr = element;
					break;
				}
				else index++;
			}
			
			idMap.put((long)i, new Student(i, fNameStr, initial, lNameStr));
		}
				
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	
	private static long sortStudentData(List<Student> students) {
		//time is a human construct
		long timeStart = System.nanoTime();
		
		Comparator<Student> nameCompare = Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName).thenComparing(Student::getMiddleName).thenComparing(Student::getId);
		Collections.sort(students, nameCompare);
		
		//time is no longer a human construct
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	
	private static long sequentialStudentData(List<Student> students) {
		//time flies
		long timeStart = System.nanoTime();
		
		for (int i = 1; i <= STUDENTNUM; i++) {
			for (Student j: students) {
				if (j.getId() == i) {
					break;
				}
			}
		}
		
		//time lands
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	private static long sequentialStudentData(Map<Long, Student> studentMap) {
		//I hate time
		long timeStart = System.nanoTime();
		
		for (int i = 1; i <= STUDENTNUM; i++) {
			studentMap.get((long)i);
		}
		
		//Hey me too (that's why I stopped it)
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	private static long generateReport(String reportFile, Collection<Student> students, String filename) throws FileNotFoundException {
		//maybe one final time
		long timeStart = System.nanoTime();
		
		PrintWriter logger = new PrintWriter(filename);
		
		Iterator<Student> iterStud = students.iterator();
		Student element = null;
		
		while(iterStud.hasNext()) {
			element = iterStud.next();
			reportFile = element.toString();
			logger.println(reportFile);
		}
		
		logger.close();
		
		//yeah, and it's done right now
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	private static long generateReport(String reportFile, Map<Long, Student> idMap, String filename) throws FileNotFoundException {
		//maybe one final time
		long timeStart = System.nanoTime();
		
		PrintWriter logger = new PrintWriter(filename);
		
		for(int i = 1; i <= STUDENTNUM; i++) {
			reportFile = idMap.get((long)i).toString();
			logger.println(reportFile);
		}
		
		logger.close();
		
		//yeah, and it's done right now
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	
	
	private static long simulate(List<String> firstNames, List<String> lastNames, List<Student> students, String filename) throws FileNotFoundException {
		//time
		long timeStart = System.nanoTime();
		
		long timeReadOne = readNames(new File("resource/names.txt"), firstNames);
		long timeReadTwo = readNames(new File("resource/surnames.txt"), lastNames);
		System.out.println("Parsed " + firstNames.size() + " names and " + lastNames.size() + " last names in " + timeReadOne + timeReadTwo + " ms");
		
		long timeGenerate = generateStudentData(firstNames, lastNames, students);
		System.out.println("Generation\t\t" + timeGenerate + " ms");
		
		long timeSort = sortStudentData(students);
		System.out.println("Sorting\t\t\t" + timeSort + " ms");
		
		long timeSequence = sequentialStudentData(students);
		System.out.println("Sequence\t\t" + timeSequence + " ms");
		
		String reportFile = "";
		long timeReport = generateReport(reportFile, students, filename);
		System.out.println("Recording\t\t" + timeReport + " ms");
		
		//no more time
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	private static long simulate(Set<String> firstNames, Set<String> lastNames, Map<Long, Student> idMap, String filename) throws FileNotFoundException {
		//time
		long timeStart = System.nanoTime();
		
		long timeReadOne = readNames(new File("resource/names.txt"), firstNames);
		long timeReadTwo = readNames(new File("resource/surnames.txt"), lastNames);
		System.out.println("Parsed " + firstNames.size() + " names and " + lastNames.size() + " last names in " + timeReadOne + timeReadTwo + " ms");
		
		long timeGenerate = generateStudentData(firstNames, lastNames, idMap);
		System.out.println("Generation\t\t" + timeGenerate + " ms");
		
		long timeSequence = sequentialStudentData(idMap);
		System.out.println("Sequence\t\t" + timeSequence + " ms");
		
		String reportFile = "";
		long timeReport = generateReport(reportFile, idMap, filename);
		System.out.println("Recording\t\t" + timeReport + " ms");
		
		//no more time
		long timeEnd = System.nanoTime();
		return (timeEnd - timeStart) / TIME; //gets time (and converts to milliseconds)
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> fNameArray = new ArrayList<String>();
		ArrayList<String> lNameArray = new ArrayList<String>();
		ArrayList<Student> studentArray = new ArrayList<Student>();
		String strArray = "output/randomRecords.ArrayList.txt";
		
		LinkedList<String> fNameLinked = new LinkedList<String>();
		LinkedList<String> lNameLinked = new LinkedList<String>();
		LinkedList<Student> studentLinked = new LinkedList<Student>();
		String strLinked = "output/randomRecords.LinkedList.txt";
		
		HashSet<String> fNameHash = new HashSet<String>();
		HashSet<String> lNameHash = new HashSet<String>();
		HashMap<Long, Student> studentHash = new HashMap<Long, Student>();
		String strHash = "output/randomRecords.HashMap.txt";
		
		TreeSet<String> fNameTree = new TreeSet<String>();
		TreeSet<String> lNameTree = new TreeSet<String>();
		TreeMap<Long, Student> studentTree = new TreeMap<Long, Student>();
		String strTree = "output/randomRecords.TreeMap.txt";
		
		
		
		long finalArray = simulate(fNameArray, lNameArray, studentArray, strArray);
		System.out.println("ArrayList total\t\t" + finalArray + " ms");
		System.out.println();
		
		long finalLinked = simulate(fNameLinked, lNameLinked, studentLinked, strLinked);
		System.out.println("LinkedList total\t" + finalLinked + " ms");
		System.out.println();
		
		long finalHash = simulate(fNameHash, lNameHash, studentHash, strHash);
		System.out.println("HashMap total\t\t" + finalHash + " ms");
		System.out.println();
		
		long finalTree = simulate(fNameTree, lNameTree, studentTree, strTree);
		System.out.println("TreeMap total\t\t" + finalTree + " ms");
	}
}
