package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.GameManager;

public class ConnectionDBA 
{
	private static ConnectionDBA instance_;
	
	public ConnectionDBA()
	{
		
	}
	
	public static ConnectionDBA instance() throws SQLException
	{
		if(instance_ == null)
		{
			instance_ = new ConnectionDBA();
		}
		return instance_;
	}
	
	private String username = "system";
	private String password = "123456";
	private String service = "localhost";
	private String url = "jdbc:oracle:thin:";
	
	Connection goConnect;
	public String sqlQuery;
	
	
	
	public Connection Connect() throws SQLException
	{
		//Connection goConnect;
		
		username = "system";
		password = "123456";
		service = "localhost";
		url = "jdbc:oracle:thin:";

		goConnect = DriverManager.getConnection(url+username+"/"+password+"@"+service);
		
		System.out.println("Connected");
	
		return goConnect;
	}
	

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void FromDBAToFile() throws SQLException
	{
		Statement statement = goConnect.createStatement();
		ResultSet myResults = null; 
		sqlQuery = "select * FROM leaderBoards";
		myResults = statement.executeQuery(sqlQuery);
		
		while(myResults.next())
		{
			int i = 0;
			int j = 1;
			//int id = myResults.getInt(1);
			String name = myResults.getString(1);
			String score = String.valueOf(myResults.getInt(2));
			//System.out.println(id + "  ===>  " + name + " " + address);
			
			GameManager.instance().gui.playNames[i][i] = (name);
			GameManager.instance().gui.playNames[i][j] = (score);
			
			System.out.println("  ===>  " + name + " " + score);
			
			i++;
			if(i>1)
			{
				i=0;
			}
		
		}
		
		
	}
	
	
	
	
	
}
