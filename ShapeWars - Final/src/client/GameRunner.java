package client;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Data.ConnectionDBA;
import bus.IShape;
import bus.Player;
import bus.Projectile;
import bus.SpawnManager;

public class GameRunner extends Applet implements Runnable, KeyListener
{
	
	
	
	
	
	Thread thread;
	Graphics gfx;
	Image img;
	Player p1 = new Player();
	
	Applet a = new Applet();
	
	boolean isPaused = false;
	boolean isGameOver = true;
	boolean isMainMenuActive = true;
	boolean isLeadboardActive = false;
	boolean isGameOverPanel = false;
	boolean isHighScorePanel = false;
	
	boolean destroy = false;
	boolean isDead = false;
	boolean canShoot = false;
	
	long last_time = System.nanoTime();
	long last_death_time = System.nanoTime();
	
	int powerUpType = 2;
	
	
	
	
	
	public void init()
	{
		
		try 
		{
			ConnectionDBA.instance().Connect();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			ConnectionDBA.instance().FromDBAToFile();
			
		} catch (SQLException e2) 
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try 
		{
			GameManager.instance().WriteToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		a.resize(1024, 768);
		this.resize(1024, 768);
//		this.resize()
		
		try 
		{
			GameManager.instance().ReadFromLife();
		} 
		catch (ClassNotFoundException | IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.addKeyListener(this);
		
		GameManager.instance().setWindowSizeX(this.getSize().getWidth());
		
		img = createImage(this.getSize().width, this.getSize().height);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
		
		
	}
	
	public void paint(Graphics g)
	{
		GameManager.instance().setWindowSizeX(this.getSize().getWidth());
		GameManager.instance().setWindowSizeY(this.getSize().getHeight());
		
		
		
		img = createImage(this.getSize().width, this.getSize().height);
		gfx = img.getGraphics();
		
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		GameManager.instance().p.CalculateShape();
//		GameManager.instance().p.DrawShape(gfx);
//		GameManager.instance().p.DrawDeath(gfx);
		
		SpawnManager.instance().setSpawnXCoordLimits(GameManager.instance().getWindowSizeX());
//		SpawnManager.instance().SpawnEnemies(gfx);
		
//		if(!destroy)
//		{			
//			GameManager.instance().we.DrawShape(gfx);
//		}
		
		if(this.isGameOver && this.isHighScorePanel)
		{
			GameManager.instance().gui.DrawHighScoreBoard(gfx);
		}
		
		if(!this.isGameOver && this.isPaused)
		{
			GameManager.instance().gui.DrawPausedMenu(gfx);
		}
		
		if(this.isGameOver && this.isMainMenuActive)
		{
			GameManager.instance().gui.DrawMainMenu(gfx);
		}
		
		if(this.isGameOver && this.isLeadboardActive)
		{
			GameManager.instance().gui.DrawLeaderBoard(gfx);
		}
		
		if(!this.isPaused && !this.isGameOver)
		{
			GameManager.instance().gui.DrawPlayerUI(gfx);
			
			if(!this.isDead)
			{
				GameManager.instance().p.DrawShape(gfx);
				
				if(SpawnManager.instance().getListOfEnemies().size() > 0)//GameManager.instance().getListOfEnemies_() != null)
				{
					for(IShape enemy : SpawnManager.instance().getListOfEnemies())
					{
						//enemy.DrawShape(gfx);
						//System.out.println("putzs");
						SpawnManager.instance().SpawnEnemies(gfx);
					}
				}
			}
			else
			{
				
				GameManager.instance().p.DrawDeath(gfx);
//				long current_time = System.nanoTime();
//				long delta_time = ((current_time - last_death_time) / 10000000);	
//				
//				if(delta_time >= 1200)
//				{
//					this.isDead = false;
//					last_death_time = current_time;
//				}
			}
			
			if(GameManager.instance().getListOfProjectiles_() != null)
			{
				for(Projectile p : GameManager.instance().getListOfProjectiles_())
				{
					p.DrawShape(gfx);
				}
			}
			
			
		}
		
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g)
	{
		this.paint(g);
	}

	@Override
	public void run() 
	{
//		long last_time = System.nanoTime()/10000000;
		for(;;)
		{
//			long current_time = System.nanoTime()/10000000;
//			
//			long delta_time = ((current_time - last_time));// / 10000000);
//			
//			System.out.println(delta_time);
//			
//			if(delta_time >= 5)
//			{
//				System.out.println("mozo");
//				//delta_time = 0;
//				//current_time = last_time;
//				last_time = current_time;
//				//delta_time = 0;
//			}
			//current_time = last_time;
			//System.out.println(delta_time);
			
			//SpawnManager.instance().GenerateEnemies();
			
			if(!this.isPaused && !this.isGameOver)
			{
				if(!this.isDead)
				{
					SpawnManager.instance().GenerateEnemies();
					GameManager.instance().p.move();
					shoot();
				}
				
				
				if(GameManager.instance().getListOfProjectiles_().size() > 0)
				{
					for(Projectile pr : GameManager.instance().getListOfProjectiles_())
					{
						if(pr.getMovePoint().y < 0)
						{
							GameManager.instance().RemoveProjectileFromList(pr);
							break;
						}
						else
						{
							pr.move();	
							
							for(IShape enemy : SpawnManager.instance().getListOfEnemies())
							{
								if(pr.CheckForCollision(enemy))
								{
									if(enemy.getHp() > 1)
									{
										enemy.setHp(enemy.getHp()-1);
										enemy.TakeDamage();
										pr.setMovePoint(new Point(-100,0));
										break;
									}
									else
									{
										enemy.setInitialCoordinatePoint(new Point(-100, 0));
										GameManager.instance().gui.scoreNumber += enemy.getScoreValue();
										SpawnManager.instance().RemoveEnemyFromList(enemy);									
										pr.setMovePoint(new Point(-100,0));
										break;
									}
								}
							}
						}
					}
				}
				
				if(SpawnManager.instance().getListOfEnemies().size() > 0)
				{
					
					for(IShape enemy : SpawnManager.instance().getListOfEnemies())
					{
						enemy.move();						
						
						if(GameManager.instance().p.CheckForCollision(enemy))
						{
							if(GameManager.instance().p.getHp() > 1)
							{
							GameManager.instance().p.setHp(GameManager.instance().p.getHp() - 1);
							this.isDead = true;
							SpawnManager.instance().getListOfEnemies().clear();
							break;
							}
							else
							{
								for(String[] score : GameManager.instance().gui.playNames)
								{
									if(Integer.parseInt(score[1]) < GameManager.instance().gui.scoreNumber)
									{
										score[1] = String.valueOf(GameManager.instance().gui.scoreNumber);
										//System.out.println("hihg score");
										String name = "AAA";
										name = JOptionPane.showInputDialog("Input Your Initials (3 LETTERS!!!)");
										
										if(name == null)
										{
											name = "AAA";
											break;
										}
										
										
										if(name != "" && name.length() >= 3)
										{
											score[0] = String.valueOf(name.charAt(0)).toUpperCase() + String.valueOf(name.charAt(1)).toUpperCase() + String.valueOf(name.charAt(2)).toUpperCase();
											try 
											{
												GameManager.instance().WriteToFile();
											} 
											catch (IOException e) 
											{
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											break;
										}
//										else
//										{
//											score[0] = "AAA";
//											break;
//										}
										//this.isHighScorePanel = true;
									}
								}
								this.isGameOver = true;
								this.isDead = true;
								this.isMainMenuActive = true;
								
							}
						}
					}
				}
//				System.out.println(SpawnManager.instance().getListOfEnemies().size());
//				System.out.println(GameManager.instance().getListOfProjectiles_().size());
				
				//System.out.println("list size " + GameManager.instance().getListOfProjectiles_().size());
				//this.repaint();
			}
			else
			{
				//GameManager.instance().gui.DrawMainMenu(gfx);
				
			}
			this.repaint();
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			GameManager.instance().p.setLeftAcceleration(true);			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			GameManager.instance().p.setRightAcceleration(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(this.isGameOver && this.isMainMenuActive)
			{
				GameManager.instance().gui.SwitchMainMenuOpt("up");
			}
			
			if(!this.isGameOver && this.isPaused)
			{
				GameManager.instance().gui.SwitchPausedMenuOpt("up");
			}
				GameManager.instance().p.setUpAcceleration(true);
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(this.isGameOver && this.isMainMenuActive)
			{
				GameManager.instance().gui.SwitchMainMenuOpt("down");
			}
			
			if(!this.isGameOver && this.isPaused)
			{
				GameManager.instance().gui.SwitchPausedMenuOpt("down");
			}
			GameManager.instance().p.setDownAcceleration(true);			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			if(!this.isGameOver)
			{
				this.isPaused = !this.isPaused;
			}
			else if(this.isLeadboardActive)
			{
				this.isLeadboardActive = !this.isLeadboardActive;
				this.isMainMenuActive = !this.isMainMenuActive;
			}
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{			
			if(!this.isGameOver && !this.isPaused)
			{	
				canShoot = true;
//				shoot();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(this.isMainMenuActive)
			{
				if(GameManager.instance().gui.getMainMenuCase() == 1)
				{
					GameManager.instance().p.setHp(3);
					//GameManager.instance().p.setxVelocity(0);
					//GameManager.instance().p.setyVelocity(0);
					GameManager.instance().gui.scoreNumber = 0;
					SpawnManager.instance().getListOfEnemies().clear();
					this.canShoot = false;
					
					
					try 
					{
						GameManager.instance().ReadFromLife();
					} 
					catch (ClassNotFoundException | IOException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					this.isGameOver = !this.isGameOver;
					
					this.isMainMenuActive = !this.isMainMenuActive;
				}
				else if(GameManager.instance().gui.getMainMenuCase() == 2)
				{
					//TODO: load game
				}
				else if(GameManager.instance().gui.getMainMenuCase() == 3)
				{
					this.isLeadboardActive = !this.isLeadboardActive;
					this.isMainMenuActive = !this.isMainMenuActive;
				}
			}
			
			if(this.isPaused)
			{
				if(GameManager.instance().gui.getPausedMenuCase() == 1)
				{
					this.isPaused = !this.isPaused;
				}
				else if(GameManager.instance().gui.getPausedMenuCase() == 2)
				{
					//TODO: save game
				}
				else if(GameManager.instance().gui.getPausedMenuCase() == 3)
				{					
					System.exit(0);
				}
			}
			
			if(this.isDead)
			{
				this.isDead = false;
			}
			
			if(this.isHighScorePanel)
			{
				//JOptionPane.showInputDialog("Input Your Initials");
			}
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_N)
		{			
			//this.isGameOver = !this.isGameOver;
			//System.out.println("NNNNNN");
			if(powerUpType < 3)
			{
				powerUpType++;
			}
			else
			{
				powerUpType = 1;
			}
			
			System.out.println(powerUpType);
		}
		
		
	}

	

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			GameManager.instance().p.setLeftAcceleration(false);			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			GameManager.instance().p.setRightAcceleration(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			GameManager.instance().p.setUpAcceleration(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			GameManager.instance().p.setDownAcceleration(false);
		}
		
//		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
//		{
//			this.isPaused = false;
//		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			this.canShoot = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	//////////////////////////////////////////////
	
	private void shoot() 
	{
		long current_time = System.nanoTime();
		long delta_time = ((current_time - last_time) / 10000000);
		
//		boolean canShoot = true;
		
		//powerUpType = 2;
		
		if(delta_time >= 30 && canShoot)
		{
			if(powerUpType == 1)
			{
				Projectile p = new Projectile(GameManager.instance().p, 1);
				GameManager.instance().AddProjectileToList(p);
			}
			
			else if(powerUpType == 2 )
			{
				Projectile p = new Projectile(GameManager.instance().p, 2);
				GameManager.instance().AddProjectileToList(p);
				p = new Projectile(GameManager.instance().p, 3);
				GameManager.instance().AddProjectileToList(p);
				p = new Projectile(GameManager.instance().p, 4);
				GameManager.instance().AddProjectileToList(p);
//						last_time = current_time;
			}
			
			else if(powerUpType == 3 )
			{
				Projectile p = new Projectile(GameManager.instance().p, 5);
				GameManager.instance().AddProjectileToList(p);
				p = new Projectile(GameManager.instance().p, 6);
				GameManager.instance().AddProjectileToList(p);						
//						last_time = current_time;
			}
			
			last_time = current_time;
		}
	}
}
