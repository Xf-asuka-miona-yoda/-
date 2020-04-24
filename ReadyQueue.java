package os;

import java.util.LinkedList;
import java.util.Queue;

public class ReadyQueue {
	Process p1;//��ͷԪ��
	Process p2; //��βԪ��
	public int length;
	
	Queue<Process> BQueue = new LinkedList<Process>(); 
	public ReadyQueue() {
		length = 0;
	}
	
	public synchronized void EnQueue(Process p) {//�����
	
		BQueue.add(p);
		p.ProState = 0;
		length ++;
	}
	
	public synchronized void DeQueue() {//������
		BQueue.poll();
		length--;
	}
	public 	synchronized Process GetTop() {//ȡ��ͷԪ��
		//System.out.print(BQueue.peek().InstructArr[3].State+"\n");
		return BQueue.peek();
	}
	public synchronized void PrintQueue() {//��������
		for(Process q : BQueue){
            q.print();
        }
	}
	public synchronized int LenQueue() {//ȡ���г�
		return BQueue.size();
	}
	
}
