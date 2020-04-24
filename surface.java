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
	public JPanel caozuo;  //操作面板
	public JTextArea jilu; //记录内容
	public JScrollPane scrollPane;
	
	public JButton PCB;  //打开文件
	public JButton ReadyQueue;  //统计字母
	public JButton BlockQueue;  //文件输出
	public JButton Memory;         //结束
	
	public Font font = new Font("宋体",Font.BOLD,25); 
	
    public surface()
    {
        c = getContentPane();
    	caozuo=new JPanel();
    	PCB=new JButton("显示PCB表信息");
    	PCB.setBounds(0,0,200,100);
    	ReadyQueue=new JButton("显示就绪队列信息");
    	ReadyQueue.setBounds(120,0,200,100);
    	BlockQueue=new JButton("显示阻塞队列信息");
    	BlockQueue.setBounds(240,0,200,10);
    	Memory=new JButton("显示内存信息");
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
   	    		jilu.append("PCB表信息如下："+ "\r\n");
   	    		jilu.append("进程ID" + "\t" + "正在执行的指令"+ "\t" + "运行状态" + "\t" + "指令数目" + "\t" + "运行时间 " + "\r\n" );
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
   	    		jilu.append("就绪队列信息如下："+ "\r\n");
   	    		jilu.append("进程ID" + "\t" +"到达时间"+ "\t" +  "指令数目" + "\t" + "运行时间 " + "\r\n" );
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
   	    		jilu.append("阻塞队列信息如下："+ "\r\n");
   	    		jilu.append("进程ID" + "\t" +"到达时间"+ "\t" +  "指令数目" + "\t" + "运行时间 " + "\r\n" );
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
   	    		jilu.append("内存中物理块表信息如下："+ "\r\n");
   	    		jilu.append("物理块ID" + "\t\t" + "物理块状态"+ "\t" + "所属进程" + "\r\n" );
   	    		jilu.append("");
   	    		jilu.append(s);
   	    	}
   	    });
	 }
	    
}
