 package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


 @TeleOp(name="FSM test", group="Tests")
public class FullJavaTelop extends LinearOpMode {

    private GamepadEx gamepadEx1, gamepadEx2;
    private ButtonReader lArm, rArm,gTPread;
    private ButtonReader inOut, flickRead, inIn, inStop;
    int rightArmState = 0;
    int leftArmState = 0;
    public enum TeleOP {
        INTAKE,
        GOTOPOS,
        SHOOT
    };


    @Override
    public void runOpMode() throws InterruptedException {

        Robot GEarheads = new Robot(hardwareMap);
        TeleOP teleop = TeleOP.INTAKE;

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        //gm1
        lArm = new ButtonReader(gamepadEx1, GamepadKeys.Button.X);
        rArm = new ButtonReader(gamepadEx1, GamepadKeys.Button.A);
        gTPread = new ButtonReader(gamepadEx1, GamepadKeys.Button.DPAD_UP);

        //gm2
        inOut =  new ButtonReader(gamepadEx2, GamepadKeys.Button.Y);
        inIn = new ButtonReader(gamepadEx2, GamepadKeys.Button.A);
        flickRead = new ButtonReader(gamepadEx2, GamepadKeys.Button.X);
        inStop = new ButtonReader(gamepadEx2, GamepadKeys.Button.B);

        waitForStart();
        GEarheads.gyroRest();
        while(opModeIsActive()) {
            lArm.readValue();
            rArm.readValue();
            gTPread.readValue();
            inOut.readValue();
            flickRead.readValue();

            switch (teleop) {
                case INTAKE:
                    telemetry.addData("state: ", "INTAKE");
                    telemetry.update();
                    GEarheads.intakeIN();

                    while(inOut.isDown()) {
                        GEarheads.intakeOUT();
                    }

                    while(inStop.isDown()){
                        GEarheads.intakeSTOP();
                    }

                    if (gTPread.wasJustReleased()) {
                        teleop = TeleOP.GOTOPOS;
                    }
                case GOTOPOS:
                    telemetry.addData("state: ", "GoToPos");
                    telemetry.update();
                    GEarheads.intakeSTOP();
                    GEarheads.gTP();
                    teleop = TeleOP.SHOOT;
                case  SHOOT:
                    telemetry.addData("state: ", "Shoot");
                    telemetry.update();
                    if (flickRead.wasJustReleased()) {
                        GEarheads.flip3();
                        teleop = TeleOP.INTAKE;
                    }
            }

            if (inIn.wasJustReleased() && teleop != TeleOP.INTAKE) {
                teleop = TeleOP.INTAKE;
            }
            if (rArm.wasJustReleased()) {
                rightArmState = (rightArmState + 1) % 4;
            }
            if (lArm.wasJustReleased()) {
                leftArmState = (leftArmState + 1) % 4;
            }
            switch (leftArmState) {
                case 0:
                    GEarheads.leftarm1();
                    break;
                case 1:
                    GEarheads.leftarm2();
                    break;
                case 2:
                    GEarheads.leftarm3();
                    break;
                case 3:
                    GEarheads.leftarm4();
                    break;
            }

            switch (rightArmState) {
                case 0:
                    GEarheads.rightarm1();;
                    break;
                case 1:
                    GEarheads.rightarm2();;
                    break;
                case 2:
                    GEarheads.rightarm3();
                    break;
                case 3:
                    GEarheads.rightarm4();
                    break;
            }

            //tele-op drive
           GEarheads.drive(gamepadEx1);
        }
    }
}