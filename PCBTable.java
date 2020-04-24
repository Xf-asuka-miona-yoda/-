package os;

import java.util.Vector;

public class PCBTable {
	int length;				//表长
	Vector<Process> p = new Vector<Process>(100); //PCB以进程的形式存放 
	//方法
	public PCBTable() {
		length = 0;
	}
	public void InsertPCB(Process a,int n) {
		p.add(a);
		length++;
	}	
	public void ShowPCB() {
		System.out.println("PCB表信息如下：");
		for(int i = 0; i < length; i++) 
		{
			Process t = p.get(i); //获取vector中的元素
			System.out.println("第"+i+"个PCB的信息为："+ t.ProID + " " + t.JobID + " "  + " " + t.ProState + " " +t.Intime +""+ t.InstructNum);
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
				p.removeElementAt(i);      //vector类的删除函数
				length--;
			}	
		}
	}

}
