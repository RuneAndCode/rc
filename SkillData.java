import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class SkillData {
	Data data;
	int[] skillType  = new int[4];//1,2,3=直接發動, 開關, 需要點擊目標
	SkillData(){
		
	}
	SkillData(Data data){
		this.data = data;
		skillType = new int[]{1,3,2,3};
	}
	public void activate(Unit unit,int skillNum){
		//CD轉好就可以發動
		if(data.noCD||unit.skillCD[skillNum]>=unit.skillCDmax[skillNum]){
			
			switch(unit.getSkill()[skillNum]){
			case 1: //水夢靈
				unit.skillCD[skillNum]=0;
				if(data.noMP||unit.getMP()>=(100+10*unit.getLevel())){
					if(!data.noMP)
					unit.setMP(unit.getMP()-(100+10*unit.getLevel()));
					skill1(unit,unit.getSkillLevel()[0]);
				}
				//nuclear(unit);
				//laser(unit,3000,Color.BLUE,200,200);
				//danmaku(unit,9,2,4,150,10,15,30);
			break;
			case 2://高壓噴泉
				if(data.noMP||unit.getMP()>=100){
					target();
					data.setWaitSkill(2);
				}
				break;
			case 3://水靈盾
				unit.skillCD[skillNum]=0;
				if(data.noMP||unit.getMP()>=(20*unit.getLevel())){
					if(!data.noMP)
					unit.setMP(unit.getMP()-(20*unit.getLevel()));
					skill3(unit,unit.getSkillLevel()[2]);
				}
				break;
			case 4://潮吹巨浪
				unit.skillCD[skillNum]=0;
				if(data.noMP||unit.getMP()>=(150+10*unit.getLevel())){
					if(!data.noMP)
					unit.setMP(unit.getMP()-(150+10*unit.getLevel()));

					skill4(unit,unit.getSkillLevel()[3]);
				}
				break;
			case 5: danmaku(unit,11,2,4,300,5,10,30);
				break;
			case 6: splitBomb(unit,12,2,3,100,1,60,30);
				break;
			case 7: laser(unit,1000,Color.BLUE,80,10);
				break;
			case 8: nuclear(unit);
				break;
			case 9:
				flowerRecoveer(unit,unit.getLevel());
				break;
			case 10:
				windflower(unit,unit.getLevel());
				break;
			case 11:
				sakura(unit,unit.getLevel());
				break;
			case 12:
				godnessflower(unit,unit.getLevel());
				break;
			case 13:OpenThroat(unit,unit.getLevel());
				break;
			case 14:Prelude(unit,unit.getLevel());
				break;
			case 15:TrebleSolo(unit,unit.getLevel());
				break;
			case 16:Glamorous(unit,unit.getLevel());
				break;
			case 17:
				Fenglianyanfan(unit,unit.getLevel());
				break;
			case 18:
				Zanwhirlwindfiveconsecutive(unit,unit.getLevel());
				break;
			case 19:
				MeteorJianYu(unit,unit.getLevel());
				break;
			case 20:
				WeekcutoffIaido(unit,unit.getLevel());
			}
		}
	}
	//魅力四射
	public void Glamorous(final Unit unit,final int level){
		AePlayWave bgm = new AePlayWave("./sound/effect/song.wav");
		bgm.start();
		playPngEffect(unit,"魅力四射",90,-320,-192,640,384,60,2,1,0);
	}
	//高音Solo
	public void TrebleSolo(final Unit unit,final int level){
		playPngEffect(unit,"高音Solo",35,-100,-100,200,200,60,2,1,0);
		gun(unit,15,2,300,10,-30,30);
		gun(unit,15,2,300,10,0,30);
		gun(unit,15,2,300,10,30,30);
	}
//	前奏
	public void Prelude(final Unit unit,final int level){
		AePlayWave bgm = new AePlayWave("./sound/effect/伊莉雅前奏.wav");
		bgm.start();
		playPngEffect(unit,"前奏2",35,-200,-200,200,200,80,1,1,0);
		playPngEffect(unit,"前奏2",35,-200,0,200,200,80,1,1,0);
		playPngEffect(unit,"前奏2",35,0,-200,200,200,80,1,1,0);
		playPngEffect(unit,"前奏2",35,0,0,200,200,80,1,1,0);
		playPngEffect(unit,"前奏1",35,-100,-100,200,200,80,2,1,0);
	}
	//開嗓
	public void OpenThroat(final Unit unit,final int level){
		playPngEffect(unit,"開嗓",20,-100,-100,200,200,80,2,2,0);
	}
	//周斷居合斬
	public void WeekcutoffIaido(final Unit unit,final int level){
		playPngEffect(unit,"周斷居合斬/1(60ms)",35,-100,-100,200,200,80,2,2,1);
//		右下
		playMovableImage(unit,"./images/effect/周斷居合斬/2/1.png",-50,-50,100,100,1,1,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/2.png",-50,-50,100,100,1,-1,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/3.png",-50,-50,100,100,-1,-1,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/4.png",-50,-50,100,100,-1,1,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/5.png",-50,-50,100,100,-1,0,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/6.png",-50,-50,100,100,1,0,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/7.png",-50,-50,100,100,0,1,2000,
				2,1,0);
		playMovableImage(unit,"./images/effect/周斷居合斬/2/8.png",-50,-50,100,100,0,-1,2000,
				2,1,0);
	}
	//流星劍雨
	public void MeteorJianYu(final Unit unit,final int level){
		playPngEffect(unit,"流星劍雨/1(60ms)",20,-50,10,100,50,60,2,2,1);
		for(int i = 0;i < 5;i++){

			int xOffset = (int)(200*Math.random()-100);
			int yOffset = (int)(200*Math.random()-100);
			playPngEffect(unit,"流星劍雨/2(60ms)",25,xOffset,yOffset,100,100,80,2,2,0);
		}
	}
	//旋風五連斬
	public void Zanwhirlwindfiveconsecutive(final Unit unit,final int level){
		playPngEffect(unit,"旋風五連斬/1(60ms)",30,-100,-100,200,200,80,2,2,1);
		playGifEffect(unit,"旋風五連斬/2.gif",2000,-25,-75,50,50,2,1,1);
		playGifEffect(unit,"旋風五連斬/2.gif",2000,55,-5,50,50,2,1,1);
		playGifEffect(unit,"旋風五連斬/2.gif",2000,-105,-5,50,50,2,1,1);
		playGifEffect(unit,"旋風五連斬/2.gif",2000,15,75,50,50,2,1,1);
		playGifEffect(unit,"旋風五連斬/2.gif",2000,-65,75,50,50,2,1,1);
	}

	//風鍊燕返
	public void Fenglianyanfan(final Unit unit,final int level){
		AePlayWave bgm = new AePlayWave("./sound/effect/凱瑟第1招.wav");
		bgm.start();
		playPngEffect(unit,"風鍊燕返1",20,-100,-100,200,200,80,2,2,0);
		for (int i = 0; i < data.units.size(); i++) {
			Unit u = data.units.get(i);
			if (u != null)
				if(u.getTeam()!=unit.getTeam())
					if(new Button(unit.getX()-100, unit.getY()-100, 200, 200).isClicked(u
									.getX()
									+ u.getWidth() / 2, u.getY()
									+ u.getHeight() / 2)){
						playPngEffect(u,"風鍊燕返2",10,-50,-50,100,100,80,2,2,0);
					}
		}
		
	}
	//天女散花
	public void godnessflower(final Unit unit,final int level){
		AePlayWave bgm = new AePlayWave("./sound/effect/舞櫻花大絕.wav");
		bgm.start();
		playPngEffect(unit,"天女散花",35,-100,-250,200,200,80,2,2,0);
		danmaku(unit,14,2,4,150,10,15,30);
	}
	//櫻沉香
	public void sakura(final Unit unit,final int level){
		playPngEffect(unit,"櫻沉香",35,-100,-100,200,200,80,2,2,0);
	}
	//風花雪月
	public void windflower(final Unit unit,final int level){
		AePlayWave bgm = new AePlayWave("./sound/effect/舞櫻花風花雪月.wav");
		bgm.start();
		playPngEffect(unit,"風花雪月1",23,-200,-200,200,200,80,1,1,0);
		playPngEffect(unit,"風花雪月1",23,-200,0,200,200,80,1,1,0);
		playPngEffect(unit,"風花雪月1",23,0,-200,200,200,80,1,1,0);
		playPngEffect(unit,"風花雪月1",23,0,0,200,200,80,1,1,0);
		playPngEffect(unit,"風花雪月2",25,-100,-100,200,200,80,2,1,0);
	}
	//花之祝福
	public void flowerRecoveer(final Unit unit,final int level){
		final int sx = unit.getX()+unit.getWidth()/2;
		final int sy = unit.getY()+unit.getHeight()/2;
		final Effect e1 = new Effect();
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setX1(sx-200);
		e1.setY1(sy-200);
		e1.setX2(400);
		e1.setY2(400);
		e1.setVisible(true);
		e1.setLive(true);
		data.effects.add(e1);
		
		new Thread(){
			public void run(){
				int n = 1;
				try{
					while(true){
						sleep(80);
						e1.setImage(new ImageIcon("./images/effect/花之祝福/"+n+".png").getImage());
						n++;
						for (int i = 0; i < data.units.size(); i++) {
							Unit u = data.units.get(i);
							if (u != null)
								if(u.getTeam()==unit.getTeam())
									if(new Button(e1.getX1(), e1.getY1(), e1
											.getX2(), e1.getY2()).isClicked(u
													.getX()
													+ u.getWidth() / 2, u.getY()
													+ u.getHeight() / 2))
								u.recovery(2*level,2*level);
						}
						if(n>=20)
							break;
					}	
					e1.setLive(false);
				}catch(Exception ex){
					
				}
			}
		}.start();
	}
	//分裂彈
	public void splitBomb(final Unit unit, final int bulletNum,
			final int radius, final int circleNum, final int interval,
			final int times, final int nextTheta, final int atk) {
		/* 彈幕 */
		/* 發射子彈編號 */
		/* 彈幕發射時距離自身半徑 */
		/* 彈幕從自身前方算起繞一圈的數量1=前方,2=前後,4=十字,8=米字 */
		/* 彈幕發射間隔 */
		/* 彈幕發射次數 */
		/* 下次彈幕距離上次彈幕偏移角度 */
		/* 攻擊力 */
		int x = 0;
		int y = 0;
		int a = unit.getX();// +unit.getWidth()/2;
		int b = unit.getY();// +unit.getHeight()/2;
		double theta = 120;
		switch (unit.getDir()) {
		case 1:
		case 2:
			theta = Math.PI * 3 / 2;// 270
			break;
		case 3:
		case 4:
			break;
		case 5:
		case 6:
			theta = Math.PI / 2;// 90
			break;
		case 7:
		case 8:
			theta = Math.PI;// 180
			break;
		}
		double offset = (2 * Math.PI) / circleNum;
		theta += Math.toRadians(nextTheta);
		BulletData bulletdata = data.bulletDatas.list.get(bulletNum - 1);
		// 算子彈產生的位置
		x = (int) (a + radius * Math.cos(theta));
		y = (int) (b + radius * Math.sin(theta));
		int vx = (int) (bulletdata.getSpeed() * Math.cos(theta));
		int vy = (int) (bulletdata.getSpeed() * Math.sin(theta));
		final Unit bullet1 = new Unit(x, y);
		data.bulletDatas.createBullet(unit, bullet1, bulletdata, vx, vy,
				bulletNum, atk);
		data.bullets.add(bullet1);
		theta += offset;
		x = (int) (a + radius * Math.cos(theta));
		y = (int) (b + radius * Math.sin(theta));
		vx = (int) (bulletdata.getSpeed() * Math.cos(theta));
		vy = (int) (bulletdata.getSpeed() * Math.sin(theta));
		final Unit bullet2 = new Unit(x, y);
		data.bulletDatas.createBullet(unit, bullet2, bulletdata, vx, vy,
				bulletNum, atk);
		data.bullets.add(bullet2);
		theta += offset;
		x = (int) (a + radius * Math.cos(theta));
		y = (int) (b + radius * Math.sin(theta));
		vx = (int) (bulletdata.getSpeed() * Math.cos(theta));
		vy = (int) (bulletdata.getSpeed() * Math.sin(theta));
		final Unit bullet3 = new Unit(x, y);
		data.bulletDatas.createBullet(unit, bullet3, bulletdata, vx, vy,
				bulletNum, atk);
		data.bullets.add(bullet3);
		// 發射分裂彈
		final Effect e1 = new Effect();//
		e1.setX1(bullet1.getX());
		e1.setY1(bullet1.getY());
		e1.setX2(150);
		e1.setY2(150);
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setVisible(true);
		e1.setImage(new ImageIcon("./images/effect/sbomb.gif").getImage());
		e1.setLifeTime(3);
		e1.setHasLifeTime(true);
		data.effects.add(e1);
		final Effect e2 = new Effect();//
		e2.setX1(bullet1.getX());
		e2.setY1(bullet1.getY());
		e2.setX2(150);
		e2.setY2(150);
		e2.setType(5);
		e2.setUnderUI(true);
		e2.setVisible(true);
		e2.setImage(new ImageIcon("./images/effect/sbomb.gif").getImage());
		e2.setLifeTime(3);
		e2.setHasLifeTime(true);
		data.effects.add(e2);
		final Effect e3 = new Effect();//
		e3.setX1(bullet1.getX());
		e3.setY1(bullet1.getY());
		e3.setX2(150);
		e3.setY2(150);
		e3.setType(5);
		e3.setUnderUI(true);
		e3.setVisible(true);
		e3.setImage(new ImageIcon("./images/effect/sbomb.gif").getImage());
		e3.setLifeTime(3);
		e3.setHasLifeTime(true);
		data.effects.add(e3);
		
		new Thread() {
			public void run() {
				int time = 0;
				while (true) {
					e1.setX1(bullet1.getX() + bullet1.getWidth() / 2 - 75);
					e1.setY1(bullet1.getY() + bullet1.getHeight() / 2 - 75);
					e2.setX1(bullet2.getX() + bullet2.getWidth() / 2 - 75);
					e2.setY1(bullet2.getY() + bullet2.getWidth() / 2 - 75);
					e3.setX1(bullet3.getX() + bullet3.getWidth() / 2 - 75);
					e3.setY1(bullet3.getY() + bullet3.getWidth() / 2 - 75);
					for (int i = 0; i < data.units.size(); i++) {
						Unit u = data.units.get(i);
						if (u != null)
							if (u.getTeam() != unit.getTeam()) {
								if (new Button(e1.getX1(), e1.getY1(), e1
										.getX2(), e1.getY2()).isClicked(u
										.getX()
										+ u.getWidth() / 2, u.getY()
										+ u.getHeight() / 2)) {

									if (u.getHP() >= atk)
										u.setHP(u.getHP() - atk);
									else {
										u.setHP(0);
									}
								}
								if (new Button(e2.getX1(), e2.getY1(), e2
										.getX2(), e2.getY2()).isClicked(u
										.getX()
										+ u.getWidth() / 2, u.getY()
										+ u.getHeight() / 2)) {

									if (u.getHP() >= atk)
										u.setHP(u.getHP() - atk);
									else {
										u.setHP(0);
									}
								}
								if (new Button(e3.getX1(), e3.getY1(), e3
										.getX2(), e3.getY2()).isClicked(u
										.getX()
										+ u.getWidth() / 2, u.getY()
										+ u.getHeight() / 2)) {

									if (u.getHP() >= atk)
										u.setHP(u.getHP() - atk);
									else {
										u.setHP(0);
									}
								}
							}
								
					}
					try {
						time+=100;
						sleep(100);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
					if(time>2000)
						break;
				}
				e1.setLive(false);
				e2.setLive(false);
				e3.setLive(false);
				playPngEffect(new Unit(e1.getX1(),e1.getY1(),e1.getX2(),e1.getY2(),unit.getTeam()),
						"爆炸",35,-100,-100,200,200,80,2,2,0);
				playPngEffect(new Unit(e2.getX1(),e2.getY1(),e2.getX2(),e2.getY2(),unit.getTeam()),
						"爆炸",35,-100,-100,200,200,80,2,2,0);
				playPngEffect(new Unit(e3.getX1(),e3.getY1(),e3.getX2(),e3.getY2(),unit.getTeam()),
						"爆炸",35,-100,-100,200,200,80,2,2,0);
			}
		}.start();

	}
	
	// 雷射
	public void laser(final Unit unit,final double time,final Color color,final int width,final double atk){
		/*持續時間*/
		/*顏色*/
		/*寬度*/
		/*傷害*/
		new Thread(){
			public void run(){
				double theta = 0.0;
				int x = 0;
				int y = 0;
				final int sx = unit.getX()+unit.getWidth()/2;
				final int sy = unit.getY()+unit.getHeight()/2;
				final Effect e1 = new Effect();//
				e1.setType(5);
				e1.setUnderUI(true);
				data.effects.add(e1);
				e1.setVisible(true);
				e1.setImage(new ImageIcon("./images/effect/雷射.PNG").getImage());
				e1.setColor(color);
				final int maxX = 0;
				final int maxY = 0;
				final int maxWidth = 0;
				final int maxHeight = 0;
				final Region maxRegion = new Region();
				switch(unit.getDir()){
				case 1:
				case 2:
					//上
					e1.setImage(new ImageIcon("./images/effect/雷射2.PNG").getImage());
					e1.setX1(sx);
					e1.setY1(sy-5000-unit.getHeight()/2);
					e1.setX2(0);
					e1.setY2(5000);
					maxRegion.setX(sx-width/2);
					maxRegion.setY(sy-5000-unit.getHeight()/2);
					maxRegion.setWidth(width);
					maxRegion.setHeight(5000);
					break;
				case 3:
				case 4:
					//右
					e1.setX1(sx+unit.getWidth()/2);
					e1.setY1(sy);
					e1.setX2(5000);
					e1.setY2(0);
					maxRegion.setX(sx+unit.getWidth()/2);
					maxRegion.setY(sy-width/2);
					maxRegion.setWidth(5000);
					maxRegion.setHeight(width);
					break;
				case 5:
				case 6:
					///下
					e1.setImage(new ImageIcon("./images/effect/雷射2.PNG").getImage());
					e1.setX1(sx);
					e1.setY1(sy+unit.getHeight()/2);
					e1.setX2(0);
					e1.setY2(5000);
					maxRegion.setX(sx-width/2);
					maxRegion.setY(sy+unit.getHeight()/2);
					maxRegion.setWidth(width);
					maxRegion.setHeight(5000);
					break;
				case 7:
				case 8:
					//左
					e1.setX1(sx-5000-unit.getWidth()/2);
					e1.setY1(sy);
					e1.setX2(5000);
					e1.setY2(0);
					maxRegion.setX(sx-5000-unit.getWidth()/2);
					maxRegion.setY(sy-width/2);
					maxRegion.setWidth(5000);
					maxRegion.setHeight(width);
					break;
				}
				double n = 0;
				//每一點傷害過幾秒
				int interval = 100;
				int incWidth = width/(int)(time/interval);
				int incHeight = width/(int)(time/interval);
				unit.setStunned(true);
				AePlayWave bgm = new AePlayWave("./sound/effect/laser.wav");
				bgm.start();
				
				while(true){
					try {
						switch(unit.getDir()){
							case 1:
							case 2:
							case 5:
							case 6:
								e1.setX1(e1.getX1()-incWidth/2);
								e1.setX2(e1.getX2()+incWidth);
								break;
							case 3:
							case 4:
							case 7:
							case 8:
								e1.setY1(e1.getY1()-incHeight/2);
								e1.setY2(e1.getY2()+incHeight);
								break;
						}
						for(int i = 0;i < data.units.size();i++){
							Unit u = data.units.get(i);
							if(u!=null)
								if(u.getTeam()!=unit.getTeam())
							if(new Button(e1.getX1(),e1.getY1(),e1.getX2(),e1.getY2()).isClicked(u.getX()+u.getWidth()/2, u.getY()+u.getHeight()/2)){
								
								if(u.getHP()>=atk)
								u.setHP(u.getHP()-atk);
								else{
									u.setHP(0);
								}
							}
						}
						sleep(interval/2);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
					n+=interval/2;
					if(n>=time/2)
						break;
				}
				while(true){
					try {
						switch(unit.getDir()){
							case 1:
							case 2:
							case 5:
							case 6:
								e1.setX1(e1.getX1()+incWidth/2);
								e1.setX2(e1.getX2()-incWidth);
								break;
							case 3:
							case 4:
							case 7:
							case 8:
								e1.setY1(e1.getY1()+incHeight/2);
								e1.setY2(e1.getY2()-incHeight);
								break;
						}
						for(int i = 0;i < data.units.size();i++){
							Unit u = data.units.get(i);
							if(u!=null)
								if(u.getTeam()!=unit.getTeam())
							if(new Button(e1.getX1(),e1.getY1(),e1.getX2(),e1.getY2()).isClicked(u.getX()+u.getWidth()/2, u.getY()+u.getHeight()/2)){
								
								if(u.getHP()>=atk)
								u.setHP(u.getHP()-atk);
								else{
									u.setHP(0);
								}
							}
						}
						sleep(interval/2);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
					n+=interval/2;
					if(n>=time)
						break;
				}
				e1.setLive(false);
				unit.setStunned(false);
			}
		}.start();
	}
	//核彈
	public void nuclear(final Unit u){
		final AePlayWave bgm;
		bgm = new AePlayWave("./sound/effect/nuclear.wav");
		bgm.start();
		//final int lev = level;
		final Step step = new Step();
		step.setNum(1);
		final int sx = u.getX()+u.getWidth()/2;
		final int sy = u.getY()+u.getHeight()/2;
		final Effect e1 = new Effect();//核彈頭
		e1.setType(5);
		data.effects.add(e1);
		e1.setVisible(true);
		e1.setImage(new ImageIcon("./images/effect/核彈.gif").getImage());
		e1.setX1(sx);
		e1.setY1(sy-5000+500-207);
		e1.setX2(33);
		e1.setY2(207);
		e1.setUnderUI(true);
		final Effect e2 = new Effect();//準心
		e2.setType(5);
		data.effects.add(e2);
		e2.setVisible(true);
		e2.setImage(new ImageIcon("./images/effect/鎖定.gif").getImage());
		e2.setX1(sx-96);
		e2.setY1(sy-96);
		e2.setX2(192);
		e2.setY2(192);
		e2.setUnderUI(true);
		new Thread(){
			public void run(){
				int dir = 0;//1,2,3,4 上 右 下 左
				int x = e2.getX1();
				int y = e2.getY1();
				int vy = 5;
				int n = 0;
				try {//Math.abs(x2-tx)<1||Math.abs(y2-ty)<1
					while(true){
						n++;
						e1.setY1(e1.getY1()+vy);
						sleep(10);
						if(n>900)
							break;
					}
					Unit e3u = new Unit(x,y,192,192);
					playPngEffect(e3u,"核彈爆炸",22,-100,-200,400,400,0,0,0,0);
					e2.setLive(false);
					e1.setLive(false);
					step.setNum(2);
					System.out.println("停止");

					
					//傷害
					for(int i = 0;i < data.units.size();i++){
						Unit unit = data.units.get(i);
						if(unit!=null)
						if(new Button(x-300,y-300,600,600).isClicked(unit.getX(), unit.getY())){
							if(unit.getHP()>=300)
							unit.setHP(unit.getHP()-300);
							else{
								unit.setHP(0);
							}
						}
					}
					bgm.stop();
					final AePlayWave bgm2;
					bgm2 = new AePlayWave("./sound/effect/bomb.wav");
					bgm2.start();
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();

		
		
	}
	public void gun(final Unit unit,final int bulletNum,final int radius,
			final int interval,final int times,
			final int thetaOffset,final int atk){
		/*彈幕*/
		/*發射子彈編號*/
		/*彈幕發射時距離自身半徑*/
		/*彈幕從自身前方算起繞一圈的數量1=前方,2=前後,4=十字,8=米字*/
		/*彈幕發射間隔*/
		/*彈幕發射次數*/
		/*下次彈幕距離上次彈幕偏移角度*/
		/*攻擊力*/
		new Thread(){
			public void run(){
				
				for(int i = 0;i < times;i++){//次數
					double theta = 0.0;
					int x = 0;
					int y = 0;
					int a = unit.getX();//+unit.getWidth()/2;
					int b = unit.getY();//+unit.getHeight()/2;
					
					switch(unit.getDir()){
					case 1:
					case 2:theta=Math.PI*3/2;//270
						break;
					case 3:
					case 4:
						break;
					case 5:
					case 6:theta=Math.PI/2;//90
						break;
					case 7:
					case 8:theta=Math.PI;//180
						break;
					}
					theta+=Math.toRadians(thetaOffset);
					
					BulletData bulletdata = data.bulletDatas.list.get(bulletNum-1);
					//算子彈產生的位置
					x = (int)(a+radius*Math.cos(theta));
					y = (int)(b+radius*Math.sin(theta));
					int vx = (int)(bulletdata.getSpeed()*Math.cos(theta));
					int vy = (int)(bulletdata.getSpeed()*Math.sin(theta));
					System.out.println(vx+","+vy);
					Unit bullet = new Unit(x,y);
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
					data.bullets.add(bullet);
					/*彈幕發射間隔*/
					try {
						sleep(interval);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
					
				}
			}
		}.start();
		
	}
	public void danmaku(final Unit unit,final int bulletNum,final int radius,
			final int circleNum,final int interval,final int times,final int nextTheta,final int atk){
		/*彈幕*/
		/*發射子彈編號*/
		/*彈幕發射時距離自身半徑*/
		/*彈幕從自身前方算起繞一圈的數量1=前方,2=前後,4=十字,8=米字*/
		/*彈幕發射間隔*/
		/*彈幕發射次數*/
		/*下次彈幕距離上次彈幕偏移角度*/
		/*攻擊力*/
		new Thread(){
			public void run(){
				double theta = 0.0;
				for(int i = 0;i < times;i++){//次數
					int x = 0;
					int y = 0;
					int a = unit.getX();//+unit.getWidth()/2;
					int b = unit.getY();//+unit.getHeight()/2;
					
					switch(unit.getDir()){
					case 1:
					case 2:theta=Math.PI*3/2;//270
						break;
					case 3:
					case 4:
						break;
					case 5:
					case 6:theta=Math.PI/2;//90
						break;
					case 7:
					case 8:theta=Math.PI;//180
						break;
					}
					double offset = (2 * Math.PI)/circleNum;
					theta+=Math.toRadians(nextTheta);
					for(int j = 0;j < circleNum;j++){//每次彈幕發射的子彈數量

						BulletData bulletdata = data.bulletDatas.list.get(bulletNum-1);
						//算子彈產生的位置
						x = (int)(a+radius*Math.cos(theta));
						y = (int)(b+radius*Math.sin(theta));
						int vx = (int)(bulletdata.getSpeed()*Math.cos(theta));
						int vy = (int)(bulletdata.getSpeed()*Math.sin(theta));
						System.out.println(vx+","+vy);
						Unit bullet = new Unit(x,y);
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
						data.bullets.add(bullet);
						theta+=offset;
						
					}
					/*彈幕發射間隔*/
					try {
						sleep(interval);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
					
				}
			}
		}.start();
		
	}
	public void skill3(final Unit unit,final int level){
		
		Step step = new Step();
		step.setNum(1);
		int sx = unit.getX()+unit.getWidth()/2;
		int sy = unit.getY()+unit.getHeight()/2;
		final Effect e1 = new Effect();
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setX1(sx-96);
		e1.setY1(sy-unit.getHeight()-96);
		e1.setX2(192);
		e1.setY2(192);
		e1.setVisible(true);
		e1.setLive(true);
		data.effects.add(e1);
		
		new Thread(){
			public void run(){
				int n = 1;
				try{
					while(true){
						sleep(60);
						e1.setImage(new ImageIcon("./images/effect/水夢靈/"+n+".png").getImage());
						n++;
						if(n>=48)
							break;
						System.out.println("xx");
					}	
					e1.setLive(false);
					shield(unit,level);
				}catch(Exception ex){
					
				}
			}
		}.start();
	}
	public void skill4(Unit unit,int level){

		wave(unit,level);
	}
	public void wave(Unit u,int level){//潮吹巨浪
		final int lev = level;
		final Step step = new Step();
		step.setNum(1);
		final int sx = u.getX();
		final int sy = u.getY();
		final int tempdir = u.getDir();
		final int team = u.getTeam();
		final Effect e1 = new Effect();
		e1.setType(5);
		data.effects.add(e1);
		e1.setVisible(true);
		
		e1.setX1(sx);
		e1.setY1(sy);
		switch(tempdir){
			case 1:
			case 2:
				e1.setImage(new ImageIcon("./images/effect/技能4 - 潮吹巨浪(上).gif").getImage());
				e1.setX2(96);
				e1.setY2(96);
				break;
			case 5:
			case 6:
				e1.setImage(new ImageIcon("./images/effect/技能4 - 潮吹巨浪(下).gif").getImage());
				e1.setX2(96);
				e1.setY2(96);
				break;
			case 3:
			case 4:
				e1.setImage(new ImageIcon("./images/effect/技能4 - 潮吹巨浪(右).gif").getImage());
				
				e1.setX2(96);
				e1.setY2(96);
				break;
			case 7:
			case 8:
				e1.setImage(new ImageIcon("./images/effect/技能4 - 潮吹巨浪(左).gif").getImage());
				e1.setX2(96);
				e1.setY2(96);
				break;
		}
		new Thread(){
			public void run(){
				int dir = 0;//1,2,3,4 上 右 下 左
				int x = sx;
				int y = sy;
				int vx = 0;
				int vy = 0;
				int v = 2;
				switch(tempdir){
					case 1:
					case 2: vy-=v; dir = 1;
						break;
					case 3:
					case 4: vx+=v; dir = 2;
						break;
					case 5:	
					case 6: vy+=v; dir = 3;
						break;
					case 7:
					case 8: vx-=v; dir = 4;
						break;
				}
				int n = 0;
				try {//Math.abs(x2-tx)<1||Math.abs(y2-ty)<1
					while(true){
						n++;
						e1.setX1(e1.getX1()+vx);
						e1.setY1(e1.getY1()+vy);
						sleep(20);
						if(n>100)
							break;
					}
					e1.setLive(false);
					step.setNum(2);
					System.out.println("停止");
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();
		
//		傷害
		new Thread(){
			public void run(){
				try {
					AePlayWave bgm;
					bgm = new AePlayWave("./sound/effect/芙露雅大絕.wav");
					bgm.start();
					while(step.getNum()!=1){
						sleep(100);
					}
					/*
					//暈眩
					for(int i = 0;i < data.units.size();i++){
						Unit e = data.units.get(i);
						if(e.getTeam()!=team){
							if(e1.getX1()-70<e.getX()&&e.getX()<e1.getX1()+70&&
									e1.getY1()-70<e.getY()&&e.getY()<e1.getY1()+70){
								
							}
						}
					}*/
					while(step.getNum()==1){
						for(int i = 0;i < data.units.size();i++){
							Unit e = data.units.get(i);
							if(e!=null)
							if(e.getTeam()!=team){
								if(e1.getX1()-70<e.getX()&&e.getX()<e1.getX1()+70&&
										e1.getY1()-70<e.getY()&&e.getY()<e1.getY1()+70){
									e.damage(100*lev, 3, data.gameData.getArmorList());
									if(!e.isStunned()){
//										暈眩
										e.setStunned(true);
										e.setStunTime(4);
										//推
										switch(tempdir){
										case 1:
										case 2: e.setY(e.getY()-20);
											break;
										case 3:
										case 4: e.setX(e.getX()+20);
											break;
										case 5:	
										case 6: e.setY(e.getY()+20);
											break;
										case 7:
										case 8: e.setX(e.getX()-20);
											break;
										}
									}
								}
							}
						}
						System.out.println("傷害");
						sleep(200);
						if(step.isStop())
							break;
					}
					step.setStop(true);
					if(step.isStop()){
						bgm.stop();
					}
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void activateTarget(Unit unit,int skillNum,int tx,int ty){
		switch(skillNum){
		case 1: 
			
		break;
		case 2:
			unit.skillCD[1]=0;
			if(data.noMP||unit.getMP()>=100){
				if(!data.noMP)
					unit.setMP(unit.getMP()-100);
				spring(unit,tx,ty,unit.getSkillLevel()[1]);
			}
			break;
		case 3:
			
			break;
		case 4:
			break;
		}
	}
	public void skill1(final Unit unit,final int level){
		Step step = new Step();
		step.setNum(1);
		int sx = unit.getX()+unit.getWidth()/2;
		int sy = unit.getY()+unit.getHeight()/2;
		final Effect e1 = new Effect();
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setX1(sx-96);
		e1.setY1(sy-unit.getHeight()-96);
		e1.setX2(192);
		e1.setY2(192);
		e1.setVisible(true);
		e1.setLive(true);
		data.effects.add(e1);
		
		new Thread(){
			public void run(){
				int n = 1;
				try{
					while(true){
						sleep(60);
						e1.setImage(new ImageIcon("./images/effect/水靈召喚/"+n+".png").getImage());
						n++;
						if(n>=20)
							break;
						System.out.println("xx");
					}	
					e1.setLive(false);
					for(int i = 0;i < level;i++)
						summon(unit);
				}catch(Exception ex){
					
				}
			}
		}.start();

	}
	public void summon(Unit unit){
		Unit newUnit = new Unit(unit.getX(),unit.getY());
		newUnit.setCopy(true);
		newUnit.setCopyCount(0);
		newUnit.setCopyCountMax(5);
		newUnit.setLiveTime(12);
		newUnit.setHasLiveTime(true);
		double r = Math.random()*10+30;
		double angle = Math.random()*360-180+1;
		int x = (int)(r * Math.cos(Math.toRadians(angle)));
		int y = (int)(r * Math.sin(Math.toRadians(angle)));
		
		data.creepDatas.CreateCreep(data.gameData,newUnit,2,unit.getX()+unit.getWidth()/2+x,unit.getY()+unit.getHeight()/2+y,1,unit.getId(),1);
		data.units.add(newUnit);
	}
	public void shield(Unit unit,int level){
		
		AePlayWave bgm;
		bgm = new AePlayWave("./sound/effect/3.wav");
		bgm.start();
		if(unit.getImageNum()==2){
			unit.setImageNum(70);
			unit.setShield(0.2);
			unit.setShieldhpPerMp(1+0.2*level);
		}else{
			unit.setImageNum(2);
			unit.setShield(0.0);
			unit.setShieldhpPerMp(1);
		}
	}
	public void target(){
		data.changeArrow(4);
		
	}
	public void spring(Unit unit,int x,int y,int level){
		final int lev = level;
		final Unit u = unit;
		
		final int team = unit.getTeam();
		System.out.println("向著"+x+","+y);
		System.out.println("發動第"+data.waitSkill+"種技能");
		data.changeArrow(1);
		data.waitSkill = 0;
		final Step step = new Step();
		//噴出水柱
		step.setNum(1);
		final Effect e1 = new Effect();
		e1.setLive(true);
		e1.setType(1);
		e1.setVisible(true);
		data.effects.add(e1);
		final int sx = unit.getX()+unit.getWidth()/2;
		final int sy = unit.getY()+unit.getHeight()/2;
		final int tx = x;
		final int ty = y;
		//顏色
		e1.setColor(Color.blue);
		//線狀效果
		//data.effectType = 1;
		new Thread(){
			public void run(){
				int n = 0;
				int x1 = sx;
				int y1 = sy;
				int x2 = sx;
				int y2 = sy;
				double Vx = tx - sx;
				double Vy = ty - sy;
				double radians = Math.atan2(Vy,Vx);
				double angle = radians * (180 / Math.PI);
				System.out.println(x2+","+y2+","+tx+","+ty);
				if(angle<0)
					angle+=360;
				if(angle>0)
					angle-=360;
				try {//Math.abs(x2-tx)<1||Math.abs(y2-ty)<1
					while(true){
						e1.setX1(x1);
						e1.setY1(y1);
						e1.setX2(x2);
						e1.setY2(y2);
						n++;
						
						x2+=5*Math.cos(Math.toRadians(angle));
						y2+=5*Math.sin(Math.toRadians(angle));
						sleep(10);
						if(step.isStop())
							break;
						if(n>40)
							break;
						System.out.println(e1.getX1()+","+e1.getY1()+","+e1.getX2()+","+e1.getY2());
					}
					System.out.println("停止");
					step.setNum(2);
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();
		//噴泉
		final Effect e2 = new Effect();
		e2.setType(5);
		data.effects.add(e2);
		e2.setImage(new ImageIcon("./images/effect/高溫噴泉.gif").getImage());
		e2.setX1(x-96);
		e2.setY1(y-150);
		e2.setX2(192);
		e2.setY2(192);
		new Thread(){
			public void run(){
				int n1 = 0;
				int n = 0;
				try{
					while(step.getNum()!=2){
						sleep(100);
					}
					e2.setVisible(true);
					while(step.getNum()==2){
						e2.setY1(e2.getY1()-10);
						e2.setY2(e2.getY2()+10);
						sleep(30);
						e2.setY1(e2.getY1()-10);
						e2.setY2(e2.getY2()+10);
						sleep(30);
						e2.setY1(e2.getY1()+10);
						e2.setY2(e2.getY2()-10);
						sleep(30);
						e2.setY1(e2.getY1()+10);
						e2.setY2(e2.getY2()-10);
						n1++;
						if(n1>4){
							break;
						}
						sleep(30);
					}
					step.setNum(3);
					while(step.getNum()==3){
						e2.setY1(e2.getY1()-1);
						e2.setY2(e2.getY2()+1);
						n++;
						if(n>100){
							break;
						}
						sleep(30);
						if(step.isStop())
							break;
					}
					step.setNum(4);
					System.out.println("噴完");
					e1.setLive(false);
					e2.setLive(false);
					
				}catch(Exception ex){
					
				}
			}
		}.start();
		final AePlayWave bgm;
		bgm = new AePlayWave("./sound/effect/2.wav");
		bgm.start();
		//傷害
		new Thread(){
			public void run(){
				try {
					while(step.getNum()!=3){
						sleep(100);
					}
					while(step.getNum()==3){
						for(int i = 0;i < data.units.size();i++){
							Unit e = data.units.get(i);
							
							if(e!=null)
							if(e.getTeam()!=team){
								
								if(tx-70<e.getX()&&e.getX()<tx+70&&
								   ty-70<e.getY()&&e.getY()<ty+70){
									e.damage(100+50*lev, 3, data.gameData.getArmorList());

									System.out.println("傷害");
									e.setBeAtkId(u.getId());
								}
							}
						}
						sleep(1000);
						if(step.isStop())
							break;
					}
					step.setStop(true);
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();
		//偵測是否移動
		new Thread(){
			public void run(){
				try {
					while(true){
						if(u.getMotion()>0)
							step.setStop(true);
						sleep(10);
						if(step.isStop()){
							bgm.stop();
							break;
						}
					}
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();

	}
	public void levelUp(Unit u){
		final Step step = new Step();
		step.setNum(1);
		final int sx = u.getX();
		final int sy = u.getY();
		final int tempdir = u.getDir();
		final Effect e1 = new Effect();
		e1.setType(6);
		data.effects.add(e1);
		e1.setVisible(true);
		e1.setColor(Color.yellow);
		e1.setString("LevelUp!!");
		e1.setX1(sx);
		e1.setY1(sy);
		e1.setX2(50);
		e1.setY2(20);
		new Thread(){
			public void run(){
				int n = 0;
				try {
					while(true){
						n++;
						e1.setY1(e1.getY1()-1);
						sleep(20);
						if(n>100)
							break;
					}
					e1.setLive(false);
					step.setNum(2);
					System.out.println("停止");
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void bomb(Trap u){
		final Step step = new Step();
		step.setNum(1);
		final int sx = u.getX();
		final int sy = u.getY();
		final Effect e1 = new Effect();
		e1.setType(5);
		data.effects.add(e1);
		e1.setVisible(true);
		e1.setImage(new ImageIcon("./images/effect/4.GIF").getImage());
		e1.setX1(sx);
		e1.setY1(sy);
		e1.setX2(88);
		e1.setY2(80);
		new Thread(){
			public void run(){
				try {
					while(true){
						sleep(1000);
						break;
					}
					e1.setLive(false);
					step.setNum(2);
					System.out.println("停止");
				} catch (InterruptedException e) {
					// TODO 自動產生 catch 區塊
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void playPngEffect(final Unit unit,final String dir,final int numOfImage,
			final int xOffset,final int yOffset,int w,int h,
			final int interval,
			final int effect,final int much,final int follow){
		
		final int sx = unit.getX()+unit.getWidth()/2;
		final int sy = unit.getY()+unit.getHeight()/2;
		final Effect e1 = new Effect();
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setX1(sx+xOffset);
		e1.setY1(sy+yOffset);
		e1.setX2(w);
		e1.setY2(h);
		e1.setVisible(true);
		e1.setLive(true);
		data.effects.add(e1);
		
		new Thread(){
			public void run(){
				int n = 1;
				try{
					while(true){
						if(follow==1){
							e1.setX1(unit.getX()+unit.getWidth()/2+xOffset);
							e1.setY1(unit.getY()+unit.getHeight()/2+yOffset);
						}
						e1.setImage(new ImageIcon("./images/effect/"+dir+"/"+n+".png").getImage());
						//
						if(effect!=0)
						for (int i = 0; i < data.units.size(); i++) {
							Unit u = data.units.get(i);
							if (u != null){
								if(new Button(e1.getX1(), e1.getY1(), e1
										.getX2(), e1.getY2()).isClicked(u
												.getX()
												+ u.getWidth() / 2, u.getY()
												+ u.getHeight() / 2)){
									
									if(effect==1){//治療我方
										if(u.getTeam()==unit.getTeam())
											u.recovery(much,much);
									}else if(effect==2){//傷害敵方
										if(u.getTeam()!=unit.getTeam()){
											u.harm(much, much);
											u.setBeAtkId(unit.getTeam());
										}
									}
								}
							}
									
						}
						n++;
						if(n>=numOfImage)
							break;
						if(interval>0)
							sleep(interval);
					}	
					e1.setLive(false);
				}catch(Exception ex){
					
				}
			}
	
		}.start();
	}

	public void playMovableImage(final Unit unit,final String imagePath,
			final int xOffset,final int yOffset,int w,int h,
			final int vx,final int vy,
			final int time,final int effect,final int much,final int follow){
		final int sx = unit.getX()+unit.getWidth()/2;
		final int sy = unit.getY()+unit.getHeight()/2;
		final Effect e1 = new Effect();
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setX1(sx+xOffset);
		e1.setY1(sy+yOffset);
		e1.setX2(w);
		e1.setY2(h);
		e1.setVisible(true);
		e1.setLive(true);
		e1.setImage(new ImageIcon(imagePath).getImage());
		data.effects.add(e1);
		
		new Thread(){
			public void run(){
				int n = 1;
				try{
					while(true){
						if(follow==1){
							e1.setX1(unit.getX()+unit.getWidth()/2+xOffset);
							e1.setY1(unit.getY()+unit.getHeight()/2+yOffset);
						}
						e1.setX1(e1.getX1()+vx);
						e1.setY1(e1.getY1()+vy);
						
						//
						if(effect!=0)
						for (int i = 0; i < data.units.size(); i++) {
							Unit u = data.units.get(i);
							if (u != null){
								if(new Button(e1.getX1(), e1.getY1(), e1
										.getX2(), e1.getY2()).isClicked(u
												.getX()
												+ u.getWidth() / 2, u.getY()
												+ u.getHeight() / 2)){
									
									if(effect==1){//治療我方
										if(u.getTeam()==unit.getTeam())
											u.recovery(much,much);
									}else if(effect==2){//傷害敵方
										if(u.getTeam()!=unit.getTeam()){

											u.harm(much, much);
											u.setBeAtkId(unit.getTeam());
										}
									}
								}
							}
									
						}
						sleep(10);
						n+=10;
						if(n>=time)
							break;
					}	
					e1.setLive(false);
				}catch(Exception ex){
					
				}
			}
		}.start();
	
		
	}
	public void playGifEffect(final Unit unit,final String dir,final int time,
			final int xOffset,final int yOffset,final int w,final int h,
			final int effect,final int much,final int follow){
		final int sx = unit.getX()+unit.getWidth()/2;
		final int sy = unit.getY()+unit.getHeight()/2;
		final Effect e1 = new Effect();
		e1.setImage(new ImageIcon("./images/effect/"+dir).getImage());
		e1.setType(5);
		e1.setUnderUI(true);
		e1.setX1(sx+xOffset);
		e1.setY1(sy+yOffset);
		e1.setX2(w);
		e1.setY2(h);
		e1.setVisible(true);
		e1.setLive(true);
		data.effects.add(e1);
		
		new Thread(){
			public void run(){
				int n = 1;
				try{
					while(true){
						sleep(10);
						if(follow==1){
							e1.setX1(unit.getX()+unit.getWidth()/2+xOffset);
							e1.setY1(unit.getY()+unit.getHeight()/2+yOffset);
						}
						if(effect!=0)
						for (int i = 0; i < data.units.size(); i++) {
							Unit u = data.units.get(i);
							if (u != null){
								if(new Button(e1.getX1(), e1.getY1(), e1
										.getX2(), e1.getY2()).isClicked(u
												.getX()
												+ u.getWidth() / 2, u.getY()
												+ u.getHeight() / 2)){
									
									if(effect==1){//治療我方
										if(u.getTeam()==unit.getTeam())
											u.recovery(much,much);
									}else if(effect==2){//傷害敵方
										if(u.getTeam()!=unit.getTeam()){
											u.harm(much, much);
											u.setBeAtkId(unit.getTeam());
										}
									}
								}
							}
									
						}
						n+=10;
						if(n>=time)
							break;
					}	
					e1.setLive(false);
				}catch(Exception ex){
					
				}
			}
		}.start();
	}
}
class Step{
	int num;
	boolean stop;
	Step(){
		
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
}