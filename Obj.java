import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;


public class Obj {
	private int x; //座標x
	private int y; //座標y
	private int vx = 0; //x軸速度(每秒向右移動速度)
	private int vy = 0; //y軸速度(每秒向右移動速度)
	private int width = 0; //物件圖像寬度
	private int height = 0; //物件圖像高度
	private Image image; //當前圖像
	private ArrayList<String> imagePath;//圖片組路徑
	private boolean isChoosed = false;
	private boolean visible = true;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	Obj()
	{
	}
	Obj(int x,int y,Image image)
	{
		this.x = x;
		this.y = y;
		this.image = image;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void paint(Graphics g,ImageObserver io){
		g.drawImage(image,x,y,width,height,io);
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isClicked(Dimension screenSize,int screenMode,int x,int y){
		double xRate = 1;
		double yRate = 1;
		switch(screenMode){
			case 1: xRate = 800/800.0; yRate = 600/600.0;
			break;
			case 2: xRate = 1024/800.0; yRate = 768/600.0;
			break;
			case 3: xRate = screenSize.width/800.0; yRate = screenSize.height/600.0;
			break;

		}
		if((int)(this.x/xRate)<x&&x<(int)(this.x/xRate)+(int)(this.width/yRate)&&
				(int)(this.y/yRate)<y&&y<(int)(this.y/yRate)+(int)(this.height/yRate))
			return true;
		else return false;
	}
	public boolean isClicked(int x,int y){
		double xRate = 1;
		double yRate = 1;
		//System.out.println("2");
		//System.out.println(getX()+","+x);
		//System.out.println(getY()+"."+y);
		
		if((int)(getX())<x&&x<(int)(getX())+(int)(getWidth())&&
				(int)(getY())<y&&y<(int)(getY())+(int)(getHeight()))
			return true;
		else return false;
	}
	public boolean isChoosed() {
		return isChoosed;
	}
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
	public ArrayList<String> getImagePath() {
		return imagePath;
	}
	public void setImagePath(ArrayList<String> imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
