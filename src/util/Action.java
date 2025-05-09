package util;

public class Action {
    public int StudentID;
    public String StudentName;
    public int CourseID;
    public String CourseName;
    public String ActionType;

    public Action(int id,String s1,int id2, String s2 ,String actionType) {
        this.StudentID = id;
        this.StudentName = s1;
        this.CourseName = s2;
        this.CourseID = id2;
        this.ActionType = actionType;
    }
}
