import java.awt.Color;
import java.awt.Image;


public class Effect {
	private int type; //效果種類 1,2,3,4,5,6,7=畫線 矩形 圓形 圓矩 圖片 字 界面特效
	//第一個點
	private int x1;
	private int y1;
	//第二個點或寬度
	private int x2;
	private int y2;
	//顏色
	private Color color;
	//圖片
	private Image image;
	//字
	private String string;
	private boolean live = true;
	private boolean visible = false;
	private boolean underUI = false;
	//有時效
	private int lifeTime;
	private boolean hasLifeTime;
	private double alpha = 1.0;
	public Effect(){
		
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public boolean isUnderUI() {
		return underUI;
	}
	public void setUnderUI(boolean underUI) {
		this.underUI = underUI;
	}
	public boolean isHasLifeTime() {
		return hasLifeTime;
	}
	public void setHasLifeTime(boolean hasLifeTime) {
		this.hasLifeTime = hasLifeTime;
	}
	public int getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
}
