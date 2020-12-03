package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class Robot {
    private MecanumDrive m_drive;
    private Motor frontLeft, frontRight, backLeft, backRight, intake;
    private ServoEx flipper, arm1, arm2, gripper1, gripper2;
    private RevIMU gyro;
    private double strafeSpeed, forwardSpeed, turnSpeed, angle;
    private DcMotor shooterRight, shooterLeft;
    private ElapsedTime time;
    private Trajectory gtp;
    private SampleMecanumDrive drive;



    public Robot (HardwareMap hardwareMap) {
        frontLeft = new MotorEx(hardwareMap, "motor_fl");
        frontRight = new MotorEx(hardwareMap, "motor_fr");
        backLeft = new MotorEx(hardwareMap, "motor_rl");
        backRight = new MotorEx(hardwareMap, "motor_rr");

        time = new ElapsedTime();

        //gyro
        gyro = new RevIMU(hardwareMap, "gyro");

        //intake
        intake = new MotorEx(hardwareMap, "intake");

        //shooter
        shooterRight =  hardwareMap.get(DcMotor.class, "shootRight");
        shooterLeft = hardwareMap.get(DcMotor.class, "shootLeft");

        //RUN_USING_ENCODER
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //servos
        flipper = new SimpleServo(hardwareMap, "flipper");
        arm1 = new SimpleServo(hardwareMap, "armLeft");
        arm2 = new SimpleServo(hardwareMap, "armRight");
        gripper1 = new SimpleServo(hardwareMap, "clawLeft");
        gripper2 = new SimpleServo(hardwareMap, "clawRight");


        //just some object declerations
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        drive = new SampleMecanumDrive(hardwareMap);

        gtp = drive.trajectoryBuilder(new Pose2d())
                .addTemporalMarker(0, () -> {
                    shooterLeft.setPower(-1);
                    shooterRight.setPower(-0.75);
                })
                .lineToLinearHeading(new Pose2d(40, 40, Math.toRadians(90)))
                .build();
    }
    public void flip3() {
        time.reset();
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

    public void rev(){
        shooterLeft.setPower(-1);
        shooterRight.setPower(-0.75);
    }
    public void flip(){
        time.reset();
        while (time.milliseconds() < 300) {}
        flipper.setPosition(0.460);
        time.reset();
        while (time.milliseconds() < 600) {}
        flipper.setPosition(0.270);
    }

    public void drive (GamepadEx gamepadEx1) {
        strafeSpeed = gamepadEx1.getLeftX();
        forwardSpeed = gamepadEx1.getLeftY();
        turnSpeed = gamepadEx1.getRightX();
        angle = gyro.getAbsoluteHeading();
        m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);
    }

    public void gyroRest() {
        gyro.reset();
        gyro.init();
    }

    public void intakeIN() {
        intake.set(-1);
    }
    public void intakeOUT() {
        intake.set(1);
    }
    public void intakeSTOP() {
        intake.set(0);
    }

    public void gTP () {
        drive.followTrajectory(gtp);
    }

    public void leftarm1 () {
        arm1.setPosition(0.560);
        gripper1.setPosition(0.550);
    }

    public void leftarm2() {
        arm1.setPosition(0.410);
        gripper1.setPosition(0.550);
    }

    public void leftarm3() {
        arm1.setPosition(0.410);
        gripper1.setPosition(0.220);
    }
    public void leftarm4(){
        arm1.setPosition(0.560);
        gripper1.setPosition(0.220);
    }


    public void rightarm1(){
        arm2.setPosition(0.450);
        gripper2.setPosition(0);
    }
    public void rightarm2(){
        arm2.setPosition(0.600);
        gripper2.setPosition(0);
    }
    public void rightarm3(){
        arm2.setPosition(0.600);
        gripper2.setPosition(0.390);
    }
    public void rightarm4(){
        arm2.setPosition(0.450);
        gripper2.setPosition(0.390);
    }


}
