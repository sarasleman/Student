public enum Action {
	REGISTER_STUDENT, VIEW_STUDENTS, UPDATE_STUDENT, DELETE_STUDENT, EXIT;
	
	public static Action getAction(int input) {
        switch (input) {
            case 1:
                return Action.REGISTER_STUDENT;
            case 2:
                return Action.VIEW_STUDENTS;
            case 3:
                return Action.UPDATE_STUDENT;
            case 4:
                return Action.DELETE_STUDENT;
            case 5:
                return Action.EXIT;
            default:
                return null;
        }
    }

}
