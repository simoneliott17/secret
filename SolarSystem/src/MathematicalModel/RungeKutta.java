package MathematicalModel;

public class RungeKutta {
	private final double GRAVITY_CONSTANT = 6.673*Math.pow(10, -11);
	private CelestialBody[] bodies;
	Point first = new Point();
	Point second = new Point();
	Point third = new Point();
	Point fourth = new Point();
	public double xLoc = 0;
	public double yLoc = 0;
	public double zLoc  = 0 ; 
	public double velY = 0;
	public double velX = 0;
	public double velZ = 0;
	public double mass = 0;
	public double rpt = 0;
	
	
	public double distance = 0; 
	
	Point acceleration =  new Point();
	
	public RungeKutta(int timestep, CelestialBody[] bodies){
        this.timestep = timestep;
        this.bodies = bodies;
    }
	
	public void previousPos(Point first, Point second, double t) {
		Point next = new Point();
		next.x = first.x + second.x * t; 
		next.y = first.y + second.y * t;
		next.z = first.z + second.z * t;
	}
	
	public void Orbit(double SunX, double SunY, double SunZ) {
		distance = Math.sqrt((SunX - xLoc)*(SunX - xLoc) + (SunY - yLoc)*(SunY - yLoc) + (SunZ - zLoc)*(SunZ - zLoc));  
		
		rpt = GRAVITY_CONSTANT * mass/ Math.pow(distance, 3);
		
		
		
	}
	public void 
	
	
}
