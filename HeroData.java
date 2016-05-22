import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class HeroData {
	//�^��
	private int num;//�s��
	private String name;//�W�r
	//���S��
	private int width; //�e��
	private int height; //����
	private int speed; //�t��
	private int baseAtk; //��¦�����O
	private int bulletNum; //�l�u�����s��
	private int mainAttribute; //�ݩ�1,2,3�O�Ӵ�
	private int[] attribute = new int[3]; //�O,��,���_�l��
	private double[] AttributeInc = new double[3]; //�O�q,�ӱ�,���z������
	private double manaArmor; //�]�k�˥ҭ�
	private double armor; //�˥ҭ�
	private int[] skill = new int[4]; //�ޯ�1-4�s��
	private int range; //�����d��
	private int detectRange; //�����d��
	public void printData(){
		System.out.print(name+" ");
		System.out.print(width+" ");
		System.out.print(height+" ");
		System.out.print(speed+" ");
		System.out.print(mainAttribute+" ");
		System.out.print(attribute[0]+" "+attribute[1]+" "+attribute[2]+" ");
		System.out.print(AttributeInc[0]+" "+AttributeInc[1]+" "+AttributeInc[2]+" ");
		System.out.print(manaArmor+" ");
		System.out.print(armor+" ");
		System.out.print(skill[0]+" "+skill[1]+" "+skill[2]+" "+skill[3]+" ");
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
				while(!input.matches("speed")){

					height = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("baseAtk")){
					
					speed = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("bulletNum")){
					
					baseAtk = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("mainAttribute")){
					
					bulletNum = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("attribute")){
					
					mainAttribute = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("attributeInc")){
					
					String[] s = input.split(" ");
					System.out.println(s[0]);
					attribute[0] = Integer.parseInt(s[0]);
					attribute[1] = Integer.parseInt(s[1]);
					attribute[2] = Integer.parseInt(s[2]);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("manaArmor")){
					
					String[] s = input.split(" ");
					System.out.println(s[0]);
					AttributeInc[0] = Double.parseDouble(s[0]);
					AttributeInc[1] = Double.parseDouble(s[1]);
					AttributeInc[2] = Double.parseDouble(s[2]);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("armor")){
					manaArmor = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("skill")){
					armor = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("range")){
					String[] s = input.split(" ");
					skill[0] = Integer.parseInt(s[0]);
					skill[1] = Integer.parseInt(s[1]);
					skill[2] = Integer.parseInt(s[2]);
					skill[3] = Integer.parseInt(s[3]);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("detectRange")){
					range = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				detectRange = Integer.parseInt(input);
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
	public double[] getAttributeInc() {
		return AttributeInc;
	}
	public void setAttributeInc(double[] attributeInc) {
		AttributeInc = attributeInc;
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
	public int[] getSkill() {
		return skill;
	}
	public void setSkill(int[] skill) {
		this.skill = skill;
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
	public int[] getAttribute() {
		return attribute;
	}
	public void setAttribute(int[] attribute) {
		this.attribute = attribute;
	}
	public int getMainAttribute() {
		return mainAttribute;
	}
	public void setMainAttribute(int mainAttribute) {
		this.mainAttribute = mainAttribute;
	}
	public int getBaseAtk() {
		return baseAtk;
	}
	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
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
