package DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;
import Bean.Student;

public class AddServer{
    private String PASSWORD="root";
    public boolean insertStudent(String className, String studentName, String studentId) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="insert into "+className+" values ('"+studentId+"','"+ studentName+"')";
        String sql2="insert into student values ('"+studentId+"','"+ studentName+"','"+ className+"','','','','','')";
        System.out.println(sql);
        System.out.println(sql2);
        //准备statement
        Statement statement=conn.createStatement();
        statement.executeUpdate(sql);
        statement.executeUpdate(sql2);
        System.out.println("2");
        //处理ResultSet
        statement.close();
        conn.close();
        return true;
    }

    public boolean insertCourse(String name, String teacherName, String time, String location) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="insert into course values ('"+name+"','"+ teacherName+"','"+ time+"','"+ location+"')";
        String sql2="create table "+name+" (studentid varchar(15), studentname varchar(5), class varchar(10), grade int(3))";
        System.out.println(sql);
        System.out.println(sql2);
        //准备statement
        Statement statement=conn.createStatement();
        statement.executeUpdate(sql);
        statement.executeUpdate(sql2);
        System.out.println("2");
        //处理ResultSet
        statement.close();
        conn.close();
        return true;
    }

    public boolean insertCourseStudent(String className, String studentName, String studentId, String courseName) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="insert into "+courseName+" values ('"+studentId+"','"+ studentName+"','"+ className+"',0)";
        System.out.println(sql);
        //准备statement
        Statement statement=conn.createStatement();
        statement.executeUpdate(sql);
        System.out.println("2");
        //处理ResultSet
        statement.close();
        conn.close();
        return true;
    }
}