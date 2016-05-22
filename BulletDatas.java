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
			Collections.sort(list,new DataCompare());//���ɮ׽s��(�D�ɦW)�Ƨ�
		}
	}
	public void createBullet(Unit unit,Unit bullet,BulletData bulletdata,int vx,int vy,
			int bulletNum,int atk){
		bullet.setVx(vx);
		bullet.setVy(vy);
		bullet.setWidth(bulletdata.getWidth());//�e��
		bullet.setHeight(bulletdata.getHeight());//����
		bullet.setSpeed(bulletdata.getSpeed());//�t��
		bullet.setAtkType(bulletdata.getAtkType());//�����κA
		//�k�ybulletdata[4]//�����κA
		bullet.setLiveTime(bulletdata.getLifeTime());//�ͦs�ɶ�
		bullet.setNum(bulletNum);
		bullet.setImageNum(bulletNum);
		bullet.setDir(1);
		bullet.setId(unit.getId());//���ݪ��a
		bullet.setTeam(unit.getTeam());//���ݶ���
		bullet.setType(5);//�ݩ�l�u
		bullet.setTrace(null);//�l�ܥؼ�
		bullet.setHasLiveTime(true);
		bullet.setHarm(atk);//�ˮ`
	}
}
