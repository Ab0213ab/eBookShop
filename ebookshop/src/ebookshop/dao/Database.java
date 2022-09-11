package ebookshop.dao;

import java.sql.Connection;

// Database class connects to database
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private String jdbcURL = "jdbc:mysql://localhost:3306/shop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "weare138";
	private Connection conn;
	public static void main (String [] args)
	{
		Database db = new Database();
		try {
			String query = "select * from books";
			ResultSet rs = db.getResultSet(query);
			if (rs==null)
			{
				System.out.println ("No data");
				return;
			}
			while (rs.next())
			{
				System.out.println (rs.getString(2));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			db.close();
		}
	}
	public Connection getConnection() {
		try {
			if (conn !=null)
				return conn;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public int execute (String sql)
	{
		try {
			if (conn==null)
				getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			return statement.executeUpdate();
		} catch (SQLException e) {
			
		}finally {
			close();
		}
		
		return -1;
	}
	public int execute (String sql, Object...values)
	{
		try {
			if (conn==null)
				getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			int row=1;
			for (Object obj:values)
			{
				statement.setObject(row++, obj);
			}
			return statement.executeUpdate();
		} catch (SQLException e) {
			
		}finally {
			close();
		}
		
		return -1;
	}
	public PreparedStatement prepare (String sql)
	{
		try {
			if (conn==null)
				getConnection();
			
			PreparedStatement statement = 
					conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			return statement;
		} catch (SQLException e) {
			
		}finally {
			
		}
		return null;
	}
	
	public ResultSet getResultSet (String query, Object...values)
	{
		ResultSet rs=null;
		try {
			if (conn==null)
				getConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			int row=1;
			if (values!=null && values.length>0)
			{				
				for (Object obj:values)
				{
					statement.setObject(row++, obj);
				}
			}
			return statement.executeQuery();
		} catch (SQLException e) {
			
		}finally {

		}
		return rs;
	}
	public boolean close ()
	{
		try {
			if (conn ==null)
				return true;
			conn.close();
			conn=null;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}



