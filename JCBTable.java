package os;

import java.util.Vector;

public class JCBTable {
	int length;				//��
	Vector<JCB> p = new Vector<JCB>(length); //JCB����ʽ��� 
	//����
	public JCBTable() {
		length = 0;
	}
	public void InsertJCB(JCB a,int n) {
		p.insertElementAt(a, n);
		length++;
	}	
	public void ShowJCB() {
		for(int i = 0; i < length; i++) 
		{
			JCB t = p.get(i); //��ȡvector�е�Ԫ��
			System.out.println("��"+i+"��JCB����Ϊ��"+ "\t"+t.JobID + "\t " + t.InTime + "\t " +t.MemoryAddress + "\t " + "7" + "\t " +t.InsNum);
		}
		
	}	
	public JCB SearchJCB(int id) {
		JCB a = new JCB();
		for(int i = 0; i < length ; i++) 
		{
			JCB t = p.get(i);
			if(t.JobID  == id) 
			{
				a = t;
			}
		}
		return a;
	}	
	public void DeleteJCB(int id) {
		for(int i = 0; i < length; i++) 
		{
			
			if(p.get(i).JobID  == id) 
			{
				p.removeElementAt(i);      //vector���ɾ������
				length--;
			}	
		}
	}
	public void sort() {//����ǰ��ҵ������ʱ������
		Vector<JCB> X = new Vector<JCB>(length);
		JCB temp1 = new JCB();
		JCB temp2 = new JCB();
		for (int i=0 ;p.size() >= 1;)
		{
			temp1 = p.get(i);
			for(int j=1; j < p.size() ;++j )
			{
				temp2=p.get(j);
				if(temp1.InTime > temp2.InTime)
				{
					temp1 = temp2;
				}
			}
			X.add(temp1);
			p.remove(temp1);
		}
		
		p = X;
	}
}
