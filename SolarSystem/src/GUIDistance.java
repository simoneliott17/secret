import MathematicalModel.RealDistance;

//Distances are in km
public class GUIDistance {
	final static double CONSTANT = 1000000.0;
	final static double sunXPosition = 600;
	final static double sunYPosition = 400;
	
	
	static double guiMercuryXDistance = RealDistance.mercuryXDistance / CONSTANT;
	static double guiMercuryYDistance = RealDistance.mercuryYDistance / CONSTANT;
	
	static double guiVenusXDistance = RealDistance.venusXDistance / CONSTANT;
	static double guiVenusYDistance = RealDistance.venusYDistance / CONSTANT;
	
	static double guiEarthXDistance = RealDistance.earthXDistance / CONSTANT;
	static double guiEarthYDistance = RealDistance.earthYDistance / CONSTANT;
	
	static double guiMarsXDistance = RealDistance.marsXDistance / CONSTANT;
	static double guiMarsYDistance = RealDistance.marsYDistance / CONSTANT;
	
	static double guiJupiterXDistance = RealDistance.jupiterXDistance / CONSTANT;
	static double guiJupiterYDistance = RealDistance.jupiterYDistance / CONSTANT;
	
	static double guiSaturneXDistance = RealDistance.saturneXDistance / CONSTANT;
	static double guiSaturneYDistance = RealDistance.saturneYDistance / CONSTANT;
	
	static double guiUranusXDistance = RealDistance.uranusXDistance / CONSTANT;
	static double guiUranusYDistance = RealDistance.uranusYDistance / CONSTANT;
	
	static double guiNeptuneXDistance = RealDistance.neptuneXDistance / CONSTANT;
	static double guiNeptuneYDistance = RealDistance.neptuneYDistance / CONSTANT;

	public static double getMercuryXDistance() {
		return (sunXPosition + guiMercuryXDistance); 
	}
	
	public static double getMercuryYDistance() {
		return (sunYPosition + guiMercuryYDistance); 
	}

	public static double getVenusXDistance() {
		return (sunXPosition + guiVenusXDistance); 
	}
	
	public static double getVenusYDistance() {
		return (sunYPosition + guiVenusYDistance); 
	}
	
	public static double getEarthXDistance() {
		return (sunXPosition + guiEarthXDistance); 
	}
	
	public static double getEarthYDistance() {
		return (sunYPosition + guiEarthYDistance);
	}
	
	public static double getMarsXDistance() {
		return (sunXPosition + guiMarsXDistance);
	}
	
	public static double getMarsYDistance() {
		return (sunYPosition + guiMarsYDistance);
	}
	
	public static double getJupiterXDistance() {
		return (sunXPosition + guiJupiterXDistance);
	}
	
	public static double getJupiterYDistance() {
		return (sunYPosition + guiJupiterYDistance);
	}
	
	public static double getSaturneXDistance() {
		return (sunXPosition + guiSaturneXDistance);
	}
	
	public static double getSaturneYDistance() {
		return (sunYPosition + guiSaturneYDistance); 
	}
	
	public static double getUranusXDistance() {
		return(sunXPosition + guiUranusXDistance);
	}
	
	public static double getUranusYDistance() {
		return(sunYPosition + guiUranusYDistance);
	}
	public static double getNeptuneXDistance() {
		return (sunXPosition + guiNeptuneXDistance); 
	}
	public static double getNeptuneYDistance() {
		return (sunYPosition + guiNeptuneYDistance); 
	}
}
