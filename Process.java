package os;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Process {
	public int ProID; //����ID
	public int JobID; //��Ӧ��ҵ��ID
	public int Priority; //������
	public int Intime; //����ʱ��
	public int ProState;//����״̬ 0 ������1���� 2���� 3��� -1�½�̬
	public int NeedTimes;//������������ʱ��
	public int RunTimes; // �����е�ʱ��
	public int PSW;//��ǰ����ִ�е�ָ���
	public int InstructNum;//ָ������
	public Instruct[] InstructArr ;//ָ������
	public int AllTime;//��תʱ��
	public CPU temp = new CPU();
	
	Random r = new Random();
	public Process() {
//		for(int i=0 ; i<20; i++)
//		{
//			InstructArr[i] = new Instruct();
//		}
	}
	
	public synchronized void proinit(int c) {//����ʱ��
		PSW=0;
//		//InstructArr = new Instruct [b];
//		for(int j=0;j<b;j++)
//		{
//			InstructArr[j] = new Instruct();
//			InstructArr[j].init(j);
//			//System.out.print(InstructArr[j].State+"\n");
//		}
		ProState=-1;
		//Priority = r.nextInt(6)+1;
		Intime= c;
		this.NeedTimes=0;
		for(int i=0;i<this.InstructNum;i++)
		{
			this.NeedTimes += this.InstructArr[i].time;
		}
		temp.PSW=0;
		temp.IR= InstructArr[0];
		temp.PC= ProID;
	}
	public synchronized int profork(PCBTable L,ReadyQueue ready) {// �����̴�����
		Process a= new Process();
		L.InsertPCB(a, L.length);
		ready.EnQueue(a);
		return 0;
	}
	public synchronized int prokill(PCBTable L,FinishQueue finish) { // (������ֹ)
		L.DeletePCB(ProID);
		finish.EnQueue(this);
		test.mem.Free(ProID);
		return 0;
	}
	public synchronized void problock(PCBTable L,BlockQueue block,CPU C) { // ������������
		block.EnQueue(L.SearchPCB(ProID));
		ProState=2;
	}
	public synchronized void procall(PCBTable L,ReadyQueue ready,BlockQueue block, CPU C) { // �����̻��ѣ�
		block.DeQueue();
		ready.EnQueue(L.SearchPCB(ProID));
		
	}
	public synchronized void print() { //���������Ϣ
		try 
		{
            File memorywirte = new File("PCB��Ϣ.txt"); // ���·�������û����Ҫ����һ���µ�txt�ļ�
            try (FileWriter writer = new FileWriter(memorywirte,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
        		out.write(this.ProID + "\t" + this.JobID +" \t"+this.InstructNum + "\t" + this.Intime+ "\t"+this.NeedTimes +"\r\n");
                for(int i=0 ; i<this.InstructNum ; i++)
                {
                	out.write("\t\t\t\t\t" + this.InstructArr[i].InsID + "\t" + this.InstructArr[i].State + "\t"+  this.InstructArr[i].time + "\t" + this.InstructArr[i].Address + "\r\n" );
                }
        		out.flush(); // �ѻ���������ѹ���ļ�
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	
	
	
}
