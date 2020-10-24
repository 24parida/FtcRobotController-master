package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

class IntakeTest extends LinearOpMode {

    private MotorEx intake;


    @Override
    public void runOpMode() throws InterruptedException {
        //motor deceleration
        intake = new MotorEx(hardwareMap, "intake");
        intake.setRunMode(Motor.RunMode.RawPower);
        intake.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);


        while(opModeIsActive()) {
            //in
            if (gamepadEx2.getButton(GamepadKeys.Button.A)) {
                intake.set(1);
            }
            //out
            if (gamepadEx2.getButton(GamepadKeys.Button.B)) {
                intake.set(-1);
            }
            //stop
            if (gamepadEx2.getButton(GamepadKeys.Button.Y)) {
                intake.set(0);
            }
        }
        }
}
