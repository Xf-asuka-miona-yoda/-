package os;

import java.util.LinkedList;
import java.util.Queue;

public class ReadyQueue {
	Process p1;//队头元素
	Process p2; //队尾元素
	public int length;
	
	Queue<Process> BQueue = new LinkedList<Process>(); 
	public ReadyQueue() {
		length = 0;
	}
	
	public synchronized void EnQueue(Process p) {//入队列
	
		BQueue.add(p);
		p.ProState = 0;
		length ++;
	}
	
	public synchronized void DeQueue() {//出队列
		BQueue.poll();
		length--;
	}
	public 	synchronized Process GetTop() {//取队头元素
		//System.out.print(BQueue.peek().InstructArr[3].State+"\n");
		return BQueue.peek();
	}
	public synchronized void PrintQueue() {//遍历队列
		for(Process q : BQueue){
            q.print();
        }
	}
	public synchronized int LenQueue() {//取队列长
		return BQueue.size();
	}
	
}
