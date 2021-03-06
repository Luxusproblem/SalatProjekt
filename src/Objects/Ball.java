package Objects;
import static java.lang.Math.sqrt;

import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Ball {
	
	private final DoubleProperty xVelocity; // pixel pro sekunde
	private final DoubleProperty yVelocity; // selbe n�r y Koordinaten
	private final ReadOnlyDoubleWrapper speed;
	private final double mass; // Masse repr�sentant 
	private final double radius; // Ballgr��e

	private final Circle view;

	public Ball(double centerX, double centerY, double radius, double xVelocity, double yVelocity, double mass) {

		this.view = new Circle(centerX, centerY, radius);
		this.xVelocity = new SimpleDoubleProperty(this, "xVelocity", xVelocity);
		this.yVelocity = new SimpleDoubleProperty(this, "yVelocity", yVelocity);
		this.speed = new ReadOnlyDoubleWrapper(this, "speed");
		speed.bind(Bindings.createDoubleBinding(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				final double xVel = getXVelocity();
				final double yVel = getYVelocity();
				return sqrt(xVel * xVel + yVel * yVel);
			}
		}, this.xVelocity, this.yVelocity));
		this.mass = mass;
		this.radius = radius;
		view.setRadius(radius);
		view.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setXVelocity(5000);
				setYVelocity(5770);
				
			}
		

		

		});
	}

	public double getMass() {
		return mass;
	}

	public double getRadius() {
		return radius;
	}

	public final double getXVelocity() {
		return xVelocity.get();
	}

	public final void setXVelocity(double xVelocity) {
		//System.out.println("new XVelo: " + xVelocity);
		this.xVelocity.set(xVelocity);
	}

	public final DoubleProperty xVelocityProperty() {
		return xVelocity;
	}

	public final double getYVelocity() {
		return yVelocity.get();
	}

	public final void setYVelocity(double yVelocity) {
		//System.out.println("New YVelo: " + yVelocity);
		this.yVelocity.set(yVelocity);
	}

	public final DoubleProperty yVelocityProperty() {
		return yVelocity;
	}

	public final double getSpeed() {
		return speed.get();
	}

	public final ReadOnlyDoubleProperty speedProperty() {
		return speed.getReadOnlyProperty();
	}

	public final double getCenterX() {
		return view.getCenterX();
	}

	public final void setCenterX(double centerX) {
		view.setCenterX(centerX);
	}

	public final DoubleProperty centerXProperty() {
		return view.centerXProperty();
	}

	public final double getCenterY() {
		return view.getCenterY();
	}

	public final void setCenterY(double centerY) {
		view.setCenterY(centerY);
	}

	public final DoubleProperty centerYProperty() {
		return view.centerYProperty();
	}

	public Shape getView() {
		return view;
	}
}