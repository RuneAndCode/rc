import java.awt.Image;


public class Card extends Obj{
	private int num;//編號
	private String name;//名字
	private String compareName = "";
	private int level;//角色可以使用該卡的等級
	private int type; //類型1,2,3,4=召喚,魔法,鑲嵌,陷阱
	private int cost; //消耗能量
	private int effect;//第幾號效果
	private int typegroup;//類型1,2,3,4=召喚,魔法,鑲嵌,陷阱
	private String  comment;//注解
	private Image image;//圖案
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Card(){
		
	}
	public Card(int num){
		this.num = num;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTypegroup() {
		return typegroup;
	}
	public void setTypegroup(int typegroup) {
		this.typegroup = typegroup;
	}
	public String getCompareName() {
		return compareName;
	}
	public void setCompareName(String compareName) {
		this.compareName = compareName;
	}
}
