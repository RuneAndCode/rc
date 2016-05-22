import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class ItemDatas {
	public ArrayList<ItemData> list = new ArrayList<ItemData>();
	String path;
	ItemDatas(){
		
	}
	ItemDatas(String path){
		this.path = path;
	}
	
	public void readData(){
		File dir = new File(path);
		String[] s = dir.list();
		for(int i = 0;i < s.length;i++){
			File file = new File(path+s[i]);
			ItemData id = new ItemData();
			id.readData(file);
			list.add(id);
			Collections.sort(list,new DataCompare());//用檔案編號(非檔名)排序
		}
	}
	
	public void CreateItem(Item itemd,int id,int num){
		ItemData ilist = list.get(num-1);
		itemd.setAtk(ilist.getAtk());
		itemd.setSpeed(ilist.getSpeed());
		itemd.setHPmax(ilist.getHPmax());
		itemd.setMPmax(ilist.getMPmax());
		itemd.setHpRate(ilist.getHpRate());
		itemd.setMpRate(ilist.getMpRate());
		itemd.setAttribute(ilist.getAttribute());
		itemd.setManaArmor(ilist.getManaArmor());
		itemd.setArmor(ilist.getArmor());
		itemd.setAtkRate(ilist.getAtkRate());
		itemd.setCDmax(ilist.getCD());
		itemd.setComment(ilist.getComment());
		itemd.setEffect(ilist.getEffect());
		itemd.setMPCost(ilist.getMPCost());
		itemd.setName(ilist.getName());
		itemd.setPrice(ilist.getPrice());
		itemd.setTimes(ilist.getTimes());
		itemd.setType(ilist.getType());
		itemd.setNum(num);
	}
	
	public void activate(Unit unit,int num){
		if(unit.item[num].getNum()!=0){//有道具
			Item item = unit.item[num];
			//CD轉好就可以發動
			if(item.getCDmax()>0&&item.getCD()<=0||item.getCDmax()==0){
				
				switch(item.getEffect()){
				case 1: skill1(unit);
				break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				}
				item.setCD(item.getCDmax());
				if(item.getTimes()>0){//有使用次數
					//
					System.out.println("消耗一次次數");
					item.setTimes(item.getTimes()-1);
					if(item.getTimes()<=0){
						unit.item[num].setNum(0);
					}
				}
			}
		}
	}
	public void skill1(Unit unit){
		remp(unit,40);
	}
	public void remp(Unit unit,int num){
		if(unit.getMP()+num>unit.getMPmax()){
			unit.setMP(unit.getMPmax());
		}else{
			unit.setMP(unit.getMP()+num);
		}
	}
	public Item copy(Item item){
		Item ci = new Item();
		ci.setAtk(item.getAtk());
		ci.setCDmax(item.getCDmax());
		ci.setComment(item.getComment());
		ci.setEffect(item.getEffect());
		ci.setMPCost(item.getMPCost());
		ci.setName(item.getName());
		ci.setPrice(item.getPrice());
		ci.setTimes(item.getTimes());
		ci.setType(item.getType());
		ci.setNum(item.getNum());
		ci.setCD(item.getCD());
		return ci;
	}
}
