import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import MathematicalModel.RealMasses;
import MathematicalModel.RealTrajectories;
import MathematicalModel.RealVelocities;
import MathematicalModel.RealForce;

import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Point;

public class SolarSystemMain extends JPanel
{
    Model model;
    static CelestialBody[] celestialBodies = new CelestialBody[10];
    static CelestialBody[] earthMoon = new CelestialBody[2];
    boolean[] descriptionSeen = new boolean[9];
    BufferedImage planets = new BufferedImage(5,5,1);
    final static int DELAY = 50; 
    double zoom = 1;
    boolean freeze = true;
    boolean frozen = false;
    int clicked = -1;
    final static double CONSTANT = 1000000;
    private double MovingUp=0;
    private double MovingLeft=0;
    Point mousePt;
    Rocket rocket;
 
        
    public SolarSystemMain()
    {
          model = new Model();
          model.setPreferredSize(new Dimension(1200, 800));
          add(model);
          setBackground(Color.BLACK);
       
          	//Make an array in which every index represents a planet

          celestialBodies[0] = new CelestialBody(GUIDistance.getMercuryXDistance() , GUIDistance.getMercuryYDistance(), RealVelocities.mercuryXVel/CONSTANT*36000,RealVelocities.mercuryYVel/CONSTANT*36000, 3.3, 4878, Color.MAGENTA); //MERCURY
          celestialBodies[1] = new CelestialBody(GUIDistance.getVenusXDistance(), GUIDistance.getVenusYDistance(), RealVelocities.venusXVel/CONSTANT, RealVelocities.venusYVel/CONSTANT*3.6 , 48.7, 12104, Color.PINK);  //VENUS
          celestialBodies[2] = new CelestialBody(GUIDistance.getEarthXDistance(), GUIDistance.getEarthYDistance(), RealVelocities.earthXVel/CONSTANT,RealVelocities.earthYVel/CONSTANT, 59.8, 12756, Color.BLUE);  //EARTH
          celestialBodies[3] = new CelestialBody(GUIDistance.getMarsXDistance(), GUIDistance.getMarsYDistance(), RealVelocities.marsXVel/CONSTANT, RealVelocities.marsYVel/CONSTANT, 6.42, 6787, Color.RED); 	//MARS
          celestialBodies[4] = new CelestialBody(GUIDistance.getJupiterXDistance(), GUIDistance.getJupiterYDistance(), RealVelocities.jupiterXVel/CONSTANT, RealVelocities.jupiterYVel/CONSTANT, 19000, 142796, Color.ORANGE); //JUPITER 
          celestialBodies[5] = new CelestialBody(GUIDistance.getSaturneXDistance(), GUIDistance.getSaturneYDistance(), RealVelocities.saturnXVel/CONSTANT, RealVelocities.saturnYVel/CONSTANT, 5690, 120660, Color.GRAY); //SATURN
          celestialBodies[6] = new CelestialBody(GUIDistance.getUranusXDistance(), GUIDistance.getUranusYDistance(), RealVelocities.uranusXVel/CONSTANT, RealVelocities.uranusYVel/CONSTANT, 8681, 51118, Color.CYAN);  //URANUS
          celestialBodies[7] = new CelestialBody(GUIDistance.getNeptuneXDistance(), GUIDistance.getNeptuneYDistance(), RealVelocities.neptuneXVel/CONSTANT, RealVelocities.neptuneYVel/CONSTANT, 10240, 48600, Color.GREEN);  //NEPTUNE
          celestialBodies[8] = new CelestialBody(500,600, 0,0, 1763,5000, Color.WHITE);//TEST
          celestialBodies[9] = new CelestialBody(GUIDistance.sunXPosition, GUIDistance.sunYPosition, 0, 0, RealMasses.SUN_MASS, 23910, Color.YELLOW);  //SUN
        
          
          
          // Earth's Moon
          //celestialBodies[2].addMoon( new CelestialBody(450, 408, -2.65, 0.6, 0.07342, 3474, Color.WHITE));//EARTH MOON
          // Jupiter's Moons
          
          celestialBodies[4].addMoon(new CelestialBody(590, 1350, -13.5, 0, 0.8931, 3660, Color.WHITE));//IO 
          celestialBodies[4].addMoon(new CelestialBody(625, 1175, 15, 4, 0.4799, 3121, Color.WHITE));//EUROPA
          celestialBodies[4].addMoon(new CelestialBody(650, 1165, 13, 6, 1.4819, 5268, Color.WHITE));//GANYMEDE
          celestialBodies[4].addMoon(new CelestialBody(700, 1120, 8, 6, 1.075, 4820, Color.WHITE));//CALLISTO
           //need to adjust position and speed
         // Saturn's Moons 
          
          celestialBodies[5].addMoon(new CelestialBody(600, 1814, -9.48, 0.8, 1.3452, 5149,Color.RED));
          
          //rocket = new Rocket(celestialBodies[2].getXPosition()+20, celestialBodies[2].getYPosition()+20, 0.6,2, 1, 2000, Color.CYAN);
          rocket = new Rocket(celestialBodies[2].getXPosition()-5, celestialBodies[2].getYPosition()-5, -0.1,-2, 131000, 2000, Color.CYAN);
          ///INTERSECTION
          /*
          celestialBodies[0] = new CelestialBody(xPos-46000000000.0/100000000.0, yPos, 0, -58980/4000000 , PlanetConstants.MERCURY_MASS/4000000, 2439700*2/4000000, Color.WHITE); 
            celestialBodies[1] = new CelestialBody(xPos-107480000000.0/100000000.0, yPos, 0, -35260/4000000, PlanetConstants.VENUS_MASS/4000000, 6051800*2/4000000, Color.ORANGE); 
            celestialBodies[2] = new CelestialBody(xPos-147095000000.0/100000000.0, yPos, 0, -30300/4000000 , PlanetConstants.EARTH_MASS/4000000, 6371000*2/4000000, Color.BLUE); 
            celestialBodies[3] = new CelestialBody(xPos-206620000000.0/100000000.0, yPos, 0, -26500/4000000, PlanetConstants.MARS_MASS/4000000, 3389500*2/4000000, Color.ORANGE); 
            celestialBodies[4] = new CelestialBody(xPos-740520000000.0/100000000.0, yPos, 0, -13720/4000000, PlanetConstants.JUPITER_MASS/4000000, 71492000*2/4000000, Color.GRAY); 
            celestialBodies[5] = new CelestialBody(xPos-1352550000000.0/100000000.0, yPos, 0, -10180/4000000, PlanetConstants.SATURNE_MASS/4000000, 54364000*2/4000000, Color.GRAY); 
            celestialBodies[6] = new CelestialBody(xPos -2741300000000.0/100000000.0, yPos, 0, -7110/4000000, PlanetConstants.URANUS_MASS/4000000, 24973000*2/4000000, Color.GRAY); 
            celestialBodies[7] = new CelestialBody(xPos -4444450000000.0/100000000.0, yPos, 0, -5500/4000000 , PlanetConstants.NEPTUNE_MASS/4000000, 24341000*2/4000000, Color.GRAY);
            celestialBodies[8] = new CelestialBody(xPos, yPos, 0, 0, PlanetConstants.SUN_MASS/4000000, 695700000*2/4000000, Color.YELLOW);
          */
            //This thread allows us to get a view on the 8 planets at the same time
            
          Thread thread =  new Thread() {
     
            @Override
             public void run() {
                Orbit();
                
             }
          }; 
          
          thread.start();
          
    }
   
    public static BufferedImage getImage(String ref) {  //loading the image
        BufferedImage bimg = null;  
        try {  
  
            bimg = ImageIO.read(new File(ref));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bimg;  
    }  
   
    
    private void Orbit() {
    	
      while (true) {
    	  long startTime =System.nanoTime();	
    	  long endTime =System.nanoTime();
  		  long timeElapsed = endTime-startTime;
  		  int updatecounter =0;
  		  
  		  if(!freeze) 
          {      
        	  for(int i = 0; i < celestialBodies.length-1; i++)
                {
                	//Get positions with regards to the sun
                	celestialBodies[i].update(celestialBodies[9].getXPosition(),celestialBodies[9].getYPosition(),900);
                	boolean test;
                	//System.out.println(rocket.getXPosition());
                	if(i==1){
                		if(celestialBodies[i].getXPosition()<1566 && celestialBodies[i].getXPosition()>1565)
                			//System.out.println(celestialBodies[i].getYPosition());
                		updatecounter++;
						test=celestialBodies[i].initpos();
						if(updatecounter>1){
							if(test){
								endTime =System.nanoTime();
								timeElapsed = endTime-startTime;
								startTime =System.nanoTime();
								System.out.println("Speed : "+i+"  "+timeElapsed);
								System.out.println("Counter : "+i+"  "+updatecounter);
								updatecounter=0;
								
								
								/** Updates it takes for one rotation:
										Mercury: 	120
										  Venus:	230
										  Earth:	385
										   Mars:	717
										Jupiter:	4782
										 Saturn:	11342
						 		*/								
							}
						}
					}
                }
        	  
        	  rocket.updateRocket(celestialBodies[8].getXPosition(), celestialBodies[8].getYPosition());
              rocket.gravity(celestialBodies);
        	  
              if(rocket.getXPosition()>595 && rocket.getXPosition()<605 && rocket.getYPosition()>395 && rocket.getYPosition()<405) {
            	  freeze=true;           	  
              }
              if(celestialBodies[8].getXPosition()>celestialBodies[9].getXPosition()-10 
             		 && celestialBodies[8].getXPosition()<celestialBodies[9].getXPosition()+10
             		 && celestialBodies[8].getYPosition()>celestialBodies[9].getYPosition()-10
             		 && celestialBodies[8].getYPosition()<celestialBodies[9].getYPosition()+10) {
            	  celestialBodies[8].freeze();
              }
          }
         repaint();
         
         try {
            Thread.sleep(DELAY);
         } catch (InterruptedException ex) { }
      }
   }
    

    class Model extends JPanel implements KeyListener, MouseListener {
    	
    	public Model() {
    		setFocusable(true); 
            requestFocus();
            addKeyListener(this);
            addMouseListener(this);
            MouseEventHandler handler = new MouseEventHandler();
            addMouseListener(handler); 
            addMouseMotionListener(handler);
            addMouseWheelListener(new MouseWheelListener() {
            	public void mouseWheelMoved(MouseWheelEvent e) {
      			if (e.getWheelRotation() < 0) {
      			  zoom -= e.getWheelRotation()*0.1;
      			} else {
      			  zoom -= e.getWheelRotation()*0.1;
      			}  
      		  }
      		});
    	}	
       
    	
    	public void paintComponent(Graphics g) {

    	  //Draws the oval form of all Celestial Bodies
    	 ImageIcon explosion = new ImageIcon("/Users/Eliott/Documents/explosion.png");
    	 
        
         for(CelestialBody body : celestialBodies) {
        	 body.draw(g,zoom,MovingLeft,MovingUp);
         }
         rocket.draw(g, zoom, MovingLeft, MovingUp);
         
         //Shows the distance between planets and the sun
         
         for (int i = 0; i < celestialBodies.length; i++)
         {
             
            	 celestialBodies[i].showDistance(g,zoom,MovingLeft,MovingUp);
            	 
         }
         rocket.showDistance(g, zoom, MovingLeft, MovingUp);
         
         if(rocket.getXPosition()>595 && rocket.getXPosition()<605 && rocket.getYPosition()>395 && rocket.getYPosition()<405) {
        	 g.drawImage(explosion.getImage(), (int)rocket.getXPosition(), (int)rocket.getYPosition(), this);
         }
         if(celestialBodies[8].getXPosition()>celestialBodies[9].getXPosition()-10 
        		 && celestialBodies[8].getXPosition()<celestialBodies[9].getXPosition()+10
        		 && celestialBodies[8].getYPosition()>celestialBodies[9].getYPosition()-10
        		 && celestialBodies[8].getYPosition()<celestialBodies[9].getYPosition()+10) {
        	 g.drawImage(explosion.getImage(), (((int)celestialBodies[9].getXPosition()-10 )* (int)zoom) + (int) MovingLeft, (((int) celestialBodies[9].getYPosition()-7) *(int) zoom) + (int) MovingUp , this);
        	 		freeze = true;
        	 
         }

         
      }
      
      public void keyTyped(KeyEvent e) {
    	  
      }
      public void mousePressed(MouseEvent e) {
    	  mousePt = e.getPoint();
      }
      public void mouseReleased(MouseEvent e) {
          
      }
      public void mouseEntered(MouseEvent e) { 
    	  
      }
      public void mouseExited(MouseEvent e) { 
    	  
      }
      public void mouseClicked(MouseEvent e) { 
    	  
      }
      
      public void keyPressed(KeyEvent e) {
    	  
      }
      
      

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			freeze = !freeze;
		}
		if(e.getKeyCode()== KeyEvent.VK_Z) {
			zoom = zoom + .1;
		}
		if(e.getKeyCode()== KeyEvent.VK_O && zoom > 0) {
			zoom = zoom - .1;
		}
	}
    

   }
    class MouseEventHandler extends MouseAdapter{         
		@Override
		public void mouseDragged(MouseEvent e){
			MovingLeft += (e.getX() - mousePt.x);
			MovingUp += (e.getY() - mousePt.y);
			mousePt.x = e.getX();
			mousePt.y = e.getY();
		}
	}
 
    
    public static void main(String[] args)
    {
        
            JFrame frame = new JFrame("Solar System Model Group 4");
            frame.setContentPane(new SolarSystemMain());  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); 
            frame.setVisible(false); 
            RealTrajectories skuu = new RealTrajectories();
            RealForce[] reee = skuu.getMathModel();
           // System.out.println(reee[1].getXPosition());
            //System.out.println(reee[1].getYPosition());
    }
}

