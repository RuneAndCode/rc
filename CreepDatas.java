import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class CreepDatas {
	public ArrayList<CreepData> list = new ArrayList<CreepData>();
	String path;
	CreepDatas(){
		
	}
	CreepDatas(String path){
		this.path = path;
	}
	public void printList(){
		File dir = new File(path);
		String[] s = dir.list();
		for(int i = 0;i < s.length;i++){
			System.out.println(s[i]);
		}
	}
	
	public void printData(){
		for(int i = 0;i < list.size();i++){
			list.get(i).printData();
		}
	}
	
	public void readData(){
		File dir = new File(path);
		String[] s = dir.list();
		for(int i = 0;i < s.length;i++){
			File file = new File(path+s[i]);
			CreepData cd = new CreepData();
			cd.readData(file);
			list.add(cd);
			Collections.sort(list,new DataCompare());//用檔案編號(非檔名)排序
		}
	}
	
	public void CreateCreep(GameData gameData,Unit unit,int num,int x,int y,int dir,int id,
			int team){
		unit.setNum(num);
		unit.setImageNum(num);
		CreepData cd = list.get(unit.getNum()-1);
		unit.setType(2);
		unit.setArmorType(0);
		
		unit.setDir(dir);
		unit.setId(id);
		unit.setTeam(team);
		unit.setX(x);
		unit.setY(y);
		unit.inst(x, y);
		
		unit.setName(cd.getName());
		unit.setWidth(cd.getWidth());
		unit.setHeight(cd.getHeight());
		unit.setSpeed(cd.getSpeed());
		unit.setAtk(cd.getBaseAtk());
		
		unit.setBulletNum(cd.getBulletNum());
		unit.setHPmax(cd.getHp());
		unit.setHP(cd.getHp());
		unit.setMPmax(cd.getMp());
		unit.setMP(cd.getMp());
		unit.setHpRate(cd.getHpRate());
		unit.setMpRate(cd.getMpRate());
		unit.setAtkIntMax(1/cd.getAtkRate());
		unit.setManaArmor(cd.getManaArmor());
		unit.setArmor(cd.getArmor());
		unit.setSkill(cd.getSkill());
		unit.setRange(cd.getRange());
		unit.setDetectRange(cd.getDetectRange());
		unit.setExp(cd.getExp());
		unit.setAward(cd.getAward());
	}
}
