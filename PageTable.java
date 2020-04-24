package os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PageTable {
	int length;				//表长
	Vector<Page> p = new Vector<Page>(length); //页表项用页表进行存放
	int ProID;
	
	
	
	public PageTable(int n) 
	{
		ProID = n;
		length = 0;
	}
	
	public void InsertPage(Page a,int n) 
	{ //在指定地方插入页表项
		p.insertElementAt(a, n);			//vector类的插入函数
		length++;
	}
	
	public void ShowPage() 
	{	//输出页表信息
		System.out.println("页表信息如下：");
		for(int i = 0; i < length; i++) 
		{
			Page t = p.get(i); //获取vector中的元素
			System.out.println("第"+i+"个页表项内容为："+ t.PageID + " " + t.BlockID + " " +t.Dwell + " " + t.Reference + " " +t.Modi);
		}
	}
	
	public Page SearchPage(int id) 
	{
		Page a = new Page();
		for(int i = 0; i < length; i++) 
		{
			Page t = p.get(i);
			if(t.PageID  == id) 
			{
				a = t;
			}
		}
		return a;
	}
	
	public void DeletePage(int id) 
	{ //删除对应的页面
		for(int i = 0; i < length; i++) 
		{
			Page t = p.get(i);
			if(t.PageID  == id) 
			{
				p.removeElementAt(i);      //vector类的删除函数
				length--;
			}	
		}
	}
	
	public void File(int k,String s)
	{
		try 
		{
            File writeName = new File(s); // 相对路径，如果没有则要建立一个新的txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
            	out.write("进程"+k+"需要写回的页面已写回\r\n");
            	out.write("页号" + "\t" + "块号"+ "\t" +"驻留位" + "\t" + "访问字段" + "\t" +"修改位"+"\r\n");
            	for(int i = 0; i < length; i++) 
            	{
        			Page t = p.get(i); //获取vector中的元素
        			if(t.Modi == 1) {
        				out.write(t.PageID + "\t" + t.BlockID + "\t" +t.Dwell + "\t" + t.Reference + "\t" +t.Modi+"\r\n");
        			}
        			
        		}
                out.flush(); // 把缓存区内容压入文件
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	
	/*public static void main(String args[]) {
		PageTable t = new PageTable();
		for(int i = 0; i < 5; i++) {
			Page a = new Page();
			a.InitPage(i, 1);
			t.InsertPage(a, i);
		}
		t.ShowPage();
		Page x = new Page();
		x.BlockID = 1;
		int n;
		n = 4;
		x.InitPage(25,0);
		t.InsertPage(x, n);
		t.ShowPage();
		Page a = new Page();
		a = t.SearchPage(25);
		if(a.PageID == -1) {
			System.out.println("未查询到对应页表项！");
		}
		else {
			System.out.println("查询结果为："+a.PageID + " " + a.BlockID + " " +a.Dwell + " " + a.Reference + " " +a.Modi);
		}
		t.DeletePage(25);
		t.ShowPage();
	}*/
	
}
