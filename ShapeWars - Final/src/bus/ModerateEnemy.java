package bus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import client.GameManager;

public class ModerateEnemy extends Shape
{
	int spawnTime = 500; 
	long last_time = System.nanoTime();
	boolean isDamaged = false;
	long current_time;
	long delta_time;
	
	private Point movePoint;
	
	private int scoreValue;
	
	public ModerateEnemy()
	{
		setScoreValue(5000);
		
		Point p = new Point(492,50);
		this.setInitialCoordinatePoint(p);
		this.setxVelocity(10);
		this.setyVelocity(10);
		int[] i = {100,100};
		this.setShapeSize(i);
	
		this.setHp(4);
	}

	public void CalculateShape()
	{
		//TODO:
	}
	
	public void move()
	{
		this.movePoint.y += this.getyVelocity();
		
		this.setInitialCoordinatePoint(movePoint);
		
		if(this.getInitialCoordinatePoint().y >= GameManager.instance().getWindowSizeY())
		{
			this.movePoint.y = -20;
		}		
	}

	@Override
	public void DrawShape(Graphics g)
	{
		long current_time = System.nanoTime();
		long delta_time = ((current_time - last_time) / 10000000);		
		
		if(this.isDamaged)
		{
			g.setColor(Color.white);			
			if(delta_time >= 20)
			{				
				isDamaged = false;
				last_time = current_time;
			}
		}
		else
		{
			g.setColor(Color.pink);
		}
		
		
		g.fillOval(this.getInitialCoordinatePoint().x, this.getInitialCoordinatePoint().y, this.getShapeSize()[0], this.getShapeSize()[1]);
	}
	
	public void TakeDamage()
	{
		long current_time = System.nanoTime();			
		isDamaged = true;
		last_time = current_time;
	}
	
	public Point getMovePoint() 
	{
		return movePoint;
	}

	public void setMovePoint(Point movePoint) 
	{
		this.movePoint = movePoint;
	}

	@Override
	public int getScoreValue() 
	{
		return scoreValue;
	}

	public void setScoreValue(int scoreValue) 
	{
		this.scoreValue = scoreValue;
	}
}
