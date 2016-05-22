import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Deck extends Spirit{
	Data data;
	String name = "";//�P�զW
	int x = 215;
	int y = 26;
	int column = 8;
	int row = 6;
	ArrayList<Card> deck = new ArrayList<Card>();//�P��
	static Image cardShadow = new ImageIcon("images/menu/CarDisplay/�d�����v.png").getImage();
	static Image cardShadow2 = new ImageIcon("images/menu/CarDisplay/�d�����v_�Q���.png").getImage();
	int deckType = 1;//1,2 = �s��P��,�s��I�]
	
	public Deck(Data data){
		this.data = data;
	}
	
	public boolean clickDeck(int mx,int my,int changeTypeValue,Dimension screenSize,int screenMode){
		boolean isClicked = false;
		int offset = 0;
		//�ݬO�P���٬O�I�]
		if(deckType==1){
			offset = data.deckOffset;
		}else{
			offset = data.bagOffset;
		}
		int searchRows = 0;//�j�M��������P�Ʋզ����C��
		
		if(deck.size()==0){
			searchRows = 0;
		}else
		if(changeTypeValue==0){
			searchRows = ((deck.size()-1)/column)+1;//1-n=>��1��
		}else{
			int count = 0;
			for(int i =0;i < deck.size();i++){
				//System.out.println(deck.get(i).getName()+deck.get(i).getType());
				if(deck.get(i).getType()==changeTypeValue){
					count++;
				}
			}
			searchRows = count/column;
		}
		int index = 0;

		int w = 38;
		int h = 47;
		int i = 0;
		int j = 0;
		if(offset > searchRows - row){
			offset = searchRows - row + 1;
		}else if(offset < 0){
			offset = 0;
		}
		if(searchRows>=row){//�j�M�X�Ӫ��C�ƶW�L�e�����C��(�i�H����)
			while(index<column*row&&offset*column+index<deck.size()){
				//System.out.println(deck.get(index).getName()+deck.get(index).getType());
				
				Card card = deck.get(offset*column+index);
				boolean match = true;
				if(data.useKeyWordSearchCard){
					match = false;
					//System.out.println(card.getCompareName());
					//System.out.println(data.compareSearchCard);
					if(card.getCompareName().matches(data.compareSearchCard)){
						match = true;
					}
				}
				if(match)
				if(changeTypeValue==0){
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(mx, my)){
						if(offset<0)
							offset = 0;
						moveCard(offset*column+index,card,data.nowDeck);//�N��e�o�i�Ƥ@��ؼеP��
						isClicked = true;
					}
					if(i<column-1)
						i++;
					else{
						i = 0;
						j++;
					}
				}else{
					if(card.getType()==changeTypeValue){
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(mx, my)){
							if(offset<0)
								offset = 0;
							moveCard(offset*column+index,card,data.nowDeck);//�N��e�o�i�Ƥ@��ؼеP��
							isClicked = true;
						}
						if(i<column-1)
							i++;
						else{
							i = 0;
							j++;
						}
					}else{
						//System.out.println(deck.get(i).getName()+deck.get(i).getType());
					}
				}
				index++;
			}
		}else{
			while(index<deck.size()){
				Card card = deck.get(index);
				boolean match = true;
				if(data.useKeyWordSearchCard){
					match = false;
					if(card.getCompareName().matches(data.compareSearchCard)){
						match = true;
					}
				}
				if(match)
				if(changeTypeValue==0){
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(mx, my)){
						if(offset<0)
							offset = 0;
						moveCard(offset*column+index,card,data.nowDeck);//�N��e�o�i�Ƥ@��ؼеP��
						isClicked = true;
					}
					if(i<column-1)
						i++;
					else{
						i = 0;
						j++;
					}
				}else{
					if(card.getType()==changeTypeValue){
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(mx, my)){
							if(offset<0)
								offset = 0;
							moveCard(offset*column+index,card,data.nowDeck);//�N��e�o�i�Ƥ@��ؼеP��
							isClicked = true;
						}
						if(i<column-1)
							i++;
						else{
							i = 0;
							j++;
						}
					}else{
						
					}
					
				}
				
				index++;
			}
		}
		return isClicked;
	}
	public void moveCard(int cardNum,Card card,Deck deck){
		if(deckType==1){//���p�O�P�ը��N����I�]
			Card cardtemp = new Card(card.getNum());
			data.cardDatas.CreateCard(cardtemp,card.getNum());
			data.deckDatas.bagCard.deck.add(card);
			deck.deck.remove(cardNum);
		}else{//���p�O�I�]���N����P��
			Card cardtemp = new Card(card.getNum());
			data.cardDatas.CreateCard(cardtemp,card.getNum());
			data.nowDeck.deck.add(cardtemp);
			data.deckDatas.bagCard.deck.remove(cardNum);
			//data.deckDatas.bagCard.deck.remove(data.deckDatas.bagCard.deck.size()-cardNum-1);
			//deck.deck.remove(cardNum);
		}
	}
	public void paint(int changeTypeValue,Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		int offset = 0;
		//�ݬO�P���٬O�I�]
		if(deckType==1){
			offset = data.deckOffset;
		}else{
			offset = data.bagOffset;
		}
		int searchRows = 0;//�j�M��������P�Ʋզ����C��
		
		if(deck.size()==0){
			searchRows = 0;
		}else
		if(changeTypeValue==0){
			searchRows = ((deck.size()-1)/column)+1;//1-n=>��1��
		}else{
			int count = 0;
			for(int i =0;i < deck.size();i++){
				//System.out.println(deck.get(i).getName()+deck.get(i).getType());
				if(deck.get(i).getType()==changeTypeValue){
					count++;
				}
			}
			searchRows = count/column;
		}
		int index = 0;

		int w = 38;
		int h = 47;
		int i = 0;
		int j = 0;
		if(offset > searchRows - row){
			offset = searchRows - row + 1;
		}else if(offset < 0){
			offset = 0;
		}
		if(searchRows>=row){//�j�M�X�Ӫ��C�ƶW�L�e�����C��(�i�H����)
			while(index<column*row&&offset*column+index<deck.size()){
				//System.out.println(deck.get(index).getName()+deck.get(index).getType());
				
				Card card = deck.get(offset*column+index);
				boolean match = true;
				if(data.useKeyWordSearchCard){
					match = false;
					//System.out.println(card.getCompareName());
					//System.out.println(data.compareSearchCard);
					if(card.getCompareName().matches(data.compareSearchCard)){
						match = true;
					}
				}
				if(match)
				if(changeTypeValue==0){
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
						drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					}else{
						drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					}
					
					drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
						Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
						Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,guide,344,416,io);
						
					}
					if(i<column-1)
						i++;
					else{
						i = 0;
						j++;
					}
				}else{
					if(card.getType()==changeTypeValue){
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my))
							drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						else
							drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
							Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
							Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,guide,344,416,io);
							
						}
						if(i<column-1)
							i++;
						else{
							i = 0;
							j++;
						}
					}else{
						//System.out.println(deck.get(i).getName()+deck.get(i).getType());
					}
				}
				index++;
			}
		}else{
			while(index<deck.size()){
				Card card = deck.get(index);
				boolean match = true;
				if(data.useKeyWordSearchCard){
					match = false;
					if(card.getCompareName().matches(data.compareSearchCard)){
						match = true;
					}
				}
				if(match)
				if(changeTypeValue==0){
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my))
					drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					else
						drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
						Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
						Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,guide,344,416,io);
						
					}
					if(i<column-1)
						i++;
					else{
						i = 0;
						j++;
					}
				}else{
					if(card.getType()==changeTypeValue){
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my))
						drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						else
							drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
							Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
							Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,guide,344,416,io);
							
						}
						if(i<column-1)
							i++;
						else{
							i = 0;
							j++;
						}
					}else{
						
					}
					
				}
				
				index++;
			}
		}
	}
	//�e�w��
	public void paintPreview(int changeTypeValue,Dimension screenSize,int screenMode,Graphics g,ImageObserver io){
		int offset = 0;
		//�ݬO�P���٬O�I�]
		if(deckType==1){
			offset = data.deckOffset;
		}else{
			offset = data.bagOffset;
		}
		int searchRows = 0;//�j�M��������P�Ʋզ����C��
		
		if(deck.size()==0){
			searchRows = 0;
		}else
		if(changeTypeValue==0){
			searchRows = ((deck.size()-1)/column)+1;//1-n=>��1��
		}else{
			int count = 0;
			for(int i =0;i < deck.size();i++){
				//System.out.println(deck.get(i).getName()+deck.get(i).getType());
				if(deck.get(i).getType()==changeTypeValue){
					count++;
				}
			}
			searchRows = count/column;
		}
		int index = 0;

		int w = 38;
		int h = 47;
		int i = 0;
		int j = 0;
		if(offset > searchRows - row){
			offset = searchRows - row + 1;
		}else if(offset < 0){
			offset = 0;
		}
		if(searchRows>=row){//�j�M�X�Ӫ��C�ƶW�L�e�����C��(�i�H����)
			while(index<column*row&&offset*column+index<deck.size()){
				//System.out.println(deck.get(index).getName()+deck.get(index).getType());
				
				Card card = deck.get(offset*column+index);
				boolean match = true;
				if(data.useKeyWordSearchCard){
					match = false;
					//System.out.println(card.getCompareName());
					//System.out.println(data.compareSearchCard);
					if(card.getCompareName().matches(data.compareSearchCard)){
						match = true;
					}
				}
				if(match)
				if(changeTypeValue==0){
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
						drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					}else{
						drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					}
					
					drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
						Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
						Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,guide,344,416,io);
						
					}
					if(i<column-1)
						i++;
					else{
						i = 0;
						j++;
					}
				}else{
					if(card.getType()==changeTypeValue){
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my))
							drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						else
							drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
							Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
							Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,guide,344,416,io);
							
						}
						if(i<column-1)
							i++;
						else{
							i = 0;
							j++;
						}
					}else{
						//System.out.println(deck.get(i).getName()+deck.get(i).getType());
					}
				}
				index++;
			}
		}else{
			while(index<deck.size()){
				Card card = deck.get(index);
				boolean match = true;
				if(data.useKeyWordSearchCard){
					match = false;
					if(card.getCompareName().matches(data.compareSearchCard)){
						match = true;
					}
				}
				if(match)
				if(changeTypeValue==0){
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my))
					drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					else
						drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
					drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
					if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
						Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
						Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
						drawImage(screenSize, screenMode,g,guide,344,416,io);
						
					}
					if(i<column-1)
						i++;
					else{
						i = 0;
						j++;
					}
				}else{
					if(card.getType()==changeTypeValue){
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my))
						drawImage(screenSize, screenMode,g,cardShadow2,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						else
							drawImage(screenSize, screenMode,g,cardShadow,x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8,io);
						drawImage(screenSize, screenMode,g,card.getImage(),x+i*(w+8),y+j*(h+8),w,h,io);
						if(new Button(x+i*(w+8)-4,y+j*(h+8)-4,w+8,h+8).isClicked(data.mx, data.my)){
							Image group = new ImageIcon("images/group/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,group,204,416,140,175,io);
							Image guide = new ImageIcon("images/guide/"+card.getNum()+".png").getImage();
							drawImage(screenSize, screenMode,g,guide,344,416,io);
							
						}
						if(i<column-1)
							i++;
						else{
							i = 0;
							j++;
						}
					}else{
						
					}
					
				}
				
				index++;
			}
		}
	}
	public void readDeck(int num){
		readData(new File("/data/deck/"+num));
	}
	public void readData(File file){
		int cardNum;
		deck = new ArrayList<Card>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String input = "";
			try {
				input = br.readLine();
				name = input;
				input = br.readLine();
				while(input!=null){
					String[] cmd = input.split(" ");
					cardNum = Integer.parseInt(cmd[0]);
					int numOfCard = Integer.parseInt(cmd[1]);
					for(int i =0;i < numOfCard;i++){

						Card card = new Card(cardNum);
						data.cardDatas.CreateCard(card,cardNum);
						//card.setType(data.cardDatas.list.get(cardNum-1).getType());
						deck.add(card);
					}
					
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
}
