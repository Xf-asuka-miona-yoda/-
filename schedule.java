package os;


import java.util.TimerTask;

public class schedule extends TimerTask{
	public RR rr = new RR();

	public static int Clock2 = 0; // 50ms一个时间片，记录进程运行时间；
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		rr.run();
		System.out.print("\r\n");
		System.out.print("/*********************************************************************************************/"+"\r\n");
		if(test.readyq.length == 0 && test.blockq.length == 0)
		{
			for(int i = 0;i<test.k;i++) {
				test.mem.File(test.pagetable[i], "页表.txt");
				test.pagetable[i].File(i,"页面置换.txt");
			}
			
			
			System.out.print("\r\n"+"调度完成" +"\r\n");
			this.cancel();
		}
	}
	
}
