package bus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JOptionPane;

import client.GameManager;

public class GUI 
{
	String mainTitle = "Shape Wars";
	String newGame = "New Game";
	String loadSaved = "Load Game";
	String leaderBoard = "Leader Board";
	
	String pausedTitle = "Game Paused";
	String resumeOpt = "Resume Game";
	String saveOpt = "Save Game";
	String exitOpt = "Exit Game";
	
	String playerHp = "Lives: ";
	String playerScore = "Score: ";
	
	public int scoreNumber = 0;
	
	public String[][] playNames = {{"AAA", "10"}, {"AAA", "100"}, {"AAA", "1000"}, {"AAA", "10000"}, {"AAA", "60000"}, {"AAA", "700000"}, {"AAA", "600000"},
	{"AAA", "800000"}, {"AAA", "900000"}, {"AAA", "1000000"}};
	
	
	
	private int mainMenuCase = 1;
	private int pausedMenuCase = 1;
	
	
	public void DrawMainMenu(Graphics g)
	{
		g.setColor(Color.green);
		
		if(this.getMainMenuCase() == 1)
		{
//			g.fillRect(410, 345, 200, 30);
			g.fillRect((int)(GameManager.instance().getWindowSizeX() * 0.4), (int)(GameManager.instance().getWindowSizeY() * 0.449), 200, 30);
		}
		else if(this.getMainMenuCase() == 2)
		{
//			g.fillRect(410, 385, 200, 30);
			g.fillRect((int)(GameManager.instance().getWindowSizeX() * 0.4), (int)(GameManager.instance().getWindowSizeY() * 0.5013), 200, 30);
		}
		else
		{
//			g.fillRect(410, 425, 200, 30);
			g.fillRect((int)(GameManager.instance().getWindowSizeX() * 0.4), (int)(GameManager.instance().getWindowSizeY() * 0.5533), 200, 30);
		}
		
		g.setColor(Color.red);
		
		int fontSize = 60;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString(mainTitle, (int)(GameManager.instance().getWindowSizeX() * 0.37), (int)(GameManager.instance().getWindowSizeY() * 0.3906)); //350, 300);
		
		fontSize = 30;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString(newGame, (int)(GameManager.instance().getWindowSizeX() * 0.4) + 30, (int)(GameManager.instance().getWindowSizeY() * 0.449) + 25); //440, 370);
		g.drawString(loadSaved, (int)(GameManager.instance().getWindowSizeX() * 0.4 + 25), (int)(GameManager.instance().getWindowSizeY() * 0.5013 + 25));//435, 410);
		g.drawString(leaderBoard, (int)(GameManager.instance().getWindowSizeX() * 0.4 + 10), (int)(GameManager.instance().getWindowSizeY() * 0.5533 + 25));//420, 450);
	}	
	
	public void DrawPausedMenu(Graphics g)
	{
		g.setColor(Color.green);
		
		if(this.getPausedMenuCase() == 1)
		{
//			g.fillRect(410, 345, 250, 30);
			g.fillRect((int)(GameManager.instance().getWindowSizeX() * 0.4), (int)(GameManager.instance().getWindowSizeY() * 0.449), 250, 30);
		}
		else if(this.getPausedMenuCase() == 2)
		{
//			g.fillRect(410, 385, 250, 30);
			g.fillRect((int)(GameManager.instance().getWindowSizeX() * 0.4), (int)(GameManager.instance().getWindowSizeY() * 0.5013), 250, 30);
		}
		else
		{
//			g.fillRect(410, 425, 250, 30);
			g.fillRect((int)(GameManager.instance().getWindowSizeX() * 0.4), (int)(GameManager.instance().getWindowSizeY() * 0.5533), 250, 30);
		}
		
		g.setColor(Color.red);
		
		int fontSize = 60;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString(pausedTitle, (int)(GameManager.instance().getWindowSizeX() * 0.37), (int)(GameManager.instance().getWindowSizeY() * 0.3906));//350, 300);
		
		fontSize = 30;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString(resumeOpt, (int)(GameManager.instance().getWindowSizeX() * 0.4) + 30, (int)(GameManager.instance().getWindowSizeY() * 0.449) + 25);//440, 370);
		g.drawString(saveOpt, (int)(GameManager.instance().getWindowSizeX() * 0.4 + 55), (int)(GameManager.instance().getWindowSizeY() * 0.5013 + 25));//465, 410);
		g.drawString(exitOpt, (int)(GameManager.instance().getWindowSizeX() * 0.4 + 55), (int)(GameManager.instance().getWindowSizeY() * 0.5533 + 25));//465, 450);
	}
	
	public void DrawLeaderBoard(Graphics g)
	{
		g.setColor(Color.red);
		
		int fontSize = 60;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString(leaderBoard, (int)(GameManager.instance().getWindowSizeX() * 0.3417), (int)(GameManager.instance().getWindowSizeY() * 0.1041)); //350, 80);
		
		fontSize = 30;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		
		int xName = (int)(GameManager.instance().getWindowSizeX() * 0.25); //200;
		int yName = (int)(GameManager.instance().getWindowSizeY() * 0.2);//150;
		int place = 1;
		
		java.util.Arrays.sort(playNames, new IdPredicate().reversed());
		
		for(String[] s : playNames)
		{
			g.drawString(place + ".  " + s[0] + " ........................................................" + s[1], xName, yName);
			place++;
			yName += (int)(GameManager.instance().getWindowSizeY() * 0.06);//50;
		}
	}
	
	public void DrawHighScoreBoard(Graphics g)
	{
		g.setColor(Color.red);
		
		int fontSize = 60;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString("High Score Board", 350, 80);
		
//		JOptionPane.showInputDialog("Input Your Initials");
	}
	
	public void SwitchMainMenuOpt(String opt)
	{
		
		if(opt == "up")
		{
			if(mainMenuCase == 1)
			{
				this.setMainMenuCase(3);
			}
			else
			{
				this.mainMenuCase--;
			}			
			
		}
		
		else if(opt == "down")
		{
			if(mainMenuCase == 3)
			{
				this.setMainMenuCase(1);
			}
			else
			{
				this.mainMenuCase++;
			}
		}
	}
	
	public void SwitchPausedMenuOpt(String opt)
	{
		
		if(opt == "up")
		{
			if(pausedMenuCase == 1)
			{
				this.setPausedMenuCase(3);
			}
			else
			{
				this.pausedMenuCase--;
			}
		}
		
		else if(opt == "down")
		{
			if(pausedMenuCase == 3)
			{
				this.setPausedMenuCase(1);
			}
			else
			{
				this.pausedMenuCase++;
			}
		}
	}
	
	public void DrawPlayerUI(Graphics g)
	{
		g.setColor(Color.red);
		
		int fontSize = 20;
		
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		
		g.drawString(playerHp, (int)(GameManager.instance().getWindowSizeX() * 0.0488), (int)(GameManager.instance().getWindowSizeY() * 0.0488)); //50, 50);
		g.drawString(playerScore, (int)(GameManager.instance().getWindowSizeX() * 0.7558), (int)(GameManager.instance().getWindowSizeY() * 0.0488));//774, 50);
		g.drawString(String.valueOf(this.scoreNumber), (int)(GameManager.instance().getWindowSizeX() * 0.8154), (int)(GameManager.instance().getWindowSizeY() * 0.0488)); //835, 50);
		
		//System.out.println("this.scoreNumber = " + this.scoreNumber);
		
		int hpSquareX = (int)(GameManager.instance().getWindowSizeX() * 0.1074);//110;
		
		for(int i = 0; i < 3; i++)
		{
			g.drawRect(hpSquareX, 30, 30, 30);
			hpSquareX += (int)(GameManager.instance().getWindowSizeX() * 0.03417);//35;
		}
		
		hpSquareX = (int)(GameManager.instance().getWindowSizeX() * 0.1074);//110;
		
		if(GameManager.instance().p.getHp() != 0)
		{
			for(int i = 0; i < GameManager.instance().p.getHp(); i++)
			{
				g.fillRect(hpSquareX, 30, 30, 30);
				hpSquareX += (int)(GameManager.instance().getWindowSizeX() * 0.03417);//35;
			}
		}
		
		
		
	}
	
	
	public int getMainMenuCase() 
	{
		return mainMenuCase;
	}


	public void setMainMenuCase(int mainMenuCase) 
	{
		this.mainMenuCase = mainMenuCase;
	}

	public int getPausedMenuCase()
	{
		return pausedMenuCase;
	}

	public void setPausedMenuCase(int pausedMenuCase)
	{
		this.pausedMenuCase = pausedMenuCase;
	}
}
