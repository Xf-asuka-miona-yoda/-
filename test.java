package os;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Timer;

public class test {
	public static Random r = new Random();
	public static int k = 20; //����������С��20��
	public static int source = 1; //��Ϊ0 ��ͬ���ź���,1Ϊ�����ź���
	public File output;
	
	public static MMU mmu = new MMU();
	public static Memory mem = new Memory();
	public static JCB[] job = new JCB[k];
	public static Process[] pcb = new Process[k];//�������̿ռ�;
	
	public static ReadyQueue readyq = new ReadyQueue();
	public static BlockQueue blockq = new BlockQueue();
	public static FinishQueue finishq = new FinishQueue();
	public static PoolQueue poolq = new PoolQueue();
	
	public static PCBTable pl = new PCBTable();
	public static JCBTable jl = new JCBTable();
	
	//public static int x;
	//public static int y;
	
	public static PageTable[] pagetable = new PageTable[k]; //Ϊÿ�����̵�ҳ���ٿռ�;
	public static CreatePro create = new CreatePro(); //��ҵ��̬�����߳�
	public static schedule sch = new schedule();//��ҵ�����߳�
	
	public static Timer timer1 = new Timer();//��̬����
	public static Timer timer2 = new Timer();//ִ�е���
	public static surface surf;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//������ҵ�ռ�
		surf = new surface();
		File file =new File("�ڴ�.txt");//����ڴ���Ϣ���ļ�
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        File pcbfile =new File("PCB��Ϣ.txt"); //���������Ϣ���ļ�
        try {
            if(!pcbfile.exists()) {
                pcbfile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(pcbfile);
            fileWriter.write("\r\n PCB����Ϣ���£� \r\n");
            fileWriter.write("���̱��"+ "\t" +"��ҵ���" + "\t " + "ָ����Ŀ" + "\t" + "����ʱ��" + " \t"+ "����ʱ��" +"\t" + "ָ����" +"\t"+ "ָ��״̬" +"\t"+ "ָ��ʱ��"+ "\t" +"ָ���ַ" + "\r\n");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
		
        File pagetablefile =new File("ҳ��.txt");//���ҳ����Ϣ���ļ�
        try {
            if(!pagetablefile.exists()) {
            	pagetablefile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(pagetablefile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        File pagetablezfile =new File("ҳ���û�.txt");//���ҳ����Ϣ���ļ�
        try {
            if(!pagetablezfile.exists()) {
            	pagetablezfile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(pagetablezfile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
		try //��out���ض����ļ� ���н��.txt
		{
            File output = new File("���н��.txt"); // ���·�������û����Ҫ����һ���µ�txt�ļ�
            PrintStream ps=new PrintStream(new FileOutputStream(output));
            System.setOut(ps);            
            
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		
		
		
		
		
		for(int i=0 ;i < k ; i++)//Ϊ��ҵ�����̣�ҳ���ٿռ�
		{
			job[i] = new JCB();
			pcb[i] = new Process();
			pagetable[i] = new PageTable(i);
		}
		
		
		
		
		try (FileReader reader = new FileReader("DISK/track  (1)/sector.txt");//��������JCB����Ϣ
	             BufferedReader br = new BufferedReader(reader) // ����һ�����������ļ�����ת�ɼ�����ܶ���������
	        ) {
	            String line;
	            int i = 0;
	            while ((line = br.readLine()) != null) {//�Զ���Ϊ�ָ���ָ����������ַ���
	            	String[] sourceStrArray = line.split(",",5);
	            	job[i].JobID=Integer.parseInt(sourceStrArray[0]);
	            	job[i].InsNum=Integer.parseInt(sourceStrArray[1]);
	            	job[i].InTime=Integer.parseInt(sourceStrArray[2]);
	            	job[i].PV = Integer.parseInt(sourceStrArray[3]);
	            	job[i].UserName=sourceStrArray[4];
	            	i++;
	            }
	            k=i;
	        } 
		catch (IOException e) 
		{
			e.printStackTrace();
	    }
		

       
		
		for (int i = 0 ; i < k ; i++) {//��������ҵ���뵽JCB��
				job[i].Init(i ,job[i].InTime ,job[i].InsNum,r.nextInt(1000)+1,job[i].PV);//��ҵID ������ʱ�䣬ָ����Ŀ��ָ���ַ   
				jl.InsertJCB(job[i], i);
		}
		jl.sort();//��JCB���յ���ʱ������
		for(int i =0 ; i< k ;i++) //������ҵ���
		{
			jl.p.get(i).JobID=i;
		}
		
		
		for(int i = 0;i < jl.length;i++) //�����д��ָ����Ϣ
		{
			for(int j = 0;j < jl.p.get(i).InsNum;j++)
			{
				String Filepath =  "DISK/track  ("+ i + ")/sector (" + j +").txt";
				 File Insfile =new File(Filepath);
		        try {
		            if(!Insfile.exists()) {
		                Insfile.createNewFile();
		            }
		            FileWriter fileWriter =new FileWriter(Insfile);
		            fileWriter.write("��"+ i +"����ҵ�ĵ�"+j+"��ָ��" + "\r\n");
		            fileWriter.write("ָ��״̬"+ "\t" +"����ʱ��" + "\t"+"PV��Ϣ"+"\t"+"�߼���ַ"+"\r\n" );
		            fileWriter.write( jl.p.get(i).InstructArr[j].State +"\t"+ jl.p.get(i).InstructArr[j].time + "\t"+jl.p.get(i).InstructArr[j].PV+"\t"+jl.p.get(i).InstructArr[j].Address);
		            fileWriter.flush();
		            fileWriter.close();
		        }
		        catch (IOException e) {
		            e.printStackTrace();
		        }
			}
           
       } 
		
		
		System.out.println("JCB����Ϣ���£�");
		System.out.println("\t\t" + "��ҵ��� " + "\t" + "����ʱ�� " + "\t"+ "�ڴ��ַ " + "\t" + "�ڴ��С " +"\t"+"ָ������");
		test.jl.ShowJCB();
		timer1.scheduleAtFixedRate(create,0,10);//û��10�����Ƿ����µ���ҵ�������ɽ��̣�
		timer2.scheduleAtFixedRate(sch, 140, 50);//50ms һ��ʱ��Ƭִ�н��̵���
		
		
	}

}
