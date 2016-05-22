import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CardEffects {
	Timer timer;
	Data data;
	CardEffects(){
		
	}
	CardEffects(Data data){
		this.data = data;
	}
//	�^�O
	public void equip(Unit unit,int attribute,int num){
		Item i = unit.getItem()[0];
		switch(attribute){
		case 1: 
			i.setAtk(i.getAtk()+num);
			break;
		}
	}
	//�l��
	public void summon(Unit unit,int num){
		Unit newUnit = new Unit(unit.getX(),unit.getY());
		double r = Math.random()*10+30;
		double angle = Math.random()*360-180+1;
		int x = (int)(r * Math.cos(Math.toRadians(angle)));
		int y = (int)(r * Math.sin(Math.toRadians(angle)));
		
		data.creepDatas.CreateCreep(data.gameData,newUnit,3,unit.getX()+unit.getWidth()/2+x,unit.getY()+unit.getHeight()/2+y,1,unit.getId(),1);
		data.units.add(newUnit);
	}
	//����
	public void putTrap(Unit unit,int num){
		Trap trap = new Trap(unit.getX(),unit.getY(),num,unit.getTeam(),unit.getId());
		data.traps.add(trap);
	}
	//�^�_HP
	public void recover(Unit unit,double hp){
		if(unit.getHP()+hp<=unit.getHPmax())
			unit.setHP(unit.getHP()+hp);
		else{
			unit.setHP(unit.getHPmax());
		}
	}
//	�l��d
	public void Summon(Unit unit, int type_group)
	{
		Unit newUnit = new Unit(unit.getX(), unit.getY());
		
		double r = Math.random() * 10 + 30;
		double angle = Math.random() * 360 - 180 + 1;
		int x = (int)(r * Math.cos(Math.toRadians(angle)));
		int y = (int)(r * Math.sin(Math.toRadians(angle)));

		switch(type_group)
		{
			case 1://��ĭ��{		
				data.creepDatas.CreateCreep(data.gameData, newUnit, 3, (unit.getX() + unit.getWidth() / 2 + x), (unit.getY() + unit.getHeight() / 2 + y), 1, unit.getId(), 1);
				break;
			case 2://�`�W�Ӥk
				data.creepDatas.CreateCreep(data.gameData, newUnit, 4, (unit.getX() + unit.getWidth() / 2 + x), (unit.getY() + unit.getHeight() / 2 + y), 1, unit.getId(), 1);	
				break;
			case 3://�u�\�Q�r�x
				data.creepDatas.CreateCreep(data.gameData, newUnit, 5, (unit.getX() + unit.getWidth() / 2 + x), (unit.getY() + unit.getHeight() / 2 + y), 1, unit.getId(), 1);	
				break;
		}
		
		data.units.add(newUnit);
	}

	//�]�k�d
	public void Magic(final Unit unit, int type_group)
	{
		switch(type_group)
		{
			case 1://�Ѩϯ���
				if((unit.getHP() + 80.0) <= unit.getHPmax())
				{
					unit.setHP(unit.getHP() + 80.0);
				}
				else
				{
					unit.setHP(unit.getHPmax());
				}
				break;
			case 2://���R��
				if((unit.getMP() + 80.0) <= unit.getMPmax())
				{
					unit.setMP(unit.getMP() + 80.0);
				}
				else
				{
					unit.setMP(unit.getMPmax());
				}
				break;
			case 3://���෽������
				if((unit.getHP() + 120.0) <= unit.getHPmax())
				{
					unit.setHP(unit.getHP() + 120.0);
				}
				else
				{
					unit.setHP(unit.getHPmax());
				}
				
				if((unit.getMP() + 80.0) <= unit.getMPmax())
				{
					unit.setMP(unit.getMP() + 120.0);
				}
				else
				{
					unit.setMP(unit.getMPmax());
				}
				break;
			case 4://�]�k���g����
				unit.setAtk(unit.getAtk() + 30);
				unit.setAtkRate(unit.getAtkRate() * 1.1);
				unit.setArmor(unit.getArmor() - 10);

				timer = new Timer(10000, new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						unit.setAtk(unit.getAtk() - 30);
						unit.setAtkRate(unit.getAtkRate() / 1.1);
						unit.setArmor(unit.getArmor() + 10);
					}
				});
				timer.setRepeats(false);
				timer.start();
				break;
			case 5://���ܪ��W���~
				final int[] temp = unit.getAttribute();
				temp[1] += 5;
				unit.setAttribute(temp);
				
				timer = new Timer(5000, new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int[] temp = unit.getAttribute();
						temp[1] -= 5;
						unit.setAttribute(temp);
					}
				});
				timer.setRepeats(false);
				timer.start();
				break;
			case 6://���z�k��������
				final int[] temp2 = unit.getAttribute();
				temp2[2] += 5;
				unit.setAttribute(temp2);
				
				timer = new Timer(5000, new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int[] temp2 = unit.getAttribute();
						temp2[2] -= 5;
						unit.setAttribute(temp2);
					}
				});
				timer.setRepeats(false);
				timer.start();
				break;
		}
	}

	//�����d
	public void Traps(Unit unit, int type_group)
	{
		Trap trap = null;

		switch(type_group)
		{
			case 1://�U��a�p
				trap = new Trap(unit.getX(), unit.getY(), 1, unit.getTeam(), unit.getId());
				data.traps.add(trap);
				break;
		}
	}

	//�^�O�d
	public void Inlay(Unit unit, int type_group)
	{
		Item i = unit.getItem()[0];

		switch(type_group)
		{
			case 1://���C����
				i.setAtk(i.getAtk() + 10);
				break;
			case 2://���s�ޥ�
				i.setArmor(i.getArmor() + 10.0);
				break;
			case 3://�ջȭ��u
				i.setSpeed(i.getSpeed() + 10);
				break;
			case 4://�����L�O
				i.setManaArmor(i.getManaArmor() + 3.0);
				break;
		}
	}
}
