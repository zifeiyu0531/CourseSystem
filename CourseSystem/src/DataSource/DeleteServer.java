package DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;
import Bean.Student;

public class DeleteServer{
    private String PASSWORD="root";
    public boolean deleteStudent(String className,String studentId) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="delete from "+className+" where studentid = '"+studentId+"'";
        String sql2="delete from student where id = '"+studentId+"'";
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

    public boolean deleteCourse(String name) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="delete from course where name = '"+name+"'";
        String sql2="drop table "+name;
        System.out.println(sql);
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

    public boolean deleteCourseStudent(String courseName,String studentId) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="delete from "+courseName+" where studentid = '"+studentId+"'";
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