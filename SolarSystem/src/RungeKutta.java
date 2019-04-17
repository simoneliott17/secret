import MathematicalModel.Point;
import MathematicalModel.RealForce;

public class RungeKutta {
	private final double GRAVITY_CONSTANT = 6.673 * Math.pow(10, -11);
	private RealForce[] bodies;
	private double timestep;
	Point acceleration = new Point();
	public RungeKutta(int timestep, RealForce[] bodies) {
		this.timestep = timestep;
		this.bodies = bodies;
	}

	public Point partialStep(Point point1, Point point2, double time_step) {

		Point point = new Point();

		point.changeX(point1.getX() + point2.getX() * time_step);
		point.changeY(point1.getY() + point2.getY() * time_step);
		point.changeZ(point1.getZ() + point2.getZ() * time_step);

		return point;
	}

	public Point getAcceleration(int index) {
		//Point acceleration = new Point();
		RealForce targetBody = bodies[index];

		Point k1 = new Point();
		Point k2 = new Point();
		Point k3 = new Point();
		Point k4 = new Point();
		Point tmpLoc = new Point();
		Point tmpVel = new Point();

		for (int i = 0; i < bodies.length; i++) {
			if (i != index) {
				double r = Math.pow(targetBody.getXPosition() - bodies[i].getXPosition(), 2)
						+ Math.pow(targetBody.getYPosition() - bodies[i].getYPosition(), 2)
						+ Math.pow(targetBody.getZPosition() - bodies[i].getZPosition(), 2);
				r = Math.sqrt(r);
				double tmp = GRAVITY_CONSTANT * bodies[i].getMass() / Math.pow(r, 3);

				k1.setX(tmp * (bodies[i].getXPosition() - targetBody.getXPosition()));
				k1.setY(tmp * (bodies[i].getYPosition() - targetBody.getYPosition()));
				k1.setZ(tmp * (bodies[i].getZPosition() - targetBody.getZPosition()));

				tmpVel = partialStep(targetBody.getVelocity(), k1, 0.5);
				tmpLoc = partialStep(targetBody.getLocation(), tmpVel, 0.5);

				k2.setX((tmpLoc.getX() - (tmpLoc.getX() + tmpVel.getX() * 0.5 * timestep)) * tmp);
				k2.setY((tmpLoc.getY() - (tmpLoc.getY() + tmpVel.getY() * 0.5 * timestep)) * tmp);
				k2.setZ((tmpLoc.getZ() - (tmpLoc.getZ() + tmpVel.getZ() * 0.5 * timestep)) * tmp);

				tmpVel = partialStep(targetBody.getVelocity(), k2, 0.5);

				k3.setX((tmpLoc.getX() - (tmpLoc.getX() + tmpVel.getX() * 0.5 * timestep)) * tmp);
				k3.setY((tmpLoc.getY() - (tmpLoc.getY() + tmpVel.getY() * 0.5 * timestep)) * tmp);
				k3.setZ((tmpLoc.getZ() - (tmpLoc.getZ() + tmpVel.getZ() * 0.5 * timestep)) * tmp);

				tmpVel = partialStep(targetBody.getVelocity(), k1, 1);
				tmpLoc = partialStep(targetBody.getLocation(), tmpVel, 0.5);

				k4.setX(bodies[i].getXPosition() - (tmpLoc.getX() + tmpVel.getX() * this.timestep) * tmp);
				k4.setY(bodies[i].getYPosition() - (tmpLoc.getY() + tmpVel.getY() * this.timestep) * tmp);
				k4.setZ(bodies[i].getZPosition() - (tmpLoc.getZ() + tmpVel.getZ() * this.timestep) * tmp);

				acceleration.changeX((k1.getX() + k2.getX() * 2 + k3.getX() * 2 + k4.getX()) / 6);
				acceleration.changeY((k1.getY() + k2.getY() * 2 + k3.getY() * 2 + k4.getY()) / 6);
				acceleration.changeZ((k1.getZ() + k2.getZ() * 2 + k3.getZ() * 2 + k4.getZ()) / 6);
			}
		}
		return acceleration;
	}

	public void updateLocation() {
		for (int i = 0; i < bodies.length; i++) {
			bodies[i].setXPosition(bodies[i].getXPosition() + bodies[i].getVelocity().getX() * timestep);
			bodies[i].setYPosition(bodies[i].getYPosition() + bodies[i].getVelocity().getY() * timestep);
			bodies[i].setZPosition(bodies[i].getZPosition() + bodies[i].getVelocity().getZ() * timestep);
		}
	}
	/*
	public void getVelocity() {
		for (int i = 0; i < bodies.length; i++) {
			acceleration = getAcceleration(i);
			bodies[i].getVelocity().getX() += acceleration 
			
		}
	}
	*/
}
