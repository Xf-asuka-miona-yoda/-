package os;

import java.util.Random;

public class Page {
	int PageID;     //页号从0开始编号
	int BlockID; 	//内存块号， 与页面大小相等， 从 0 开始编号
	int Dwell; 		//驻留标志位， 0 表示不在内存， 1 表示在内存
	int Reference;	//访问字段
	int Modi;		//修改位， 0 表示未被修改， 1 表示被修改
	
	Random r=new Random();
	
	public Page() 
	{
		PageID = -1;
		BlockID = r.nextInt(64);;
		Dwell = -1;
		Reference = 0;
		Modi = 0;
	} 
	
	public void InitPage(int id,int dwell) 
	{
		PageID = id;
		Dwell = dwell;
	}
}
