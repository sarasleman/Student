import java.util.Scanner;

public class RegistrationSystem {
	private StudentDAO studentDAO;
	private Scanner scanner;

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public RegistrationSystem() {
		this.studentDAO = new StudentDAO();
		this.scanner = new Scanner(System.in);
	}

	public void start() {
		Action choice = null;
		while (choice != Action.EXIT) {
			System.out.println("Registration System Menu:");
			System.out.println("1. Register Student");
			System.out.println("2. View all students");
			System.out.println("3. Update student record");
			System.out.println("4. Delete student record");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			int input = scanner.nextInt();
			scanner.nextLine();

			choice = Action.getAction(input);
			switch (choice) {
			case REGISTER_STUDENT:
				studentDAO.addStudent();
				break;
			case VIEW_STUDENTS:
				viewAllStudents();
				break;
			case UPDATE_STUDENT:
				updateStudent();

				break;
			case DELETE_STUDENT:
				deleteStudentRecord();
				break;
			case EXIT:
				System.out.println("Exiting Reagistration System...\n");
				break;
			default:
				System.out.println("Invalid choice. Please try again.\n");
			}
		}
	}

	private void updateStudent() {
		System.out.print("Enter the ID of student to update: ");
		int updateID = scanner.nextInt();
		
		if (studentDAO.getStudentByID(updateID) == null) {
			System.out.println("Student not Registered in the system.");
			return;
		} else {
			Student studentToUpdate = studentDAO.getStudentByID(updateID);
			
			System.out.print("Enter what you want to update (name, age, gender or contact details):");
			String update = scanner.next();
			switch (update) {
			case "name":
				System.out.print("Enter new name: ");
				update = scanner.next();
				studentToUpdate.setName(update);
				System.out.println("Student name updated successfully.\n");
				break;

			case "age":
				System.out.print("Enter new age: ");
				int age2 = scanner.nextInt();
				studentToUpdate.setAge(age2);
				System.out.println("Student age updated successfully.\n");
				break;

			case "gender":
				System.out.print("Enter new gender: ");
				update = scanner.next();
				studentToUpdate.setGender(update);
				System.out.println("Student gender updated successfully.\n");
				break;

			case "contact details":
				System.out.print("Enter new contact details: ");
				update = scanner.next();
				studentToUpdate.setContactDetails(update);
				System.out.println("Student contact details updated successfully.\n");
				break;

			default:
				System.out.println("Please Enter a valid field\n");
				break;
			}
		}
	}

	private void viewAllStudents() {
		System.out.println(studentDAO.getAllStudents().toString() + "\n");
	}


	private void deleteStudentRecord() {
		System.out.print("Enter ID of student to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		Student studentToDelete = studentDAO.getStudentByID(id);
		if (studentToDelete != null) {
			studentDAO.deleteStudent(studentToDelete);
			System.out.println("Student record deleted successfully.\n");
		} else {
			System.out.println("Student not found with ID " + id + "\n");
		}
	}

}
