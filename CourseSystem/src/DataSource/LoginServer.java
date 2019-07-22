package DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;
import Bean.User;

public class LoginServer{
    private String PASSWORD="root";
    public boolean getLogin(User user) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="select * from user where name = '"+user.getName()+"' and password = '"+user.getPassWord()+"'";
        System.out.println(sql);
        //准备statement
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        //处理ResultSet
        if(!rs.next()) {
            rs.close();
            ps.close();
            conn.close();
            return false;
        } else{
            rs.close();
            ps.close();
            conn.close();
            return true;
        }
    }
}