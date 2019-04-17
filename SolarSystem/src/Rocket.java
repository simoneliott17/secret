import java.awt.*;

public class Rocket {
    private final double GRAVITY_CONSTANT = 6.673*Math.pow(10, -11);
    private double mass = 0;
    private double diameter = 0;
    private double xLoc = 0;
    private double yLoc = 0;
    private double velX = 0;
    private double velY = 0;
    Color color;
    private double distance = 0;
    boolean visible;

    public Rocket(double x, double y, double xVelocity, double yVelocity, double bodyMass, double bodyDiameter, Color bodyColor){
        xLoc = x;
        yLoc = y;
        velX = xVelocity;
        velY = yVelocity;
        mass = bodyMass;
        diameter = bodyDiameter/1000;
        color = bodyColor;
    }

    public double getXPosition(){
        return xLoc;
    }
    public double getYPosition(){
        return yLoc;
    }
    public double getMass(){
        return mass;
    }

    public void updateRocket(double StarX, double StarY) {

        distance = Math.sqrt((StarX - xLoc)*(StarX - xLoc) + (StarY - yLoc)*(StarY - yLoc));

        xLoc += velX;
        yLoc += velY;

    }
    public void draw(Graphics g, double size, double MovingLeft, double MovingUp)
    {
        g.setColor(color);
        g.fillOval((int)(650+(xLoc-diameter/2-650)*size+MovingLeft), (int)(500+(yLoc-diameter/2-500)*size+MovingUp),(int)(diameter*size), (int)(diameter*size));
    }

    public void showDistance(Graphics g, double scale, double MovingLeft, double MovingUp)
    {
        g.setColor(color);
        g.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 10));
        g.setColor(Color.MAGENTA);
        g.drawString((Math.round(distance*100.0)/100.0) * 1000000 + " km", (int)diameter+(int)(600+(xLoc-(int)diameter/2-600)*scale+MovingLeft), 16+(int)(400+(yLoc-diameter/2-400)*scale+MovingUp)+ (int)diameter);

    }
    public int posNeg(double num){
        if(num ==0)
            return 0;
        if(num>0)
            return 1;
        return -1;
    }

    public void gravity(CelestialBody[] celestialBodies){
    
        for(int i=0; i<9; i++){
            double difX = (xLoc - celestialBodies[i].getXPosition());
            double difY = (yLoc - celestialBodies[i].getYPosition());
            double force =0;
            		
            
            double squareDistance = (difX*difX+difY*difY);
            double realDistance = Math.pow(((Math.round(squareDistance*100.0)/100.0) * 100000000),2);
            
            if (i == 8) {
            	 force = ((GRAVITY_CONSTANT*mass*(50000*Math.pow(10, 23)))/(realDistance));
            }
            else {
            	 force = ((GRAVITY_CONSTANT*mass*(celestialBodies[i].getMass()*Math.pow(10, 23)))/(realDistance));
            }
           
            //May not work properly
            //Error if it goes exactly to the center of the planet

            //If on same vertical, then only the y velocity is affected
            if(difX == 0)
                velY+=posNeg(difY)*force;
            //if on same horizontal only x velocity is affected
            else if(difY == 0)
                velX-=posNeg(difX)*force;
            //Else both are affected, proportions may be off/ there may be something wrong with my calculations
            else {
                double proportion = Math.abs(difX /(difY+difX));
                velX -= posNeg(difX) * proportion * force;
                velY += posNeg(difY) * (1-proportion) * force;
            }
            //System.out.println(i +": "+force);
        }
    }
    public void realGravity(CelestialBody[] celestialBodies) {
    	for(int i = 0 ; i < celestialBodies.length;i++) {
    		double sqdistance = (xLoc - celestialBodies[i].getXPosition())*(xLoc - celestialBodies[i].getXPosition()) + (yLoc - celestialBodies[i].getYPosition())*(yLoc - celestialBodies[i].getYPosition());
    		double force = ((GRAVITY_CONSTANT*mass*celestialBodies[i].getMass()));
    	}
    }

}
