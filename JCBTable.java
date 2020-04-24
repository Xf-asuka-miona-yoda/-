package os;

import java.util.Vector;

public class JCBTable {
	int length;				//表长
	Vector<JCB> p = new Vector<JCB>(length); //JCB的形式存放 
	//方法
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
			JCB t = p.get(i); //获取vector中的元素
			System.out.println("第"+i+"个JCB内容为："+ "\t"+t.JobID + "\t " + t.InTime + "\t " +t.MemoryAddress + "\t " + "7" + "\t " +t.InsNum);
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
				p.removeElementAt(i);      //vector类的删除函数
				length--;
			}	
		}
	}
	public void sort() {//将当前作业按到达时间排序
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
