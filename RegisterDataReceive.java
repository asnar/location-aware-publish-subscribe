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

public class RegisterDataReceive extends Thread
{
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;
	
	public RegisterDataReceive()
	{
		try
		{
		ss=new ServerSocket(1020);
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		start();
	
	}
	public void run()
	{
		try
		{
			
			while(true)
			{
				s=ss.accept();
				ois=new ObjectInputStream(s.getInputStream());
				Vector recv=(Vector)ois.readObject();
				Middle.reg.insertRow(Middle.reg.getRowCount(),recv);
				Middle.regmap.put(recv.get(0).toString(),(Integer)recv.get(1));

				DBConnection obj = new DBConnection();

				Connection con = obj.getConnection();

				Statement st = con.createStatement();

				int i=st.executeUpdate("insert into regmap values ('"+recv.get(0).toString()+"','"+(Integer)recv.get(1)+"')");


				


				if( i==1)
				{
					System.out.println("Register Successfully");


					

				}

				else
				{
					System.out.println("Register Failed");

					
				}

			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
