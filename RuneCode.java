import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RuneCode {

	/**
	 * @param args
	 */
	private static int DEFAULT_FPS = 60;
	
	static MainFrame app;
	
	public static void main(String[] args) {

		int fps = DEFAULT_FPS;
		if (args.length != 0)
			fps = Integer.parseInt(args[0]);

		int period = (int) 1000.0 / fps; // in ms
		System.out.println("fps: " + fps + " period: " + period + " ms");

		PaintPanel pp = new PaintPanel(period);
		// create a JFrame to hold the timer test JPanel
		app = new MainFrame();
		pp.data.setFrame(app);
		app.getContentPane().add(pp, BorderLayout.CENTER);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setFocusable(true);

		MainTimerTask task = new MainTimerTask(pp);
		Timer t = new Timer();
		t.scheduleAtFixedRate(task, 0, period);
		// execute every period ms (at a fixed rate)
		app.setUndecorated(true);
		app.pack();
		app.setResizable(false);
		app.setSize(800, 600);
		app.setVisible(true);
		app.setTitle("Rune & Code");
		app.setLocationRelativeTo(null);
	}

}

// -------------------------------------------------
class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3032356111469754758L;

	// private GraphicsDevice gd;
	MainFrame() {
		Image cursorImage = new ImageIcon("./images/arrow/1.png").getImage();
		Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "1");
		setCursor(customCursor);
	}
}

class MainTimerTask extends TimerTask {
	private PaintPanel pp;

	public MainTimerTask(PaintPanel pp) {
		this.pp = pp;
	}

	public void run()
	// update, render
	{
		sillyTask();
		pp.repaint();
	} // end of run()

	private void sillyTask() {
		long tot = 0;
		for (long i = 0; i < 200000L; i++)
			tot += i;
	} // end of sillyTask()

} // end of myTimerTask class

// ------------------------------------------------------

class PaintPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4346717640335949479L;

	private static final int PWIDTH = 200;

	private static final int PHEIGHT = 75;

	private static long MAX_STATS_INTERVAL = 1000L;

	// record stats every 1 second (roughly)

	private static int NUM_FPS = 10;

	// number of FPS values stored to get an average

	// used for gathering statistics
	private long statsInterval = 0L; // in ms

	private long prevStatsTime;

	private long totalElapsedTime = 0L;

	private long frameCount = 0;

	private double fpsStore[];

	private long statsCount = 0;

	private double averageFPS = 0.0;

	private DecimalFormat df = new DecimalFormat("0.##"); // 2 dp

	//private DecimalFormat timedf = new DecimalFormat("0.####"); // 4 dp

	private int period; // period between drawing in _ms_

	// i.e. the time requested for one frame iteration

	final int key0 = KeyEvent.VK_Z;

	final int key1 = KeyEvent.VK_X;

	final int key2 = KeyEvent.VK_SHIFT;

	final int keyUp = KeyEvent.VK_UP;

	final int keyDown = KeyEvent.VK_DOWN;

	final int keyLeft = KeyEvent.VK_LEFT;

	final int keyRight = KeyEvent.VK_RIGHT;

	int nowView = 0;

	Image selbk;

	Image selBorder;

	static GameSetting gs = new GameSetting();
	Data data = new Data();
	View view = new View(gs,data);
	
	public PaintPanel(int p) {
		MediaTracker mediaTracker = new MediaTracker(this);

		try {
			mediaTracker.waitForAll();
		} catch (InterruptedException e) {
		}

		period = p;

		setBackground(Color.white);
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));

		fpsStore = new double[NUM_FPS];
		for (int i = 0; i < NUM_FPS; i++)
			fpsStore[i] = 0.0;

		// prevStatsTime = J3DTimer.getValue();
		prevStatsTime = System.nanoTime();
		//更新執行緒
		new Thread(){
			public void run(){
				while(true){
					try {
						sleep(30);
					} catch (InterruptedException e) {
						// TODO 自動產生 catch 區塊
						e.printStackTrace();
					}
					view.renewObject();
				}
			}
		}.start();
		
//		 滑鼠事件
		addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				double xRate = 1;
				double yRate = 1;
				switch(gs.getScreenMode()){
				case 1: xRate = 1; yRate = 1;
				break;
				case 2: xRate = 1.28; yRate = 1.28;
				break;
				case 3: xRate = gs.getScreenSize().width/800.0; yRate = gs.getScreenSize().height/600.0;
				break;
				}
				x=(int)(x/xRate);
				y=(int)(y/yRate);
				view.mouseDrag(x,y);
				//System.out.print(e.getButton());
				/*
				int type = e.getButton();//1,2,3 左,中,右鍵
				switch(type)
				{
					case 1:	view.mouseDrag(e.getX(),e.getY());
					break;
					case 2:	
					break;
					case 3:	
					break;
				}*/
			}
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				double xRate = 1;
				double yRate = 1;
				switch(gs.getScreenMode()){
				case 1: xRate = 1; yRate = 1;
				break;
				case 2: xRate = 1.28; yRate = 1.28;
				break;
				case 3: xRate = gs.getScreenSize().width/800.0; yRate = gs.getScreenSize().height/600.0;
				break;
				}
				x=(int)(x/xRate);
				y=(int)(y/yRate);
				view.mouseMove(x,y);
			}

		});
		addMouseWheelListener(new MouseWheelListener() {
		      public void mouseWheelMoved(MouseWheelEvent e) {
		        if (e.getWheelRotation() < 0) {
		        	view.MouseWheel(1);
		        } else {
		        	view.MouseWheel(2);
		        }
		      }
		 });
		addMouseListener(new MouseListener() {
			

			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				double xRate = 1;
				double yRate = 1;
				switch(gs.getScreenMode()){
				case 1: xRate = 1; yRate = 1;
				break;
				case 2: xRate = 1.28; yRate = 1.28;
				break;
				case 3: xRate = gs.getScreenSize().width/800.0; yRate = gs.getScreenSize().height/600.0;
				break;
				}
				x=(int)(x/xRate);
				y=(int)(y/yRate);
				view.mouseReleased(e,x,y);
			}

			public void mouseClicked(MouseEvent e) {
				
				int x = e.getX();
				int y = e.getY();
				
				double xRate = 1;
				double yRate = 1;
				switch(gs.getScreenMode()){
				case 1: xRate = 1; yRate = 1;
				break;
				case 2: xRate = 1.28; yRate = 1.28;
				break;
				case 3: xRate = gs.getScreenSize().width/800.0; yRate = gs.getScreenSize().height/600.0;
				break;
				}
				x=(int)(x/xRate);
				y=(int)(y/yRate);
				//System.out.println(x+","+y);
				
				//System.out.println(x+","+y);
				int type = e.getButton();//1,2,3 左,中,右鍵
				switch(type)
				{
					case 1:	view.clickLeft(x, y);
					break;
					case 2:	view.clickMiddle(x, y);
					break;
					case 3:	view.clickRight(x, y);
					break;
				}

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				double xRate = 1;
				double yRate = 1;
				switch(gs.getScreenMode()){
				case 1: xRate = 1; yRate = 1;
				break;
				case 2: xRate = 1.28; yRate = 1.28;
				break;
				case 3: xRate = gs.getScreenSize().width/800.0; yRate = gs.getScreenSize().height/600.0;
				break;
				}
				x=(int)(x/xRate);
				y=(int)(y/yRate);
				view.mousePressed(x,y);
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				try {
					switch(e.getKeyCode()){
						case KeyEvent.VK_ESCAPE:
							System.exit(0);
						case KeyEvent.VK_F11:
							gs.changeScreen();
						break;
					}
					view.keyIn(e);
				} catch (Exception ex) {

				}

			}

			public void keyReleased(KeyEvent e) {

			}
		});
	} // end of PaintPanel()

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 清除背景
		g.setColor(Color.white);
		g.fillRect(0, 0, PWIDTH, PHEIGHT);

		//回報當前FPS
		g.setColor(Color.black);
		g.drawString("Average FPS: " + df.format(averageFPS), 10, 25);
		
		view.paint(gs.getScreenSize(), gs.getScreenMode(), g, this);
		
	} // end of paintComponent()
	
} // end of PaintPanel class