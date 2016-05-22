import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class GameData {
	String path;
	private int heroHpBase = 150; //�^����¦�ͩR��
	private int atkDice = 6; //������l
	private int atkPerMainAttribute = 1; //�C�I�D�n�ݩʴ��ɦh�֧����O
	private int hpPerStr = 19; //�C�I�O�q���ɥͩR��
	private double hpRatePerStr = 0.05; //�C�I�O�q���ɥͩR�Ȧ^�_��
	private int incArmorNeedAti = 3; //�C�X�I�ӱ����ɤ@�I���m
	private double atkRatePerAti = 0.01; //�C�I�ӱ����ɧ����t��
	private int mpPerInt = 13; //�C�I���O�����]�O��
	private double mpRatePerInt = 0.05; //�C�I���O�����]�k�^�_��

	private double[][] armorList = new double[][]{
			{1   ,1   ,1.5 ,1   ,0.7 ,1  ,0.05},
			{1.5 ,2   ,0.75,1   ,0.35,0.5,0.05},
			{1.5 ,1   ,0.5 ,1   ,1.5 ,0.5,0.05},
			{1   ,1   ,0.5 ,2   ,0.35,0.5,0.05},
			{1   ,1   ,1   ,1   ,1   ,1  ,1   },
			{1   ,1   ,1   ,1   ,1   ,0.7,0.05},
			{1   ,1   ,1   ,1   ,0.5 ,1  ,0.05}
	};
//	�ˮ`��
	/*
	 * -----�L�˥� ���� ����  ����   �j��  �^��  ����
		���q 100% 100% 150% 100%  70%  100% 5%  
		��� 150% 200% 75%  100%  35%  50%  5% 
		�� 150% 100% 50%  100%  150% 50%  5% 
		�]�k 100% 100% 75%  200%  35%  50%  5% 
		�V�P 100% 100% 100% 100%  100% 100% 100% 
		�ޯ� 100% 100% 100% 100%  100% 70%  5% 
		�^�� 100% 100% 100% 100%  50%  100% 5%  
	 */
	public GameData(){
		
	}
	public GameData(String path){
		this.path = path;

	}
	public void readData(){
		File file = new File(path);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				input = br.readLine();
				while(!input.matches("atkDice")){
					heroHpBase = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkPerMainAttribute")){
					atkDice = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hpPerStr")){
					atkPerMainAttribute = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hpRatePerStr")){
					hpPerStr = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("incArmorNeedAti")){
					hpRatePerStr = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkRatePerAti")){
					incArmorNeedAti = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("mpPerInt")){
					atkRatePerAti = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("mpRatePerInt")){
					mpPerInt = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				mpRatePerInt = Double.parseDouble(input);
				br.close();
			} catch (IOException e) {
				// TODO �۰ʲ��� catch �϶�
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO �۰ʲ��� catch �϶�
			e.printStackTrace();
		}
	}

	public double[][] getArmorList() {
		return armorList;
	}

	public void setArmorList(double[][] armorList) {
		this.armorList = armorList;
	}

	public int getAtkDice() {
		return atkDice;
	}

	public void setAtkDice(int atkDice) {
		this.atkDice = atkDice;
	}

	public int getAtkPerMainAttribute() {
		return atkPerMainAttribute;
	}

	public void setAtkPerMainAttribute(int atkPerMainAttribute) {
		this.atkPerMainAttribute = atkPerMainAttribute;
	}

	public double getAtkRatePerAti() {
		return atkRatePerAti;
	}

	public void setAtkRatePerAti(double atkRatePerAti) {
		this.atkRatePerAti = atkRatePerAti;
	}

	public int getHeroHpBase() {
		return heroHpBase;
	}

	public void setHeroHpBase(int heroHpBase) {
		this.heroHpBase = heroHpBase;
	}

	public int getHpPerStr() {
		return hpPerStr;
	}

	public void setHpPerStr(int hpPerStr) {
		this.hpPerStr = hpPerStr;
	}

	public double getHpRatePerStr() {
		return hpRatePerStr;
	}

	public void setHpRatePerStr(double hpRatePerStr) {
		this.hpRatePerStr = hpRatePerStr;
	}

	public int getIncArmorNeedAti() {
		return incArmorNeedAti;
	}

	public void setIncArmorNeedAti(int incArmorNeedAti) {
		this.incArmorNeedAti = incArmorNeedAti;
	}

	public int getMpPerInt() {
		return mpPerInt;
	}

	public void setMpPerInt(int mpPerInt) {
		this.mpPerInt = mpPerInt;
	}

	public double getMpRatePerInt() {
		return mpRatePerInt;
	}

	public void setMpRatePerInt(double mpRatePerInt) {
		this.mpRatePerInt = mpRatePerInt;
	}
}
