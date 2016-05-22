import java.awt.Image;

import javax.swing.ImageIcon;


public class DeckListButton extends Button{
	private Image image;
	private int x = 0;
	private int y = 0;
	private int vx = 0;
	private int vy = 0;
	private int width = 0;
	private int height = 0;
	Button broswerB;
	Button editB;
	Button deleteB;
	int mode = 0;//0,1,2,3,4=沒被選到,被選到,瀏覽,編輯,刪除
	boolean showCard = false;
	DeckListButton(int x,int y,int w,int h,Image image)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.image = image;
		broswerB  = new Button(x+130,y+12,16,16,new ImageIcon("images/other/null.gif").getImage());
		editB  = new Button(x+155,y+12,16,16,new ImageIcon("images/other/null.gif").getImage());
		deleteB  = new Button(x+180,y+12,16,16,new ImageIcon("images/other/null.gif").getImage());
	}
	public Button getBroswerB() {
		return broswerB;
	}
	public void setBroswerB(Button broswerB) {
		this.broswerB = broswerB;
	}
	public Button getDeleteB() {
		return deleteB;
	}
	public void setDeleteB(Button deleteB) {
		this.deleteB = deleteB;
	}
	public Button getEditB() {
		return editB;
	}
	public void setEditB(Button editB) {
		this.editB = editB;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
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
}
