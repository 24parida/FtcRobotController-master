package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Thread.sleep;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

public class Robot {
    private MecanumDrive m_drive;
    private Motor frontLeft, frontRight, backLeft, backRight, intake;
    private ServoEx flipper, arm1, arm2, gripper1, gripper2;
    private GamepadEx gamepadEx1, gamepadEx2;
    private RevIMU gyro;
    private double strafeSpeed, forwardSpeed, turnSpeed, angle;
    private DcMotor shooterRight = null;
    private DcMotor shooterLeft = null;
    private int arm1Down, arm2Down, grip1Down, grip2Down;
    private ElapsedTime time;



    public Robot (HardwareMap hardwareMap) {
        frontLeft = new MotorEx(hardwareMap, "motor_fl");
        frontRight = new MotorEx(hardwareMap, "motor_fr");
        backLeft = new MotorEx(hardwareMap, "motor_rl");
        backRight = new MotorEx(hardwareMap, "motor_rr");
        time = new ElapsedTime();

        //gyro
        gyro = new RevIMU(hardwareMap, "testGyro");

        //intake
        intake = new MotorEx(hardwareMap, "intakeMotor");

        //shooter
        shooterRight =  hardwareMap.get(DcMotor.class, "shootingMotorRight");
        shooterLeft = hardwareMap.get(DcMotor.class, "shootingMotorLeft");

        //RUN_USING_ENCODER
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //servos
        flipper = new SimpleServo(hardwareMap, "leftFlipper");
        arm1 = new SimpleServo(hardwareMap, "liftServo");
        arm2 = new SimpleServo(hardwareMap, "liftServo2");
        gripper1 = new SimpleServo(hardwareMap, "grabServo");
        gripper2 = new SimpleServo(hardwareMap, "grabServo2");


        //just some object declerations
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        arm1Down = 1;
        arm2Down = 1;
        grip1Down = 1;
        grip2Down = 1;
    }
    public void flip3() {




        while (time.milliseconds() < 2000) {}
        flipper.setPosition(0.270);
        time.reset();
        while (time.milliseconds() < 700) {}
        flipper.setPosition(0.460);
        time.reset();
        while (time.milliseconds() < 600) {}
        flipper.setPosition(0.270);
        time.reset();
        while (time.milliseconds() < 300) {}
        flipper.setPosition(0.460);
        time.reset();
        while (time.milliseconds() < 600) {}
        flipper.setPosition(0.270);
        time.reset();
        while (time.milliseconds() < 300) {}
        flipper.setPosition(0.460);
    }

    public void arm() throws InterruptedException {

    }

    public void drive () {
        strafeSpeed = gamepadEx1.getLeftX();
        forwardSpeed = gamepadEx1.getLeftY();
        turnSpeed = gamepadEx1.getRightX();
        angle = gyro.getAbsoluteHeading();

        m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);
    }

    public void gyroRest() {

    }



}
