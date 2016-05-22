
public class Barrack extends Unit{
	public Barrack(){
		
	}
	public Barrack(int x,int y,int team,int num){
		setVisible(true);
		setX(x);
		setY(y);
		inst(x, y);
		setHP(100);
		setHPmax(100);
		setTeam(team);
		setType(3);
		setNum(num);
		setImageNum(num);
	}
}
