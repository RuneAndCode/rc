import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Unit extends Obj{
	private int type; //1,2,3,4=�^��,�p�L ����CP BOSS,�ؿv,�l�u
	private String name; //���m�W
	private int id;//���a�s�� 0���D���a
	private int AI = 0;//�ĴX��AI 1,2,3,4,5,6,7,8,9,10,11,12,13=
	//����W���p�LAI,���䤤���p�LAI,����U���p�LAI
	//�k��W���p�LAI,�k�䤤���p�LAI,�k��U���p�LAI
	//����W���^��AI,���䤤���^��AI,����U���^��AI
	//�k��W���^��AI,�k�䤤���^��AI,�k��U���^��AI
	//CP��AI
	private int AIstep = 1;//AI���ĴX�Ӫ��A
	private int baseSpeed;//��¦���ʳt��
	private int speed; //���ʳt��
	private int num; //�������ĴX�ӳ��
	private int imageNum = 0; //���Ϯ׽s��
	private int bulletNum = 2; //�ϥβĴX�ؤl�u
	private int level = 1; //����
	private int atk; //�����O
	private int baseAtk; //��¦�����O
	private double atkRate = 2;//��¦�����t��
	private double atkInt = 0.5; //�������j(atkInterval)
	private double atkIntMax = 0.5; //�������j
	private int atkType; //��������//0,1,2,3,4,5,6 = 
	//���q,���,��,�]�k,�V�P,�ޯ�,�^��
	private double HP; //��e�ͩR��
	private double HPmax; //�̤j�ͩR��
	private double MP; //��e�]�k��
	private double MPmax; //�̤j�]�k��
	private double hpRate; //�ͩR�ȦA�Ͳv
	private double mpRate; //�]�k�ȦA�Ͳv
	private double exp1; //��e�g���
	private double exp2 = 60; //��U�@�ũһݸg���
	private double expInc = 10; //�C�ũһݸg��ȼW�[�q
	private int mainAttribute; //0,1,2 = �O,��,��
	private int[] baseAttribute = new int[]{0,0,0};//��¦�O,��,��
	private int[] attribute = new int[]{0,0,0}; //���[�O,��,��
	private double[] AttributeInc = new double[3]; //�O,��,���C�Ŧ����q
	private double baseManaArmor; //��¦�]�k�˥ҭ�
	private double manaArmor; //�]�k�˥ҭ�
	private double baseArmor; //��¦�˥ҭ�
	private double armor; //�˥ҭ�
	private int dir = 0;//��V+�R��
	private int tx = 0; //�ؼЮy��x
	private int ty = 0; //�ؼЮy��y
	private int armorType; //�˥ҧκA0,1,2,3,4,5,6,7 = 
	//���Z��(Unarmored),����(Light),����(Medium),����(Heavy),�^��(Hero),
	//�j��(�ؿv)(Fortified),����(Divine),�L��
	int[] skill = new int[4]; //�ޯ�s��
	int[] skillLevel = new int[4]; //�ޯ൥��
	int[] skillCD = new int[4]; //�ޯ�N�o�ɶ�
	int[] skillCDmax = new int[4]; //�ޯ�N�o�ɶ�
	private int skillpoint = 1; //��e���Χޯ��I��
	private int bigSkill = 0;//�j��
	private int preLearnSkill = 0; //�W�@���ǲ߲ĴX�ӧޯ�
	private int magicballatk; //�k�y����
	private ArrayList state; 
	private ArrayList soulair; //�F��
	private int range = 100; //�����d��
	private int detectRange = 110; //�����d��
	Item[] item = new Item[4]; //���W�˳�
	Card[] card = new Card[6]; //���W�d��
	private int team; //���ݶ���
	private int online; //�O�_�b�u 0=���b,1=�b,-1=�D���a����
	private boolean isChoosed = false;
	private int harm;//�@���l�u���ˮ`
	private boolean canAtk = true;//�O�_�i����
	private int motion = 0;//�欰 0=�R 1=���� 2=���ʤ�����
	private boolean hasLiveTime = false;//���L�ͦs�ɶ�
	private int liveTime = 100;//�ͦs�ɶ�
	private Unit trace=null; //�l�ܥؼ�
	private boolean hasAtkObj = false;//���w�g��w�������ؼ�
	private int exp; //�Q�����κR���g���
	private int award; //�Q�����κR�����y����
	private double shield = 0.0; //���@�n���פ�v
	private double shieldhpPerMp = 0.0; //���@�n�C��פ@�I�ͩR�ȻݯӦh��MP
	private boolean copy = false; //�����@�w���Ʒ|�ƻs
	private int copyCount = 0; //�����ƻs����
	private int copyCountMax = 0; //�����ƻs���ƨ�F���̤j��
	private boolean stunned = false; //�Q�w
	private double stunTime = 0; //�w�t���
	private int money = 0;
	private int beAtkId = 0;//�̫�Q������ID
	private Image headImage;
	private Image deadImage;
	private boolean rebirth = false;
	private int rebirthTime = 0;
	private boolean dead = false;
	private int lastX = 0; //�W�@���٨S�I���쪺�y��
	private int lastY = 0;
	private boolean atking = false;//���b����
	private boolean CPU = false;//�O�_��CPU
	boolean retreat = false;
	boolean nextPath = false;
	Unit targetOfatk = null;
	int yellowCross = 0;
	ArrayList<Block> path = new ArrayList<Block>();
//	A*�M����k
	ArrayList<Block> openList = new ArrayList<Block>();
	ArrayList<Block> closeList = new ArrayList<Block>();
	public void unlock(){
		path = new ArrayList<Block>();
		openList = new ArrayList<Block>();
		closeList = new ArrayList<Block>();
	}
	//A*�M����k
	public void findPath(Terrain[][] ta,int x,int y){
		path = new ArrayList<Block>();
		openList = new ArrayList<Block>();
		closeList = new ArrayList<Block>();
		//��_�l�`�Iindex
		int startX = this.getX()/50;
		int startY = this.getY()/50;
		//����I�`�Iindex
		int endX = x/50;
		int endY = y/50;
		System.out.println("start"+startX+","+startY);
		System.out.println("end"+endX+","+endY);
		//��_�l���I�[�Jopenlist
		openList.add(new Block(startX,startY));
		//��e�`�I
		Block nowBlock;
		//while openlist������
		while(!openList.isEmpty()){
			//��openlist�������̧C���`�I
			int min = openList.get(0).cost;
			int minIndex = 0;
			for(int i = 0;i < openList.size();i++){
				if(openList.get(i).cost<=min){
					min = openList.get(i).cost;
					minIndex = i;
				}
			}
			//��e�`�I=openlist�������̧C���`�I
			nowBlock = openList.get(minIndex);
			//if��e���I=�ؼи`�I
			if(nowBlock.x == endX&&
				nowBlock.y == endY){
				//	���|����
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
				//	���e�`�I���Jcloselist
				openList.remove(minIndex);
				closeList.add(nowBlock);
				//	�˵���e�`�I���C�Ӭ۾F�`�I
				//	for�C�Ӭ۾F�`�I
				int ni = nowBlock.x-1;
				int nj = nowBlock.y-1;
				//		if�ӵ��I���bopenlist��and
				//		�Ӹ`�I���bcloselist��and
				//		�Ӹ`�I���O��ê��then
				//			�N�Ӹ`�I���iopenlist�íp�⦨��
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
						//�]�wparent
						newBlock.parent = nowBlock;
						//����
						int G = 0;
						if(Math.abs(newBlock.x-newBlock.parent.x)+Math.abs(newBlock.y-newBlock.parent.y)==1)
							G=10;
						else if(Math.abs(newBlock.x-newBlock.parent.x)+Math.abs(newBlock.y-newBlock.parent.y)==2)
							G=14;
						int H = (Math.abs(endX-newBlock.x)+Math.abs(endY-newBlock.y))*10;
						newBlock.cost = G+H;//F=G+H
						boolean canWalk = false;
						//���k�W���S��ê
						if(newBlock.x-newBlock.parent.x==1&&newBlock.y-newBlock.parent.y==-1){
							if(ta[nj][ni-1].isCanMove()&&ta[nj+1][ni].isCanMove()){
								canWalk = true;
							}
						}else //�����W���S��ê
							if(newBlock.x-newBlock.parent.x==-1&&newBlock.y-newBlock.parent.y==1){
								if(ta[nj][ni+1].isCanMove()&&ta[nj+1][ni].isCanMove()){
									canWalk = true;
								}
							}
						else //���k�U���S��ê
							if(newBlock.x-newBlock.parent.x==1&&newBlock.y-newBlock.parent.y==1){
								if(ta[nj][ni-1].isCanMove()&&ta[nj-1][ni].isCanMove()){
									canWalk = true;
								}
							}
						else //�����U���S��ê
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
	//�ˬd�`�I�O�_���bopenlist��and
	//		�Ӹ`�I���bcloselist��and
	//		�Ӹ`�I���O��ê��then
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
		
		/*if(getY()+getHeight()>=unit.getY()||//�W
		   getY()<=unit.getY()+unit.getHeight()||//�U
		   getX()+getWidth()>=unit.getX()||//��
		   getX()<=unit.getX()+unit.getWidth())//�k
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
		
		/*if(getY()+getHeight()>=unit.getY()||//�W
		   getY()<=unit.getY()+unit.getHeight()||//�U
		   getX()+getWidth()>=unit.getX()||//��
		   getX()<=unit.getX()+unit.getWidth())//�k
		return true;*/
		int ax0 = getX();
		int ax1 = getX()+getWidth();
		int ay0 = getY();
		int ay1 = getY()+getHeight();
		int bx0 = terrain.getX();
		int bx1 = terrain.getX()+terrain.getWidth();
		int by0 = terrain.getY();
		int by1 = terrain.getY()+terrain.getHeight();;

	    // �M�J�W�����쪺����

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
		//�^�_�ޯ�CD
		for(int i = 0;i < 4;i++){
			if(skillCD[i]<skillCDmax[i]){
				skillCD[i]++;
			}
		}
		//�^�_�D��CD
		for(int i = 0;i < item.length;i++){
			if(item[i]!=null)
			if(item[i].getCDmax()>0&&
					item[i].getCD()>0){
				item[i].setCD(item[i].getCD()-1);
			}
		}
	}
	public void learnSkill(int num){
		if(level<6){//���ťH�U
			if(preLearnSkill!=(num+1)&&num!=3){//�u��ǲ߸򤧫e���@�˪��ޯ�ӥB����Ǥj��
				skillpoint--;
				skillLevel[num]++;
				preLearnSkill = num+1;
				System.out.println("pre"+preLearnSkill);
			}
		}else{
			if(num!=3){//��L���ťu��Ǥj���H�~���ޯ�
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
			if(exp1+exp>=exp2){//�i�H�ɯ�
				data.skillData.levelUp(this);
				if(data.inView(this)){

					AePlayWave bgm;
					bgm = new AePlayWave("./sound/effect/5.wav");
					bgm.start();
				}
				level++;
				if(level<=15)
				skillpoint++;
				exp1 = exp1+exp-exp2;//�h�X�Ӫ����U�@��
				exp2+=expInc;//�һݸg��ȼW�[
				if(level == 25){//���p�ɺ��ū�
					exp1 = exp2;//����������
				}
				if(level==6||level==11||level==16){//6,11,16�ťi�H�Ǥj��
					bigSkill++;//�W�[�j���I��
				}
				adjust(data);
				while(exp1>=exp2&&level<25){
					level++;
					if(level<=15)
					skillpoint++;
					exp1 = exp1-exp2;//�h�X�Ӫ����U�@��
					exp2+=expInc;//�һݸg��ȼW�[
					if(level == 25){//���p�ɺ��ū�
						exp1 = exp2;//����������
					}
					if(level==6||level==11||level==16){//6,11,16�ťi�H�Ǥj��
						bigSkill++;//�W�[�j���I��
					}
					for(int i = 0;i < 3;i++){//�ɯŪ��ɭԤO�Ӵ�����
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
		for(int i = 0;i < 3;i++){//�ɯŪ��ɭԤO�Ӵ�����
			attribute[i]+=AttributeInc[i];
		}
		adjust(data);//�O�Ӵ��W�[
	}
	public void adjust(Data data){
		GameData gameData = data.gameData;
		if(type==1){//���p�O�^��
			//�W�[��
			int incAtk = 0;//�����O
			int incSpeed = 0;//���ʳt��
			double incHPmax = 0; //�ͩR�ȤW��
			double incMPmax = 0; //�]�k�ȤW��
			double incHpRate = 0; //�ͩR�ȦA�Ͳv
			double incMpRate = 0; //�]�k�ȦA�Ͳv
			int[] incAttribute = new int[]{0,0,0}; //�O,��,���ݩ�
			double incManaArmor = 0; //�]�k�˥ҭ�
			double incArmor = 0; //�˥ҭ�
			double incAtkRate = 0; //�����t�ץ[��
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
			//���s�p��ƭ�
			//���[�O�Ӵ�
			attribute[0] = incAttribute[0];
			attribute[1] = incAttribute[1];
			attribute[2] = incAttribute[2];
			//�ͩR�ȳ̤j��
			double hpr = HP/HPmax;
			HPmax = gameData.getHeroHpBase()+
			(baseAttribute[0]+attribute[0])*gameData.getHpPerStr()+
			incHPmax;
			HP = getHPmax()*hpr;
			//�]�k�ȳ̤j��
			double mpr = MP/MPmax;
			MPmax = (baseAttribute[2]+attribute[2])*gameData.getMpPerInt()+
			incMPmax;
			MP = MPmax*mpr;
			//�ͩR�]�k�^�_�t�v
			hpRate = (baseAttribute[0]+attribute[0])*gameData.getHpRatePerStr()*(1+incHpRate);
			mpRate = (baseAttribute[2]+attribute[2])*gameData.getMpRatePerInt()*(1+incMpRate);
			//�����O
			atk = baseAtk+
			(baseAttribute[mainAttribute-1]+attribute[mainAttribute-1])*
			gameData.getAtkPerMainAttribute()+
			incAtk;
			//�˥�
			armor = (int)((baseAttribute[1]+attribute[1])/gameData.getIncArmorNeedAti())+incArmor;
			//�]�k�˥�
			manaArmor = baseManaArmor + incManaArmor;
			//�����t��
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
	//�ˮ`��
	/*
	 * -----�L�˥� ���� ����  ����   �j��  �^��  ����
		���q 100% 100% 150% 100%  70%  100% 5%  
		��� 150% 200% 75%  100%  35%  50%  5% 
		�� 150% 100% 50%  100%  150% 50%  5% 
		�]�k 100% 100% 75%  200%  35%  50%  5% 
		�V�P 100% 100% 100% 100%  100% 100% 100% 
		�ޯ� 100% 100% 100% 100%  100% 70%  5% 
		�^�� 100% 100% 100% 100%  50%  100% 5%  
	 */
	//
	/*
	 * footman�������O�O11(��¦�����O)+1*(1--2)�H����l=12-13(�̫�����O)�C�ӨC�ɤ@�ŧ����O�A�N�h�@���H����l�C 
		3�ŧ�����footman�������O�O11+4*(1--2) =15-19 

		���m�O�p��C 
		���m�O���ĪG�����O: 
		{[(���m�O)*0.06)/(1+0.06*(���m�O)]}*�ĤH�����O=�̲׶ˮ` 

		�p�G���m�O�O�t�ơC 
		�̲׶ˮ`�h= [2-0.94^(�t�����m�O)]*�ĤH�����O 
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
	public void damage(int atk,int atkType,double[][] armorList){//�ˮ`�p��
		double damage = 0;
		if(atkType==3){//�]�k
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
		//�ˮ`��K
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
	public void reCompute(){//���s�p���O��
		
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
		if(motion==0)//�R
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
		}else if(motion==1)//��
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
			if(this.id>=1&&this.id<=10&&//�O�^�����ܧ����|�ֿn��q
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
			//��Ƹ̭����l�u��ƪ��C��ĴX��
			BulletData bulletdata = data.bulletDatas.list.get(bulletNum-1);
			atkInt=0;
			canAtk=false;
			Unit bullet = new Unit(getX()+getWidth()/2,getY()+getHeight()/2);
			bullet.setWidth(bulletdata.getWidth());//�e��
			bullet.setHeight(bulletdata.getHeight());//����
			bullet.setSpeed(bulletdata.getSpeed());//�t��
			bullet.setAtkType(bulletdata.getAtkType());//�����κA
			//�k�ybulletdata[4]//�����κA
			bullet.setLiveTime(bulletdata.getLifeTime());//�ͦs�ɶ�
			bullet.setNum(bulletNum);
			bullet.setImageNum(bulletNum);
			bullet.setDir(1);
			bullet.setId(id);//���ݪ��a
			bullet.setTeam(team);//���ݶ���
			bullet.setType(5);//�ݩ�l�u
			bullet.setTx(x);//�ؼ�X��
			bullet.setTy(y);//�ؼ�y��
			bullet.setHasLiveTime(true);
			bullet.setHarm(atk+(int)(Math.random()*data.gameData.getAtkDice()+1));//�ˮ`
			bullet.setTrace(e);//�l�ܥؼ�
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
		if(trace==null&&type==5){//�l�u��
			//renewDir(getX()+getVx(),getY()+getVy());
			setX(getX()+getVx());
			setY(getY()+getVy());
		}else{//��L���

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
	
	public void atkMove(){//���ʤ�����
		
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
	//�����g��ĤH
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
				
				if(dx*dx+dy*dy<=detectRange*detectRange)//���ĤH�b�����d��
				{
					
					if(dx*dx+dy*dy<=range*range)//���ĤH�b�����d��
					{
						if(targetOfatk==null)
						targetOfatk = e;//�]��������H
						
						if(type==1&&!CPU||type==3){//�ۤv�O�^���Ϋؿv
							attack(data,id,team,ex,ey,bullets,e);
						}else{
							if(targetOfatk==e){//�W�ӧ����ﹳ�M�ثe�ݨ쪺�۵�

								if(isAtking()){//���b����
									atking = true;
									trace = null;
									motion=0;//���U��
									tx=sx;
									tx=sy;
									attack(data,id,team,ex,ey,bullets,e);
								}else{//�٨S����
									atking = true;
									trace = null;
									motion=0;//���U��
									tx=sx;
									tx=sy;
									attack(data,id,team,ex,ey,bullets,e);
								}
							}
						}
					}else{
						targetOfatk = null;//�S���ĤH�b�d��=�S��������H
						if(type==1&&!CPU){//�O�^��
							
						}else{//�D�^�����
							atking = false;
//							�b�d��
							//�]�w�l�ܥؼ�
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
	//���b��a
	public void stop(){
		tx = getX()+getWidth()/2;
		ty = getY()+getHeight()/2;
		trace = null;
		setMotion(0);
	}
	//�R��u��
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
