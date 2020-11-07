package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.lang.annotation.Documented;

//shooter test is a class to run the shooter motor with encoders in order to keep velocity constant
@TeleOp(name="Shooter Test", group="Tests")
public class ShooterTest extends LinearOpMode{


    private DcMotor shooterRight = null;
    private DcMotor shooterLeft = null;

    @Override
    public void runOpMode() throws InterruptedException {
        shooterRight =  hardwareMap.get(DcMotor.class, "shootingMotorRight");
        shooterLeft = hardwareMap.get(DcMotor.class, "shootingMotorLeft");

        //RUN_USING_ENCODER
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepadEx2.getButton(GamepadKeys.Button.A)) {
                shooterRight.setPower(1);
                shooterLeft.setPower(0);
            }
            if (gamepadEx2.getButton(GamepadKeys.Button.B)) {
                shooterRight.setPower(0);
                shooterLeft.setPower(0);
            }
        }
    }
}