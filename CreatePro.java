package os;

import java.util.Random;
import java.util.TimerTask;


public class CreatePro extends TimerTask {
	public static int Clock1 = 0; // 10ms ����Ѵӣ���¼���̴���ʱ��
	public PCBTable L;
	public static int i=0;//ͳ���Դ����Ľ��̸���
	Random r = new Random();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (test.poolq.length > 0 )//�����жϺ󱸶��У�����Ϊ��
		
		{
			if(test.mem.FreeSpace >= 7)  //����󱸶��еĶ���Ԫ���ܻ�ȡ���㹻�ڴ�
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
				break;//�ڴ�ռ䲻�㣬�˳�ѭ��
		}
		for( int j=i ; j < test.jl.length ; j++) //�󱸶���û������Ҫ�����ҵ������jcb��
		{
			
			if(test.jl.SearchJCB(j).InTime <= Clock1*10 && test.mem.FreeSpace > 7)//����ҵ����ʱ��С�ڵ�ǰʱ�䣬���ڴ�ռ���ʣ�࣬�������̡�
			{
				Process temp = test.jl.SearchJCB(j).Create();
				temp.proinit( Clock1 * 10 );
				temp.print();
				test.mem.Set(test.jl.SearchJCB(j).MemorySize,i,test.pagetable[i]);
				test.pl.InsertPCB(temp, i);
				test.readyq.EnQueue(temp);
				
				i++;
			}
			
			else if(test.jl.SearchJCB(j).InTime <= Clock1*10) { //�ڴ�ռ䲻�㣬����󱸶���
				JCB temp = test.jl.SearchJCB(j);
				test.poolq.EnQueue(temp);
				i++;
			}
			else 
				break;//û�к��ʽ��̣��˳�ѭ��
		}
		Clock1 ++; //��ʱ��10
		//test.poolq.PrintQueue();
	}

}
