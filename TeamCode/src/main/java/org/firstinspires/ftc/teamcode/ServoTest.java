package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

class ServoTest extends LinearOpMode {

    private ServoEx flipperRight, flipperLeft, armLeft, armRight, gripperLeft, gripperRight;
    private GamepadEx gamepadEx;


    @Override
    public void runOpMode() throws InterruptedException {
        //Indexer servos
        flipperRight = new SimpleServo(hardwareMap, "flipperRight");
        flipperLeft = new SimpleServo(hardwareMap, "flipperLeft");
        //armservos
        armLeft = new SimpleServo(hardwareMap, "armLeft");
        armRight = new SimpleServo(hardwareMap, "armRight");
        gripperLeft = new SimpleServo(hardwareMap, "gripperLeft");
        gripperRight = new SimpleServo(hardwareMap, "gripperRight");

        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

        while(opModeIsActive()) {

            //Arms
            if (gamepadEx1.getButton(GamepadKeys.Button.A)) {
                armLeft.rotate(0);
                armRight.rotate(0);

            }
            //Grippers
            if (gamepadEx1.getButton(GamepadKeys.Button.B)) {
            }

            //Flicker Servos
            if (gamepadEx2.getButton(GamepadKeys.Button.X)) {
            }

        }
    }
}