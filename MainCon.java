import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;


public class MainCon implements ActionListener
{
	//Components

	JFrame mainform;

	JLabel reqlabel,reslabel;


	JButton send;

	public static JTextArea restext,neighnodes,drestext,irestext,frestext;

	JScrollPane restextsp,neighsp,drestextsp,irestextsp,frestextsp;

	JRadioButton r1,r2,r3;

	ButtonGroup bgr;
        JComboBox reqtext;



	//objects

	public static HashMap stodet=new HashMap();

	SendData sd=new SendData();

	ConsumerResponse conres;

	String nodename;

	int portno;

	int distance;


	public MainCon()
	{
                String arr[]={"Channasandra Layout","Channasandra","Uttrahalli","Banashankri","Jaya Nagar","Ragigudda","Silk Board"};
                JComboBox jcb = new JComboBox(arr);
                int msg;
                JOptionPane.showMessageDialog(null,jcb,"select",JOptionPane.QUESTION_MESSAGE);
                msg=jcb.getSelectedIndex();
		distance=msg*40+40;
		Random r=new Random();
		//distance=Integer.parseInt(JOptionPane.showInputDialog("Distance"));
		nodename="S"+r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10);
		portno=Integer.parseInt(r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10));



		conres=new ConsumerResponse(portno);
		mainform=new JFrame(nodename);
		mainform.setLayout(null);

		try
		{
	      	SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSunsetTheme");
			SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceCrosshatchWatermark");
			SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.SpecularGradientPainter");
			SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.ClassicButtonShaper");
		    UIManager.setLookAndFeel(new SubstanceLookAndFeel());
		}
		catch (Exception e)
		{
			e.printStackTrace();
	    }
	      mainform.setDefaultLookAndFeelDecorated(true);


		reqlabel=new JLabel("Query");
		reqlabel.setBounds(70,10,100,30);
		mainform.add(reqlabel);

                String dm[]={"Restraunt","Apple_Store","Furniture_Works","Android"};
		reqtext=new JComboBox(dm);
		reqtext.setBounds(70,40,200,25);
		mainform.add(reqtext);

		send=new JButton("Register");
		send.setBounds(280,40,80,25);
		mainform.add(send);
		send.addActionListener(this);

		restext=new JTextArea();
		restextsp=new JScrollPane(restext);
		restextsp.setBounds(10,150,200,200);
		restextsp.setBorder(BorderFactory.createTitledBorder("Response"));
		//mainform.add(restextsp);

		drestext=new JTextArea();
		drestextsp=new JScrollPane(drestext);
		drestextsp.setBounds(10,300,200,200);
		drestextsp.setBorder(BorderFactory.createTitledBorder("MBR Filtering"));
		mainform.add(drestextsp);


		irestext=new JTextArea();
		irestextsp=new JScrollPane(irestext);
		irestextsp.setBounds(230,300,200,200);
		irestextsp.setBorder(BorderFactory.createTitledBorder("Token Filtering"));
		mainform.add(irestextsp);



		frestext=new JTextArea();
		frestextsp=new JScrollPane(frestext);
		frestextsp.setBounds(450,300,200,200);
		frestextsp.setBorder(BorderFactory.createTitledBorder("Filtered Results"));
		mainform.add(frestextsp);

		neighnodes=new JTextArea();
		neighsp=new JScrollPane(neighnodes);
		neighsp.setBounds(430,30,200,200);
		neighsp.setBorder(BorderFactory.createTitledBorder("Neighbour Nodes"));
		mainform.add(neighsp);

		bgr=new ButtonGroup();
		r1=new JRadioButton("Dependent");
		r1.setBounds(75,85,85,25);
		//mainform.add(r1);

		r2=new JRadioButton("Independent");
		r2.setBounds(170,85,85,25);
		//mainform.add(r2);

		r3=new JRadioButton("Filtering");
		r3.setBounds(265,85,85,25);
		//mainform.add(r3);

		bgr.add(r1);
		bgr.add(r2);
		bgr.add(r3);

		mainform.setSize(700,600);
		mainform.setVisible(true);

		String whois="subscriber";

		new WirelessSender(nodename,portno,whois,distance);
		new WirelessReceiver(nodename,whois,distance);

	}

	public static void main(String args[])
	{
		try
				{
			      	SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSunsetTheme");
					SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceCrosshatchWatermark");
					SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.SpecularGradientPainter");
					SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.ClassicButtonShaper");
				    UIManager.setLookAndFeel(new SubstanceLookAndFeel());
				}
				catch (Exception e)
				{
					e.printStackTrace();
			    }

		new MainCon();
	}

	public void actionPerformed(ActionEvent e)
	{
		//boolean status=r1.isSelected();



		HashMap hmap1=new HashMap();
		hmap1.put("nodename",nodename);
		hmap1.put("portno",portno);
		hmap1.put("query",reqtext.getSelectedItem());
		hmap1.put("distance",distance);
		hmap1.put("status","dependent");
		sd.SendSubscriberRequest(hmap1);


	}

}
