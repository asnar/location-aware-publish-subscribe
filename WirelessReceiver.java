

import java.util.*;
import java.net.*;
public class WirelessReceiver extends Thread
{
	public static Vector consumer=new Vector();
	public static Vector producer=new Vector();
	
    MulticastSocket ms;
    String s=null;
    String nodename,whois;
    int distance;
  	public WirelessReceiver(String nodename,String whois,int distance)
	{
  		this.nodename=nodename;
  		this.whois=whois;
  		this.distance=distance;
  		
      	start();
	}

    public void run()
    {
        try
        {
        while(true)
         {
      	 ms=new MulticastSocket(5454);
		 InetAddress ia=InetAddress.getByName("228.2.5.1");
		 ms.joinGroup(ia);
         byte[] b=new byte[1000];
		 DatagramPacket dp=new DatagramPacket(b,b.length);
		 ms.receive(dp);
		 s=new String(dp.getData()).trim();
    	 GetInfo(s);//finds the neighbouring nodes,for publisher it will find subscribes within the range and vice versa
          }
        } 
      catch(Exception ee)
      {
            ee.printStackTrace();
      }
    }
   
  public void GetInfo(String info)
  {
	  StringTokenizer st=new StringTokenizer(info,":");
	  String tempwhois=st.nextToken();
	  String tempnodename=st.nextToken();
	  String tempportno=st.nextToken();
	  int tempdistance=Integer.parseInt(st.nextToken());
	  String tempaddress=st.nextToken();
	  
	  if((!tempwhois.equals(whois)) && (!tempnodename.equals(nodename)))
	  {
		 if(tempwhois.equals("subscriber"))
		  {
			  if(!producer.contains(tempnodename))
			  {
				  if(tempdistance>=(distance-50) && tempdistance<=(distance+50))
				  {
				  String strdet=tempaddress+"$"+tempportno;
				  MainPro.stodet.put(tempnodename,strdet);
				  producer.add(tempnodename);
				  System.out.println(producer);
				  MainPro.neighnodes.setText(""+producer);
				  }
			  }
			
			 
		  }
		  else if(tempwhois.equals("publisher"))
		  {
			  if(!consumer.contains(tempnodename))
			  {
				  if(tempdistance>=(distance-50) && tempdistance<=(distance+50))
				  {
					  String strdet=tempaddress+"$"+tempportno;
					  MainCon.stodet.put(tempnodename,strdet);
					  consumer.add(tempnodename);
					  System.out.println(consumer);
					  MainCon.neighnodes.setText(""+consumer);
				  }
			  }
			 
			  
		  }
	  }
  }
}
