import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class ConsumerResponse extends Thread
{
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;
	
	public ConsumerResponse(int portno)
	{
		try
		{
		ss=new ServerSocket(portno);
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
				String data=(String)ois.readObject();
				String[] parts = data.split("&&");
				String part1 = parts[0]; // 004
				String part2 = parts[1]; // 034556
				if(part2.equals("dependent")) {
					MainCon.drestext.setText(""+part1);
				}	
				if(part2.equals("independent")) {
					MainCon.irestext.setText(""+part1);
				}
				if(part2.equals("filter")) {
					MainCon.frestext.setText(""+part1);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
