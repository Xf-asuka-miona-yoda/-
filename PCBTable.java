package os;

import java.util.Vector;

public class PCBTable {
	int length;				//��
	Vector<Process> p = new Vector<Process>(100); //PCB�Խ��̵���ʽ��� 
	//����
	public PCBTable() {
		length = 0;
	}
	public void InsertPCB(Process a,int n) {
		p.add(a);
		length++;
	}	
	public void ShowPCB() {
		System.out.println("PCB����Ϣ���£�");
		for(int i = 0; i < length; i++) 
		{
			Process t = p.get(i); //��ȡvector�е�Ԫ��
			System.out.println("��"+i+"��PCB����ϢΪ��"+ t.ProID + " " + t.JobID + " "  + " " + t.ProState + " " +t.Intime +""+ t.InstructNum);
		}
		
	}	
	public Process SearchPCB(int id) {
		Process a = new Process();
		for(int i = 0; i < length; i++) 
		{
			Process t = p.get(i);
			if(t.ProID  == id) 
			{
				a = t;
			}
		}
		return a;
	}	
	public void DeletePCB(int id) {
		for(int i = 0; i < length; i++) 
		{
			Process t = p.get(i);
			if(t.ProID  == id) 
			{
				p.removeElementAt(i);      //vector���ɾ������
				length--;
			}	
		}
	}

}
