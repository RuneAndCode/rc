import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class CardData {
	private int num;//�s��
	private String name;//�W�r
	private int level;//����i�H�ϥθӥd������
	private int type; //����1,2,3,4=�l��,�]�k,�^�O,����
	private int cost; //���ӯ�q
	private int effect;
	private int typegroup;
	private String  comment;//�`��
	public void printData(){
		
	}
	public void readData(File file){
		try {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file),"UTF-8"));
				
			} catch (UnsupportedEncodingException e1) {
				// TODO �۰ʲ��� catch �϶�
				e1.printStackTrace();
			}
			//br = new BufferedReader(new InputStreamReader(
			//		new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				input = br.readLine();
				while(!input.matches("name")){
					num = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("level")){
					name = input;
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("type")){
					level = Integer.parseInt(input);;
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("cost")){
					type = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("effect")){
					cost = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("typegroup")){
					effect = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(!input.matches("comment")){
					typegroup = Integer.parseInt(input);
					input = br.readLine();
				}
				input = br.readLine();
				while(input!=null){
					comment += input+"\n";
					input = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				// TODO �۰ʲ��� catch �϶�
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO �۰ʲ��� catch �϶�
			e.printStackTrace();
		}
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTypegroup() {
		return typegroup;
	}
	public void setTypegroup(int typegroup) {
		this.typegroup = typegroup;
	}
	
}
