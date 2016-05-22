import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class GameData {
	String path;
	private int heroHpBase = 150; //英雄基礎生命值
	private int atkDice = 6; //攻擊骰子
	private int atkPerMainAttribute = 1; //每點主要屬性提升多少攻擊力
	private int hpPerStr = 19; //每點力量提升生命值
	private double hpRatePerStr = 0.05; //每點力量提升生命值回復值
	private int incArmorNeedAti = 3; //每幾點敏捷提升一點防禦
	private double atkRatePerAti = 0.01; //每點敏捷提升攻擊速度
	private int mpPerInt = 13; //每點智力提升魔力值
	private double mpRatePerInt = 0.05; //每點智力提升魔法回復值

	private double[][] armorList = new double[][]{
			{1   ,1   ,1.5 ,1   ,0.7 ,1  ,0.05},
			{1.5 ,2   ,0.75,1   ,0.35,0.5,0.05},
			{1.5 ,1   ,0.5 ,1   ,1.5 ,0.5,0.05},
			{1   ,1   ,0.5 ,2   ,0.35,0.5,0.05},
			{1   ,1   ,1   ,1   ,1   ,1  ,1   },
			{1   ,1   ,1   ,1   ,1   ,0.7,0.05},
			{1   ,1   ,1   ,1   ,0.5 ,1  ,0.05}
	};
//	傷害表
	/*
	 * -----無裝甲 輕型 中型  重型   強化  英雄  神性
		普通 100% 100% 150% 100%  70%  100% 5%  
		穿刺 150% 200% 75%  100%  35%  50%  5% 
		攻城 150% 100% 50%  100%  150% 50%  5% 
		魔法 100% 100% 75%  200%  35%  50%  5% 
		混沌 100% 100% 100% 100%  100% 100% 100% 
		技能 100% 100% 100% 100%  100% 70%  5% 
		英雄 100% 100% 100% 100%  50%  100% 5%  
	 */
	public GameData(){
		
	}
	public GameData(String path){
		this.path = path;

	}
	public void readData(){
		File file = new File(path);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				input = br.readLine();
				while(!input.matches("atkDice")){
					heroHpBase = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkPerMainAttribute")){
					atkDice = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hpPerStr")){
					atkPerMainAttribute = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("hpRatePerStr")){
					hpPerStr = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("incArmorNeedAti")){
					hpRatePerStr = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkRatePerAti")){
					incArmorNeedAti = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("mpPerInt")){
					atkRatePerAti = Double.parseDouble(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("mpRatePerInt")){
					mpPerInt = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				mpRatePerInt = Double.parseDouble(input);
				br.close();
			} catch (IOException e) {
				// TODO 自動產生 catch 區塊
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO 自動產生 catch 區塊
			e.printStackTrace();
		}
	}

	public double[][] getArmorList() {
		return armorList;
	}

	public void setArmorList(double[][] armorList) {
		this.armorList = armorList;
	}

	public int getAtkDice() {
		return atkDice;
	}

	public void setAtkDice(int atkDice) {
		this.atkDice = atkDice;
	}

	public int getAtkPerMainAttribute() {
		return atkPerMainAttribute;
	}

	public void setAtkPerMainAttribute(int atkPerMainAttribute) {
		this.atkPerMainAttribute = atkPerMainAttribute;
	}

	public double getAtkRatePerAti() {
		return atkRatePerAti;
	}

	public void setAtkRatePerAti(double atkRatePerAti) {
		this.atkRatePerAti = atkRatePerAti;
	}

	public int getHeroHpBase() {
		return heroHpBase;
	}

	public void setHeroHpBase(int heroHpBase) {
		this.heroHpBase = heroHpBase;
	}

	public int getHpPerStr() {
		return hpPerStr;
	}

	public void setHpPerStr(int hpPerStr) {
		this.hpPerStr = hpPerStr;
	}

	public double getHpRatePerStr() {
		return hpRatePerStr;
	}

	public void setHpRatePerStr(double hpRatePerStr) {
		this.hpRatePerStr = hpRatePerStr;
	}

	public int getIncArmorNeedAti() {
		return incArmorNeedAti;
	}

	public void setIncArmorNeedAti(int incArmorNeedAti) {
		this.incArmorNeedAti = incArmorNeedAti;
	}

	public int getMpPerInt() {
		return mpPerInt;
	}

	public void setMpPerInt(int mpPerInt) {
		this.mpPerInt = mpPerInt;
	}

	public double getMpRatePerInt() {
		return mpRatePerInt;
	}

	public void setMpRatePerInt(double mpRatePerInt) {
		this.mpRatePerInt = mpRatePerInt;
	}
}
