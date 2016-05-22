
class SkillEffect
{
	int mode;	/*技能模式：0自身, 1對方, 2地上*/
	int trace;	/*投擲路徑：0無, 1直線, 2拋物線*/
	int position;	/*如果是放自身，該特效擺放於角色附近幾個單位，0為直接貼在身上*/
	int range;	/*偵測範圍*/
	int effectType;	/*特效貼圖方式：0動態gif, 1連續貼圖, 2連續且持續貼圖*/
	int time;	/*貼圖間隔(ms)*/
	int imgCount;	/*特效圖張數*/
	/*彈幕*/
	int danmaku; /*彈幕:0否1開啟*/
	int bulletType; /*發射子彈類型*/
	int radius;  /*彈幕半徑*/
	int circleNum; /*彈幕從自身前方算起繞一圈的數量1=前方,2=前後,4=十字,8=米字*/
	
	/*以下為角色數值變化：增加為正數，減少為負數*/
	int HPChange;	/*血量*/
	int MPChange;	/*法力*/
	int StrChange;	/*力量*/
	int DexChange;	/*敏捷*/
	int IntChange;	/*智慧*/
	int AtkChange;	/*攻擊力*/
	int AtkRangeChange;	/*攻擊速度*/
	double ArmorChange;	/*防禦力*/
	double MagicArmorChange;	/*魔防*/
	int SpeedChange;	/*跑速*/
	int CardChange;	/*卡片能量*/

	SkillEffect()
	{

	}

	void run()
	{

	}
}
