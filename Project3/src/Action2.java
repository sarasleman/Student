
public enum Action2 {
	ADD_GRADE, CALCULATE_AVG, PERFORMANCE_REPORT, TOP_PERFORMING, EXIT;
	
	public static Action2 getAction(int input) {
        switch (input) {
            case 1:
                return Action2.ADD_GRADE;
            case 2:
                return Action2.CALCULATE_AVG;
            case 3:
                return Action2.PERFORMANCE_REPORT;
            case 4:
                return Action2.TOP_PERFORMING;
            case 5:
                return Action2.EXIT;
            default:
                return null;
        }
    }
}
