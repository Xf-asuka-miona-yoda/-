package os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MMU {
	PageTable pagetableaddr; //页表寄存器
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
		
//		if(pagetableaddr.p.get(n).Modi == 1)  //如果发生过修改需将页面写回外存
//		{
//			try 
//			{
//				File memorywirte = new File("页面置换.txt"); // 相对路径，如果没有则要建立一个新的txt文件
//				try (FileWriter writer = new FileWriter(memorywirte,true);
//						BufferedWriter out = new BufferedWriter(writer)
//					) 
//				{
//					out.write("\r\n发生一次页面置换\r\n");
//					out.write("页号" + "\t" + "块号"+ "\t" +"驻留位" + "\t" + "访问字段" + "\t" +"修改位"+"\r\n");
//					out.write(pagetableaddr.p.get(n).PageID + "\t" + pagetableaddr.p.get(n).BlockID + "\t" +pagetableaddr.p.get(n).Dwell + "\t" + pagetableaddr.p.get(n).Reference + "\t" +pagetableaddr.p.get(n).Modi+"\r\n");
//            		out.flush(); // 把缓存区内容压入文件
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
		{    //访问字段增加
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
		System.out.println("物理地址为："+a.trueaddr);
		
		if(temp.Dwell == 0) 
		{	
			System.out.println("产生缺页中断");
			System.out.println("新页面已经置入");
			System.out.println("需要写回的页面已写回外存");
			System.out.println("重新执行中断指令");
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
				MissingPage();//LRU算法
			}
		}
	}
	
	
	public void GO(PageTable p,int addr)
	{
		Addr a = new Addr();
		pagetableaddr = p;	
		Breakaddr(addr,a);
		System.out.println("逻辑地址分解完成：页号："+a.pageid+",偏移地址： "+a.pianyi);
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
