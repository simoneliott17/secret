package MathematicalModel;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





public class RealTrajectories extends JPanel {
	static RealForce[] mathModel = new RealForce[9];
	final static int DELAY = 50;
	double zoom = 1;
	boolean freeze = true;
	Model model;

	public RealTrajectories() {
		
		model = new Model();
        model.setPreferredSize(new Dimension(1200, 800));
        add(model);
        setBackground(Color.BLACK);
        
		mathModel[0] = new RealForce("Sun", 0, 0, 0, 0, 0, 0, RealMasses.SUN_MASS);
		mathModel[1] = new RealForce("Mercury", RealDistance.mercuryXDistance, RealDistance.mercuryYDistance,
				RealDistance.mercuryZDistance, RealVelocities.mercuryXVel, RealVelocities.mercuryYVel,
				RealVelocities.mercuryZVel, RealMasses.MERCURY_MASS);
		mathModel[2] = new RealForce("Venus", RealDistance.venusXDistance, RealDistance.venusYDistance,
				RealDistance.venusZDistance, RealVelocities.venusXVel, RealVelocities.venusYVel,
				RealVelocities.venusZVel, RealMasses.VENUS_MASS);
		mathModel[3] = new RealForce("Earth", RealDistance.earthXDistance, RealDistance.earthYDistance,
				RealDistance.earthZDistance, RealVelocities.earthXVel, RealVelocities.earthYVel,
				RealVelocities.earthZVel, RealMasses.EARTH_MASS);
		mathModel[4] = new RealForce("Mars", RealDistance.marsXDistance, RealDistance.marsYDistance,
				RealDistance.marsZDistance, RealVelocities.marsXVel, RealVelocities.marsYVel, RealVelocities.marsZVel,
				RealMasses.MARS_MASS);
		mathModel[5] = new RealForce("Jupiter", RealDistance.jupiterXDistance, RealDistance.jupiterYDistance,
				RealDistance.jupiterZDistance, RealVelocities.jupiterXVel, RealVelocities.jupiterYVel,
				RealVelocities.jupiterZVel, RealMasses.JUPITER_MASS);
		mathModel[6] = new RealForce("Saturn", RealDistance.saturneXDistance, RealDistance.saturneYDistance,
				RealDistance.saturneZDistance, RealVelocities.saturnXVel, RealVelocities.saturnYVel,
				RealVelocities.saturnZVel, RealMasses.SATURNE_MASS);
		mathModel[7] = new RealForce("Uranus", RealDistance.uranusXDistance, RealDistance.uranusYDistance,
				RealDistance.uranusZDistance, RealVelocities.uranusXVel, RealVelocities.uranusYVel,
				RealVelocities.uranusZVel, RealMasses.URANUS_MASS);
		mathModel[8] = new RealForce("Neptune", RealDistance.neptuneXDistance, RealDistance.neptuneYDistance,
				RealDistance.neptuneZDistance, RealVelocities.neptuneXVel, RealVelocities.neptuneYVel,
				RealVelocities.neptuneZVel, RealMasses.NEPTUNE_MASS);

		/*Thread thread = new Thread() {

			@Override
			public void run() {
				Orbit();

			}
		};
		*/

		//thread.start();
	}

	public RealForce[] getMathModel() {
		return mathModel;
	}

	public void Orbit() {
		while (true) {
			if(!freeze) {
			for (int i = 0; i < mathModel.length; i++) {
				mathModel[i].computeGravityStep(mathModel, 1000000);
			}
			}
			repaint();
			
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
						zoom -= e.getWheelRotation() * 0.1;
					} else {
						zoom -= e.getWheelRotation() * 0.1;
					}
				}
			});

		}

		public void paintComponent(Graphics g) {

			// Draws the oval form of all Celestial Bodies
			ImageIcon explosion = new ImageIcon("/Users/Eliott/Documents/explosion.png");

			for (RealForce body : mathModel) {
				body.draw(g, zoom);
			}
			// rocket.draw(g, zoom, MovingLeft, MovingUp);

			// Shows the distance between planets and the sun

			for (int i = 0; i < mathModel.length; i++) {

				// mathModel[i].showDistance(g);

			}
			// rocket.showDistance(g, zoom, MovingLeft, MovingUp);

			// if(rocket.getXPosition()>595 && rocket.getXPosition()<605 &&
			// rocket.getYPosition()>395 && rocket.getYPosition()<405) {
			// g.drawImage(explosion.getImage(), (int)rocket.getXPosition(),
			// (int)rocket.getYPosition(), this);
			// }
			/*
			 * if(celestialBodies[8].getXPosition()>celestialBodies[9].getXPosition()-10 &&
			 * celestialBodies[8].getXPosition()<celestialBodies[9].getXPosition()+10 &&
			 * celestialBodies[8].getYPosition()>celestialBodies[9].getYPosition()-10 &&
			 * celestialBodies[8].getYPosition()<celestialBodies[9].getYPosition()+10) {
			 * g.drawImage(explosion.getImage(), (((int)celestialBodies[9].getXPosition()-10
			 * )* (int)zoom) + (int) MovingLeft, (((int)
			 * celestialBodies[9].getYPosition()-7) *(int) zoom) + (int) MovingUp , this);
			 * freeze = true;
			 * 
			 * }
			 */

		}

		public void keyTyped(KeyEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			//mousePt = e.getPoint();
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
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				freeze = !freeze;
			}
			if (e.getKeyCode() == KeyEvent.VK_Z) {
				zoom = zoom + .1;
			}
			if (e.getKeyCode() == KeyEvent.VK_O && zoom > 0) {
				zoom = zoom - .1;
			}
		}

	}
	class MouseEventHandler extends MouseAdapter{         
		@Override
		public void mouseDragged(MouseEvent e){
			
			//mousePt.x = e.getX();
			//mousePt.y = e.getY();
		}
	}
	 public static void main(String[] args)
	    {
	        
	            JFrame frame = new JFrame("Solar System Model Group 4");
	            frame.setContentPane(new RealTrajectories());  
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.pack();
	            frame.setLocationRelativeTo(null); 
	            frame.setVisible(true); 
	           // RealTrajectories skuu = new RealTrajectories();
	           // RealForce[] reee = skuu.getMathModel();
	           // System.out.println(reee[1].getXPosition());
	            //System.out.println(reee[1].getYPosition());
	    }
 

}
