import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.HashMap;
import java.sql.*;


public class ContentRegDataReceive extends Thread
{
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;
	SendData sd=new SendData();
	
	public ContentRegDataReceive()
	{
		try
		{
		ss=new ServerSocket(1023);
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
				HashMap hmap=(HashMap)ois.readObject();
				System.out.println(hmap);

				String nodename=(String)hmap.get("nodename");
				String portno=hmap.get("portno").toString();
				String que=hmap.get("query").toString();
				String dis=hmap.get("distance").toString();
				
				Vector recv=new Vector();
				recv.add(nodename);
				recv.add(portno);
				recv.add(que);
				recv.add(dis);
				Middle.regc.insertRow(Middle.regc.getRowCount(),recv);
				System.out.println("first"+hmap);

				DBConnection obj = new DBConnection();

				Connection con = obj.getConnection();

				Statement st = con.createStatement();
				String f="";

				int i=st.executeUpdate("insert into submap values ('"+nodename+"','"+portno+"','"+que +"','"+dis+"')");
				if( i==1)
				{
					System.out.println("Register Successfully");

				}

				else
				{
					System.out.println("Register Failed");

					
				}

				sd.SendRequest(hmap);
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
