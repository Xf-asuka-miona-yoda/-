package os;

import java.util.Random;
import java.util.TimerTask;


public class CreatePro extends TimerTask {
	public static int Clock1 = 0; // 10ms 检查已从，记录进程创建时间
	public PCBTable L;
	public static int i=0;//统计以创建的进程个数
	Random r = new Random();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (test.poolq.length > 0 )//首先判断后备队列，若不为空
		
		{
			if(test.mem.FreeSpace >= 7)  //如果后备队列的队首元素能获取到足够内存
			{
				Process temp = test.poolq.GetTop().Create();
				test.poolq.DeQueue();
				temp.proinit( Clock1 * 10 );
				temp.print();
			
				test.mem.Set(test.jl.SearchJCB(test.pl.length+1).MemorySize,test.pl.length+1,test.pagetable[test.pl.length+1]);
				test.pl.InsertPCB(temp, test.pl.length+1);
				test.readyq.EnQueue(temp);
					
				//i++;
			}
			else
				break;//内存空间不足，退出循环
		}
		for( int j=i ; j < test.jl.length ; j++) //后备队列没有满足要求的作业，查找jcb表
		{
			
			if(test.jl.SearchJCB(j).InTime <= Clock1*10 && test.mem.FreeSpace > 7)//若作业到达时间小于当前时间，且内存空间有剩余，则分配进程。
			{
				Process temp = test.jl.SearchJCB(j).Create();
				temp.proinit( Clock1 * 10 );
				temp.print();
				test.mem.Set(test.jl.SearchJCB(j).MemorySize,i,test.pagetable[i]);
				test.pl.InsertPCB(temp, i);
				test.readyq.EnQueue(temp);
				
				i++;
			}
			
			else if(test.jl.SearchJCB(j).InTime <= Clock1*10) { //内存空间不足，加入后备队列
				JCB temp = test.jl.SearchJCB(j);
				test.poolq.EnQueue(temp);
				i++;
			}
			else 
				break;//没有合适进程，退出循环
		}
		Clock1 ++; //计时加10
		//test.poolq.PrintQueue();
	}

}
