package os;

import java.util.Random;

public class Instruct {
	public int InsID; //ָ��ID
	public int State; //ָ��״̬0 ϵͳ���� 1���û� 2��PV
	public int time; //����ʱ�� �� State=0 �� 1 ʱ,Time=����[10-40]֮������ 10ms ���� ���������� State=2 ʱ,Times=50���û����̷��� I/O �������� 
	public int Address; //��ַ
	public int PV ;
	
	Random r = new Random();
	
	public void init(int id, int a, int pv) {//ָ��ID ����ָ�����ڽ��̵�ָ��������PV��־��
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
