package bus;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnManager 
{
	private Random rand1 = new Random();
	private Random rand2 = new Random();
	
	int spawnTime = 200; //miliseconds
	
	private List<IShape> listOfEnemies_ = new ArrayList<IShape>();
	
	private static SpawnManager instance_;
	
	private int randOpt;
	
	private double spawnXCoordLimits;
	
	long last_time = System.nanoTime();	
	
	public SpawnManager()
	{
		
	}
	
	public static SpawnManager instance()
	{
		if(instance_ == null)
		{
			instance_ = new SpawnManager();
		}
		return instance_;
	}	
	
	public void GenerateEnemies()
	{

		long current_time = System.nanoTime();
		long delta_time = ((current_time - last_time) / 10000000);		
		
		if(delta_time >= spawnTime)
		{
			randOpt = rand1.nextInt(2);
			
			if(randOpt == 0)
			{
				WeakEnemy e0 = new WeakEnemy();
				double coordX = rand2.nextInt((int)this.spawnXCoordLimits);
				e0.setInitialCoordinatePoint(new Point((int)coordX, e0.getInitialCoordinatePoint().y));
				e0.setMovePoint(new Point((int)coordX, e0.getInitialCoordinatePoint().y));
				this.listOfEnemies_.add(e0);
				last_time = current_time;
				return;
			}
			else if(randOpt == 1)
			{
				ModerateEnemy e1 = new ModerateEnemy();				
				double coordX = rand2.nextInt((int)this.spawnXCoordLimits);
				e1.setInitialCoordinatePoint(new Point((int)coordX, e1.getInitialCoordinatePoint().y));
				e1.setMovePoint(new Point((int)coordX, e1.getInitialCoordinatePoint().y));
				this.listOfEnemies_.add(e1);
				last_time = current_time;
				return;
			}
			last_time = current_time;			
		}
		
		
		
	}

	public void setSpawnXCoordLimits(double spawnXCoordLimits) 
	{
		this.spawnXCoordLimits = spawnXCoordLimits - (spawnXCoordLimits * 0.04);
		//System.out.println(this.spawnXCoordLimits);
	}
	
	public void SpawnEnemies(Graphics g)
	{		
		for(IShape sh : this.listOfEnemies_)
		{
			sh.DrawShape(g);			
		}
	}
	
	public List<IShape> getListOfEnemies() 
	{
		return listOfEnemies_;
	}

	public void setListOfEnemies(List<IShape> listOfEnemies_) 
	{
		this.listOfEnemies_ = listOfEnemies_;
	}
	
	public void RemoveEnemyFromList(IShape enemy)
	{
		this.listOfEnemies_.remove(enemy);
	}
	
	
	
	
	
}
