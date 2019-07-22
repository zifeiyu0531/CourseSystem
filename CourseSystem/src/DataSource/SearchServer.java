package DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;
import Bean.Student;
import Bean.Course;

public class SearchServer{
    private Object[][] students = new Object[30][2];
    private Object[][] courses = new Object[30][4];
    private Object[][] courseDetail = new Object[30][4];
    private String[] courseList = new String[30];
    private String PASSWORD="root";
    public Object[][] getStudentTable(String className) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from "+className;
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            for(int j = 0;j < 2;j++){
                students[i][j] = rs.getString(j+1);
                System.out.println(students[i][j]);
            }
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return students;
    }

    public Object[][] getStudent(String className, String studentInfo) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from "+className+" where studentid like '%"+studentInfo+"%' or studentname like '%"+studentInfo+"%'";
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            for(int j = 0;j < 2;j++){
                students[i][j] = rs.getString(j+1);
                System.out.println(students[i][j]);
            }
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return students;
    }

    public Object[][] getCourseTable() throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from course";
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            for(int j = 0;j < 4;j++){
                courses[i][j] = rs.getString(j+1);
                System.out.println(courses[i][j]);
            }
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return courses;
    }

    public Object[][] getCourse(String courseName) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from course where name like '%"+courseName+"%'";
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            for(int j = 0;j < 4;j++){
                courses[i][j] = rs.getString(j+1);
                System.out.println(courses[i][j]);
            }
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return courses;
    }

    public String[] getCourses() throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from course";
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            courseList[i] = rs.getString(1);
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return courseList;
    }

    public Object[][] getCourseDetail(String name) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from "+name;
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            for(int j = 0;j < 4;j++){
                courseDetail[i][j] = rs.getString(j+1);
                System.out.println(courseDetail[i][j]);
            }
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return courseDetail;
    }

    public Object[][] getStudentDetail(String id, String courseName) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from "+courseName+"  where studentid like '%"+id+"%'";
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        System.out.println("2");
        int i = 0;
        while(rs.next()) {
            for(int j = 0;j < 4;j++){
                courseDetail[i][j] = rs.getString(j+1);
                System.out.println(courseDetail[i][j]);
            }
            i++;
        }
        //处理ResultSet
        rs.close();
        ps.close();
        conn.close();
        return courseDetail;
    }
}