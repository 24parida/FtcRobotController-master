package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
@Disabled
class PositionTracker extends LinearOpMode {

    private MecanumDrive m_drive;
    private MotorEx frontLeft, backRight, frontRight, backLeft;
    private MotorEx encoderLeft, encoderRight, encoderPerp;
    private GamepadEx gamepadEx;
    private double strafeSpeed, forwardSpeed, turnSpeed, angle;

    public static final double WHEEL_DIAMETER = 1.96850394;
    public static final double TICKS_PER_REV = 28;

    static final double TRACKWIDTH = 13.7;
    static final double TICKS_TO_INCHES = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;
    static final double CENTER_WHEEL_OFFSET = 2.4;

    @Override
    public void runOpMode() throws InterruptedException {
        //drive motors
        frontLeft = new MotorEx(hardwareMap, "front_left");
        frontRight = new MotorEx(hardwareMap, "front_right");
        backLeft = new MotorEx(hardwareMap, "back_left");
        backRight = new MotorEx(hardwareMap, "back_right");

        //odometry
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

        //just some object deceleration
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);

        waitForStart();


        while(opModeIsActive()) {
            //tele-op drive
            strafeSpeed = gamepadEx1.getLeftX();
            forwardSpeed = gamepadEx1.getLeftY();
            turnSpeed = gamepadEx1.getRightX();
            angle = odometry.getPose().getHeading();

            m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);

            telemetry.addData("Position: ", "X: " + odometry.getPose().getX() );
            telemetry.addData("Position: ", "Y: " + odometry.getPose().getY());
            telemetry.addData("Position: ", "Angle: " + odometry.getPose().getHeading());
            telemetry.update();
        }



    }
}
