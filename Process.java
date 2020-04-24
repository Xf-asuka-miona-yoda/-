package os;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Process {
	public int ProID; //进程ID
	public int JobID; //对应作业的ID
	public int Priority; //优先数
	public int Intime; //到达时间
	public int ProState;//进程状态 0 就绪，1运行 2阻塞 3完成 -1新建态
	public int NeedTimes;//进程所需运行时间
	public int RunTimes; // 已运行的时间
	public int PSW;//当前正在执行的指令号
	public int InstructNum;//指令数量
	public Instruct[] InstructArr ;//指令数组
	public int AllTime;//周转时间
	public CPU temp = new CPU();
	
	Random r = new Random();
	public Process() {
//		for(int i=0 ; i<20; i++)
//		{
//			InstructArr[i] = new Instruct();
//		}
	}
	
	public synchronized void proinit(int c) {//到达时间
		PSW=0;
//		//InstructArr = new Instruct [b];
//		for(int j=0;j<b;j++)
//		{
//			InstructArr[j] = new Instruct();
//			InstructArr[j].init(j);
//			//System.out.print(InstructArr[j].State+"\n");
//		}
		ProState=-1;
		//Priority = r.nextInt(6)+1;
		Intime= c;
		this.NeedTimes=0;
		for(int i=0;i<this.InstructNum;i++)
		{
			this.NeedTimes += this.InstructArr[i].time;
		}
		temp.PSW=0;
		temp.IR= InstructArr[0];
		temp.PC= ProID;
	}
	public synchronized int profork(PCBTable L,ReadyQueue ready) {// （进程创建）
		Process a= new Process();
		L.InsertPCB(a, L.length);
		ready.EnQueue(a);
		return 0;
	}
	public synchronized int prokill(PCBTable L,FinishQueue finish) { // (进程终止)
		L.DeletePCB(ProID);
		finish.EnQueue(this);
		test.mem.Free(ProID);
		return 0;
	}
	public synchronized void problock(PCBTable L,BlockQueue block,CPU C) { // （进程阻塞）
		block.EnQueue(L.SearchPCB(ProID));
		ProState=2;
	}
	public synchronized void procall(PCBTable L,ReadyQueue ready,BlockQueue block, CPU C) { // （进程唤醒）
		block.DeQueue();
		ready.EnQueue(L.SearchPCB(ProID));
		
	}
	public synchronized void print() { //输出进程信息
		try 
		{
            File memorywirte = new File("PCB信息.txt"); // 相对路径，如果没有则要建立一个新的txt文件
            try (FileWriter writer = new FileWriter(memorywirte,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
        		out.write(this.ProID + "\t" + this.JobID +" \t"+this.InstructNum + "\t" + this.Intime+ "\t"+this.NeedTimes +"\r\n");
                for(int i=0 ; i<this.InstructNum ; i++)
                {
                	out.write("\t\t\t\t\t" + this.InstructArr[i].InsID + "\t" + this.InstructArr[i].State + "\t"+  this.InstructArr[i].time + "\t" + this.InstructArr[i].Address + "\r\n" );
                }
        		out.flush(); // 把缓存区内容压入文件
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	
	
	
}
