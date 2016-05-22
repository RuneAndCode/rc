import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class BulletDatas {
	public ArrayList<BulletData> list = new ArrayList<BulletData>();
	String path;
	BulletDatas(){
		
	}
	BulletDatas(String path){
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
			BulletData bd = new BulletData();
			bd.readData(file);
			list.add(bd);
			Collections.sort(list,new DataCompare());//用檔案編號(非檔名)排序
		}
	}
	public void createBullet(Unit unit,Unit bullet,BulletData bulletdata,int vx,int vy,
			int bulletNum,int atk){
		bullet.setVx(vx);
		bullet.setVy(vy);
		bullet.setWidth(bulletdata.getWidth());//寬度
		bullet.setHeight(bulletdata.getHeight());//高度
		bullet.setSpeed(bulletdata.getSpeed());//速度
		bullet.setAtkType(bulletdata.getAtkType());//攻擊形態
		//法球bulletdata[4]//攻擊形態
		bullet.setLiveTime(bulletdata.getLifeTime());//生存時間
		bullet.setNum(bulletNum);
		bullet.setImageNum(bulletNum);
		bullet.setDir(1);
		bullet.setId(unit.getId());//所屬玩家
		bullet.setTeam(unit.getTeam());//所屬隊伍
		bullet.setType(5);//屬於子彈
		bullet.setTrace(null);//追蹤目標
		bullet.setHasLiveTime(true);
		bullet.setHarm(atk);//傷害
	}
}
