import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.sql.*;
import java.util.Random;

public class ContentDataReceive extends Thread
{
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;

	public ContentDataReceive()
	{
		try
		{
		ss=new ServerSocket(1021);
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

				String nodename=""+recv.get(0);
				String dom=""+recv.get(1);
				String des=""+recv.get(2);
				String dist=""+recv.get(3);
				Middle.content.insertRow(Middle.content.getRowCount(),recv);


				System.out.println("Middle.conmap"+Middle.conmap);
				System.out.println("Middle.conmap_dep"+Middle.conmap_dep);

				if(Middle.conmap.containsKey(nodename+dom))
				{
					String val=Middle.conmap.get(nodename+dom);
					System.out.println("val1"+val);
					Middle.conmap.remove(nodename+dom);
					Middle.conmap.put(nodename+dom,val+"---"+des);
				}
				else
				{
					Middle.conmap.put(nodename+dom,des);
				}
				if(Middle.conmap_dep.containsKey(nodename))
				{
					String val=Middle.conmap_dep.get(nodename);
					System.out.println("val2"+val);
					Middle.conmap_dep.remove(nodename);
					Middle.conmap_dep.put(nodename,val+"---"+des);
				}
				else
				{
					Middle.conmap_dep.put(nodename,des);
				}
				DBConnection obj = new DBConnection();

				Connection con = obj.getConnection();

				Statement st = con.createStatement();
				String f="";

				int i=st.executeUpdate("insert into contentmap values ('"+nodename+"','"+dom+"','"+des +"','"+dist+"')");
				if( i==1)
				{
					System.out.println("Register Successfully");

				}

				else
				{
					System.out.println("Register Failed");


				}
				st.close();
				con.close();

				Vector por=new Vector();
				Vector dis=new Vector();

				DBConnection obj1 = new DBConnection();
				Connection con1 = obj1.getConnection();

				Statement st1 = con1.createStatement();

                                ResultSet rs = st1.executeQuery("select * from submap where domain='"+dom+"'");

				while(rs.next())
				{
					String nname=rs.getString(1);
					por.add(rs.getString(2));
					dis.add(rs.getString(4));

				}
                                
				System.out.println("port vector"+por);
				System.out.println("dist"+dis);


				for(int r=0;r<por.size();r++)
				{
					String resultvalue="";
					Random r2=new Random();
					String token="T"+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10);

					Connection con2 = obj.getConnection();
					Statement st2 = con2.createStatement();
					ResultSet rs1 = st2.executeQuery("select description,distance from contentmap where domain='"+dom+"'");
					//filtered result
                                        while(rs1.next())
					{
						String que=rs1.getString(1);
						int tempdis=Integer.parseInt(rs1.getString(2));
						System.out.println(que);
						System.out.println(tempdis);
						if(true)
						{

							Vector vec=new Vector();
							String dis1=(String)dis.get(r);
							int dis2=Integer.parseInt(dis1);

							if(tempdis>=(dis2-50) && tempdis<=(dis2+50))
							{
						 		vec.add(que);
							}
							System.out.println(vec);
							for(int k=0;k<vec.size();k++)
							{
								resultvalue=resultvalue+"---3--->"+vec.get(k);
							}

						}




					}
					System.out.println("new"+resultvalue);
					if(resultvalue !=null &&  !resultvalue.isEmpty()){
						resultvalue=""+token+resultvalue;
					} else {
						resultvalue="";
					}
					resultvalue=resultvalue+"&&"+"filter";
					System.out.println("filter"+resultvalue);
					String port1=(String)por.get(r);
					int port2=Integer.parseInt(port1);
					System.out.println("port"+por.get(r));
					SendData sd=new SendData();
					sd.SendResponse(resultvalue,port2);

				}
				for(int r=0;r<por.size();r++)
				{
					String resultvalue="";
					Random r2=new Random();
					String token="T"+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10);

					Connection con2 = obj.getConnection();
					Statement st2 = con2.createStatement();
					ResultSet rs1 = st1.executeQuery("select description,distance from contentmap where domain='"+dom+"'");
					while(rs1.next())
					{
						String que=rs1.getString(1);
						int tempdis=Integer.parseInt(rs1.getString(2));
						System.out.println(que);
						System.out.println(tempdis);
						if(true)
						{

							Vector vec=new Vector();
							String dis1=(String)dis.get(r);
							int dis2=Integer.parseInt(dis1);

							//if(tempdis>=(dis2-50) && tempdis<=(dis2+50))
							//{
						 		vec.add(que);
							//}
							System.out.println(vec);
							for(int k=0;k<vec.size();k++)
							{
								resultvalue=resultvalue+"---2--->"+vec.get(k);
							}

						}




					}
					System.out.println("new"+resultvalue);
					if(resultvalue !=null &&  !resultvalue.isEmpty()){
						resultvalue=""+token+resultvalue;
					} else {
						resultvalue="";
					}
					resultvalue=resultvalue+"&&"+"independent";
					System.out.println("independent"+resultvalue);
					String port1=(String)por.get(r);
					int port2=Integer.parseInt(port1);
					System.out.println("port"+por.get(r));
					SendData sd=new SendData();
					sd.SendResponse(resultvalue,port2);

				}

                                //MBR filtering
			/*	DBConnection objm = new DBConnection();
				Connection conm = objm.getConnection();

				Statement stm = conm.createStatement();

				ResultSet rsm = stm.executeQuery("select * from submap");                                
				while(rs.next())
				{
					String nname=rs.getString(1);
					por.add(rs.getString(2));
					dis.add(rs.getString(4));

				}   
                                */
                                con1.close();
                                st1.close();
				DBConnection obj3 = new DBConnection();
				Connection con3 = obj3.getConnection();

				Statement st3 = con3.createStatement();

                                ResultSet rsm = st3.executeQuery("select * from submap");
                              
                                while(rsm.next())
				{
					String nname=rsm.getString(1);
					por.add(rsm.getString(2));
					dis.add(rsm.getString(4));

				}
                                //changed till here
                                for(int r=0;r<por.size();r++)
				{
					String resultvalue="";
					Random r2=new Random();
					String token="T"+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10)+""+r2.nextInt(10);

					Connection con2 = obj.getConnection();
					Statement st2 = con2.createStatement();
					ResultSet rs1 = st2.executeQuery("select description,distance from contentmap");
					while(rs1.next())
					{
						String que=rs1.getString(1);
						int tempdis=Integer.parseInt(rs1.getString(2));
						System.out.println(que);
						System.out.println(tempdis);
						if(true)
						{

							Vector vec=new Vector();
							String dis1=(String)dis.get(r);
							int dis2=Integer.parseInt(dis1);

							if(tempdis>=(dis2-50) && tempdis<=(dis2+50))
							{
						 		vec.add(que);
							}
							System.out.println(vec);
							for(int k=0;k<vec.size();k++)
							{
								resultvalue=resultvalue+"---1--->"+vec.get(k);
							}

						}




					}
					System.out.println("new"+resultvalue);
					if(resultvalue !=null &&  !resultvalue.isEmpty()){
						resultvalue=""+token+resultvalue;
					} else {
						resultvalue="";
					}
					resultvalue=resultvalue+"&&"+"dependent";
					System.out.println("dependent"+resultvalue);
					String port1=(String)por.get(r);
					int port2=Integer.parseInt(port1);
					System.out.println("port"+por.get(r));
					SendData sd=new SendData();
					sd.SendResponse(resultvalue,port2);

				}

                        }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}