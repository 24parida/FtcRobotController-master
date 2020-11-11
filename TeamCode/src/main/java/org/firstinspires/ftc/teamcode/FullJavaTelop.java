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
import org.firstinspires.ftc.teamcode.Robot;

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
    public enum TeleOP {
        INTAKE,
        GOTOPOS,
        SHOOT
    };


    @Override
    public void runOpMode() throws InterruptedException {

        Robot GEarheads = new Robot(hardwareMap);
        TeleOP teleop = TeleOP.INTAKE;
        waitForStart();
        GEarheads.gyroRest();
        while(opModeIsActive()) {
            lArm.readValue();
            rArm.readValue();
            lGrip.readValue();
            rGrip.readValue();
            gTPread.readValue();

            shooterRev.readValue();
            inIn.readValue();
            inOut.readValue();
            inStop.readValue();
            flickRead.readValue();
            shooterStop.readValue();

            switch (teleop) {
                case TeleOP.INTAKE:

            }


            //tele-op drive
           GEarheads.drive();
        }
    }
}