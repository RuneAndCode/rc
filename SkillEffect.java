
class SkillEffect
{
	int mode;	/*�ޯ�Ҧ��G0�ۨ�, 1���, 2�a�W*/
	int trace;	/*���Y���|�G0�L, 1���u, 2�ߪ��u*/
	int position;	/*�p�G�O��ۨ��A�ӯS���\��󨤦����X�ӳ��A0�������K�b���W*/
	int range;	/*�����d��*/
	int effectType;	/*�S�ĶK�Ϥ覡�G0�ʺAgif, 1�s��K��, 2�s��B����K��*/
	int time;	/*�K�϶��j(ms)*/
	int imgCount;	/*�S�Ĺϱi��*/
	/*�u��*/
	int danmaku; /*�u��:0�_1�}��*/
	int bulletType; /*�o�g�l�u����*/
	int radius;  /*�u���b�|*/
	int circleNum; /*�u���q�ۨ��e���_¶�@�骺�ƶq1=�e��,2=�e��,4=�Q�r,8=�̦r*/
	
	/*�H�U������ƭ��ܤơG�W�[�����ơA��֬��t��*/
	int HPChange;	/*��q*/
	int MPChange;	/*�k�O*/
	int StrChange;	/*�O�q*/
	int DexChange;	/*�ӱ�*/
	int IntChange;	/*���z*/
	int AtkChange;	/*�����O*/
	int AtkRangeChange;	/*�����t��*/
	double ArmorChange;	/*���m�O*/
	double MagicArmorChange;	/*�]��*/
	int SpeedChange;	/*�]�t*/
	int CardChange;	/*�d����q*/

	SkillEffect()
	{

	}

	void run()
	{

	}
}
