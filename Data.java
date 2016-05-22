import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.*;

import javax.swing.ImageIcon;

public class Data {
	ArrayList<Unit> units = new ArrayList<Unit>();
	ArrayList<Unit> bullets = new ArrayList<Unit>();//存放子彈物件
	ArrayList<Effect> effects = new ArrayList<Effect>();
	ArrayList<Item> mapItem = new ArrayList<Item>();//放在地上的物件
	GameData gameData = new GameData("./data/game.txt");
	BulletDatas bulletDatas = new BulletDatas("./data/unit/bullet/");
	HeroDatas heroDatas = new HeroDatas("./data/unit/hero/");
	CreepDatas creepDatas = new CreepDatas("./data/unit/creep/");
	BuildingDatas buildingDatas = new BuildingDatas("./data/unit/building/");
	SkillData skillData = new SkillData(this);
	EffectDatas effectDatas = new EffectDatas(this);
	ItemDatas itemDatas = new ItemDatas("./data/item/");
	ImageData imageData = new ImageData();
	CardDatas cardDatas;
	DeckDatas deckDatas;
	ArrayList<Trap> traps = new ArrayList<Trap>();
	MainFrame mainFrame;
	PaintPanel paintPanel;

	static String searchCard = "";
	static String compareSearchCard = "";
	boolean useKeyWordSearchCard = false;
	static String searchFriend = "";
	static String account = "";
	static String password = "";
	static String passwordStar = "";
	static String chatTemp = "";
	static ArrayList<String> chatList = new ArrayList<String>();
	AI ai = new AI();
	int summon = 0;
	int summonMax = 20;
	static int arrowNum = 1;
	static int waitSkill = 0;//等待按下的技能編號
	static int[] effect = new int[4];
	static int effectType = 0;//效果種類 1,2,3,4,5=畫線 矩形 圓形 圓矩 圖片
	static Color effectColor = null;
	static int camp = 1;//陣營 1,2=魔法方, 科技方
	String dialogStr = "";
	int dialogMode = 1;//0關閉 1開啟
	int hour = 0;
	int minute = 0;
	int second = 0;
	int nowView = 0;
	Unit unit;
	ArrayList<Unit> landscape = new ArrayList<Unit>();
	Terrain[][] ta;
	Deck nowDeck = null;
	public int nowChooseHeroNum = 2;//目前選的英雄編號
	int deckOffset = 0;//牌組
	int bagOffset = 0;//背包
	//士兵生成點
	//生兵
	int creatorCount = 0;//滿了就會生兵
	int creatorCountMax = 5;//生兵所需時間
	//左下角上路
	int creator1X = 300;
	int creator1Y = 3700;
	//左下角中路
	int creator2X = 875;
	int creator2Y = 4000;
	//左下角下路
	int creator3X = 1400;
	int creator3Y = 4700;
	//右上角上路
	int creator4X = 4100;
	int creator4Y = 300;
	//右上角中路
	int creator5X = 4280;
	int creator5Y = 940;
	//右上角下路
	int creator6X = 4637;
	int creator6Y = 1226;
	//滑鼠游標位置
	int mx;
	int my;
	int bigX;
	int bigY;
	static int wi = 0;//橫向地圖起始index
	static int hi = 0;//縱向地圖起始index
	boolean noCD  = false;
	boolean noMP  = false;
	boolean highRecover = false;
	Unit ChooseUnit;
	Data(){
		readData();
	}
	//是否在玩家畫面中
	public boolean inView(Unit unit){
		boolean result = false;
		if(new Button(wi*50,hi*50,800,600).isClicked(unit.getX(), unit.getY())){
			result = true;
		}
		return result;
	}
	public void readData(){
		readGameData();
		readBulletData();
		readHeroData();
		readCreepData();
		readBuildingData();
		itemDatas.readData();
		readCard();
		readDeck();
	}
	public void readCard(){
		cardDatas = new CardDatas(this,"./data/card/");
	}
	public void readDeck(){
		deckDatas = new DeckDatas(this,"./data/deck/");
	}
	public void readGameData(){
		gameData.readData();
	}
	public void readBulletData(){
		bulletDatas.readData();
	}
	public void readHeroData(){
		heroDatas.readData();
	}
	public void readCreepData(){
		creepDatas.readData();
	}
	public void readBuildingData(){
		buildingDatas.readData();
	}
	public void setFrame(MainFrame app){
		this.mainFrame = app;
	}
	public void changeArrow(int num){
		if(arrowNum!=num){
			Image cursorImage = new ImageIcon("./images/arrow/"+camp+"/"+num+".png").getImage();
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(11,11), "4");
			mainFrame.setCursor(customCursor);
			arrowNum = num;
		}
	}
	public static int getWaitSkill() {
		return waitSkill;
	}
	public static void setWaitSkill(int waitSkill) {
		Data.waitSkill = waitSkill;
	}
	public void keyIn(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_ENTER:
			sendMessage();
		break;
		case KeyEvent.VK_BACK_SPACE:
			dialogStr = dialogStr.substring(0,dialogStr.length()-1);
		break;
		default:
			if(e.getKeyCode()>=32&&e.getKeyCode()<=122)
			dialogStr += e.getKeyChar();
		}
	}
	public void sendMessage(){
		keyInDialog(dialogStr);
		dialogStr = "";
		dialogMode = 0;
	}
	public void keyInDialog(String s){
		s = s.toLowerCase();
		String[] cmd = s.split(" ");
		if(s.matches("levelmax")){
			unit.levelUp(10000, this);
		}else if(s.matches("die")){
			if(ChooseUnit!=null){
				ChooseUnit.setHP(0);
			}else
			unit.setHP(0);
		}else if(s.matches("recover")){
			unit.setHP(unit.getHPmax());
			unit.setMP(unit.getMPmax());
		}else if(s.matches("power")){
			cardDatas.power = cardDatas.powerMax;
		}else if(s.matches("ul")){
			unit.unlock();
		}else if(s.matches("move")){
			if(ChooseUnit!=null){
				ChooseUnit.unlock();
				ChooseUnit.setX(bigX);
				ChooseUnit.setY(bigY);
			}else{
				unit.unlock();
				unit.setX(bigX);
				unit.setY(bigY);
			}
		}else if(s.matches("nocd")){
			noCD = !noCD;
		}else if(s.matches("nomp")){
			noMP = !noMP;
		}else if(cmd[0].matches("hp")){
			try{

				if(ChooseUnit!=null){
					ChooseUnit.setHP(ChooseUnit.getHP()+Integer.parseInt(
				cmd[1]));
				}
			}catch(Exception ex){
				
			}
		}
	}
}
