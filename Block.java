package os;

public class Block {
	int BlockID;		//����� ID
	int BlockState;		//��������״̬��0 ��ʾ���У� 1 ��ʾռ�У�
	int OwnerPro;		//������Ľ��� ID
	
	public Block() 
	{	//���캯��
		BlockID = -1;
		BlockState = 0;
		OwnerPro = -1;
	}
	
	public void InitBlock(int id) 
	{ //������ʼ��
		BlockID = id;	
	}
}
