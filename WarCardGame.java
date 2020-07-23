package WCG;

import java.util.ArrayList;     
import java.util.Random;        
import java.util.List;         
import java.util.Collections; 
import java.util.LinkedList;  
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WarCardGame extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;

	private JFrame frame;
    private JTextArea space;
    private JPanel buttonPanel;
    private JButton next;
    
    private List<Card> cardDeck;
    private LinkedList<Card> deck1;
    private LinkedList<Card> deck2;
    private Card p1Card;
    private Card p2Card;
    private List<Card> war1;
    private List<Card> war2;
    	
	public WarCardGame() {
    	//GUI of Frame
    	frame = new JFrame("The Best War Card Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth())/2, 
                (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight())/2);       
        frame.setVisible(true);

        buttonPanel = new JPanel();
        next = new JButton("Next");
        next.setBounds(50,100,100,50);
        next.addMouseListener(this);
        buttonPanel.add(next);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        space = new JTextArea();
        space.setBounds(50,50,5,5);
        frame.add(space); 
        
        cardDeck = new ArrayList<Card>(); //create an ArrayList "cardDeck"
        
        for(int x=0; x<4; x++){          //0-3 for suit (4 suits)
            for(int y=2; y<15; y++){     //2-14 for rank (13 ranks)
                cardDeck.add(new Card(x,y)); //create new card and add into the deck
            }
        }
        
        Collections.shuffle(cardDeck, new Random()); //shuffle the deck randomly
        
        //creating 2 decks, each for player1/player2
        deck1 = new LinkedList<Card>();
        deck2 = new LinkedList<Card>();
        
        deck1.addAll(cardDeck.subList(0, 25));              //26 cards for p1       
        deck2.addAll(cardDeck.subList(26, cardDeck.size()));//26 cards for p2
        
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		p1Card = deck1.pop();  //each player place one card face up
        p2Card = deck2.pop();

        //display the face up card
        space.setText("Player 1 plays card is " + p1Card.toString()+ '\n');
        space.setText(space.getText()+"Player 2 plays card is " + p2Card.toString()+ '\n');
            
        //rank comparation between two cards
        if(p1Card.getCard() > p2Card.getCard()){//if player 1 win 
        	deck1.addLast(p1Card);  //higher rank wins both cards and 
            deck1.addLast(p2Card);  //places them at the bottom of his deck.
            space.setText(space.getText()+"PLayer 1 wins the round"+ '\n');

        }else if(p1Card.getCard() < p2Card.getCard()){//if player 2 win 
            deck2.addLast(p1Card);   
            deck2.addLast(p2Card);  
            space.setText(space.getText()+"PLayer 2 wins the round"+ '\n');

        }else { //war happens when both cards' rank matched
            space.setText(space.getText()+"War"+ '\n');
                
            //creating war cards
            war1 = new ArrayList<Card>(); 
            war2 = new ArrayList<Card>();
                
            //checking do players have enough (4)cards to stay in game
            for(int x=0; x<3; x++){ 
                //either one player runs out of card is game over
                if(deck1.size() == 0 || deck2.size() == 0 ){                      
                     break;
                 }
                space.setText(space.getText()+"War card for player1 is xx" + '\n'+ "War card for player2 is xx"+ '\n');
                    
                war1.add(deck1.pop());  //place additional card for war
                war2.add(deck2.pop());                  
            }
                
           	//only compare result when both players have enough cards for war
           	 if(war1.size() == 3 && war2.size() == 3 ){
             	//display the war cards from each player
               	space.setText(space.getText()+"War card for player1 is " + war1.get(0).toString()+ '\n');
               	space.setText(space.getText()+"War card for player2 is " + war2.get(0).toString()+ '\n');
    
             	//if player 1 wins the war round
               	if(war1.get(2).getCard() > war2.get(2).getCard()){
	         		deck1.addAll(war1); //player1 get all 10 cards
	               	deck1.addAll(war2);
	               	space.setText(space.getText()+"Player 1 wins the war round"+ '\n');

              	}
              	//otherwise player 2 wins the war round
               	else{
              		deck2.addAll(war1); //player2 get all 10 cards
                 	deck2.addAll(war2);
                  	space.setText(space.getText()+"Player 2 wins the war round"+ '\n');
               	}                     
           	 }
                
        }
            
        //game over either one player runs out of card(deck size is 0)
        if(deck1.size() == 0 ){
        	space.setText("game over"+ '\n'+"Player 1 wins the game"+ '\n');
        	space.setText("");
        }
        else if(deck2.size() == 0){
        	space.setText("game over"+ '\n' + "Player 2 wins the game"+ '\n');
        	space.setText("");
        }
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}     
}