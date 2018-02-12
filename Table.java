import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;
 

public class Table extends JPanel implements ActionListener
{
	private ArrayList <Card> playerHand;
	private ArrayList <Card> dealerHand;
	private ArrayList <Card> deck;
	
	private int dealerTotal;
	private int dealerPoints;
	private int playerTotal;
	private int playerPoints;
	private int bet;
	private int tempBet;
	private int balance;
	private String text;
	private String n1;
	private boolean gameOver;
	private boolean win;
	private boolean notHidden;
	private boolean open;
	private boolean allowBet;
	private boolean showStuff;

	private JButton hitButton;
	private JButton standButton;
	private JButton playAgainButton;
	private JButton customBetButton;
	private JTextField customBetTextField;

	
	private BufferedImage cardBackImage;
	
    public Table()
    {
		this.setLayout(null);
		allowBet = false;
		notHidden = false;
		open = true;
		text = "";
		n1 = "";
		gameOver = true;
		balance = 100;
		
		try
		{
			cardBackImage= ImageIO.read(new File("images/cardback.png"));
		}catch(IOException e){}
		
		playerHand = new ArrayList <Card>();
		dealerHand = new ArrayList <Card>();
		deck = new ArrayList <Card>();
		
		deck.add(new Card(2, "2", "hearts"));
		deck.add(new Card(3, "3", "hearts"));
		deck.add(new Card(4, "4", "hearts"));
		deck.add(new Card(5, "5", "hearts"));
		deck.add(new Card(6, "6", "hearts"));
		deck.add(new Card(7, "7", "hearts"));
		deck.add(new Card(8, "8", "hearts"));
		deck.add(new Card(9, "9", "hearts"));
		deck.add(new Card(10, "10", "hearts"));
		deck.add(new Card(10, "J", "hearts"));
		deck.add(new Card(10, "Q", "hearts"));
		deck.add(new Card(10, "K", "hearts"));
		deck.add(new Card(11, "A", "hearts"));
		
		deck.add(new Card(2, "2", "diamonds"));
		deck.add(new Card(3, "3", "diamonds"));
		deck.add(new Card(4, "4", "diamonds"));
		deck.add(new Card(5, "5", "diamonds"));
		deck.add(new Card(6, "6", "diamonds"));
		deck.add(new Card(7, "7", "diamonds"));
		deck.add(new Card(8, "8", "diamonds"));
		deck.add(new Card(9, "9", "diamonds"));
		deck.add(new Card(10, "10", "diamonds"));
		deck.add(new Card(10, "J", "diamonds"));
		deck.add(new Card(10, "Q", "diamonds"));
		deck.add(new Card(10, "K", "diamonds"));
		deck.add(new Card(11, "A", "diamonds"));
		
		deck.add(new Card(2, "2", "clubs"));
		deck.add(new Card(3, "3", "clubs"));
		deck.add(new Card(4, "4", "clubs"));
		deck.add(new Card(5, "5", "clubs"));
		deck.add(new Card(6, "6", "clubs"));
		deck.add(new Card(7, "7", "clubs"));
		deck.add(new Card(8, "8", "clubs"));
		deck.add(new Card(9, "9", "clubs"));
		deck.add(new Card(10, "10", "clubs"));
		deck.add(new Card(10, "J", "clubs"));
		deck.add(new Card(10, "Q", "clubs"));
		deck.add(new Card(10, "K", "clubs"));
		deck.add(new Card(11, "A", "clubs"));
		deck.add(new Card(2, "2", "spades"));
		
		deck.add(new Card(3, "3", "spades"));
		deck.add(new Card(4, "4", "spades"));
		deck.add(new Card(5, "5", "spades"));
		deck.add(new Card(6, "6", "spades"));
		deck.add(new Card(7, "7", "spades"));
		deck.add(new Card(8, "8", "spades"));
		deck.add(new Card(9, "9", "spades"));
		deck.add(new Card(10, "10", "spades"));
		deck.add(new Card(10, "J", "spades"));
		deck.add(new Card(10, "Q", "spades"));
		deck.add(new Card(10, "K", "spades"));
		deck.add(new Card(11, "A", "spades"));
		
		shuffle();
		
		customBetTextField = new JTextField(10);
        customBetTextField.setBounds(600, 300, 100, 31);
		
		hitButton = new JButton("Hit");
		hitButton.setBounds(424, 20, 75, 30);
		hitButton.addActionListener(this);
		
		standButton = new JButton("Stand");
		standButton.setBounds(501, 20, 75, 30);
		standButton.addActionListener(this);
		
	
		playAgainButton = new JButton("Play Round");
		playAgainButton.setBounds(450, 20, 100, 30);
		playAgainButton.addActionListener(this);
		this.add(playAgainButton);
		
	
		customBetButton = new JButton("Confirm Bet");
		customBetButton.setBounds(600, 350, 175, 30);
		customBetButton.addActionListener(this);
		

		
		
		
		this.setFocusable(true);  
		
    }
     
    public Dimension getPreferredSize() 
    {
        return new Dimension(1000,1000);
    }
 
    public void paintComponent(Graphics g)
    {   
	    super.paintComponent(g);
		Color red = new Color(247, 108, 108);
		Font font = new Font("Times New Roman", Font.PLAIN, 27 );
		g.setColor(red);
		g.fillRect(0, 0, 1000, 1000); 
		if(balance <= 0 && bet == 0)
		{
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("You're out of money ", 400, 400);
		}

		if(showStuff && balance > 0 || bet > 0)
		{
		
			if(!notHidden)
			{
				int x = 100;
				int y = 400;
				for(int i = 0; i < playerHand.size(); i++)
				{
					playerHand.get(i).drawMe(g, x, y);
					x += 90;
				}
				
				int x2 = 100;
				int y2 = 175;
				for(int i = 0; i < dealerHand.size(); i++)
				{
					dealerHand.get(i).drawMe(g, x2, y2);
					
					if(i > 0 && gameOver == false)
					{
						g.drawImage(cardBackImage, x2, y2, null);
					}
					x2 += 90;
				}
				if(gameOver)
				{
					dealerPoints = 0;
					for(int i = 0; i < dealerHand.size(); i++)
					{
						dealerPoints += dealerHand.get(i).getValue();
					}
					g.setFont(font);
					g.setColor(Color.black);
					g.drawString(text, 400, 370);
					
				}
				if(!open)
				{
					g.setFont(font);
					g.setColor(Color.black);
					g.drawString("Dealer's Cards: " + dealerPoints, 120, 170);
					g.drawString("Player's Cards: " + playerPoints, 120, 395);
					
				}
			
			}
			
		}
				g.setFont(font);
				g.setColor(Color.black);
				g.drawString("Current Bet: " + bet, 700, 35);
				g.drawString("Balance: " + balance, 702, 65);
				g.drawString("Dealer's Total Wins: " + dealerTotal, 120, 139);
				g.drawString("Player's  Total Wins: " + playerTotal, 120, 582);
	}
	 
		private void shuffle()
		{
			for(int i = 0; i < deck.size(); i++)
			{
				int pos = (int)(Math.random() * deck.size());
				Card temp1 = deck.get(i);
				Card temp2 = deck.get(pos);
				
				deck.set(i, temp2);
				deck.set(pos, temp1);
			}
		}
    public void actionPerformed(ActionEvent e) 
    {
		if( e.getSource() == hitButton)
		{	
	
			playerHand.add(deck.get(0));
			
			deck.remove(0);
			
			playerPoints = 0;
			for(int i = 0; i < playerHand.size(); i++)
			{
				playerPoints += playerHand.get(i).getValue();
			}
			if(playerPoints == 21)
			{
				gameOver = true;
				
				remove(hitButton);
				remove(standButton);
				add(playAgainButton);
				dealerPoints = 0;
				for(int i = 0; i < dealerHand.size(); i++)
				{
					dealerPoints += dealerHand.get(i).getValue();
				}
				
				while(dealerPoints < 17)
				{
					dealerHand.add(deck.get(0));
					deck.remove(0);
					dealerPoints = 0;
					for(int i = 0; i < dealerHand.size(); i++)
					{
						dealerPoints += dealerHand.get(i).getValue();
					}
				}
				dealerPoints = 0;
				for(int i = 0; i < dealerHand.size(); i++)
				{
					dealerPoints += dealerHand.get(i).getValue();
				}
				
				if(dealerPoints > 21)
				{
					win = true;
					playerTotal++;
					text = "You Win!";
					balance += bet;
					gameOver = true;
					bet = 0;
				}
				
				if(playerPoints > dealerPoints)
				{
					win = true;
					playerTotal++;
					text = "You Win!";
					balance += bet;
					gameOver = true;
					bet = 0;
				}
				else if(playerPoints == dealerPoints)
				{
					text = "Tie!";
					gameOver = true;
					bet = 0;
				}
			}
			
			else if(playerPoints > 21)
			{
				gameOver = true;
				remove(hitButton);
				remove(standButton);
				add(playAgainButton);
				text = "Dealer Wins";
				dealerTotal++;
				bet = 0;
				
			}
		}
		
		
		if( e.getSource() == standButton)
		{	
			
			remove(hitButton);
			remove(standButton);
			add(playAgainButton);
			gameOver = true;
			
			
			if(playerPoints <= 21)
			{
				
				
				playerPoints = 0;
				for(int i = 0; i < playerHand.size(); i++)
				{
					playerPoints += playerHand.get(i).getValue();
				}
				
				dealerPoints = 0;
				for(int i = 0; i < dealerHand.size(); i++)
				{
					dealerPoints += dealerHand.get(i).getValue();
				}
				
				while(dealerPoints < 17)
				{
					dealerHand.add(deck.get(0));
					deck.remove(0);
					dealerPoints = 0;
					for(int i = 0; i < dealerHand.size(); i++)
					{
						dealerPoints += dealerHand.get(i).getValue();
					}
				}
				
				dealerPoints = 0;
				for(int i = 0; i < dealerHand.size(); i++)
				{
					dealerPoints += dealerHand.get(i).getValue();
				}
			
			
				if(playerPoints <= 21 && dealerPoints <= 21)
				{
					if(playerPoints < dealerPoints)
					{
						win = false;
						text = "Dealer Wins";
						dealerTotal++;
						bet = 0;
					}
					if(playerPoints > dealerPoints)
					{
						win = true;
						playerTotal++;
						text = "You Win!";
						balance += (bet * 2);
						bet = 0;
						
					}
				}
				else if(dealerPoints > 21)
				{
					win = true;
					playerTotal++;
					text = "You Win!";
					balance += (bet * 2);
					bet = 0;
				}
				if(playerPoints == dealerPoints)
				{
					text = "Tie!";
					balance += bet;
					bet = 0;
				}
			}
			else
			{
				text = "Dealer Wins";
				dealerTotal++;
				bet = 0;
			}
			bet = 0;
		}
	if(balance == 0)
	{
		remove(playAgainButton);
		remove(customBetButton);
		remove(customBetTextField);
	}

		
		if( e.getSource() == playAgainButton)
		{	
			if(allowBet = false)
			{
				balance -= bet;
			}
			showStuff = false;
			if(showStuff)
			{
				remove(playAgainButton);
			}
			newGame();
		}



		
		if( e.getSource() == customBetButton)
		{	
			
			n1 = customBetTextField.getText();
			int amount = Integer.parseInt(n1);
			if(amount <= balance)
			{
				bet+= amount;
				balance -= amount;
			}
			add(hitButton);
			add(standButton);
			remove(customBetButton);
			remove(customBetTextField);
			showStuff = true; 
		}

		repaint();
    } 
	

	

	
	private void newGame()
	{
		add(customBetButton);
		add(customBetTextField);

		allowBet = true;
		open = false;
		gameOver = false;
		
		for(int i = 0; i < deck.size(); i++)
		{
			deck.remove(i);
			i--;
		}
		
		for(int i = 0; i < playerHand.size(); i++)
		{
			playerHand.remove(i);
			i--;
		}
		
		for(int i = 0; i < dealerHand.size(); i++)
		{
			dealerHand.remove(i);
			i--;
		}
		
		deck.add(new Card(2, "2", "hearts"));
		deck.add(new Card(3, "3", "hearts"));
		deck.add(new Card(4, "4", "hearts"));
		deck.add(new Card(5, "5", "hearts"));
		deck.add(new Card(6, "6", "hearts"));
		deck.add(new Card(7, "7", "hearts"));
		deck.add(new Card(8, "8", "hearts"));
		deck.add(new Card(9, "9", "hearts"));
		deck.add(new Card(10, "10", "hearts"));
		deck.add(new Card(10, "J", "hearts"));
		deck.add(new Card(10, "Q", "hearts"));
		deck.add(new Card(10, "K", "hearts"));
		deck.add(new Card(11, "A", "hearts"));
		
		deck.add(new Card(2, "2", "diamonds"));
		deck.add(new Card(3, "3", "diamonds"));
		deck.add(new Card(4, "4", "diamonds"));
		deck.add(new Card(5, "5", "diamonds"));
		deck.add(new Card(6, "6", "diamonds"));
		deck.add(new Card(7, "7", "diamonds"));
		deck.add(new Card(8, "8", "diamonds"));
		deck.add(new Card(9, "9", "diamonds"));
		deck.add(new Card(10, "10", "diamonds"));
		deck.add(new Card(10, "J", "diamonds"));
		deck.add(new Card(10, "Q", "diamonds"));
		deck.add(new Card(10, "K", "diamonds"));
		deck.add(new Card(11, "A", "diamonds"));
		
		deck.add(new Card(2, "2", "clubs"));
		deck.add(new Card(3, "3", "clubs"));
		deck.add(new Card(4, "4", "clubs"));
		deck.add(new Card(5, "5", "clubs"));
		deck.add(new Card(6, "6", "clubs"));
		deck.add(new Card(7, "7", "clubs"));
		deck.add(new Card(8, "8", "clubs"));
		deck.add(new Card(9, "9", "clubs"));
		deck.add(new Card(10, "10", "clubs"));
		deck.add(new Card(10, "J", "clubs"));
		deck.add(new Card(10, "Q", "clubs"));
		deck.add(new Card(10, "K", "clubs"));
		deck.add(new Card(11, "A", "clubs"));
		deck.add(new Card(2, "2", "spades"));
		
		deck.add(new Card(3, "3", "spades"));
		deck.add(new Card(4, "4", "spades"));
		deck.add(new Card(5, "5", "spades"));
		deck.add(new Card(6, "6", "spades"));
		deck.add(new Card(7, "7", "spades"));
		deck.add(new Card(8, "8", "spades"));
		deck.add(new Card(9, "9", "spades"));
		deck.add(new Card(10, "10", "spades"));
		deck.add(new Card(10, "J", "spades"));
		deck.add(new Card(10, "Q", "spades"));
		deck.add(new Card(10, "K", "spades"));
		deck.add(new Card(11, "A", "spades"));
		
		shuffle();
		
		
		playerHand.add(deck.get(0));
		deck.remove(0);
		
		playerHand.add(deck.get(0));
		deck.remove(0);
			
		dealerHand.add(deck.get(0));
		deck.remove(0);
		
		dealerHand.add(deck.get(0));
		deck.remove(0);
		
		remove(hitButton);
		remove(standButton);
		remove(playAgainButton);
	
		playerPoints = 0;
		for(int i = 0; i < playerHand.size(); i++)
		{
			playerPoints += playerHand.get(i).getValue();
		}
		
		dealerPoints = dealerHand.get(0).getValue();
		
		if(playerPoints == 21)
		{
			gameOver = true;
			remove(hitButton);
			remove(standButton);
			add(playAgainButton);
			while(dealerPoints < 17)
			{
				dealerHand.add(deck.get(0));
				deck.remove(0);
				dealerPoints = 0;
				for(int i = 0; i < dealerHand.size(); i++)
				{
					dealerPoints += dealerHand.get(i).getValue();
				}
			}
			if(playerPoints > dealerPoints)
			{
				win = true;
				playerTotal++;
				text = "You Win";
				balance += (bet * 2);
				gameOver = true;
				bet = 0;
			}
			else if(playerPoints == dealerPoints)
			{
				text = "Tie!";
				balance += bet;
				gameOver = true;
				bet = 0;
			}
		}
		if(playerPoints > 21)
		{
			gameOver = true;
			remove(hitButton);
			remove(standButton);
			add(playAgainButton);
			text = "Dealer Wins";
			dealerTotal++;
			bet = 0;
		}
	} 
}