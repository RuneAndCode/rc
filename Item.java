import java.util.ArrayList;


public class Item extends Obj{
	private int num;//�s��
	private String name;//�W�r
	private int price; //����;
	private int type;//1,2,3,4,5 = �D�ʪ����Ұ� �D�ʶ}�� �D�ʫ��w�ﹳ �D�ʫ��w�d�� �Q��
	//��a�b���W�������ĪG
	private int atk;//�����O
	private int speed;//���ʳt��
	private double HPmax; //�ͩR�ȤW��
	private double MPmax; //�]�k�ȤW��
	private double hpRate; //�ͩR�ȦA�Ͳv
	private double mpRate; //�]�k�ȦA�Ͳv
	private int[] attribute; //�O,��,���ݩ�
	private double manaArmor; //�]�k�˥ҭ�
	private double armor; //�˥ҭ�
	private double atkRate; //�����t�ץ[��
	//�o��
	private int MPCost; //�]�k���ӭ�
	private int CDmax; //�N�o�ɶ�
	private int CD; //�N�o�ɶ�
	private int times;//�ϥΦ���(0=�L��)
	private int effect;//�ĪG
	private String comment;//����
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
