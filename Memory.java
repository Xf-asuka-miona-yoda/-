package os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Memory {
	int Size;		//�ڴ��С
	int BlockNum;	//���������
	int BlockSize;	//������С
	
	BlockTable blocktable;	//������
	int FreeSpace;	//���пռ�
	int UsedSpace;	//���ÿռ�
	File memorywirte; //����ڴ���Ϣ���ļ�
	int[] a = {0,0,0,0,0,0,0,0,0}; //�̶�������ʶλ
	
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
	{//������Դ ����0����ʧ�ܣ�����1�ɹ�
		for(int k = 0;k < size;k++) 
		{ //��ʼ��ҳ��
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
			File memorywirte = new File("�ڴ�.txt"); // ���·�������û����Ҫ����һ���µ�txt�ļ�
	        try (FileWriter writer = new FileWriter(memorywirte,true);
	               BufferedWriter out = new BufferedWriter(writer)
	        		) 
	        {
	        	out.write("\r\n��Ϊĳ���̷�����Դ֮���ڴ���������Ϣ���£�\r\n");
	        	out.write("���"+ "\t" +"ռ��λ" + "\t" + "��������" + "\r\n");
	            for(int i = 0; i < blocktable.length; i++) 
	            {
	        		Block t = blocktable.v.get(i); //��ȡvector�е�Ԫ��
	        		out.write(t.BlockID + "\t" +t.BlockState + "\t" + t.OwnerPro+"\r\n");
	        	}
	            out.flush(); // �ѻ���������ѹ���ļ�
	        }
	    } 
		catch (IOException e) 
		{
			e.printStackTrace();
	    }
		return 1; //����ɹ�
		
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
            File memorywirte = new File("�ڴ�.txt"); // ���·�������û����Ҫ����һ���µ�txt�ļ�
            try (FileWriter writer = new FileWriter(memorywirte,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
            	out.write("\r\n��Ϊĳ���̻�����Դ֮���ڴ���������Ϣ���£�\r\n");
            	out.write("���"+ "\t" +"ռ��λ" + "\t" + "��������" + "\r\n");
            	for(int i1 = 0; i1 < blocktable.length; i1++) 
            	{
        			Block t = blocktable.v.get(i1); //��ȡvector�е�Ԫ��
        			out.write(t.BlockID + "\t" +t.BlockState + "\t" + t.OwnerPro+"\r\n");
        		}
                out.flush(); // �ѻ���������ѹ���ļ�
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
            File writeName = new File(s); // ���·�������û����Ҫ����һ���µ�txt�ļ�
            writeName.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            try (FileWriter writer = new FileWriter(writeName,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
            	out.write("ҳ��" + "\t" + "���"+ "\t" +"פ��λ" + "\t" + "�����ֶ�" + "\t" +"�޸�λ"+"\r\n");
            	for(int i = 0; i < pagetable.length; i++) 
            	{
        			Page t = pagetable.p.get(i); //��ȡvector�е�Ԫ��
        			out.write(t.PageID + "\t" + t.BlockID + "\t" +t.Dwell + "\t" + t.Reference + "\t" +t.Modi+"\r\n");
        		}
                out.flush(); // �ѻ���������ѹ���ļ�
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
