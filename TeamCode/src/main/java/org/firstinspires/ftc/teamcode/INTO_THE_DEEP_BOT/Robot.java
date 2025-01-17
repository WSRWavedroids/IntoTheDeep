package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Objects;

public class Robot {

    public DcMotorEx frontLeftDrive;
    public DcMotorEx frontRightDrive;
    public DcMotorEx backLeftDrive;
    public DcMotorEx backRightDrive;
    public DcMotor liftyL;
    public DcMotor liftyR;



    public CRServo leftIntake;
    public CRServo rightIntake;

    public Servo intakeFlipper;

    public Servo leftFlippyOutakeServo;
    public Servo rightFlippyOutakeServo;
    public Servo grabbyOutakeServoL;
    public Servo grabbyOutakeServoR;

    public Servo leftSlide;
    public Servo rightSlide;

    public boolean teleopEncoderMode;

    public boolean teleopPowerMode;

    //public DistanceSensor distanceSensor;

    SparkFunOTOS myOtos;

    //public WebcamName CamCam;

    public Telemetry telemetry;
    //public BNO055IMU imu;

    //init and declare war
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public static double parkingZone;
    public String startingPosition;
    public String controlMode = "Robot Centric";// Robot Centric
    public String intakeFlipperPos ="UP";
    public IMU.Parameters imuParameters;

    //Initialize motors and servos
    public Robot(HardwareMap hardwareMap, Telemetry telemetry, OpMode opmode){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opmode = opmode;

        // This section turns the names of the pieces of hardware into variables that we can program with.
        // Make sure that the device name is the exact same thing you typed in on the configuration on the driver hub.
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");
        liftyL = hardwareMap.get(DcMotor.class, "liftyL");
        liftyR = hardwareMap.get(DcMotor.class, "liftyR");
        leftIntake = hardwareMap.get(CRServo.class, "leftIntake");
        rightIntake = hardwareMap.get(CRServo.class, "rightIntake");
        intakeFlipper = hardwareMap.get(Servo.class, "flipperServo");
        leftFlippyOutakeServo = hardwareMap.get(Servo.class, "leftFlippyOutakeServo");
        rightFlippyOutakeServo = hardwareMap.get(Servo.class, "rightFlippyOutakeServo");
        grabbyOutakeServoL = hardwareMap.get(Servo.class, "grabbyOutakeServoL");
        grabbyOutakeServoR = hardwareMap.get(Servo.class, "grabbyOutakeServoR");
        leftSlide = hardwareMap.get(Servo.class, "leftSlide");
        rightSlide = hardwareMap.get(Servo.class, "rightSlide");
        myOtos = hardwareMap.get(SparkFunOTOS.class, "sparkFunSparkJoy");


        imuParameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
                )
        );

        // This section sets the direction of all of the motors. Depending on the motor, this may change later in the program.
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        liftyL.setDirection(DcMotor.Direction.FORWARD);//Might need inverted
        liftyR.setDirection(DcMotorSimple.Direction.FORWARD);//Might need inverted

        // This tells the motors to chill when we're not powering them.
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftyL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftyR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //This is new..
        telemetry.addData("Status", "Initialized");

    }


    public boolean isWheelsBusy(){
        return backLeftDrive.isBusy() || frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backRightDrive.isBusy();
    }

    public void stopAllMotors() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void setTargets(String direction, int ticks) {

        //This is all inverted (big sigh)

        if (Objects.equals(direction, "Right")){
            frontLeftDrive.setTargetPosition(-ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Left"){
            frontLeftDrive.setTargetPosition(ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Forward"){
            frontLeftDrive.setTargetPosition(-ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Backward") {
            frontLeftDrive.setTargetPosition(ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Turn Right") {
            frontLeftDrive.setTargetPosition(-ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Turn Left") {
            frontLeftDrive.setTargetPosition(ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks + backRightDrive.getCurrentPosition());
        }
        else if (direction == "Diagonal Right") {
            frontLeftDrive.setTargetPosition(-ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setTargetPosition(-ticks + backRightDrive.getCurrentPosition());
        }
        else if (direction == "Diagonal Left") {
            frontLeftDrive.setPower(0);
            frontRightDrive.setTargetPosition(-ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setPower(0);
        }




    }

    public void positionRunningMode(){

        frontLeftDrive.setTargetPositionTolerance(50);
        frontRightDrive.setTargetPositionTolerance(50);
        backLeftDrive.setTargetPositionTolerance(50);
        backRightDrive.setTargetPositionTolerance(50);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void powerRunningMode()
    {
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void powerSet(double speed) {
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

    }

    public void slidesIn()
    {
        leftSlide.setPosition(1); //guess value... DO NOT TRUST
        rightSlide.setPosition(0); //guess value... DO NOT TRUST
        //intakePosition("UP");
    }

    public void collapseExpansion()
    {
        tempOutakePos("DOWN");
        //Moves and waits until the vert slides are at the bottom before moving on
        while (liftyL.getCurrentPosition() < -5 || liftyL.getCurrentPosition() > 5)
        {
            liftyL.setPower(1);
            liftyR.setPower(1);
            liftyL.setTargetPosition(0);
            liftyR.setTargetPosition(0);
            liftyL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftyR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        while(leftFlippyOutakeServo.getPosition() > 0.1)
        {
            tempOutakePos("DOWN");
            tellMotorOutput(); //Just a stalling method... had to have something here
        }

        intakePosition("UP");

        slidesIn(); //move the intake up and the horiz slides in
        while(leftSlide.getPosition() < 1)
        {
            slidesIn();
            tellMotorOutput();
        }
    }

    public void intake_spin (double direction){
        //servos spin in thingy
        if(direction > 0) // >0 is out
        {
            leftIntake.setDirection(CRServo.Direction.FORWARD);
            rightIntake.setDirection(CRServo.Direction.REVERSE);//direction is forward... set speed to that val
            leftIntake.setPower(Math.abs(direction));
            rightIntake.setPower(Math.abs(direction));
        }
        else // <0 is in
        {
            leftIntake.setDirection(CRServo.Direction.REVERSE);
            rightIntake.setDirection(CRServo.Direction.FORWARD);//direction is forward... set speed to that val
            leftIntake.setPower(Math.abs(direction));
            rightIntake.setPower(Math.abs(direction));
        }

    }
    public boolean canWiggle = true;
    public void intakePosition (String intakeFlipperPos)
    {
        if(intakeFlipperPos == "IN")
        {
            intakeFlipper.setPosition(1);//guess position
            canWiggle = false;
        }
        else if(intakeFlipperPos == "DOWN")
        {
            intakeFlipper.setPosition(.15);
            canWiggle = true;
        }
        else if(intakeFlipperPos == "UP")
        {
            intakeFlipper.setPosition(.75);//testing value DO NOT TRUST
            canWiggle = true;
        }

    }

    public void outakeclawOpenClose(String state)
    {
        if(state == "OPEN")
        {
           grabbyOutakeServoL.setPosition(.5);
            grabbyOutakeServoR.setPosition(.4);
        }
        else if (state == "CLOSED")
        {
            grabbyOutakeServoL.setPosition(1);
            grabbyOutakeServoR.setPosition(0);
        }
    }

    public void tempOutakePos(String pos)// DO NOT TRUST THESE VALS ARE PLACEHOLDERS
    {
        if (pos == "DOWN")
        {
            leftFlippyOutakeServo.setPosition(0);
            rightFlippyOutakeServo.setPosition(0);
        }
        if (pos == "UP")
        {
            leftFlippyOutakeServo.setPosition(.8);
            rightFlippyOutakeServo.setPosition(.8);
        }
        if (pos == "MOREUP")
        {
            leftFlippyOutakeServo.setPosition(.8);
            rightFlippyOutakeServo.setPosition(.8);
        }


    }

    public void TransferSequence()
    {
        //intakePosition("UP");
        tempOutakePos("DOWN");

        //Moves and waits until the vert slides are at the bottom before moving on
        while (liftyL.getCurrentPosition() < -5 || liftyL.getCurrentPosition() > 5)
        {
            liftyL.setPower(1);
            liftyR.setPower(1);
            liftyL.setTargetPosition(0);
            liftyR.setTargetPosition(0);
            liftyL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftyR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }


        while(leftFlippyOutakeServo.getPosition() > 0.1)
        {
            tempOutakePos("DOWN");
            tellMotorOutput(); //Just a stalling method... had to have something here
        }

        intakePosition("UP");
       /* while(intakeFlipper.getPosition() != .75)
        {
            intakePosition("UP");
            tellMotorOutput();
        }*/

        slidesIn(); //move the intake up and the horiz slides in
        while(leftSlide.getPosition() < 1)
        {
            slidesIn();
            tellMotorOutput();
        }

        //flip the transfer down here


        //Moves the intake in and waits until it reaches its destination
        while(intakeFlipper.getPosition() < 1)
        {
            intakeFlipper.setPosition(1);
           tellMotorOutput(); //Just a stalling method... had to have something here
        }


        //Make a timer for running the intake spit-out
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.milliseconds() < 1000 )
        {
            tellMotorOutput();
        }
        timer.reset();
        while (timer.milliseconds() < 1000 )
        {
            intake_spin(-.5);
        }
        intake_spin(0);

        //Flip the intake out of the way before moving on
        intakePosition("UP");
        while(intakeFlipper.getPosition() != .75)
        {
            intakeFlipper.setPosition(.75);
            tellMotorOutput(); //more stalling... tee hee
        }
        //flip the intake up to allow scoring
        //Function Ends here
    }

    public void safeCollapse()
    {

        tempOutakePos("DOWN");

        //Moves and waits until the vert slides are at the bottom before moving on
        while (liftyL.getCurrentPosition() < -5 || liftyL.getCurrentPosition() > 5)
        {
            liftyL.setPower(1);
            liftyR.setPower(1);
            liftyL.setTargetPosition(0);
            liftyR.setTargetPosition(0);
            liftyL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftyR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        while(leftFlippyOutakeServo.getPosition() > 0.1)
        {
            tempOutakePos("DOWN");
            tellMotorOutput(); //Just a stalling method... had to have something here
        }

        intakePosition("UP");
       /* while(intakeFlipper.getPosition() != .75)
        {
            intakePosition("UP");
            tellMotorOutput();
        }*/

        slidesIn(); //move the intake up and the horiz slides in
        while(leftSlide.getPosition() < 1)
        {
            slidesIn();
            tellMotorOutput();
        }

        //flip the transfer down here



        //Flip the intake out of the way before moving on
        intakePosition("UP");
        while(intakeFlipper.getPosition() != .75)
        {
            intakeFlipper.setPosition(.75);
            tellMotorOutput(); //more stalling... tee hee
        }
        //flip the intake up to allow scoring
        //Function Ends here
    }



    public DcMotor.RunMode encoderRunningMode(){
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return null;
    }

    public void encoderReset(){
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @SuppressLint("DefaultLocale")
    public void tellMotorOutput(){
        telemetry.addData("Control Mode", controlMode);
        telemetry.addData("Motors", String.format("FL Power(%.2f) FL Location (%d) FL Target (%d)", frontLeftDrive.getPower(), frontLeftDrive.getCurrentPosition(), frontLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("FR Power(%.2f) FR Location (%d) FR Target (%d)", frontRightDrive.getPower(), frontRightDrive.getCurrentPosition(), frontRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BL Power(%.2f) BL Location (%d) BL Target (%d)", backLeftDrive.getPower(), backLeftDrive.getCurrentPosition(), backLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BR Power(%.2f) BR Location (%d) BR Target (%d)", backRightDrive.getPower(), backRightDrive.getCurrentPosition(), backRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("LiftyL Power (%.2f) LiftyL Location (%d) LiftyL Target (%d)", liftyL.getPower(), liftyL.getCurrentPosition(), liftyL.getTargetPosition()));
        telemetry.addData("Motors", String.format("LiftyR Power (%.2f) LiftyR Location (%d) LiftyR Target (%d)", liftyR.getPower(), liftyR.getCurrentPosition(), liftyR.getTargetPosition()));
        telemetry.addData("Flipper", intakeFlipper.getPosition());
        telemetry.update();
    }

    public double inchesToTicks(double inches){
        // returns the inches * ticks per rotation / wheel circ
        return ((inches/12.25) * 537.6 / .5);
        //todo Reference that 1 inch ~= 50 ticks
    }
    // one side may be backwards due to the direction that the motor was faced
    public void moveArm(String direction){
        if (direction == "Up"){
            liftyL.setPower(1);
            liftyL.setDirection(DcMotor.Direction.FORWARD);//inverted
            liftyR.setPower(1);
            liftyR.setDirection(DcMotor.Direction.FORWARD);//inverted
        } else if (direction == "Down"){
            liftyL.setPower(0.25);
            liftyL.setDirection(DcMotor.Direction.REVERSE);//Inverted
            liftyR.setPower(0.25);
            liftyR.setDirection(DcMotor.Direction.REVERSE);//Inverted
        }
    }

    ElapsedTime timer = new ElapsedTime();

    public void holdArm(){
        liftyL.setDirection(DcMotor.Direction.FORWARD);//
        liftyL.setPower(0.05);
        liftyR.setDirection(DcMotor.Direction.FORWARD);//
        liftyR.setPower(0.05);
    }


    public boolean primaryClawClosed = false;


  /*  Some April Tag and tensorflow stuff

    public void showersAndFlowers(){

        AprilTagProcessor OSHAmobile;

        OSHAmobile = new AprilTagProcessor.Builder()
                .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .build();
    }

    public void tensorFlowDetection(){

        TfodProcessor safetyGlasses;

        safetyGlasses = new TfodProcessor.Builder()
                .setMaxNumRecognitions(10)
                .setUseObjectTracker(true)
                .setTrackerMaxOverlap((float) 0.2)
                .setTrackerMinSize(16)
                .build();
    }

    public void visionPortal(AprilTagProcessor aprilTagProcessor, TfodProcessor tfodProcessor){
        VisionPortal Oracle;


        myVisionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Cam Cam"))
                .addProcessor(aprilTagProcessor)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
                .enableCameraMonitoring(true)
                .setAutoStopLiveView(true)
                .build();


    }

    public void retrieveAprilTags(AprilTagProcessor ATP){
        List<AprilTagDetection> ATDS;         // list of all detections // current detection in for() loop
        int SPOTnum;                           // ID code of current detection, in for() loop

        // Get a list of AprilTag detections.
        ATDS = ATP.getDetections();

        // Cycle through through the list and process each AprilTag.
        for (AprilTagDetection SPOT : ATDS) {

            if (SPOT.metadata != null) {  // This check for non-null Metadata is not needed for reading only ID code.
                SPOTnum = SPOT.id;

                // Now take action based on this tag's ID code, or store info for later action.

            }
        }
    }


   */



}
