import MathematicalModel.Point;
import MathematicalModel.RealForce;

public class Euler {
    private final double GRAVITY_CONSTANT = 6.673 * Math.pow(10, -11);

    public Point calculate_single_body_acceleration(RealForce[] bodies, int index) {

        Point acceleration = new Point();
        RealForce targetBody = bodies[index];

        for (int i = 0; i < bodies.length; i++) {
            if (i != index) {
                double r =(Math.pow((targetBody.getXPosition() - bodies[i].getXPosition()), 2) + Math.pow((targetBody.getYPosition() - bodies[i].getYPosition()), 2)); // + Math.pow((targetBody.getZPosition() - bodies[i].getZPosition()), 2);
                r = Math.sqrt(r);
                double tmp = GRAVITY_CONSTANT * bodies[index].getMass() / (Math.pow(r, 3));
                acceleration.changeX(tmp * (bodies[index].getXPosition() - targetBody.getXPosition()));
                acceleration.changeY(tmp * (bodies[index].getYPosition() - targetBody.getYPosition()));
                //            acceleration.changeZ(tmp*(bodies[index].getZPosition()-targetBody.getZPosition()));
            }
        }
        return acceleration;
    }


    //  Updates the velocities
    //  dv = a.dt;
    private void computeVelocity(RealForce[] bodies, int timeStep) {
        for (int i = 0; i < bodies.length; i++) {
            Point acceleration = calculate_single_body_acceleration(bodies, i);
            bodies[i].setVelX(bodies[i].getVelX() + acceleration.getX() * timeStep);
            bodies[i].setVelY(bodies[i].getVelY() + acceleration.getY() * timeStep);
            //     bodies[i].setVelZ(bodies[i].getVelZ()+acceleration.getZ()*timeStep);
        }
    }


    //   Updates the locations
    //   dx = v*dt
    private void updateLocation(RealForce[] bodies, int timeStep) {
        for (int i = 0; i < bodies.length; i++) {
            RealForce targetBody = bodies[i];
            System.out.println("X: "+targetBody.getXPosition()+"\nY: "+targetBody.getYPosition());

            targetBody.setXPosition(targetBody.getXPosition() + targetBody.getVelX() * timeStep);
            targetBody.setYPosition(targetBody.getYPosition() + targetBody.getVelY() * timeStep);
//          targetBody.setZPosition(targetBody.getZPosition()+targetBody.getVelZ()*timeStep);
        }
    }


    //    Combines the location and velocity update
    public void computeGravityStep(RealForce[] bodies, int timeStep){
        computeVelocity(bodies,1);
        updateLocation(bodies,1);
    }



}


