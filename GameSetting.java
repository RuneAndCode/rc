import java.awt.Dimension;


public class GameSetting {
	private int screenMode = 1; //0,1024,800
	private int nextScreenMode = 2;
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int mouseScrollSpeed = 0;//0,1,2=slow,middle,fasts
	private int DPI = 0;//0,1,2=800*600,1024*768,fullScreen
	private int HpVisibility = 0;//0,1=false,true;
	private int soundEffect = 0;//0,1=false,true;
	private int BGMEffect = 0;//0,1=false,true;

	GameSetting(){
		//讀取之前設定檔 假如有設定記錄就採用
		//否則採用預設的設定值
	}
	public void changeScreen(){
		//screenMode的123就是800*600,1024*768和全螢幕
		switch(nextScreenMode){
		case 1:
			nextScreenMode=2;
			screenMode = 1;
			RuneCode.app.dispose();  
			//RuneCode.app.setUndecorated(false);
			RuneCode.app.setVisible(true);
			RuneCode.app.setResizable(true);
			RuneCode.app.setSize(800, 600);
			RuneCode.app.setResizable(false);
			RuneCode.app.setLocationRelativeTo(null);
			RuneCode.app.setUndecorated(true);
		break;
		case 2:
			nextScreenMode=3;
			screenMode = 2;
			RuneCode.app.setResizable(true);
			RuneCode.app.setSize(1024, 768);
			RuneCode.app.setResizable(false);
			RuneCode.app.setLocationRelativeTo(null);
		break;
		case 3:
			nextScreenMode=1;
			screenMode = 3;
			RuneCode.app.dispose();  
			RuneCode.app.setUndecorated(true);
			RuneCode.app.setVisible(true);
			RuneCode.app.pack();
			RuneCode.app.setSize(screenSize.width, screenSize.height);
			RuneCode.app.setLocation(0, 0);

		}
		
	}
	public void changeScreen(int mode){
		if(mode!=screenMode)
		switch(mode){
		case 1:
			nextScreenMode=2;
			screenMode = 1;
			RuneCode.app.dispose();  
			RuneCode.app.setUndecorated(false);
			RuneCode.app.setVisible(true);
			RuneCode.app.setResizable(true);
			RuneCode.app.setSize(800, 600);
			RuneCode.app.setResizable(false);
			RuneCode.app.setLocationRelativeTo(null);
			//RuneCode.app.setUndecorated(true);
		break;
		case 2:
			nextScreenMode=3;
			screenMode = 2;
			RuneCode.app.dispose();  
			RuneCode.app.setUndecorated(false);
			RuneCode.app.setVisible(true);
			RuneCode.app.setResizable(true);
			RuneCode.app.setSize(1024, 768);
			RuneCode.app.setResizable(false);
			RuneCode.app.setLocationRelativeTo(null);

		break;
		case 3:
			nextScreenMode=1;
			screenMode = 3;
			RuneCode.app.dispose();  
			RuneCode.app.setUndecorated(true);
			RuneCode.app.setVisible(true);
			RuneCode.app.pack();
			RuneCode.app.setResizable(true);
			RuneCode.app.setSize(screenSize.width, screenSize.height);
			RuneCode.app.setResizable(false);
			RuneCode.app.setLocation(0, 0);

		}
		
	}
	public int getNextScreenMode() {
		return nextScreenMode;
	}
	public void setNextScreenMode(int nextScreenMode) {
		this.nextScreenMode = nextScreenMode;
	}
	public int getScreenMode() {
		return screenMode;
	}
	public void setScreenMode(int screenMode) {
		this.screenMode = screenMode;
	}
	public Dimension getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}
	public int getBGMEffect() {
		return BGMEffect;
	}
	public void setBGMEffect(int effect) {
		BGMEffect = effect;
	}
	public int getDPI() {
		return DPI;
	}
	public void setDPI(int dpi) {
		DPI = dpi;
	}
	public int getHpVisibility() {
		return HpVisibility;
	}
	public void setHpVisibility(int hpVisibility) {
		HpVisibility = hpVisibility;
	}
	public int getMouseScrollSpeed() {
		return mouseScrollSpeed;
	}
	public void setMouseScrollSpeed(int mouseScrollSpeed) {
		this.mouseScrollSpeed = mouseScrollSpeed;
	}
	public int getSoundEffect() {
		return soundEffect;
	}
	public void setSoundEffect(int soundEffect) {
		this.soundEffect = soundEffect;
	}
	
}
