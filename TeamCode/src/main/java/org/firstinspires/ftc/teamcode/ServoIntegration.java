package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Servo Integration", group="Tests")

public class ServoIntegration extends LinearOpMode {

    private ServoEx flipperServo, arm1, arm2, gripper1, gripper2;
    private int arm1Down, arm2Down, grip1Down, grip2Down;


    @Override
    public void runOpMode() throws InterruptedException {

        flipperServo = new SimpleServo(hardwareMap, "leftFlipper");
        arm1 = new SimpleServo(hardwareMap, "liftServo");
        arm2 = new SimpleServo(hardwareMap, "liftServo2");
        gripper1 = new SimpleServo(hardwareMap, "grabServo");
        gripper2 = new SimpleServo(hardwareMap, "grabServo2");

        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

        arm1Down = 1;
        arm2Down = 1;
        grip1Down = 1;
        grip2Down = 2;


        waitForStart();
        while (opModeIsActive()) {
            //Flipper Servos
            if (gamepadEx2.getButton(GamepadKeys.Button.X)) {
                flipperServo.setPosition(0);
                sleep(1000); //increase sleep?
                flipperServo.setPosition(0.09);
            }

            //arm1 - Working!
            if(gamepadEx1.getButton(GamepadKeys.Button.A)) {
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
            if(gamepadEx1.getButton(GamepadKeys.Button.B)) {
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
            if(gamepadEx1.getButton(GamepadKeys.Button.X)) {
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
            if(gamepadEx1.getButton(GamepadKeys.Button.Y)) {
                //logic for if gripper down go up if gripper up go down
                if(grip1Down == 1) {
                    gripper1.setPosition(0.39);
                    grip1Down = 0;
                } else if (grip1Down == 0)  {
                    gripper1.setPosition(0);
                    grip1Down = 1;
                }
            }


        }

    }
}
