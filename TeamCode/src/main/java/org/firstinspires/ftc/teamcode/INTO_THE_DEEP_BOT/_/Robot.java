package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Trajectory;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Objects;

public class Robot {

    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor lifty; //vertical extension
    public DcMotor waterslide; //horizontal extension

    public CRServo leftIntake;
    public CRServo rightIntake;

    public Servo leftSlide;
    public Servo rightSlide;

    public Servo intakeFlipper;

    public Servo flippyOutakeServo;

    public Servo grabbyOutakeServo;

    public NormalizedColorSensor colorSens;
    public SparkFunOTOS sparky;

    public Telemetry telemetry;

    //init and declare war
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public String controlMode = "Robot Centric";// Robot Centric
    public String intakeFlipperPos ="UP";
    public String color = "";
    public IMU.Parameters imuParameters;

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
        lifty = hardwareMap.get(DcMotor.class, "lifty");
        //waterslide = hardwareMap.get(DcMotor.class, "waterslide");
        leftIntake = hardwareMap.get(CRServo.class, "leftIntake");
        rightIntake = hardwareMap.get(CRServo.class, "rightIntake");
        intakeFlipper = hardwareMap.get(Servo.class, "flipperServo");
        flippyOutakeServo = hardwareMap.get(Servo.class, "flippyOutakeServo");
        grabbyOutakeServo = hardwareMap.get(Servo.class, "grabbyOutakeServo");

        leftSlide = hardwareMap.get(Servo.class, "leftSlide");
        rightSlide = hardwareMap.get(Servo.class, "rightSlide");
        colorSens = hardwareMap.get(NormalizedColorSensor.class,"colorSens");
        sparky = hardwareMap.get(SparkFunOTOS.class,"sparkFunSparkJoy");


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
        backRightDrive.setDirection(DcMotor.Direction.FORWARD); //Was inverted as forward
        lifty.setDirection(DcMotor.Direction.REVERSE);//inverted
        //waterslide.setDirection(DcMotor.Direction.FORWARD);

        // This tells the motors to chill when we're not powering them.
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //waterslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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




    }

    public void positionRunningMode(){
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lifty.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waterslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
        intakePosition("UP");
    }

    public void collapseExpansion()
    {
        slidesIn();
        //lifty.setTargetPosition(-20);
    }

    public void intake_spin (double direction){
        //servos spin in thingy
        if(direction > 0)
        {
            leftIntake.setDirection(CRServo.Direction.FORWARD);
            rightIntake.setDirection(CRServo.Direction.REVERSE);//direction is forward... set speed to that val
            leftIntake.setPower(Math.abs(direction));
            rightIntake.setPower(Math.abs(direction));
        }
        else
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
        if(intakeFlipperPos == "UP")
        {
            intakeFlipper.setPosition(1);//guess position
            canWiggle = true;
        }
        else if(intakeFlipperPos == "DOWN")
        {
            intakeFlipper.setPosition(.15);
            canWiggle = true;
        }

    }

    public void outakeclawOpenClose(String state)
    {
        if(state == "OPEN")
        {
            grabbyOutakeServo.setPosition(.9);
        }
        else if (state == "CLOSED")
        {
            grabbyOutakeServo.setPosition(1);
        }
    }

    public void tempOutakePos(String pos)
    {
        if (pos == "DOWN")
        {
            flippyOutakeServo.setPosition(.5);
        }
        if (pos == "UP")
        {
            flippyOutakeServo.setPosition(.8);
        }
        if (pos == "MOREUP")
        {
            flippyOutakeServo.setPosition(1);
        }


    }

    public NormalizedRGBA getColors(){

        colorSens.setGain(2);   //This can be tuned in order to get more useful values
        final float[] hsvValues = new float[3];
        NormalizedRGBA colors = colorSens.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        telemetry.addLine()
                .addData("Red", "%.3f", colors.red)
                .addData("Green", "%.3f", colors.green)
                .addData("Blue", "%.3f", colors.blue);

        return colors;

    }

    public float[] getHSV(){

        colorSens.setGain(2);   //This can be tuned in order to get more useful values
        final float[] hsvValues = new float[3];
        NormalizedRGBA colors = colorSens.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        telemetry.addLine()
                .addData("Hue", "%.3f", hsvValues[0])
                .addData("Saturation", "%.3f", hsvValues[1])
                .addData("Value", "%.3f", hsvValues[2]);
        telemetry.addData("Alpha", "%.3f", colors.alpha);

        return hsvValues;

    }

    public void identifyColor(NormalizedRGBA colors, float[] hsvValues){

        if (colors.blue > colors.green && colors.blue > colors.red){
            color = "blue";
        } else if (hsvValues[0] > 50){
            color = "yellow";
        } else {
            color = "red";
        }

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
        telemetry.addData("Motors", String.format("Lifty Power (%.2f) Lifty Location (%d) Lifty Target (%d)", lifty.getPower(), lifty.getCurrentPosition(), lifty.getTargetPosition()));
        //telemetry.addData("Motors", String.format("WaterSlide Motor Power (%.2f) WaterSlide Location (%d) WaterSlide Target (%d)", waterslide.getPower(), waterslide.getCurrentPosition(), waterslide.getTargetPosition()));
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
            lifty.setPower(1);
            lifty.setDirection(DcMotor.Direction.FORWARD);//inverted
        } else if (direction == "Down"){
            lifty.setPower(0.25);
            lifty.setDirection(DcMotor.Direction.REVERSE);//Inverted
        }
    }

    ElapsedTime timer = new ElapsedTime();

    public void holdArm(){
        lifty.setDirection(DcMotor.Direction.FORWARD);//
        lifty.setPower(0.05);
    }

    public boolean primaryClawClosed = false;

}
