package bus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import client.GameManager;

public class Player extends Shape
{
	private List<Point> leftWing = new ArrayList<Point>();
	private List<Point> rightWing = new ArrayList<Point>();
	
	private int[] leftWingX = {0,0,0,0,0,0};
	private int[] leftWingY = {0,0,0,0,0,0};
	
	private int[] rightWingX = {0,0,0,0,0,0};
	private int[] rightWingY = {0,0,0,0,0,0};
	
	private int[] shipBodyX = {0,0,0,0,0,0,0,0,0};
	private int[] shipBodyY = {0,0,0,0,0,0,0,0,0};
	
	private int[] leftEngineX = {0,0,0,0,0,0,0};
	private int[] leftEngineY = {0,0,0,0,0,0,0};	
	
	private int[] rightEngineX = {0,0,0,0,0,0,0};
	private int[] rightEngineY = {0,0,0,0,0,0,0};
	
	private int[] cockpitX = {0,0,0,0,0,0};	
	private int[] cockpitY = {0,0,0,0,0,0};
	
	long last_death_time = System.nanoTime();
	long death_anim_time = 50;
	
	public void CalculateShape()
	{
		int iniX = this.getInitialCoordinatePoint().x;
		int iniY = this.getInitialCoordinatePoint().y;
		
		int shapeSizeX = this.getShapeSize()[0];
		int shapeSizeY = this.getShapeSize()[1];
		
		
		
		
		//leftwing has 6 points
		
		leftWingX[0] = iniX + (int)(shapeSizeX * 0.3823);
		leftWingX[1] = iniX + (int)(shapeSizeX * 0.4515);
		leftWingX[2] = iniX + (int)(shapeSizeX * 0.4515);
		leftWingX[3] = iniX + (int)(shapeSizeX * 0.3931);
		leftWingX[4] = iniX + (int)(shapeSizeX * 0.1939);
		leftWingX[5] = iniX + (int)(shapeSizeX * 0.0693);
		
		leftWingY[0] = iniY + (int)(shapeSizeY * (1-0.9628));
		leftWingY[1] = iniY + (int)(shapeSizeY * (1-0.8812));
		leftWingY[2] = iniY + (int)(shapeSizeY * (1-0.8254));
		leftWingY[3] = iniY + (int)(shapeSizeY * (1-0.3025));
		leftWingY[4] = iniY + (int)(shapeSizeY * (1-0.0424));
		leftWingY[5] = iniY + (int)(shapeSizeY * (1-0.2142));
		
		//Right Wing has 6 points
		
		rightWingX[0] = iniX + (int)(shapeSizeX * (0.6177));
		rightWingX[1] = iniX + (int)(shapeSizeX * (0.5485));
		rightWingX[2] = iniX + (int)(shapeSizeX * (0.5485));
		rightWingX[3] = iniX + (int)(shapeSizeX * (0.6069));
		rightWingX[4] = iniX + (int)(shapeSizeX * (0.8061));
		rightWingX[5] = iniX + (int)(shapeSizeX * (0.9307));
		
		rightWingY[0] = iniY + (int)(shapeSizeY * (1-0.9628));
		rightWingY[1] = iniY + (int)(shapeSizeY * (1-0.8812));
		rightWingY[2] = iniY + (int)(shapeSizeY * (1-0.8254));
		rightWingY[3] = iniY + (int)(shapeSizeY * (1-0.3025));
		rightWingY[4] = iniY + (int)(shapeSizeY * (1-0.0424));
		rightWingY[5] = iniY + (int)(shapeSizeY * (1-0.2142));
		
		//Cockpit
		
		shipBodyX[0] = iniX + (int)(shapeSizeX * (0.5));
		shipBodyX[1] = iniX + (int)(shapeSizeX * (0.4522));
		shipBodyX[2] = iniX + (int)(shapeSizeX * (0.3937));
		shipBodyX[3] = iniX + (int)(shapeSizeX * (0.3937));
		shipBodyX[4] = iniX + (int)(shapeSizeX * (0.4259));
		shipBodyX[5] = iniX + (int)(shapeSizeX * (0.5741));
		shipBodyX[6] = iniX + (int)(shapeSizeX * (0.6063));
		shipBodyX[7] = iniX + (int)(shapeSizeX * (0.6063));
		shipBodyX[8] = iniX + (int)(shapeSizeX * (0.5478));
		
		
		shipBodyY[0] = iniY + (int)(shapeSizeY * (1-0.8789));
		shipBodyY[1] = iniY + (int)(shapeSizeY * (1-0.8311));
		shipBodyY[2] = iniY + (int)(shapeSizeY * (1-0.6282));
		shipBodyY[3] = iniY + (int)(shapeSizeY * (1-0.3082));
		shipBodyY[4] = iniY + (int)(shapeSizeY * (1-0.1199));
		shipBodyY[5] = iniY + (int)(shapeSizeY * (1-0.1199));
		shipBodyY[6] = iniY + (int)(shapeSizeY * (1-0.3082));
		shipBodyY[7] = iniY + (int)(shapeSizeY * (1-0.6282));
		shipBodyY[8] = iniY + (int)(shapeSizeY * (1-0.8311));
		
		//Left Engine Has 7 points
		
		leftEngineX[0] = iniX + (int)(shapeSizeX * (0.4739));
		leftEngineX[1] = iniX + (int)(shapeSizeX * (0.3931));
		leftEngineX[2] = iniX + (int)(shapeSizeX * (0.3641));
		leftEngineX[3] = iniX + (int)(shapeSizeX * (0.3641));
		leftEngineX[4] = iniX + (int)(shapeSizeX * (0.3789));
		leftEngineX[5] = iniX + (int)(shapeSizeX * (0.4535));
		leftEngineX[6] = iniX + (int)(shapeSizeX * (0.4739));
		
		
		leftEngineY[0] = iniY + (int)(shapeSizeY * (1-0.3025));
		leftEngineY[1] = iniY + (int)(shapeSizeY * (1-0.3025));
		leftEngineY[2] = iniY + (int)(shapeSizeY * (1-0.2647));
		leftEngineY[3] = iniY + (int)(shapeSizeY * (1-0.1066));
		leftEngineY[4] = iniY + (int)(shapeSizeY * (1-0.0803));
		leftEngineY[5] = iniY + (int)(shapeSizeY * (1-0.0803));
		leftEngineY[6] = iniY + (int)(shapeSizeY * (1-0.1155));
		
		//Right ENgine Has 7 Points
		
		rightEngineX[0] = iniX + (int)(shapeSizeX * (0.5261));
		rightEngineX[1] = iniX + (int)(shapeSizeX * (0.6069));
		rightEngineX[2] = iniX + (int)(shapeSizeX * (0.6359));
		rightEngineX[3] = iniX + (int)(shapeSizeX * (0.6359));
		rightEngineX[4] = iniX + (int)(shapeSizeX * (0.6211));
		rightEngineX[5] = iniX + (int)(shapeSizeX * (0.5465));
		rightEngineX[6] = iniX + (int)(shapeSizeX * (0.5261));
		
		rightEngineY[0] = iniY + (int)(shapeSizeY * (1-0.3025));
		rightEngineY[1] = iniY + (int)(shapeSizeY * (1-0.3025));
		rightEngineY[2] = iniY + (int)(shapeSizeY * (1-0.2647));
		rightEngineY[3] = iniY + (int)(shapeSizeY * (1-0.1066));
		rightEngineY[4] = iniY + (int)(shapeSizeY * (1-0.0803));
		rightEngineY[5] = iniY + (int)(shapeSizeY * (1-0.0803));
		rightEngineY[6] = iniY + (int)(shapeSizeY * (1-0.1155));
		
		//Cockpit Has 6 points
		
		cockpitX[0] = iniX + (int)(shapeSizeX * (0.5233));
		cockpitX[1] = iniX + (int)(shapeSizeX * (0.4767));
		cockpitX[2] = iniX + (int)(shapeSizeX * (0.4253));
		cockpitX[3] = iniX + (int)(shapeSizeX * (0.4253));
		cockpitX[4] = iniX + (int)(shapeSizeX * (0.5747));
		cockpitX[5] = iniX + (int)(shapeSizeX * (0.5747));
		
		cockpitY[0] = iniY + (int)(shapeSizeY * (1-0.8225));
		cockpitY[1] = iniY + (int)(shapeSizeY * (1-0.8225));
		cockpitY[2] = iniY + (int)(shapeSizeY * (1-0.5611));
		cockpitY[3] = iniY + (int)(shapeSizeY * (1-0.5));
		cockpitY[4] = iniY + (int)(shapeSizeY * (1-0.5));
		cockpitY[5] = iniY + (int)(shapeSizeY * (1-0.5611));
		
		
	}
	
	@Override
	public void DrawShape(Graphics g) 
	{
		//g.drawPolygon(p);
		g.setColor(Color.magenta);//Color.lightGray);
		g.drawPolygon(leftWingX, leftWingY, 6);
		g.fillPolygon(leftWingX, leftWingY, 6);
		
		//g.setColor(Color.red);
		g.drawPolygon(rightWingX, rightWingY, 6);
		g.fillPolygon(rightWingX, rightWingY, 6);
		
		g.setColor(Color.red);
		g.drawPolygon(leftEngineX, leftEngineY, 7);
		g.fillPolygon(leftEngineX, leftEngineY, 7);
		
		//g.setColor(Color.lightGray);
		g.drawPolygon(rightEngineX, rightEngineY, 7);
		g.fillPolygon(rightEngineX, rightEngineY, 7);
				
		g.setColor(Color.orange);
		g.drawPolygon(shipBodyX, shipBodyY, 9);
		g.fillPolygon(shipBodyX, shipBodyY, 9);
		
		g.setColor(Color.black);
		g.drawPolygon(cockpitX, cockpitY, 6);
		g.fillPolygon(cockpitX, cockpitY, 6);
		
		
		
	}
	
	public void DrawDeath(Graphics g)
	{
		long current_time = System.nanoTime();
		long delta_time = ((current_time - last_death_time) / 10000000);		
		
        g.setColor(Color.orange);
		
		int fontSize = 30;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
//		g.drawString("POW!!!", ((this.getInitialCoordinatePoint().x + (this.getShapeSize()[0] / 2))), ((this.getInitialCoordinatePoint().y + (this.getShapeSize()[1] / 2))));
		
//		iniX + (int)(shapeSizeX
		
		if(delta_time >= this.death_anim_time)
		{
//			g.fillRect((this.getInitialCoordinatePoint().x + (this.getShapeSize()[0] / 2)), (this.getInitialCoordinatePoint().y + ((this.getShapeSize()[1] / 2) - 30)), 40, 40);
			g.fillRect((this.getInitialCoordinatePoint().x + (this.getShapeSize()[0] / 2)), (this.getInitialCoordinatePoint().y + ((this.getShapeSize()[1] / 2) - 30)), 100, 40);
			
			if(delta_time >= this.death_anim_time + 50)
			{
				//g.fillRect((this.getInitialCoordinatePoint().x + (this.getShapeSize()[0] / 2)), (this.getInitialCoordinatePoint().y + ((this.getShapeSize()[1] / 2) - 30)), 60, 60);
				
				if(delta_time >= this.death_anim_time + 100)
				{
					//g.fillRect((this.getInitialCoordinatePoint().x + (this.getShapeSize()[0] / 2)), (this.getInitialCoordinatePoint().y + ((this.getShapeSize()[1] / 2) - 30)), 80, 80);
					last_death_time = current_time;
				}
			}
		}
		g.setColor(Color.yellow);
		g.drawString("POW!!!", ((this.getInitialCoordinatePoint().x + (this.getShapeSize()[0] / 2))), ((this.getInitialCoordinatePoint().y + (this.getShapeSize()[1] / 2))));
		
		fontSize = 20;
		g.drawString("YOU DIED :( PRESS ENTER TO RE-SPAWN", ((int)(GameManager.instance().getWindowSizeX()/2 - (GameManager.instance().getWindowSizeX() * .2))), (int)(GameManager.instance().getWindowSizeY() /2));
		
		
		
	}

	public Player()
	{
		this.setLeftAcceleration(false);
		this.setRightAcceleration(false);
		
		Point p = new Point(492,660);
		this.setInitialCoordinatePoint(p);
		this.setxVelocity(10);
		this.setyVelocity(10);
		int[] i = {100,100};
		this.setShapeSize(i);
		
		this.setHp(3);
		
	}
	
	public void move()
	{
		if(this.isLeftAcceleration())
		{
			Point p = new Point(this.getInitialCoordinatePoint().x - this.getxVelocity(), this.getInitialCoordinatePoint().y);
			this.setInitialCoordinatePoint(p);
		}
		
		if(this.isRightAcceleration())
		{
			Point p = new Point(this.getInitialCoordinatePoint().x + this.getxVelocity(), this.getInitialCoordinatePoint().y);
			this.setInitialCoordinatePoint(p);
		}
		
		if(this.isUpAcceleration())
		{
			Point p = new Point(this.getInitialCoordinatePoint().x, this.getInitialCoordinatePoint().y - this.getyVelocity());
			this.setInitialCoordinatePoint(p);
		}
		
		if(this.isDownAcceleration())
		{
			Point p = new Point(this.getInitialCoordinatePoint().x, this.getInitialCoordinatePoint().y + this.getyVelocity());
			this.setInitialCoordinatePoint(p);
		}
		
		if(this.getInitialCoordinatePoint().x <= 0 - this.getShapeSize()[0])
		{
			Point p = new Point((int)GameManager.instance().getWindowSizeX(), this.getInitialCoordinatePoint().y);
			this.setInitialCoordinatePoint(p);
		}
		else if(this.getInitialCoordinatePoint().x >= GameManager.instance().getWindowSizeX() + this.getShapeSize()[0])
		{
			Point p = new Point(0, this.getInitialCoordinatePoint().y);
			this.setInitialCoordinatePoint(p);
		}
		
		if(this.getInitialCoordinatePoint().y <= GameManager.instance().getWindowSizeY()/2)
		{
			Point p = new Point(this.getInitialCoordinatePoint().x, (int)GameManager.instance().getWindowSizeY()/2);
			this.setInitialCoordinatePoint(p);
		}
		else if(this.getInitialCoordinatePoint().y >= GameManager.instance().getWindowSizeY() - this.getShapeSize()[1])
		{
			Point p = new Point(this.getInitialCoordinatePoint().x, (int)GameManager.instance().getWindowSizeY() - this.getShapeSize()[1]);
			this.setInitialCoordinatePoint(p);
		}
	
		
	}
	
	public boolean CheckForCollision(IShape enemy)
	{
		if(this.getInitialCoordinatePoint().y >= enemy.getInitialCoordinatePoint().y && this.getInitialCoordinatePoint().y <= enemy.getInitialCoordinatePoint().y + enemy.getShapeSize()[1])
		{
			//if(this.projectileType == 1)
			//{
				if(this.getInitialCoordinatePoint().x >= enemy.getInitialCoordinatePoint().x && this.getInitialCoordinatePoint().x <= enemy.getInitialCoordinatePoint().x + enemy.getShapeSize()[0])
				{					
					return true;
				}
			//}
		}
		return false;
	}
	
}
