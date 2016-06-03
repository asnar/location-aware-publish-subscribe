
import java.net.*;
import javax.swing.*;
import java.util.Properties;
public class WirelessSender  implements Runnable
{
    MulticastSocket ms;
    byte[] sendbyte;
    public boolean status=true;
    Thread t;
    String nodename,whois;
    int distance,portno;
   
WirelessSender(String nodename,int portno,String whois,int distance)
{
	this.nodename=nodename;
	this.portno=portno;
	this.whois=whois;
	this.distance=distance;
	
    t=new Thread(this);
    t.start();
}

public void run()
{

    try
    {
        ms=new MulticastSocket(5454);
        InetAddress ia=InetAddress.getByName("228.2.5.1");
        ms.joinGroup(ia);
        Thread.sleep(5000);
        while(status)
        {
        String sendmsg=whois+":"+nodename+":"+portno+":"+distance+":"+InetAddress.getLocalHost().getHostName();
        sendbyte=sendmsg.getBytes();
		DatagramPacket dp=new DatagramPacket(sendbyte,sendbyte.length,ia,5454);
		ms.send(dp);//sends to all subscribers with the port no 5454
        Thread.sleep(1200);
        }
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}

}
