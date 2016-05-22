
public class Trap extends Obj{
	int num = 0;//³´¨Àªº½s¸¹
	int team = 0;
	int width = 50;
	int height = 50;
	int id = 0;
	Trap(){
		
	}
	Trap(int x,int y,int num,int team,int id){
		setX(x);
		setY(y);
		this.num = num;
		this.team = team;
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
