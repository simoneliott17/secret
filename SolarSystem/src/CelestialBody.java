import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

	public class CelestialBody
	{
		private CelestialBody[] Moons = new CelestialBody[10];
		private int moonCounter =0;
		private double startX;
		private double startY;
		private boolean initpos;
		private double mass = 0;
		private double diameter = 0;
	    private double xLoc = 0; 
	    private double yLoc = 0;
	    private double velX = 0; 
	    private double velY = 0;
	    Color color;
	    private double acceleration =0;
	    private double dirX = 0;
	    private double dirY = 0;
	    private double distance = 0;
	    boolean visible;
	    boolean frozen = false;
	    int orbitDots[][] = new int[1000][2];
	    int counter = 0;
	    /**
	     * Constructor for objects of class Planet
	     */
	    public CelestialBody(double  x, double y, double xVelocity, double yVelocity, double bodyMass, double bodyDiameter, Color bodyColor)
	    {
	       xLoc = x;
	       yLoc = y;
	       velX = xVelocity;
	       velY = yVelocity;
	       mass = bodyMass;
	       diameter = bodyDiameter/1000;
	       color = bodyColor;
	       
	    }
	   
	    public void addMoon(CelestialBody Moon){
	    
	    	Moons[moonCounter]=Moon;
	    	moonCounter++;
	    }
	    public void setYPosition(double Y){
	    	yLoc = Y;
	    	}
	    public void setXPosition(double X){
	    	xLoc = X;
	    	}
	    public double getXPosition(){
	    
	    	return xLoc;
	    	}
	    public double getYPosition(){
	    	return yLoc;
	    	}
	    
	    public Point getVelocity() {
	    	return new Point(xLoc, yLoc, zLoc);
	    }
	    
	    public double getMass(){
	    	return mass;
	    	}
	    public double getDiameter(){
	    	return diameter;
	    	}
	    public void setDisVis(boolean b) {
	    	visible = b;
	    	}
	    public void freeze() {
	    	frozen =! frozen;
	    }
	    
	    public void move()
	    {
	        xLoc += velX;
	        yLoc += velY;
	        
	        if(moonCounter>0){
	        	for(int i=0; i<moonCounter; i++){
					Moons[i].setXPosition(Moons[i].getXPosition()+velX);
					Moons[i].setYPosition(Moons[i].getYPosition()+velY);
				}
			}
	    }
	    public boolean initpos(){
	    	return initpos;
	    }
	 
	    		 
	    //The parameters define the position and mass of the sun
	    
	    public void update(double StarX, double StarY, double StarMass)
	    {
	       if(!frozen) {
	        distance = Math.sqrt((StarX - xLoc)*(StarX - xLoc) + (StarY - yLoc)*(StarY - yLoc) );
	        
	        
	        acceleration = StarMass/distance/distance;
	        
	        dirX = (StarX-xLoc)/distance;
	        dirY = (StarY-yLoc)/distance;
	        
	        velX += dirX * acceleration;
	        velY += dirY * acceleration;
	        
	        move();
	       
	        if(moonCounter>0){
	        	for(int i=0; i<moonCounter; i++){
	        		Moons[i].update(xLoc,yLoc,mass);
	        	}
	        }
	        
	        if(startX<xLoc+5 && startX>xLoc-5 && startY<yLoc+5 && startY>yLoc-5){
	        	initpos = true;
	        }else{
	        	initpos = false;
	        }
	       }
	       
	    }
	    public void draw(Graphics g, double size, double MovingLeft, double MovingUp)
	    {
	    	if(moonCounter>0) {
	    		for(int i =0; i<moonCounter;i++) {
	    			Moons[i].draw(g,size,MovingLeft,MovingUp);
	    		}
	    	}
	        g.setColor(color);
	        g.fillOval((int)(650+(xLoc-diameter/2-650)*size+MovingLeft), (int)(500+(yLoc-diameter-500)*size+MovingUp),(int)(diameter*size), (int)(diameter*size));
	    }
	  
	    
	    public void showDistance(Graphics g, double scale,double MovingLeft, double MovingUp)
	    {
	        g.setColor(color);
	        g.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 10));
	        g.setColor(Color.MAGENTA);	        
	        g.drawString((Math.round(distance*100.0)/100.0) * 1000000 + " km", (int)diameter+(int)(600+(xLoc-(int)diameter/2-600)*scale+MovingLeft), 16+(int)(400+(yLoc-diameter/2-400)*scale+MovingUp)+(int)diameter);
	        
	    }

		public int getZPosition() {
			// TODO Auto-generated method stub
			return 0;
		}
	    
	}
