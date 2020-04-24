package os;

public class RR {
	public CPU c = new CPU();
	
	public static int q = 0;
	public void run() {
		int round = 50;//时间片大小
		int rest = round;//剩余时间
		
		if(test.blockq.length != 0 && test.source >= 0)
		{
			test.readyq.EnQueue(test.blockq.GetTop());
			System.out.print("第" + test.blockq.GetTop().ProID +"号进程被唤醒"+"\r\n");
			test.blockq.DeQueue();
		}
outterloop:		while(test.readyq.length != 0 )
		{
			Process p = new Process();
			p = test.readyq.GetTop();//取就绪队列队首元素
			test.readyq.DeQueue();
			if(p.PSW == p.InstructNum - 1) 
			{ //如果是最后一条指令
				if(p.InstructArr[p.PSW].State == 2)
				{ //pv指令 v操作
						test.source++;
						c.recover(p);
						p.RunTimes += 50;
						System.out.print(p.ProID+"号进程执行" + p.PSW + "号指令执行v操作释放资源" + "\r\n");
				}
				else while(p.InstructArr[p.PSW].State != 2 && rest > p.InstructArr[p.PSW].time) 
				{ //如果不是PV指令
					
					c.flag = 1;//占用CPU
					c.recover(p);
					rest = rest - c.IR.time; //剩余时间
					// 取出进程ID ，当前执行指令的地址，
					System.out.print("当前执行 " + p.ProID +"号进程" + p.PSW +"指令");
					test.mmu.GO(test.pagetable[p.ProID], p.InstructArr[p.PSW].Address);
					c.clear();
				}
				System.out.print("第" + p.ProID + "号进程调度完成 "  );
				p.prokill(test.pl, test.finishq);
				c.clear();
				break outterloop;

			}
			else if(p.InstructArr[p.PSW].State == 2)
			{ //不是最后一条指令的pv指令，即V操作
				
				if(test.source <= 0)	
				{
					c.recover(p);
					System.out.print(p.ProID+"号进程由于" + p.PSW + "号指令执行P操作阻塞" + "\r\n");
					c.PSW++;
					c.protect(p);
					p.problock(test.pl, test.blockq, c); //将进程阻塞
					c.clear();
					test.source--;
					p.RunTimes += 50;
					//System.out.print(p.ProID+"号进程由于" + p.PSW + "号指令执行P操作阻塞" + "\r\n");
					//break outterLoop;
					break;
				}
				else
				{
					c.recover(p);
					System.out.print(p.ProID+"号进程执行" + p.PSW + "号指令执行P操作，有足够资源，不阻塞" + "\r\n");
					c.PSW++;
					c.protect(p);
					c.clear();
					test.source--;
					p.RunTimes += 50;
				}
			}
			else while(p.InstructArr[p.PSW].State != 2 && rest > p.InstructArr[p.PSW].time) 
			{ //如果不是最后一条指令且不是pv指令
				
				c.flag = 1;//占用CPU
				c.recover(p);
				rest = rest - c.IR.time; //剩余时间
				// 取出进程ID ，当前执行指令的地址，
				System.out.print("当前执行 " + p.ProID +"号进程" + p.PSW +"指令");
				test.mmu.GO(test.pagetable[p.ProID], p.InstructArr[p.PSW].Address);
				if(p.PSW == p.InstructNum - 1) 
				{
					System.out.print("第" + p.ProID + "号进程调度完成 "  );
					p.prokill(test.pl, test.finishq);
					c.clear();
					break outterloop;
				}
				else{
					c.PSW++;
					c.protect(p);//保护现场
					c.clear();
				}
				
			}
			test.readyq.EnQueue(p);
			p.RunTimes += 50;
			break;
		}
		
	}
	
	
}
