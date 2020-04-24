package os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MMU {
	PageTable pagetableaddr; //ҳ��Ĵ���
	Random r = new Random();
	
	public MMU() 
	{
		pagetableaddr = new PageTable(-1);
		for(int i = 0; i < 10; i++) 
		{
			Page a = new Page();
			a.InitPage(i, 1);
			pagetableaddr.InsertPage(a, i);
		}
	}
	
	public void Breakaddr(int addr,Addr a) 
	{
		a.pageid = addr/512;
		a.pianyi = addr%512;
	}
	
	public void MissingPage() 
	{
		int a = pagetableaddr.p.get(0).Reference;
		int n = 0;
		for(int i = 0;i<pagetableaddr.length;i++) 
		{
			if(pagetableaddr.p.get(i).Reference > a) 
			{
				a = pagetableaddr.p.get(i).Reference;
				n = i;
			}
		}
		pagetableaddr.p.get(n).Dwell = 0;
		pagetableaddr.p.get(n).Reference = 0;
		
//		if(pagetableaddr.p.get(n).Modi == 1)  //����������޸��轫ҳ��д�����
//		{
//			try 
//			{
//				File memorywirte = new File("ҳ���û�.txt"); // ���·�������û����Ҫ����һ���µ�txt�ļ�
//				try (FileWriter writer = new FileWriter(memorywirte,true);
//						BufferedWriter out = new BufferedWriter(writer)
//					) 
//				{
//					out.write("\r\n����һ��ҳ���û�\r\n");
//					out.write("ҳ��" + "\t" + "���"+ "\t" +"פ��λ" + "\t" + "�����ֶ�" + "\t" +"�޸�λ"+"\r\n");
//					out.write(pagetableaddr.p.get(n).PageID + "\t" + pagetableaddr.p.get(n).BlockID + "\t" +pagetableaddr.p.get(n).Dwell + "\t" + pagetableaddr.p.get(n).Reference + "\t" +pagetableaddr.p.get(n).Modi+"\r\n");
//            		out.flush(); // �ѻ���������ѹ���ļ�
//				}
//			} 
//			catch (IOException e) 
//			{
//				e.printStackTrace();
//			}
//		}
		
	}
	
	
	public void VisitPageTable(Addr a)
	{
		Page temp = new Page();
		temp = pagetableaddr.SearchPage(a.pageid);
		a.trueaddr = temp.BlockID * 1000 + a.pianyi;
		int k = 0;
		for(int i = 0;i<pagetableaddr.length;i++)
		{
			if(pagetableaddr.p.get(i).Dwell == 1)
			{
				k++;
			}
		}
		
		for(int i = 0;i<pagetableaddr.length;i++) 
		{    //�����ֶ�����
			if(pagetableaddr.p.get(i).PageID != a.pageid && pagetableaddr.p.get(i).Dwell == 1) 
			{
				pagetableaddr.p.get(i).Reference++;
			}
			if(pagetableaddr.p.get(i).PageID == a.pageid) 
			{
				pagetableaddr.p.get(i).Reference = 0;
				int s = r.nextInt(10);
				if((s % 3) == 0) 
				{
					pagetableaddr.p.get(i).Modi = 1;
				}
			}
		}
		System.out.println("�����ַΪ��"+a.trueaddr);
		
		if(temp.Dwell == 0) 
		{	
			System.out.println("����ȱҳ�ж�");
			System.out.println("��ҳ���Ѿ�����");
			System.out.println("��Ҫд�ص�ҳ����д�����");
			System.out.println("����ִ���ж�ָ��");
			for(int i = 0;i<pagetableaddr.length;i++) 
			{
				if(pagetableaddr.p.get(i).PageID == a.pageid) 
				{
					
					pagetableaddr.p.get(i).Dwell = 1;
					break;
				}
			}
			if(k >= 7)
			{
				MissingPage();//LRU�㷨
			}
		}
	}
	
	
	public void GO(PageTable p,int addr)
	{
		Addr a = new Addr();
		pagetableaddr = p;	
		Breakaddr(addr,a);
		System.out.println("�߼���ַ�ֽ���ɣ�ҳ�ţ�"+a.pageid+",ƫ�Ƶ�ַ�� "+a.pianyi);
		VisitPageTable(a);
		p = pagetableaddr;
	}
	
//	public static void main(String args[]) {
//		MMU m = new MMU();
//		PageTable p = new PageTable();
//		for(int i = 0; i < 10; i++) {
//			Page a = new Page();
//			a.InitPage(i, 1);
//			p.InsertPage(a, i);
//		}
//		
//		int addr = 10512;
//		int add = 512;
//		p.p.get(0).Modi = 1;
//		m.GO(p,addr);
//		p.ShowPage();
//		m.GO(p, add);
//		p.ShowPage();
//		m.GO(p, 2048);
//		p.ShowPage();
//		m.GO(p, 6527);
//		p.ShowPage();
//		m.GO(p, 7545);
//		p.ShowPage();
//	}
}
