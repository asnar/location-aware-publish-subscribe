import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;


public class SendData 
{
	Socket s;
	ObjectOutputStream oos;
	
	
	public void SendRegister(String pname,int distance)
	{
		Vector v=new Vector();
		v.add(pname);
		v.add(distance);
		
		try
		{
		s=new Socket("localhost",1020);
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(v);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void SendContent(Vector v)
	{
		try
		{
		s=new Socket("localhost",1021);
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(v);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void SendRequest(HashMap hmap)
	{
		try
		{
		s=new Socket("localhost",1022);
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(hmap);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void SendSubscriberRequest(HashMap hmap)
	{
		try
		{
		s=new Socket("localhost",1023);
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(hmap);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void SendResponse(String hmap,int portno)
	{
		try
		{
		s=new Socket("localhost",portno);
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(hmap);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
