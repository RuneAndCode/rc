import java.awt.Image;

import javax.swing.ImageIcon;


public class HeroHeadButton extends Button{
	int num = 0;
	public HeroHeadButton(int x,int y,int w,int h,int num){
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
		this.num = num;
		setImage(new ImageIcon("images/head/"+num+".png").getImage());
	}
}
