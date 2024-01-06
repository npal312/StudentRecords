# StudentRecords
Compares performance of different operations on sample student record data using ArrayList, LinkedList, HashSet/HashMap, and TreeSet/TreeMap.

Assignment Description: (From SSW-315)

In this assignment we will compare performance of different operations on student records using ArrayList, LinkedList, HashSet/HashMap, and TreeSet/TreeMap. 

Student class will contain id, firstName, middleName, lastName as data fields.
 

You need to implement the following methods in StudentRecords class. Each method should return execution time, which is also displayed on the screen.
private static long simulate(List<String> firstNames, List<String> lastNames, List<Student> students, String fileName)
private static long simulate(Set<String> firstNames, Set<String> lastNames, Map<Long, Student> idMap, String fileName)
Read names.txt Download names.txt  and surnames.txt Download surnames.txtfiles
Call generateStudentData 
Call sortStudentData (skip for idMap)
Call iterateStudentData
Call generateReport
Return total simulation time
 

private static long readNames(File file, Collection<String> names)
Read either of name files into the list.
 

private static long generateStudentData(List<String> firstNames, List<String> lastNames, List<Student> students)
private static long generateStudentData(Set<String> firstNames, Set<String> lastNames, Map<Long, Student> idMap)
Generate 100,000 names by randomly selecting from respective records and a middle name initial (with 50% probability select a random letter).
Student IDs should start from 1 and increment.
 

private static long sortStudentData(List<Student> students)
Sort students list using Collections.sort() method comparing lastName, then firstName, then middleName, then id. Note that, you would need getters for Student class.
 

private static long sequentialStudentData(List<Student> students)
private static long sequentialStudentData(Map<Long, Student> studentMap)
Sequentially look for each student id. Note that student lists are not sorted by id.
 

private static long generateReport(String reportFile, Collection<Student> students, String fileName)
Write student records into a file using iterator approach. Note that records would be sorted by name with lists and by id with maps.
Utilize toString method of Student class to obtain a formatted output.
 

Sample Output:

parsed 18239 names and 1000 last names in 165 sec 
Generation       46 sec
Sorting          143 sec 
Sequence         4 sec 
Recording        150 sec 
ArrayList total  510 sec 

parsed 18239 names and 1000 last names in 30 sec 
Generation       2121 sec
Sorting          183 sec 
Sequence         7401 sec 
Recording        215 sec 
LinkedList total 9950 sec 

parsed 18239 names and 1000 last names in 42 sec 
Generation       12971 sec
Sequence         9 sec 
Recording        184 sec 
HashMap total    13206 sec 

parsed 18239 names and 1000 last names in 63 sec 
Generation       15139 sec
Sequence         15 sec 
Recording        189 sec 
TreeMap total    15406 sec 
 

Notes:

When uploading the results of your test run, make sure to include a snapshot of your code and test results within IDE.
You can continue updating your work and submitting newer versions before the deadline. Only the last version before the deadline will be graded.
If 100,000 records is taking about 0 sec in multiple steps then increase the number of records to 1,000,000.
 

Submission:

Java source code named StudentRecords.java
Output files named randomRecords.ArrayList.txt, randomRecords.LinkedList.txt, randomRecords.HashMap.txt, and randomRecords.TreeMap.txt
Screenshot of your test run as pdf or image (jpg, jpeg, png)
