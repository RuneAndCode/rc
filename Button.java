import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public class Button extends Obj
{
	private Image image;
	private int x = 0;
	private int y = 0;
	private int vx = 0;
	private int vy = 0;
	private int width = 144;
	private int height = 55;
	private boolean isChoosed = false;
	float alpha = (float)1.0;//透明度
	boolean isAlpha = false;
	int alphaCount = 0;//透明計數器
	private int state = 0;//0=通常, 1=遊標在上面, 2按下去
	private int scroll = 0;//0無, 1=往上拉, 2=往下拉
	int num;//編號(EX英雄編號)
	public void normal(){
		this.state = 0;
	}
	public void over(){
		this.state = 1;
	}
	public void press(){
		this.state = 2;
	}
	
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
	Button()
	{
		width = 144;
		height = 55;
	}
	Button(int x,int y,Image image)
	{
		this.x = x;
		this.y = y;
		this.image = image;
	}
	Button(int x,int y,int w,int h)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	Button(int x,int y,int w,int h,Image image)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
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
		if((int)(this.x*xRate)<x&&x<(int)(this.x*xRate)+(int)(this.width*yRate)&&
				(int)(this.y*yRate)<y&&y<(int)(this.y*yRate)+(int)(this.height*yRate))
			return true;
		else return false;
	}
	public boolean isChoosed() {
		return isChoosed;
	}
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
}
