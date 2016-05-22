import java.util.Comparator;


public class DataCompare implements Comparator<Object>{

	public int compare(Object arg0, Object arg1) {
		// TODO 自動產生方法 Stub
		if(arg0 instanceof ItemData){
			if(((ItemData) arg0).getNum()>((ItemData) arg1).getNum())
				return 1;
			else
				return -1;
		}else if(arg0 instanceof CardData){
			if(((CardData) arg0).getNum()>((CardData) arg1).getNum())
				return 1;
			else
				return -1;
		}else if(arg0 instanceof BulletData){
			if(((BulletData) arg0).getNum()>((BulletData) arg1).getNum())
				return 1;
			else
				return -1;
		}else if(arg0 instanceof BuildingData){
			if(((BuildingData) arg0).getNum()>((BuildingData) arg1).getNum())
				return 1;
			else
				return -1;
		}
		else if(arg0 instanceof CreepData){
			if(((CreepData) arg0).getNum()>((CreepData) arg1).getNum())
				return 1;
			else
				return -1;
		}
		else if(arg0 instanceof HeroData){
			if(((HeroData) arg0).getNum()>((HeroData) arg1).getNum())
				return 1;
			else
				return -1;
		}else
			return 0;
		
	}
}
