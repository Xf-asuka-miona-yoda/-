package os;

import java.util.*;

public class BlockTable {
	int length;				//��
	Vector<Block> v = new Vector<Block>(length); //��������vector�������
	
	public BlockTable(int n) 
	{ //���캯��
		length = n;
		for(int i = 0; i < length; i++){
			Block a = new Block();
			a.InitBlock(i);
			v.addElement(a);
		}
	}
	
	public void InsertBlock(Block x,int i) 
	{	//��ָ��λ�ò��������
		v.insertElementAt(x, i);
		length++;
	}
	
	public void ShowBlock() 
	{	//�����������Ϣ
		System.out.println("��������Ϣ���£�");
		for(int i = 0; i < length; i++) 
		{
			Block t = v.get(i);
			System.out.println("��"+i+"�����������Ϊ��"+t.BlockID+" "+t.BlockState+" "+t.OwnerPro);
		}
	}
	
	public Block SearchBlock(int id) 
	{ //��ѯ��Ӧ�������
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
	{ //ɾ����Ӧ�������
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
			System.out.println("δ��ѯ����Ӧ����飡");
		}
		else {
			System.out.println("��ѯ���Ϊ��"+a.BlockID+" "+a.BlockState+" "+a.OwnerPro);
		}
		t.DeleteBlock(25);
		t.ShowBlock();
	}*/
}
