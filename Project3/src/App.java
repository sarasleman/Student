
public class App {
	 public static void main(String[] args) {
	        RegistrationSystem registrationSystem = new RegistrationSystem();
	        registrationSystem.start();
	        
	        GradeTracker tracker = new GradeTracker(registrationSystem);
	        tracker.startTracker();
	    }
}
