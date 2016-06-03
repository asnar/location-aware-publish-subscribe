import java.sql.*;

public class DBConnection
{
	private Connection con = null;

	private String driver = "com.mysql.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3306/pubsub";

	public Connection getConnection()throws ClassNotFoundException,SQLException
	{
		try{
		
		Class.forName( driver );
	
		con = DriverManager.getConnection(url,"root","root");

		
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return con;


	}
	
}
