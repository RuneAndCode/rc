import java.util.ArrayList;


public class Item extends Obj{
	private int num;//編號
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
	private int[] attribute; //力,敏,智屬性
	private double manaArmor; //魔法裝甲值
	private double armor; //裝甲值
	private double atkRate; //攻擊速度加成
	//發動
	private int MPCost; //魔法消耗值
	private int CDmax; //冷卻時間
	private int CD; //冷卻時間
	private int times;//使用次數(0=無限)
	private int effect;//效果
	private String comment;//註解
	private boolean isTaken;
	private int width = 18;
	private int height = 18;
	public boolean isTaken() {
		return isTaken;
	}
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCDmax() {
		return CDmax;
	}
	public void setCDmax(int dmax) {
		CDmax = dmax;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
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
	
}
