package os;

import java.util.*;

public class BlockTable {
	int length;				//表长
	Vector<Block> v = new Vector<Block>(length); //物理块表用vector容器存放
	
	public BlockTable(int n) 
	{ //构造函数
		length = n;
		for(int i = 0; i < length; i++){
			Block a = new Block();
			a.InitBlock(i);
			v.addElement(a);
		}
	}
	
	public void InsertBlock(Block x,int i) 
	{	//在指定位置插入物理块
		v.insertElementAt(x, i);
		length++;
	}
	
	public void ShowBlock() 
	{	//输出物理块表信息
		System.out.println("物理块表信息如下：");
		for(int i = 0; i < length; i++) 
		{
			Block t = v.get(i);
			System.out.println("第"+i+"块物理块内容为："+t.BlockID+" "+t.BlockState+" "+t.OwnerPro);
		}
	}
	
	public Block SearchBlock(int id) 
	{ //查询对应的物理块
		Block a = new Block();
		for(int i = 0; i < length; i++) 
		{
			Block t = v.get(i);
			if(t.BlockID  == id) 
			{
				a = t;
			}
		}
		return a;
	}  
	
	public void DeleteBlock(int id) 
	{ //删除对应的物理块
		for(int i = 0; i < length; i++) 
		{
			Block t = v.get(i);
			if(t.BlockID  == id) 
			{
				v.removeElementAt(i);
				length--;
			}	
		}
	}
	
	/*public static void main(String args[]) {
		BlockTable t = new BlockTable(10);
		t.ShowBlock();
		t.v.get(0).BlockState = 10;
		Block x = new Block();
		x.BlockState = 1;
		int n;
		n = 10;
		x.InitBlock(25);
		t.InsertBlock(x, n);
		t.ShowBlock();
		Block a = new Block();
		a = t.SearchBlock(15);
		if(a.BlockID == -1) {
			System.out.println("未查询到对应物理块！");
		}
		else {
			System.out.println("查询结果为："+a.BlockID+" "+a.BlockState+" "+a.OwnerPro);
		}
		t.DeleteBlock(25);
		t.ShowBlock();
	}*/
}
