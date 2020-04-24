package os;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
//import java.io.*;
import java.awt.*;

public class surface extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Container c;
	public JPanel caozuo;  //�������
	public JTextArea jilu; //��¼����
	public JScrollPane scrollPane;
	
	public JButton PCB;  //���ļ�
	public JButton ReadyQueue;  //ͳ����ĸ
	public JButton BlockQueue;  //�ļ����
	public JButton Memory;         //����
	
	public Font font = new Font("����",Font.BOLD,25); 
	
    public surface()
    {
        c = getContentPane();
    	caozuo=new JPanel();
    	PCB=new JButton("��ʾPCB����Ϣ");
    	PCB.setBounds(0,0,200,100);
    	ReadyQueue=new JButton("��ʾ����������Ϣ");
    	ReadyQueue.setBounds(120,0,200,100);
    	BlockQueue=new JButton("��ʾ����������Ϣ");
    	BlockQueue.setBounds(240,0,200,10);
    	Memory=new JButton("��ʾ�ڴ���Ϣ");
    	Memory.setBounds(360,0,200,10);
        caozuo.add(PCB);
        caozuo.add(ReadyQueue);
        caozuo.add(BlockQueue);
        caozuo.add(Memory);
        caozuo.setVisible(true);
        caozuo.setBounds(0,0,1000,400);
        c.add(caozuo,BorderLayout.SOUTH);
        
        jilu=new JTextArea();
        jilu.setFont(font);
        scrollPane=new JScrollPane();
   	 
        scrollPane.setViewportView(jilu);
   	    c.add(scrollPane,BorderLayout.CENTER);
        //c.add(jilu,BorderLayout.CENTER);
        this.setBounds(300,0,1000,800);
   	    this.setVisible(true);
   	    
	   	    
	   	    
   	    PCB.addActionListener(new ActionListener(){
   	    	public void actionPerformed(ActionEvent event)
   	    	{
   	    		String s = "" ;
   	    		for(int i=0; i<test.pl.length; i++)
   	    		{
   	    			s=s+ test.pl.p.get(i).ProID + "\t" + test.pl.p.get(i).PSW +"\t\t" + test.pl.p.get(i).ProState +  "\t" + test.pl.p.get(i).InstructNum+ "\t" +test.pl.p.get(i).RunTimes + "\r\n "   ;                                                                                               
   	    		}
   	    		jilu.append("PCB����Ϣ���£�"+ "\r\n");
   	    		jilu.append("����ID" + "\t" + "����ִ�е�ָ��"+ "\t" + "����״̬" + "\t" + "ָ����Ŀ" + "\t" + "����ʱ�� " + "\r\n" );
   	    		jilu.append("");
   	    		jilu.append(s);
   	    		
   	    	}
   	    });
	   	    
   	    ReadyQueue.addActionListener(new ActionListener(){
   	    	public void actionPerformed(ActionEvent event)
			{
   	    		String s = "" ;
   	    		for(Process p : test.readyq.BQueue)
   	    		{
   	    			s=s+ p.ProID+ "\t" +p.Intime + "\t"+p.InstructNum+"\t" + p.RunTimes+ "\r\n "   ;                                                                                               
   	    		}
   	    		jilu.append("����������Ϣ���£�"+ "\r\n");
   	    		jilu.append("����ID" + "\t" +"����ʱ��"+ "\t" +  "ָ����Ŀ" + "\t" + "����ʱ�� " + "\r\n" );
   	    		jilu.append("");
   	    		jilu.append(s);
			}
   	    });
   	    BlockQueue.addActionListener(new ActionListener(){
   	    	public void actionPerformed(ActionEvent event)
   	    	{
   	    		String s = "" ;
   	    		for(Process p : test.blockq.BQueue)
   	    		{
   	    			s=s+ p.ProID+ "\t" +p.Intime + "\t"+p.InstructNum+"\t" + p.RunTimes+ "\r\n "   ;                                                                                               
   	    		}
   	    		jilu.append("����������Ϣ���£�"+ "\r\n");
   	    		jilu.append("����ID" + "\t" +"����ʱ��"+ "\t" +  "ָ����Ŀ" + "\t" + "����ʱ�� " + "\r\n" );
   	    		jilu.append("");
   	    		jilu.append(s);
   	    	}
   	    });
   	    Memory.addActionListener(new ActionListener(){
   	    	public void actionPerformed(ActionEvent event)
   	    	{
   	    		String s = "" ;
   	    		for(int i=0; i<test.mem.blocktable.length; i++)
   	    		{
   	    			s=s+ test.mem.blocktable.v.get(i).BlockID + "\t\t" + test.mem.blocktable.v.get(i).BlockState +"\t\t" + test.mem.blocktable.v.get(i).OwnerPro + "\r\n "   ;                                                                                               
   	    		}
   	    		jilu.append("�ڴ�����������Ϣ���£�"+ "\r\n");
   	    		jilu.append("�����ID" + "\t\t" + "�����״̬"+ "\t" + "��������" + "\r\n" );
   	    		jilu.append("");
   	    		jilu.append(s);
   	    	}
   	    });
	 }
	    
}
