import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class Card 
{
 
	private int value;
	private String name;
	private String suit;
	private BufferedImage suitImage;
	
	public Card(int value, String name, String suit)
	{
		
		this.value = value;
		this.name = name;
		this.suit = suit;
		if(suit.equals("hearts"))
		{
			try
			{
				suitImage = ImageIO.read(new File("images/hearts.png"));
			}catch(IOException e){}
		}
		if(suit.equals("diamonds"))
		{
			try
			{
				suitImage = ImageIO.read(new File("images/diamonds.png"));
			}catch(IOException e){}
		}
		if(suit.equals("clubs"))
		{
			try
			{
				suitImage = ImageIO.read(new File("images/clubs.png"));
			}catch(IOException e){}
		}
		if(suit.equals("spades"))
		{
			try
			{
				suitImage = ImageIO.read(new File("images/spades.png"));
			}catch(IOException e){}
		}
	}
	public void drawMe(Graphics g, int x, int y)
	{
	
		g.setColor(Color.white);
		g.fillRect(x, y, 114, 160);
		g.setColor(Color.black);
		g.drawRect(x, y, 114, 160);
	
		g.drawImage(suitImage, x+4, y+4, null);
		

		Font font = new Font("Calibri", Font.PLAIN, 60);
		g.setFont(font);
		if(suit.equals("hearts") || suit.equals("diamonds"))
		{
			g.setColor(Color.red);
		}
		else if(suit.equals("clubs") || suit.equals("spades"))
		{
			g.setColor(Color.black);
		}
		
		g.drawString(name, x + 40, y + 110);
		
		
	}
	public int getValue()
	{
		return value;
	}
 
}