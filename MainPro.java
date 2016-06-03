
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;


public class MainPro implements ActionListener
{
	//components
        JComboBox domaintext;
        
	JFrame mainform;

	JPanel mainpanel;

	JLabel domainlabel,descriptionlabel;

	JTextField descriptiontext;

	JButton send,clear;

	public static JTextArea neighnodes;

	//variables

	String nodename;

	public static HashMap stodet=new HashMap();

	SendData sdata=new SendData();
	int distance;

	public MainPro()
	{
		try
				{
			      	SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSunsetTheme");
					SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceExceedWatermark");
					SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.MixDelegateGradientPainter");
					SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.ClassicButtonShaper");
				    UIManager.setLookAndFeel(new SubstanceLookAndFeel());
				}
				catch (Exception e)
				{
					e.printStackTrace();
	    }
		Random r=new Random();
                String arr[]={"Channasandra Layout","Channasandra","Uttrahalli","Banashankri","Jaya Nagar","Ragigudda","Silk Board"};
                JComboBox jcb = new JComboBox(arr);
                int msg;
                JOptionPane.showMessageDialog(null,jcb,"select",JOptionPane.QUESTION_MESSAGE);
                msg=jcb.getSelectedIndex();
		distance=msg*40+40;//Integer.parseInt(JOptionPane.showInputDialog("Distance"));
		nodename="P"+r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10);
		sdata.SendRegister(nodename, distance);

		mainform=new JFrame(nodename);


	      mainform.setDefaultLookAndFeelDecorated(true);

		mainpanel=new JPanel();
		mainpanel.setLayout(null);

		domainlabel=new JLabel("Domain");
		domainlabel.setBounds(50,50,80,25);
		mainpanel.add(domainlabel);
                //heeerrreee
                String dm[]={"Restraunt","Apple_Store","Furniture_Works","Android"};
		domaintext=new JComboBox(dm);
		domaintext.setBounds(130,50,100,25);
                mainpanel.add(domaintext);

		descriptionlabel=new JLabel("Description");
		descriptionlabel.setBounds(50,90,80,25);
		mainpanel.add(descriptionlabel);

		descriptiontext=new JTextField(20);
		descriptiontext.setBounds(130,90,100,25);
		mainpanel.add(descriptiontext);

		send=new JButton("Send");
		send.setBounds(60,130,75,25);
		mainpanel.add(send);
		send.addActionListener(this);

		clear=new JButton("Exit");
		clear.setBounds(140,130,75,25);
		mainpanel.add(clear);
		clear.addActionListener(this);

		neighnodes=new JTextArea();
		neighnodes.setBounds(50,200,200,150);
		neighnodes.setBorder(BorderFactory.createTitledBorder("Neighbour Nodes"));
		mainpanel.add(neighnodes);
		mainpanel.add(new JLabel());

		mainform.add(mainpanel);
		//mainform.setResizable(false);
		mainform.setSize(300,400);
		mainform.setVisible(true);

		String whois="publisher";

		new WirelessSender(nodename,10,whois,distance);
		new WirelessReceiver(nodename,whois,distance);


	}


	public static void main(String[] args)
	{
		new MainPro();
	}


	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==clear)
		{
			System.exit(0);
		}

		if(ae.getSource()==send)
		{
			Vector v=new Vector();
			v.add(nodename);
			v.add(domaintext.getSelectedItem());
			v.add(descriptiontext.getText());
			v.add(distance);
			sdata.SendContent(v);
                        domaintext.setEditable(true);
                        domaintext.setSelectedItem(" ");
                        descriptiontext.setText(" ");
		}


	}

}
