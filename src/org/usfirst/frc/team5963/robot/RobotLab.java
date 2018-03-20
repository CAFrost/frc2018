/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5963.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotLab extends IterativeRobot {
	private CameraServer camera;

	private WPI_VictorSPX arm;
	private WPI_TalonSRX leftFront, leftBack, rightFront, rightBack;
	private SpeedControllerGroup leftDrive, rightDrive;
	private DifferentialDrive differentialDrive;
	private Joystick joystick;
	private Timer m_timer = new Timer();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		camera = CameraServer.getInstance();
		camera.startAutomaticCapture(0);           //originally ("cam0"), changed to int

		arm = new WPI_VictorSPX(5);
		arm.setInverted(true);
		
		leftFront = new WPI_TalonSRX(2);
		leftBack = new WPI_TalonSRX(3);
		rightFront = new WPI_TalonSRX(0);
		rightBack = new WPI_TalonSRX(1);
		
		leftDrive = new SpeedControllerGroup(leftFront, leftBack);
		rightDrive = new SpeedControllerGroup(rightFront, rightBack);
		
		differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
		
		joystick = new Joystick(0);
		
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds
		if (m_timer.get() < 2.0) {
			//differentialDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
		} else {
			//differentialDrive.stopMotor(); // stop robot
		}
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		differentialDrive.arcadeDrive(joystick.getY(), -joystick.getX());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
