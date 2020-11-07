package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="DriverCode Test", group="Tests")
public class FullJavaTelop extends LinearOpMode {

    private MecanumDrive m_drive;
    private Motor frontLeft, frontRight, backLeft, backRight, intake;
    private ServoEx flipperServo, arm1, arm2, gripper1, gripper2;
    private GamepadEx gamepadEx1, gamepadEx2;
    private RevIMU gyro;
    private double strafeSpeed, forwardSpeed, turnSpeed, angle;
    private DcMotor shooterRight = null;
    private DcMotor shooterLeft = null;
    private int arm1Down, arm2Down, grip1Down, grip2Down;
    private ButtonReader lArm, rArm, lGrip, rGrip, gTPread, shooterStop;
    private ButtonReader inIn, inOut, inStop, flickRead;
    private TriggerReader shooterRev;


    @Override
    public void runOpMode() throws InterruptedException {
        //drive motors
        frontLeft = new MotorEx(hardwareMap, "motor_fl");
        frontRight = new MotorEx(hardwareMap, "motor_fr");
        backLeft = new MotorEx(hardwareMap, "motor_rl");
        backRight = new MotorEx(hardwareMap, "motor_rr");

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
        flipperServo = new SimpleServo(hardwareMap, "leftFlipper");
        arm1 = new SimpleServo(hardwareMap, "liftServo");
        arm2 = new SimpleServo(hardwareMap, "liftServo2");
        gripper1 = new SimpleServo(hardwareMap, "grabServo");
        gripper2 = new SimpleServo(hardwareMap, "grabServo2");


        //just some object declerations
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        //gm1
        lArm = new ButtonReader(gamepadEx1, GamepadKeys.Button.X);
        rArm = new ButtonReader(gamepadEx1, GamepadKeys.Button.A);
        lGrip = new ButtonReader(gamepadEx1, GamepadKeys.Button.Y);
        rGrip = new ButtonReader(gamepadEx1, GamepadKeys.Button.B);
        gTPread = new ButtonReader(gamepadEx1, GamepadKeys.Button.DPAD_UP);

        //gm2
        shooterRev = new TriggerReader(gamepadEx2, GamepadKeys.Trigger.RIGHT_TRIGGER);
        inOut =  new ButtonReader(gamepadEx2, GamepadKeys.Button.Y);
        inIn =  new ButtonReader(gamepadEx2, GamepadKeys.Button.A);
        inStop =  new ButtonReader(gamepadEx2, GamepadKeys.Button.B);
        flickRead =  new ButtonReader(gamepadEx2, GamepadKeys.Button.X);
        shooterStop = new ButtonReader(gamepadEx2, GamepadKeys.Button.DPAD_UP);



        //servo logic declreations
        arm1Down = 1;
        arm2Down = 1;
        grip1Down = 1;
        grip2Down = 1;

        waitForStart();
        gyro.init();
        while(opModeIsActive()) {
            //Flipper Servos
            if (flickRead.wasJustReleased()) {
                flipperServo.setPosition(0);
                sleep(1000); //increase sleep?
                flipperServo.setPosition(0.09);
            }

            //arm1 - Working!
            if(lArm.wasJustReleased()) {
                //logic for if arm down go up if arm up go down
                if(arm1Down == 1) {
                    arm1.setPosition(0.6);
                    arm1Down = 0;
                } else if (arm1Down == 0)  {
                    arm1.setPosition(0.46);
                    arm1Down = 1;
                }
            }

            //arm2 - Working
            if(rArm.wasJustReleased()) {
                //logic for if arm down go up if arm up go down
                if(arm2Down == 1) {
                    arm2.setPosition(0.42);
                    arm2Down = 0;
                } else if (arm2Down == 0)  {
                    arm2.setPosition(0.56);
                    arm2Down = 1;
                }
            }

            //gripper2 - Wire issue?
            if(rGrip.wasJustReleased()) {
                //logic for if gripper down go up if gripper up go down
                if(grip2Down == 1) {
                    gripper2.setPosition(0.5);
                    grip2Down = 0;
                } else if (grip2Down == 0)  {
                    gripper2.setPosition(0.14);
                    grip2Down = 1;
                }
            }

            //gripper1 - Working!
            if(lGrip.wasJustReleased()) {
                //logic for if gripper down go up if gripper up go down
                if(grip1Down == 1) {
                    gripper1.setPosition(0.39);
                    grip1Down = 0;
                } else if (grip1Down == 0)  {
                    gripper1.setPosition(0);
                    grip1Down = 1;
                }
            }

            if (shooterRev.isDown()) {
                shooterRight.setPower(1);
                shooterLeft.setPower(1);
            }
            if (shooterStop.wasJustReleased()) {
                shooterRight.setPower(0);
                shooterLeft.setPower(0);
            }

            if (inIn.wasJustReleased()) {
                intake.set(-1);
            }
            if(inStop.wasJustReleased()) {
                intake.set(0);

            }
            if(inOut.wasJustReleased()) {
                intake.set(0);

            }

            //tele-op drive
            strafeSpeed = gamepadEx1.getLeftX();
            forwardSpeed = gamepadEx1.getLeftY();
            turnSpeed = gamepadEx1.getRightX();
            angle = gyro.getAbsoluteHeading();

            m_drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angle);
        }
    }
}