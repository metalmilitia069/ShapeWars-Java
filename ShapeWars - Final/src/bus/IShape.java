package bus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

public interface IShape 
{
	public void DrawShape(Graphics g);
	public void ShapeColor(Color c);
	
	public Point getInitialCoordinatePoint();
	public void setInitialCoordinatePoint(Point initialCoordinatePoint);
	
	public void move();
	
	public int[] getShapeSize();
	
	public int getHp();
	public void setHp(int hp);
	
	public void TakeDamage();
	
	public int scoreValue = 0;
	
	public int getScoreValue();
	
//	public double getY();
//	public float getHeight();
//	public float getWidth();
//	public double getX();
}
