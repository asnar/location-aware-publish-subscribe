import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Random;


public class RequestDataReceive extends Thread
{
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;

	public RequestDataReceive()
	{
		try
		{
		ss=new ServerSocket(1022);
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

				String sta=(String)hmap.get("status");
				int dis=(Integer)hmap.get("distance");
				String que=hmap.get("query").toString();
				Random r2=new Random();
				String token="T"+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10);
				if(true)
				{
					String resultvalue="";
					//Middle.content.get
					Iterator it=Middle.regmap.keySet().iterator();
					Vector vec=new Vector();
					while(it.hasNext())
					{
						String val=it.next().toString();
						System.out.println("dfffffffff"+val);
						int tempdis=Middle.regmap.get(val);

						 if(tempdis>=(dis-50) && tempdis<=(dis+50))
						 {
							 vec.add(val+""+que);//publishers name and domain
						 }

					}

					for(int i=0;i<vec.size();i++)
					{
						if(Middle.conmap.containsKey(vec.get(i)))//nodename+dom
						{
							resultvalue=resultvalue+"----4-->"+Middle.conmap.get(vec.get(i));//desc
						}
					}
					System.out.println("req"+resultvalue);
					if(resultvalue !=null &&  !resultvalue.isEmpty()){
						resultvalue=""+token+resultvalue;
					} else {
						resultvalue="";
					}
					resultvalue=resultvalue+"&&"+"filter";
					System.out.println("filter"+resultvalue);
					int gg=(Integer)hmap.get("portno");
					System.out.println("port"+gg);
					SendData sd=new SendData();
					sd.SendResponse(resultvalue,(Integer)hmap.get("portno"));

				}
				if(true)
				{
					String resultvalue="";


					//Middle.content.get
					Iterator it=Middle.regmap.keySet().iterator();
					Vector vec=new Vector();
					while(it.hasNext())
					{
						String val=it.next().toString();
						System.out.println("dfffffffff"+val);
						int tempdis=Middle.regmap.get(val);

						 if(tempdis>=(dis-50) && tempdis<=(dis+50))
						 {
							 vec.add(val);
						 }

					}

					for(int i=0;i<vec.size();i++)
					{//changes
						if(Middle.conmap_dep.containsKey(vec.get(i)))
						{
							resultvalue=resultvalue+"---5--->"+Middle.conmap_dep.get(vec.get(i));
						}
					}
					System.out.println("req"+resultvalue);
					if(resultvalue !=null &&  !resultvalue.isEmpty()){
						resultvalue=""+token+resultvalue;
					} else {
						resultvalue="";
					}
					resultvalue=resultvalue+"&&"+"dependent";
					System.out.println("dependent"+resultvalue);
					int gg=(Integer)hmap.get("portno");
					System.out.println("port"+gg);
					SendData sd=new SendData();
					sd.SendResponse(resultvalue,(Integer)hmap.get("portno"));
				}
				if(true)
				{
					String resultvalue="";
					//Middle.content.get
					Iterator it=Middle.regmap.keySet().iterator();
					Vector vec=new Vector();
					while(it.hasNext())
					{
						String val=it.next().toString();
						System.out.println("dfffffffff"+val);
						int tempdis=Middle.regmap.get(val);

						// if(tempdis>=(dis-50) && tempdis<=(dis+50))
						// {
							 vec.add(val+""+que);
						// }

					}

					for(int i=0;i<vec.size();i++)
					{
						if(Middle.conmap.containsKey(vec.get(i)))
						{
							resultvalue=resultvalue+"---6--->"+Middle.conmap.get(vec.get(i));
						}
					}
					System.out.println("req"+resultvalue);
					if(resultvalue !=null &&  !resultvalue.isEmpty()){
						resultvalue=""+token+resultvalue;
					} else {
						resultvalue="";
					}
					resultvalue=resultvalue+"&&"+"independent";
					System.out.println("inpendent"+resultvalue);
					int gg=(Integer)hmap.get("portno");
					System.out.println("port"+gg);

					SendData sd=new SendData();
					sd.SendResponse(resultvalue,(Integer)hmap.get("portno"));
				}
				//Middle.content.insertRow(Middle.content.getRowCount(),(Vector)ois.readObject());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}
