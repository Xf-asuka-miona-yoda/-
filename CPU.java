package os;

public class CPU {
	public int PC;//程序计数器 当前进程编号
	public Instruct IR = new Instruct();//指令寄存器.当前指令
	public int PSW;//状态寄存器,当前指令编号
	public int flag ; //标识占用位， 1 表示占用， 0 表示空闲
	
	CPU(){
		flag=0;
		PC= -1;
		PSW = -1;
		
	}
	public void clear() {// 清空CPU 缓冲
		flag=0;
		PC= -1;
		PSW = -1;
		
	}
	public void protect(Process p) {
		p.temp.PC = this.PC;
		p.temp.IR = this.IR;
		p.temp.PSW = this.PSW;
		p.PSW = this.PSW;
	}
	
	public void recover(Process p) {
		this.PC = p.temp.PC;
		this.IR = p.temp.IR;
		this.PSW = p.temp.PSW;
		
	}
	
}
