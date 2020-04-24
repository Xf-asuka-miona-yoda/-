package os;

import java.util.LinkedList;
import java.util.Queue;

public class BlockQueue{
	
	Process p1;//��ͷԪ��
	Process p2; //��βԪ��
	public int length = 0;
	Queue<Process> BQueue = new LinkedList<Process>(); 
	public void EnQueue(Process p) {//�����
	
		BQueue.offer(p);
		length++;
	}
	
	public void DeQueue() {//������
		BQueue.poll();
		length--;
	}
	public 	Process GetTop() {//ȡ��ͷԪ��
		return BQueue.peek();
	}
	public void PrintQueue() {//��������
		for(Process q : BQueue){
            q.print();
        }
	}
	public int LenQueue() {//ȡ���г�
		return BQueue.size();
	}
}
