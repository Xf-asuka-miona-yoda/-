package os;

public class RR {
	public CPU c = new CPU();
	
	public static int q = 0;
	public void run() {
		int round = 50;//ʱ��Ƭ��С
		int rest = round;//ʣ��ʱ��
		
		if(test.blockq.length != 0 && test.source >= 0)
		{
			test.readyq.EnQueue(test.blockq.GetTop());
			System.out.print("��" + test.blockq.GetTop().ProID +"�Ž��̱�����"+"\r\n");
			test.blockq.DeQueue();
		}
outterloop:		while(test.readyq.length != 0 )
		{
			Process p = new Process();
			p = test.readyq.GetTop();//ȡ�������ж���Ԫ��
			test.readyq.DeQueue();
			if(p.PSW == p.InstructNum - 1) 
			{ //��������һ��ָ��
				if(p.InstructArr[p.PSW].State == 2)
				{ //pvָ�� v����
						test.source++;
						c.recover(p);
						p.RunTimes += 50;
						System.out.print(p.ProID+"�Ž���ִ��" + p.PSW + "��ָ��ִ��v�����ͷ���Դ" + "\r\n");
				}
				else while(p.InstructArr[p.PSW].State != 2 && rest > p.InstructArr[p.PSW].time) 
				{ //�������PVָ��
					
					c.flag = 1;//ռ��CPU
					c.recover(p);
					rest = rest - c.IR.time; //ʣ��ʱ��
					// ȡ������ID ����ǰִ��ָ��ĵ�ַ��
					System.out.print("��ǰִ�� " + p.ProID +"�Ž���" + p.PSW +"ָ��");
					test.mmu.GO(test.pagetable[p.ProID], p.InstructArr[p.PSW].Address);
					c.clear();
				}
				System.out.print("��" + p.ProID + "�Ž��̵������ "  );
				p.prokill(test.pl, test.finishq);
				c.clear();
				break outterloop;

			}
			else if(p.InstructArr[p.PSW].State == 2)
			{ //�������һ��ָ���pvָ���V����
				
				if(test.source <= 0)	
				{
					c.recover(p);
					System.out.print(p.ProID+"�Ž�������" + p.PSW + "��ָ��ִ��P��������" + "\r\n");
					c.PSW++;
					c.protect(p);
					p.problock(test.pl, test.blockq, c); //����������
					c.clear();
					test.source--;
					p.RunTimes += 50;
					//System.out.print(p.ProID+"�Ž�������" + p.PSW + "��ָ��ִ��P��������" + "\r\n");
					//break outterLoop;
					break;
				}
				else
				{
					c.recover(p);
					System.out.print(p.ProID+"�Ž���ִ��" + p.PSW + "��ָ��ִ��P���������㹻��Դ��������" + "\r\n");
					c.PSW++;
					c.protect(p);
					c.clear();
					test.source--;
					p.RunTimes += 50;
				}
			}
			else while(p.InstructArr[p.PSW].State != 2 && rest > p.InstructArr[p.PSW].time) 
			{ //����������һ��ָ���Ҳ���pvָ��
				
				c.flag = 1;//ռ��CPU
				c.recover(p);
				rest = rest - c.IR.time; //ʣ��ʱ��
				// ȡ������ID ����ǰִ��ָ��ĵ�ַ��
				System.out.print("��ǰִ�� " + p.ProID +"�Ž���" + p.PSW +"ָ��");
				test.mmu.GO(test.pagetable[p.ProID], p.InstructArr[p.PSW].Address);
				if(p.PSW == p.InstructNum - 1) 
				{
					System.out.print("��" + p.ProID + "�Ž��̵������ "  );
					p.prokill(test.pl, test.finishq);
					c.clear();
					break outterloop;
				}
				else{
					c.PSW++;
					c.protect(p);//�����ֳ�
					c.clear();
				}
				
			}
			test.readyq.EnQueue(p);
			p.RunTimes += 50;
			break;
		}
		
	}
	
	
}
