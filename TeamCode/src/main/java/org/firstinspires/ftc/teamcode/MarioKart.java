package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Star Cup", group="Linear Opmode")
public class MarioKart extends LinearOpMode {
    private DcMotor backLeft = null;
    private DcMotor  backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor llSlide = null;
    private DcMotor rlSlide = null;
    private DcMotor intake = null;
    public Servo claw = null;
    public Servo arm = null;


    static final double MAX_POS = 0.5;
    static final double MIN_POS = 0;

    double position = (MAX_POS - MIN_POS) / 2;
    private int count;


    //variable that holds the amount of time is running
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontRight = hardwareMap.get(DcMotor.class, "rightFront_Drive");
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront_Drive");
        backRight = hardwareMap.get(DcMotor.class, "rightBack_Drive");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack_Drive");
        llSlide = hardwareMap.get(DcMotor.class, "leftlinear_slide");
        rlSlide = hardwareMap.get(DcMotor.class, "rightlinear_slide");
        intake = hardwareMap.get(DcMotor.class, "intake");
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(Servo.class, "arm");

        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //  run until the end of the match (driver presses STOP)
        while (opModeIsActive()){
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
            double max;
            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = -1*(axial + lateral + yaw);
            double rightFrontPower = -1*(axial - lateral - yaw);
            double leftBackPower   = -1*(axial - lateral + yaw);
            double rightBackPower  = -1*(axial + lateral - yaw);

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }


            // Send calculated power to wheels
            frontLeft.setPower(leftFrontPower);
            frontRight.setPower(rightFrontPower);
            backLeft.setPower(leftBackPower);
            backRight.setPower(rightBackPower);
            // adds precesion mode when bumper pressed


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.update();

            if(gamepad1.left_trigger > 0)
            {
                llSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                llSlide.setPower(0.6);
                rlSlide.setPower(0.6);
            }else if(gamepad1.right_trigger > 0)
            {
                llSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                rlSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                llSlide.setPower(0.9);
                rlSlide.setPower(0.9);
            } else {
                llSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                rlSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                llSlide.setPower(0.09);
                rlSlide.setPower(0.09);
            }

            if(gamepad1.x){
                count++;
                if (count % 2 != 0) {
                    intake.setDirection(DcMotorSimple.Direction.FORWARD);
                    intake.setPower(1);
                    //motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                    //motor2.setPower(1);
                } else {
                    intake.setPower(0);
                    //motor2.setPower(0);
                }
            }

//Tristan is smelly
        }

    }
}

