package client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bus.GUI;
import bus.IShape;
import bus.Player;
import bus.Projectile;
import bus.WeakEnemy;

public class GameManager implements Serializable
{
	private static GameManager instance_;
	
	public GameManager()
	{
		
	}
	
	public static GameManager instance()
	{
		if(instance_ == null)
		{
			instance_ = new GameManager();
		}
		return instance_;
	}
	
	private List<Projectile> listOfProjectiles_ = new ArrayList<Projectile>();
	
	private List<IShape> listOfEnemies_ = new ArrayList<IShape>();
	
	private double windowSizeX;
	private double windowSizeY;
	
	public Player p = new Player();
	
	public WeakEnemy we = new WeakEnemy();
	
	public GUI gui = new GUI();
	
	public List<Projectile> getListOfProjectiles_() 
	{
		return listOfProjectiles_;
	}

	public void setListOfProjectiles_(List<Projectile> listOfProjectiles_) 
	{
		this.listOfProjectiles_ = listOfProjectiles_;
	}
	
	public void AddProjectileToList(Projectile p)
	{
		this.listOfProjectiles_.add(p);
	}
	
	public void RemoveProjectileFromList(Projectile p)
	{
		this.listOfProjectiles_.remove(p);
	}

	public List<IShape> getListOfEnemies_() 
	{
		return listOfEnemies_;
	}

	public void setListOfEnemies_(List<IShape> listOfEnemies_) 
	{
		this.listOfEnemies_ = listOfEnemies_;
	}

	public double getWindowSizeX() 
	{
		return windowSizeX;
	}

	public void setWindowSizeX(double windowSizeX) 
	{
		this.windowSizeX = windowSizeX;
	}

	public double getWindowSizeY() 
	{
		return windowSizeY;
	}

	public void setWindowSizeY(double windowSizeY) 
	{
		this.windowSizeY = windowSizeY;
	}
	
	public void WriteToFile() throws IOException
	{		
		FileOutputStream fos = new FileOutputStream("leaderBoards.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this.gui.playNames);
		oos.close();
	}
	
	public void ReadFromLife() throws IOException, ClassNotFoundException
	{
		
		
		
		FileInputStream fis = new FileInputStream("leaderBoards.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		


		this.gui.playNames = (String[][]) ois.readObject();
		ois.close();
		
	}
		
	
	
	
}
