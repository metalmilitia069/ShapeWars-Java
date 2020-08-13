package bus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
//import java.awt.List;
import java.util.ArrayList;
import java.util.List;

import client.GameManager;

public class Projectile extends Shape 
{
	private int projectileType = 1;
	private int spawnX;
	private int spawnY;
	private Point movePoint;
	
	long current_time;
	long delta_time;
	//long last_time = System.nanoTime();
	
//	private List<Projectile> listOfProjectiles_ = new ArrayList<Projectile>();
	
	public Projectile(Player p, int type)
	{
		this.setyVelocity(10);
		this.setxVelocity(3);
		spawnX = p.getInitialCoordinatePoint().x + (p.getShapeSize()[0]/2);
		spawnY = p.getInitialCoordinatePoint().y - 10;		
		this.projectileType = type;
		this.movePoint = new Point(spawnX, spawnY); 
	}
	
	@Override
	public void DrawShape(Graphics g)
	{
		if(this.projectileType == 1)
		{
			g.setColor(Color.yellow);
			g.fillRect(movePoint.x, movePoint.y, 5, 10);
		}
		
		else if(this.projectileType == 2)
		{
			g.setColor(Color.orange);
			g.fillOval(movePoint.x - 20, movePoint.y, 15, 15);			
		}
		
		else if(this.projectileType == 3)
		{
			g.setColor(Color.orange);
			g.fillOval(movePoint.x, movePoint.y, 15, 15);
		}
		
		else if(this.projectileType == 4)
		{
			g.setColor(Color.orange);
			g.fillOval(movePoint.x + 20, movePoint.y, 15, 15);
		}
		
		else if(this.projectileType == 5)
		{
			g.setColor(Color.cyan);
			g.fillOval(movePoint.x - 20, movePoint.y, 7, 15);			
		}
		
		else if(this.projectileType == 6)
		{
			g.setColor(Color.cyan);
			g.fillOval(movePoint.x + 18, movePoint.y, 7, 15);			
		}
		
	}
	
	public void move()
	{
		if(this.projectileType == 1 || this.projectileType == 5 || this.projectileType == 6)
		{
			movePoint.y -= this.getyVelocity();
		}
		else if(this.projectileType == 2)
		{
			movePoint.y -= this.getyVelocity();
			movePoint.x -= this.getxVelocity();
		}
		else if(this.projectileType == 3)
		{
			movePoint.y -= this.getyVelocity();
		}
		else if(this.projectileType == 4)
		{
			movePoint.y -= this.getyVelocity();
			movePoint.x += this.getxVelocity();
		}
		
		
		this.setInitialCoordinatePoint(movePoint);
	}

	public Point getMovePoint() 
	{
		return movePoint;
	}

	public void setMovePoint(Point movePoint) 
	{
		this.movePoint = movePoint;
	}
	
	public boolean CheckForCollision(IShape enemy)
	{
		if(this.getMovePoint().y >= enemy.getInitialCoordinatePoint().y && this.getMovePoint().y <= enemy.getInitialCoordinatePoint().y + enemy.getShapeSize()[1])
		{
			if(this.projectileType == 1)
			{
				if(this.getMovePoint().x >= enemy.getInitialCoordinatePoint().x && this.getMovePoint().x <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{
					return true;
				}
			}
			else if(this.projectileType == 2)
			{
				if(this.getMovePoint().x - 20 >= enemy.getInitialCoordinatePoint().x && this.getMovePoint().x - 20 <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{
					return true;
				}
			}
			else if(this.projectileType == 3)
			{
				if(this.getMovePoint().x >= enemy.getInitialCoordinatePoint().x && this.getMovePoint().x <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{
					return true;
				}
			}
			else if(this.projectileType == 4)
			{
				if(this.getMovePoint().x + 20 >= enemy.getInitialCoordinatePoint().x && this.getMovePoint().x + 20 <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{
					return true;
				}
			}
			else if(this.projectileType == 5)
			{
				if(this.getMovePoint().x - 20 >= enemy.getInitialCoordinatePoint().x && this.getMovePoint().x - 20 <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{
					return true;
				}
			}
			else if(this.projectileType == 6)
			{
				if(this.getMovePoint().x + 18 >= enemy.getInitialCoordinatePoint().x && this.getMovePoint().x + 18 <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{
					return true;
				}
			}
			
		}
		return false;
	}
	
//	public void Shoot(Player p, int type)
//	{
//		long current_time = System.nanoTime();
//		long delta_time = ((current_time - last_time) / 10000000);
//		
//		if(delta_time >= 20)
//		{				
//			//isDamaged = false;
//			Projectile p = new Projectile(GameManager.instance().p, 1);
//			GameManager.instance().AddProjectileToList(p);
//			last_time = current_time;
//		}
//		
//	}
	
//	public void AddProjectileToList()
//	{
//		
//	}

//	public List<Projectile> getListOfProjectiles_() 
//	{
//		return listOfProjectiles_;
//	}

//	public void setListOfProjectiles_(List<Projectile> listOfProjectiles_) 
//	{
//		this.listOfProjectiles_ = listOfProjectiles_;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
}
