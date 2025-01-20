package org.firstinspires.ftc.teamcode.Outreach;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Objects;

public class Starmont_Bot {

    //TODO: Add all motors and servos
    //Example: public DcMotor frontLeftDrive;
    public DcMotor backleftwheel;
    public DcMotor backrightwheel;
    public DcMotor frontleftwheel;
    public DcMotor frontrightwheel;
    public DcMotor arm;

    public Servo rightjaw;
    public Servo leftjaw;

    public Telemetry telemetry;

    //Do not delete! Very important!
    public OpMode opmode;
    public HardwareMap hardwareMap;

    public Starmont_Bot()
    {
        //You don't have to put anything in here :)
    }

    public Starmont_Bot(HardwareMap hardwareMap, Telemetry telemetry, OpMode opmode){
        //This function must be called in any TeleOp or autonomous mode in order for the code to have access to the hardware.
        //Example usage: public Starmont_Bot Bot = new Starmont_Bot();
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opmode = opmode;

        // This section turns the names of the pieces of hardware into variables that we can program with.
        // Make sure that the device name is the exact same thing you typed in on the configuration on the driver hub.

        //TODO: Initialize all motors and servos from the hardware map
        //Example: frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backleftwheel = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backrightwheel = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontleftwheel = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontrightwheel = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        arm = hardwareMap.get(DcMotor.class, "frontLeftDrive");

        leftjaw = hardwareMap.get(Servo.class, "frontLeftDrive");
        rightjaw = hardwareMap.get(Servo.class, "frontLeftDrive");

        // This section sets the direction of all of the motors. Depending on the motor, this may change later in the program.
        //TODO: Reverse left side motors for mecanum wheel driving
        //Example frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftwheel.setDirection(DcMotor.Direction.REVERSE);
        frontleftwheel.setDirection(DcMotor.Direction.REVERSE);

        // This tells the motors to chill when we're not powering them.
        //TODO: Add in zero-power behaviors to avoid drift
        //Example: frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleftwheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftwheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightwheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightwheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");

    }


    public boolean isWheelsBusy(){
        //This checks if each wheel is "busy" (moving). If the answer to any single check is yes,
        // the whole expression is true since we're using or statements. If the answer to ALL the checks is no, then the expression will be false.
        //Example usage: if (robot.isWheelsBusy()){ fdjsahgjfhsgri }
        return backleftwheel.isBusy() || frontleftwheel.isBusy() || frontrightwheel.isBusy() || backrightwheel.isBusy();
    }

    public void stopAllMotors() {
        //Setting motor powers is the bread and butter of controlling your robot. This is a simple way to tell all your motors to stop.
        //Example usage: robot.stopAllMotors();
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void setTargets(String direction, int ticks) {

        //DON'T GET FREAKED OUT BY THIS! There's a lot going on, but it's very repetitive.

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

        }

        //See this file for a visual explanation of how mecanum wheels move: https://cdn11.bigcommerce.com/s-x56mtydx1w/images/stencil/original/products/2233/13272/3625-0202-0104-Product-Insight-2__24748__37151.1725633319.png?c=1

    }

    public void positionRunningMode(){
        //This will make all the motors set their goal to be moving in the direction necessary to hit the target number of ticks.
        //This is the mode you use in autonomous to drive to a certain position.
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void powerSet(double speed) {
        //This is a versatile function to set all of the motors to the same speed.
        //Example usage: robot.powerSet(0.75);
        //That will set all of the motors to 75% power.
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

    }

    public void encoderRunningMode(){
        //This makes all the motors go back to trying to move at velocity based on provided power
        //This is the mode you run the drivetrain motors on 85% of the time
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderReset(){
        //This sets the current encoder position to zero and stops providing power to the motors.
        //This can be really helpful if your motors seem to be making up random number of ticks and not moving as intended.
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @SuppressLint("DefaultLocale")
    public void tellMotorOutput(){

        //Each line prints out the power, encoder location, and encoder target location of the motor
        telemetry.addData("Motors", String.format("FL Power(%.2f) FL Location (%d) FL Target (%d)", frontLeftDrive.getPower(), frontLeftDrive.getCurrentPosition(), frontLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("FR Power(%.2f) FR Location (%d) FR Target (%d)", frontRightDrive.getPower(), frontRightDrive.getCurrentPosition(), frontRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BL Power(%.2f) BL Location (%d) BL Target (%d)", backLeftDrive.getPower(), backLeftDrive.getCurrentPosition(), backLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BR Power(%.2f) BR Location (%d) BR Target (%d)", backRightDrive.getPower(), backRightDrive.getCurrentPosition(), backRightDrive.getTargetPosition()));
        telemetry.update();
    }

}
