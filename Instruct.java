package os;

import java.util.Random;

public class Instruct {
	public int InsID; //指令ID
	public int State; //指令状态0 系统调用 1、用户 2、PV
	public int time; //所需时间 当 State=0 或 1 时,Time=产生[10-40]之间的随机 10ms 倍数 的整数；当 State=2 时,Times=50，用户进程发生 I/O 阻塞请求 
	public int Address; //地址
	public int PV ;
	
	Random r = new Random();
	
	public void init(int id, int a, int pv) {//指令ID ，此指令所在进程的指令数量，PV标志符
		InsID= id;
		State = r.nextInt(2);
		if(pv == 1 || pv == 2)
			{
				this.State = 2;
				time = 50;
				PV = pv;
			}
		else
			time = (r.nextInt(4)+1)*10;
		Address = id * 512+1;
		
	}

}
