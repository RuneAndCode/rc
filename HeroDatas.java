import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class HeroDatas {
	public ArrayList<HeroData> list = new ArrayList<HeroData>();
	String path;
	HeroDatas(){
		
	}
	HeroDatas(String path){
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
			HeroData hd = new HeroData();
			hd.readData(file);
			list.add(hd);
			Collections.sort(list,new DataCompare());//用檔案編號(非檔名)排序
		}
	}
	
	public void CreateHero(GameData gameData,Unit unit,int num,int x,int y,int dir,int id,
			int team){
		unit.setNum(num);
		unit.setImageNum(num);
		HeroData hd = new HeroData();
		for(int i = 0;i < list.size();i++)
		{
			if(list.get(i).getNum()==unit.getNum()){
				hd = list.get(i);
			}
		}
		//HeroData hd = list.get(unit.getNum()-1);
		unit.setType(1);
		unit.setArmorType(5);
		
		unit.setDir(dir);
		unit.setId(id);
		unit.setTeam(team);
		unit.setX(x);
		unit.setY(y);
		unit.inst(x, y);
		
		unit.setName(hd.getName());
		unit.setWidth(hd.getWidth());
		unit.setHeight(hd.getHeight());
		unit.setSpeed(hd.getSpeed());
		unit.setBaseAtk(hd.getBaseAtk());
		unit.setMainAttribute(hd.getMainAttribute());
		unit.setBaseAttribute(hd.getAttribute());
		unit.setAttributeInc(hd.getAttributeInc());
		unit.setManaArmor(hd.getManaArmor());
		unit.setArmor(hd.getArmor());
		unit.setSkill(hd.getSkill());
		unit.setRange(hd.getRange());
		unit.setDetectRange(hd.getDetectRange());
		unit.setBulletNum(hd.getBulletNum());
		
		unit.setHPmax(gameData.getHeroHpBase()+unit.getBaseAttribute()[0]*gameData.getHpPerStr());
		unit.setHP(unit.getHPmax());
		unit.setMPmax(unit.getBaseAttribute()[2]*gameData.getMpPerInt());
		unit.setMP(unit.getMPmax());
		unit.setHpRate(unit.getBaseAttribute()[0]*gameData.getHpRatePerStr());
		unit.setMpRate(unit.getBaseAttribute()[2]*gameData.getMpRatePerInt());
		unit.setAtk(unit.getBaseAtk()+unit.getBaseAttribute()[unit.getMainAttribute()-1]*gameData.getAtkPerMainAttribute());
		unit.setArmor(unit.getArmor()+(int)(unit.getBaseAttribute()[1]/gameData.getIncArmorNeedAti()));
	}
}
