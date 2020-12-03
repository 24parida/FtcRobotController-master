package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import static java.lang.Math.*;
@TeleOp
public class joshcode extends LinearOpMode {
    Servo flipper;
    Servo armLeft;
    Servo clawLeft;
    Servo armRight;
    Servo clawRight;
    DcMotorEx shootLeft;
    DcMotorEx shootRight;
    DcMotorEx intake;
    DcMotorEx fl;
    DcMotorEx bl;
    DcMotorEx fr;
    DcMotorEx br;
    BNO055IMU gyro;
    double robotAngle;
    double joystickAngle;
    double joystickMag;
    double turn;
    int leftArmState = 0;
    int rightArmState = 0;
    boolean leftTriggerUp = true;
    boolean rightTriggerUp = true;
    @Override
    public void runOpMode() {
        flipper = hardwareMap.get(Servo.class, "flipper");
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        shootLeft = hardwareMap.get(DcMotorEx.class, "shootLeft");
        shootRight = hardwareMap.get(DcMotorEx.class, "shootRight");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        fl = hardwareMap.get(DcMotorEx.class, "motor_fl");
        bl = hardwareMap.get(DcMotorEx.class, "motor_rl");
        fr = hardwareMap.get(DcMotorEx.class, "motor_fr");
        br = hardwareMap.get(DcMotorEx.class, "motor_rr");
        gyro = hardwareMap.get(BNO055IMU.class, "gyro");
        shootLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shootRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.mode = BNO055IMU.SensorMode.IMU;
        params.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        gyro.initialize(params);
        while(!gyro.isGyroCalibrated()) {}
        waitForStart();
        while (opModeIsActive()) {
            robotAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;
            joystickAngle = atan2(-gamepad1.left_stick_x, -gamepad1.left_stick_y);
            joystickMag = pow(gamepad1.left_stick_x, 2) + pow(gamepad1.left_stick_y, 2);
            turn = gamepad1.right_stick_x;
            fl.setPower(Range.clip(joystickMag * sin(PI / 4 - joystickAngle + robotAngle) + turn, -1, 1));
            bl.setPower(Range.clip(joystickMag * sin(PI / 4 + joystickAngle - robotAngle) + turn, -1, 1));
            fr.setPower(Range.clip(joystickMag * sin(PI / 4 + joystickAngle - robotAngle) - turn, -1, 1));
            br.setPower(Range.clip(joystickMag * sin(PI / 4 - joystickAngle + robotAngle) - turn, -1, 1));
            if (gamepad1.a) {
                intake.setPower(0);
                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);
                ElapsedTime time = new ElapsedTime();
                shootRight.setPower(-0.5);
                shootLeft.setPower(-1);
                while (time.milliseconds() < 2000) {}
                for (int i = 0; i < 3; i++) {
                    time.reset();
                    flipper.setPosition(0.270);
                    while (time.milliseconds() < 300) {}
                    time.reset();
                    flipper.setPosition(0.450);
                    if (i != 2) {
                        while (time.milliseconds() < 400) {}
                    }
                }
                shootRight.setPower(0);
                shootLeft.setPower(0);
            } else {
                flipper.setPosition(0.450);
            }
            if (gamepad1.x) {
                intake.setPower(-1);
            } else if (gamepad1.y) {
                intake.setPower(0);
            } else {
                intake.setPower(1);
            }
            if (gamepad1.left_trigger > 0.5 && leftTriggerUp) {
                leftArmState = (leftArmState + 1) % 4;
                leftTriggerUp = false;
            } else if (gamepad1.left_trigger <= 0.5) {
                leftTriggerUp = true;
            }
            switch (leftArmState) {
                case 0:
                    armLeft.setPosition(0.560);
                    clawLeft.setPosition(0.550);
                    break;
                case 1:
                    armLeft.setPosition(0.410);
                    clawLeft.setPosition(0.550);
                    break;
                case 2:
                    armLeft.setPosition(0.410);
                    clawLeft.setPosition(0.220);
                    break;
                case 3:
                    armLeft.setPosition(0.560);
                    clawLeft.setPosition(0.220);
                    break;
            }
            if (gamepad1.right_trigger > 0.5 && rightTriggerUp) {
                rightArmState = (rightArmState + 1) % 4;
                rightTriggerUp = false;
            } else if (gamepad1.right_trigger <= 0.5) {
                rightTriggerUp = true;
            }
            switch (rightArmState) {
                case 0:
                    armRight.setPosition(0.450);
                    clawRight.setPosition(0);
                    break;
                case 1:
                    armRight.setPosition(0.600);
                    clawRight.setPosition(0);
                    break;
                case 2:
                    armRight.setPosition(0.600);
                    clawRight.setPosition(0.390);
                    break;
                case 3:
                    armRight.setPosition(0.450);
                    clawRight.setPosition(0.390);
                    break;
            }
        }
    }
}

