package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Servo Test", group="Tests")

public class ServoTest extends LinearOpMode {

    private ServoEx flipperServo;
    private ButtonReader flickRead, flickRead1, flickRead2, servoInc, servoDec;
    private int sleep;
    private double pos;

    @Override
    public void runOpMode() throws InterruptedException {
        //Indexer servo
        flipperServo = new SimpleServo(hardwareMap, "leftFlipper");

        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

        flickRead =  new ButtonReader(gamepadEx1, GamepadKeys.Button.X);
        flickRead1 =  new ButtonReader(gamepadEx1, GamepadKeys.Button.Y);
        flickRead2 =  new ButtonReader(gamepadEx1, GamepadKeys.Button.B);
        servoDec = new ButtonReader(gamepadEx2, GamepadKeys.Button.A);
        servoInc = new ButtonReader(gamepadEx2, GamepadKeys.Button.X);


        sleep = 1000;
        pos = 0.4;
        waitForStart();
        while(opModeIsActive()) {
            flickRead.readValue();
            flickRead1.readValue();
            flickRead2.readValue();
            servoDec.readValue();
            servoInc.readValue();

            if(servoInc.wasJustReleased()) {
                pos = pos + 0.025;
                telemetry.addData("pos: ", pos);
                telemetry.update();
            }
            if(servoDec.wasJustReleased()) {
                pos = pos - 0.025;
                telemetry.addData("pos: ", pos);
                telemetry.update();
            }

            if(flickRead2.wasJustReleased()) {
                sleep = sleep + 100;
                telemetry.addData("sleep: ", sleep);
                telemetry.update();
            }
             if(flickRead1.wasJustReleased()) {
                 sleep = sleep - 100;
                 telemetry.addData("sleep: ", sleep);
                 telemetry.update();
             }

            if(flickRead.wasJustReleased()){
                flipperServo.setPosition(0.224);
                sleep(sleep);
                flipperServo.setPosition(pos);
            }
        }
    }
}
