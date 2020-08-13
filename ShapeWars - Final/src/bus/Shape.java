package bus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shape implements IShape
{
	//ration 1.068 (y is bigger)
	private int[] shapeSize = {100, 100};
	//board side 1024 x 768
	private Point basePoint = new Point(512,650);
	
	private List<Point> leftWing = new ArrayList<Point>();
//	public static List<IShape> FakeDataList = new ArrayList<IShape>();
	
	
	private final double gravity = 0.94;
	private boolean upAcceleration;
	private boolean downAcceleration;
	private boolean rightAcceleration;
	private boolean leftAcceleration;
	
	private int xVelocity;
	private int yVelocity;
	
	
	private int hp = 0;

	

	@Override
	public void DrawShape(Graphics g) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ShapeColor(Color c) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public Point getInitialCoordinatePoint() 
	{		
		return basePoint;
	}


	@Override
	public void setInitialCoordinatePoint(Point initialCoordinatePoint) 
	{
		this.basePoint = initialCoordinatePoint;
		
	}


	public int[] getShapeSize() 
	{
		return shapeSize;
	}


	public void setShapeSize(int[] shapeSize) 
	{
		this.shapeSize = shapeSize;
	}


	public double getGravity() 
	{
		return gravity;
	}


	public boolean isUpAcceleration() 
	{
		return upAcceleration;
	}


	public void setUpAcceleration(boolean upAcceleration) 
	{
		this.upAcceleration = upAcceleration;
	}


	public boolean isDownAcceleration() 
	{
		return downAcceleration;
	}


	public void setDownAcceleration(boolean downAcceleration) 
	{
		this.downAcceleration = downAcceleration;
	}


	public boolean isRightAcceleration() 
	{
		return rightAcceleration;
	}


	public void setRightAcceleration(boolean rightAcceleration) 
	{
		this.rightAcceleration = rightAcceleration;
	}


	public boolean isLeftAcceleration() 
	{
		return leftAcceleration;
	}


	public void setLeftAcceleration(boolean leftAcceleration) 
	{
		this.leftAcceleration = leftAcceleration;
	}


	@Override
	public void move()
	{
		// TODO Auto-generated method stub
		
	}


	public int getxVelocity() 
	{
		return xVelocity;
	}


	public void setxVelocity(int xVelocity) 
	{
		this.xVelocity = xVelocity;
	}


	public int getyVelocity() 
	{
		return yVelocity;
	}


	public void setyVelocity(int yVelocity) 
	{
		this.yVelocity = yVelocity;
	}


	public int getHp() 
	{
		return hp;
	}


	public void setHp(int hp) 
	{
		this.hp = hp;
	}


	@Override
	public void TakeDamage() 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getScoreValue() 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	


	
	
}
