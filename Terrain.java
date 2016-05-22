import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

class Terrain
{
	//private Image[] image = new Image[6];
	private Image image = null;
	private int x = 0;
	private int y = 0;
	private int vx = 0;
	private int vy = 0;
	//private int durability = 1; //耐久度
	private int width = 50;
	private int height = 50;
	private int type = 0; //磚塊類型(0-10)
	private boolean canMove = true;//是否可以通行
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
	}/*
	public void loadImage(){	
		for(int i = 0;i <= 5;i++)
		{
			image[i] = new ImageIcon("./images/terrain/"+i+".gif").getImage();
		}
	}*/
	Terrain()
	{
		//loadImage();
	}
	Terrain(Data data,int x,int y,int type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		image = data.imageData.terrainImage[type];
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
		if(image!=null)
		g.drawImage(image,x,y,width,height,io);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isClicked(int x,int y){
		if(this.x<x&&x<this.x+this.width&&
				this.y<y&&y<this.y+this.height)
			return true;
		else return false;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isCanMove() {
		return canMove;
	}
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
}