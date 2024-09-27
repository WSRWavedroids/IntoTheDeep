package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.android.util.Size;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class Robot {

    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor slideL;
    public DcMotor slideR;
    public DcMotor hookMotor;

    public DcMotor droneMotor;


    public Servo leftClaw;
    public Servo rightClaw;

    public Servo SecondaryClaw;

    public Servo hookServo;

    public Servo armL;

    public Servo armR;

    //public DistanceSensor distanceSensor;


    public WebcamName CamCam;

    public Telemetry telemetry;
    //public BNO055IMU imu;

    //init and declare war
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public static double parkingZone;
    public String startingPosition;
    public String controlMode = "Robot Centric";

    //Initialize motors and servos
    public Robot(HardwareMap hardwareMap, Telemetry telemetry, OpMode opmode){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opmode = opmode;

        // This section turns the names of the pieces of hardware into variables that we can program with.
        // Make sure that the device name is the exact same thing you typed in on the configuration on the driver hub.
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        slideL = hardwareMap.get(DcMotor.class, "slideL");
        slideR = hardwareMap.get(DcMotor.class, "slideRAndOdoPodR");
        armL = hardwareMap.get(Servo.class, "armL");
        armR = hardwareMap.get(Servo.class, "armR");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        hookServo = hardwareMap.get(Servo.class, "hookServo");
        SecondaryClaw = hardwareMap.get(Servo.class, "SecondaryClaw");
        CamCam = hardwareMap.get(WebcamName.class, "CamCam");
        //distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
        hookMotor = hardwareMap.get(DcMotor.class, "hookAndOdoPodC");
        droneMotor = hardwareMap.get(DcMotor.class, "droneAndOdoPodL");

        //add arms to map
        /*
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        */
        // This section sets the direction of all of the motors. Depending on the motor, this may change later in the program.
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD); //Was inverted as forward
        slideL.setDirection(DcMotor.Direction.REVERSE);//inverted
        slideR.setDirection(DcMotor.Direction.FORWARD);
        hookMotor.setDirection(DcMotor.Direction.FORWARD);
        droneMotor.setDirection(DcMotor.Direction.REVERSE);
        // This tells the motors to chill when we're not powering them.
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hookMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        droneMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
            backLeftDrive.setTargetPosition(-ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Backward") {
            frontLeftDrive.setTargetPosition(ticks - frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks - frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Turn Right") {
            frontLeftDrive.setTargetPosition(ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks - frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Turn Left") {
            frontLeftDrive.setTargetPosition(-ticks - frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Arm"){
            slideL.setTargetPosition(ticks + slideL.getCurrentPosition());
            slideR.setTargetPosition(ticks + slideR.getCurrentPosition());

        }
        else if (direction == "Hook")//new remove if no work
        {
            hookMotor.setTargetPosition(ticks + hookMotor.getCurrentPosition());
        }




        /*else if (direction == "Turntable"){
            robot.open
            armR.setTargetPosition(-ticks + armR.getCurrentPosition());
        }*/

    }

    public void positionRunningMode(){
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void powerSet(double speed) {
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

    }

    public void openAndCloseLeftClaw (double position){
        leftClaw.setPosition(position);
        //rightClaw.setPosition(position);

        if (position == 0){
            telemetry.addData("Claw", "Closed");
        } else if (position >= 0.3){
            telemetry.addData("Claw", "Open");
        }

    }

    public void openAndCloseRightClaw (double position) {
        //leftClaw.setPosition(position);
        rightClaw.setPosition(position);



        if (position == 0) {
            telemetry.addData("Claw", "Closed");
        } else if (position >= 0.3) {
            telemetry.addData("Claw", "Open");
        }
    }

    public void closeSecondaryClaw()
    {
        SecondaryClaw.setPosition(0.55);
    }

    public void openSecondaryClaw()
    {
        SecondaryClaw.setPosition(.62);
    }


    public void rotateArmUp()
    {         //Raise
        rotateLeftArm(0.30); // perfect... The lower the value the higher it goes
        rotateRightArm(0.54);

    }

    public void rotateArmDown()
    {
        //lower
        rotateLeftArm(0.55);
        rotateRightArm(0.26);

    }

    public void rotateRightArm(double position) // Remeber these are opposite directions
    {
        armR.setPosition(position);
    }

    public void rotateLeftArm(double position) // Rememeber these are opposite directions
    {
        armL.setPosition(position);
    }

    public void encoderRunningMode(){
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
        telemetry.addData("Motors", String.format("SlideL Power (%.2f) Arm Location (%d) Arm Target (%d)", slideL.getPower(), slideL.getCurrentPosition(), slideL.getTargetPosition()));
        telemetry.addData("Motors", String.format("SlideR Power (%.2f) Arm Location (%d) Arm Target (%d)", slideR.getPower(), slideR.getCurrentPosition(), slideR.getTargetPosition()));
        telemetry.addData("Motors", String.format("Hook Motor Power (%.2f) Arm Location (%d) Arm Target (%d)", hookMotor.getPower(), hookMotor.getCurrentPosition(), hookMotor.getTargetPosition()));
        telemetry.addData("ArmL", armL.getPosition());
        telemetry.addData("ArmR", armR.getPosition());
        telemetry.addData("ClawL", leftClaw.getPosition());
        telemetry.addData("ClawR", rightClaw.getPosition());
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
            slideL.setPower(0.75);
            slideL.setDirection(DcMotor.Direction.FORWARD);//inverted
            slideR.setPower(0.75);
            slideR.setDirection(DcMotor.Direction.REVERSE);
        } else if (direction == "Down"){
            slideL.setPower(0.25);
            slideL.setDirection(DcMotor.Direction.REVERSE);//Inverted
            slideR.setPower(0.25);
            slideR.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    ElapsedTime timer = new ElapsedTime();
    public void firePlane(long motorTime)
    {
        long beginning = System.currentTimeMillis();
        long end = beginning + motorTime;
        boolean planeAlreadyLaunched = false;
        while (end > System.currentTimeMillis() && planeAlreadyLaunched == false){
            droneMotor.setPower(2);
        }
        planeAlreadyLaunched = true;
        droneMotor.setPower(0);



        //timer

    }

    public void holdArm(){
        slideL.setDirection(DcMotor.Direction.FORWARD);//
        slideL.setPower(0.05);
        slideR.setDirection(DcMotor.Direction.REVERSE);//Inverted BC facing other way
        slideR.setPower(0.05);//used to be 0.1
    }


    public boolean primaryClawClosed = false;
    public void closeClaw()
    {
        openAndCloseRightClaw(0.6); //Moves right claw left
        openAndCloseLeftClaw(0.4); // //Moves left claw right
        primaryClawClosed = true;
    }

    public void openClaw() {
        openAndCloseLeftClaw(0.5); //Moves left claw left
        openAndCloseRightClaw(0.5); // Moves right claw
        primaryClawClosed = false;
    }

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
