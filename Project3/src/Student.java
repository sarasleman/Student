public class Student {
	private String name;
	private int age;
	private String gender;
	private String contactDetails;
	private int id;
	private static int nextID = 1; // Initialize the next available ID to 1

	public Student(String name, int age, String gender, String contactDetails) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.contactDetails = contactDetails;
		this.id = nextID++; // Assign the next available ID to the student
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: "
				+ gender + " and Contact details: " + contactDetails + "\n";
	}
}
