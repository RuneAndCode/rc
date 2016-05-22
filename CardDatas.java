import java.awt.Image;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;


public class CardDatas {
	public ArrayList<CardData> list = new ArrayList<CardData>();
	String path;
	ArrayList<Card> card = new ArrayList<Card>();//卡片資料
	ArrayList<Card> deck = new ArrayList<Card>();//牌組
	//ArrayList<Card> hand = new ArrayList<Card>();//手牌
	Card[] hand = new Card[5];
	int deckMax = 80; //牌組上限
	int handMax = 5; //手牌上限
	int nextTimeMax = 3; //下次抽牌時間
	int nextTime = 3;
	int power = 0;
	int powerMax = 10;
	int powerCount = 0;
	int powerCountMax = 1;
	int canDraw = 0;
	Data data;
	CardEffects ce;
	public CardDatas(){
		//initial();
	}
	public CardDatas(Data data,String path){
		this.path = path;
		this.data = data;
		readData();
		ce = new CardEffects(data);
		//initial();
	}
	public void activate(Unit unit,int num){
		if(hand[num].getNum()!=0){
			if(power>=1){
				int effectNum = hand[num].getEffect();
				int type = hand[num].getType();
				int typegroup = hand[num].getTypegroup();
				if(!(unit.getLevel()>hand[num].getLevel()*2)){
					//假如英雄的等級小於卡片限制等級*2則不能發動(卡片最少零級 所以英雄最少要一級才能發動)
					//卡片最多12級 英雄25等級滿等 剛好可以使用
				}else if(effectNum==4&&unit.getItem()[0].getNum()==0){
					//使用鑲嵌卡 左上角的鑲嵌格卻沒有裝備 則不做任何事
				}else{
					data.effectDatas.activate(unit);//表示發動得特效
					System.out.print(type+","+typegroup);
					switch(type){
						case 1: ce.Summon(unit,typegroup);
					break;		
						case 2: ce.Magic(unit,typegroup);
					break;		
						case 3: ce.Inlay(unit,typegroup);
					break;
						case 4: ce.Traps(unit,typegroup);
					break;
					}
					hand[num].setNum(0);
					close(num);
					/*
					
					
					switch(effectNum){
					
						case 1: ce.recover(unit,80);
						break;		
						case 2: ce.putTrap(unit,1);
						break;		
						case 3: ce.summon(unit,3);
						break;
						case 4: ce.equip(unit,1,10);
						break;
					}*/
					//hand[numtemp].setNum(0);
					power-=1;
				}
			}
		}
	}
	
	//使用卡片的關閉動畫
	public int close(int num){
		final AePlayWave bgm;
		bgm = new AePlayWave("./sound/card/open.wav");
		bgm.start();
		Card card = hand[num];
		int x = 372+num*40;
		int y = 551;
		final int numtemp = num;
		final ArrayList<Card> decktemp = deck;
		final Card[] handtemp = hand;
		final Step step = new Step();
		step.setNum(1);
		final Effect e2 = new Effect();
		e2.setType(7);
		e2.setX1(x);
		e2.setY1(y);
		e2.setX2(37);
		e2.setY2(45);
		e2.setVisible(true);
		e2.setLive(true);
		data.effects.add(e2);
		new Thread(){
			public void run(){
				int n = 0;
				int m = 24;
				Image image = new ImageIcon("./images/card/"+data.camp+"/card"+m+"gif").getImage();
				e2.setImage(image);
				try{
					while(m>1){
						sleep(40);
						m--;
						e2.setImage(new ImageIcon("./images/card/"+data.camp+"/card"+m+".gif").getImage());
						n++;
						if(m<1)
							step.setNum(2);
					}	
					e2.setLive(false);
					hand[numtemp].setNum(0);
				}catch(Exception ex){
					
				}
			}
		}.start();
		
		new Thread(){
			public void run(){
				int n = 0;
				try{
					while(step.getNum()!=2){
						sleep(200);
						n++;
					}	
					//step.setNum(3);
					//step.setNum(hand[numtemp].getEffect());
					bgm.stop();
					
				}catch(Exception ex){
					
				}
			}
		}.start();
		x+=40;
		return hand[numtemp].getEffect();
	}
	public void initial(){
		//產生牌組
		for(int i = 1;i <= 12;i++){
			Card newCard = new Card(i);
			CreateCard(newCard,i);
			deck.add(newCard);
		}
		for(int i = 0;i < deckMax-12;i++){
			Card newCard = new Card(1);
			CreateCard(newCard,1);
			deck.add(newCard);
		}
		initialCard();
		//抽五張
		
	}
	//抽牌
	public void draw(int num){
		
		for(int i = 0;i < num;i++){
			final AePlayWave bgm = new AePlayWave("./sound/card/open.wav");
			boolean hasSpace = false;//有空位
			int tempNum = 0;
			for(int j = hand.length-1;j >= 0;j--){
				if(hand[j]!=null){
					if(hand[j].getNum()==0){
						tempNum = j;
						hasSpace=true;
					}
				}
			}
			final int tempNum2 = tempNum;
			if(hasSpace){
				bgm.start();
//				牌組不為空且手牌小於五張可以抽

				int x = 372;
				int y = 551;
				x+=tempNum*40;
				final Step step = new Step();
				step.setNum(1);
				final Effect e1 = new Effect();
				e1.setType(7);
				e1.setX1(x);
				e1.setY1(y);
				e1.setX2(37);
				e1.setY2(45);
				e1.setVisible(true);
				e1.setLive(true);
				data.effects.add(e1);
				new Thread(){
					public void run(){
						int n = 0;
						int m = 1;
						Image image = new ImageIcon("./images/card/"+data.camp+"/card"+m+"gif").getImage();
						e1.setImage(image);
						try{
							while(m<24){
								sleep(40);
								m++;
								e1.setImage(new ImageIcon("./images/card/"+data.camp+"/card"+m+".gif").getImage());
								n++;
								if(m>2)
									step.setNum(2);
							}	
							e1.setLive(false);
						}catch(Exception ex){
							
						}
					}
				}.start();
				//hand.add(deck.remove(0));
				
				final ArrayList<Card> decktemp = deck;
				final Card[] handtemp = hand;
				new Thread(){
					public void run(){
						int n = 0;
						try{
							while(step.getNum()!=2){
								sleep(200);
								n++;
							}	
							Card drawCard = decktemp.remove(0);
							handtemp[tempNum2] = drawCard;
							if(bgm!=null)
							bgm.stop();
						}catch(Exception ex){
							
						}
					}
				}.start();
			}
		}
	}
	public void waitCardTime(){
		if(nextTime>0){
			nextTime--;//減一
		}else{
			nextTime = nextTimeMax;//重新計算
			//抽一張
			draw(1);
		}
	}
	public void readData(){
		File dir = new File(path);
		String[] s = dir.list();
		for(int i = 0;i < s.length;i++){
			File file = new File(path+s[i]);
			CardData cd = new CardData();
			cd.readData(file);
			list.add(cd);
			Collections.sort(list,new DataCompare());//用檔案編號(非檔名)排序
		}
	}
	public void CreateCard(Card card,int num){
		CardData cardData = list.get(num-1);
		card.setName(cardData.getName());
		card.setCompareName(card.getName() );
		card.setLevel(cardData.getLevel());
		card.setType(cardData.getType());
		card.setCost(cardData.getCost());
		card.setEffect(cardData.getEffect());
		card.setTypegroup(cardData.getTypegroup());
		card.setComment(cardData.getComment());
		card.setNum(num);
		card.setImage(new ImageIcon("images/card/"+num+".png").getImage());
	}

	public void initialCard(){
		final AePlayWave bgm;
		bgm = new AePlayWave("./sound/card/up.wav");
		bgm.start();
		final Effect e1 = new Effect();
		e1.setType(7);
		e1.setX1(372);
		e1.setY1(600);
		e1.setX2(37);
		e1.setY2(45);
		e1.setVisible(true);
		e1.setLive(true);
		e1.setImage(new ImageIcon("./images/card/"+data.camp+"/back.png").getImage());
		data.effects.add(e1);
		final Effect e2 = new Effect();
		e2.setType(7);
		e2.setX1(412);
		e2.setY1(600);
		e2.setX2(37);
		e2.setY2(45);
		e2.setVisible(true);
		e2.setImage(new ImageIcon("images/card/"+data.camp+"/back.png").getImage());
		data.effects.add(e2);
		final Effect e3 = new Effect();
		e3.setType(7);
		e3.setX1(452);
		e3.setY1(600);
		e3.setX2(37);
		e3.setY2(45);
		e3.setVisible(true);
		e3.setImage(new ImageIcon("images/card/"+data.camp+"/back.png").getImage());
		data.effects.add(e3);
		final Effect e4 = new Effect();
		e4.setType(7);
		e4.setX1(492);
		e4.setY1(600);
		e4.setX2(37);
		e4.setY2(45);
		e4.setVisible(true);
		e4.setImage(new ImageIcon("images/card/"+data.camp+"/back.png").getImage());
		data.effects.add(e4);
		final Effect e5 = new Effect();
		e5.setType(7);
		e5.setX1(532);
		e5.setY1(600);
		e5.setX2(37);
		e5.setY2(45);
		e5.setVisible(true);
		e5.setImage(new ImageIcon("images/card/"+data.camp+"/back.png").getImage());
		data.effects.add(e5);
		new Thread(){
			public void run(){
				int n1 = 0;
				int n = 0;
				try{
					while(n<49){
						e1.setY1(e1.getY1()-1);
						e2.setY1(e2.getY1()-1);
						e3.setY1(e3.getY1()-1);
						e4.setY1(e4.getY1()-1);
						e5.setY1(e5.getY1()-1);
						sleep(60);
						n++;
					}	
					bgm.interrupt();
					draw5();
					e1.setLive(false);
					e2.setLive(false);
					e3.setLive(false);
					e4.setLive(false);
					e5.setLive(false);
				}catch(Exception ex){
					
				}
			}
		}.start();

		
	}
	public void draw5(){
		
		int x = 372;
		int y = 551;
		final Step step = new Step();
		step.setNum(1);
		final ArrayList<Card> decktemp = deck;
		final Card[] handtemp = hand;
		final AePlayWave bgm;
		bgm = new AePlayWave("./sound/card/open.wav");
		bgm.start();
		for(int i = 0;i < 5;i++){
//			draw2
			//x+=40;
			final Effect e2 = new Effect();
			e2.setType(7);
			e2.setX1(x);
			e2.setY1(y);
			e2.setX2(37);
			e2.setY2(45);
			e2.setVisible(true);
			e2.setLive(true);
			data.effects.add(e2);
			new Thread(){
				public void run(){
					int n = 0;
					int m = 1;
					Image image = new ImageIcon("./images/card/"+data.camp+"/card"+m+"gif").getImage();
					e2.setImage(image);
					try{
						while(m<24){
							sleep(40);
							m++;
							e2.setImage(new ImageIcon("./images/card/"+data.camp+"/card"+m+".gif").getImage());
							n++;
							if(m>2)
								step.setNum(2);
						}	
						e2.setLive(false);
					}catch(Exception ex){
						
					}
				}
			}.start();
			
			new Thread(){
				public void run(){
					int n = 0;
					try{
						while(step.getNum()!=2){
							sleep(200);
							n++;
						}	
						
						
					}catch(Exception ex){
						
					}
				}
			}.start();
			x+=40;
		}
		for(int i = 0;i < 5;i++){
			Card drawCard = decktemp.remove(0);
			handtemp[i] = drawCard;
			if(data.cardDatas.canDraw==0){
				data.cardDatas.canDraw = 1;
				bgm.interrupt();
			}
		}
	}
}