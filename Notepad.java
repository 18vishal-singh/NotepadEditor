import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.net.URL;
	
import java.awt.Color;
import java.awt.Font;

class Notepad implements ActionListener
{
	JEditorPane ta;
	JFrame f;
	JFileChooser jfc;
	JMenuBar menuBar;
	JMenu menu,submenu;
	JMenuItem menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,exit;
	

    	int fileToSave;
    	JFileChooser fileSave;

	Notepad()
	{
		JFrame f=new JFrame("NOTEPAD");
		ta=new JEditorPane();
		f.add(ta);
		
		JPopupMenu p=new JPopupMenu("Pop");
		JMenuItem i1=new JMenuItem("cut");
		JMenuItem i2=new JMenuItem("copy");
		JMenuItem i3=new JMenuItem("paste");
		JMenuItem i4=new JMenuItem("exit");
		JMenuItem i5=new JMenuItem("select color");
		p.add(i1);
		p.add(i2);
		p.add(i3);
		p.add(i4);
		p.add(i5);
		ta.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{	
				int x=e.getButton();
				if(x==MouseEvent.BUTTON3)				//3 stand for right click
					p.show(e.getComponent(),e.getX(),e.getY());
			}
		});
		
		i1.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);
		i4.addActionListener(this);
		i5.addActionListener(this);
		
		jfc =new JFileChooser("D:\\java program" );
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		menuItem1 = new JMenuItem("new",KeyEvent.VK_T);
		menuItem1.addActionListener(this);
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu.add(menuItem1);

		menuItem2 = new JMenuItem("open",KeyEvent.VK_T);
		menuItem2.addActionListener(this);
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menu.add(menuItem2);

		menuItem3= new JMenuItem("save",KeyEvent.VK_T);
		menuItem3.addActionListener(this);
		menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(menuItem3);

		menu.addSeparator();

		exit=new JMenuItem("exit");
		exit.addActionListener(this);
		menu.add(exit);
		
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);
		menuItem4 = new JMenuItem("copy",KeyEvent.VK_T);
		menuItem4.addActionListener(this);
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menu.add(menuItem4);
		menuItem5 = new JMenuItem("cut",KeyEvent.VK_T);
		menuItem5.addActionListener(this);
		menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menu.add(menuItem5);
		menuItem6 = new JMenuItem("paste",KeyEvent.VK_T);
		menuItem6.addActionListener(this);
		menuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menu.add(menuItem6);
		menuItem7 = new JMenuItem("text color");
		menuItem7.addActionListener(this);
		menu.add(menuItem7);

		menuBar.add(menu);
		f.setJMenuBar(menuBar);
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public static void main(String...s)
	{
		new Notepad();
	}
	public void actionPerformed(ActionEvent e)
	{
		int x;
		if(e.getActionCommand().equals("exit"))
			System.exit(0);
		if(e.getActionCommand().equals("open"))
		{
			int r=jfc.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION)
			{
				String filePath = jfc.getSelectedFile().getPath();
				try {
					FileInputStream fr = new FileInputStream(filePath);
					InputStreamReader isr = new InputStreamReader(fr, "UTF-8");
					BufferedReader reader = new BufferedReader(isr);
					StringBuffer buffer = new StringBuffer();

					String line = null;
					while ((line = reader.readLine()) != null) 
					{
						buffer.append(line+"\n");
					}

					reader.close();

					ta.setText(buffer.toString());
				    } 
				catch (IOException ex) 
				{
					ex.printStackTrace();
				}
			}
		}
		if(e.getActionCommand().equals("save"))
		{
			saveFile();
    			if (fileToSave == JFileChooser.APPROVE_OPTION)
			{
    				try
				{
   	 				BufferedWriter out = new BufferedWriter(new FileWriter(fileSave.getSelectedFile().getPath()));
    					out.write(ta.getText());
    					out.close();
    				} 	
				catch (Exception ex) 	
				{
    					System.out.println(ex.getMessage());
    				}
    			}
		}
		if(e.getActionCommand().equals("new"))
			new Notepad();
		if(e.getActionCommand().equals("copy"))
			ta.copy();
		if(e.getActionCommand().equals("cut"))
			ta.cut();
		if(e.getActionCommand().equals("paste"))
			ta.paste();
		if(e.getActionCommand().equals("text color"))
		{
			System.out.println("checked");
			setColor();
		}
	}
     
    	public void saveFile()
	{
    		JFileChooser save = new JFileChooser();
   		int option = save.showSaveDialog(null);
    		fileToSave = option;
    		fileSave = save;
    	}
	public void setColor()
	{
		Color bgColor= JColorChooser.showDialog(null,"adavance color selection",Color.RED);
    			if (bgColor != null)
      				ta.setForeground(bgColor);
	}
}
//http://erikalust.com/