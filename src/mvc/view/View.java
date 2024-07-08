package mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import mvc.model.*;
import mvc.model.squares.EndSlideSquare;
import mvc.model.squares.StartSlideSquare;

public class View {

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
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * 
	 * Postconditions
	 * - Initializes the game window
	 * 
	 * @return The number of players entered by the user
	 */
	
	public int initView() {		
		//cldr = this.getClass().getClassLoader();
		Image backr = (new ImageIcon(getClass().getClassLoader().getResource("resourses/images/background.png")).getImage());
        //URL imageURL2 = cldr.getResource("resourses/images/background.png"); //image
       // Image image2 = new ImageIcon(backImg).getImage();
       //image2 = image2.getScaledInstance(60, 70, java.awt.Image.SCALE_SMOOTH);
      		
		ImageIcon backImg = new ImageIcon(backr);
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
        
		ImageIcon backLogoImg = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/sorryImage.png")).getImage());
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
        			ImageIcon redSlideStart = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/redSlideStart.png")).getImage());
        			roundsquares[i].setIcon(redSlideStart);
        		} else if(i == 4 || i == 13) {
        			ImageIcon redSlideEnd =new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/redSlideEnd.png")).getImage());
        			roundsquares[i].setIcon(redSlideEnd);
        		} else if((i > 1 && i < 4) || (i > 9 && i < 13)) {
        			ImageIcon redSlideMedium = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/redSlideMedium.png")).getImage());
        			roundsquares[i].setIcon(redSlideMedium);
        		}	
        	} else if(i < 30) {
        		if((i - 15) == 1 || (i - 15) == 9) {
        			ImageIcon blueSlideStart = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/blueSlideStart.png")).getImage());
        			roundsquares[i].setIcon(blueSlideStart);
        		} else if((i - 15) == 4 || (i - 15) == 13) {
        			ImageIcon blueSlideEnd = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/blueSlideEnd.png")).getImage());
        			roundsquares[i].setIcon(blueSlideEnd);
        		} else if(((i - 15) > 1 && (i - 15) < 4) || ((i - 15) > 9 && (i - 15) < 13)) {
        			ImageIcon blueSlideMedium = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/blueSlideMedium.png")).getImage());
        			roundsquares[i].setIcon(blueSlideMedium);
        		}	
        	} else if(i < 45) {
        		if((i - 30) == 1 || (i - 30) == 9) {
        			ImageIcon yellowSlideStart = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/yellowSlideStart.png")).getImage());
        			roundsquares[i].setIcon(yellowSlideStart);
        		} else if((i - 30) == 4 || (i - 30) == 13) {
        			ImageIcon yellowSlideEnd = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/yellowSlideEnd.png")).getImage());
        			roundsquares[i].setIcon(yellowSlideEnd);
        		} else if(((i - 30) > 1 && (i - 30) < 4) || ((i - 30) > 9 && (i - 30) < 13)) {
        			ImageIcon yellowSlideMedium = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/yellowSlideMedium.png")).getImage());
        			roundsquares[i].setIcon(yellowSlideMedium);
        		}
        	} else {
        		if((i - 45) == 1 || (i - 45) == 9) {
        			ImageIcon greenSlideStart = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/greenSlideStart.png")).getImage());
        			roundsquares[i].setIcon(greenSlideStart);
        		} else if((i - 45) == 4 || (i - 45) == 13) {
        			ImageIcon greenSlideEnd = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/greenSlideEnd.png")).getImage());
        			roundsquares[i].setIcon(greenSlideEnd);
        		} else if(((i - 45) > 1 && (i - 45) < 4) || ((i - 45) > 9 && (i - 45) < 13)) {
        			ImageIcon greenSlideMedium = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/slides/greenSlideMedium.png")).getImage());
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
                
		ImageIcon backofCard = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/cards/backCard.png")).getImage());
		backCard = new JButton(backofCard);
		backCard.setBounds(17 * 40 - 20, 4 * 40, backofCard.getIconWidth(), backofCard.getIconHeight());

		ImageIcon Img = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/cards/card1.png")).getImage());
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
        
		boolean inp[] = new boolean[1];
		inp[0] = false;
		
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
		
        this.NewGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	inp[0] = true;
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
        
        while(inp[0] != true) {
            try {
                Thread.sleep(20);
            } catch(InterruptedException e) {
            }
        }
        
        return inputDialogue();
	}

	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box for the number of players
	 * 
	 * @return The number of players entered by the user
	 */
	
	public int inputDialogue() {
		int userInput = 0;
		do {
			userInput = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Players: "));
		} while(!(userInput >= 2 && userInput <= 4));
		
	    return userInput;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * 
	 * Postconditions
	 * - Initializes the game window with the pawns of each player
	 * 
	 */
	
	public void startGameView(ArrayList<Player> p, Square[] roundsq, Square[] bluesq, Square[] redsq, Square[] yellowsq, Square[] greensq, StartSlideSquare ssqRed1, StartSlideSquare ssqBlue1, StartSlideSquare ssqYellow1, StartSlideSquare ssqGreen1, StartSlideSquare ssqRed2, StartSlideSquare ssqBlue2, StartSlideSquare ssqYellow2, StartSlideSquare ssqGreen2, EndSlideSquare esqRed1, EndSlideSquare esqBlue1, EndSlideSquare esqYellow1, EndSlideSquare esqGreen1, EndSlideSquare esqRed2, EndSlideSquare esqBlue2, EndSlideSquare esqYellow2, EndSlideSquare esqGreen2, Square startRed, Square startYellow, Square startBlue, Square startGreen, Square homeRed, Square homeYellow, Square homeBlue, Square homeGreen) {		
		
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
		
			int plcount = p.size();
			
			for(int i = 0; i < 60; i++) {
				roundsq[i].setLabel(roundsquares[i]);
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

			ImageIcon pr1 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/redPawn1.png")).getImage());
			pawnRed1 = new JButton(pr1);
			pawnRed1.setBackground(Color.WHITE);
			pawnRed1.setBounds(4 * 40 - 20, 40 + 10, 40, 40);
			Window.add(pawnRed1, JLayeredPane.POPUP_LAYER);
			
			ImageIcon pr2 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/redPawn2.png")).getImage());
			pawnRed2 = new JButton(pr2);
			pawnRed2.setBackground(Color.WHITE);
			pawnRed2.setBounds(5 * 40 - 20, 40 + 10, 40, 40);
			Window.add(pawnRed2, JLayeredPane.POPUP_LAYER);
			
			ImageIcon pb1 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/bluePawn1.png")).getImage());
			pawnBlue1 = new JButton(pb1);
			pawnBlue1.setBackground(Color.WHITE);
			pawnBlue1.setBounds(13 * 40, 3 * 40 + 30, 40, 40);
			Window.add(pawnBlue1, JLayeredPane.POPUP_LAYER);
			
			ImageIcon pb2 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/bluePawn2.png")).getImage());
			pawnBlue2 = new JButton(pb2);
			pawnBlue2.setBackground(Color.WHITE);
			pawnBlue2.setBounds(14 * 40, 3 * 40 + 30, 40, 40);
			Window.add(pawnBlue2, JLayeredPane.POPUP_LAYER);

			ImageIcon pg1 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/greenPawn1.png")).getImage());
			pawnGreen1 = new JButton(pg1);
			pawnGreen1.setBackground(Color.WHITE);
			pawnGreen1.setBounds(40, 10 * 40 + 30, 40, 40);
			Window.add(pawnGreen1, JLayeredPane.POPUP_LAYER);
			
			ImageIcon pg2 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/greenPawn2.png")).getImage());
			pawnGreen2 = new JButton(pg2);
			pawnGreen2.setBackground(Color.WHITE);
			pawnGreen2.setBounds(2 * 40, 10 * 40 + 30, 40, 40);
			Window.add(pawnGreen2, JLayeredPane.POPUP_LAYER);
			
			ImageIcon py1 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/yellowPawn1.png")).getImage());
			pawnYellow1 = new JButton(py1);
			pawnYellow1.setBackground(Color.WHITE);
			pawnYellow1.setBounds(11 * 40 - 20, 13 * 40 + 10, 40, 40);
			Window.add(pawnYellow1, JLayeredPane.POPUP_LAYER);
			
			ImageIcon py2 = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("resourses/images/pawns/yellowPawn2.png")).getImage());
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
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box for the names of the players
	 * 
	 * @return The names of players
	 */
	
	public String[] getNames(int c) {
	    String[] n = new String[c];
		for(int i = 1; i <= c; i++) {
			String userInput = JOptionPane.showInputDialog("Enter name of Player "+ i + " : ");
		    n[i - 1] = userInput;
		}
		
		return n;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * 
	 * Postconditions
	 * - Checks of the Fold Button is clicked
	 * 
	 * @return If the button is clicked return true or false
	 */
	
	public boolean clickCard() {
		boolean inp[] = new boolean[1];
		
        backCard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	inp[0] = true;
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
        
        while(inp[0] != true) {
            try {
                Thread.sleep(20);
            } catch(InterruptedException e) {
            }
        }
        
        return inp[0];
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box for which pawn should move
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user ( Pawn 1 or Pawn 2 )
	 */
	
	public int askForPawn(ArrayList <Player> p, int t) {
		
		Object[] options = {"Pawn 1", "Pawn 2"};
		
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " which pawn would you like to move ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return p1Orp2;
	}

	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a choice
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user ( Move Pawns or Draw Other Card )
	 */
	
	public int askEight(ArrayList <Player> p, int t) {
		
		Object[] options = {"Move Pawns", "Draw Other Card"};
	
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " what would you like to do ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return p1Orp2;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a choice
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user ( Move Pawns or Draw Other Card )
	 */
	
	public int askTwelve(ArrayList <Player> p, int t) {
		
		Object[] options = {"Move Pawns", "Draw Other Card"};
	
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " what would you like to do ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return p1Orp2;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a choice
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user ( Move Pawn 10 steps Forward or Move Pawn 1 step Back )
	 */
	
	public int askTen(ArrayList <Player> p, int t) {
		
		Object[] options = {"Move Pawn 10 steps Forward", "Move Pawn 1 step Back"};
	
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " what would you like to do ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return p1Orp2;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a choice
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user ( Move Pawn 11 steps Forward or Exchange Pawn )
	 */
	
	public int askEleven(ArrayList <Player> p, int t) {
		
		Object[] options = {"Move Pawn 11 steps Forward", "Exchange Pawn"};
	
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " what would you like to do ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return p1Orp2;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a choice
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user ( Move one pawn or Split moves )
	 */
	
	public int askSeven(ArrayList <Player> p, int t) {
		
		Object[] options = {"Move one pawn", "Split moves"};
	
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " what would you like to do ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return p1Orp2;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a choice
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return The choice of the user
	 */
		
	public int[] askElevenPawn(ArrayList <Player> p, int t) {
		
		int[] ret = new int[2];
		int ch = 0;
		
		if(p.size() == 2) {
			Object[] options = {"Player " + p.get(1 - t).getName()};
			ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(1 - t).getColour() + ")", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			ch = 1 - ch;
		} else if(p.size() == 3) {
			if(t == 0) {
				Object[] options = {"Player " + p.get(1).getName(), "Player " + p.get(2).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(1).getColour() + " , " + p.get(2).getColour() + ")" , null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				ch++;
			} else if(t == 1) {
				Object[] options = {"Player " + p.get(0).getName(), "Player " + p.get(2).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(0).getColour() + " , " + p.get(2).getColour() + ")", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(ch == 1) {
					ch = 2;
				}
			} else {
				Object[] options = {"Player " + p.get(0).getName(), "Player " + p.get(1).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(0).getColour() + " , " + p.get(1).getColour() + ")" , null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			}
		} else if(p.size() == 4) {
			if(t == 0) {
				Object[] options = {"Player " + p.get(1).getName(), "Player " + p.get(2).getName(), "Player " + p.get(3).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(1).getColour() + " , " + p.get(2).getColour() + p.get(3).getColour() + ")" , null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				ch++;
			} else if(t == 1) {
				Object[] options = {"Player " + p.get(0).getName(), "Player " + p.get(2).getName(), "Player " + p.get(3).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(0).getColour() + " , " + p.get(2).getColour() + p.get(3).getColour() + ")", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(ch == 1 || ch == 2) {
					ch++;
				}
			} else if(t == 2){
				Object[] options = {"Player " + p.get(0).getName(), "Player " + p.get(1).getName(), "Player " + p.get(3).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(0).getColour() + " , " + p.get(1).getColour() + p.get(3).getColour() + ")" , null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(ch == 2) {
					ch = 3;
				}
			} else {
				Object[] options = {"Player " + p.get(0).getName(), "Player " + p.get(1).getName(), "Player " + p.get(2).getName()};
				ch = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " from what player would you like to exchange pawns ? (" + p.get(0).getColour() + " , " + p.get(1).getColour() + p.get(2).getColour() + ")" , null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			}
		}
		
		Object[] options = {"Pawn 1", "Pawn 2"};
	    int p1Orp2 = JOptionPane.showOptionDialog(frame, "Player " + p.get(t).getName() + " with which pawn would you like to exchange ?", null , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
	    ret[0] = ch;
	    ret[1] = p1Orp2;
	    
	    return ret;
	    
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - p ArrayList must not be null
	 * - t must not be null
	 * 
	 * Postconditions
	 * - Asks the user using a dialogue box to make a chose how many squares should one of his pawns move
	 * 
	 * @param p The players ArrayList
	 * @param t Integer that shows which player has a turn
	 * @return Returns an integer array containing the number of squares each pawn should move
	 */
		
	public int[] AskSevenSplit(ArrayList <Player> p, int t) {
	    int[] res = new int[2];
	    
		String List[] = new String[6];

	    for (int i = 0; i < List.length; i++) {
	      List[i] = Integer.toString(i + 1);
	    }

	    int num = Integer.parseInt((String)JOptionPane.showInputDialog(frame, "How many squares should pawn 1 move ?", "Input", JOptionPane.QUESTION_MESSAGE, null, List, List[0]));
	    res[0] = num;
	    res[1] = 7 - num;
	    return res;
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - Card c must not be null
	 * 
	 * Postconditions
	 * - Updates the card JLabel graphics
	 * 
	 * @param c The current controller instance
	 */
		
	public void updateCards(Card c) {
		Image a = (new ImageIcon(getClass().getClassLoader().getResource(c.getImage())).getImage());
		Card.setIcon(new ImageIcon(a));
		Card.setVisible(true);
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - Pawn p must not be null
	 * - integer id must not be null
	 * 
	 * Postconditions
	 * - Updates the pawn JLabel graphics to make the pawn appear at the Start position
	 * 
	 * @param p A player's pawn
	 * @param id The id of the pawn
	 */
	
	public void sendToStart(Pawn p, int id) {
		if(p.getColour() == COLOUR.Red) {
			if(id == 0) {
				pawnRed1.setBounds(4 * 40 - 20, 40 + 10, 40, 40);
			} else {
				pawnRed2.setBounds(5 * 40 - 20, 40 + 10, 40, 40);	
			}
		} else if(p.getColour() == COLOUR.Green) {
			if(id == 0) {
				pawnGreen1.setBounds(40, 10 * 40 + 30, 40, 40);			
			} else {
				pawnGreen2.setBounds(2 * 40, 10 * 40 + 30, 40, 40);		
			}
		} else if(p.getColour() == COLOUR.Yellow) {
			if(id == 0) {
				pawnYellow1.setBounds(11 * 40 - 20, 13 * 40 + 10, 40, 40);					
			} else {
				pawnYellow2.setBounds(12 * 40 - 20, 13 * 40 + 10, 40, 40);	
			}
		} else {
			if(id == 0) {
				pawnBlue1.setBounds(13 * 40, 3 * 40 + 30, 40, 40);	
			} else {
				pawnBlue2.setBounds(14 * 40, 3 * 40 + 30, 40, 40);
			}
		}
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - Pawn p must not be null
	 * - integer id must not be null
	 * 
	 * Postconditions
	 * - Updates the pawn JLabel graphics to make the pawn appear at the Home position
	 * 
	 * @param p A player's pawn
	 * @param id The id of the pawn
	 */
	
	public void sendToHome(Pawn p, int id) {
		if(p.getColour() == COLOUR.Red) {
			if(id == 0) {
				pawnRed1.setBounds(2 * 40 - 20, 6 * 40 + 10, 40, 40);
			} else {
				pawnRed2.setBounds(3 * 40 - 20, 6 * 40 + 10, 40, 40);	
			}
		} else if(p.getColour() == COLOUR.Green) {
			if(id == 0) {
				pawnGreen1.setBounds(6 * 40, 12 * 40 + 30, 40, 40);			
			} else {
				pawnGreen2.setBounds(7 * 40, 12 * 40 + 30, 40, 40);		
			}
		} else if(p.getColour() == COLOUR.Yellow) {
			if(id == 0) {
				pawnYellow1.setBounds(13 * 40 - 20, 8 * 40 + 10, 40, 40);					
			} else {
				pawnYellow2.setBounds(14 * 40 - 20, 8* 40 + 10, 40, 40);	
			}
		} else {
			if(id == 0) {
				pawnBlue1.setBounds(8 * 40, 40 + 30, 40, 40);	
			} else {
				pawnBlue2.setBounds(9 * 40, 40 + 30, 40, 40);
			}
		}
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - Player pl must not be null
	 * 
	 * Postconditions
	 * - Updates the Player's pawns JLabel graphics
	 * 
	 * @param pl A player
	 */
	
	public void updatePawn(Player pl) {
		Pawn[] pawns = pl.getPawns();
		if(pl.getColour() == COLOUR.Red) {
			if(pawns[0].getSq().getNum() != -1 && pawns[0].getSq().getNum() != -2) {
				pawnRed1.setBounds(pawns[0].getSq().getLabel().getBounds());
			} else if(pawns[0].getSq().getNum() == -2) {
				sendToHome(pawns[0], 0);
			}
			if(pawns[1].getSq().getNum() != -1 && pawns[1].getSq().getNum() != -2) {
				pawnRed2.setBounds(pawns[1].getSq().getLabel().getBounds());
			} else if(pawns[1].getSq().getNum() == -2) {
				sendToHome(pawns[1], 1);
			}
		} else if(pl.getColour() == COLOUR.Green) {
			if(pawns[0].getSq().getNum() != -1 && pawns[0].getSq().getNum() != -2) {
				pawnGreen1.setBounds(pawns[0].getSq().getLabel().getBounds());
			} else if(pawns[0].getSq().getNum() == -2) {
				sendToHome(pawns[0], 0);
			}
			if(pawns[1].getSq().getNum() != -1 && pawns[1].getSq().getNum() != -2) {
				pawnGreen2.setBounds(pawns[1].getSq().getLabel().getBounds());
			} else if(pawns[1].getSq().getNum() == -2) {
				sendToHome(pawns[1], 1);
			}
		} else if(pl.getColour() == COLOUR.Yellow) {
			if(pawns[0].getSq().getNum() != -1 && pawns[0].getSq().getNum() != -2) {
				pawnYellow1.setBounds(pawns[0].getSq().getLabel().getBounds());
			} else if(pawns[0].getSq().getNum() == -2) {
				sendToHome(pawns[0], 0);
			}
			if(pawns[1].getSq().getNum() != -1 && pawns[1].getSq().getNum() != -2) {
				pawnYellow2.setBounds(pawns[1].getSq().getLabel().getBounds());
			} else if(pawns[1].getSq().getNum() == -2) {
				sendToHome(pawns[1], 1);
			}
		} else{
			if(pawns[0].getSq().getNum() != -1 && pawns[0].getSq().getNum() != -2) {
				pawnBlue1.setBounds(pawns[0].getSq().getLabel().getBounds());
			} else if(pawns[1].getSq().getNum() == -2) {
				sendToHome(pawns[0], 0);
			}
			if(pawns[1].getSq().getNum() != -1 && pawns[1].getSq().getNum() != -2) {
				pawnBlue2.setBounds(pawns[1].getSq().getLabel().getBounds());
			} else if(pawns[1].getSq().getNum() == -2) {
				sendToHome(pawns[1], 1);
			}
		}
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * 
	 * Postconditions
	 * - If the Fold button is clicked it hides the number card
	 * 
	 */
	
	public void clickFold() {
		boolean inp[] = new boolean[1];
		
        Fold.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	inp[0] = true;
            	Card.setVisible(false);
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
        
        while(inp[0] != true) {
            try {
                Thread.sleep(20);
            } catch(InterruptedException e) {
            }
        }
	}
	

	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - Player p must not be null
	 * - integer c must not be null
	 * 
	 * Postconditions
	 * - Updates the Info Box
	 * 
	 * @param p A player
	 * @param c The number of cards left in the deck
	 */
	
	public void updateInfo(Player p, int c) {
		String txt = "Turn: " + p.getName() + " (" + p.getColour() + ")\nCards Left: " + c;
		infoBox.setText(txt);
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the View class
	 * - Pawn p must not be null
	 * 
	 * Postconditions
	 * - Once the game has ended it shows the winner to the user
	 * 
	 * @param p A player
	 */
	
	public void showEnd(Player p) {
		JOptionPane.showMessageDialog(frame, "Player " + p.getName() + " (" + p.getColour() + ")  WON !!");
	}	
}