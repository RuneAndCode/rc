//A*尋路算法用的磚塊節點
public class Block {
	//當前index
	int x = 0;
	int y = 0;
	//成本
	int cost = 0;
	//連結的parent
	Block parent;
	Block(){
		
	}
	Block(int x,int y){
		this.x = x;
		this.y = y;
	}
}
