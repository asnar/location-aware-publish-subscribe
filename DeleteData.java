import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;
import java.io.*;
import java.net.*;

public class DeleteData extends Thread
{
	
	public DeleteData()
	{
		try
		{
			DBConnection obj = new DBConnection();
			Connection con = obj.getConnection();
			Statement st = con.createStatement();
			int i=st.executeUpdate("delete from regmap");
			int i1=st.executeUpdate("delete from submap");
			int i2=st.executeUpdate("delete from contentmap");
			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	
	}
				
			
}
