package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

class FieldCentricDrive extends LinearOpMode {

    private MecanumDrive m_drive;
    private Motor frontLeft, frontRight, backLeft, backRight, shooterRight, shooterLeft, intake;
    private ServoEx flipperRight, flipperLeft, armLeft, armRight, gripperLeft, gripperRight;
    private GamepadEx gamepadEx;
    private RevIMU gyro;
    double strafeSpeed, forwardSpeed, turnSpeed, angle;
    double flipperEndPos, flipperStartPos;
    double armEndPos, armStartPos, gripperEnd, gripperStart;

    @Override
    public void runOpMode() throws InterruptedException {
        //drive motors
        frontLeft = new Motor(hardwareMap, "front_left");
        frontRight = new Motor(hardwareMap, "front_right");
        backLeft = new Motor(hardwareMap, "back_left");
        backRight = new Motor(hardwareMap, "back_right");
        //shooter + intake motors
        shooterRight = new Motor(hardwareMap, "shooter_right");
        shooterLeft = new Motor(hardwareMap, "shooter_left");
        intake = new Motor(hardwareMap, "intake");
        //Indexer servos
        flipperRight = new SimpleServo(hardwareMap, "flipperRight");
        flipperLeft = new SimpleServo(hardwareMap, "flipperLeft");
        //armservos
        armLeft = new SimpleServo(hardwareMap, "armLeft");
        armRight = new SimpleServo(hardwareMap, "armRight");
        gripperLeft = new SimpleServo(hardwareMap, "gripperLeft");
        gripperRight = new SimpleServo(hardwareMap, "gripperRight");

        //just some object declerations
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

        gyro = new RevIMU(hardwareMap, "gyro");

        shooterRight.setRunMode(Motor.RunMode.VelocityControl);

        waitForStart();


        while(opModeIsActive()) {
            //shooter testing
            if (gamepadEx1.getButton(GamepadKeys.Button.X)) {

            }

            //teleopDrive
            strafeSpeed = gamepadEx1.getLeftX();
            forwardSpeed = gamepadEx1.getLeftY();
            turnSpeed = gamepadEx1.getRightX();
            angle = gyro.getAbsoluteHeading();

            m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);
        }
    }


}