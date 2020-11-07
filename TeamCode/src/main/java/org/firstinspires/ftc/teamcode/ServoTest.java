package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Servo Test", group="Tests")

public class ServoTest extends LinearOpMode {

    private ServoEx flipperServo, arm;

    @Override
    public void runOpMode() throws InterruptedException {
        //Indexer servos
        flipperServo = new SimpleServo(hardwareMap, "liftServo");
        arm = new SimpleServo(hardwareMap, "grabServo");

        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
        double pos = 0.0;
        double pos1 = 0.0;


        waitForStart();

        while(opModeIsActive()) {
            if (gamepadEx1.getButton(GamepadKeys.Button.X)) {
                flipperServo.setPosition(0);
                telemetry.addData("servo pos: ", 0);
                telemetry.update();

            }
            if (gamepadEx1.getButton(GamepadKeys.Button.A)) {
                pos=pos+0.01;
                flipperServo.setPosition(pos);
                telemetry.addData("servo pos: ", pos);
                telemetry.update();

            }

            if (gamepadEx1.getButton(GamepadKeys.Button.B)) {
                pos = pos - 0.01;
                flipperServo.setPosition(pos);

                telemetry.addData("servo pos: ", pos);
                telemetry.update();
            }
            if (gamepadEx1.getButton(GamepadKeys.Button.Y)) {
                flipperServo.setPosition(0.09);
                flipperServo.setPosition(0);
            }

            if (gamepadEx1.getButton(GamepadKeys.Button.X)) {
                flipperServo.setPosition(0);
                telemetry.addData("servo pos: ", 0);
                telemetry.update();

            }




            if (gamepadEx2.getButton(GamepadKeys.Button.A)) {
                pos1 =pos1+0.01;
                arm.setPosition(pos1);
                telemetry.addData("servo pos1: ", pos1);
                telemetry.update();
            }
            if (gamepadEx2.getButton(GamepadKeys.Button.B)) {
                pos1 = pos1 - 0.01;
                arm.setPosition(pos1);

                telemetry.addData("servo pos1: ", pos1);
                telemetry.update();
            }
            if (gamepadEx2.getButton(GamepadKeys.Button.Y)) {
                arm.setPosition(0.09);
                arm.setPosition(0);
            }
            if (gamepadEx2.getButton(GamepadKeys.Button.X)) {
                arm.setPosition(0);
                telemetry.addData("servo pos1: ", 0);
                telemetry.update();
            }

        }

        }
    }
