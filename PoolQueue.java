package os;

import java.util.LinkedList;
import java.util.Queue;

public class PoolQueue {
	public int length;
	JCB p1;//队头元素
	JCB p2; //队尾元素
	
	Queue<JCB> BQueue = new LinkedList<JCB>(); 
	public PoolQueue()
	{
		length = 0;
	}
	public void EnQueue(JCB j) {//入队列
	
		BQueue.offer(j);
		length++;
	}
	
	public void DeQueue() {//出队列
		BQueue.poll();
		length--;
	}
	public 	JCB GetTop() {//取队头元素
		return BQueue.peek();
	}
	public void PrintQueue() {//遍历队列
		for(JCB j : BQueue){
            j.print();
        }
	}
	public int LenQueue() {//取队列长
		return BQueue.size();
	}
}
