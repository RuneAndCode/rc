import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainMenu extends Spirit{
	Data data;
	View view;
	static Button exitButton = new Button(770,0,30,30,new ImageIcon("images/menu/Button/Leave.png").getImage());
	
	static Button Login = new Button(0,0,250,150,new ImageIcon("images/menu/Login/Login.png").getImage());
	static Button LoginO = new Button(0,0,250,150,new ImageIcon("images/menu/Login/Login_Button_Pointed.png").getImage());
	static Button LoginP = new Button(0,0,250,150,new ImageIcon("images/menu/Login/Login_Button_Pressed.png").getImage());
	
	static Button leftUnlogin = new Button(0,0,240,45,new ImageIcon("images/menu/Login/左上欄_未登入.png").getImage());
	static Button Login_Button = new Button(30,100,71,28,new ImageIcon("images/menu/Button/Login_Button.png").getImage());
	static Button Register_Button = new Button(120,100,71,28,new ImageIcon("images/menu/Button/Register_Button.png").getImage());
	static Button Chat = new Button(0,390,800,210,new ImageIcon("images/menu/Lobby/Chat.png").getImage());
	//static Button Chat = new Button(0,390,800,210,new ImageIcon("images/menu/Lobby/Chat.png").getImage());
	static Button ChatTF = new Button(202,568,516,22,new ImageIcon("images/other/null.gif").getImage());
	static Button SendButton = new Button(721,562,71,28,new ImageIcon("images/menu/Button/Send_Button.png").getImage());
	
	static Button accoTF = new Button(82,42,124,24,new ImageIcon("images/other/null.gif").getImage());
	static Button passTF = new Button(82,72,124,24,new ImageIcon("images/other/null.gif").getImage());

	static Button leftUpBar = new Button(0,0,240,45,new ImageIcon("images/menu/Lobby/左上欄.png").getImage());
	static Button loginOut = new Button(182,3,20,20,new ImageIcon("images/other/null.gif").getImage());
	static Button setup = new Button(202,3,20,20,new ImageIcon("images/other/null.gif").getImage());
	static Button leftUpBarLoginOut = new Button(0,0,240,45,new ImageIcon("images/menu/Lobby/左上欄_logout按下.png").getImage());
	
	static Button gameSetup = new Button(0,0,355,405,new ImageIcon("images/menu/Lobby/Game_Setting.png").getImage());
	static Button leftUpBarSetup = new Button(0,0,240,45,new ImageIcon("images/menu/Lobby/左上欄_setting按下.png").getImage());
	
	static Button leftUpBarCircle = new Button(5,7,27,24,new ImageIcon("images/other/null.gif").getImage());
	static Button leftUpBarDown = new Button(0,0,240,45,new ImageIcon("images/menu/Lobby/左上欄_按下.png").getImage());
	static Button Bar = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar.png").getImage());
	
	static Button BarCard = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar_Card_Pressed.png").getImage());
	static Button BarGame = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar_Game_Pressed.png").getImage());
	static Button BarList = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar_List_Pressed.png").getImage());
	
	static Button showListButton = new Button(38,25,25,25,new ImageIcon("images/other/null.gif").getImage());
	static Button List = new Button(0,0,215,355,new ImageIcon("images/menu/Lobby/List.png").getImage());
	static Button friendList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換好友名單.png").getImage());
	static Button blackList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換黑名單.png").getImage());
	static Button searchFriend = new Button(150,315,24,24,new ImageIcon("images/menu/Button/Search.png").getImage());
	static Button addFriend = new Button(175,315,24,24,new ImageIcon("images/menu/Button/Add.png").getImage());
	
	
	static Button showCardButton = new Button(68,25,25,25,new ImageIcon("images/other/null.gif").getImage());
	static Button EditorBG = new Button(0,0,800,412,new ImageIcon("images/menu/CarDisplay/牌組編輯.png").getImage());
	static Button changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/整體.png").getImage());
	static Button editDeck = new Button(629,366,71,28,new ImageIcon("images/menu/Button/編輯.png").getImage());
	static Button cancelDeck = new Button(719,366,71,28,new ImageIcon("images/menu/Button/離開.png").getImage());
	static Button searchCard = new Button(600,370,24,24,new ImageIcon("images/menu/Button/Search.png").getImage());
	static Button splitBar = new Button(575,0,32,160,new ImageIcon("images/menu/CarDisplay/split.png").getImage());
	static Button splitButton = new Button(575,15,32,32,new ImageIcon("images/menu/CarDisplay/left.png").getImage());
	static Button storeButton = new Button(650,0,124,29,new ImageIcon("images/menu/Button/卡片商店.png").getImage());
	static Button searchCardTextField = new Button(451,371,141,19,new ImageIcon("images/menu/other/null.gif").getImage());
	
	static ArrayList<DeckListButton> deckListButton = new ArrayList<DeckListButton>();//牌組名單按鈕
	static boolean hasChooseDeck = false;
	static int nowChooseDeck = 0;
	static Image dlb0 = new ImageIcon("images/menu/CarDisplay/牌組名單_無焦點.png").getImage();
	static Image dlb1 = new ImageIcon("images/menu/CarDisplay/牌組名單.png").getImage();
	static Image dlb2 = new ImageIcon("images/menu/CarDisplay/牌組名單_view.png").getImage();
	static Image dlb3 = new ImageIcon("images/menu/CarDisplay/牌組名單_edit.png").getImage();
	static Image dlb4 = new ImageIcon("images/menu/CarDisplay/牌組名單_canel.png").getImage();
	static int editMode = 0;//0,1,2,3,4=沒被選到,被選到,瀏覽,編輯,刪除
	
	static Button showGameButton = new Button(102,25,25,25,new ImageIcon("images/other/null.gif").getImage());
	static Button heroPanel = new Button(187,0,425,420,new ImageIcon("images/menu/Hero/Hero_Choose.png").getImage());
	static ArrayList<Button> playerPanel = new ArrayList<Button>();
	static Button[] playerHead = new Button[10];
	static ArrayList<HeroHeadButton> heroHead = new ArrayList<HeroHeadButton>();//69
	static Button goButton = new Button(367,0,65,40,new ImageIcon("images/menu/Hero/Go_Button.png").getImage());
	
	static boolean hasLogin = false;//已登入
	static boolean hasSetup = false;//已進入設定
	static boolean hasBar = false;///已打開工具列
	static boolean showCard = false;///已進入牌組編輯
	static boolean showGame = false;///已進入遊戲
	static boolean showList = false;///已進入朋友名單
	static boolean showFriend = true;
	static int loginBaseY = 0;
	static boolean loginRising = false;
	static boolean loginDowning = false;
	static int chatBaseY = 210;
	static boolean chatRising = false;
	static boolean chatDowning = false;
	static int changeTypeValue = 0;//0,1,2,3,4整體,召喚,魔法,鑲嵌,陷阱
	Image bk = new ImageIcon("images/menu/bk.jpg").getImage();//背景
	
	static boolean gameReady = false;
	static int readyCount = 5;
	public MainMenu(Data data,View view){
		this.data = data;
		this.view = view;
		for(int i = 0;i < 5;i++){
			playerPanel.add(new Button(4,30+i*80,250,50,new ImageIcon("images/menu/Hero/Player_Left.png").getImage()));
			playerHead[i]=new Button(8,34+i*80,40,40,new ImageIcon("images/other/null.gif").getImage());
		}
		for(int i = 0;i < 5;i++){
			playerPanel.add(new Button(546,30+i*80,250,50,new ImageIcon("images/menu/Hero/Player_Right.png").getImage()));
			playerHead[i+5]=new Button(8,34+i*80,40,40,new ImageIcon("images/other/null.gif").getImage());
		}
		
		//playerHead[0] = new Button(8,34,40,40,new ImageIcon("images/head/2.gif").getImage());
		/*for(int i = 0;i < heroHead.length;i++){
			heroHead[i] = new Button(8,0,0,0,new ImageIcon("images/other/null.gif").getImage());
		}*/
		//科技
		heroHead.add(new HeroHeadButton(219,30,40,40,42));
		heroHead.add(new HeroHeadButton(264,30,40,40,46));
		heroHead.add(new HeroHeadButton(310,30,40,40,26));
		heroHead.add(new HeroHeadButton(356,30,40,40,27));
		
		heroHead.add(new HeroHeadButton(196,70,40,40,28));
		heroHead.add(new HeroHeadButton(241,70,40,40,29));
		heroHead.add(new HeroHeadButton(287,70,40,40,30));
		heroHead.add(new HeroHeadButton(333,70,40,40,31));
		
		heroHead.add(new HeroHeadButton(219,110,40,40,32));
		heroHead.add(new HeroHeadButton(264,110,40,40,33));
		heroHead.add(new HeroHeadButton(310,110,40,40,34));
		heroHead.add(new HeroHeadButton(356,110,40,40,35));
		
		heroHead.add(new HeroHeadButton(196,150,40,40,36));
		heroHead.add(new HeroHeadButton(241,150,40,40,37));
		heroHead.add(new HeroHeadButton(287,150,40,40,38));
		heroHead.add(new HeroHeadButton(333,150,40,40,39));
		
		heroHead.add(new HeroHeadButton(219,190,40,40,40));
		heroHead.add(new HeroHeadButton(264,190,40,40,41));
		heroHead.add(new HeroHeadButton(310,190,40,40,24));
		//heroHead.add(new Button(356,190,40,40,new ImageIcon("images/head/3.png").getImage()));
		
		heroHead.add(new HeroHeadButton(196,230,40,40,43));
		heroHead.add(new HeroHeadButton(241,230,40,40,44));
		heroHead.add(new HeroHeadButton(287,230,40,40,45));
		heroHead.add(new HeroHeadButton(333,230,40,40,25));
		
		//魔法
		heroHead.add(new HeroHeadButton(402,30,40,40,17));
		heroHead.add(new HeroHeadButton(448,30,40,40,20));
		heroHead.add(new HeroHeadButton(494,30,40,40,3));
		heroHead.add(new HeroHeadButton(540,30,40,40,4));
		
		heroHead.add(new HeroHeadButton(425,70,40,40,5));
		heroHead.add(new HeroHeadButton(471,70,40,40,6));
		heroHead.add(new HeroHeadButton(517,70,40,40,7));
		heroHead.add(new HeroHeadButton(563,70,40,40,8));
		
		heroHead.add(new HeroHeadButton(402,110,40,40,9));
		heroHead.add(new HeroHeadButton(448,110,40,40,10));
		heroHead.add(new HeroHeadButton(494,110,40,40,11));
		heroHead.add(new HeroHeadButton(540,110,40,40,12));
		
		heroHead.add(new HeroHeadButton(425,150,40,40,13));
		heroHead.add(new HeroHeadButton(471,150,40,40,14));
		heroHead.add(new HeroHeadButton(517,150,40,40,15));
		heroHead.add(new HeroHeadButton(563,150,40,40,16));
		
		//heroHead.add(new Button(402,190,40,40,new ImageIcon("images/head/2.png").getImage()));
		heroHead.add(new HeroHeadButton(448,190,40,40,1));
		heroHead.add(new HeroHeadButton(494,190,40,40,18));
		heroHead.add(new HeroHeadButton(540,190,40,40,19));
		
		heroHead.add(new HeroHeadButton(425,230,40,40,2));
		heroHead.add(new HeroHeadButton(471,230,40,40,21));
		heroHead.add(new HeroHeadButton(517,230,40,40,22));
		heroHead.add(new HeroHeadButton(563,230,40,40,23));
		
		//中立
		heroHead.add(new HeroHeadButton(219,270,40,40,59));
		heroHead.add(new HeroHeadButton(264,270,40,40,48));
		heroHead.add(new HeroHeadButton(310,270,40,40,49));
		heroHead.add(new HeroHeadButton(356,270,40,40,50));
		heroHead.add(new HeroHeadButton(402,270,40,40,51));
		heroHead.add(new HeroHeadButton(448,270,40,40,52));
		heroHead.add(new HeroHeadButton(494,270,40,40,53));
		heroHead.add(new HeroHeadButton(540,270,40,40,54));
		
		heroHead.add( new HeroHeadButton(196,309,40,40,55));
		heroHead.add(new HeroHeadButton(241,309,40,40,56));
		heroHead.add(new HeroHeadButton(287,309,40,40,57));
		heroHead.add(new HeroHeadButton(333,309,40,40,58));
		heroHead.add(new HeroHeadButton(380,309,40,40,47));
		heroHead.add(new HeroHeadButton(425,309,40,40,60));
		heroHead.add(new HeroHeadButton(471,309,40,40,61));
		heroHead.add(new HeroHeadButton(517,309,40,40,62));
		heroHead.add(new HeroHeadButton(563,309,40,40,63));
		
		heroHead.add(new HeroHeadButton(219,349,40,40,64));
		heroHead.add(new HeroHeadButton(264,349,40,40,65));
		heroHead.add(new HeroHeadButton(310,349,40,40,66));
		heroHead.add(new HeroHeadButton(448,349,40,40,67));
		heroHead.add(new HeroHeadButton(494,349,40,40,68));
		heroHead.add(new HeroHeadButton(540,349,40,40,69));
		//產生牌組
		int baseDeckY = 30;
		for(int i = 0;i< 9;i++){
			
			deckListButton.add(new DeckListButton(0,baseDeckY,215,40,new ImageIcon("images/menu/CarDisplay/牌組名單_無焦點.png").getImage()));
			baseDeckY+=40;
		}
		//載入牌組
		
		//透明化
		new Thread(){
			public void run(){
				try{
					while(true){
						if(Login_Button.isAlpha){
							Login_Button.alphaCount++;
							Login_Button.alpha*=0.9;
							if(Login_Button.alphaCount>=10){
								Login_Button.isAlpha=false;
								hasLogin=true;
							}
						}
						sleep(100);
					}
					
				}catch(Exception ex){
					
				}
			}
		}.start();
//		面版升降
		new Thread(){
			public void run(){
				try{
					while(true){
						if(loginRising){
							loginBaseY-=15;
							if(loginBaseY<=-150){
								hasLogin=true;
								loginRising = false;
							}
						}
						if(loginDowning){
							loginBaseY+=15;
							if(loginBaseY>=0){
								hasLogin=false;
								loginDowning = false;
							}
						}
						if(chatRising){
							//System.out.println(chatBaseY);
							chatBaseY-=21;
							if(chatBaseY<=0){
								chatRising = false;
							}
						}
						if(chatDowning){
							chatBaseY+=21;
							if(chatBaseY>=210){
								chatDowning = false;
							}
						}
						sleep(100);
					}
					
				}catch(Exception ex){
					
				}
			}
		}.start();
		//遊戲開始
		final ArrayList<String> chatList = data.chatList;
		final View viewtemp = view;
		new Thread(){
			public void run(){
				try{
					while(true){
						if(gameReady){
							if(chatList.size()<8){
								chatList.remove(0);
								chatList.add(readyCount+"");
							}else{
								chatList.add(readyCount+"");
							}
							readyCount--;
							if(readyCount==0){
								gameReady=false;
								viewtemp.startGame();
							}
							
						}
						sleep(200);
					}
					
				}catch(Exception ex){
					
				}
			}
		}.start();
	}
	
	public void paint(Button bt,Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
 
		drawImageAlpha(bt.alpha,screenSize, screenMode,g,bt.getImage(),bt.getX(),bt.getY(),bt.getWidth(),bt.getHeight(),io);
	}
	public void paint(Button bt,Dimension screenSize,int screenMode,Graphics g,ImageObserver io,int x,int y){
		 
		drawImageAlpha(bt.alpha,screenSize, screenMode,g,bt.getImage(),bt.getX()+x,bt.getY()+y,bt.getWidth(),bt.getHeight(),io);
	}
	//畫主選單
	public void paintMainMenu(Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		//畫背景
		drawImage(screenSize, screenMode,g,bk,0,0,800,600,io);

		//未登入
		if(!hasLogin){

			paint(Login,screenSize,screenMode, g,io,0,loginBaseY);
			paint(Login_Button,screenSize,screenMode, g,io,0,loginBaseY);
			paint(Register_Button,screenSize,screenMode, g,io,0,loginBaseY);
			g.setColor(Color.white);
			Font font = g.getFont();
			g.setFont(new Font(font.getFontName(),font.getStyle(),20));
			drawString(screenSize,screenMode,g,"帳號", 38,loginBaseY+55,io);
			drawString(screenSize,screenMode,g,"密碼", 38,loginBaseY+85,io);
			if(data.dialogMode==1){
				drawString(screenSize,screenMode,g,data.account+"|", 90,loginBaseY+55,io);
				drawString(screenSize,screenMode,g,data.passwordStar, 90,loginBaseY+85,io);
			}else if(data.dialogMode==2){
				drawString(screenSize,screenMode,g,data.account, 90,loginBaseY+55,io);
				drawString(screenSize,screenMode,g,data.passwordStar+"|", 90,loginBaseY+85,io);
			}
			g.setFont(new Font(font.getFontName(),font.getStyle(),font.getSize()));
			paint(leftUnlogin,screenSize,screenMode, g,io);
			paint(Chat,screenSize,screenMode, g,io,0,chatBaseY);
			paint(SendButton,screenSize,screenMode, g,io,0,chatBaseY);
		}else{//已登入
			paint(Login,screenSize,screenMode, g,io,0,loginBaseY);
			paint(Login_Button,screenSize,screenMode, g,io,0,loginBaseY);
			paint(Register_Button,screenSize,screenMode, g,io,0,loginBaseY);
			
			if(hasSetup){//設定
				paint(SendButton,screenSize,screenMode, g,io);
				paint(gameSetup,screenSize,screenMode, g,io);
				paint(leftUpBarSetup,screenSize,screenMode, g,io);
			}else if(hasBar){//工具列
				paint(Bar,screenSize,screenMode, g,io);
				paint(leftUpBarDown,screenSize,screenMode, g,io);
			}else{
				if(showList){
					paint(List,screenSize,screenMode, g,io);
					if(showFriend){
						paint(blackList,screenSize,screenMode, g,io);
					}else{
						paint(friendList,screenSize,screenMode, g,io);
					}
					paint(searchFriend,screenSize,screenMode, g,io);
					paint(addFriend,screenSize,screenMode, g,io);
					//paint(BarList,screenSize,screenMode, g,io);
				}else if(showCard){
					paint(EditorBG,screenSize,screenMode, g,io);
					paint(changeType,screenSize,screenMode, g,io);

					
					paint(searchCard,screenSize,screenMode, g,io);
					paint(cancelDeck,screenSize,screenMode, g,io);
					paint(storeButton,screenSize,screenMode, g,io);
					
					switch(editMode){//0,1,2,3,4=沒被選到,被選到,瀏覽,編輯,刪除
						case 3:	paint(editDeck,screenSize,screenMode, g,io);
						break;
					}
					//畫牌組列表
					for(int i = 0;i< data.deckDatas.deckList.size()+1;i++){
						DeckListButton dlb = deckListButton.get(i);
						
						switch(dlb.mode){//0,1,2,3,4=沒被選到,被選到,瀏覽,編輯,刪除
							case 0:drawImage(screenSize, screenMode,g,dlb0,
									dlb.getX(),dlb.getY(),dlb.getWidth(),dlb.getHeight(),io);
							break;
							case 1:drawImage(screenSize, screenMode,g,dlb1,
									dlb.getX(),dlb.getY(),dlb.getWidth(),dlb.getHeight(),io);
							break;
							case 2:drawImage(screenSize, screenMode,g,dlb2,
									dlb.getX(),dlb.getY(),dlb.getWidth(),dlb.getHeight(),io);
							break;
							case 3:drawImage(screenSize, screenMode,g,dlb3,
									dlb.getX(),dlb.getY(),dlb.getWidth(),dlb.getHeight(),io);
							break;
							case 4:drawImage(screenSize, screenMode,g,dlb4,
									dlb.getX(),dlb.getY(),dlb.getWidth(),dlb.getHeight(),io);
							break;
						}
						
//						牌組名稱
						if(i< data.deckDatas.deckList.size()){
							g.setColor(Color.white);
							drawString(screenSize,screenMode,g,data.deckDatas.deckList.get(i).name,dlb.getX()+24,dlb.getY()+24,io);
						}
						
						//paint(deckListButton.get(i),screenSize,screenMode, g,io);
					}
					
					//字
					g.setColor(Color.black);
					Font font = g.getFont();
					g.setFont(new Font(font.getFontName(),font.getStyle(),25));
					drawString(screenSize,screenMode,g,"顯示：", 222,387,io);
					g.setFont(new Font(font.getFontName(),font.getStyle(),font.getSize()));
					//牌組名稱
					drawString(screenSize,screenMode,g,data.searchCard, 460,385,io);
					//paint(BarCard,screenSize,screenMode, g,io);
				}else if(showGame){
					paint(goButton,screenSize,screenMode, g,io);
					for(int i = 0;i < playerPanel.size();i++){
						paint(playerPanel.get(i),screenSize,screenMode, g,io);
					}
					for(int i = 0;i < playerHead.length;i++){
						paint(playerHead[i],screenSize,screenMode, g,io);
					}
					paint(heroPanel,screenSize,screenMode, g,io);

					for(int i = 0;i < heroHead.size();i++){
						paint(heroHead.get(i),screenSize,screenMode, g,io);
					}
					

					//paint(BarGame,screenSize,screenMode, g,io);
				}
				paint(leftUpBar,screenSize,screenMode, g,io);
				
				//帳號
				paint(Chat,screenSize,screenMode, g,io,0,chatBaseY);
				paint(SendButton,screenSize,screenMode, g,io,0,chatBaseY);
				if(!chatDowning){
					drawString(screenSize,screenMode,g,data.account, 50,20,io);
					if(data.dialogMode==3){
						g.setColor(Color.white);
						Font font = g.getFont();
						g.setFont(new Font(font.getFontName(),font.getStyle(),16));
						drawString(screenSize,screenMode,g,data.chatTemp+"|", 202,585,io);
						for(int i = 0;i < data.chatList.size();i++){
							drawString(screenSize,screenMode,g,data.account+":"+data.chatList.get(i), 204,(428+18*i),io);
						}
						g.setFont(new Font(font.getFontName(),font.getStyle(),font.getSize()));
					}
				}
				if(showCard){
					switch(editMode){//0,1,2,3,4=沒被選到,被選到,瀏覽,編輯,刪除
					case 2:
						if(deckListButton.get(nowChooseDeck).showCard){
							data.deckDatas.deckList.get(nowChooseDeck).column=13;
							data.deckDatas.deckList.get(nowChooseDeck).paintPreview(changeTypeValue,screenSize, screenMode, g, io);
						}
						//paint(splitBar,screenSize,screenMode, g,io);
						//paint(splitButton,screenSize,screenMode, g,io);
						break;
					case 3:
						if(deckListButton.get(nowChooseDeck).showCard){
							data.deckDatas.deckList.get(nowChooseDeck).column=8;
							data.deckDatas.deckList.get(nowChooseDeck).paint(changeTypeValue,screenSize, screenMode, g, io);
							if(data.deckDatas.bagCard.deck.size()>0)
								data.deckDatas.bagCard.paint(changeTypeValue,screenSize, screenMode, g, io);
						}
					
						
						paint(splitBar,screenSize,screenMode, g,io);
						paint(splitButton,screenSize,screenMode, g,io);
						break;
					}
				}
				
			}

		}
		//離開按鈕
		paint(exitButton,screenSize,screenMode, g,io);
	}
	public void mouseReleased(int x,int y) {
		if(exitButton.isClicked(x, y)){
			System.exit(0);
		}
		if(!hasLogin){//		未登入
			if(Login_Button.isClicked(x, y)){
				Login_Button = new Button(30,100,71,28,new ImageIcon("images/menu/Button/Login_Button.png").getImage());
				loginRising = true;
				chatRising = true;
				//Login_Button.isAlpha = true;
				data.dialogMode = 3;
				if(data.account.matches(""))//假如沒輸入帳號就用匿名
				data.account = "Anonymous";
			}else if(Register_Button.isClicked(x, y)){
				Register_Button = new Button(120,100,71,28,new ImageIcon("images/menu/Button/Register_Button.png").getImage());
				BrowserControl.displayURL("http://ruleandcode.myftp.biz");
			}else if(accoTF.isClicked(x, y)){
				data.account = "";
				data.dialogMode = 1;
			}else if(passTF.isClicked(x, y)){
				data.password = "";
				data.passwordStar = "";
				data.dialogMode = 2;
			}
		}else{//已登入
			if(ChatTF.isClicked(x, y)){
				data.dialogMode = 3;
			}else if(SendButton.isClicked(x, y)){
				SendButton = new Button(721,562,71,28,new ImageIcon("images/menu/Button/Send_Button.png").getImage());
				if(data.chatTemp.length()>0){

					if(data.chatList.size()<9){
						data.chatList.add(data.chatTemp);
					}else{
						data.chatList.remove(0);
						data.chatList.add(data.chatTemp);
					}
					data.chatTemp = "";
				}
			}
			if(hasSetup){
				
			}else if(hasBar){//工具列
				if(loginOut.isClicked(x, y)){
					hasLogin = false;
					data.dialogMode = 1;
				}else if(showCardButton.isClicked(x, y)){
					showCard = true;
					showList = false;
					showGame = false;
					hasBar = false;
				}else if(showGameButton.isClicked(x, y)){
					showGame = true;
					showList = false;
					showCard = false;
					hasBar = false;
				}else if(showListButton.isClicked(x, y)){
					showList = true;
					showGame = false;
					showCard = false;
					hasBar = false;
				}
			}else if(showList){
				if(showFriend){
					
					if(blackList.isClicked(x, y)){
						showFriend=false;
						blackList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換黑名單.png").getImage());
					}
						
				}else{
					
					if(friendList.isClicked(x, y)){
						showFriend=true;
						friendList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換好友名單.png").getImage());
					}
						
				}
				if(searchFriend.isClicked(x, y)){
					searchFriend = new Button(150,315,24,24,new ImageIcon("images/menu/Button/search.png").getImage());
					JOptionPane.showMessageDialog(null,"對不起!!您沒有朋友!!");
				}
				if(addFriend.isClicked(x, y)){
					addFriend = new Button(175,315,24,24,new ImageIcon("images/menu/Button/Add.png").getImage());
				}
				
			}else if(showCard){
				if(changeType.isClicked(x, y)){//改變卡片篩選類型

					switch(changeTypeValue){
						case 0:
							changeTypeValue = 1;
							changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/召喚卡.png").getImage());
						break;
						case 1:
							changeTypeValue = 2;
							changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/魔法卡.png").getImage());
						break;
						case 2:
							changeTypeValue = 3;
							changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/鑲嵌卡.png").getImage());
						break;
						case 3:
							changeTypeValue = 4;
							changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/陷阱卡.png").getImage());
						break;
						case 4:
							changeTypeValue = 0;
							changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/整體.png").getImage());
						break;
					}
				}
				
				if(cancelDeck.isClicked(x, y)){
					cancelDeck = new Button(719,366,71,28,new ImageIcon("images/menu/Button/離開.png").getImage());
					editMode = 1;
				}
				
				if(searchCardTextField.isClicked(x, y)){//搜尋框
					String input = "";
					try{

						input = JOptionPane.showInputDialog("Enter Text:");
					}catch(Exception ex){
						
					}
					data.searchCard = input;
					data.compareSearchCard = input;
					
					
					try {
						//String utf8String = new String(input.getBytes("big5"), "UTF-8");
						String utf8String = new String(input.getBytes(System.getProperty("file.encoding")), "UTF-8");
						data.compareSearchCard = input;
						System.out.println(input );
					} catch (UnsupportedEncodingException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					} 
				}
				
				if(searchCard.isClicked(x, y)){//搜尋
					searchCard = new Button(600,370,24,24,new ImageIcon("images/menu/Button/Search.png").getImage());
					if(!data.searchCard.matches("")){
						data.useKeyWordSearchCard = true;
					}else{
						data.useKeyWordSearchCard = false;
					}
				}
				if(storeButton.isClicked(x, y)){//卡片商店
					storeButton = new Button(650,0,124,29,new ImageIcon("images/menu/Button/卡片商店.png").getImage());
				}
				switch(editMode)
				{
					case 0:
					case 1:for(int i = 0;i< data.deckDatas.deckList.size()+1;i++){
						DeckListButton dlb = deckListButton.get(i);
						if(dlb.isClicked(x, y)){
							dlb.mode = 1;
							editMode = 1;
							dlb.setChoosed(true);
							nowChooseDeck = i;
							if(dlb.isChoosed()){
								if(dlb.broswerB.isClicked(x, y)){//進入瀏覽模式
									dlb.mode = 2;
									editMode = 2;
									if(i<data.deckDatas.deckList.size()){
										dlb.showCard = true;
									}
								}else if(dlb.editB.isClicked(x, y)){//進入編輯模式
									data.readDeck();
									dlb.mode = 3;
									editMode = 3;
									System.out.println("i"+i );
									System.out.println("size"+data.deckDatas.deckList.size() );
									if(i<data.deckDatas.deckList.size()){
										dlb.showCard = true;
										data.nowDeck = data.deckDatas.deckList.get(nowChooseDeck);
									}
								}else if(dlb.deleteB.isClicked(x, y)){//進入刪除模式
									if(data.deckDatas.deckList.size()<=1){//只剩一個牌組
										//無法刪除
									}else{
										
										//請問是否要刪除
										dlb.mode = 4;
										editMode = 4;
									}
								}
							}
						}else{
							dlb.mode = 0;
							dlb.setChoosed(false);
							dlb.showCard = false;
						}
					}

					
						break;
					case 2:
						break;
					case 3:
						if(deckListButton.get(nowChooseDeck).showCard){
							boolean isClicked = data.deckDatas.deckList.get(nowChooseDeck).clickDeck(x, y, changeTypeValue, view.screenSize, view.gs.getScreenMode());
							if(isClicked)
							splitButton = new Button(575,15,32,32,new ImageIcon("images/menu/CarDisplay/right.png").getImage());
						}
						
						if(deckListButton.get(nowChooseDeck).showCard){
							boolean isClicked = data.deckDatas.bagCard.clickDeck(x, y, changeTypeValue, view.screenSize, view.gs.getScreenMode());
							if(isClicked)
								splitButton = new Button(575,15,32,32,new ImageIcon("images/menu/CarDisplay/left.png").getImage());
						}
						
						
						//存檔
						if(editDeck.isClicked(x, y)){
							if(data.nowDeck.deck.size()!=80){
								JOptionPane.showMessageDialog(null,"您現在牌組有"+
										data.nowDeck.deck.size()+"張\n，要80張才能夠組牌組!!");
							}else{
								editDeck = new Button(629,366,71,28,new ImageIcon("images/menu/Button/編輯.png").getImage());
								editMode = 1;
								data.deckDatas.writeDeck();
							}
						}
						break;
					case 4:
						break;
				}
			}else if(showGame){
				if(goButton.isClicked(x, y)){
					goButton = new Button(367,0,65,40,new ImageIcon("images/menu/Hero/Go_Button.png").getImage());
					view.startGame();
					//gameReady = true;
				}
				for(int i = 0;i < heroHead.size();i++){
					if(heroHead.get(i).isClicked(x, y)){
						playerHead[0].setImage(heroHead.get(i).getImage());
						data.nowChooseHeroNum = heroHead.get(i).num;
					}
				}
			}
			if(loginOut.isClicked(x, y)){//登出
				loginDowning = true;
				chatDowning = true;
				//hasLogin = false;
				hasSetup = false;
			}else if(setup.isClicked(x, y)){
				hasSetup = !hasSetup;
			}else if(leftUpBarCircle.isClicked(x, y)){
				hasBar = !hasBar;
			}
		}
	}

	public void mouseMove(int x,int y){
		if(exitButton.isClicked(x, y)){
			exitButton = new Button(770,0,30,30,new ImageIcon("images/menu/Button/Leave_Pointed.png").getImage());
		}else{
			exitButton = new Button(770,0,30,30,new ImageIcon("images/menu/Button/Leave.png").getImage());
			
		}
		if(!hasLogin){//		未登入
			if(Login_Button.isClicked(x, y)){
				Login_Button = new Button(30,100,71,28,new ImageIcon("images/menu/Button/Login_Button_Pointed.png").getImage());
			}else{
				Login_Button = new Button(30,100,71,28,new ImageIcon("images/menu/Button/Login_Button.png").getImage());
			}
			
			if(Register_Button.isClicked(x, y)){
				Register_Button = new Button(120,100,71,28,new ImageIcon("images/menu/Button/Register_Button_Pointed.png").getImage());
			}else{
				Register_Button = new Button(120,100,71,28,new ImageIcon("images/menu/Button/Register_Button.png").getImage());
			}
			if(accoTF.isClicked(x, y)){

			}else if(passTF.isClicked(x, y)){

			}
		}else{//已登入
			if(SendButton.isClicked(x, y)){
				SendButton = new Button(721,562,71,28,new ImageIcon("images/menu/Button/Send_Button_Pointed.png").getImage());
			}else{
				SendButton = new Button(721,562,71,28,new ImageIcon("images/menu/Button/Send_Button.png").getImage());
			}
			if(hasSetup){
				if(loginOut.isClicked(x, y)){

				}else if(setup.isClicked(x, y)){
				}
			}else if(hasBar){//工具列
				if(loginOut.isClicked(x, y)){
				}else if(leftUpBarCircle.isClicked(x, y)){
					
				}else if(showCardButton.isClicked(x, y)){

				}else if(showGameButton.isClicked(x, y)){

				}else if(showListButton.isClicked(x, y)){

				}
			}else if(showList){
				if(showFriend){
					if(blackList.isClicked(x, y))
						blackList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換黑名單_Pointed.png").getImage());
					else {
						blackList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換黑名單.png").getImage());
					}
				}else{
					if(friendList.isClicked(x, y))
						friendList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換好友名單_Pointed.png").getImage());
					else{
						friendList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換好友名單.png").getImage());
					}
				}
				if(searchFriend.isClicked(x, y)){
					searchFriend = new Button(150,315,24,24,new ImageIcon("images/menu/Button/Search_Pointed.png").getImage());
				}else{
					searchFriend = new Button(150,315,24,24,new ImageIcon("images/menu/Button/search.png").getImage());
				}
				if(addFriend.isClicked(x, y)){
					addFriend = new Button(175,315,24,24,new ImageIcon("images/menu/Button/Add_Pointed.png").getImage());
				}else{
					addFriend = new Button(175,315,24,24,new ImageIcon("images/menu/Button/Add.png").getImage());
				}

			}else if(showCard){
				if(changeType.isClicked(x, y)){

					switch(changeTypeValue){
					case 0:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/整體_Pointed.png").getImage());
					break;
					case 1:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/召喚卡_Pointed.png").getImage());
					break;
					case 2:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/魔法卡_Pointed.png").getImage());
					break;
					case 3:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/鑲嵌卡_Pointed.png").getImage());
					break;
					case 4:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/陷阱卡_Pointed.png").getImage());
					break;
					}
				}else{
					switch(changeTypeValue){
					case 0:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/整體.png").getImage());
					break;
					case 1:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/召喚卡.png").getImage());
					break;
					case 2:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/魔法卡.png").getImage());
					break;
					case 3:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/鑲嵌卡.png").getImage());
					break;
					case 4:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/陷阱卡.png").getImage());
					break;
					}
				}
				
				if(cancelDeck.isClicked(x, y)){
					cancelDeck = new Button(719,366,71,28,new ImageIcon("images/menu/Button/離開_Pointed.png").getImage());
				}else{
					cancelDeck = new Button(719,366,71,28,new ImageIcon("images/menu/Button/離開.png").getImage());
				}
				if(searchCard.isClicked(x, y)){
					searchCard = new Button(600,370,24,24,new ImageIcon("images/menu/Button/Search_Pointed.png").getImage());
				}else{
					searchCard = new Button(600,370,24,24,new ImageIcon("images/menu/Button/Search.png").getImage());
				}
				if(storeButton.isClicked(x, y)){
					storeButton = new Button(650,0,124,29,new ImageIcon("images/menu/Button/卡片商店_Pointed.png").getImage());
				}else{
					storeButton = new Button(650,0,124,29,new ImageIcon("images/menu/Button/卡片商店.png").getImage());
				}
				switch(editMode)
				{
					case 0:
					case 1:for(int i = 0;i< data.deckDatas.deckList.size()+1;i++){
						DeckListButton dlb = deckListButton.get(i);
						if(dlb.isClicked(x, y)){
							
							if(dlb.isChoosed()){
								if(dlb.broswerB.isClicked(x, y)){//瀏覽
									if(deckListButton.get(nowChooseDeck).showCard){
										
									}
								}else if(dlb.editB.isClicked(x, y)){//編輯
									
								}else if(dlb.deleteB.isClicked(x, y)){//刪除
									
								}
							}
						}else{
							
						}
					}
						break;
					case 2:
						break;
					case 3:
						if(editDeck.isClicked(x, y)){
							editDeck = new Button(629,366,71,28,new ImageIcon("images/menu/Button/編輯_Pointed.png").getImage());
						}else{
							editDeck = new Button(629,366,71,28,new ImageIcon("images/menu/Button/編輯.png").getImage());
						}
						break;
					case 4:
						break;
				}
			}else if(showGame){
				if(goButton.isClicked(x, y)){
					goButton = new Button(367,0,65,40,new ImageIcon("images/menu/Hero/Go_Button_Pointed.png").getImage());
				}else{
					goButton = new Button(367,0,65,40,new ImageIcon("images/menu/Hero/Go_Button.png").getImage());
				}
			}
			else{
				if(loginOut.isClicked(x, y)){//登出

				}else if(setup.isClicked(x, y)){

				}else if(leftUpBarCircle.isClicked(x, y)){

				}
			}
		}
	}
	public void mousePressed(int x,int y) {
		if(exitButton.isClicked(x, y)){
			exitButton = new Button(770,0,30,30,new ImageIcon("images/menu/Button/Leave_Pressed.png").getImage());
		}
		if(!hasLogin){//		未登入
			if(Login_Button.isClicked(x, y)){
				Login_Button = new Button(30,100,71,28,new ImageIcon("images/menu/Button/Login_Button_Pressed.png").getImage());
			}else if(Register_Button.isClicked(x, y)){
				Register_Button = new Button(120,100,71,28,new ImageIcon("images/menu/Button/Register_Button_Pressed.png").getImage());
			}else if(accoTF.isClicked(x, y)){

			}else if(passTF.isClicked(x, y)){

			}
		}else{//已登入
			if(ChatTF.isClicked(x, y)){

			}else if(SendButton.isClicked(x, y)){
				SendButton = new Button(721,562,71,28,new ImageIcon("images/menu/Button/Send_Button_Pressed.png").getImage());
			}
			if(hasSetup){
				if(loginOut.isClicked(x, y)){

				}else if(setup.isClicked(x, y)){
				}
			}else if(hasBar){//工具列
				if(loginOut.isClicked(x, y)){
				}else if(leftUpBarCircle.isClicked(x, y)){
					
				}else if(showCardButton.isClicked(x, y)){
					Bar = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar_Card_Pressed.png").getImage());
				}else if(showGameButton.isClicked(x, y)){
					Bar = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar_Game_Pressed.png").getImage());
				}else if(showListButton.isClicked(x, y)){
					Bar = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar_List_Pressed.png").getImage());
				}else{
					Bar = new Button(0,0,150,65,new ImageIcon("images/menu/Bar/Bar.png").getImage());
				}
			}else if(showList){
				if(showFriend){
					if(blackList.isClicked(x, y))
						blackList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換黑名單_Pressed.png").getImage());
				}else{
					if(friendList.isClicked(x, y))
						friendList = new Button(78,28,124,29,new ImageIcon("images/menu/Button/切換好友名單_Pressed.png").getImage());
				}
				if(searchFriend.isClicked(x, y)){
					searchFriend = new Button(150,315,24,24,new ImageIcon("images/menu/Button/Search_Pressed.png").getImage());
				}
				if(addFriend.isClicked(x, y)){
					addFriend = new Button(175,315,24,24,new ImageIcon("images/menu/Button/Add_Pressed.png").getImage());
				}
			}else if(showCard){
				if(changeType.isClicked(x, y)){
					switch(changeTypeValue){
					case 0:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/整體_Pressed.png").getImage());
						break;
					case 1:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/召喚卡_Pressed.png").getImage());
					break;
					case 2:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/魔法卡_Pressed.png").getImage());
					break;
					case 3:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/鑲嵌卡_Pressed.png").getImage());
					break;
					case 4:
						changeType = new Button(300,366,71,28,new ImageIcon("images/menu/Button/陷阱卡_Pressed.png").getImage());
					break;
					}
				}
				if(editDeck.isClicked(x, y)){
					editDeck = new Button(629,366,71,28,new ImageIcon("images/menu/Button/編輯_Pressed.png").getImage());
				}
				if(cancelDeck.isClicked(x, y)){
					cancelDeck = new Button(719,366,71,28,new ImageIcon("images/menu/Button/離開_Pressed.png").getImage());
				}
				if(searchCard.isClicked(x, y)){
					searchCard = new Button(600,370,24,24,new ImageIcon("images/menu/Button/Search_Pressed.png").getImage());
				}
				if(storeButton.isClicked(x, y)){
					storeButton = new Button(650,0,124,29,new ImageIcon("images/menu/Button/卡片商店_Pressed.png").getImage());
				}
			}else if(showGame){
				if(goButton.isClicked(x, y)){
					goButton = new Button(367,0,65,40,new ImageIcon("images/menu/Hero/Go_Button_Pressed.png").getImage());
				}
			}
			else{
			
				if(loginOut.isClicked(x, y)){//登出

				}else if(setup.isClicked(x, y)){

				}else if(leftUpBarCircle.isClicked(x, y)){

				}
			}
		}
	}
	
	public void keyInMainMenu(KeyEvent e){
		switch(data.dialogMode){
			case 0://不能打字
			break;
			case 1://登入框
				switch(e.getKeyCode()){
				case KeyEvent.VK_ENTER:
					data.dialogMode=2;
				break;
				case KeyEvent.VK_BACK_SPACE:
					data.account = data.account.substring(0,data.account.length()-1);
				break;
				default:
					if(data.account.length()<10){
						if(e.getKeyCode()>=32&&e.getKeyCode()<=122)
						data.account += e.getKeyChar();
					}
				}
			break;
			case 2://密碼框
				switch(e.getKeyCode()){
				case KeyEvent.VK_ENTER:
					Login_Button = new Button(30,100,71,28,new ImageIcon("images/menu/Button/Login_Button.png").getImage());
					loginRising = true;
					chatRising = true;
					//Login_Button.isAlpha = true;
					data.dialogMode = 3;
					if(data.account.matches(""))//假如沒輸入帳號就用匿名
					data.account = "Anonymous";
				break;
				case KeyEvent.VK_BACK_SPACE:
					data.password = data.password.substring(0,data.password.length()-1);
					data.passwordStar = data.passwordStar.substring(0,data.passwordStar.length()-1);
				break;
				default:
					if(data.password.length()<10)
						if(e.getKeyCode()>=32&&e.getKeyCode()<=122)
					data.password += e.getKeyChar();
				    if(data.passwordStar.length()<10)
				    	if(e.getKeyCode()>=32&&e.getKeyCode()<=122)
					data.passwordStar += '*';
				}
				break;
			case 3://聊天框
				switch(e.getKeyCode()){
				case KeyEvent.VK_ENTER:
					if(data.chatTemp.length()>0){
						if(data.chatList.size()<8){
							data.chatList.add(data.chatTemp);
						}else{
							data.chatList.remove(0);
							data.chatList.add(data.chatTemp);
						}
						if(data.chatTemp.matches("go")){
							view.startGame();
						}
						data.chatTemp = "";
					}
				break;
				case KeyEvent.VK_BACK_SPACE:
					data.chatTemp = data.chatTemp.substring(0,data.chatTemp.length()-1);
				break;
				default:
					if(data.chatTemp.length()<50)
						if(e.getKeyCode()>=32&&e.getKeyCode()<=122)
					data.chatTemp += e.getKeyChar();
				}
				break;
		}
	}
}
