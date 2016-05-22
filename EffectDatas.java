import java.awt.Color;

import javax.swing.ImageIcon;


public class EffectDatas {
	Data data;
	EffectDatas(){
		
	}
	EffectDatas(Data data){
		this.data = data;
	}
	public void bomb(int x,int y){
		final Step step = new Step();
		step.setNum(1);
		final int sx = x;
		final int sy = y;
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
	public void activate(Unit u){
		final Step step = new Step();
		step.setNum(1);
		final int sx = u.getX();
		final int sy = u.getY();
		final int tempdir = u.getDir();
		final Effect e1 = new Effect();
		e1.setType(6);
		data.effects.add(e1);
		e1.setVisible(true);
		e1.setColor(Color.green);
		e1.setString("activate!!");
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
	public void moveInst(Unit u,int x,int y){
		final Step step = new Step();
		step.setNum(1);
		final int sx = u.getX();
		final int sy = u.getY();
		final int tempdir = u.getDir();
		final Effect e1 = new Effect();
		e1.setType(3);
		data.effects.add(e1);
		e1.setVisible(true);
		e1.setColor(Color.red);
		e1.setX1(x-u.getWidth()/2);
		e1.setY1(y+u.getHeight()/2);
		e1.setX2(u.getHeight());
		e1.setY2(u.getWidth()/5);
		e1.setUnderUI(true);
		new Thread(){
			public void run(){
				int n = 0;
				try {
					while(true){
						n++;
						e1.setX1(e1.getX1()+1);
						e1.setY1(e1.getY1()+1);
						e1.setX2(e1.getX2()-2);
						e1.setY2(e1.getY2()-2);
						sleep(100);
						if(e1.getX2()<=0)
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
}
