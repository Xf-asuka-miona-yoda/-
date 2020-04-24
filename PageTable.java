package os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PageTable {
	int length;				//��
	Vector<Page> p = new Vector<Page>(length); //ҳ������ҳ����д��
	int ProID;
	
	
	
	public PageTable(int n) 
	{
		ProID = n;
		length = 0;
	}
	
	public void InsertPage(Page a,int n) 
	{ //��ָ���ط�����ҳ����
		p.insertElementAt(a, n);			//vector��Ĳ��뺯��
		length++;
	}
	
	public void ShowPage() 
	{	//���ҳ����Ϣ
		System.out.println("ҳ����Ϣ���£�");
		for(int i = 0; i < length; i++) 
		{
			Page t = p.get(i); //��ȡvector�е�Ԫ��
			System.out.println("��"+i+"��ҳ��������Ϊ��"+ t.PageID + " " + t.BlockID + " " +t.Dwell + " " + t.Reference + " " +t.Modi);
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
	{ //ɾ����Ӧ��ҳ��
		for(int i = 0; i < length; i++) 
		{
			Page t = p.get(i);
			if(t.PageID  == id) 
			{
				p.removeElementAt(i);      //vector���ɾ������
				length--;
			}	
		}
	}
	
	public void File(int k,String s)
	{
		try 
		{
            File writeName = new File(s); // ���·�������û����Ҫ����һ���µ�txt�ļ�
            writeName.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            try (FileWriter writer = new FileWriter(writeName,true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) 
            {
            	out.write("����"+k+"��Ҫд�ص�ҳ����д��\r\n");
            	out.write("ҳ��" + "\t" + "���"+ "\t" +"פ��λ" + "\t" + "�����ֶ�" + "\t" +"�޸�λ"+"\r\n");
            	for(int i = 0; i < length; i++) 
            	{
        			Page t = p.get(i); //��ȡvector�е�Ԫ��
        			if(t.Modi == 1) {
        				out.write(t.PageID + "\t" + t.BlockID + "\t" +t.Dwell + "\t" + t.Reference + "\t" +t.Modi+"\r\n");
        			}
        			
        		}
                out.flush(); // �ѻ���������ѹ���ļ�
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
			System.out.println("δ��ѯ����Ӧҳ���");
		}
		else {
			System.out.println("��ѯ���Ϊ��"+a.PageID + " " + a.BlockID + " " +a.Dwell + " " + a.Reference + " " +a.Modi);
		}
		t.DeletePage(25);
		t.ShowPage();
	}*/
	
}
