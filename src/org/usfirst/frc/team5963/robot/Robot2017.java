package org.usfirst.frc.team5963.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings("deprecation")
public class Robot2017 extends IterativeRobot {
	Spark leftDrive1;
	Spark leftDrive2;
	Spark rightDrive1;
	Spark rightDrive2;
	CameraServer camera;
	Joystick stick;
	Joystick controller;
	int autoLoopCounter;
	Spark frontMotor;

	public Robot2017() {
	}
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */;
    public void robotInit() {
    	
		camera = CameraServer.getInstance();
		//camera.setQuality(50);
		camera.startAutomaticCapture(0);           //originally ("cam0"), changed to int
    	stick = new Joystick(0);
    	controller = new Joystick(1);
    	rightDrive1 = new Spark(1);
    	rightDrive2 = new Spark(2);
    	leftDrive1 = new Spark(3);
    	leftDrive2 = new Spark(4);
    	frontMotor = new Spark(0);
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit()
    {
		rightDrive1.set(0);
		rightDrive2.set(0);
		leftDrive1.set(0);
		leftDrive2.set(0);
		frontMotor.set(0);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
    	// joystick
    	double leftPower = stick.getY();
    	double rightPower = -stick.getY();
    	double xAxis = -stick.getX();
    	double frontMotorPower = 1;
    	boolean triggerButton = stick.getButton(Joystick.ButtonType.kTrigger);
    	leftPower = leftPower + xAxis;
    	rightPower = rightPower + xAxis;

    	leftPower = Math.min(leftPower, 1);
    	leftPower = Math.max(leftPower, -1);
    	rightPower = Math.min(rightPower, 1);
    	rightPower = Math.max(rightPower, -1);
    	leftDrive1.set(-leftPower);
    	rightDrive1.set(-rightPower);
    	leftDrive2.set(-leftPower);
    	rightDrive2.set(-rightPower);
    	if (triggerButton){
    		frontMotor.set(frontMotorPower); 
    	}
    	else
    	{
    		frontMotor.set(0);
    	}

    	/* xboxcontroller
    	double armPower = controller.getRawAxis(1);
    	double intakePower = controller.getRawAxis(5);

    	armDrive.set(-armPower);
    	intakeMotor.set(-intakePower);
    	*/
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit()
    {
    	rightDrive1.set(0);
		rightDrive2.set(0);
		leftDrive1.set(0);
		leftDrive2.set(0);
    	autoLoopCounter = 0;
    	frontMotor.set(0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
    	if(autoLoopCounter < 250) //Check if we've completed 150 loops (approximately 3.5 seconds)
		{
    		double speed = 0.7;
    		leftDrive1.set(speed);
    		leftDrive2.set(speed);
        	rightDrive1.set(-speed);
    		rightDrive2.set(-speed);
			autoLoopCounter++;
		}
    	else
    	{
        	rightDrive1.set(0);
    		rightDrive2.set(0);
    		leftDrive1.set(0);
    		leftDrive2.set(0);
		}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {
    	LiveWindow.run();
    }
    
}
