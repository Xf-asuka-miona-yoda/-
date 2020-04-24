package os;

import java.util.LinkedList;
import java.util.Queue;

public class PoolQueue {
	public int length;
	JCB p1;//��ͷԪ��
	JCB p2; //��βԪ��
	
	Queue<JCB> BQueue = new LinkedList<JCB>(); 
	public PoolQueue()
	{
		length = 0;
	}
	public void EnQueue(JCB j) {//�����
	
		BQueue.offer(j);
		length++;
	}
	
	public void DeQueue() {//������
		BQueue.poll();
		length--;
	}
	public 	JCB GetTop() {//ȡ��ͷԪ��
		return BQueue.peek();
	}
	public void PrintQueue() {//��������
		for(JCB j : BQueue){
            j.print();
        }
	}
	public int LenQueue() {//ȡ���г�
		return BQueue.size();
	}
}
