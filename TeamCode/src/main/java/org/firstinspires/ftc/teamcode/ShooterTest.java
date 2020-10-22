package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

class ShooterTest extends LinearOpMode {

    private MotorEx shooterRight, shooterLeft;




    @Override
    public void runOpMode() throws InterruptedException {
        shooterRight = new MotorEx(hardwareMap, "shooter_right");
        shooterLeft = new MotorEx(hardwareMap, "shooter_left");

        shooterLeft.setRunMode(Motor.RunMode.VelocityControl);
        shooterRight.setRunMode(Motor.RunMode.VelocityControl);

        shooterLeft.setVelocity(7 * 6000 * 0.9 * 60);
        shooterRight.setVelocity(0);

        while(opModeIsActive()) {
        }
    }
}
