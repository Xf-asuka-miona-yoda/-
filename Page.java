package os;

import java.util.Random;

public class Page {
	int PageID;     //ҳ�Ŵ�0��ʼ���
	int BlockID; 	//�ڴ��ţ� ��ҳ���С��ȣ� �� 0 ��ʼ���
	int Dwell; 		//פ����־λ�� 0 ��ʾ�����ڴ棬 1 ��ʾ���ڴ�
	int Reference;	//�����ֶ�
	int Modi;		//�޸�λ�� 0 ��ʾδ���޸ģ� 1 ��ʾ���޸�
	
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
