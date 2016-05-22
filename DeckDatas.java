import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;


public class DeckDatas {
	static ArrayList<Deck> deckList = new ArrayList<Deck>();//牌組名單
	static Deck bagCard;
	Data data;
	String path;
	public DeckDatas(Data data,String path){
		this.path = path;
		this.data = data;
		deckList = new ArrayList<Deck>();//牌組名單
		readData();
		bagCard = new Deck(data);//背包裡的卡片
		readBag();
		
	}
	public void readData(){
		File dir = new File(path);
		String[] s = dir.list();
		for(int i = 0;i < s.length;i++){
			File file = new File(path+s[i]);
			Deck dd = new Deck(data);
			dd.deckType = 1;
			dd.readData(file);
			deckList.add(dd);
		}
	}
	public void writeDeck(){
		
		for(int i = 0;i < deckList.size();i++){
			int[] deckArray = new int[100];
			for(int j = 0;j < deckList.get(i).deck.size();j++){

				deckArray[deckList.get(i).deck.get(j).getNum()]+=1;
			}
			String dir = path + deckList.get(i).name+".txt";
			FileWriter fw;
			try {
				fw = new FileWriter(dir);
				BufferedWriter bfw=new BufferedWriter(fw);
				bfw.write(deckList.get(i).name+"\r\n");
				for(int j = 0;j < deckArray.length;j++){
					if(deckArray[j]!=0){
						bfw.write(j+" "+deckArray[j]+"\r\n");
					}
				}
				bfw.flush();
				fw.close();
			}catch(Exception ex){
				
			}
		}
		//writebag
		int[] deckArray = new int[100];
		for(int j = 0;j < bagCard.deck.size();j++){
			deckArray[bagCard.deck.get(j).getNum()]+=1;
		}
		String dir = "./data/bag.txt";
		FileWriter fw;
		try {
			fw = new FileWriter(dir);
			BufferedWriter bfw=new BufferedWriter(fw);
			for(int j = 0;j < deckArray.length;j++){
				if(deckArray[j]!=0){
					bfw.write(j+" "+deckArray[j]+"\r\n");
				}
			}
			bfw.flush();
			fw.close();
		}catch(Exception ex){
			
		}
	}
	public void readBag(){
		bagCard.deck = new ArrayList<Card>();
		bagCard.x = 610;
		bagCard.column = 4;
		bagCard.deckType = 2;
		File file = new File("data/bag.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				while(input!=null){
					String[] arg = input.split(" ");
					int cardNum = Integer.parseInt(arg[0]);
					int times = Integer.parseInt(arg[1]);
					for(int i = 0;i < times;i++){
						Card card = new Card(cardNum);
						data.cardDatas.CreateCard(card,cardNum);
						bagCard.deck.add(card);
					}
					input = br.readLine();
				}
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
}
