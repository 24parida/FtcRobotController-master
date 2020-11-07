package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.GyroEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.GyroSensor;

// Field Centric Drive using odometry to get angle
@TeleOp(name="Field Centric Test", group="Tests")
public class FieldCentricDrive extends LinearOpMode {

    private MecanumDrive m_drive;
    private MotorEx frontLeft, backRight, frontRight, backLeft;
   // private MotorEx encoderLeft, encoderRight, encoderPerp;
    private GamepadEx gamepadEx;
    private double strafeSpeed, forwardSpeed, turnSpeed, angle;
    private GyroEx gyro;

    /*
    public static final double WHEEL_DIAMETER = 1.96850394;
    public static final double TICKS_PER_REV = 28;


    static final double TRACKWIDTH = 13.7;
    static final double TICKS_TO_INCHES = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;
    static final double CENTER_WHEEL_OFFSET = 2.4;
     */

    @Override
    public void runOpMode() throws InterruptedException {
        //drive motors
        frontLeft = new MotorEx(hardwareMap, "motor_fl");
        frontRight = new MotorEx(hardwareMap, "motor_fr");
        backLeft = new MotorEx(hardwareMap, "motor_rl");
        backRight = new MotorEx(hardwareMap, "motor_rr");

        gyro = new RevIMU(hardwareMap, "testGyro");

       /* //odometry
        encoderLeft = new MotorEx(hardwareMap, "left_encoder");
        encoderRight = new MotorEx(hardwareMap, "right_encoder");
        encoderPerp = new MotorEx(hardwareMap, "center_encoder");

        //encoder pulse definition
        encoderLeft.setDistancePerPulse(TICKS_TO_INCHES);
        encoderRight.setDistancePerPulse(TICKS_TO_INCHES);
        encoderPerp.setDistancePerPulse(TICKS_TO_INCHES);

        // create the odometry object
        HolonomicOdometry holOdom = new HolonomicOdometry(
                encoderLeft::getDistance,
                encoderRight::getDistance,
                encoderPerp::getDistance,
                TRACKWIDTH, CENTER_WHEEL_OFFSET
        );

        // create the odometry subsystem
        OdometrySubsystem odometry = new OdometrySubsystem(holOdom);

        */

        //just some object deceleration
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);

        waitForStart();

        gyro.init();

        while(opModeIsActive()) {
            //tele-op drive
            strafeSpeed = gamepadEx1.getLeftX();
            forwardSpeed = gamepadEx1.getLeftY();
            turnSpeed = gamepadEx1.getRightX();
            angle = gyro.getAbsoluteHeading();

            m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);
        }
    }


}