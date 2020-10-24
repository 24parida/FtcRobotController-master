package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//shooter test is a class to run the shooter motor with encoders in order to keep velocity constant
class ShooterTest extends LinearOpMode {

    private MotorEx shooterRight, shooterLeft;
    public static final double WHEEL_DIAMETER = 4.0;
    public static final double TICKS_PER_REV = 28;


    private static final double TICKS_TO_INCHES = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;


    @Override
    public void runOpMode() throws InterruptedException {
        //shooter motor deceleration
        shooterRight = new MotorEx(hardwareMap, "shooter_right");
        shooterLeft = new MotorEx(hardwareMap, "shooter_left");

        //encoder ticks/inches
        shooterRight.setDistancePerPulse(TICKS_TO_INCHES);
        shooterLeft.setDistancePerPulse(TICKS_TO_INCHES);

        //make sure that motors don't slip while stopped
        shooterRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        shooterLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        //RUN_USING_ENCODER
        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);
        shooterRight.setRunMode(Motor.RunMode.VelocityControl);

        while(opModeIsActive()) {
            //5202 1:1 motor || ticks per rev * rpm * seconds / minute * percentage
            shooterLeft.setVelocity(7 * 6000 * 60 * 0.9);
            shooterRight.setVelocity(0);
        }
    }
}
