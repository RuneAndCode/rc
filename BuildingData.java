import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class BuildingData {
	private int num;//�s��
	private String name;//�W�r
	private int width; //�e��
	private int height; //����
	private int speed; //�t��
	private int baseAtk; //��¦�����O
	private int bulletNum;//�l�u�s��
	private double hp; //�ͩR��
	private double hpRate; //�C��ͩR�Ȧ^�_��
	private double manaArmor; //�]�k�˥ҭ�
	private double atkRate; //�����t�v
	private double armor; //�˥ҭ�
	private int range; //�����d��
	private int detectRange; //�����d��
	private int exp; //�Q�����κR���g���
	private int award; //�Q�����κR�����y����
	public void printData(){
		System.out.print(name+" ");
		System.out.print(width+" ");
		System.out.print(height+" ");
		System.out.print(speed+" ");
		
		System.out.print(manaArmor+" ");
		System.out.print(armor+" ");
		
		System.out.print(range+" ");
		System.out.print(detectRange+"\n");
	}
	public void readData(File file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				input = br.readLine();
				while(!input.matches("name")){
					num = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("width")){
					name = input;
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("height")){
					width = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("baseAtk")){

					height = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("bulletNum")){

					baseAtk = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hp")){
					
					bulletNum = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hpRate")){
					hp = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkRate")){
					hpRate = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("manaArmor")){
					atkRate = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("armor")){
					manaArmor = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("range")){
					armor = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("detectRange")){
					range = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("exp")){
					detectRange = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("award")){
					exp = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				award = Integer.parseInt(input);
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
	public double getArmor() {
		return armor;
	}
	public void setArmor(double armor) {
		this.armor = armor;
	}
	public double getAtkRate() {
		return atkRate;
	}
	public void setAtkRate(double atkRate) {
		this.atkRate = atkRate;
	}
	public double getHp() {
		return hp;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	public double getHpRate() {
		return hpRate;
	}
	public void setHpRate(double hpRate) {
		this.hpRate = hpRate;
	}
	public int getDetectRange() {
		return detectRange;
	}
	public void setDetectRange(int detectRange) {
		this.detectRange = detectRange;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getManaArmor() {
		return manaArmor;
	}
	public void setManaArmor(double manaArmor) {
		this.manaArmor = manaArmor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getBaseAtk() {
		return baseAtk;
	}
	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getBulletNum() {
		return bulletNum;
	}
	public void setBulletNum(int bulletNum) {
		this.bulletNum = bulletNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
