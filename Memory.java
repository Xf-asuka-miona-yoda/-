package os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Memory {
	int Size;		//内存大小
	int BlockNum;	//物理块数量
	int BlockSize;	//物理块大小
	
	BlockTable blocktable;	//物理块表
	int FreeSpace;	//空闲空间
	int UsedSpace;	//已用空间
	File memorywirte; //存放内存信息的文件
	int[] a = {0,0,0,0,0,0,0,0,0}; //固定分区标识位
	
	public Memory() 
	{
		Size = 32768;
		BlockNum = 64;
		BlockSize = 512;
		blocktable = new BlockTable(BlockNum);
		FreeSpace = BlockNum;
		UsedSpace = 0;
	}
	
	public int Set(int size,int JobId,PageTable pagetable) 
	{//分配资源 返回0分配失败，返回1成功
		for(int k = 0;k < size;k++) 
		{ //初始化页表
			Page page = new Page();
			page.InitPage(k, 0);
			pagetable.InsertPage(page, k);
		}
		
		for(int i = 0;i < 9;i++) 
		{
			if(a[i] == 0) 
			{
				for(int j = 0;j < 7;j++) 
				{
					blocktable.v.get(i*7+j).BlockState = 1;
					blocktable.v.get(i*7+j).OwnerPro = JobId;
				}
				a[i] = 1;
				FreeSpace = FreeSpace - 7;
				UsedSpace = UsedSpace + 7;
				break;
			}
		}	
		
		try 
		{
			File memorywirte = new File("内存.txt"); // 相对路径，如果没有则要建立一个新的txt文件
	        try (FileWriter writer = new FileWriter(memorywirte,true);
	               BufferedWriter out = new BufferedWriter(writer)
	        		) 
	        {
	        	out.write("\r\n在为某进程分配资源之后内存物理块表信息如下：\r\n");
	        	out.write("块号"+ "\t" +"占用位" + "\t" + "所属进程" + "\r\n");
	            for(int i = 0; i < blocktable.length; i++) 
	            {
	        		Block t = blocktable.v.get(i); //获取vector中的元素
	        		out.write(t.BlockID + "\t" +t.BlockState + "\t" + t.OwnerPro+"\r\n");
	        	}
	            out.flush(); // 把缓存区内容压入文件
	        }
	    } 
		catch (IOException e) 
		{
			e.printStackTrace();
	    }
		return 1; //分配成功
		
	}
	
	public int Free(int ProId) 
	{
		int j = 0;
		for(int i = 0;i < 64;i++)
		{
			if(blocktable.v.get(i).OwnerPro == ProId)
			{
				j = i / 7;
				break;
			}
		}
		
		for(int i = 0;i < 64;i++)
		{
			if(blocktable.v.get(i).OwnerPro == ProId)
			{
				blocktable.v.get(i).BlockState = 0;
				blocktable.v.get(i).OwnerPro = -1;
			}
		}
		a[j] = 0;
		FreeSpace = FreeSpace + 7;
		UsedSpace = UsedSpace - 7;
		try 
		{
            File memorywirte = new File("内存.txt"); // 相对路径，如果没有则要建立一个新的txt文件
            try (FileWriter writer = new FileWriter(memorywirte,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
            	out.write("\r\n在为某进程回收资源之后内存物理块表信息如下：\r\n");
            	out.write("块号"+ "\t" +"占用位" + "\t" + "所属进程" + "\r\n");
            	for(int i1 = 0; i1 < blocktable.length; i1++) 
            	{
        			Block t = blocktable.v.get(i1); //获取vector中的元素
        			out.write(t.BlockID + "\t" +t.BlockState + "\t" + t.OwnerPro+"\r\n");
        		}
                out.flush(); // 把缓存区内容压入文件
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		return 0;
	}
	
	public void File(PageTable pagetable,String s) 
	{
		try 
		{
            File writeName = new File(s); // 相对路径，如果没有则要建立一个新的txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
            	out.write("页号" + "\t" + "块号"+ "\t" +"驻留位" + "\t" + "访问字段" + "\t" +"修改位"+"\r\n");
            	for(int i = 0; i < pagetable.length; i++) 
            	{
        			Page t = pagetable.p.get(i); //获取vector中的元素
        			out.write(t.PageID + "\t" + t.BlockID + "\t" +t.Dwell + "\t" + t.Reference + "\t" +t.Modi+"\r\n");
        		}
                out.flush(); // 把缓存区内容压入文件
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
    }

	
//	public static void main(String args[]) {
//		Memory m = new Memory();
//		m.blocktable.v.get(1).show();
//		PageTable pagetable = new PageTable();
//		PageTable pagetable1 = new PageTable();
//		m.Set(11, 10, pagetable);
//		m.Set(12, 1, pagetable1);
//		pagetable.ShowPage();
//		pagetable1.ShowPage();
//		m.blocktable.ShowBlock();
//		m.Free(11, 10);
//		PageTable pagetable2 = new PageTable();
//		m.Set(5, 5, pagetable2);
//		m.blocktable.ShowBlock();
//		m.File(pagetable, "Pagetable.txt");
//		m.File(pagetable1, "Pagetable1.txt");
//		m.File(pagetable2, "Pagetable2.txt");
//	}
}
