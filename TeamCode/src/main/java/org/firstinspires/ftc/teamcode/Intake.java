package org.firstinspires.ftc.teamcode;

import android.net.wifi.p2p.WifiP2pConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="intakeTest", group="Linear Opmode")
public class Intake extends LinearOpMode {
    private DcMotor motor1 = null;
    //private DcMotor motor2 = null;
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        int count = 0;
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            motor1 = hardwareMap.get(DcMotor.class, "mouthI");
            //motor2 = hardwareMap.get(DcMotor.class, "bodyI");
            motor1.setDirection(DcMotorSimple.Direction.FORWARD);
            if (gamepad1.x) {
                count++;
                if (count % 2 != 0) {
                    motor1.setPower(1);
                    //motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                    //motor2.setPower(1);
                } else {
                    motor1.setPower(0);
                }

            }

        }
    }
}
