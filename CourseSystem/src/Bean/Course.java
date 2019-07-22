package Bean;

public class Course {     //课程类
    private String name;
    private String teacherName;
    private String time;
    private String location;
    public Course(){};
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setTeacherName(String teacherName){this.teacherName = teacherName;}
    public String getTeacherName(){return teacherName;}
    public void setTime(String time){this.time = time;}
    public String getTime(){return time;}
    public void setLocation(String location){this.location = location;}
    public String getLocation(){return location;}
}
