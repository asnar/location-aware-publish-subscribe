import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;


	public class Middle 
	{

		//component
		
		JFrame mainform;
		
		public static DefaultTableModel reg,content,regc;
		
		JTable regtable,contenttable,regctable;
		
		JScrollPane regsp,contentsp,regcsp;
		
		//Objects 
		
		public static HashMap<String,Integer> regmap=new HashMap<String,Integer>();
		
		public static HashMap<String,String> conmap=new HashMap<String,String>();
		
		public static HashMap<String,String> conmap_dep=new HashMap<String,String>();
		
		
		RegisterDataReceive rdr1=new RegisterDataReceive();
		ContentDataReceive cdr=new ContentDataReceive();
		RequestDataReceive redr=new RequestDataReceive();
		ContentRegDataReceive cdr1=new ContentRegDataReceive();
		DeleteData dd=new DeleteData();
			
	public Middle() 
	{
		mainform=new JFrame("Middle Tier");
		mainform.setLayout(new GridLayout(1,2));
		
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
		
		reg=new DefaultTableModel();
		regtable=new JTable(reg);
		reg.addColumn("Nodename");
		reg.addColumn("Distance");
		regsp=new JScrollPane(regtable);
		regsp.setBorder(BorderFactory.createTitledBorder("Register"));
		
		content=new DefaultTableModel();
		contenttable=new JTable(content);
		content.addColumn("Nodename");
		content.addColumn("Domain");
		content.addColumn("Description");
		contentsp=new JScrollPane(contenttable);
		contentsp.setBorder(BorderFactory.createTitledBorder("Publish Content"));

		regc=new DefaultTableModel();
		regctable=new JTable(regc);
		regc.addColumn("Nodename");
		regc.addColumn("Port No");
		regc.addColumn("Domain");
		regc.addColumn("Distance");
		regcsp=new JScrollPane(regctable);
		regcsp.setBorder(BorderFactory.createTitledBorder("Subscribe Query"));
		
		mainform.add(regsp);
		mainform.add(contentsp);
		mainform.add(regcsp);
		
		mainform.setResizable(false);
		mainform.setSize(700,350);
		mainform.setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Middle();
	}

}
