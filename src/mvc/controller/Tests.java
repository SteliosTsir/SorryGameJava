/**
 * 
 */
package mvc.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import org.junit.jupiter.api.Test;

import mvc.model.COLOUR;
import mvc.model.Card;
import mvc.model.Deck;
import mvc.model.Pawn;
import mvc.model.Player;
import mvc.model.Square;
import mvc.model.squares.EndSlideSquare;
import mvc.model.squares.HomeSquare;
import mvc.model.squares.InternalSlideSquare;
import mvc.model.squares.SafetySquare;
import mvc.model.squares.SimpleSquare;
import mvc.model.squares.StartSlideSquare;
import mvc.model.squares.StartSquare;
import mvc.view.View;

import org.junit.jupiter.api.BeforeEach;
/**
 * 
 */
class Tests {

	Controller c;
	Deck deck;
	View view;
	Card currCard;
	ArrayList<Player> p;
	Square[] roundsquaresarr;
	Square[] greensq;
	Square[] bluesq;
	Square[] yellowsq;
	Square[] redsq;
	StartSquare startRed;
	StartSquare startBlue;
	StartSquare startGreen;
	StartSquare startYellow;
	HomeSquare homeRed;
	HomeSquare homeBlue;
	HomeSquare homeGreen;
	HomeSquare homeYellow;
	StartSlideSquare ssqRed1;
	StartSlideSquare ssqBlue1;
	StartSlideSquare ssqGreen1;
	StartSlideSquare ssqYellow1;	
	StartSlideSquare ssqRed2;
	StartSlideSquare ssqBlue2;
	StartSlideSquare ssqGreen2;
	StartSlideSquare ssqYellow2;	
	EndSlideSquare esqRed1;
	EndSlideSquare esqBlue1;
	EndSlideSquare esqGreen1;
	EndSlideSquare esqYellow1;	
	EndSlideSquare esqRed2;
	EndSlideSquare esqBlue2;
	EndSlideSquare esqGreen2;
	EndSlideSquare esqYellow2;
	JLayeredPane Window;
	JMenuBar Menu;
    JMenu NewGame; 
    JMenu Save; 
    JMenu Cont;
    JMenu Exit; 
	JLabel Square;
	JLabel PhotoSqaure;
	JLabel StartRedSq;
	JLabel HomeRedSq;
	JLabel StartYellowSq;
	JLabel HomeYellowSq;
	JLabel StartGreenSq;
	JLabel HomeGreenSq;
	JLabel StartBlueSq;
	JLabel HomeBlueSq;
	JLabel Text1;
	JLabel Text2;
	JLabel Logo;
	JButton Fold;
	JButton pawnRed1;
	JButton pawnRed2;	
	JButton pawnGreen1;
	JButton pawnGreen2;	
	JButton pawnYellow1;
	JButton pawnYellow2;	
	JButton pawnBlue1;
	JButton pawnBlue2;
	JButton backCard;
	JLabel Card;	
	JLabel[] roundsquares;
	JLabel[] greensquares;
	JLabel[] bluesquares;
	JLabel[] yellowsquares;
	JLabel[] redsquares;
	JTextArea infoBox;
	JFrame frame;
	
	int turn;
	int max;
	
	@BeforeEach
	void setUp() {
		c = new Controller();
		deck = new Deck();
		view = new View();
		int count = 0;
		
		ImageIcon backImg = new ImageIcon("src\\resourses\\images\\background.png");
        JLabel back = new JLabel(backImg);
        back.setOpaque(true);
        back.setBounds(0, 0, backImg.getIconWidth(), backImg.getIconHeight());
    
    	StartRedSq = new JLabel("Start", JLabel.CENTER);
    	HomeRedSq = new JLabel("Home", JLabel.CENTER);
    	StartYellowSq = new JLabel("Start", JLabel.CENTER);
    	HomeYellowSq = new JLabel("Home", JLabel.CENTER);
    	StartGreenSq = new JLabel("Start", JLabel.CENTER);;
    	HomeGreenSq = new JLabel("Home", JLabel.CENTER);
    	StartBlueSq = new JLabel("Start", JLabel.CENTER);;
    	HomeBlueSq = new JLabel("Home", JLabel.CENTER);
    	yellowsquares = new JLabel[5];
    	redsquares = new JLabel[5];
    	bluesquares = new JLabel[5];
    	greensquares = new JLabel[5];
        
        roundsquares = new JLabel[60];
        
        for(int i = 0; i < 60; i++) {
        	roundsquares[i] = new JLabel();
        	roundsquares[i].setOpaque(true);
        	roundsquares[i].setBackground(Color.WHITE);
        	roundsquares[i].setBorder(new LineBorder(Color.BLACK, 1));
        	if( i < 15) {
        		if(i == 2) {
        			for(int k = 0; k < 5; k++) {
        				redsquares[k] = new JLabel();
        				redsquares[k].setOpaque(true);
        				redsquares[k].setBackground(Color.RED);
        				redsquares[k].setBorder(new LineBorder(Color.BLACK, 1));
        				redsquares[k].setBounds(i * 40, (k + 1) * 40, 40, 40);
        				redsquares[k].setText(Integer.toString(k));
        			}
        			HomeRedSq.setOpaque(true);
        			HomeRedSq.setBackground(Color.WHITE);
        			HomeRedSq.setBorder(new LineBorder(Color.RED, 5));
        			HomeRedSq.setBounds((i * 40) - 20, 6 * 40, 80, 80);
        			HomeRedSq.setVerticalAlignment(JLabel.BOTTOM);     
        			HomeRedSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        			
        			StartRedSq.setOpaque(true);
        			StartRedSq.setBackground(Color.WHITE);
        			StartRedSq.setBorder(new LineBorder(Color.RED, 5));
        			StartRedSq.setBounds((i * 40) + 60, 40, 80, 80);
        			StartRedSq.setVerticalAlignment(JLabel.BOTTOM);
        			StartRedSq.setFont(new Font("Monospaced", Font.BOLD, 23));

        		}
        		roundsquares[i].setBounds(i * 40, 0, 40, 40);
        	} else if (i < 30) {
        		if((i - 15) == 2) {
        			for(int k = 0; k < 5; k++) {
        				bluesquares[k] = new JLabel();
        				bluesquares[k].setOpaque(true);
        				bluesquares[k].setBackground(Color.BLUE);
        				bluesquares[k].setBorder(new LineBorder(Color.BLACK, 1));
        				bluesquares[k].setBounds((k + i - 7) * 40, (i - 15) * 40, 40, 40);
        				bluesquares[k].setText(Integer.toString(k));

        			}
        			HomeBlueSq.setOpaque(true);
        			HomeBlueSq.setBackground(Color.WHITE);
        			HomeBlueSq.setBorder(new LineBorder(Color.BLUE, 5));
        			HomeBlueSq.setBounds(8 * 40, 2 * 40 - 20, 80, 80);
        			HomeBlueSq.setVerticalAlignment(JLabel.BOTTOM);     
        			HomeBlueSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        			
        			StartBlueSq.setOpaque(true);
        			StartBlueSq.setBackground(Color.WHITE);
        			StartBlueSq.setBorder(new LineBorder(Color.BLUE, 5));
        			StartBlueSq.setBounds(13 * 40, 4 * 40 - 20, 80, 80);
        			StartBlueSq.setVerticalAlignment(JLabel.BOTTOM);
        			StartBlueSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        		}
        		roundsquares[i].setBounds(15 * 40, (i - 15) * 40, 40, 40);
        	} else if (i < 45) {
        		if((i - 31) == 2) {
        			for(int k = 0; k < 5; k++) {
        				yellowsquares[k] = new JLabel();
        				yellowsquares[k].setOpaque(true);
        				yellowsquares[k].setBackground(Color.YELLOW);
        				yellowsquares[k].setBorder(new LineBorder(Color.BLACK, 1));
        				yellowsquares[k].setBounds( 13 * 40, (k + i - 23) * 40, 40, 40);
        				yellowsquares[k].setText(Integer.toString(k));

        			}
        			HomeYellowSq.setOpaque(true);
        			HomeYellowSq.setBackground(Color.WHITE);
        			HomeYellowSq.setBorder(new LineBorder(Color.YELLOW, 5));
        			HomeYellowSq.setBounds(13 * 40 - 20, 8 * 40, 80, 80);
        			HomeYellowSq.setVerticalAlignment(JLabel.BOTTOM);     
        			HomeYellowSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        			
        			StartYellowSq.setOpaque(true);
        			StartYellowSq.setBackground(Color.WHITE);
        			StartYellowSq.setBorder(new LineBorder(Color.YELLOW, 5));
        			StartYellowSq.setBounds(11 * 40 - 20, 13 * 40, 80, 80);
        			StartYellowSq.setVerticalAlignment(JLabel.BOTTOM);
        			StartYellowSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        		}
        		roundsquares[i].setBounds((45 - i) * 40, 15 * 40, 40, 40);
        	} else {
        		if((15 - (i - 45)) == 2) {
        			for(int k = 0; k < 5; k++) {
        				greensquares[k] = new JLabel();
        				greensquares[k].setOpaque(true);
        				greensquares[k].setBackground(Color.decode("#009500"));
        				greensquares[k].setBorder(new LineBorder(Color.BLACK, 1));
        				greensquares[k].setBounds( (k + 1) * 40, 13 * 40, 40, 40);
        				greensquares[k].setText(Integer.toString(k));

        			}
        			HomeGreenSq.setOpaque(true);
        			HomeGreenSq.setBackground(Color.WHITE);
        			HomeGreenSq.setBorder(new LineBorder(Color.decode("#009500"), 5));
        			HomeGreenSq.setBounds(6 * 40, 12 * 40 + 20, 80, 80);
        			HomeGreenSq.setVerticalAlignment(JLabel.BOTTOM);     
        			HomeGreenSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        			
        			StartGreenSq.setOpaque(true);
        			StartGreenSq.setBackground(Color.WHITE);
        			StartGreenSq.setBorder(new LineBorder(Color.decode("#009500"), 5));
        			StartGreenSq.setBounds(40, 10 * 40 + 20, 80, 80);
        			StartGreenSq.setVerticalAlignment(JLabel.BOTTOM);
        			StartGreenSq.setFont(new Font("Monospaced", Font.BOLD, 23));
        		}
        		roundsquares[i].setBounds(0, (60 - i) * 40, 40, 40);
        	}
        }
        
        JLabel backLogo = new JLabel();
        backLogo.setOpaque(true);
        backLogo.setBackground(Color.CYAN);
        backLogo.setBounds(0, 0, 40*15, 40 * 15);
        
		ImageIcon backLogoImg = new ImageIcon("src\\resourses\\images\\sorryImage.png");
		JLabel backLogoIco = new JLabel(backLogoImg);
		backLogoIco.setOpaque(true);
        backLogoIco.setBounds(15 * 15, 18 * 15, backLogoImg.getIconWidth(), backLogoImg.getIconHeight());
        
		Window = new JLayeredPane();
		Window.setBounds(0, 0, backImg.getIconWidth(), backImg.getIconHeight());
        Window.add(back, JLayeredPane.DEFAULT_LAYER);
        Window.add(backLogo, JLayeredPane.PALETTE_LAYER);
        for(int i = 0; i < 60; i++) {
        	Window.add(roundsquares[i], JLayeredPane.MODAL_LAYER);
        }        
        
        for(int i = 0; i < 5; i++) {
	    	Window.add(redsquares[i], JLayeredPane.MODAL_LAYER);
	    	Window.add(yellowsquares[4 - i], JLayeredPane.MODAL_LAYER);
	   		Window.add(bluesquares[4 - i], JLayeredPane.MODAL_LAYER);
			Window.add(greensquares[i], JLayeredPane.MODAL_LAYER);
        }

        
        for(int i = 0; i < 60; i++) {
        	if(i < 15) {
        		if(i == 1 || i == 9) {
        			ImageIcon redSlideStart = new ImageIcon("src\\resourses\\images\\slides\\redSlideStart.png");
        			roundsquares[i].setIcon(redSlideStart);
        		} else if(i == 4 || i == 13) {
        			ImageIcon redSlideEnd = new ImageIcon("src\\resourses\\images\\slides\\redSlideEnd.png");
        			roundsquares[i].setIcon(redSlideEnd);
        		} else if((i > 1 && i < 4) || (i > 9 && i < 13)) {
        			ImageIcon redSlideMedium = new ImageIcon("src\\resourses\\images\\slides\\redSlideMedium.png");
        			roundsquares[i].setIcon(redSlideMedium);
        		}	
        	} else if(i < 30) {
        		if((i - 15) == 1 || (i - 15) == 9) {
        			ImageIcon blueSlideStart = new ImageIcon("src\\resourses\\images\\slides\\blueSlideStart.png");
        			roundsquares[i].setIcon(blueSlideStart);
        		} else if((i - 15) == 4 || (i - 15) == 13) {
        			ImageIcon blueSlideEnd = new ImageIcon("src\\resourses\\images\\slides\\blueSlideEnd.png");
        			roundsquares[i].setIcon(blueSlideEnd);
        		} else if(((i - 15) > 1 && (i - 15) < 4) || ((i - 15) > 9 && (i - 15) < 13)) {
        			ImageIcon blueSlideMedium = new ImageIcon("src\\resourses\\images\\slides\\blueSlideMedium.png");
        			roundsquares[i].setIcon(blueSlideMedium);
        		}	
        	} else if(i < 45) {
        		if((i - 30) == 1 || (i - 30) == 9) {
        			ImageIcon yellowSlideStart = new ImageIcon("src\\resourses\\images\\slides\\yellowSlideStart.png");
        			roundsquares[i].setIcon(yellowSlideStart);
        		} else if((i - 30) == 4 || (i - 30) == 13) {
        			ImageIcon yellowSlideEnd = new ImageIcon("src\\resourses\\images\\slides\\yellowSlideEnd.png");
        			roundsquares[i].setIcon(yellowSlideEnd);
        		} else if(((i - 30) > 1 && (i - 30) < 4) || ((i - 30) > 9 && (i - 30) < 13)) {
        			ImageIcon yellowSlideMedium = new ImageIcon("src\\resourses\\images\\slides\\yellowSlideMedium.png");
        			roundsquares[i].setIcon(yellowSlideMedium);
        		}
        	} else {
        		if((i - 45) == 1 || (i - 45) == 9) {
        			ImageIcon greenSlideStart = new ImageIcon("src\\resourses\\images\\slides\\greenSlideStart.png");
        			roundsquares[i].setIcon(greenSlideStart);
        		} else if((i - 45) == 4 || (i - 45) == 13) {
        			ImageIcon greenSlideEnd = new ImageIcon("src\\resourses\\images\\slides\\greenSlideEnd.png");
        			roundsquares[i].setIcon(greenSlideEnd);
        		} else if(((i - 45) > 1 && (i - 45) < 4) || ((i - 45) > 9 && (i - 45) < 13)) {
        			ImageIcon greenSlideMedium = new ImageIcon("src\\resourses\\images\\slides\\greenSlideMedium.png");
        			roundsquares[i].setIcon(greenSlideMedium);
        		}
        	}
        }
        
        Menu = new JMenuBar();
        NewGame =  new JMenu("New Game");
        Save =  new JMenu("Save Game");
        Cont =  new JMenu("Continue Saved Game");
        Exit =  new JMenu("Exit Game");
        Menu.add(NewGame);
        Menu.add(Save);
        Menu.add(Cont);
        Menu.add(Exit);
                
		ImageIcon backofCard = new ImageIcon("src\\resourses\\images\\cards\\backCard.png");
		backCard = new JButton(backofCard);
		backCard.setBounds(17 * 40 - 20, 4 * 40, backofCard.getIconWidth(), backofCard.getIconHeight());

		ImageIcon Img = new ImageIcon("src\\resourses\\images\\cards\\card1.png");
		Card = new JLabel();
		Card.setOpaque(true);
        Card.setBounds(20 * 40 - 20, 4 * 40, Img.getIconWidth(), Img.getIconHeight());
		Card.setVisible(false);
        
		Text1 = new JLabel();
		Text1.setText("Receive Card");
		Text1.setBounds(17 * 40 - 10, 8 * 40, 300, 10);
        
		Text2 = new JLabel();
		Text2.setText("Current Card");
		Text2.setBounds(19 * 40 + 30, 8 * 40, 300, 10);        
        
		Fold = new JButton();
		Fold.setOpaque(true);
		Fold.setText("Fold Button");
		Fold.setBackground(Color.RED);
		Fold.setBounds(17 * 40 - 20, 9 * 40, 220, 50);
	
		infoBox = new JTextArea();
		infoBox.setOpaque(true);
		infoBox.setBackground(Color.WHITE);
		infoBox.setBounds(17 * 40 - 20, 10 * 40 + 20, 220, 160);
		infoBox.setBorder(new LineBorder(Color.BLACK, 3));
		infoBox.setText("Press New Game to\nstart a new game\n\nPress\nContinue Saved Game\nto load a game\nfrom a file\n");
		infoBox.setFont(new Font("Monospaced", Font.BOLD, 15));
		
        Window.add(infoBox, JLayeredPane.MODAL_LAYER);
        Window.add(Fold, JLayeredPane.MODAL_LAYER);
        Window.add(Text1, JLayeredPane.MODAL_LAYER);
        Window.add(Text2, JLayeredPane.MODAL_LAYER);
        Window.add(backCard, JLayeredPane.MODAL_LAYER);
        Window.add(backLogoIco, JLayeredPane.MODAL_LAYER);
        Window.add(Card, JLayeredPane.MODAL_LAYER);
        Window.add(HomeRedSq, JLayeredPane.MODAL_LAYER);
        Window.add(StartRedSq, JLayeredPane.MODAL_LAYER);
        Window.add(HomeYellowSq, JLayeredPane.MODAL_LAYER);
        Window.add(StartYellowSq, JLayeredPane.MODAL_LAYER);
        Window.add(HomeGreenSq, JLayeredPane.MODAL_LAYER);
        Window.add(StartGreenSq, JLayeredPane.MODAL_LAYER);
        Window.add(HomeBlueSq, JLayeredPane.MODAL_LAYER);
        Window.add(StartBlueSq, JLayeredPane.MODAL_LAYER);
        
        
        frame = new JFrame("Sorry Game");
        frame.add(Window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(backImg.getIconWidth() - 80, backImg.getIconHeight() - 40);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLayout(null);;
        frame.setVisible(true);
        frame.setJMenuBar(Menu);
        
		
        this.Exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
		
	    count = 2;	
		
	    String names[] = new String[count];
	    
	    names[0] = "Player 1";
	    names[1] = "Player 2";
	    
		p = new ArrayList<Player>();

		deck.Shuffle();
	    
		redsq = new SafetySquare[5];
		yellowsq = new SafetySquare[5];
		for(int i = 0; i < 5; i++) {
			redsq[i] = new SafetySquare(i + 1, COLOUR.Red, null);
			yellowsq[i] = new SafetySquare(i + 1, COLOUR.Yellow, null);			
		}
		
		
		roundsquaresarr = new Square[60];
		for(int i = 0; i < 60; i++) {
			if(!((i >= 1 && i <= 4) || (i >= 9 && i <= 13) || (i >= 16 && i <= 19) || (i >= 24 && i <= 28) || (i >= 31 && i <= 34) || (i >= 39 && i <= 43) || (i >= 46 && i <= 49) || (i >= 54 && i <= 58))) {
				roundsquaresarr[i] = new SimpleSquare(i + 1, null);
			} else {
				if((i >= 1 && i <= 4) || (i >= 9 && i <= 13)) {
					if(i == 1 || i == 9) {
						roundsquaresarr[i] = new StartSlideSquare(i + 1, COLOUR.Red, null);
					} else if(i == 4 || i == 13) {
						roundsquaresarr[i] = new EndSlideSquare(i + 1, COLOUR.Red, null);
					} else {
						roundsquaresarr[i] = new InternalSlideSquare(i + 1, COLOUR.Red, null);
					}
				} else if((i >= 16 && i <= 19) || (i >= 24 && i <= 28)) {
					if(i == 16 || i == 24) {
						roundsquaresarr[i] = new StartSlideSquare(i + 1, COLOUR.Blue, null);
					} else if(i == 19 || i == 28) {
						roundsquaresarr[i] = new EndSlideSquare(i + 1, COLOUR.Blue, null);
					} else {
						roundsquaresarr[i] = new InternalSlideSquare(i + 1, COLOUR.Blue, null);
					}
				} else if((i >= 31 && i <= 34) || (i >= 39 && i <= 43)) {
					if(i == 31 || i == 39) {
						roundsquaresarr[i] = new StartSlideSquare(i + 1, COLOUR.Yellow, null);
					} else if(i == 34 || i == 43) {
						roundsquaresarr[i] = new EndSlideSquare(i + 1, COLOUR.Yellow, null);
					} else {
						roundsquaresarr[i] = new InternalSlideSquare(i + 1, COLOUR.Yellow, null);
					}
				} else {
					if(i == 46 || i == 54) {
						roundsquaresarr[i] = new StartSlideSquare(i + 1, COLOUR.Green, null);
					} else if(i == 49 || i == 58) {
						roundsquaresarr[i] = new EndSlideSquare(i + 1, COLOUR.Green, null);
					} else {
						roundsquaresarr[i] = new InternalSlideSquare(i + 1, COLOUR.Green, null);
					}
				}
			}
		}
		
		
		ssqRed1 = (StartSlideSquare) roundsquaresarr[1];
		ssqRed2 = (StartSlideSquare) roundsquaresarr[9];
		esqRed1 = (EndSlideSquare) roundsquaresarr[4];
		esqRed2 = (EndSlideSquare) roundsquaresarr[13];
		
		ssqYellow1 = (StartSlideSquare) roundsquaresarr[31];
		ssqYellow2 = (StartSlideSquare) roundsquaresarr[39];
		esqYellow1 = (EndSlideSquare) roundsquaresarr[34];
		esqYellow2 = (EndSlideSquare) roundsquaresarr[43];
		
		ssqGreen1 = (StartSlideSquare) roundsquaresarr[46];
		ssqGreen2 = (StartSlideSquare) roundsquaresarr[54];
		esqGreen1 = (EndSlideSquare) roundsquaresarr[49];
		esqGreen2 = (EndSlideSquare) roundsquaresarr[58];
		
		ssqBlue1 = (StartSlideSquare) roundsquaresarr[16];
		ssqBlue2 = (StartSlideSquare) roundsquaresarr[24];
		esqBlue1 = (EndSlideSquare) roundsquaresarr[19];
		esqBlue2 = (EndSlideSquare) roundsquaresarr[28];
		
		startRed = new StartSquare(-1, esqRed1 ,COLOUR.Red);		
		startYellow = new StartSquare(-1, esqYellow1 ,COLOUR.Yellow);
		
		homeRed = new HomeSquare(-2 ,COLOUR.Red);
		homeYellow = new HomeSquare(-2 ,COLOUR.Yellow);
		
		p.add(new Player(names[0], COLOUR.Red));
		p.get(0).setPawns(new Pawn(1, p.get(0), COLOUR.Red, startRed), new Pawn(2, p.get(0), COLOUR.Red, startRed));
		p.add(new Player(names[1], COLOUR.Yellow));
		p.get(1).setPawns(new Pawn(1, p.get(1), COLOUR.Yellow, startYellow), new Pawn(2, p.get(1), COLOUR.Yellow, startYellow));
	
		int plcount = p.size();
		
		for(int i = 0; i < 60; i++) {
			roundsquaresarr[i].setLabel(roundsquares[i]);
		}
		
		for(int i = 0; i < 5; i++) {
			redsq[i].setLabel(redsquares[i]);
			yellowsq[4 - i].setLabel(yellowsquares[i]);				
		}
		
		esqRed1.setLabel(roundsquares[4]);
		ssqRed1.setLabel(roundsquares[1]);
		esqYellow1.setLabel(roundsquares[34]);
		ssqYellow1.setLabel(roundsquares[31]);
		esqGreen1.setLabel(roundsquares[49]);
		ssqGreen1.setLabel(roundsquares[46]);
		esqBlue1.setLabel(roundsquares[19]);
		ssqBlue1.setLabel(roundsquares[16]);

		esqRed2.setLabel(roundsquares[13]);
		ssqRed2.setLabel(roundsquares[9]);
		esqYellow2.setLabel(roundsquares[43]);
		ssqYellow2.setLabel(roundsquares[39]);
		esqGreen2.setLabel(roundsquares[58]);
		ssqGreen2.setLabel(roundsquares[54]);
		esqBlue2.setLabel(roundsquares[28]);
		ssqBlue2.setLabel(roundsquares[24]);
		
		startRed.setLabel(StartRedSq);
		startYellow.setLabel(StartYellowSq);
		homeRed.setLabel(HomeRedSq);
		homeYellow.setLabel(HomeYellowSq);
		
		if(plcount == 3) {
			startBlue.setLabel(StartBlueSq);
			homeBlue.setLabel(HomeBlueSq);
			for(int i = 0; i < 5; i++) {
				bluesq[i].setLabel(bluesquares[i]);
			}
		} else if(plcount == 4) {				
			startGreen.setLabel(StartGreenSq);
			homeGreen.setLabel(HomeGreenSq);
			startBlue.setLabel(StartBlueSq);
			homeBlue.setLabel(HomeBlueSq);
			for(int i = 0; i < 5; i++) {
				greensq[i].setLabel(greensquares[i]);
				bluesq[4 - i].setLabel(bluesquares[i]);				
			}
		}

		ImageIcon pr1 = new ImageIcon("src\\resourses\\images\\pawns\\redPawn1.png");
		pawnRed1 = new JButton(pr1);
		pawnRed1.setBackground(Color.WHITE);
		pawnRed1.setBounds(4 * 40 - 20, 40 + 10, 40, 40);
		Window.add(pawnRed1, JLayeredPane.POPUP_LAYER);
		
		ImageIcon pr2 = new ImageIcon("src\\resourses\\images\\pawns\\redPawn2.png");
		pawnRed2 = new JButton(pr2);
		pawnRed2.setBackground(Color.WHITE);
		pawnRed2.setBounds(5 * 40 - 20, 40 + 10, 40, 40);
		Window.add(pawnRed2, JLayeredPane.POPUP_LAYER);
		
		ImageIcon pb1 = new ImageIcon("src\\resourses\\images\\pawns\\bluePawn1.png");
		pawnBlue1 = new JButton(pb1);
		pawnBlue1.setBackground(Color.WHITE);
		pawnBlue1.setBounds(13 * 40, 3 * 40 + 30, 40, 40);
		Window.add(pawnBlue1, JLayeredPane.POPUP_LAYER);
		
		ImageIcon pb2 = new ImageIcon("src\\resourses\\images\\pawns\\bluePawn2.png");
		pawnBlue2 = new JButton(pb2);
		pawnBlue2.setBackground(Color.WHITE);
		pawnBlue2.setBounds(14 * 40, 3 * 40 + 30, 40, 40);
		Window.add(pawnBlue2, JLayeredPane.POPUP_LAYER);

		ImageIcon pg1 = new ImageIcon("src\\resourses\\images\\pawns\\greenPawn1.png");
		pawnGreen1 = new JButton(pg1);
		pawnGreen1.setBackground(Color.WHITE);
		pawnGreen1.setBounds(40, 10 * 40 + 30, 40, 40);
		Window.add(pawnGreen1, JLayeredPane.POPUP_LAYER);
		
		ImageIcon pg2 = new ImageIcon("src\\resourses\\images\\pawns\\greenPawn2.png");
		pawnGreen2 = new JButton(pg2);
		pawnGreen2.setBackground(Color.WHITE);
		pawnGreen2.setBounds(2 * 40, 10 * 40 + 30, 40, 40);
		Window.add(pawnGreen2, JLayeredPane.POPUP_LAYER);
		
		ImageIcon py1 = new ImageIcon("src\\resourses\\images\\pawns\\yellowPawn1.png");
		pawnYellow1 = new JButton(py1);
		pawnYellow1.setBackground(Color.WHITE);
		pawnYellow1.setBounds(11 * 40 - 20, 13 * 40 + 10, 40, 40);
		Window.add(pawnYellow1, JLayeredPane.POPUP_LAYER);
		
		ImageIcon py2 = new ImageIcon("src\\resourses\\images\\pawns\\yellowPawn2.png");
		pawnYellow2 = new JButton(py2);
		pawnYellow2.setBackground(Color.WHITE);
		pawnYellow2.setBounds(12 * 40 - 20, 13 * 40 + 10, 40, 40);
		Window.add(pawnYellow2, JLayeredPane.POPUP_LAYER);
		
		
		if(plcount == 2) {
			HomeBlueSq.setVisible(false);
			StartBlueSq.setVisible(false);
			for(int i = 0; i < 5; i++) {
				bluesquares[i].setVisible(false);;
			}
			pawnBlue1.setVisible(false);
			pawnBlue2.setVisible(false);
			HomeGreenSq.setVisible(false);
			StartGreenSq.setVisible(false);
			for(int i = 0; i < 5; i++) {
				greensquares[i].setVisible(false);;
			}
			pawnGreen1.setVisible(false);
			pawnGreen2.setVisible(false);
		} else if(plcount == 3) {
			HomeGreenSq.setVisible(false);
			StartGreenSq.setVisible(false);
			for(int i = 0; i < 5; i++) {
				greensquares[i].setVisible(false);;
			}
			pawnGreen1.setVisible(false);
			pawnGreen2.setVisible(false);
		}			
		
		max = p.size();
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(max);
		turn = randomInt;
		
		return;
	}
	
	
	
	
	@Test
	void test() {
		// we have two players a red and a Yellow
		assertEquals(COLOUR.Red, p.get(0).getColour());
		assertEquals(COLOUR.Yellow, p.get(1).getColour());
		
		//with two pawns each
		Pawn[] pawns1 = p.get(0).getPawns();
		Pawn[] pawns2 = p.get(1).getPawns();

		assertNotNull(pawns1[0]);
		assertNotNull(pawns1[1]);

		assertNotNull(pawns2[0]);
		assertNotNull(pawns2[1]);

	}
	
	@Test
	void testNumberTwo() {
		//all pawns should be at their Start Positions
		Pawn[] pawns1 = p.get(0).getPawns();
		Pawn[] pawns2 = p.get(1).getPawns();
		
		assertEquals(-1, pawns1[0].getSq().getNum());
		assertEquals(-1, pawns1[1].getSq().getNum());

		assertEquals(-1, pawns2[0].getSq().getNum());
		assertEquals(-1, pawns2[1].getSq().getNum());

	}
}