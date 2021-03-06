package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

// Field Centric Drive using odometry to get angle
@Disabled
class GoToPos extends LinearOpMode {

    private DcMotorEx frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        //drive motors
        frontLeft = hardwareMap.get(DcMotorEx.class, "motor_fl");
        frontRight = hardwareMap.get(DcMotorEx.class, "motor_fr");
        backLeft = hardwareMap.get(DcMotorEx.class, "motor_rl");
        backRight = hardwareMap.get(DcMotorEx.class, "motor_rr");


        //just some object deceleration
        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(40, 40, Math.toRadians(90)))
                .build();

        waitForStart();


        while (opModeIsActive()) {if (gamepadEx1.getButton(GamepadKeys.Button.X)) {drive.followTrajectory(myTrajectory);}}
    }
}