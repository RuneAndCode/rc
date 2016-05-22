import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class View extends Spirit {
	//按鈕
	ArrayList<Button> bt0;//MainMenu
	ArrayList<Button> bt1;//practiceMode
	ArrayList<Button> bt3;//Option
	
	ArrayList<Unit> units = new ArrayList<Unit>();//存放單位(包含建築)物件
	ArrayList<Unit> bullets = new ArrayList<Unit>();//存放子彈物件
	
	static ArrayList<Terrain> tal = new ArrayList<Terrain>();//存放碰撞地形
	static int taHeight = 100;//總共長度
	static int taWidth = 101;//總共寬度
	static Terrain[][] ta = new Terrain[taHeight][taWidth];//存放地形

	static boolean showDialog = false;//是否顯示對話框

	//小地圖圖片座標和長寬
	Image smap = new ImageIcon("./images/menu/smap.png").getImage();
	int smapX = 650;
	int smapY = 450;
	int smapW = 150;
	int smapH = 150;
	int selfID = 1;
	//我方隊伍
	int team = 1;
	//拖曳區起始點
	static int drawStartX,drawStartY;
	//拖曳區終點
	static int drawEndX,drawEndY;
	//滑鼠狀態
	static  int MouseState = 0;//0, 1, 2, 3 無動作 按下去 拖曳 放開
//	已經有選中單位了
	static boolean hasChooseUnit = false;
	//目前選到的是第幾個單位
	static int nowChooseNum = 0;
//	目前選到的非幾方英雄是第幾個單位
	static int nowChooseNSNum = 0;
	static GameSetting gs;//遊戲設定
	Dimension screenSize;//維度
	
	Unit unit = new Unit(400,300);//我方英雄初始
	Unit enemyHero = new Unit(400,300);//敵方英雄初始
	int nowView = 0; //0 = MainMenu,1 = practiceMode,2=multiplayerMode,3=option,
	Data data;//資料(大部分class讀取此物件共通的參數)
	boolean showMenu = false;//顯示選單
	boolean showScore = false;//顯示分數
	//boolean takeItem = false;//拿起道具
	Item takeItem = new Item();//拿起的道具
	int takeNum = 0;//拿起第幾格
	//生兵
	//int creatorCount = 0;
	//int creatorCountMax = 50;/
	//左下角上路
	//int creatorX = 300;
	//int creatorY = 4000;
	//4=exit
//	預設黑色，魔法部份字體顯示
	static Color BLACK = new Color(53, 53, 53);
	//預設白色，科技部份字體顯示
	static Color WHITE = new Color(237, 237, 237);
	//小地圖用
	static Color RED = new Color(234, 50, 43);
	static Color GREY = new Color(103, 103, 103);
	static Color LIGHT_GREY = new Color(212, 212, 212);
	//經驗值
	static Color PURPLE = new Color(121, 34, 167);
	//血量條、魔法卡
	static Color GREEN = new Color(32, 127, 40);
	//法力值、鑲嵌卡
	static Color BLUE = new Color(23, 108, 155);
	//召喚卡
	static Color BROWN = new Color(209, 154, 59);
	//大廳介面按鈕按下時的字體顏色
	static Color YELLOW = new Color(255, 236, 69);
	static Color LIGHT_ORANGE = new Color(243, 186, 72);
	static Color PINK = new Color(240, 156, 156);
	//科技卡片能量表
	static Color SILVER_RED = new Color(242, 225, 225);
	//魔法卡片能量表
	static Color SILVER_GREEN = new Color(169, 215, 195);
	//技能等級顯示值
	static Color PURPLE_RED = new Color(160, 106, 104);
	Image chatImage = new ImageIcon("images/menu/1/chat.png").getImage();//聊天
	Button chatButton = new Button(513,467,78,25,null);
	Barrack[] barrack = new Barrack[6];
	MainMenu mainMenu;
	static Button deckArea = new Button(212,24,365,327);
	AePlayWave gameBgm;//目前背景音樂
	View()
	{
		
	}
	
	View(GameSetting gs,Data data)
	{
		
		this.gs = gs;
		data.nowView = nowView;
		this.data = data;
		units = data.units;
		bullets = data.bullets;
		data.unit = unit;
		data.ta = ta;
		data.camp = 2;
		mainMenu = new MainMenu(data,this);
		gameBgm = new AePlayWave("./BGM/1.wav");
		gameBgm.start();//播放
		
		
		//startGame();
		/*
		//產生測試用CP怪
		for(int i = 0;i < 5;i++){
			Unit unit2 = new Unit(400,300);
			data.creepDatas.CreateCreep(data.gameData, unit2, 1, 800, 4100+70*i, 1, 0, 2);
			units.add(unit2);
			
		}
		for(int i = 0;i < 5;i++){

			Unit unit3 = new Unit(400,300);
			unit3.setAI(1);
			data.creepDatas.CreateCreep(data.gameData, unit3, 1, 150+60*i, 4000, 1, 0, 1);
			unit3.setHP(50000);
			unit3.setHPmax(50000);
			//unit3.setAtk(150);
			units.add(unit3);
			
		}*/
//		初始主畫面
		
		initial0();
		/*
		*/
		/*
		 * System.out.println("選項");
							nowView = 3;
							initial3();
							bk = new ImageIcon("images/menu/option.jpg").getImage();
							break;
		 */
	}
	public void startGame(){
		initial1();
		gameBgm.stop();
		//gameBgm.stop();
		gameBgm = new AePlayWave("./BGM/2.wav");
		gameBgm.loop = true;
		//gameBgm.setLoop(true);
		gameBgm.start();
		nowView = 1;//進入練習模式
		checkSmap();//偵測小地圖啟動
		data.wi = 0;
		data.hi = 87;////移動到左下角
		data.readCard();//讀取卡片資料
		data.cardDatas.initial();
		updatePerSecond();//開始每秒更新
	}
	public void exitGame(){
		nowView = 0;//進入MENU
		
		gameBgm.interrupt();
		//gameBgm.stop();
		gameBgm = new AePlayWave("./BGM/1.wav");
		gameBgm.start();
		data.wi = 0;
		data.hi = 87;
		//data.cardDatas.initial();
	}
	//載入地圖
	public void initailMap(){
		
		String fileName = "./map/1.txt";
		// This is where a rual application would open the file.
		try {// 讀檔

			// 文件緩衝
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			// 讀取資料
			String s = br.readLine();
			//讀地形
			while (!s.matches("building")) {

				for(int i = 0;i < 100;i++)
				{
					String[] element = s.split(" ");
					for(int j = 0;j < 101;j++)
					{
						int num = Integer.parseInt(element[j]);
						ta[i][j].setType(num);
						ta[i][j].setImage(data.imageData.terrainImage[num]);
						if(num==2||num==5){
							Unit land = new Unit();
							land.setX(j*50);
							land.setY(i*50);
							land.setWidth(50);
							land.setHeight(50);
							data.landscape.add(land);
							ta[i][j].setCanMove(false);;
						}
					}
					s = br.readLine();
				}
				// 加入
				
				// 讀取下一行

			}
			s = br.readLine();
			//讀建築
			while (!s.matches("other")) {
				String[] element = s.split(" ");
				int num = Integer.parseInt(element[0]);
				int x = Integer.parseInt(element[1]);
				int y = Integer.parseInt(element[2]);
				Unit b = new Unit(x,y,3,num);
				int id = 0;
				int team = 0;
				if(num>=1&&num<=4){
					id = 0;
					team = 1;
				}else if(num>=5&&num<=8){
					id = 0;
					team = 2;
				}
				data.buildingDatas.CreateBuilding(data.gameData, b, num, x, y, 1, id, team);
				//System.out.println(e.getType()+","+e.getNum());
				//e.setHP(100);
				//e.setHPmax(100);
				units.add(b);
				// 加入
				
				// 讀取下一行
				s = br.readLine();
			}
			//算建築影響地形
			/*
			for(int n = 0;n < units.size();n++){
				Unit u = units.get(n);
				if(u!=null)
				if(u.getType()==3){
					for(int i = 0;i < 100;i++){
						for(int j = 0;j < 101;j++)
						{
							if(u.isCollision(ta[i][j])){
								ta[i][j].setCanMove(false);
							}
								
						}
					}
				}
			}*/
			s = br.readLine();
			//讀其他
			while (!s.matches("trigger")) {
				//s = br.readLine();
			}
			s = br.readLine();
			//讀觸發
			while (s!=null){
				//s = br.readLine();
			}
			// 關閉
			br.close();
			// 傳回資料
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//初始主畫面
	public void initial0(){
		
	}
	//初始練習模式
	public void initial1(){
//		產生地形
		for(int i = 0;i < taHeight;i++)
		for(int j = 0;j < taWidth;j++)
			ta[i][j] = (new Terrain(data,10+j*50,98+50*i,(int)(1+Math.random()*2)));
		
		//加入軍營
		for(int i = 0;i < barrack.length;i++){
			barrack[i] = new Barrack();
		}
		data.buildingDatas.CreateBuilding(data.gameData, barrack[0], 9, data.creator1X+50, data.creator1Y+50, 1, 0, 1);
		data.buildingDatas.CreateBuilding(data.gameData, barrack[1], 9, data.creator2X, data.creator2Y+50, 1, 0, 1);
		data.buildingDatas.CreateBuilding(data.gameData, barrack[2], 9, data.creator3X, data.creator3Y+50, 1, 0, 1);
		data.buildingDatas.CreateBuilding(data.gameData, barrack[3], 10, data.creator4X, data.creator4Y+50, 1, 0, 2);
		data.buildingDatas.CreateBuilding(data.gameData, barrack[4], 10, data.creator5X+50, data.creator5Y-50, 1, 0, 2);
		data.buildingDatas.CreateBuilding(data.gameData, barrack[5], 10, data.creator6X, data.creator6Y-100, 1, 0, 2);
		//units.add(barrack[0]);
		//units.add(barrack[1]);
		//units.add(barrack[2]);
		for(int i = 0;i < barrack.length;i++){
			units.add(barrack[i]);
		}
		//初始地圖
		initailMap();
		//產生自己的英雄並加入資料結構
		if(data.nowChooseHeroNum==0)
			data.nowChooseHeroNum = 2;
		data.heroDatas.CreateHero(data.gameData,unit,data.nowChooseHeroNum,300,4800,1,1,1);
		unit.setHeadImage(new ImageIcon("images/head/"+data.nowChooseHeroNum+".png").getImage());
		unit.setDeadImage(new ImageIcon("images/head/"+data.nowChooseHeroNum+".png").getImage());
		//unit.setDeadImage(new ImageIcon("images/dead/2.gif").getImage());
		unit.setRebirth(true);
		unit.skillCD = new int[]{5,5,5,5};
		unit.skillCDmax = new int[]{5,5,5,5};
		units.add(unit);
		//產生道具
		for(int i = 0;i < 4;i++){
			Item item = new Item();
			unit.item[i] = item;
		}
		
		//產生敵方的英雄並加入資料結構
		enemyHero = new Unit();
		data.heroDatas.CreateHero(data.gameData,enemyHero,47,4800,200,1,2,2);
		enemyHero.setHeadImage(new ImageIcon("images/head/"+47+".png").getImage());
		enemyHero.setDeadImage(new ImageIcon("images/head/"+47+".png").getImage());
		//unit.setDeadImage(new ImageIcon("images/dead/2.gif").getImage());
		enemyHero.setRebirth(true);
		enemyHero.skillCD = new int[]{5,5,5,5};
		enemyHero.skillCDmax = new int[]{5,5,5,5};
		units.add(enemyHero);
		enemyHero.setAI(11);
		data.ai.createSkillThread(data, enemyHero);
		enemyHero.setCPU(true);
		//產生道具
		for(int i = 0;i < 4;i++){
			Item item = new Item();
			enemyHero.item[i] = item;
		}
		
		
		Item itemx1 = new Item();
		data.itemDatas.CreateItem(itemx1, 1, 3);
		unit.item[0] = itemx1;
		Item itemx2 = new Item();
		data.itemDatas.CreateItem(itemx2, 1, 2);
		unit.item[1] = itemx2;
		data.dialogMode = 0;
		bt1 = new ArrayList<Button>();
		//小地圖面板
		Button b1 = new Button(smapX,smapY,smapW,smapH,null);
		bt1.add(b1);
		//技能紐1
		Button skillb1 = new Button(201,570,40,38,null);
		bt1.add(skillb1);
//		技能紐2
		Button skillb2 = new Button(241,570,40,38,null);
		bt1.add(skillb2);
//		技能紐3
		Button skillb3 = new Button(281,570,40,38,null);
		bt1.add(skillb3);
//		技能紐4
		Button skillb4 = new Button(321,570,40,38,null);
		bt1.add(skillb4);
		//選單紐
		Button menub = new Button(435,2,37,22,null);
		bt1.add(menub);
		//分數紐
		Button scoreb = new Button(475,3,37,22,null);
		bt1.add(scoreb);
		//卡片紐1
		Button card1b = new Button(371,548,37,45,null);
		bt1.add(card1b);
		//卡片紐2
		Button card2b = new Button(411,548,37,45,null);
		bt1.add(card2b);
		//卡片紐3
		Button card3b = new Button(451,548,37,45,null);
		bt1.add(card3b);
		//卡片紐4
		Button card4b = new Button(491,548,37,45,null);
		bt1.add(card4b);
		//卡片紐5
		Button card5b = new Button(531,548,37,45,null);
		bt1.add(card5b);
		//黃十字紐
		Button skillup0 = new Button(163,566,30,30,null);
		bt1.add(skillup0);
		//技能1升級紐
		Button skillup1 = new Button(201,560,34,8,null);
		bt1.add(skillup1);
//		技能2升級紐
		Button skillup2 = new Button(241,560,34,8,null);
		bt1.add(skillup2);
//		技能3升級紐
		Button skillup3 = new Button(281,560,34,8,null);
		bt1.add(skillup3);
//		技能4升級紐
		Button skillup4 = new Button(321,560,34,8,null);
		bt1.add(skillup4);
		//道具格1
		Button item1 = new Button(80,553,18,18,null);
		bt1.add(item1);
//		道具格2
		Button item2 = new Button(104,553,18,18,null);
		bt1.add(item2);
//		道具格3
		Button item3 = new Button(80,577,18,18,null);
		bt1.add(item3);
//		道具格4
		Button item4 = new Button(104,577,18,18,null);
		bt1.add(item4);
	}
	//初始遊戲選項
	public void initial3(){
		bt3 = new ArrayList<Button>();
		for(int i = 0;i < 18;i++)
		{
			bt3.add(new Button());
		}
		bt3.get(0).setImage(new ImageIcon("images/menu/option/option11.gif").getImage());
		bt3.get(0).setX(30);
		bt3.get(0).setY(100);
		bt3.get(1).setImage(new ImageIcon("images/menu/option/option12.gif").getImage());
		bt3.get(1).setX(230);
		bt3.get(1).setY(100);
		bt3.get(2).setImage(new ImageIcon("images/menu/option/option13.gif").getImage());
		bt3.get(2).setX(430);
		bt3.get(2).setY(100);
		bt3.get(3).setImage(new ImageIcon("images/menu/option/option14.gif").getImage());
		bt3.get(3).setX(630);
		bt3.get(3).setY(100);
		bt3.get(4).setImage(new ImageIcon("images/menu/option/option21.gif").getImage());
		bt3.get(4).setX(30);
		bt3.get(4).setY(200);
		bt3.get(5).setImage(new ImageIcon("images/menu/option/option22.gif").getImage());
		bt3.get(5).setX(230);
		bt3.get(5).setY(200);
		bt3.get(6).setImage(new ImageIcon("images/menu/option/option23.gif").getImage());
		bt3.get(6).setX(430);
		bt3.get(6).setY(200);
		bt3.get(7).setImage(new ImageIcon("images/menu/option/option24.gif").getImage());
		bt3.get(7).setX(630);
		bt3.get(7).setY(200);
		bt3.get(8).setImage(new ImageIcon("images/menu/option/option31.gif").getImage());
		bt3.get(8).setX(30);
		bt3.get(8).setY(300);
		bt3.get(9).setImage(new ImageIcon("images/menu/option/option32.gif").getImage());
		bt3.get(9).setX(230);
		bt3.get(9).setY(300);
		bt3.get(10).setImage(new ImageIcon("images/menu/option/option33.gif").getImage());
		bt3.get(10).setX(430);
		bt3.get(10).setY(300);
		bt3.get(11).setImage(new ImageIcon("images/menu/option/option41.gif").getImage());
		bt3.get(11).setX(30);
		bt3.get(11).setY(400);
		bt3.get(12).setImage(new ImageIcon("images/menu/option/option32.gif").getImage());
		bt3.get(12).setX(230);
		bt3.get(12).setY(400);
		bt3.get(13).setImage(new ImageIcon("images/menu/option/option33.gif").getImage());
		bt3.get(13).setX(430);
		bt3.get(13).setY(400);
		bt3.get(14).setImage(new ImageIcon("images/menu/option/option51.gif").getImage());
		bt3.get(14).setX(30);
		bt3.get(14).setY(500);
		bt3.get(15).setImage(new ImageIcon("images/menu/option/option32.gif").getImage());
		bt3.get(15).setX(230);
		bt3.get(15).setY(500);
		bt3.get(16).setImage(new ImageIcon("images/menu/option/option33.gif").getImage());
		bt3.get(16).setX(430);
		bt3.get(16).setY(500);
		bt3.get(17).setImage(new ImageIcon("images/menu/option/option54.gif").getImage());
		bt3.get(17).setX(630);
		bt3.get(17).setY(500);
	}
	//滑鼠移動
	public void mouseMove(int x,int y){
		data.mx = x;
		data.my = y;
		data.bigX = x+data.wi*50;
		data.bigY = y+data.hi*50;
		if(nowView==0)
			mainMenu.mouseMove(x, y);
	}
	public void mousePressed(int x,int y) {
		if(nowView==0)
			mainMenu.mousePressed(x, y);
	}
	//偵測點擊左鍵
	public void clickLeft(int x,int y)
	{
		
		switch(nowView){
		case 0: 
			break;
		case 1:
			if(data.dialogMode==1){//對話框
				if(chatButton.isClicked(x, y)){
					data.sendMessage();
				}
			}else
			if(takeItem.getNum()==0){//沒拿道具
//				小地圖面板被按到
				if(bt1.get(0).isClicked(x, y)){
					int tw = (int)((x-smapX)/1.8);
					if(tw>taWidth-16)
						tw = taWidth-16;
					int th = (int)((y-smapY)/1.72);
					if(th>taHeight-12)
						th = taHeight-12;
					data.wi = tw;
					data.hi = th;
				}else if(bt1.get(1).isClicked(x, y)){
					//技能1被按到
					if(unit.getSkillLevel()[0]>0){
						data.skillData.activate(unit,0);
					}
				}else if(bt1.get(2).isClicked(x, y)){
					//技能2被按到
					if(unit.getSkillLevel()[1]>0){
						data.skillData.activate(unit,1);
					}
				}else if(bt1.get(3).isClicked(x, y)){
					//技能3被按到
					if(unit.getSkillLevel()[2]>0){
						data.skillData.activate(unit,2);
					}
				}else if(bt1.get(4).isClicked(x, y)){
					//技能4被按到
					if(unit.getSkillLevel()[3]>0){
						data.skillData.activate(unit,3);
					}
				}else if(bt1.get(5).isClicked(x, y)){
					//選單紐被按到
					System.out.print("選單");
					if(showMenu==false){
						showMenu = true;
					}else{
						showMenu = false;
					}
				}else if(bt1.get(6).isClicked(x, y)){
					//分數紐被按到
					if(showScore==false){
						showScore = true;
					}else{
						showScore = false;
					}
				}else if(bt1.get(7).isClicked(x, y)){
					//卡片紐1被按到
					data.cardDatas.activate(unit,0);
				}else if(bt1.get(8).isClicked(x, y)){
					//卡片紐2被按到
					data.cardDatas.activate(unit,1);
				}else if(bt1.get(9).isClicked(x, y)){
					//卡片紐3被按到
					data.cardDatas.activate(unit,2);
				}else if(bt1.get(10).isClicked(x, y)){
					//卡片紐4被按到
					data.cardDatas.activate(unit,3);
				}else if(bt1.get(11).isClicked(x, y)){
					//卡片紐5被按到
					data.cardDatas.activate(unit,4);
				}else if(bt1.get(12).isClicked(x, y)){
					//黃十字紐被按到
					/*//未開放
					if(unit.getSkillpoint()>0){
						if(unit.yellowCross<10){
							unit.yellowCross++;
							unit.addAttribute(data);
							unit.setSkillpoint(unit.getSkillpoint()-1);
						}
					}*/
				}else if(bt1.get(13).isClicked(x, y)){
					System.out.println("升級技能1");
					//技能1升級紐被按到
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[0]<4){
						unit.learnSkill(0);
					}
				}else if(bt1.get(14).isClicked(x, y)){
					System.out.println("升級技能2");
//					技能2升級紐被按到
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[1]<4){
						unit.learnSkill(1);
					}
				}else if(bt1.get(15).isClicked(x, y)){
					System.out.println("升級技能3");
//					技能3升級紐被按到
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[2]<4){
						unit.learnSkill(2);
					}
				}else if(bt1.get(16).isClicked(x, y)){
					System.out.println("升級技能4");
					//技能4升級紐被按到
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[3]<3){
						unit.learnSkill(3);
					}
				}else if(bt1.get(17).isClicked(x, y)){
					System.out.println("道具格1");
					//道具格1被按到
					data.itemDatas.activate(unit,0);
				}else if(bt1.get(18).isClicked(x, y)){
					System.out.println("道具格2");
					//道具格2被按到
					data.itemDatas.activate(unit,1);
				}else if(bt1.get(19).isClicked(x, y)){
					System.out.println("道具格3");
					//道具格3被按到
					data.itemDatas.activate(unit,2);
				}else if(bt1.get(20).isClicked(x, y)){
					System.out.println("道具格4");
					//道具格4被按到
					data.itemDatas.activate(unit,3);
				}else{
					if(data.waitSkill!=0){//發動等待中的技能
						x+=data.wi*50;
						y+=data.hi*50;
						data.skillData.activateTarget(unit, data.waitSkill, x, y);
					}else{
//						單位選取
						x+=data.wi*50;
						y+=data.hi*50;
						System.out.println(x+","+y);
						boolean chooseOne = false;//已經選到一個
						//算點擊重疊的目標 只會點到一個單位
						for(int i = 0;i < units.size();i++)
						{
							if(units.get(i)!=null)
							if(unit!=null){
								if(chooseOne==false){
									//units.get(i).getId()==selfID&&
									if(units.get(i)!=null)
									if(units.get(i).isClicked(x, y))
									{
										units.get(i).setChoosed(true);
										nowChooseNum = i;
										if(!(units.get(i).getId()==selfID&&
												units.get(i).getType()==1)){
											nowChooseNSNum = i;
										}
										chooseOne = true;
										hasChooseUnit = true;
										data.ChooseUnit = units.get(i);
									}else
									{
										units.get(i).setChoosed(false);
									}
								}
								else{
									units.get(i).setChoosed(false);
								}
							}
							
						}
						if(!chooseOne){
							hasChooseUnit = false;
						}
					}
					
				}
			}else{
				//有拿道具
				if(y>543){
					if(bt1.get(17).isClicked(x, y)){
						System.out.println("放到道具格1");
						Item temp =  data.itemDatas.copy(unit.item[0]);
						unit.item[0] =  data.itemDatas.copy(takeItem);
						unit.item[takeNum-1].setTaken(false);
						unit.item[takeNum-1] = data.itemDatas.copy(temp);
						takeItem = new Item();
						//道具格1被按到
					}else if(bt1.get(18).isClicked(x, y)){
						System.out.println("放到道具格2");
						//道具格2被按到
						Item temp =  data.itemDatas.copy(unit.item[1]);
						unit.item[1] =  data.itemDatas.copy(takeItem);
						unit.item[takeNum-1].setTaken(false);
						unit.item[takeNum-1] = data.itemDatas.copy(temp);
						takeItem = new Item();
					}else if(bt1.get(19).isClicked(x, y)){
						System.out.println("放到道具格3");
						//道具格3被按到
						Item temp =  data.itemDatas.copy(unit.item[2]);
						unit.item[2] =  data.itemDatas.copy(takeItem);
						unit.item[takeNum-1].setTaken(false);
						unit.item[takeNum-1] = data.itemDatas.copy(temp);
						takeItem = new Item();
					}else if(bt1.get(20).isClicked(x, y)){
						System.out.println("放到道具格4");
						//道具格4被按到
						Item temp =  data.itemDatas.copy(unit.item[3]);
						unit.item[3] =  data.itemDatas.copy(takeItem);
						unit.item[takeNum-1].setTaken(false);
						unit.item[takeNum-1] = data.itemDatas.copy(temp);
						takeItem = new Item();
					}
				}else{//放在地上(待改範圍)
					Item temp = data.itemDatas.copy(takeItem);
					temp.setX(x+50*data.wi);
					temp.setY(y+50*data.hi);
					temp.setCDmax(takeItem.getCDmax());
					data.mapItem.add(temp);
					unit.item[takeNum-1].setTaken(false);
					unit.item[takeNum-1].setNum(0);
					unit.item[takeNum-1] = new Item();
					takeItem = new Item();
				}
			}
			

			break;
		case 3://選項
			for(int i = 0;i < bt3.size();i++)
			{
				if(bt3.get(i).isClicked(x, y))
				{
					switch(i){
						case 1:
							bt3.get(1).setChoosed(true);
							bt3.get(2).setChoosed(false);
							bt3.get(3).setChoosed(false);
							break;
						case 2:
							bt3.get(1).setChoosed(false);
							bt3.get(2).setChoosed(true);
							bt3.get(3).setChoosed(false);
							break;
						case 3:
							bt3.get(1).setChoosed(false);
							bt3.get(2).setChoosed(false);
							bt3.get(3).setChoosed(true);
							break;
						case 5:
							bt3.get(5).setChoosed(true);
							bt3.get(6).setChoosed(false);
							bt3.get(7).setChoosed(false);
							gs.changeScreen(1);
							//gs.changeScreen();
							break;
						case 6:
							bt3.get(5).setChoosed(false);
							bt3.get(6).setChoosed(true);
							bt3.get(7).setChoosed(false);
							gs.changeScreen(2);
							//gs.changeScreen();
							break;
						case 7:
							bt3.get(5).setChoosed(false);
							bt3.get(6).setChoosed(false);
							bt3.get(7).setChoosed(true);
							gs.changeScreen(3);
							//gs.changeScreen();
							break;
						case 9:
							bt3.get(9).setChoosed(true);
							bt3.get(10).setChoosed(false);
							break;
						case 10:
							bt3.get(9).setChoosed(false);
							bt3.get(10).setChoosed(true);
							break;
						case 12:
							bt3.get(12).setChoosed(true);
							bt3.get(13).setChoosed(false);
							break;
						case 13:
							bt3.get(12).setChoosed(false);
							bt3.get(13).setChoosed(true);
							break;
						case 15:
							bt3.get(15).setChoosed(true);
							bt3.get(16).setChoosed(false);
							break;
						case 16:
							bt3.get(15).setChoosed(false);
							bt3.get(16).setChoosed(true);
							break;
						case 17: System.out.println("主選單");
						nowView = 0;
						initial0();
						mainMenu.bk = new ImageIcon("images/menu/bk.jpg").getImage();
							break;
					}

				}
			}
			break;
		}
		
	}
	//偵測點擊中鍵
	public void clickMiddle(int x,int y)
	{
		
	}
//	偵測滾輪
	public void MouseWheel(int n)
	{
		switch(nowView){
		case 0: 
			if(deckArea.isClicked(data.mx, data.my)){
				if(n==1){
					data.deckOffset--;
				}else{
					data.deckOffset++;
				}
			}else{
				if(n==1){
					data.bagOffset--;
				}else{
					data.bagOffset++;
				}
			}
			break;
		}

	}
	//偵測點擊右鍵
	public void clickRight(int x,int y)
	{
		switch(nowView){
		case 0: 
			break;
		case 1:

			//取消目前狀態
			data.waitSkill = 0;
			data.changeArrow(1);
//			地圖面板被按到
			if(bt1.get(0).isClicked(x, y)){
				int tw = (int)((x-smapX)*30);
				int th = (int)((y-smapY)*30);
				x= tw;
				y= th;
				for(int i = 0;i < units.size();i++)
				{
					Unit u = units.get(i);
					if(u!=null){
						if(units.get(i).getId()==selfID&&units.get(i).isChoosed())
						{
							boolean clickEnemy = false;
							for(int j = 0;j < units.size();j++)
							{
								//System.out.println(units.get(j).getId());
								Unit e = units.get(j);
								//有點到敵人
								if(e!=null)
								if(e.isClicked( x, y))
								if(e.getId()!=selfID)
								{

									clickEnemy=true;
									//有已經選定的攻擊目標
									//u.setHasAtkObj(true);
									//u.setTrace(e);
									//System.out.println("clickEnemy");
									//射程範圍之內
									if(units.get(i).getRange()*units.get(i).getRange()>=
										(units.get(i).getX()-e.getX())*
										(units.get(i).getX()-e.getX())+
										(units.get(i).getY()-e.getY())*
										(units.get(i).getY()-e.getY()))
									{
										units.get(i).attack(data,units.get(i).getId(),units.get(i).getTeam(),units.get(j).getX()+units.get(j).getWidth()/2,
												units.get(j).getY()+e.getHeight()/2,
												bullets,e);
										//System.out.println("發出攻擊");
									}
								}
							}
							//點到的不是敵人=>移動
							if(!clickEnemy)
							{
								//尋路
								if(ta[y/50][x/50].isCanMove())
								u.findPath(ta,x,y);
								//
								u.setMotion(1);
								//units.get(i).inst(x, y);
								data.effectDatas.moveInst(u, x, y);
								
								if(u.getType()==1){//是英雄有音效
									final AePlayWave bgm = new AePlayWave("./sound/unit/"+u.getNum()+"/"+(int)(Math.random()*4+1)+".wav");
									bgm.start();
								}
							}
						}
					}
					
				}
			}else if(bt1.get(17).isClicked(x, y)){
				System.out.println("道具格1");
				takeItem=data.itemDatas.copy(unit.item[0]);
				unit.item[0].setTaken(true);
				takeNum = 1;
				//道具格1被按到
			}else if(bt1.get(18).isClicked(x, y)){
				System.out.println("道具格2");
				takeItem=data.itemDatas.copy(unit.item[1]);
				unit.item[1].setTaken(true);
				takeNum = 2;
				//道具格2被按到
			}else if(bt1.get(19).isClicked(x, y)){
				System.out.println("道具格3");
				takeItem=data.itemDatas.copy(unit.item[2]);
				unit.item[2].setTaken(true);
				takeNum = 3;
				//道具格3被按到
			}else if(bt1.get(20).isClicked(x, y)){
				System.out.println("道具格4");
				takeItem=data.itemDatas.copy(unit.item[3]);
				unit.item[3].setTaken(true);
				takeNum = 4;
				//道具格4被按到
			}else if(y<543)//大地圖面板
			{
				x+=data.wi*50;
				y+=data.hi*50;
				for(int i = 0;i < units.size();i++)
				{
					Unit u = units.get(i);
					if(u!=null){
						if(units.get(i).getId()==selfID&&units.get(i).isChoosed())
						{
							boolean clickEnemy = false;
							for(int j = 0;j < units.size();j++)
							{
								//System.out.println(units.get(j).getId());
								Unit e = units.get(j);
								//有點到敵人
								if(e!=null)
								if(e.isClicked( x, y))
								if(e.getId()!=selfID)
								{

									clickEnemy=true;
									//有已經選定的攻擊目標
									//u.setHasAtkObj(true);
									//u.setTrace(e);
									//System.out.println("clickEnemy");
									//射程範圍之內
									if(units.get(i).getRange()*units.get(i).getRange()>=
										(units.get(i).getX()-e.getX())*
										(units.get(i).getX()-e.getX())+
										(units.get(i).getY()-e.getY())*
										(units.get(i).getY()-e.getY()))
									{
										units.get(i).attack(data,units.get(i).getId(),units.get(i).getTeam(),units.get(j).getX()+units.get(j).getWidth()/2,
												units.get(j).getY()+e.getHeight()/2,
												bullets,e);
										//System.out.println("發出攻擊");
									}
								}
							}
							//點到的不是敵人=>移動
							if(!clickEnemy)
							{
								//尋路
								if(ta[y/50][x/50].isCanMove())
								u.findPath(ta,x,y);
								//
								u.setMotion(1);
								//units.get(i).inst(x, y);
								data.effectDatas.moveInst(u, x, y);
								
								if(u.getType()==1){//是英雄有音效
									final AePlayWave bgm = new AePlayWave("./sound/unit/"+u.getNum()+"/"+(int)(Math.random()*4+1)+".wav");
									bgm.start();
								}
							}
						}
					}
					
				}
			}
			//偵測是否有點到道具格
			for(int i = 0;i < data.mapItem.size();i++){
				Item item = data.mapItem.get(i);
				if(item.isClicked(x, y)){
					int num = 0;//第幾格有空間
					for(int j = 0;j < unit.item.length;j++){
						if(unit.item[j].getNum()==0){
							num = j+1;
							break;
						}
					}
					if(num==0){//沒空位
						
					}else{//有空位
						Item temp = data.itemDatas.copy(data.mapItem.remove(i));
						//System.out.print("take"+temp.getCDmax());
						unit.item[num-1] = temp;
						unit.item[num-1].setCD(temp.getCD());
						unit.item[num-1].setCDmax(temp.getCDmax());
						break;
					}
				}
			}
			break;
		}
		
	}
	//偵測滑鼠拖曳
	public void mouseDrag(int x,int y){
		switch(nowView){
		case 1:
			
			//小地圖面板被按到
			if(MouseState==0&&bt1.get(0).isClicked(x, y)){
				int tw = (int)((x-smapX)/1.8);
				if(tw>taWidth-16)
					tw = taWidth-16;
				int th = (int)((y-smapY)/1.72);
				if(th>taHeight-12)
					th = taHeight-12;
				data.wi = tw;
				data.hi = th;
			}else{
				if(MouseState == 0)//未按下
				{
					MouseState = 1;
					drawStartX = x;
					drawStartY = y;
					drawEndX = x;
					drawEndY = y;
				}
				if(MouseState == 1)//按下去
				{
					MouseState = 2;
					if(drawEndX >= drawStartX)
					drawEndX = x;
					if(drawEndY >= drawStartY)
					drawEndY = y;
				}
				if(MouseState == 2)//按著
				{
					MouseState = 2;
					if(drawEndX >= drawStartX)
					drawEndX = x;
					if(drawEndY >= drawStartY)
					drawEndY = y;
				}
			}
		}
	}
	//滑鼠放開
	public void mouseReleased(MouseEvent e,int x,int y) {
		switch(nowView){
			case 0:
				mainMenu.mouseReleased(x, y);
				/*
				for(int i = 0;i < bt0.size();i++)
				{
					if(bt0.get(i).isClicked(x, y))
					{
						switch(i){
						case 0: System.out.println("練習模式");
							//初始練習模式
							initial1();
							bgm.stop();
							AePlayWave bgm2 = new AePlayWave("BGM/2.wav");
							bgm2.start();
							nowView = 1;//進入練習模式
							checkSmap();//偵測小地圖啟動
							data.hi+=87;////移動到左下角
							data.readCard();//讀取卡片資料
							updatePerSecond();//開始每秒更新
							break;
						case 1: //左上攔
							break;
						case 2: //登入
							break;
						case 3: //註冊
							break;
						case 4: //
							System.out.println("離開遊戲");
							System.exit(0);
							break;
						}

					}
				}
				*/
				break;
			case 1:
				if(MouseState == 2)
				checkDrag(e.getButton());//檢查框選
				MouseState = 0;
				drawStartX = 0;
				drawStartY = 0;
				drawEndX = 0;
				drawEndY = 0;
				break;
		}
	}
	public void checkDrag(int button)//檢查框選
	{
		switch(button)
		{

			case MouseEvent.BUTTON1:
				
				boolean hasChoose = false;
				for(int i = 0;i < units.size();i++)
				{
					Unit unit = units.get(i);
					if(unit!=null){
//						int r = unit.getRadius();
						int x = unit.getX()+unit.getWidth()/2;
						int y = unit.getY()+unit.getHeight()/2;;
						if((x>=drawStartX+data.wi*50&&x<=drawEndX+data.wi*50)&&
						  (y>=drawStartY+data.hi*50&&y<=drawEndY+data.hi*50))
						{
							unit.setChoosed(true);
							data.ChooseUnit = unit;
							hasChoose = true;
							if(!hasChooseUnit)
							{
								hasChooseUnit=true;
								nowChooseNum = i;
							}
							if(!(units.get(i).getId()==selfID&&
									units.get(i).getType()==1)){
								nowChooseNSNum = i;
							}
						}
						else
						{
							unit.setChoosed(false);
						}
					}
					
				}
				if(hasChooseUnit)
					if(!hasChoose)
						hasChooseUnit=false;
				break;
				
			case MouseEvent.BUTTON2:
				break;
			case MouseEvent.BUTTON3:
				break;
		}
		
	}
	public void keyIn(KeyEvent e){
		switch(nowView){//0=主畫面,1=練習模式,2=連線,3=遊戲選項
		case 0://主畫面
			mainMenu.keyInMainMenu(e);
			 break;
		case 1://打字
			keyInPractice(e);
			break;
		}
	}
	
	//練習模式
	public void keyInPractice(KeyEvent e){
		switch(data.dialogMode){
		case 0:
			switch(e.getKeyCode())
			{//點技能快捷(假如還有點數就升級 沒有當發動)
				case KeyEvent.VK_Q:
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[0]<4){
						unit.learnSkill(0);
					}else if(unit.getSkillLevel()[0]>0){
						data.skillData.activate(unit,0);
					}
					//data.skillData.activate(unit,0);
				break;
				case KeyEvent.VK_W:
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[1]<4){
						unit.learnSkill(1);
					}else if(unit.getSkillLevel()[1]>0){
						data.skillData.activate(unit,1);
					}
					//data.skillData.activate(unit,1);
					break;
				case KeyEvent.VK_E:
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[2]<4){
						unit.learnSkill(2);
					}else if(unit.getSkillLevel()[2]>0){
						data.skillData.activate(unit,2);
					}
					//data.skillData.activate(unit,2);
					break;
				case KeyEvent.VK_R:
					if(unit.getSkillpoint()>0&&unit.getSkillLevel()[3]<3){
						unit.learnSkill(3);
					}else if(unit.getSkillLevel()[3]>0){
						data.skillData.activate(unit,3);
					}
					//data.skillData.activate(unit,3);
					break;
					//移動地圖
				case KeyEvent.VK_UP:
				if(data.hi>0)
					data.hi--;
				break;
				case KeyEvent.VK_DOWN:
				if(data.hi<taHeight-12)
					data.hi++;
				break;
				case KeyEvent.VK_LEFT:
				if(data.wi>0)
					data.wi--;
				break;
				case KeyEvent.VK_RIGHT:
				if(data.wi<taWidth-16)
					data.wi++;
				break;
				case KeyEvent.VK_ENTER:
					data.dialogMode = 1;//進入打字模式
				break;
				case KeyEvent.VK_F2:
					if(data.camp == 1)data.camp=2;
					else data.camp = 1;
					break;
				case KeyEvent.VK_F3:
					if(unit.getLevel()<25)
					unit.levelUp(10000, data);
					data.noCD = !data.noCD;
					data.noMP = !data.noMP;
					data.highRecover = !data.highRecover;
					break;
				case KeyEvent.VK_F12:
					exitGame();
					break;
			}
			break;
		case 1:
			data.keyIn(e);
			break;
		}
		
	}
	public void paint(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		switch(nowView){//0=主畫面,1=練習模式,2=連線,3=遊戲選項
			case 0: 
				//畫主選單
				mainMenu.paintMainMenu(screenSize,screenMode, g, io);
			break;
			case 1:
				//畫地形
				paintTerrain(screenSize,screenMode, g, io);
				//畫單位
				paintUnit(screenSize,screenMode, g, io);
				//畫圈選框
				g.setColor(GREEN);
				if(MouseState == 2)
				{
					drawRectangle(screenSize, screenMode,g,drawStartX,drawStartY,drawEndX-drawStartX, drawEndY-drawStartY,io);
				}
				paintUnderEffect(screenSize,screenMode, g, io);
				//畫界面
				paintUI(screenSize,screenMode, g, io);
				//畫特效
				paintEffect(screenSize,screenMode, g, io);
				break;
			case 3:
				//畫選項
				paintOption(screenSize,screenMode, g, io);
				break;
		}
	}
	//畫面板下的特效
	public void paintUnderEffect(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		for(int i = 0;i < data.effects.size();i++){
			Effect e = data.effects.get(i);
			if(e!=null){
				if(e.isLive()){
					if(e.isVisible()&&e.isUnderUI()){
						if(e.getColor()!=null){
							g.setColor(e.getColor());
						}
						switch(e.getType()){
						case 1: drawLine(screenSize,screenMode,g,e.getX1()-data.wi*50, e.getY1()-data.hi*50, e.getX2()-data.wi*50, e.getY2()-data.hi*50,io);
							break;
						case 2:g.fillRect(e.getX1()-data.wi*50, e.getY1()-data.hi*50, e.getX2(), e.getY2());
							break;
						case 3: drawOval(screenSize,screenMode,g,e.getX1()-data.wi*50,
									e.getY1()-data.hi*50,
									e.getX2(),
									e.getY2(),io);
							break;
						case 4: 
							break;
						case 5: 
							//g.drawImage(e.getImage(), e.getX1()-data.wi*50, e.getY1()-data.hi*50,e.getX2(),e.getY2(),io);
							drawImage(screenSize, screenMode,g,e.getImage(),e.getX1()-data.wi*50, e.getY1()-data.hi*50,e.getX2(),e.getY2(),io);
							
							break;
						case 6: 
							drawString(screenSize, screenMode,g,e.getString(), e.getX1()-data.wi*50, e.getY1()-data.hi*50,io);
							break;
						case 7://界面特效
							drawImage(screenSize, screenMode,g,e.getImage(),e.getX1(),e.getY1(),e.getX2(),e.getY2(),io);
						}
					}
				}else{
					data.effects.remove(i);
				}
			}
			
			
		}
	}
//	畫特效
	public void paintEffect(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		for(int i = 0;i < data.effects.size();i++){
			Effect e = data.effects.get(i);
			double alpha = e.getAlpha();
			if(e.isLive()){
				if(e.isVisible()&&!e.isUnderUI()){
					if(e.getColor()!=null){
						g.setColor(e.getColor());
					}
					switch(e.getType()){//效果種類 1,2,3,4,5,6,7=畫線 矩形 圓形 圓矩 圖片 字 界面特效
					case 1: 
						drawLine(screenSize,screenMode,g,e.getX1()-data.wi*50, e.getY1()-data.hi*50, e.getX2()-data.wi*50, e.getY2()-data.hi*50,io);
						break;
					case 2: 
						break;
					case 3: drawOval(screenSize,screenMode,g,e.getX1(),
								e.getY1(),
								e.getX2(),
								e.getY2(),io);
						break;
					case 4: 
						break;
					case 5: 
						//g.drawImage(e.getImage(), e.getX1()-data.wi*50, e.getY1()-data.hi*50,e.getX2(),e.getY2(),io);
						drawImage(screenSize, screenMode,g,e.getImage(),e.getX1()-data.wi*50, e.getY1()-data.hi*50,e.getX2(),e.getY2(),io);
						
						break;
					case 6: 
						drawString(screenSize, screenMode,g,e.getString(), e.getX1()-data.wi*50, e.getY1()-data.hi*50,io);
						break;
					case 7://界面特效
						drawImage(screenSize, screenMode,g,e.getImage(),e.getX1(),e.getY1(),e.getX2(),e.getY2(),io);
						break;
					case 8://透明圖片
						drawImageAlpha((float)alpha,screenSize, screenMode,g,e.getImage(),e.getX1(),e.getY1(),e.getX2(),e.getY2(),io);
					}
				}
			}else{
				data.effects.remove(i);
			}
			
		}
	}

	//畫選項
	public void paintOption(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		//畫背景
			drawImage(screenSize, screenMode,g,mainMenu.bk,0,0,800,600,io);
			if(bt3!=null)
			for(int i = 0;i < bt3.size();i++)
			{
				if(bt3.get(i).isChoosed())
				drawRectangle(screenSize, screenMode,g,bt3.get(i).getX(),bt3.get(i).getY(),bt3.get(i).getWidth(),bt3.get(i).getHeight(),io);
				drawImage(screenSize, screenMode,g,bt3.get(i).getImage(),bt3.get(i).getX(),bt3.get(i).getY(),bt3.get(i).getWidth(),bt3.get(i).getHeight(),io);
			}
		}
	//畫界面
	public void paintUI(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		g.setColor(BLACK);
		//貼黑底
		drawFillRectangle(screenSize,screenMode,g,0,0,380,64,io);
		drawFillRectangle(screenSize,screenMode,g,0,550,600,54,io);
		drawFillArc(screenSize, screenMode,g,569,527,132,132,180,-100,io);
		if(hasChooseUnit){
//			貼黑底
			g.setColor(BLACK);
			drawFillRectangle(screenSize,screenMode,g,430,1,370,72,io);
			drawFillRectangle(screenSize,screenMode,g,625,67,84,24,io);
		}
//		頭像
		if(unit.isDead()){
			drawImage(screenSize, screenMode,g,unit.getDeadImage(),8,8,80,80,io);
			
			Font font = g.getFont();
			g.setColor(BLACK);
			g.setFont(new Font(font.getFontName(),font.getStyle(),30));
			drawString(screenSize,screenMode,g,unit.getRebirthTime()+"", 41,61,io);
			g.setFont(new Font(font.getFontName(),font.getStyle(),30));
			g.setColor(WHITE);
			drawString(screenSize,screenMode,g,unit.getRebirthTime()+"", 40,60,io);
			g.setFont(new Font(font.getFontName(),font.getStyle(),font.getSize()));
		}else
			drawImage(screenSize, screenMode,g,unit.getHeadImage(),8,8,80,80,io);
//		貼黑底
		drawFillRectangle(screenSize,screenMode,g,31,1,64,16,io);
		//生命值
		int health = (int)(277*((double)unit.getHP()/unit.getHPmax()));
		double redRate = (double)((unit.getHPmax()-unit.getHP())/unit.getHPmax());
		double greenRate = (double)unit.getHP()/unit.getHPmax();
		Color color = new Color(colorNormalize((int)((32*greenRate+255*redRate))),
				colorNormalize((int)(127*greenRate))
				,(int)(40*greenRate));
		g.setColor(color);
		drawFillRectangle(screenSize,screenMode,g,90, 20, health, 22,io);
		g.setColor(WHITE);
		drawString(screenSize,screenMode,g,(int)unit.getHP()+"/"+(int)unit.getHPmax(), 200, 35,io);
		g.setColor(BLACK);
		//魔法值
		int mprate = (int)(277*((double)unit.getMP()/unit.getMPmax()));
		g.setColor(new Color(23,108,155));
		drawFillRectangle(screenSize,screenMode,g,90, 44, mprate, 22,io);
		//經驗值
		int exprate = (int)(337*((double)unit.getExp1()/unit.getExp2()));
		Color purple = new Color(121,34,167);
		g.setColor(purple);
		drawFillRectangle(screenSize,screenMode,g,33, 1, exprate, 16,io);
		g.setColor(WHITE);
		drawString(screenSize,screenMode,g,(int)unit.getMP()+"/"+(int)unit.getMPmax(), 200,60,io);
		
//		經驗值
		if(hasChooseUnit){

			Unit u = data.ChooseUnit;
			if(u!=null)
			if(u.getType()==1){
				exprate = (int)(337*((double)u.getExp1()/u.getExp2()));
				g.setColor(purple);
				drawFillRectangle(screenSize,screenMode,g,506, 1, exprate, 16,io);
			}
		}
		/*
		if(exprate>33){
			int rightExp = exprate - 33;
			drawFillRectangle(screenSize,screenMode,g,33, 1, rightExp, 16,io);
			drawFillRectangle(screenSize,screenMode,g,0, 1, 33, 32,io);
		}else
			drawFillRectangle(screenSize,screenMode,g,0, 1, exprate, 32,io);*/

		//卡片能量槽
		if(data.camp==1){
			g.setColor(SILVER_GREEN);
		}else
		g.setColor(SILVER_RED);
		int arc = (int)(((double)data.cardDatas.power/data.cardDatas.powerMax)*100);
		drawFillArc(screenSize, screenMode,g,569,527,132,132,180,-arc,io);
		//中間
		//g.setColor(new Color(232,187,254));
		//drawFillArc(screenSize, screenMode,g,377,8,46,46,0,360,io);
		Image timeui = new ImageIcon("images/menu/time.png").getImage();
		drawImage(screenSize, screenMode,g,timeui,377,8,46,46,io);
		//選取單位面板
		if(hasChooseUnit)
		paintChooseUnit(screenSize,screenMode,g,io);
		//顯示技能CD等級
		paintSkill(screenSize,screenMode,g,io);
//		分數
		paintScore(screenSize,screenMode,g,io);
		//基本面板
		Image ui = new ImageIcon("images/menu/"+data.camp+"/ui.png").getImage();//遊戲面板
		drawImage(screenSize, screenMode,g,ui,0,0,800,600,io);
//		剩餘技能點數
		g.setColor(YELLOW);
		drawString(screenSize,screenMode,g,unit.getSkillpoint()+"", bt1.get(1).getX()-40,bt1.get(1).getY()-10,io);
		//遊戲選單
		paintGameMenu(screenSize,screenMode,g,io);
		
		//時間
		g.setColor(WHITE);
		String minute = "";
		String second = "";
		String hour = "";
		if(data.hour<10)
			hour+="0";
		if(data.minute<10)
			minute+="0";
		if(data.second<10)
			second+="0";
		hour+=data.hour;
		minute+=data.minute;
		second+=data.second;
		drawString(screenSize,screenMode,g,hour+":"+minute+":"+second, 376,12,io);
		//畫卡片
		if(data.cardDatas.canDraw==1)
		drawCard(screenSize, screenMode,g,io);
		//畫小地圖
		paintSmap(screenSize,screenMode, g, io);
		//畫錢
		paintMoney(screenSize,screenMode,g,io);
		//顯示等級經驗
		paintState(screenSize,screenMode,g,io);
		//對話框
		paintDialog(screenSize,screenMode,g,io);
		//畫道具
		paintItem(screenSize,screenMode,g,io);
	}
//	畫道具
	public void paintItem(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		Item[] items = unit.getItem();
		for(int i = 0;i < items.length;i++){
			Item item = items[i];
			if(item.getNum()!=0)
			if(!item.isTaken()){
				Image image = new ImageIcon("images/item/"+item.getNum()+".png").getImage();
				Button b = bt1.get(17+i);
				drawImage(screenSize, screenMode,g,image,b.getX(),b.getY(),b.getWidth(),b.getHeight(),io);
				g.setColor(GREEN);
				if(item.getTimes()>0){//有使用次數
					drawString(screenSize,screenMode,g, item.getTimes()+"" ,b.getX()+b.getWidth()-10,b.getY()+b.getHeight(),io);
				}
				g.setColor(WHITE);
				if(item.getCDmax()>0){//有CD時間
					drawString(screenSize,screenMode,g, item.getCD()+"" ,b.getX(),b.getY()+b.getHeight()/2,io);
				}
			}else{
				Image image = new ImageIcon("images/item/"+item.getNum()+".png").getImage();
				Button b = bt1.get(17+i);
				drawImage(screenSize, screenMode,g,image,data.mx,data.my,b.getWidth(),b.getHeight(),io);
			}
		}
	}
	//畫卡片
	public void drawCard(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		Font font = g.getFont();
		//畫下一次要抽卡的時間
		//g.setColor(WHITE);
		//drawString(screenSize,screenMode,g,data.cardDatas.nextTime+"", 573,560,io);
		//牌組數量
		//Font deckFont = new Font("digital-7", Font.PLAIN, 24);
		//g.setFont(deckFont);
		g.setColor(WHITE);
		//drawString(screenSize,screenMode,g,data.cardDatas.deck.size()+"", 616,595,io);
		int digit10 = data.cardDatas.deck.size()/10;
		int digit1 = data.cardDatas.deck.size()%10;
		drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/"+data.camp+"/decknum/"+digit10+".png").getImage(),
				612,577,12,24,io);
		drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/"+data.camp+"/decknum/"+digit1+".png").getImage(),
				624,577,12,24,io);
		//g.setFont(font);
		//畫手牌
		int x = 372;
		int y = 551;
		for(int i =0;i < data.cardDatas.hand.length;i++){
			if(data.cardDatas.hand[i]!=null){
				int num = data.cardDatas.hand[i].getNum();
				if(num==0){//手牌為空
					Image image = new ImageIcon("images/card/"+data.camp+"/back.png").getImage();
					drawImage(screenSize, screenMode,g,image,x,y,37,45,io);
					x+=40;
				}else{
					Image image = new ImageIcon("images/card/"+num+".png").getImage();
					drawImage(screenSize, screenMode,g,image,x,y,37,45,io);
					//畫種類
					int type = data.cardDatas.hand[i].getType();
					switch(type){
						case 1: g.setColor(new Color(148,82,10));
							break;
						case 2: g.setColor(GREEN);
						break;
						case 3: g.setColor(BLUE);
						break;
						case 4: g.setColor(new Color(121,34,167));
						break;
					}
					drawFillRectangle(screenSize,screenMode,g,x+2, 547, 33, 2,io);
					x+=40;
				}
			}
			
		}
		//卡片說明
		if(bt1.get(7).isClicked(data.mx, data.my)){
			Image image = new ImageIcon("images/guide/"+data.cardDatas.hand[0].getNum()+".png").getImage();
			drawImage(screenSize, screenMode,g,image,368,396,io);
		}else if(bt1.get(8).isClicked(data.mx, data.my)){
			Image image = new ImageIcon("images/guide/"+data.cardDatas.hand[1].getNum()+".png").getImage();
			drawImage(screenSize, screenMode,g,image,368,396,io);
		}else if(bt1.get(9).isClicked(data.mx, data.my)){
			Image image = new ImageIcon("images/guide/"+data.cardDatas.hand[2].getNum()+".png").getImage();
			drawImage(screenSize, screenMode,g,image,368,396,io);
		}else if(bt1.get(10).isClicked(data.mx, data.my)){
			Image image = new ImageIcon("images/guide/"+data.cardDatas.hand[3].getNum()+".png").getImage();
			drawImage(screenSize, screenMode,g,image,368,396,io);
		}else if(bt1.get(11).isClicked(data.mx, data.my)){
			Image image = new ImageIcon("images/guide/"+data.cardDatas.hand[4].getNum()+".png").getImage();
			drawImage(screenSize, screenMode,g,image,368,396,io);
		}
	}
	//畫選單(遊戲中的)
	public void paintGameMenu(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		if(showMenu){
			Image image = new ImageIcon("images/menu/chooseUnit.png").getImage();
			drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/menub2.png").getImage(),434,4,34,12,io);
		}else{
			drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/menub1.png").getImage(),434,4,34,12,io);
		}
		
	}
	//畫備選取的單位的界面
	public void paintChooseUnit(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		//nowChooseUnit
		if(nowChooseNSNum<units.size())
		try{
			
			//Unit u = units.get(nowChooseNSNum);
			Unit u = data.ChooseUnit;
			if(u!=null){
				//頭像
				if(u.getType()==1){//是英雄
//					頭像
					if(u.isDead()){
						drawImage(screenSize, screenMode,g,u.getHeadImage(),710,15,80,80,io);
						
						Font font = g.getFont();
						g.setColor(BLACK);
						g.setFont(new Font(font.getFontName(),font.getStyle(),30));
						drawString(screenSize,screenMode,g,u.getRebirthTime()+"", 41,61,io);
						g.setFont(new Font(font.getFontName(),font.getStyle(),30));
						g.setColor(WHITE);
						drawString(screenSize,screenMode,g,u.getRebirthTime()+"", 40,60,io);
						g.setFont(new Font(font.getFontName(),font.getStyle(),font.getSize()));
					}else
						drawImage(screenSize, screenMode,g,u.getHeadImage(),710,15,80,80,io);
				}else{//不是英雄
					Image image = null;
					switch(u.getType())
					{
						case 1:
							image = new ImageIcon("images/unit/hero/"+u.getImageNum()+"/"+u.getDir()+".gif").getImage();
							break;
						case 2:
							image = new ImageIcon("images/unit/creep/"+u.getImageNum()+"/"+u.getDir()+".gif").getImage();
							break;
						case 3:
							if(u.getNum()==9){
								image = new ImageIcon("./images/unit/building/1/5.gif").getImage();
							}else if(u.getNum()==10){
								image = new ImageIcon("./images/unit/building/2/5.gif").getImage();
							}else
							if(u.getImageNum()<=4){
								image = new ImageIcon("./images/unit/building/1/"+u.getImageNum()+".gif").getImage();
							}else{
								image = new ImageIcon("./images/unit/building/2/"+(u.getImageNum()-4)+".gif").getImage();
							}
							
							break;
						case 4:
							image = new ImageIcon("images/unit/bullet/"+u.getImageNum()+"/"+u.getDir()+".gif").getImage();
							break;
					}
					u.setImage(image);
					//畫單位圖像
					drawImage(screenSize, screenMode,g,image,710,15,80,80,io);
				}

				//裝備
				if(u.getType()==1){//是英雄
					Item[] items = unit.getItem();
					int tempx = 632;
					for(int i = 0;i < items.length;i++){
						Item item = items[i];
						Image image = new ImageIcon("images/item/"+item.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,image,tempx,70,15,15,io);
						tempx+=20;
					}
				}

				
				//生命值
				int health = (int)(277*((double)u.getHP()/u.getHPmax()));
				double redRate = (double)((u.getHPmax()-u.getHP())/u.getHPmax());
				double greenRate = (double)u.getHP()/u.getHPmax();
				Color color = new Color(colorNormalize((int)((32*greenRate+255*redRate))),
						colorNormalize((int)(127*greenRate))
						,(int)(40*greenRate));
				g.setColor(color);
				drawFillRectangle(screenSize,screenMode,g,433, 20, health, 22,io);
				g.setColor(WHITE);
				drawString(screenSize,screenMode,g,(int)u.getHP()+"/"+(int)u.getHPmax(), 550, 38,io);
				//魔法值
				if(u.getMPmax()>0){
					int mprate = (int)(277*((double)u.getMP()/u.getMPmax()));
					g.setColor(new Color(23,108,155));
					drawFillRectangle(screenSize,screenMode,g,433, 44, mprate, 24,io);
					g.setColor(WHITE);
					drawString(screenSize,screenMode,g,(int)u.getMP()+"/"+(int)u.getMPmax(), 550, 62,io);
				}

				
				
				//貼上層
				Image image = new ImageIcon("images/menu/"+data.camp+"/chooseUnit.png").getImage();
				drawImage(screenSize, screenMode,g,image,400,0,400,200,io);

				//等級
				g.setColor(YELLOW);
				drawString(screenSize,screenMode,g,""+u.getLevel(), 782,14,io);
				//名字
				g.setColor(WHITE);
				drawString(screenSize,screenMode,g,""+u.getName(), 712,14,io);
				
//				攻擊力
				if(data.camp==1){
					g.setColor(BLACK);
				}else
				g.setColor(WHITE);
				drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/attack.png").getImage(),724,100,20,20,io);
				drawString(screenSize,screenMode,g,u.getAtk()+"", 744,115,io);
				//裝甲
				drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/defend.png").getImage(),724,130,20,20,io);
				drawString(screenSize,screenMode,g,u.getArmor()+"", 744,145,io);
				if(u.getType()==1){
//					黃十字
					drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/yellow_ten.png").getImage(),724,160,20,20,io);
					drawString(screenSize,screenMode,g,u.getBaseAttribute()[0]+" "+
							u.getBaseAttribute()[1]+" "+
							u.getBaseAttribute()[2], 744,175,io);
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			//donothing
		}

	}
	//畫分數
	public void paintScore(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		if(showScore){
			Image image = new ImageIcon("images/menu/"+data.camp+"/score.png").getImage();
			drawImage(screenSize, screenMode,g,image,400,0,400,259,io);
			drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/scoreb2.png").getImage(),471,4,34,12,io);
		}else{
			drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/scoreb1.png").getImage(),471,4,34,12,io);
		}
	}
	//話對話框和對話
	public void paintDialog(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		if(data.dialogMode==1){
			drawImage(screenSize, screenMode,g,chatImage,100,450,510,60,io);
			g.setColor(WHITE);
			drawString(screenSize,screenMode,g,data.dialogStr, 123, 486,io);
		}
	}
	//狀態
	public void paintState(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		//名字
		g.setColor(WHITE);
		drawString(screenSize,screenMode,g,unit.getName(), 38,12,io);
		//等級
		Font font = g.getFont();
		//g.setFont(new Font(font.getFontName(),font.getStyle(),30));
		g.setColor(YELLOW);
		drawString(screenSize,screenMode,g,""+unit.getLevel(), 4,18,io);
		g.setFont(new Font(font.getFontName(),font.getStyle(),font.getSize()));
		//經驗
		//g.setColor(Color.CYAN);
		//drawString(screenSize,screenMode,g,unit.getExp1()+"/"+unit.getExp2(), 100,11,io);
//		攻擊力
		if(data.camp==1){
			g.setColor(BLACK);
		}else
		g.setColor(WHITE);
		drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/attack.png").getImage(),0,535,20,20,io);
		drawString(screenSize,screenMode,g,unit.getAtk()+"", 20,550,io);
		//裝甲
		drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/defend.png").getImage(),0,555,20,20,io);
		drawString(screenSize,screenMode,g,unit.getArmor()+"", 20,570,io);
//		黃十字
		drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/yellow_ten.png").getImage(),0,575,20,20,io);
		drawString(screenSize,screenMode,g,unit.getBaseAttribute()[0]+" "+
				unit.getBaseAttribute()[1]+" "+
				unit.getBaseAttribute()[2], 20,590,io);
		//魔法裝甲
		//drawString(screenSize,screenMode,g,"MArmor:"+unit.getManaArmor()+"", 0,590,io);
		
	}
	//畫技能面板
	public void paintSkill(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		//可升級標示
		
		for(int num = 0;num < 4;num++){
			int skillLevel = unit.getSkillLevel()[num];
			
			if(unit.getLevel()<6){//六級以下
				if(unit.getPreLearnSkill()!=(num+1)&&num!=3){//只能學習跟之前不一樣的技能而且不能學大絕
					if(unit.getSkillpoint()>0)
					drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/levelup.png").getImage(),201+40*num,550,35,8,io);
				}else{
					for(int j = 0;j < skillLevel;j++){

						g.setColor(PINK);
						drawFillRectangle(screenSize, screenMode,g,
								201+40*num+9*j,550,8,8,io);
					}
				}
			}else{
				if(num!=3&&unit.getSkillLevel()[num]<4){
					if(unit.getSkillpoint()>0)
					drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/levelup.png").getImage(),201+40*num,550,35,8,io);
				}else if(unit.getBigSkill()>0&&unit.getSkillLevel()[num]<3){
					if(unit.getSkillpoint()>0)
					drawImage(screenSize, screenMode,g,new ImageIcon("images/menu/levelup.png").getImage(),201+40*num,550,35,8,io);
				}else{
					for(int j = 0;j < skillLevel;j++){

						g.setColor(PINK);
						drawFillRectangle(screenSize, screenMode,g,
								201+40*num+9*j,550,8,8,io);
					}
				}
			}
		}
		if(unit.yellowCross>0){
			drawString(screenSize,screenMode,g,unit.yellowCross+"",
					bt1.get(12).getX(),bt1.get(12).getY()+10,io);
		}
		
		//等級
		g.setColor(YELLOW);
		for(int i = 0;i < 4;i++)
			drawString(screenSize,screenMode,g,unit.skillLevel[i]+"", bt1.get(i+1).getX(),bt1.get(i+1).getY()+10,io);
		//CD
		g.setColor(GREEN);
		for(int i = 0;i < 4;i++)
			drawString(screenSize,screenMode,g,unit.skillCD[i]+"", bt1.get(i+1).getX(),bt1.get(i+1).getY(),io);
		//圖示
		for(int i = 0;i < 4;i++){
			drawImage(screenSize, screenMode,g,new ImageIcon("images/skill/"+unit.getSkill()[i]+".jpg").getImage(),bt1.get(i+1).getX(),bt1.get(i+1).getY()-10,36,36,io);
		}
	}
	//畫金錢面板
	public void paintMoney(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		g.setColor(BLACK);
		int digit = 8;
		if(unit.getMoney()>0){

			int mdigit = (int)Math.log10(unit.getMoney());
			digit -= mdigit;
			drawString(screenSize,screenMode,g,unit.getMoney()+"", 92+digit,548,io);
		}else{
			drawString(screenSize,screenMode,g,unit.getMoney()+"", 92+digit,548,io);
		}
	}
	//畫單位
	public void paintUnit(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		//畫放在地圖上的道具
		for(int i = 0;i < data.mapItem.size();i++){
			Item item = data.mapItem.get(i);
			Image image = new ImageIcon("images/item/"+item.getNum()+".png").getImage();
			Button b = bt1.get(17);
			drawImage(screenSize, screenMode,g,image,item.getX()-data.wi*50,item.getY()-data.hi*50,b.getWidth(),b.getHeight(),io);
		}
		//單位
		for(int i = 0;i < units.size();i++)
		{
			Unit u = units.get(i);
			if(u!=null)
			if(u.isVisible()){
				Image image = null;
				switch(u.getType())
				{
					case 1://英雄
						
						drawImage(screenSize, screenMode,g,data.imageData.heroBottom,
								u.getX()-data.wi*50-10,u.getY()-data.hi*50+15,u.getWidth()+20,u.getHeight()+20,io);
						image = new ImageIcon("images/unit/hero/"+u.getImageNum()+"/"+u.getDir()+".gif").getImage();
						break;
					case 2:
						image = new ImageIcon("images/unit/creep/"+u.getImageNum()+"/"+u.getDir()+".gif").getImage();
						break;
					case 3:
						if(u.getImageNum()==9){
							image = new ImageIcon("./images/unit/building/1/5.gif").getImage();
						}else if(u.getImageNum()==10){
							image = new ImageIcon("./images/unit/building/2/5.gif").getImage();
						}else
						if(u.getImageNum()<=4){
							image = new ImageIcon("./images/unit/building/1/"+u.getImageNum()+".gif").getImage();
						}else{
							image = new ImageIcon("./images/unit/building/2/"+(u.getImageNum()-4)+".gif").getImage();
						}
						
						break;
					case 4:
						image = new ImageIcon("images/unit/bullet/"+u.getImageNum()+"/"+u.getDir()+".gif").getImage();
						break;
				}
				u.setImage(image);
				//畫單位圖像
				drawImage(screenSize, screenMode,g,u.getImage(),u.getX()-data.wi*50,u.getY()-data.hi*50,u.getWidth(),u.getHeight(),io);
				//暈眩
				if(u.isStunned()){
					g.setColor(WHITE);
					drawString(screenSize,screenMode,g,"@", u.getX()-data.wi*50-25,u.getY()-data.hi*50-100,io);
				}
				//畫圈選框
				if(u.isChoosed())
				{
					g.setColor(GREEN);
					drawOval(screenSize,screenMode,g,u.getX()-data.wi*50,
							u.getY()-data.hi*50+units.get(i).getHeight(),
							u.getWidth(),
							u.getWidth()/5,io);
				}
				//畫生命值
				if(u.getHPmax()>0)
				{
					g.setColor(BLACK);
					drawRectangle(screenSize,screenMode,g,u.getX()-data.wi*50-1, u.getY()-data.hi*50-21, 51, 6,io);
					int health = (int)(50*((double)u.getHP()/u.getHPmax()));
					int red = (int)(255*(double)(u.getHPmax()-u.getHP())/u.getHPmax());
					int green = (int)(255*(double)u.getHP()/u.getHPmax());
					Color color = new Color(colorNormalize(red),colorNormalize(green),0);
					g.setColor(color);
					drawFillRectangle(screenSize,screenMode,g,u.getX()-data.wi*50, u.getY()-data.hi*50-20, health, 5,io);
					g.setColor(GREEN);
					drawString(screenSize,screenMode,g,(int)u.getHP()+"//"+(int)u.getHPmax(), u.getX()-data.wi*50-30,u.getY()-data.hi*50-30,io);
				}
				//畫座標
				if(!u.path.isEmpty())
				drawString(screenSize,screenMode,g,u.path.get(0).x+","+u.path.get(0).y, u.getX()-data.wi*50-30,u.getY()-data.hi*50-50,io);
				
				//畫魔法值
				if(u.getMPmax()>0)
				{
					g.setColor(BLACK);
					drawRectangle(screenSize,screenMode,g,u.getX()-data.wi*50-1, u.getY()-data.hi*50-16, 51, 6,io);
					int health = (int)(50*((double)u.getMP()/u.getMPmax()));
					g.setColor(BLUE);
					drawFillRectangle(screenSize,screenMode,g,u.getX()-data.wi*50, u.getY()-data.hi*50-15, health, 5,io);
					g.setColor(BLUE);
					drawString(screenSize,screenMode,g,(int)u.getMP()+"//"+(int)u.getMPmax(), u.getX()-data.wi*50+30,u.getY()-data.hi*50-30,io);
				}
			}
			
		}
		//子彈
		for(int i = 0;i < bullets.size();i++)
		{
			Unit b = bullets.get(i);
			Image image = null;
			switch(b.getType())
			{
				case 0:
					image = new ImageIcon("images/unit/hero/"+b.getNum()+"/"+b.getDir()+".gif").getImage();
					break;
				case 1:
					image = new ImageIcon("images/unit/creep/"+b.getNum()+"/"+b.getDir()+".gif").getImage();
					break;
				case 5:
					image = new ImageIcon("images/unit/bullet/"+b.getNum()+"/"+b.getDir()+".gif").getImage();
					break;
			}
			b.setImage(image);
			drawImage(screenSize, screenMode,g,b.getImage(),b.getX()-data.wi*50,b.getY()-data.hi*50,b.getWidth(),b.getHeight(),io);
		}
		//畫陷阱
		for(int i = 0;i < data.traps.size();i++)
		{
			Trap t = data.traps.get(i);
			Image image = new ImageIcon("images/trap/"+t.getNum()+".png").getImage(); 
			drawImage(screenSize, screenMode,g,image,t.getX()-data.wi*50,t.getY()-data.hi*50,50,50,io);
		}
	}
//	畫地形
	public void paintTerrain(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		
		for (int i = data.hi,k=0; i < data.hi+12; i++,k++) {
			for (int j = data.wi,l=0; j < data.wi+16; j++,l++) {
				if(j<taWidth&&i<taHeight)
					drawImage(screenSize, screenMode,g,ta[i][j].getImage(),l*50,50*k,ta[i][j].getHeight()+1,ta[i][j].getWidth()+1,io);
			}
		}
	}
//	畫小地圖
	public void paintSmap(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){

		//drawImage(screenSize, screenMode,g,smap,629,435,100,100,io);
		g.setColor(BLACK);
		drawFillRectangle(screenSize, screenMode,g,smapX,smapY,smapW,smapH,io);
		g.setColor(WHITE);
		drawRectangle(screenSize, screenMode,g,smapX+(int)(data.wi*1.57),smapY+(int)(data.hi*1.55),24,18,io);
		//畫小地圖上的單位建築
		for(int i = 0;i < units.size();i++)
		{
			Unit u = units.get(i);
			if(u!=null){
				switch(u.getType())
				{
					
					//英雄
					case 0:
						team = u.getTeam();
						if(u.getId()==selfID)
						{
							g.setColor(WHITE);
						}
						else if(u.getTeam() == team)
						{
							g.setColor(BLUE);
						}
						
						break;
					case 1:
						if(u.getId()==selfID)
						{
							g.setColor(WHITE);
						}
						else if(u.getTeam() == team)
						{
							g.setColor(BLUE);
						}else
						{
							g.setColor(RED);
						}
						break;
					case 2:
						if(u.getId()==selfID)
						{
							g.setColor(WHITE);
						}
						else if(u.getTeam() == team)
						{
							g.setColor(BLUE);
						}else
						{
							g.setColor(RED);
						}
						break;
					case 3:
						if(u.getId()==selfID)
						{
							g.setColor(WHITE);
						}
						else if(u.getTeam() == team)
						{
							g.setColor(BLUE);
						}else
						{
							g.setColor(RED);
						}
						break;
				}
				int tw = (int)(smapW*(((u.getX()/50.0))/100));
				int th = (int)(smapH*(((u.getY()/50.0))/100));
				if(smapX+(int)(tw)>800)
					tw = 800;
				else
					tw = smapX+tw;
				if(smapY+(int)(th)>800)
					th = 600;
				else
					th = smapY+th;
				//g.fillOval(tw, th, 6, 6);
				drawFillOval(screenSize, screenMode,g,tw,th,6,6,io);
			}
			
		}
		//畫小地圖上的障礙
		for(int i = 0;i < data.landscape.size();i++){
			Unit u = data.landscape.get(i);
			switch(u.getType())
			{
				
				case 0:
					break;
			}
			g.setColor(new Color(0,128,0));
			int tw = (int)(smapW*(((u.getX()/50.0))/100));
			int th = (int)(smapH*(((u.getY()/50.0))/100));
			if(smapX+(int)(tw)>800)
				tw = 800;
			else
				tw = smapX+tw;
			if(smapY+(int)(th)>800)
				th = 600;
			else
				th = smapY+th;
			//g.fillOval(tw, th, 6, 6);
			drawFillRectangle(screenSize, screenMode,g,tw,th,2,2,io);
		}
	}
	//更新物件
	public void renewObject(){
		switch(nowView){
			case 1:
				//cheat
				if(data.highRecover)
					unit.recovery((int)unit.getHPmax(), (int)unit.getMPmax());
//				檢查子彈是否打中單位
				for(int i = 0;i < bullets.size();i++)
				{
					Unit u = bullets.get(i);
					if(u!=null){

						if(u.getTrace()!=null)
						{
							u.setTx(u.getTrace().getX()+u.getTrace().getWidth()/2);
							u.setTy(u.getTrace().getY()+u.getTrace().getHeight()/2);
						}
						u.move();
						for(int j = 0;j < units.size();j++)
						{
							//判斷是敵人
							Unit e = units.get(j);//enemy
							if(e!=null)
							if(u.getTeam()!=e.getTeam())
							{
								int dx = (u.getX()+u.getWidth()/2)-(e.getX()+e.getWidth()/2);
								int dy = (u.getY()+u.getHeight()/2)-(e.getY()+e.getHeight()/2);
								//System.out.println(dx*dx+dy*dy);
								
								if(dx*dx+dy*dy<=200)
								{
									//擊中
									if(!bullets.isEmpty()&&i<bullets.size())
										bullets.remove(i);
									e.damage(u.getHarm(), e.getAtkType(), data.gameData.getArmorList());
									e.setBeAtkId(u.getId());
									//e.setHP(e.getHP()-u.getHarm());
									
								}
							}
						}
					}
				}
				try{
//					更新單位狀態
					for(int i = 0;i < units.size();i++)
					{
						Unit u = units.get(i);
						if(u!=null){

							//更新單位能力值
							u.adjust(data);
							//更新單位AI
							data.ai.runAI(u, data);
							//更新路徑
							if(!u.path.isEmpty()){
								Block b = u.path.get(u.path.size()-1);
								//System.out.println(	b.x+","+b.y);
								if(u.isClicked(b.x*50+25, b.y*50+25)){
									u.nextPath=true;
								}else{
									u.nextPath=false;
								}
								if(u.nextPath){
									//System.out.println("走下一個");
									u.path.remove(u.path.size()-1);
								}
								else{
									
									u.inst(b.x*50+25, b.y*50+25);
								}
							}
							
							
//							檢查單位是否進入陷阱偵測範圍
							for(int j = 0;j < data.traps.size();j++){
								Trap t = data.traps.get(j);
								if(u.getTeam()!=t.getTeam())
								{
									int dx = (u.getX()+u.getWidth()/2)-(t.getX()+t.getWidth()/2);
									int dy = (u.getY()+u.getHeight()/2)-(t.getY()+t.getHeight()/2);
									
									if(dx*dx+dy*dy<=10000)
									{
										data.skillData.bomb(t);
										//踩到
										if(!data.traps.isEmpty()&&j<data.traps.size())
											data.traps.remove(j);
										u.damage(100, 0, data.gameData.getArmorList());
										u.setBeAtkId(t.getId());
										AePlayWave se = new AePlayWave("./sound/effect/bomb.wav");
										se.start();
									}
								}
							}
							
							//充填彈藥
							u.reload();
							//偵測週圍敵人
							
							//有單位生命值小於等於零即死亡
							if((int)u.getHP()<1&&!unit.isDead()){
								u.setDead(true);
								u.setMP(0);
								if(u.getType()==1){//英雄死亡音效
									if(data.inView(u)){
										AePlayWave se = new AePlayWave("./sound/unit/"+u.getNum()+"/"+6+".wav");
										se.start();
									}
									//產生暫時墓碑
									final Effect e1 = new Effect();//
									e1.setX1(u.getX()+u.getWidth()-25);
									e1.setY1(u.getY()+u.getHeight()-25);
									e1.setX2(50);
									e1.setY2(50);
									e1.setType(5);
									e1.setUnderUI(true);
									e1.setVisible(true);
									e1.setImage(new ImageIcon("./images/other/英雄死亡.png").getImage());
									e1.setLifeTime(3*u.getLevel()-1);
									e1.setHasLifeTime(true);
									data.effects.add(e1);
								}else if(u.getType()==2){//小兵死亡音效
									if(data.inView(u)){
										AePlayWave bgm = new AePlayWave("./sound/dead/creep/"+u.getNum()+".wav");
										bgm.start();
									}
									
//									產生暫時墓碑
									final Effect e1 = new Effect();//
									e1.setX1(u.getX()+u.getWidth()-15);
									e1.setY1(u.getY()+u.getHeight()-15);
									e1.setX2(30);
									e1.setY2(30);
									e1.setType(5);
									e1.setUnderUI(true);
									e1.setVisible(true);
									e1.setImage(new ImageIcon("./images/other/小兵死亡.gif").getImage());
									e1.setLifeTime(5);
									e1.setHasLifeTime(true);
									data.effects.add(e1);
									
								}else if(u.getType()==3){//建築死亡音效
									if(data.inView(u)){

										AePlayWave bgm = new AePlayWave("./sound/dead/building/"+u.getNum()+".wav");
										bgm.start();
									}
									if(u.getNum()==5){//敵方主堡破壞
										Victory();
									}
								}else{
									if(data.inView(u)){

										AePlayWave bgm = new AePlayWave("./sound/dead/"+u.getNum()+".wav");
										bgm.start();
									}
								}
								if(u.getBeAtkId()==selfID){
									unit.levelUp(u.getExp(),data);
									unit.setMoney(unit.getMoney()+u.getAward());
								}
								if(u.getBeAtkId()==2){
									enemyHero.levelUp(u.getExp(),data);
									enemyHero.setMoney(enemyHero.getMoney()+u.getAward());
								}
								if(u.isRebirth()){//可重生
									//設定死亡狀態
									u.setDead(true);
									//設定讀秒時間
									u.setRebirthTime(3*u.getLevel());
									u.setVisible(false);
									if(u.getTeam()==1){

										u.setX(300);
										u.setY(4700);
									}else{
										u.setX(4700);
										u.setY(959);
									}
									u.setStunned(true);
								}else //否則從遊戲中移除
								units.remove(i);
								
								if(u.getType()==3){//假如是建築被破壞會有爆炸動畫
									data.effectDatas.bomb(u.getX(), u.getY());
								}
							}
							
							
							/*
							//假如有目標在可攻擊範圍內就停止
							//移動中攻擊狀態
							if(u.getMotion()==2){
								//假如有已經選定的攻擊目標
								if(u.isHasAtkObj()){
									//攻擊目標進入範圍就停止移動
									if(u.enemyInRange())
									{
										u.stop();
									}
									//並且攻擊
								}
								//目標已經超出視野
								if(u.atkObjOutOfsight())
									u.stop();
							}*/
							if(units.size()<200)
							if(u.isCopy()){//攻擊會複製單位
								if(u.getCopyCount()>=u.getCopyCountMax()){
									data.skillData.summon(u);
									u.setCopyCount(0);
								}
							}
//							讓每個單位移動

							if(!u.isStunned()){//沒有被暈
								switch(u.getMotion()){
									case 0://靜止
//										沒攻擊才偵查
										if(!u.isAtking())
										u.detect(data,u.getTeam(), units, bullets);
										break;
									case 1://移動					
										//假如有要追蹤的單位就改變目前目的地座標
										if(u.getTrace()!=null)
										{
											u.setTx(u.getTrace().getX()+u.getTrace().getWidth()/2);
											u.setTy(u.getTrace().getY()+u.getTrace().getHeight()/2);
										}
										if(u.getType()==3){
											u.setMotion(0);
										}else{
											boolean collision = false;//偵測碰撞
											for(int j = 0;j < units.size();j++){//建築
												if(units.get(j)!=null)
												if(units.get(j).getType()==3&&i!=j&&u.isCollision(units.get(j))){
													collision = true;
													break;
												}
											}
											for (int j = 0; j < data.landscape.size(); j++) {//地形
												Unit tunit = data.landscape.get(j);
												if(tunit!=null)
												if(u.isCollision(tunit)){
													collision = true;
													break;
												}
												if(collision){
													break;
												}
											}
											/*
											if(u.getY()>4850){//地圖邊界
												collision = true;
											}*/
											if(!collision){
												u.setLastX(u.getX());
												u.setLastY(u.getY());
												u.move();
											}else{
												if(u.getType()==2){//小兵
													u.setMotion(0);
												}else{

													u.setX(u.getLastX());
													u.setY(u.getLastY());
													u.setMotion(0);
												}
											}
										}
										break;
									case 2://移動中攻擊
//										假如有要追蹤的單位就改變目前目的地座標
										if(u.getTrace()!=null)
										{
											u.setTx(u.getTrace().getX()+u.getTrace().getWidth()/2);
											u.setTy(u.getTrace().getY()+u.getTrace().getHeight()/2);
										}
										
										if(!u.retreat){
											//沒撤退
											u.detect(data,u.getTeam(), units, bullets);
											
										}
											
										
										if(u.getMotion()!=0)
										if(u.getType()==3){//是建築
											u.setMotion(0);
										}else{
											boolean collision = false;//偵測碰撞
											/*
											for(int j = 0;j < units.size();j++){//建築
												if(units.get(j)!=null)
												if(units.get(j).getType()==3&&i!=j&&u.isCollision(units.get(j))){
													collision = true;
													break;
												}
											}*/
											for (int j = 0; j < data.landscape.size(); j++) {//地形
												Unit tunit = data.landscape.get(j);
												if(u.isCollision(tunit)){
													collision = true;
													break;
												}
												if(collision){
													break;
												}
											}
											/*
											if(u.getY()>4850){//地圖邊界
												collision = true;
											}*/
											if(!collision){
												u.setLastX(u.getX());
												u.setLastY(u.getY());
												u.atkMove();
											}else{
												if(u.getType()==2){//小兵
													u.setMotion(0);
												}else{
													u.setX(u.getLastX());
													u.setY(u.getLastY());
													u.setMotion(0);
												}
											}
										}
										break;
								}
							}
						}
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(chatButton.isClicked(data.mx,data.my)){
					chatImage = new ImageIcon("images/menu/"+data.camp+"/chat2.png").getImage();
				}else{
					chatImage = new ImageIcon("images/menu/"+data.camp+"/chat.png").getImage();
				}
				//更新游標
				try{
					if(data.waitSkill==0){//沒有等待發動的技能
						boolean overUnit = false;
						for(int i = 0;i < units.size();i++){
							Unit u = units.get(i);
							if(u!=null){
								if(u.isClicked(data.mx+50*data.wi, data.my+50*data.hi)){
									if(unit.getTeam()==u.getTeam()){
										if(u.getId()==selfID){
											data.changeArrow(1);
											overUnit=true;
										}else{
											data.changeArrow(2);
											overUnit=true;
										}
									}else{
										data.changeArrow(3);
										overUnit=true;
									}
									break;
								}
							}
							
						}
						if(!overUnit){
							data.changeArrow(1);
						}
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			break;
		}
		
	}
	//每秒更新
	public void updatePerSecond(){
		new Thread(){
			public void run(){
				while(true){
					try {
						sleep(1000);
						//遊戲時間
						if(data.second<60){
							data.second++;
						}else{
							data.second=0;
							if(data.minute<60){
								data.minute++;
							}else{
								data.minute=0;
								if(data.hour<24){
									data.hour++;
								}else{
									data.hour=0;
								}
							}
						}
						//消除效果
						for(int i = 0;i < data.effects.size();i++){
							Effect e = data.effects.get(i);
							if(e!=null){
								if(e.isHasLifeTime()){
									if(e.getLifeTime()<=0){
										e.setLive(false);
										e.setLifeTime(0);
									}else{
										e.setLifeTime(e.getLifeTime()-1);
									}
								}
							}
						}
						
						//生兵
						data.creatorCount++;
						if(data.creatorCount>=data.creatorCountMax){
							boolean leftDown = true;
							boolean rightUp = true;
							final int leftDownMax = 80;
							final int rightUpMax = 80;
							if(leftDown){
								if(!barrack[0].isDead())
								new Thread(){
									public void run(){
										data.creatorCount = 0;
										//左下角上路
										for(int i = 0;i < 4;i++){
											try {
												
												if(units.size()<leftDownMax){
													geneCreep(60,1,4, data.creator1X, data.creator1Y,1);
													
												}
												sleep(250);
											} catch (InterruptedException e) {
												// TODO 自動產生 catch 區塊
												e.printStackTrace();
											}
										}
										if(units.size()<leftDownMax){
											geneCreep(60,1,5, data.creator1X, data.creator1Y,1);
										}
									}
								}.start();
								if(!barrack[1].isDead())
								new Thread(){
									public void run(){
										data.creatorCount = 0;
//										左下角中路
										for(int i = 0;i < 4;i++){
											try {
												
												if(units.size()<leftDownMax){
													geneCreep(60,2,4, data.creator2X, data.creator2Y,1);
												}
												sleep(250);
											} catch (InterruptedException e) {
												// TODO 自動產生 catch 區塊
												e.printStackTrace();
											}
										}
										if(units.size()<leftDownMax){
											geneCreep(60,2,5, data.creator2X, data.creator2Y,1);
										}
									}
								}.start();
								if(!barrack[2].isDead())
								new Thread(){
									public void run(){
										data.creatorCount = 0;
//										左下角下路
										for(int i = 0;i < 4;i++){
											try {
												if(units.size()<leftDownMax){
													geneCreep(60,3,4, data.creator3X, data.creator3Y,1);
												}
												sleep(250);
											} catch (InterruptedException e) {
												// TODO 自動產生 catch 區塊
												e.printStackTrace();
											}
										}
										if(units.size()<leftDownMax){
											geneCreep(60,3,5, data.creator3X, data.creator3Y,1);
										}
									}
								}.start();
							}
							
							if(rightUp){
								if(!barrack[3].isDead())
								new Thread(){
									public void run(){
										data.creatorCount = 0;
//										右上角上路
										for(int i = 0;i < 4;i++){
											try {
												
												if(units.size()<rightUpMax){
													geneCreep(60,4,6, data.creator4X, data.creator4Y,2);
												}
												sleep(250);
											} catch (InterruptedException e) {
												// TODO 自動產生 catch 區塊
												e.printStackTrace();
											}
										}
										if(units.size()<rightUpMax){
											geneCreep(60,4,7, data.creator4X, data.creator4Y,2);
										}
									}
								}.start();
								if(!barrack[4].isDead())
								new Thread(){
									public void run(){
										data.creatorCount = 0;
//										右上角中路
										for(int i = 0;i < 4;i++){
											try {
												
												if(units.size()<rightUpMax){
													geneCreep(60,5,6, data.creator5X, data.creator5Y,2);
												}
												sleep(250);
											} catch (InterruptedException e) {
												// TODO 自動產生 catch 區塊
												e.printStackTrace();
											}
										}
										if(units.size()<rightUpMax){
											geneCreep(60,5,7, data.creator5X, data.creator5Y,2);
										}
									}
								}.start();
								if(!barrack[5].isDead())
								new Thread(){
									public void run(){
										data.creatorCount = 0;
//										右上角下路
										for(int i = 0;i < 4;i++){
											try {
												
												if(units.size()<rightUpMax){
													geneCreep(60,6,6, data.creator6X, data.creator6Y,2);
												}
												sleep(250);
											} catch (InterruptedException e) {
												// TODO 自動產生 catch 區塊
												e.printStackTrace();
											}
										}
										if(units.size()<rightUpMax){
											geneCreep(60,6,7, data.creator6X, data.creator6Y,2);
										}
										
									}
								}.start();
							}
							
						}
						
						//卡片時間數減少
						data.cardDatas.waitCardTime();

						//溫泉
						for(int i = 0;i < units.size();i++){
							Unit u = units.get(i);
							if(u!=null){
								int x = u.getX();
								int y = u.getY();
								if(u.getTeam()==1&&
										data.ai.leftDown.isClicked(x, y)){
									u.recovery(50, 50);
								}else if(u.getTeam()==2&&
										data.ai.rightUp.isClicked(x, y)){
									u.recovery(50, 50);
								}
							}
						}
						
						for(int i = 0;i < units.size();i++){
							Unit u = units.get(i);
							if(u!=null){
								if(u.isRebirth()){//重生
									if(u.isDead()){
										if(u.getRebirthTime()>0)
										u.setRebirthTime(u.getRebirthTime()-1);
										if(u.getRebirthTime()<=0){
											u.setRebirthTime(0);
											u.setDead(false);
											u.setVisible(true);
											if(u.getTeam()==1){
												u.setX(200);
												u.setY(4800);
											}else{
												u.setX(4800);
												u.setY(200);
											}
											u.setHP(u.getHPmax());
											u.setStunned(false);
											if(u.getType()==1&&
												u.getId()==selfID){//英雄復活音效
												AePlayWave se = new AePlayWave("./sound/unit/"+u.getNum()+"/"+7+".wav");
												se.start();
											}
										}
									}
								}
//								回副CD
								u.recoverCD();
								//回復生命值和魔力
								u.recovery();
								//暈眩回復
								if(u.isStunned())
								u.setStunTime(u.getStunTime()-1);
								if(u.getStunTime()<=0){
									u.setStunned(false);
								}
								//生存時間歸零
								if(u.isHasLiveTime())
								if(u.getLiveTime()>0)
								{
									u.setLiveTime(u.getLiveTime()-1);
									if(u.getLiveTime()<=0)
									units.remove(i);
								}
							}
							
						}
						for(int i = 0;i < bullets.size();i++){
							Unit u = bullets.get(i);
							//生存時間歸零
							if(u.isHasLiveTime()){
								if(u.getLiveTime()>0)
								{
									u.setLiveTime(u.getLiveTime()-1);
									if(u.getLiveTime()<=0)
										bullets.remove(i);
								}
							}
						}
						
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	//小地圖偵測
	public void checkSmap(){
		new Thread() {
			public void run() {
				try {
					while (nowView==1) {
						sleep(100);
						//碰到地圖左邊界
						if(data.mx<5&&data.my>5&&data.my<595)
						{	//地圖可以再往左
							if(data.wi>0)
							{

								data.wi--;
							}
						}
						//碰到地圖右邊界
						if(data.mx>795&&data.mx<800&&data.my>5&&data.my<595)
						{	//地圖可以再往右
							if(data.wi<taWidth-16)
							{

								data.wi++;
							}
						}
						//碰到地圖上邊界
						if(data.mx>5&&data.mx<795&&data.my>0&&data.my<5)
						{	//地圖可以再往上
							if(data.hi>0)
							{

								data.hi--;
							}
						}
						//碰到地圖下邊界
						if(data.mx>5&&data.mx<795&&data.my>595)
						{	//地圖可以再往下
							if(data.hi<taHeight-12)
							{

								data.hi++;
							}
						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	public int colorNormalize(int color){
		if(color>255)
			color = 255;
		else if(color<0)
			color = 0;
		return color;
	}
	public void geneCreep(int liveTime,int ai,int num,int x,int y,int team){
		Unit u = new Unit(400,300);
		if(liveTime>0){
			u.setLiveTime(liveTime);
			u.setHasLiveTime(true);
		}
		u.setAI(ai);
		data.creepDatas.CreateCreep(data.gameData, u, num, x, y, 1, 0, team);
		//u.setHP(50000);
		//u.setHPmax(50000);
		try
		{
			units.add(u);
		}catch(Exception ex){
			
		}
	}
	public void Victory(){
		new Thread(){
			public void run(){
				final Effect e1 = new Effect();
				e1.setType(8);
				e1.setAlpha(0.0);
				e1.setX1(30);
				e1.setY1(150);
				e1.setX2(740);
				e1.setY2(302);
				e1.setVisible(true);
				e1.setLive(true);
				e1.setImage(new ImageIcon("./images/effect/victory.png").getImage());
				data.effects.add(e1);
				while(true){
					if(e1.getAlpha()<1.0){
						e1.setAlpha(e1.getAlpha()+0.05);
					}else{
						break;
					}
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
				}
				//e1.setLive(false);
			}
		}.start();
		
	}
}
