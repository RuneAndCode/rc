import java.awt.Image;

import javax.swing.ImageIcon;


public class ImageData {
	public Image[] terrainImage = new Image[6];
	Image heroBottom = new ImageIcon("images/effect/­^¶¯¶H¼x/1.gif").getImage();
	public ImageData(){
		for(int i = 1;i <= 5;i++)
		{
			terrainImage[i] = new ImageIcon("./images/terrain/"+i+".gif").getImage();
		}
	}
}
