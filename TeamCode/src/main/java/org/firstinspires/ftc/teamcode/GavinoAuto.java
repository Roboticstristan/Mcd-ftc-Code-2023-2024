package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="El_Salvador", group="Auto2022")
public class GavinoAuto extends LinearOpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DistanceSensor sensorRange1;
    private DistanceSensor sensorRange2;
    //private DcMotor linearSlide = null;
    //declare color sensor
    //private NormalizedColorSensor colorSensor = null;
    //private DcMotor colorSensor = null;
    //public Servo claw = null;

    public void setDirectionForward() {
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setDirectionBackward() {
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void STOP_AND_RESET_ENCODERS_ALL_WHEELS() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void SET_POWER_ALL_WHEELS(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    public void DRIVE_DISTANCE_FORWARD(float distance_in_in, double power) {
        float ticksPerInch = 59.6031746032f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        // 751.8 ticks per rotation

        if (power > 0) {
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        }


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        SET_POWER_ALL_WHEELS(power);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are running to position");
        telemetry.update();
        while (frontLeft.getCurrentPosition() <= (frontLeft.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("motors", "> Are strafing to position");
            telemetry.addData("ticks", ">" + frontLeft.getCurrentPosition() + " need to get to " + frontLeft.getTargetPosition());
            telemetry.update();
        }
    }

    void TURN(int power, float distance_in_in) {
        float ticksPerInch = 59.6031746032f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        // 1120 Ticks per revolution

        if (power > 0) {
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        //backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        //frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        //frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        //backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        SET_POWER_ALL_WHEELS(0.6);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are running to position");
        telemetry.update();
        while (frontLeft.getCurrentPosition() <= (frontLeft.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("motors", "> Are strafing to position");
            telemetry.addData("ticks", ">" + frontLeft.getCurrentPosition() + " need to get to " + frontLeft.getTargetPosition());
            telemetry.update();
        }

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();

        telemetry.addData("motors", "> have run to position");
        telemetry.update();
    }

    public void DRIVE_DISTANCE_RIGHT(float distance_in_in) {
        float ticksPerInch = 120.737061895f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        // 1120 Ticks per revolution

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        SET_POWER_ALL_WHEELS(1);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are running to position");
        telemetry.update();
        while (frontLeft.isBusy()) {
            //Wait until job is finished
        }

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();

        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("motors", "> have run to position");
        telemetry.update();
    }

    public void DRIVE_DISTANCE_LEFT(float distance_in_in) {
        float ticksPerInch = 120.737061895f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        // 1120 Ticks per revolution


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        STOP_AND_RESET_ENCODERS_ALL_WHEELS();
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        SET_POWER_ALL_WHEELS(1);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("motors", "> Are strafing to position");
        telemetry.update();
        while (frontLeft.getCurrentPosition() <= (frontLeft.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("motors", "> Are strafing to position");
            telemetry.addData("ticks", ">" + frontLeft.getCurrentPosition());
            telemetry.update();
        }

    }

    /*
    public void LINEAR_SLIDE_DRIVE(float distance_in_in, double power) {
        float ticksPerInch = 450.149432158f;
        float f_ticks = ticksPerInch * distance_in_in;
        int ticks = Math.round(f_ticks);
        //753.1 ticks per revolution
        //1.673 in per revolution (circumference)
        //450.149432158 ticks per in
        if (power > 0) {
            //go up
            linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            //go down
            linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setPower(power);
        linearSlide.setTargetPosition(ticks);
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("slide", "> is running to position");
        telemetry.update();
        while (linearSlide.getCurrentPosition() <= (linearSlide.getTargetPosition() - 50)) {
            //Wait until job is finished
            telemetry.addData("slide", "> is strafing to position");
            telemetry.addData("ticks", ">" + linearSlide.getCurrentPosition() + " need to get to " + linearSlide.getTargetPosition());
            telemetry.update();
        }
    }
     */

  //  public void getData() {
   //     sensorRange1.getDistance(DistanceUnit.CM);
      //  sensorRange2.getDistance(DistanceUnit.CM);
  //  }

    public void SIX_EYES() {
        if (sensorRange1.getDistance(DistanceUnit.CM) > sensorRange2.getDistance(DistanceUnit.CM) && (sensorRange1.getDistance(DistanceUnit.CM) < 20 || sensorRange2.getDistance(DistanceUnit.CM) < 20)) {
            telemetry.addData("Block Placement:", "Left");
            TURN(1,90f);
            TURN(1,-90f);
            //DRIVE_DISTANCE_LEFT(10f);
        } else if (sensorRange1.getDistance(DistanceUnit.CM) < sensorRange2.getDistance(DistanceUnit.CM) && (sensorRange1.getDistance(DistanceUnit.CM) < 20 || sensorRange2.getDistance(DistanceUnit.CM) < 20)) {
            telemetry.addData("Block Placement:", "Right");
            TURN(1,90f);
            TURN(1,-90f);
            //DRIVE_DISTANCE_RIGHT(10f);
        } else {
            telemetry.addData("Block Placement:", "Forward");
            DRIVE_DISTANCE_FORWARD(-10f,0.8);
        }
    }



      /*   if (duncan.SIX_EYES() == 1) {
                telemetry.addData("Block Placement:", "Left");
                //turn left 90
                // Place pixel
                // turn right 90
            } else if (duncan.SIX_EYES() == 2) {
                telemetry.addData("Block Placement:", "Right");
                //turn right 90
                //place pixel
                //turn left 90
            } else {
                telemetry.addData("Block Placement:", "Forward");
                //drive forward
                //place pixel
            }
        }*/



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontRight = hardwareMap.get(DcMotor.class, "rightFront_Drive");
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront_Drive");
        backRight = hardwareMap.get(DcMotor.class, "rightBack_Drive");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack_Drive");
        sensorRange1 = hardwareMap.get(DistanceSensor.class, "left_Distance");
        sensorRange2 = hardwareMap.get(DistanceSensor.class, "right_Distance");
        //linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_colorb");
        //claw = hardwareMap.get(Servo.class, "claw");




        /*if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }
        claw.setPosition(0);
        waitForStart();
        runtime.reset();
         */
        waitForStart();
        runtime.reset();


        //run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            TURN(1,90f);
        }
            /*
            DRIVE_DISTANCE_FORWARD(-30f, 0.8);
            sleep(1873);
            SIX_EYES();
        }
        sleep(2000);

        DRIVE_DISTANCE_FORWARD(18f, 0.8);
        DRIVE_DISTANCE_RIGHT(72f);
        DRIVE_DISTANCE_FORWARD(24f, 0.8);
        TURN(1, -30f);
        DRIVE_DISTANCE_FORWARD(8f,1);

      //  TURN(12,1);
       // DRIVE_DISTANCE_RIGHT();
*/




    }
}

