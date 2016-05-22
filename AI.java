
public class AI {
	Region leftUp = new Region(100,100,300,300);
	Region leftDown = new Region(100,4750,300,300);
	Region rightDown = new Region(4690,4750,300,300);
	Region mid = new Region(2400,2400,300,300);
	Region rightUp = new Region(4690,100,300,300);
	Thread skillThread = null;
	boolean canSkill = true;
	AI(){
		
	}
	public void createSkillThread(final Data data,final Unit unit){
		skillThread = new Thread(){
			public void run(){
				while(canSkill){
					try {
						//�C����
						sleep(5000);
						//�P�򦳼ĤH�N�o�ʧޯ�
						boolean hasEnemy = false;
						for(int i = 0;i < data.units.size();i++){
							Unit enemy = data.units.get(i);
							if(enemy!=null){
								if(enemy.getTeam()!=unit.getTeam()){

									int x = unit.getX()-300;
									int y = unit.getY()-300;
									int w = 600;
									int h = 600;
									if(new Region(x,y,w,h).isClicked(enemy.getX(), enemy.getY())){

										hasEnemy = true;
									}
								}
							}
						}
						if(hasEnemy){

							int skillNum = (int)(Math.random()*4);
							data.skillData.activate(unit,skillNum);
						}
					} catch (InterruptedException e) {
						// TODO �۰ʲ��� catch �϶�
						e.printStackTrace();
					}
				}
			}
		};
		skillThread.start();
	}
	public void startSkillThread(){
		canSkill = true;
	}
	public void stopSkillThread(){
		canSkill = false;
	}
	public void runAI(Unit u,Data data){
		int x = u.getX();
		int y = u.getY();
		switch(u.getAI()){
			case 0://�L
				//u.setMotion(2);
				break;
			case 1://����W���p�LAI
				switch(u.getAIstep()){
					case 1://�٨S���쥪�W
					if(!leftUp.isClicked(x, y)){
						
						//u.findPath(data.ta,250,250);
						//if(u.getMotion()==0){
						//	//u.findPath(data.ta,rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
						//	u.findPath(data.ta,leftUp.getX()+leftUp.getWidth()/2,leftUp.getY()+leftUp.getHeight()/2);
						//}
							
							u.inst(leftUp.getX()+leftUp.getWidth()/2, leftUp.getY()+leftUp.getHeight()/2);
						
						u.setMotion(2);
					}else{
						u.setAIstep(2);
					}
					break;
					case 2://�٨S���W�k�W
						
					if(!rightUp.isClicked(x, y)){
						//if(u.getMotion()==0){
						//	System.out.println("�k�W");
						//	u.findPath(data.ta,rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
						//}
							
						
						u.inst(rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
						//u.findPath(data.ta,rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
						u.setMotion(2);
					}else{
						u.setAIstep(3);
					}
					break;
				}
				
				break;
			case 2://���䤤���p�LAI
				switch(u.getAIstep()){
				case 1://�٨S���줤��
				if(!mid.isClicked(x, y)){
					//u.findPath(data.ta,mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
					u.inst(mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(2);
				}
				break;
				case 2://�٨S���W�k�W
					//System.out.println("�k�W");
				if(!rightUp.isClicked(x, y)){
					//u.findPath(data.ta,rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
					u.inst(rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(3);
				}
				break;
			}
			
			break;
			case 3://����U���p�LAI
				switch(u.getAIstep()){
				case 1://�٨S����k�U
				if(!rightDown.isClicked(x, y)){
					//u.findPath(data.ta,rightDown.getX()+rightDown.getWidth()/2, rightDown.getY()+rightDown.getHeight()/2);
					u.inst(rightDown.getX()+rightDown.getWidth()/2, rightDown.getY()+rightDown.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(2);
				}
				break;
				case 2://�٨S���W�k�W
					//System.out.println("�k�W");
				if(!rightUp.isClicked(x, y)){
					//u.findPath(data.ta,rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
					u.inst(rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(3);
				}
				break;
				}
				break;
			case 4://�k��W���p�LAI
				switch(u.getAIstep()){
				case 1://�٨S���쥪�W
				if(!leftUp.isClicked(x, y)){
					//u.findPath(data.ta,leftUp.getX()+leftUp.getWidth()/2, leftUp.getY()+leftUp.getHeight()/2);
					u.inst(leftUp.getX()+leftUp.getWidth()/2, leftUp.getY()+leftUp.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(2);
				}
				break;
				case 2://�٨S���쥪�U
				if(!leftDown.isClicked(x, y)){
					//u.findPath(data.ta,leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
					u.inst(leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(3);
				}
				break;
				}
				break;
			case 5://�k�䤤���p�LAI
				switch(u.getAIstep()){
				case 1://�٨S���줤��
					if(!mid.isClicked(x, y)){
						//u.findPath(data.ta,mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
						u.inst(mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
						u.setMotion(2);
					}else{
						u.setAIstep(2);
					}
					break;
				case 2://�٨S���쥪�U
				if(!leftDown.isClicked(x, y)){
					//u.findPath(data.ta,leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
					u.inst(leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(3);
				}
				break;
				}
				break;
			case 6://�k��U���p�LAI
				switch(u.getAIstep()){
				case 1://�٨S����k�U
					if(!rightDown.isClicked(x, y)){
						//u.findPath(data.ta,rightDown.getX()+rightDown.getWidth()/2, rightDown.getY()+rightDown.getHeight()/2);
						u.inst(rightDown.getX()+rightDown.getWidth()/2, rightDown.getY()+rightDown.getHeight()/2);
						u.setMotion(2);
					}else{
						u.setAIstep(2);
					}
					break;
				case 2://�٨S���쥪�U
				if(!leftDown.isClicked(x, y)){
					//u.findPath(data.ta,leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
					u.inst(leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
					u.setMotion(2);
				}else{
					u.setAIstep(3);
				}
				break;
				}
				break;
			case 7://����W���^��AI
				break;
			case 8://���䤤���^��AI
				break;
			case 9://����U���^��AI
				break;
			case 10://�k��W���^��AI
				break;
			case 11://�k�䤤���^��AI
				if(u.getHP()>u.getHPmax()/2){//�W�L�@�b��q�O�w��

					switch(u.getAIstep()){
					case 1://�٨S���줤��
						if(!mid.isClicked(x, y)){
							//u.findPath(data.ta,mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
							u.inst(mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
							u.setMotion(2);
							u.retreat=false;
						}else{
							u.setAIstep(2);
						}
						break;
					case 2://�٨S���쥪�U
					if(!leftDown.isClicked(x, y)){
						//u.findPath(data.ta,leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
						u.inst(leftDown.getX()+leftDown.getWidth()/2, leftDown.getY()+leftDown.getHeight()/2);
						u.setMotion(2);
						u.retreat=false;
					}else{
						u.setAIstep(3);
					}
					break;
					case 3:
						if(u.getHP()>=u.getHPmax()){
							u.retreat=false;
							u.setAIstep(1);;
						}
					
					break;
					}
					
				}else{//�M�h
					switch(u.getAIstep()){
					case 1://�٨S���줤��
					if(!mid.isClicked(x, y)){
						//u.findPath(data.ta,mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
						u.inst(mid.getX()+mid.getWidth()/2, mid.getY()+mid.getHeight()/2);
						u.setMotion(2);
						u.retreat = true;
					}else{
						u.setAIstep(2);
					}
					break;
					case 2://�٨S���W�k�W
						//System.out.println("�k�W");
					if(!rightUp.isClicked(x, y)){
						//u.findPath(data.ta,rightUp.getX()+rightUp.getWidth()/2, rightUp.getY()+rightUp.getHeight()/2);
						u.inst(4800, 200);
						u.setMotion(2);
						u.retreat = true;
					}else{
						u.setAIstep(3);
					}
					break;
					case 3:
						if(u.getHP()>=u.getHPmax()){
							u.retreat=false;
							u.setAIstep(1);;
						}
					}
				}
				break;
			case 12://�k��U���^��AI
				break;
			case 13://CP��AI
				break;
		}
	}
}
