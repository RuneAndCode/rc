import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Unit extends Obj{
	private int type; //1,2,3,4=英雄,小兵 中立CP BOSS,建築,子彈
	private String name; //單位姓名
	private int id;//玩家編號 0為非玩家
	private int AI = 0;//第幾種AI 1,2,3,4,5,6,7,8,9,10,11,12,13=
	//左邊上路小兵AI,左邊中路小兵AI,左邊下路小兵AI
	//右邊上路小兵AI,右邊中路小兵AI,右邊下路小兵AI
	//左邊上路英雄AI,左邊中路英雄AI,左邊下路英雄AI
	//右邊上路英雄AI,右邊中路英雄AI,右邊下路英雄AI
	//CP怪AI
	private int AIstep = 1;//AI的第幾個狀態
	private int baseSpeed;//基礎移動速度
	private int speed; //移動速度
	private int num; //該類型第幾個單位
	private int imageNum = 0; //單位圖案編號
	private int bulletNum = 2; //使用第幾種子彈
	private int level = 1; //等級
	private int atk; //攻擊力
	private int baseAtk; //基礎攻擊力
	private double atkRate = 2;//基礎攻擊速度
	private double atkInt = 0.5; //攻擊間隔(atkInterval)
	private double atkIntMax = 0.5; //攻擊間隔
	private int atkType; //攻擊類型//0,1,2,3,4,5,6 = 
	//普通,穿刺,攻城,魔法,混沌,技能,英雄
	private double HP; //當前生命值
	private double HPmax; //最大生命值
	private double MP; //當前魔法值
	private double MPmax; //最大魔法值
	private double hpRate; //生命值再生率
	private double mpRate; //魔法值再生率
	private double exp1; //當前經驗值
	private double exp2 = 60; //到下一級所需經驗值
	private double expInc = 10; //每級所需經驗值增加量
	private int mainAttribute; //0,1,2 = 力,敏,智
	private int[] baseAttribute = new int[]{0,0,0};//基礎力,敏,智
	private int[] attribute = new int[]{0,0,0}; //附加力,敏,智
	private double[] AttributeInc = new double[3]; //力,敏,智每級成長量
	private double baseManaArmor; //基礎魔法裝甲值
	private double manaArmor; //魔法裝甲值
	private double baseArmor; //基礎裝甲值
	private double armor; //裝甲值
	private int dir = 0;//方向+靜動
	private int tx = 0; //目標座標x
	private int ty = 0; //目標座標y
	private int armorType; //裝甲形態0,1,2,3,4,5,6,7 = 
	//未武裝(Unarmored),輕型(Light),中等(Medium),重型(Heavy),英雄(Hero),
	//強化(建築)(Fortified),神性(Divine),無敵
	int[] skill = new int[4]; //技能編號
	int[] skillLevel = new int[4]; //技能等級
	int[] skillCD = new int[4]; //技能冷卻時間
	int[] skillCDmax = new int[4]; //技能冷卻時間
	private int skillpoint = 1; //當前未用技能點數
	private int bigSkill = 0;//大絕
	private int preLearnSkill = 0; //上一次學習第幾個技能
	private int magicballatk; //法球攻擊
	private ArrayList state; 
	private ArrayList soulair; //靈氣
	private int range = 100; //攻擊範圍
	private int detectRange = 110; //偵測範圍
	Item[] item = new Item[4]; //身上裝備
	Card[] card = new Card[6]; //身上卡片
	private int team; //所屬隊伍
	private int online; //是否在線 0=不在,1=在,-1=非玩家控制
	private boolean isChoosed = false;
	private int harm;//作為子彈的傷害
	private boolean canAtk = true;//是否可攻擊
	private int motion = 0;//行為 0=靜 1=移動 2=移動中攻擊
	private boolean hasLiveTime = false;//有無生存時間
	private int liveTime = 100;//生存時間
	private Unit trace=null; //追蹤目標
	private boolean hasAtkObj = false;//有已經選定的攻擊目標
	private int exp; //被擊殺或摧毀經驗值
	private int award; //被擊殺或摧毀獎勵金錢
	private double shield = 0.0; //防護罩阻擋比率
	private double shieldhpPerMp = 0.0; //防護罩每抵擋一點生命值需耗多少MP
	private boolean copy = false; //攻擊一定次數會複製
	private int copyCount = 0; //攻擊複製次數
	private int copyCountMax = 0; //攻擊複製次數到達的最大值
	private boolean stunned = false; //被暈
	private double stunTime = 0; //暈眩秒數
	private int money = 0;
	private int beAtkId = 0;//最後被攻擊者ID
	private Image headImage;
	private Image deadImage;
	private boolean rebirth = false;
	private int rebirthTime = 0;
	private boolean dead = false;
	private int lastX = 0; //上一個還沒碰撞到的座標
	private int lastY = 0;
	private boolean atking = false;//正在攻擊
	private boolean CPU = false;//是否為CPU
	boolean retreat = false;
	boolean nextPath = false;
	Unit targetOfatk = null;
	int yellowCross = 0;
	ArrayList<Block> path = new ArrayList<Block>();
//	A*尋路算法
	ArrayList<Block> openList = new ArrayList<Block>();
	ArrayList<Block> closeList = new ArrayList<Block>();
	public void unlock(){
		path = new ArrayList<Block>();
		openList = new ArrayList<Block>();
		closeList = new ArrayList<Block>();
	}
	//A*尋路算法
	public void findPath(Terrain[][] ta,int x,int y){
		path = new ArrayList<Block>();
		openList = new ArrayList<Block>();
		closeList = new ArrayList<Block>();
		//找起始節點index
		int startX = this.getX()/50;
		int startY = this.getY()/50;
		//找終點節點index
		int endX = x/50;
		int endY = y/50;
		System.out.println("start"+startX+","+startY);
		System.out.println("end"+endX+","+endY);
		//把起始結點加入openlist
		openList.add(new Block(startX,startY));
		//當前節點
		Block nowBlock;
		//while openlist不為空
		while(!openList.isEmpty()){
			//算openlist中成本最低的節點
			int min = openList.get(0).cost;
			int minIndex = 0;
			for(int i = 0;i < openList.size();i++){
				if(openList.get(i).cost<=min){
					min = openList.get(i).cost;
					minIndex = i;
				}
			}
			//當前節點=openlist中成本最低的節點
			nowBlock = openList.get(minIndex);
			//if當前結點=目標節點
			if(nowBlock.x == endX&&
				nowBlock.y == endY){
				//	路徑完成
				while(nowBlock!=null){
					path.add(nowBlock);
					//System.out.println(	nowBlock.x+","+nowBlock.y);
					nowBlock = nowBlock.parent;
				}
				for(int i =path.size()-1;i >= 0;i--){
					
					System.out.println(	path.get(i).x+","+path.get(i).y);
				}
				break;
			}else{
				//else
				//	把當前節點移入closelist
				openList.remove(minIndex);
				closeList.add(nowBlock);
				//	檢視當前節點的每個相鄰節點
				//	for每個相鄰節點
				int ni = nowBlock.x-1;
				int nj = nowBlock.y-1;
				//		if該結點不在openlist中and
				//		該節點不在closelist中and
				//		該節點不是障礙物then
				//			將該節點移進openlist並計算成本
				for(int i = 0;i < 8;i++){
					if(i<2)
						ni++;
					else if(i<4)
						nj++;
					else if(i<6)
						ni--;
					else if(i<8)
						nj--;
					if(checkBlock(ni,nj,ta)){
						Block newBlock = new Block(ni,nj);
						//設定parent
						newBlock.parent = nowBlock;
						//成本
						int G = 0;
						if(Math.abs(newBlock.x-newBlock.parent.x)+Math.abs(newBlock.y-newBlock.parent.y)==1)
							G=10;
						else if(Math.abs(newBlock.x-newBlock.parent.x)+Math.abs(newBlock.y-newBlock.parent.y)==2)
							G=14;
						int H = (Math.abs(endX-newBlock.x)+Math.abs(endY-newBlock.y))*10;
						newBlock.cost = G+H;//F=G+H
						boolean canWalk = false;
						//往右上走沒障礙
						if(newBlock.x-newBlock.parent.x==1&&newBlock.y-newBlock.parent.y==-1){
							if(ta[nj][ni-1].isCanMove()&&ta[nj+1][ni].isCanMove()){
								canWalk = true;
							}
						}else //往左上走沒障礙
							if(newBlock.x-newBlock.parent.x==-1&&newBlock.y-newBlock.parent.y==1){
								if(ta[nj][ni+1].isCanMove()&&ta[nj+1][ni].isCanMove()){
									canWalk = true;
								}
							}
						else //往右下走沒障礙
							if(newBlock.x-newBlock.parent.x==1&&newBlock.y-newBlock.parent.y==1){
								if(ta[nj][ni-1].isCanMove()&&ta[nj-1][ni].isCanMove()){
									canWalk = true;
								}
							}
						else //往左下走沒障礙
							if(newBlock.x-newBlock.parent.x==-1&&newBlock.y-newBlock.parent.y==1){
								if(ta[nj][ni+1].isCanMove()&&ta[nj-1][ni].isCanMove()){
									canWalk = true;
								}
							}
						if(G==10){
							openList.add(newBlock);
						}
						else if(G==14&&canWalk)
						openList.add(newBlock);
					}
				}
			}
		}
	}
	//檢查節點是否不在openlist中and
	//		該節點不在closelist中and
	//		該節點不是障礙物then
	public boolean checkBlock(int x,int y,Terrain[][] ta){
		boolean notInOpenList = true;
		boolean notInCloseList = true;
		boolean canWalk = false;
		for(int i = 0;i < openList.size();i++){
			if(x==openList.get(i).x&&y==openList.get(i).y)
				notInOpenList = false;
		}
		for(int i = 0;i < closeList.size();i++){
			if(x==closeList.get(i).x&&y==closeList.get(i).y)
				notInCloseList = false;
		}
		if(ta[y][x].isCanMove()){
			canWalk = true;
		}
		if(notInOpenList&&notInCloseList&&canWalk){
			return true;
		}else
			return false;
	}
	public boolean isCollision(Unit unit){
		
		/*if(getY()+getHeight()>=unit.getY()||//上
		   getY()<=unit.getY()+unit.getHeight()||//下
		   getX()+getWidth()>=unit.getX()||//左
		   getX()<=unit.getX()+unit.getWidth())//右
		return true;*/
		int sx = getX()+getWidth()/2;
		int sy = getY()+getHeight()/2;
		int ex = unit.getX()+unit.getWidth()/2;
		int ey = unit.getY()+unit.getHeight()/2;
		int dx = ex-sx;
		int dy = ey-sy;
		int w = (getWidth()+unit.getWidth())/4;
		int h = (getHeight()+unit.getHeight())/4;
		if(dx*dx+dy*dy<=w*w+h*h){
			return true;
		}
		else return false;
	}
	public boolean isCollision(Terrain terrain){
		
		/*if(getY()+getHeight()>=unit.getY()||//上
		   getY()<=unit.getY()+unit.getHeight()||//下
		   getX()+getWidth()>=unit.getX()||//左
		   getX()<=unit.getX()+unit.getWidth())//右
		return true;*/
		int ax0 = getX();
		int ax1 = getX()+getWidth();
		int ay0 = getY();
		int ay1 = getY()+getHeight();
		int bx0 = terrain.getX();
		int bx1 = terrain.getX()+terrain.getWidth();
		int by0 = terrain.getY();
		int by1 = terrain.getY()+terrain.getHeight();;

	    // 套入上面提到的條件式

	    if( ax0 < bx1 && ax1 > bx0 )
	        if( ay0 < by1 && ay1 > by0 ) return true;
	    return false;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void recoverCD(){
		//回復技能CD
		for(int i = 0;i < 4;i++){
			if(skillCD[i]<skillCDmax[i]){
				skillCD[i]++;
			}
		}
		//回復道具CD
		for(int i = 0;i < item.length;i++){
			if(item[i]!=null)
			if(item[i].getCDmax()>0&&
					item[i].getCD()>0){
				item[i].setCD(item[i].getCD()-1);
			}
		}
	}
	public void learnSkill(int num){
		if(level<6){//六級以下
			if(preLearnSkill!=(num+1)&&num!=3){//只能學習跟之前不一樣的技能而且不能學大絕
				skillpoint--;
				skillLevel[num]++;
				preLearnSkill = num+1;
				System.out.println("pre"+preLearnSkill);
			}
		}else{
			if(num!=3){//其他等級只能學大絕以外的技能
				skillpoint--;
				skillLevel[num]++;
				preLearnSkill = num+1;
			}else if(bigSkill>0){
				bigSkill--;
				skillpoint--;
				skillLevel[num]++;
				preLearnSkill = num+1;
			}
		}
	}
	public void levelUp(int exp,Data data){
		if(level<25){
			if(exp1+exp>=exp2){//可以升級
				data.skillData.levelUp(this);
				if(data.inView(this)){

					AePlayWave bgm;
					bgm = new AePlayWave("./sound/effect/5.wav");
					bgm.start();
				}
				level++;
				if(level<=15)
				skillpoint++;
				exp1 = exp1+exp-exp2;//多出來的放到下一級
				exp2+=expInc;//所需經驗值增加
				if(level == 25){//假如升滿級後
					exp1 = exp2;//直接把欄位填滿
				}
				if(level==6||level==11||level==16){//6,11,16級可以學大絕
					bigSkill++;//增加大絕點數
				}
				adjust(data);
				while(exp1>=exp2&&level<25){
					level++;
					if(level<=15)
					skillpoint++;
					exp1 = exp1-exp2;//多出來的放到下一級
					exp2+=expInc;//所需經驗值增加
					if(level == 25){//假如升滿級後
						exp1 = exp2;//直接把欄位填滿
					}
					if(level==6||level==11||level==16){//6,11,16級可以學大絕
						bigSkill++;//增加大絕點數
					}
					for(int i = 0;i < 3;i++){//升級的時候力敏智成長
						attribute[i]+=AttributeInc[i];
					}
					adjust(data);
				}
			}else{
				exp1+=exp;
			}
		}
	}
	public void addAttribute(Data data){
		for(int i = 0;i < 3;i++){//升級的時候力敏智成長
			attribute[i]+=AttributeInc[i];
		}
		adjust(data);//力敏智增加
	}
	public void adjust(Data data){
		GameData gameData = data.gameData;
		if(type==1){//假如是英雄
			//增加值
			int incAtk = 0;//攻擊力
			int incSpeed = 0;//移動速度
			double incHPmax = 0; //生命值上限
			double incMPmax = 0; //魔法值上限
			double incHpRate = 0; //生命值再生率
			double incMpRate = 0; //魔法值再生率
			int[] incAttribute = new int[]{0,0,0}; //力,敏,智屬性
			double incManaArmor = 0; //魔法裝甲值
			double incArmor = 0; //裝甲值
			double incAtkRate = 0; //攻擊速度加成
			for(int i = 0;i < 4;i++){
				if(item[i].getNum()!=0){
					incAtk += item[i].getAtk();
					incSpeed += item[i].getSpeed();
					incHPmax += item[i].getHPmax();
					incMPmax += item[i].getMPmax();
					incHpRate += item[i].getHpRate();
					incMpRate += item[i].getMpRate();
					incAttribute[0] += item[i].getAttribute()[0];
					incAttribute[1] += item[i].getAttribute()[1];
					incAttribute[2] += item[i].getAttribute()[2];
					incMpRate += item[i].getMpRate();
					incManaArmor += item[i].getManaArmor();
					incArmor += item[i].getArmor();
					incAtkRate += item[i].getAtkRate();
				}
			}
			//重新計算數值
			//附加力敏智
			attribute[0] = incAttribute[0];
			attribute[1] = incAttribute[1];
			attribute[2] = incAttribute[2];
			//生命值最大值
			double hpr = HP/HPmax;
			HPmax = gameData.getHeroHpBase()+
			(baseAttribute[0]+attribute[0])*gameData.getHpPerStr()+
			incHPmax;
			HP = getHPmax()*hpr;
			//魔法值最大值
			double mpr = MP/MPmax;
			MPmax = (baseAttribute[2]+attribute[2])*gameData.getMpPerInt()+
			incMPmax;
			MP = MPmax*mpr;
			//生命魔法回復速率
			hpRate = (baseAttribute[0]+attribute[0])*gameData.getHpRatePerStr()*(1+incHpRate);
			mpRate = (baseAttribute[2]+attribute[2])*gameData.getMpRatePerInt()*(1+incMpRate);
			//攻擊力
			atk = baseAtk+
			(baseAttribute[mainAttribute-1]+attribute[mainAttribute-1])*
			gameData.getAtkPerMainAttribute()+
			incAtk;
			//裝甲
			armor = (int)((baseAttribute[1]+attribute[1])/gameData.getIncArmorNeedAti())+incArmor;
			//魔法裝甲
			manaArmor = baseManaArmor + incManaArmor;
			//攻擊速度
			atkIntMax = 1/(atkRate*(1+incAtkRate));
		}
	}
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}
	public int getBulletNum() {
		return bulletNum;
	}
	public void setBulletNum(int bulletNum) {
		this.bulletNum = bulletNum;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int[] getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(int[] skillLevel) {
		this.skillLevel = skillLevel;
	}
	//傷害表
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
	//
	/*
	 * footman的攻擊力是11(基礎攻擊力)+1*(1--2)隨機骰子=12-13(最後攻擊力)。而每升一級攻擊力，就多一個隨機骰子。 
		3級攻擊的footman的攻擊力是11+4*(1--2) =15-19 

		防禦力計算。 
		防禦力的效果公式是: 
		{[(防禦力)*0.06)/(1+0.06*(防禦力)]}*敵人攻擊力=最終傷害 

		如果防禦力是負數。 
		最終傷害則= [2-0.94^(負的防禦力)]*敵人攻擊力 
	 */
	public Unit(){
		int tx = getX()+getWidth()/2;
		int ty = getY()+getHeight()/2;
	}
	public Unit(int x,int y){
		setX(x);
		setY(y);
		int tx = getX()+getWidth()/2;
		int ty = getY()+getHeight()/2;
		lastX = x;
		lastY = y;
	}
	public Unit(int x,int y,int type,int num){
		setX(x);
		setY(y);
		this.num = num;
		this.type = type;
		int tx = getX()+getWidth()/2;
		int ty = getY()+getHeight()/2;
		lastX = x;
		lastY = y;
	}
	public Unit(int x,int y,int w,int h,int team){
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
		setTeam(team);
		int tx = getX()+getWidth()/2;
		int ty = getY()+getHeight()/2;
		lastX = x;
		lastY = y;
	}
	public void damage(int atk,int atkType,double[][] armorList){//傷害計算
		double damage = 0;
		if(atkType==3){//魔法
			/*
			if(manaArmor>=0){
				damage = (int)((manaArmor*0.06)/(1+0.06*manaArmor)*atk);
			}else{
				damage = (int)((2-Math.pow(0.94,-manaArmor))*atk); 
			}*/
			damage = atk - manaArmor;
			damage*=armorList[atkType][armorType];
		}else{
			/*
			if(armor>=0){
				damage = (int)((armor*0.06)/(1+0.06*armor)*atk);
			}else{
				damage = (int)((2-Math.pow(0.94,-armor))*atk); 
			}*/
			damage = atk - armor;
			damage*=armorList[atkType][armorType];
		}
		//傷害抵免
		if(shield>0){
			double costMp = (damage*shield)/shieldhpPerMp;
			damage*=(1-shield);
			MP-=costMp;
		}
		if(HP>0){
			if(damage>1){
				if(HP-damage<=0){
					HP=0;
				}else
				HP-=damage;
			}else{
				HP--;
			}
		}
	}
	public void reCompute(){//重新計算能力值
		
	}
	public void recovery(int hp,int mp ){
		if(HP>=HPmax){
			
		}else if(HP + hp>HPmax){
			HP = HPmax;
		}else
		HP += hp;
		if(MP>=MPmax){
			
		}else if(MP + mp>MPmax){
			MP = MPmax;
		}else
		MP += mp;
	}
	public void harm(int hp,int mp ){
		if(HP - hp < 0 ){
			HP = 0;
		}else
			HP -= hp;
		if(MP - mp < 0){
			MP = 0;
		}else
			MP -= mp;
	}
	public void recovery(){
		if(hpRate>0){
			if(HP>=HPmax){
				
			}else if(HP + hpRate>HPmax){
				HP = HPmax;
			}else
			HP += hpRate;
			if(MP>=MPmax){
				
			}else if(MP + mpRate>MPmax){
				MP = MPmax;
			}else
			MP += mpRate;
		}
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public void paint(Graphics g,ImageObserver io){
		Image image = null;
		switch(type)
		{
			case 0:
				image = new ImageIcon("images/unit/hero/"+num+"/"+dir+".gif").getImage();
				break;
			case 1:
				image = new ImageIcon("images/unit/creep/"+num+"/"+dir+".gif").getImage();
				break;
			case 5:
				image = new ImageIcon("images/unit/bullet/"+num+"/"+dir+".gif").getImage();
				break;
		}
		g.drawImage(image,getX(),getY(),getWidth(),getHeight(),io);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void renewDir(int x,int y){
		int cx = getX()+getWidth()/2;
		int cy = getY()+getHeight()/2;
		double Vx = x - cx;
		double Vy = (300-y) - (300 - cy);
		//System.out.println(Vx+","+Vy);
		double radians = Math.atan2(Vy,Vx);
		//System.out.println("radians"+radians);
		double angle = radians * (180 / Math.PI);
		//System.out.println("theta"+angle);
		if(motion==0)//靜
		{
			if(angle>=45&&angle<135)
			{
				dir = 2;
			}
			if(angle>=-45&&angle<45)
			{
				dir = 4;
			}
			if(angle<-45&&angle>=-135)
			{
				dir = 6;
			}
			if((angle<-135&&angle>=-180)||(angle>135&&angle<=180))
			{
				dir = 8;
			}
		}else if(motion==1)//動
		{
			if(angle>=45&&angle<135)
			{
				dir = 1;
			}
			if(angle>=-45&&angle<45)
			{
				dir = 3;
			}
			if(angle<-45&&angle>=-135)
			{
				dir = 5;
			}
			if((angle<-135&&angle>=-180)||(angle>135&&angle<=180))
			{
				dir = 7;
			}
		}
		
	}
	public void inst(int x,int y){
		motion = 1;
		renewDir(x,y);
		tx = x;
		ty = y;
	}
	public void attack(Data data,int id,int team,int x,int y,ArrayList<Unit> bullets,Unit e){
		if(canAtk)
		{
			if(this.id>=1&&this.id<=10&&//是英雄的話攻擊會累積能量
					data.cardDatas.power<data.cardDatas.powerMax&&
					this.type==1){
				if(data.cardDatas.powerCount<data.cardDatas.powerCountMax){
					//System.out.println("xx"+data.cardDatas.powerCount);
					data.cardDatas.powerCount++;
					if(data.cardDatas.powerCount>=data.cardDatas.powerCountMax){
						data.cardDatas.powerCount = 0;
						data.cardDatas.power++;
					}
				}
			}
			//int bulletNum = num;
			//資料裡面的子彈資料的列表第幾個
			BulletData bulletdata = data.bulletDatas.list.get(bulletNum-1);
			atkInt=0;
			canAtk=false;
			Unit bullet = new Unit(getX()+getWidth()/2,getY()+getHeight()/2);
			bullet.setWidth(bulletdata.getWidth());//寬度
			bullet.setHeight(bulletdata.getHeight());//高度
			bullet.setSpeed(bulletdata.getSpeed());//速度
			bullet.setAtkType(bulletdata.getAtkType());//攻擊形態
			//法球bulletdata[4]//攻擊形態
			bullet.setLiveTime(bulletdata.getLifeTime());//生存時間
			bullet.setNum(bulletNum);
			bullet.setImageNum(bulletNum);
			bullet.setDir(1);
			bullet.setId(id);//所屬玩家
			bullet.setTeam(team);//所屬隊伍
			bullet.setType(5);//屬於子彈
			bullet.setTx(x);//目標X值
			bullet.setTy(y);//目標y值
			bullet.setHasLiveTime(true);
			bullet.setHarm(atk+(int)(Math.random()*data.gameData.getAtkDice()+1));//傷害
			bullet.setTrace(e);//追蹤目標
			bullets.add(bullet);
			if(copy&&copyCount<copyCountMax){
				copyCount++;
			}
			if(data.inView(this)){

				AePlayWave bgm;
				bgm = new AePlayWave("./sound/bullet/"+bulletNum+".wav",0.1);
				bgm.start();
			}
		}
	}
	public void move(){
		if(trace==null&&type==5){//子彈用
			//renewDir(getX()+getVx(),getY()+getVy());
			setX(getX()+getVx());
			setY(getY()+getVy());
		}else{//其他單位

			int cx = getX()+getWidth()/2;
			int cy = getY()+getHeight()/2;
			double Vx = tx - cx;
			double Vy = (300-ty) - (300 - cy);
			double radians = Math.atan2(Vy,Vx);
			double angle = radians * (180 / Math.PI);
			if(angle<0)
				angle+=360;
			
			setVx((int)(speed*Math.cos(Math.toRadians(angle))));
			setVy((int)(speed*Math.sin(Math.toRadians(angle))));
			if(Math.abs(tx-cx)>3||Math.abs(ty-cy)>3)
			{			
				motion = 1;
				setX(getX()+getVx());
				setY(getY()-getVy());
			}else{
				motion = 0;
				renewDir(tx,ty);
			}
		}
	}
	
	public void atkMove(){//移動中攻擊
		
		int sx = getX()+getWidth()/2;
		int sy = getY()+getHeight()/2;
		double Vx = tx - sx;
		double Vy = ty - sy;//(300-ty) - (300 - cy);
		double radians = Math.atan2(Vy,Vx);
		double angle = radians * (180 / Math.PI);
		if(angle<0)
			angle+=360;
		
		setVx((int)(speed*Math.cos(Math.toRadians(angle))));
		setVy((int)(speed*Math.sin(Math.toRadians(angle))));
		if(targetInRange())
		{
			//System.out.print("xyz");
			trace = null;
			motion=0;
			tx=sx;
			tx=sy;
		}
		else{
			if(Math.abs(tx-sx)>3||Math.abs(ty-sy)>3)
			{			
				motion = 2;
				setX(getX()+getVx());
				setY(getY()+getVy());
			}else{
				motion = 0;
				renewDir(tx,ty);
			}
		}
		
	}
	public boolean atkObjOutOfsight(){
		int sx = getX()+getWidth()/2;
		int sy = getY()+getHeight()/2;
		int dx = tx-sx;
		int dy = ty-sy;
		if(dx*dx+dy*dy>detectRange*detectRange)
		{
			
			return true;
		}
		else
			return false;
	}
	public boolean targetInRange(){
		if(trace!=null)
		{
			int ex = trace.getX()+trace.getWidth()/2;
			int ey = trace.getY()+trace.getHeight()/2;
		}
		int sx = getX();//+getWidth()/2;
		int sy = getY();//+getHeight()/2;
		int dx = tx-sx;
		int dy = ty-sy;
		
		if(dx*dx+dy*dy<=range*range)
		{
			return true;
		}
		else
			return false;
	}
	//偵測週圍敵人
	public void detect(Data data,int team,ArrayList<Unit> units,ArrayList<Unit> bullets){
		int sx = getX()+getWidth()/2;
		int sy = getY()+getHeight()/2;
		for(int i = 0;i < units.size();i++)
		{
			if(units.get(i)!=null)
			if(team!=units.get(i).getTeam())
			{
				Unit e = units.get(i);
				int ex = e.getX()+e.getWidth()/2;
				int ey = e.getY()+e.getHeight()/2;
				int dx = ex-sx;
				int dy = ey-sy;
				
				if(dx*dx+dy*dy<=detectRange*detectRange)//有敵人在視野範圍內
				{
					
					if(dx*dx+dy*dy<=range*range)//有敵人在攻擊範圍內
					{
						if(targetOfatk==null)
						targetOfatk = e;//設為攻擊對象
						
						if(type==1&&!CPU||type==3){//自己是英雄或建築
							attack(data,id,team,ex,ey,bullets,e);
						}else{
							if(targetOfatk==e){//上個攻擊對像和目前看到的相等

								if(isAtking()){//正在攻擊
									atking = true;
									trace = null;
									motion=0;//停下來
									tx=sx;
									tx=sy;
									attack(data,id,team,ex,ey,bullets,e);
								}else{//還沒攻擊
									atking = true;
									trace = null;
									motion=0;//停下來
									tx=sx;
									tx=sy;
									attack(data,id,team,ex,ey,bullets,e);
								}
							}
						}
					}else{
						targetOfatk = null;//沒有敵人在範圍內=沒有攻擊對象
						if(type==1&&!CPU){//是英雄
							
						}else{//非英雄單位
							atking = false;
//							在範圍內
							//設定追蹤目標
							trace = e;
							//inst(e.getX(),e.getY());
							//hasAtkObj = true;
							motion=2;
						}
					}
					
				}else{
					//trace = null;
					//motion = 0;
					//tx = sx;
					//ty = sy;
				}
			}
		}
	}
	//停在原地
	public void stop(){
		tx = getX()+getWidth()/2;
		ty = getY()+getHeight()/2;
		trace = null;
		setMotion(0);
	}
	//充填彈藥
	public void reload(){
		if(atkInt>=atkIntMax){
			canAtk = true;
		}else{
			atkInt+=0.01;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public boolean isChoosed() {
		return isChoosed;
	}
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getTx() {
		return tx;
	}
	public void setTx(int tx) {
		this.tx = tx;
	}
	public int getTy() {
		return ty;
	}
	public void setTy(int ty) {
		this.ty = ty;
	}
	public void setHPmax(int pmax) {
		HPmax = pmax;
	}
	public int getHarm() {
		return harm;
	}
	public void setHarm(int harm) {
		this.harm = harm;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public ArrayList getState() {
		return state;
	}
	public void setState(ArrayList state) {
		this.state = state;
	}
	public int getMotion() {
		return motion;
	}
	public void setMotion(int motion) {
		this.motion = motion;
	}
	public int getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}
	public boolean isHasLiveTime() {
		return hasLiveTime;
	}
	public void setHasLiveTime(boolean hasLiveTime) {
		this.hasLiveTime = hasLiveTime;
	}
	public Unit getTrace() {
		return trace;
	}
	public void setTrace(Unit trace) {
		this.trace = trace;
	}
	public boolean isHasAtkObj() {
		return hasAtkObj;
	}
	public void setHasAtkObj(boolean hasAtkObj) {
		this.hasAtkObj = hasAtkObj;
	}
	public int getAtkType() {
		return atkType;
	}
	public void setAtkType(int atkType) {
		this.atkType = atkType;
	}
	public double getArmor() {
		return armor;
	}
	public void setArmor(double armor) {
		this.armor = armor;
	}
	public int getArmorType() {
		return armorType;
	}
	public void setArmorType(int armorType) {
		this.armorType = armorType;
	}
	public double getAtkInt() {
		return atkInt;
	}
	public void setAtkInt(double atkInt) {
		this.atkInt = atkInt;
	}
	public double getAtkIntMax() {
		return atkIntMax;
	}
	public void setAtkIntMax(double atkIntMax) {
		this.atkIntMax = atkIntMax;
	}
	public double[] getAttributeInc() {
		return AttributeInc;
	}
	public void setAttributeInc(double[] attributeInc) {
		AttributeInc = attributeInc;
	}
	public boolean isCanAtk() {
		return canAtk;
	}
	public void setCanAtk(boolean canAtk) {
		this.canAtk = canAtk;
	}
	public Card[] getCard() {
		return card;
	}
	public void setCard(Card[] card) {
		this.card = card;
	}
	public int getDetectRange() {
		return detectRange;
	}
	public void setDetectRange(int detectRange) {
		this.detectRange = detectRange;
	}
	public Item[] getItem() {
		return item;
	}
	public void setItem(Item[] item) {
		this.item = item;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMagicballatk() {
		return magicballatk;
	}
	public void setMagicballatk(int magicballatk) {
		this.magicballatk = magicballatk;
	}
	public double getManaArmor() {
		return manaArmor;
	}
	public void setManaArmor(double manaArmor) {
		this.manaArmor = manaArmor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}

	public int getSkillpoint() {
		return skillpoint;
	}
	public void setSkillpoint(int skillpoint) {
		this.skillpoint = skillpoint;
	}
	public ArrayList getSoulair() {
		return soulair;
	}
	public void setSoulair(ArrayList soulair) {
		this.soulair = soulair;
	}
	public int[] getSkill() {
		return skill;
	}
	public void setSkill(int[] skill) {
		this.skill = skill;
	}
	public int[] getAttribute() {
		return attribute;
	}
	public void setAttribute(int[] attribute) {
		this.attribute = attribute;
	}
	public int getMainAttribute() {
		return mainAttribute;
	}
	public void setMainAttribute(int mainAttribute) {
		this.mainAttribute = mainAttribute;
	}
	public int getBaseAtk() {
		return baseAtk;
	}
	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}
	public double getHP() {
		return HP;
	}
	public void setHP(double hp) {
		HP = hp;
	}
	public double getHPmax() {
		return HPmax;
	}
	public void setHPmax(double pmax) {
		HPmax = pmax;
	}
	public double getHpRate() {
		return hpRate;
	}
	public void setHpRate(double hpRate) {
		this.hpRate = hpRate;
	}
	public double getMP() {
		return MP;
	}
	public void setMP(double mp) {
		MP = mp;
	}
	public double getMPmax() {
		return MPmax;
	}
	public void setMPmax(double pmax) {
		MPmax = pmax;
	}
	public double getMpRate() {
		return mpRate;
	}
	public void setMpRate(double mpRate) {
		this.mpRate = mpRate;
	}
	public int getImageNum() {
		return imageNum;
	}
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}
	public double getShield() {
		return shield;
	}
	public void setShield(double shield) {
		this.shield = shield;
	}
	public double getShieldhpPerMp() {
		return shieldhpPerMp;
	}
	public void setShieldhpPerMp(double shieldhpPerMp) {
		this.shieldhpPerMp = shieldhpPerMp;
	}
	public int[] getSkillCD() {
		return skillCD;
	}
	public void setSkillCD(int[] skillCD) {
		this.skillCD = skillCD;
	}
	public int[] getSkillCDmax() {
		return skillCDmax;
	}
	public void setSkillCDmax(int[] skillCDmax) {
		this.skillCDmax = skillCDmax;
	}
	public boolean isCopy() {
		return copy;
	}
	public void setCopy(boolean copy) {
		this.copy = copy;
	}
	public int getCopyCount() {
		return copyCount;
	}
	public void setCopyCount(int copyCount) {
		this.copyCount = copyCount;
	}
	public int getCopyCountMax() {
		return copyCountMax;
	}
	public void setCopyCountMax(int copyCountMax) {
		this.copyCountMax = copyCountMax;
	}
	public boolean isStunned() {
		return stunned;
	}
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	public double getStunTime() {
		return stunTime;
	}
	public void setStunTime(double stunTime) {
		this.stunTime = stunTime;
	}
	public double getExp1() {
		return exp1;
	}
	public void setExp1(double exp1) {
		this.exp1 = exp1;
	}
	public double getExp2() {
		return exp2;
	}
	public void setExp2(double exp2) {
		this.exp2 = exp2;
	}
	public double getExpInc() {
		return expInc;
	}
	public void setExpInc(double expInc) {
		this.expInc = expInc;
	}
	public int getPreLearnSkill() {
		return preLearnSkill;
	}
	public void setPreLearnSkill(int preLearnSkill) {
		this.preLearnSkill = preLearnSkill;
	}
	public int getBeAtkId() {
		return beAtkId;
	}
	public void setBeAtkId(int beAtkId) {
		this.beAtkId = beAtkId;
	}
	public Image getHeadImage() {
		return headImage;
	}
	public void setHeadImage(Image headImage) {
		this.headImage = headImage;
	}
	public boolean isRebirth() {
		return rebirth;
	}
	public void setRebirth(boolean rebirth) {
		this.rebirth = rebirth;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public int getRebirthTime() {
		return rebirthTime;
	}
	public void setRebirthTime(int rebirthTime) {
		this.rebirthTime = rebirthTime;
	}
	public Image getDeadImage() {
		return deadImage;
	}
	public void setDeadImage(Image deadImage) {
		this.deadImage = deadImage;
	}
	public int getBigSkill() {
		return bigSkill;
	}
	public void setBigSkill(int bigSkill) {
		this.bigSkill = bigSkill;
	}
	public int getLastX() {
		return lastX;
	}
	public void setLastX(int lastX) {
		this.lastX = lastX;
	}
	public int getLastY() {
		return lastY;
	}
	public void setLastY(int lastY) {
		this.lastY = lastY;
	}
	public double getBaseArmor() {
		return baseArmor;
	}
	public void setBaseArmor(double baseArmor) {
		this.baseArmor = baseArmor;
	}
	public int[] getBaseAttribute() {
		return baseAttribute;
	}
	public void setBaseAttribute(int[] baseAttribute) {
		this.baseAttribute = baseAttribute;
	}
	public double getBaseManaArmor() {
		return baseManaArmor;
	}
	public void setBaseManaArmor(double baseManaArmor) {
		this.baseManaArmor = baseManaArmor;
	}
	public int getBaseSpeed() {
		return baseSpeed;
	}
	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}
	public double getAtkRate() {
		return atkRate;
	}
	public void setAtkRate(double atkRate) {
		this.atkRate = atkRate;
	}
	public int getAI() {
		return AI;
	}
	public void setAI(int ai) {
		AI = ai;
	}
	public int getAIstep() {
		return AIstep;
	}
	public void setAIstep(int istep) {
		AIstep = istep;
	}
	public boolean isAtking() {
		return atking;
	}
	public void setAtking(boolean atking) {
		this.atking = atking;
	}
	public boolean isCPU() {
		return CPU;
	}
	public void setCPU(boolean CPU) {
		this.CPU = CPU;
	}
}
