import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class BuildingDatas {
	public ArrayList<BuildingData> list = new ArrayList<BuildingData>();
	String path;
	BuildingDatas(){
		
	}
	BuildingDatas(String path){
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
			BuildingData bd = new BuildingData();
			bd.readData(file);
			list.add(bd);
			Collections.sort(list,new DataCompare());//用檔案編號(非檔名)排序
		}
		//System.out.print(""+list.size());
	}
	
	public void CreateBuilding(GameData gameData,Unit unit,int num,int x,int y,int dir,int id,
			int team){
		unit.setNum(num);
		unit.setImageNum(num);
		BuildingData bd = list.get(unit.getNum()-1);
		//System.out.println(unit.getNum()+""+bd.getBaseAtk()+"");
		unit.setType(3);
		unit.setAtkType(1);
		unit.setArmorType(4);
		
		unit.setDir(dir);
		unit.setId(id);
		unit.setTeam(team);
		unit.setX(x);
		unit.setY(y);
		unit.inst(x, y);
		
		unit.setName(bd.getName());
		unit.setWidth(bd.getWidth());
		unit.setHeight(bd.getHeight());
		unit.setSpeed(bd.getSpeed());
		unit.setBaseAtk(bd.getBaseAtk());
		unit.setAtk(bd.getBaseAtk());
		unit.setBulletNum(bd.getBulletNum());
		unit.setHPmax(bd.getHp());
		unit.setHP(bd.getHp());
		unit.setHpRate(bd.getHpRate());
		unit.setAtkIntMax(1/bd.getAtkRate());
		unit.setManaArmor(bd.getManaArmor());
		unit.setArmor(bd.getArmor());
		unit.setRange(bd.getRange());
		unit.setDetectRange(bd.getDetectRange());
		unit.setExp(bd.getExp());
		unit.setAward(bd.getAward());
	}
}
