package os;

public class Block {
	int BlockID;		//物理块 ID
	int BlockState;		//物理块分配状态（0 表示空闲， 1 表示占有）
	int OwnerPro;		//分配给的进程 ID
	
	public Block() 
	{	//构造函数
		BlockID = -1;
		BlockState = 0;
		OwnerPro = -1;
	}
	
	public void InitBlock(int id) 
	{ //物理块初始化
		BlockID = id;	
	}
}
