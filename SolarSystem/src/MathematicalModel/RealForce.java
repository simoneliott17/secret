package MathematicalModel;

import java.awt.Color;
import java.awt.Graphics;

public class RealForce {
	private final double GRAVITY_CONSTANT = 6.673 * Math.pow(10, -8);
	private final double CONSTANT = 10000000;
	double distance = 0;
	double acceleration = 0;
	double xLoc = 0;
	double yLoc = 0;
	double zLoc = 0;
	double velX = 0;
	double velY = 0;
	double velZ = 0;
	double accX = 0;
	double accY = 0;
	double accZ = 0;
	double dirX = 0;
	double dirY = 0;
	String name = "";
	double dirZ = 0;
	double mass = 0;
	boolean frozen = false;
	int timeStep = 0;

	public RealForce(String name, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity,
			double bodyMass) {
		xLoc = x;
		yLoc = y;
		zLoc = z;
		velX = xVelocity;
		velY = yVelocity;
		velZ = zVelocity;
		mass = bodyMass;
		this.name = name;
	}

	public double getMass() {
		return mass;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public double getVelZ() {
		return velZ;
	}

	public double getAccX() {
		return accX;
	}

	public double getAccY() {
		return accY;
	}

	public double getAccZ() {
		return accZ;
	}

	public void setXPosition(double X) {
		xLoc = X;
	}

	public void setYPosition(double Y) {
		yLoc = Y;
	}

	public void setZPosition(double Z) {
		zLoc = Z;
	}

	public double getXPosition() {

		return xLoc;
	}

	public void changeVelocity(double vx, double vy, double vz) {
		velX += vx;
		velY += vy;
		velZ += vz;
	}

	public Point getLocation() {
		Point pt = new Point(xLoc, yLoc, zLoc);
		return pt;
	}

	public Point getVelocity() {
		Point pt = new Point(velX, velY, velZ);
		return pt;
	}

	public double getYPosition() {
		return yLoc;
	}

	public double getZPosition() {
		return zLoc;
	}

	public String getName() {
		return this.name;
	}

	public void move() {

		xLoc += velX * timeStep;
		yLoc += velY * timeStep;
		zLoc += velZ * timeStep;

	}

	public void freeze() {
		frozen = !frozen;
	}

	/*
	 * public void update(double StarX, double StarY, double StarZ, double StarMass)
	 * { if (!frozen) {
	 * 
	 * distance = Math.sqrt((StarX - xLoc) * (StarX - xLoc) + (StarY - yLoc) *
	 * (StarY - yLoc) + (StarZ - zLoc) * (StarZ - zLoc));
	 * 
	 * acceleration = (GRAVITY_CONSTANT * StarMass * mass) / distance / distance;
	 * 
	 * // acceleration = StarMass/distance/distance;
	 * 
	 * dirX = (StarX - xLoc) / distance; dirY = (StarY - yLoc) / distance; dirZ =
	 * (StarZ - zLoc) / distance;
	 * 
	 * velX += dirX * acceleration; velY += dirY * acceleration; velZ += dirZ *
	 * acceleration; System.out.println(distance); move(); } }
	 */
	public void setVelX(double d) {
		velX = d;

	}

	public void setVelY(double d) {
		velY = d;
	}

	public void setVelZ(double d) {
		velZ = d;
	}

	public void setAccX(double d) {
		accX = d;

	}

	public void setAccY(double d) {
		accY = d;
	}

	public void setAccZ(double d) {
		accZ = d;
	}
	// private final double GRAVITY_CONSTANT = 6.673 * Math.pow(10, -11);

	public void calculate_single_body_acceleration(RealForce[] bodies, int index) {

		Point acceleration = new Point();
		RealForce targetBody = bodies[index];

		for (int i = 0; i < bodies.length; i++) {
			if (i != index) {
				double r = (Math.pow((targetBody.getXPosition() - bodies[i].getXPosition()), 2)
						+ Math.pow((targetBody.getYPosition() - bodies[i].getYPosition()), 2)
						+ Math.pow((targetBody.getZPosition() - bodies[i].getZPosition()), 2));
				r = Math.sqrt(r);
				//double tmp = GRAVITY_CONSTANT * bodies[index].getMass() / (Math.pow(r, 3))  * 1000000000;
				double tmp = 1000.0;
				bodies[index].setAccX(tmp * (bodies[index].getXPosition() - targetBody.getXPosition()));
				bodies[index].setAccY(tmp * (bodies[index].getYPosition() - targetBody.getYPosition()));
				bodies[index].setAccZ(tmp * (bodies[index].getZPosition() - targetBody.getZPosition()));
				
				//targetBody.setAccX(targetBody.getAccX() + tmp * (bodies[i].getXPosition() - targetBody.getXPosition()));
				//targetBody.setAccY(targetBody.getAccY() + tmp * (bodies[i].getYPosition() - targetBody.getYPosition()));
				//targetBody.setAccZ(targetBody.getAccZ() + tmp * (bodies[i].getZPosition() - targetBody.getZPosition()));
			}
		}

	}

	// Updates the velocities
	// dv = a.dt;
	private void computeVelocity(RealForce[] bodies, int timeStep) {
		for (int i = 0; i < bodies.length; i++) {
			// Point acceleration = calculate_single_body_acceleration(bodies, i);
			RealForce targetBody = bodies[i];
			System.out.println(targetBody.getName());
			System.out.println("VX: " + targetBody.getVelX() + "\nVY: " + targetBody.getVelY() + "\nVZ: "
					+ targetBody.getVelZ());
			
			bodies[i].setVelX(bodies[i].getVelX() + bodies[i].getAccX() * timeStep);
			bodies[i].setVelY(bodies[i].getVelY() + bodies[i].getAccY() * timeStep);
			bodies[i].setVelZ(bodies[i].getVelZ() + bodies[i].getAccZ() * timeStep);
		}
	}

	// Updates the locations
	// dx = v*dt
	void updateLocation(RealForce[] bodies, int timeStep) {
		for (int i = 0; i < bodies.length; i++) {
			RealForce targetBody = bodies[i];
			System.out.println(targetBody.getName());
			System.out.println("X: " + targetBody.getXPosition() + "\nY: " + targetBody.getYPosition() + "\nZ: "
					+ targetBody.getZPosition());

			targetBody.setXPosition(targetBody.getXPosition() + targetBody.getVelX() * timeStep);
			targetBody.setYPosition(targetBody.getYPosition() + targetBody.getVelY() * timeStep);
			targetBody.setZPosition(targetBody.getZPosition() + targetBody.getVelZ() * timeStep);

		}
	}

	// Combines the location and velocity update
	public void computeGravityStep(RealForce[] bodies, int timeStep) {
		for (int i = 0; i < bodies.length; i++) {

			calculate_single_body_acceleration(bodies, i);
		}
		updateLocation(bodies, timeStep);
		computeVelocity(bodies, timeStep);
	}

	public void draw(Graphics g, double zoom) {

		g.setColor(Color.YELLOW);
		g.fillOval((int) (650 + (xLoc / CONSTANT) * zoom), (int) (500 + (yLoc / CONSTANT) * zoom), (int) (25 * zoom),
				(int) (25 * zoom));
	}

}
