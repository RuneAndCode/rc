import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class ItemData {
	private int num;//物品編號
	private String name;//名字
	private int price; //價格;
	private int type;//1,2,3,4,5 = 主動直接啟動 主動開關 主動指定對像 主動指定範圍 被動
	//攜帶在身上的正面效果
	private int atk;//攻擊力
	private int speed;//移動速度
	private double HPmax; //生命值上限
	private double MPmax; //魔法值上限
	private double hpRate; //生命值再生率
	private double mpRate; //魔法值再生率
	private int[] attribute = new int[3]; //力,敏,智屬性
	private double manaArmor; //魔法裝甲值
	private double armor; //裝甲值
	private double atkRate; //攻擊速度加成
	//發動
	private int MPCost; //魔法消耗值
	private int CD; //冷卻時間
	private int times;//使用次數(0=無限)
	private int effect;//效果
	private String comment;//註解
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getCD() {
		return CD;
	}
	public void setCD(int cd) {
		CD = cd;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	public int getMPCost() {
		return MPCost;
	}
	public void setMPCost(int cost) {
		MPCost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
				while(!input.matches("price")){
					name = input;
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("type")){
					price = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atk")){
					type = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("speed")){
					atk = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("HPmax")){
					speed = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("MPmax")){
					HPmax = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hpRate")){
					MPmax = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("mpRate")){
					hpRate = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("attribute")){
					mpRate = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("manaArmor")){
					String[] s = input.split(" ");
					attribute[0] = Integer.parseInt(s[0]);
					attribute[1] = Integer.parseInt(s[1]);
					attribute[2] = Integer.parseInt(s[2]);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("armor")){
					manaArmor = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkRate")){
					armor = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("MPCost")){
					atkRate = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("CD")){
					MPCost = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("times")){
					CD = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("effect")){
					times = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("comment")){
					effect = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				comment = input;
				br.close();
			} catch (IOException e) {
				// TODO 自動產生 catch 區塊
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO 自動產生 catch 區塊
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
	public int[] getAttribute() {
		return attribute;
	}
	public void setAttribute(int[] attribute) {
		this.attribute = attribute;
	}
	public double getHPmax() {
		return HPmax;
	}
	public void setHPmax(double pmax) {
		HPmax = pmax;
	}
	public double getHpRate() {
		return hpRate;
	}
	public void setHpRate(double hpRate) {
		this.hpRate = hpRate;
	}
	public double getManaArmor() {
		return manaArmor;
	}
	public void setManaArmor(double manaArmor) {
		this.manaArmor = manaArmor;
	}
	public double getMPmax() {
		return MPmax;
	}
	public void setMPmax(double pmax) {
		MPmax = pmax;
	}
	public double getMpRate() {
		return mpRate;
	}
	public void setMpRate(double mpRate) {
		this.mpRate = mpRate;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
