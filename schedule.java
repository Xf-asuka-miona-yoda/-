package os;


import java.util.TimerTask;

public class schedule extends TimerTask{
	public RR rr = new RR();

	public static int Clock2 = 0; // 50msһ��ʱ��Ƭ����¼��������ʱ�䣻
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		rr.run();
		System.out.print("\r\n");
		System.out.print("/*********************************************************************************************/"+"\r\n");
		if(test.readyq.length == 0 && test.blockq.length == 0)
		{
			for(int i = 0;i<test.k;i++) {
				test.mem.File(test.pagetable[i], "ҳ��.txt");
				test.pagetable[i].File(i,"ҳ���û�.txt");
			}
			
			
			System.out.print("\r\n"+"�������" +"\r\n");
			this.cancel();
		}
	}
	
}
