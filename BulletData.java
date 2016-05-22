import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class BulletData {
	private int num;//編號
	private int width;
	private int height;
	private int speed;
	private int atkType;
	private String magicball;
	private int lifeTime;
	public BulletData(){
		
	}
	public void printData(){
		System.out.print(width+" ");
		System.out.print(height+" ");
		System.out.print(speed+" ");
		System.out.print(atkType+" ");
		System.out.print(magicball+" ");
		System.out.print(lifeTime+"\n");
	}
	public void readData(File file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				input = br.readLine();
				while(!input.matches("width")){
					num = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("height")){
					width = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("speed")){

					height = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("atkType")){
					
					speed = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("magicball")){
					
					atkType = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("lifetime")){
					
					magicball = input;
					input = br.readLine();
				}
				input = br.readLine();
				lifeTime = Integer.parseInt(input);
				br.close();
			} catch (IOException e) {
				// TODO 自動產生 catch 區塊
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO 自動產生 catch 區塊
			e.printStackTrace();
		}
	}
	public int getAtkType() {
		return atkType;
	}
	public void setAtkType(int atkType) {
		this.atkType = atkType;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	public String getMagicball() {
		return magicball;
	}
	public void setMagicball(String magicball) {
		this.magicball = magicball;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
