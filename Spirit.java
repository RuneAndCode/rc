import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;


public class Spirit {
	public double computeXRate(Dimension screenSize,int screenMode){
		double result = 1.0;
		switch(screenMode){
		case 1: result = 800/800.0;
		break;
		case 2: result = 1024/800.0;
		break;
		case 3: result = screenSize.width/800.0;
		break;
		}
		return result;
	}
	public double computeYRate(Dimension screenSize,int screenMode){
		double result = 1.0;
		switch(screenMode){
		case 1: result = 800/800.0;
		break;
		case 2: result = 1024/800.0;
		break;
		case 3: result = screenSize.width/800.0;
		break;
		}
		return result;
	}
	public void drawLine(Dimension screenSize,int screenMode,Graphics g,int x1,int y1,int x2,int y2,ImageObserver io){
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
		g.drawLine((int)(x1*xRate), (int)(y1*yRate),(int)(x2*xRate),(int)(y2*yRate));
	}
	public void drawImage(Dimension screenSize,int screenMode,Graphics g,Image image,int x,int y,int w,int h,ImageObserver io){
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
		g.drawImage(image, (int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate), io);
	}
	public void drawImage(Dimension screenSize,int screenMode,Graphics g,Image image,int x,int y,ImageObserver io){
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
		g.drawImage(image, (int)(x*xRate), (int)(y*yRate), io);
	}
	public void drawRectangle(Dimension screenSize,int screenMode,Graphics g,int x,int y,int w,int h,ImageObserver io){
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
		g.drawRect((int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate));
	}
	public void drawFillRectangle(Dimension screenSize,int screenMode,Graphics g,int x,int y,int w,int h,ImageObserver io){
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
		g.fillRect((int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate));
	}
	public void drawString(Dimension screenSize,int screenMode,Graphics g,String string,int x,int y,ImageObserver io){
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
		if(string!=null)
		g.drawString(string,(int)(x*xRate), (int)(y*yRate));
	}
	public void drawOval(Dimension screenSize,int screenMode,Graphics g,int x,int y,int w,int h,ImageObserver io){
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
		g.drawOval((int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate));
	}
	public void drawFillOval(Dimension screenSize,int screenMode,Graphics g,int x,int y,int w,int h,ImageObserver io){
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
		g.fillOval((int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate));
	}
	public void drawFillArc(Dimension screenSize,int screenMode,Graphics g,int x,int y,int w,int h,int sa,int ea,ImageObserver io){
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
		g.fillArc((int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate),sa,ea);
	}
	//
	public void drawImageAlpha(float alpha,Dimension screenSize,int screenMode,Graphics g,Image image,int x,int y,int w,int h,ImageObserver io){
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
		Graphics2D g2d = (Graphics2D) g;
		int compositeRule = AlphaComposite.SRC_OVER; 
		AlphaComposite alphaComposite=AlphaComposite.getInstance(compositeRule,(float)alpha); 
		g2d.setComposite(alphaComposite);
		g.drawImage(image, (int)(x*xRate), (int)(y*yRate),(int)(w*xRate),(int)(h*yRate), io);
		
	}
}
