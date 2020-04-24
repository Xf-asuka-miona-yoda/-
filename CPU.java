package os;

public class CPU {
	public int PC;//��������� ��ǰ���̱��
	public Instruct IR = new Instruct();//ָ��Ĵ���.��ǰָ��
	public int PSW;//״̬�Ĵ���,��ǰָ����
	public int flag ; //��ʶռ��λ�� 1 ��ʾռ�ã� 0 ��ʾ����
	
	CPU(){
		flag=0;
		PC= -1;
		PSW = -1;
		
	}
	public void clear() {// ���CPU ����
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
