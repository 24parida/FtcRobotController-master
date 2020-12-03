package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Position test", group="Tests")

public class PositionTracker extends LinearOpMode {

    private MecanumDrive m_drive;
    private MotorEx frontLeft, backRight, frontRight, backLeft;
    private MotorEx encoderLeft, encoderRight, encoderPerp;
    private GamepadEx gamepadEx1;
    private double strafeSpeed, forwardSpeed, turnSpeed, angle;
    private RevIMU gyro;


    public static final double WHEEL_DIAMETER = (0.68897638 * 2);
    public static final double TICKS_PER_REV = 8192;

    static final double TRACKWIDTH = 10.5;
    static final double TICKS_TO_INCHES = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;
    static final double CENTER_WHEEL_OFFSET = 1.85;

    @Override
    public void runOpMode() throws InterruptedException {
        //drive motors
        frontLeft = new MotorEx(hardwareMap, "motor_fl");
        frontRight = new MotorEx(hardwareMap, "motor_fr");
        backLeft = new MotorEx(hardwareMap, "motor_rl");
        backRight = new MotorEx(hardwareMap, "motor_rr");

        //odometry
        encoderLeft = new MotorEx(hardwareMap, "fake motor");
        encoderRight = new MotorEx(hardwareMap, "shootRight");
        encoderPerp = new MotorEx(hardwareMap, "shootLeft");

        //encoder pulse definition
        encoderLeft.setDistancePerPulse(TICKS_TO_INCHES);
        encoderRight.setDistancePerPulse(TICKS_TO_INCHES);
        encoderPerp.setDistancePerPulse(TICKS_TO_INCHES);

        // create the odometry object
        HolonomicOdometry odometry = new HolonomicOdometry(
                encoderLeft::getDistance,
                encoderRight::getDistance,
                encoderPerp::getDistance,
                TRACKWIDTH, CENTER_WHEEL_OFFSET
        );


        //just some object deceleration
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        gamepadEx1 = new GamepadEx(gamepad1);
        gyro = new RevIMU(hardwareMap, "gyro");


        waitForStart();


        while(opModeIsActive()) {
            //tele-op drive
            strafeSpeed = gamepadEx1.getLeftX();
            forwardSpeed = gamepadEx1.getLeftY();
            turnSpeed = gamepadEx1.getRightX();
            angle = gyro.getAbsoluteHeading();

            m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);

            odometry.updatePose();

            telemetry.addData("Position: ", "X: " + odometry.getPose().getX() );
            telemetry.addData("Position: ", "Y: " + odometry.getPose().getY());
            telemetry.addData("Position: ", "Angle: " + odometry.getPose().getHeading());
            telemetry.addData("right sticks", "X: " + strafeSpeed + "Y: " + forwardSpeed + "turn: " + turnSpeed + "angle gyro: " + angle);
            telemetry.update();
        }



    }
}
