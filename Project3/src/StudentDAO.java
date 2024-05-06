import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class StudentDAO {
	private Set<Student> students;
	private Scanner scan;

	public StudentDAO() {
		this.students = new LinkedHashSet<>();
		scan = new Scanner(System.in);
	}

	public void addStudent() {
		System.out.print("Enter student name: ");
		String name = scan.next();
		System.out.print("Enter student age: ");
		int age = scan.nextInt();
		System.out.print("Enter student gender (M/F): ");
		String gender = scan.next();
		System.out.print("Enter student contact details: ");
		String contactDetails = scan.next();
		Student newStudent = new Student(name, age, gender, contactDetails);
		students.add(newStudent);
		System.out.println("Student added successfuly !\n");
	}

	public Student getStudentByID(int id) {
		for (Student student : students) {
			if (student.getID() == id) {
				return student;
			}
		}
		return null; // If the student with given ID is not found
	}


	public void deleteStudent(Student student) {
		students.remove(student);
	}

	public Set<Student> getAllStudents() {
		return students;
	}
}
