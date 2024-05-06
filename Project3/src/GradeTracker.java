import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GradeTracker {
	private Map<Integer, List<Course>> gradeMap;
	private StudentDAO studentDAO;

	public GradeTracker(RegistrationSystem regSystem) {
		this.gradeMap = new LinkedHashMap<>();
		this.studentDAO = regSystem.getStudentDAO();
	}

	public void startTracker() {
		Action2 choice = null;
		try (Scanner scanner = new Scanner(System.in)) {
			while (choice != Action2.EXIT) {
				System.out.println("Grade Tracker Menu:");
				System.out.println("1. Add Grade");
				System.out.println("2. Calculate Avarage");
				System.out.println("3. Print performance Report");
				System.out.println("4. Print top performing Student");
				System.out.println("5. Exit");
				System.out.print("Enter your choice: ");
				int input = scanner.nextInt();
				scanner.nextLine();

				choice = Action2.getAction(input);
				switch (choice) {
				case ADD_GRADE:
					int studentID = idInput(scanner);
					addGrade(studentID, scanner);
					break;
				case CALCULATE_AVG:
					int studentID2 = idInput(scanner);
					double AVG = calculateAvg(studentID2);
					if (AVG == 0.0) {
						break;
					} else {
						System.out.println("The Average grade for is: " + AVG + "\n");
					}
					break;
				case PERFORMANCE_REPORT:
					int studentID3 = idInput(scanner);
					performanceReport(studentID3);

					break;
				case TOP_PERFORMING:
					topPerforming();
					break;
				case EXIT:
					System.out.println("Exiting Tracking System...\n");
					break;
				default:
					System.out.println("Invalid choice. Please try again.\n");
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR in the tracker.......\n");
		}
	}

	private int idInput(Scanner scanner) {
		System.out.print("Enter the student ID: ");
		int studentID = scanner.nextInt();
		return studentID;
	}

	private void addGrade(int studentID, Scanner scanner) {
		Student student = studentDAO.getStudentByID(studentID);
		if (student == null) {
			System.out.println("Student does not exist in the system..\n");
		} else {
			System.out.print("Enter the number of courses you want to add: ");
			int no = scanner.nextInt();
			for (int i = 1 ; i <= no ; i++){
				System.out.print("Enter the Course " + i +" name: ");
				String name = scanner.next();
				System.out.print("Enter the Course grade: ");
				int grade = scanner.nextInt();
				System.out.println();

				Course course = new Course(name, grade);

				if (gradeMap.containsKey(student.getID())) {
					List<Course> courses = gradeMap.get(student.getID());
					courses.add(course);
					gradeMap.put(student.getID(), courses);
				} else {
					List<Course> courses = new ArrayList<>();
					courses.add(course);
					gradeMap.put(student.getID(), courses);
				}
			}
			
			System.out.println("Course & grade were added successfully!......\n");

		}
	}

	private double calculateAvg(int studentID) {
		Student student = studentDAO.getStudentByID(studentID);
		if (student == null) {
			System.out.println("Student does not exist in the system..\n");
			return 0.0;
		}

		List<Course> courses = gradeMap.get(student.getID());
		if (courses == null || courses.isEmpty()) {
			System.out.println("No grades recorded for student: "
					+ student.getName());
			return 0.0;
		}

		double totalGrade = 0.0;
		for (Course course : courses) {
			totalGrade += course.getGrade();
		}
		return totalGrade / courses.size();

	}

	private void performanceReport(int studentID) {
		Student student = studentDAO.getStudentByID(studentID);
		if (student == null) {
			System.out.println("Student does not exist in the system..\n");
			return;
		}

		List<Course> courses = gradeMap.get(student.getID());
		if (courses == null || courses.isEmpty()) {
			System.out.println("No grades recorded for student: "
					+ student.getName());
			return;
		}

		String filePath = "performance.txt";
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true))) {
			br.newLine();
			br.write("Performance Report for " + student.getName() + ":");
			br.newLine();
			for (Course course : courses) {
				br.write("Course name: " + course.getCourseName() + ", Grade: "
						+ course.getGrade());
				br.newLine(); // Add new line after each course
			}
			br.write("Average Grade: " + calculateAvg(studentID));
			br.flush(); // Flush data to the file
		} catch (IOException e) {
			System.out.println("Unable to write to " + filePath);
		}
		System.out.println("Performance Report for " + student.getName() + " is printed on the file! \n");
	}

	private void topPerforming() {
		List<Student> topPerformingStudents = new ArrayList<>();
		double maxAverageGrade = 0;

		for (Integer studentID : gradeMap.keySet()) {
			double averageGrade = calculateAvg(studentID);
			Student student = studentDAO.getStudentByID(studentID);

			if (averageGrade > maxAverageGrade) {
				maxAverageGrade = averageGrade;
				topPerformingStudents.clear();
				topPerformingStudents.add(student);
			} else if (averageGrade == maxAverageGrade) {
				topPerformingStudents.add(student);
			}
		}

		String filePath = "performance.txt";
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true))) {
			br.newLine();
			br.write("Top Performing Student(s):  " );
			br.newLine();
			for (Student st : topPerformingStudents) {
				br.write(st.getName());
				br.newLine();
			}
		} catch (IOException e) {
			System.out.println("Unable to write to " + filePath);
		}
		System.out.println("Top performing Student is printed on the file!\n");
	}

}
