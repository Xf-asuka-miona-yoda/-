package os;

import java.util.Random;

public class JCB {
	public int JobID; //作业ID
	public int ProId; //非配给的进程ID
	//public int RunTime; //CPU运行时间
	//public int DeadLine; //截止时间
	public int MemorySize; //所需内存页数
	public int InTime; //进入系统时间
	public int StartTime; //开始运行时间
	public int MemoryAddress; //内存地址
	public int InsNum;
	public String UserName = "OS"; //用户名，默认OS
	public Instruct[] InstructArr = new Instruct[64];
	public int PV; //PV操作标示， 0无，1、 P操作， 2 、 V操作
	Random r = new Random();
	public Process p = new Process();
	public JCB() {
	
		JobID = -1;
		
	}
	public void Init(int jobid, int intime, int num,int memadd , int pv ) {
		JobID = jobid;
		InTime = intime; //500ms内
		InsNum = num;//5 —— 13 条指令
		MemorySize = InsNum; //所分配个作业的内存空间是指令数目减3
		MemoryAddress = memadd;//待调整 ！！
		PV = pv;
		for (int i=0;i<InsNum ;i++)
		{
			InstructArr[i]= new Instruct();
			if(PV == 1 && i == 0)
			{
				InstructArr[i].init(i,InsNum,PV);
			}
			else if(PV == 2 && i == InsNum-1)
			{
				InstructArr[i].init(i,InsNum,PV);
			}
			else if(PV == 3 )
			{
				if( i == 0)
				{
					InstructArr[i].init(i, InsNum, 1);
				}
				else if(i == InsNum-1)
				{
					InstructArr[i].init(i, InsNum, 2);
				}
				else
					InstructArr[i].init(i,InsNum,0);
			}
			else
				InstructArr[i].init(i,InsNum,0);
		}
		
	}
	
	public Process Create() {
		p.JobID = this.JobID;
		p.InstructNum=this.InsNum;
		p.InstructArr= new Instruct[p.InstructNum];
		p.ProID = this.JobID;
		for (int i=0;i<InsNum ;i++)
		{
			p.InstructArr[i]= this.InstructArr[i];
		}
		ProId = JobID;
		return p;
		
	}
	
	public void print() {//输出作业信息
		System.out.println("第"+JobID+"个JCB的信息为："+ JobID + " " + InTime + " " +MemoryAddress + " " + MemorySize + " " +InTime);
	}
	
	
}
