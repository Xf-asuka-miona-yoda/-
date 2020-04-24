package os;

import java.util.Random;

public class JCB {
	public int JobID; //��ҵID
	public int ProId; //������Ľ���ID
	//public int RunTime; //CPU����ʱ��
	//public int DeadLine; //��ֹʱ��
	public int MemorySize; //�����ڴ�ҳ��
	public int InTime; //����ϵͳʱ��
	public int StartTime; //��ʼ����ʱ��
	public int MemoryAddress; //�ڴ��ַ
	public int InsNum;
	public String UserName = "OS"; //�û�����Ĭ��OS
	public Instruct[] InstructArr = new Instruct[64];
	public int PV; //PV������ʾ�� 0�ޣ�1�� P������ 2 �� V����
	Random r = new Random();
	public Process p = new Process();
	public JCB() {
	
		JobID = -1;
		
	}
	public void Init(int jobid, int intime, int num,int memadd , int pv ) {
		JobID = jobid;
		InTime = intime; //500ms��
		InsNum = num;//5 ���� 13 ��ָ��
		MemorySize = InsNum; //���������ҵ���ڴ�ռ���ָ����Ŀ��3
		MemoryAddress = memadd;//������ ����
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
	
	public void print() {//�����ҵ��Ϣ
		System.out.println("��"+JobID+"��JCB����ϢΪ��"+ JobID + " " + InTime + " " +MemoryAddress + " " + MemorySize + " " +InTime);
	}
	
	
}
