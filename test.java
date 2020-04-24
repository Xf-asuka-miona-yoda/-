package os;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Timer;

public class test {
	public static Random r = new Random();
	public static int k = 20; //进程数量（小于20）
	public static int source = 1; //设为0 ，同步信号量,1为互斥信号量
	public File output;
	
	public static MMU mmu = new MMU();
	public static Memory mem = new Memory();
	public static JCB[] job = new JCB[k];
	public static Process[] pcb = new Process[k];//创建进程空间;
	
	public static ReadyQueue readyq = new ReadyQueue();
	public static BlockQueue blockq = new BlockQueue();
	public static FinishQueue finishq = new FinishQueue();
	public static PoolQueue poolq = new PoolQueue();
	
	public static PCBTable pl = new PCBTable();
	public static JCBTable jl = new JCBTable();
	
	//public static int x;
	//public static int y;
	
	public static PageTable[] pagetable = new PageTable[k]; //为每个进程的页表开辟空间;
	public static CreatePro create = new CreatePro(); //作业动态生成线程
	public static schedule sch = new schedule();//作业调度线程
	
	public static Timer timer1 = new Timer();//动态生成
	public static Timer timer2 = new Timer();//执行调度
	public static surface surf;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建作业空间
		surf = new surface();
		File file =new File("内存.txt");//输出内存信息到文件
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        File pcbfile =new File("PCB信息.txt"); //输出进程信息到文件
        try {
            if(!pcbfile.exists()) {
                pcbfile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(pcbfile);
            fileWriter.write("\r\n PCB表信息如下： \r\n");
            fileWriter.write("进程编号"+ "\t" +"作业编号" + "\t " + "指令数目" + "\t" + "到达时间" + " \t"+ "所需时间" +"\t" + "指令编号" +"\t"+ "指令状态" +"\t"+ "指令时间"+ "\t" +"指令地址" + "\r\n");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
		
        File pagetablefile =new File("页表.txt");//输出页表信息到文件
        try {
            if(!pagetablefile.exists()) {
            	pagetablefile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(pagetablefile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        File pagetablezfile =new File("页面置换.txt");//输出页表信息到文件
        try {
            if(!pagetablezfile.exists()) {
            	pagetablezfile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(pagetablezfile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
		try //将out流重定向到文件 运行结果.txt
		{
            File output = new File("运行结果.txt"); // 相对路径，如果没有则要建立一个新的txt文件
            PrintStream ps=new PrintStream(new FileOutputStream(output));
            System.setOut(ps);            
            
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		
		
		
		
		
		for(int i=0 ;i < k ; i++)//为作业，进程，页表开辟空间
		{
			job[i] = new JCB();
			pcb[i] = new Process();
			pagetable[i] = new PageTable(i);
		}
		
		
		
		
		try (FileReader reader = new FileReader("DISK/track  (1)/sector.txt");//从外存读入JCB的信息
	             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
	        ) {
	            String line;
	            int i = 0;
	            while ((line = br.readLine()) != null) {//以逗号为分割符分割所读出的字符串
	            	String[] sourceStrArray = line.split(",",5);
	            	job[i].JobID=Integer.parseInt(sourceStrArray[0]);
	            	job[i].InsNum=Integer.parseInt(sourceStrArray[1]);
	            	job[i].InTime=Integer.parseInt(sourceStrArray[2]);
	            	job[i].PV = Integer.parseInt(sourceStrArray[3]);
	            	job[i].UserName=sourceStrArray[4];
	            	i++;
	            }
	            k=i;
	        } 
		catch (IOException e) 
		{
			e.printStackTrace();
	    }
		

       
		
		for (int i = 0 ; i < k ; i++) {//将所有作业加入到JCB表
				job[i].Init(i ,job[i].InTime ,job[i].InsNum,r.nextInt(1000)+1,job[i].PV);//作业ID ，进入时间，指令数目，指令地址   
				jl.InsertJCB(job[i], i);
		}
		jl.sort();//把JCB按照到达时间排序
		for(int i =0 ; i< k ;i++) //重排作业序号
		{
			jl.p.get(i).JobID=i;
		}
		
		
		for(int i = 0;i < jl.length;i++) //往外存写入指令信息
		{
			for(int j = 0;j < jl.p.get(i).InsNum;j++)
			{
				String Filepath =  "DISK/track  ("+ i + ")/sector (" + j +").txt";
				 File Insfile =new File(Filepath);
		        try {
		            if(!Insfile.exists()) {
		                Insfile.createNewFile();
		            }
		            FileWriter fileWriter =new FileWriter(Insfile);
		            fileWriter.write("第"+ i +"号作业的第"+j+"条指令" + "\r\n");
		            fileWriter.write("指令状态"+ "\t" +"所需时间" + "\t"+"PV信息"+"\t"+"逻辑地址"+"\r\n" );
		            fileWriter.write( jl.p.get(i).InstructArr[j].State +"\t"+ jl.p.get(i).InstructArr[j].time + "\t"+jl.p.get(i).InstructArr[j].PV+"\t"+jl.p.get(i).InstructArr[j].Address);
		            fileWriter.flush();
		            fileWriter.close();
		        }
		        catch (IOException e) {
		            e.printStackTrace();
		        }
			}
           
       } 
		
		
		System.out.println("JCB表信息如下：");
		System.out.println("\t\t" + "作业序号 " + "\t" + "到达时间 " + "\t"+ "内存地址 " + "\t" + "内存大小 " +"\t"+"指令数量");
		test.jl.ShowJCB();
		timer1.scheduleAtFixedRate(create,0,10);//没隔10秒检查是否有新的作业请求生成进程；
		timer2.scheduleAtFixedRate(sch, 140, 50);//50ms 一个时间片执行进程调度
		
		
	}

}
