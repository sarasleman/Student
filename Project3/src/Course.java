
public class Course {
	private static int nextCourseID = 1;
	private String courseName;
	private double grade;
	private int courseID;
	
	public Course(String courseName, double grade){
		this.courseName = courseName;
		this.grade = grade;
		courseID = nextCourseID++;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getCourseID() {
		return courseID;
	}
	
	@Override
	public String toString(){
		return "Course ID: " + courseID + ", Course Name: " + courseName + ", Grade: " + grade;
	}
}
