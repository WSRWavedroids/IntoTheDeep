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
        // Make sure that the device name (text in green) is the EXACT SAME thing you typed in on the configuration on the driver hub.

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
        frontleftwheel.setPower(0);
        frontrightwheel.setPower(0);
        backleftwheel.setPower(0);
        backrightwheel.setPower(0);
    }

    public void setTargets(String direction, int ticks) {

        //DON'T GET FREAKED OUT BY THIS! There's a lot going on, but it's very repetitive.
        //Each of these sets the target position of each of the drivetrain motors based on the direction you want the robot to move in.
        //Example usage: robot.setTargets("Right", 400);
        //This will add or subtract 400 ticks from the current position of each motor and set that as the target position for the motor.

        if (direction == "Right"){
            //To move right, the FRONT LEFT and BACK RIGHT wheels should spin forward, and the FRONT RIGHT and BACK LEFT wheels should spin backward.
            frontleftwheel.setTargetPosition(frontleftwheel.getCurrentPosition() + ticks);
            frontrightwheel.setTargetPosition(frontrightwheel.getCurrentPosition() - ticks);
            backleftwheel.setTargetPosition(backleftwheel.getCurrentPosition() - ticks);
            backrightwheel.setTargetPosition(backrightwheel.getCurrentPosition() + ticks);

        } else if (direction == "Left"){
            //To move right, the FRONT RIGHT and BACK LEFT wheels should spin forward, and the FRONT LEFT and BACK RIGHT wheels should spin backward.
            frontleftwheel.setTargetPosition(frontleftwheel.getCurrentPosition() - ticks);
            frontrightwheel.setTargetPosition(frontrightwheel.getCurrentPosition() + ticks);
            backleftwheel.setTargetPosition(backleftwheel.getCurrentPosition() + ticks);
            backrightwheel.setTargetPosition(backrightwheel.getCurrentPosition() - ticks);

        } else if (direction == "Forward"){
            //To move forward, all of the wheels should spin forward.
            frontleftwheel.setTargetPosition(frontleftwheel.getCurrentPosition() + ticks);
            frontrightwheel.setTargetPosition(frontrightwheel.getCurrentPosition() + ticks);
            backleftwheel.setTargetPosition(backleftwheel.getCurrentPosition() + ticks);
            backrightwheel.setTargetPosition(backrightwheel.getCurrentPosition() + ticks);

        } else if (direction == "Backward") {
            //To move backward, all of the wheels should spin backward.
            frontleftwheel.setTargetPosition(frontleftwheel.getCurrentPosition() - ticks);
            frontrightwheel.setTargetPosition(frontrightwheel.getCurrentPosition() - ticks);
            backleftwheel.setTargetPosition(backleftwheel.getCurrentPosition() - ticks);
            backrightwheel.setTargetPosition(backrightwheel.getCurrentPosition() - ticks);

        } else if (direction == "Turn Right") {
            //To turn right, the LEFT SIDE wheels should spin forward, and the RIGHT SIDE wheels should spin backward.
            frontleftwheel.setTargetPosition(frontleftwheel.getCurrentPosition() + ticks);
            frontrightwheel.setTargetPosition(frontrightwheel.getCurrentPosition() - ticks);
            backleftwheel.setTargetPosition(backleftwheel.getCurrentPosition() + ticks);
            backrightwheel.setTargetPosition(backrightwheel.getCurrentPosition() - ticks);

        } else if (direction == "Turn Left") {
            //To turn left, the RIGHT SIDE wheels should spin forward, and the LEFT SIDE wheels should spin backward.
            frontleftwheel.setTargetPosition(frontleftwheel.getCurrentPosition() - ticks);
            frontrightwheel.setTargetPosition(frontrightwheel.getCurrentPosition() + ticks);
            backleftwheel.setTargetPosition(backleftwheel.getCurrentPosition() - ticks);
            backrightwheel.setTargetPosition(backrightwheel.getCurrentPosition() + ticks);

        }

        //See this file for a visual explanation of how mecanum wheels move: https://cdn11.bigcommerce.com/s-x56mtydx1w/images/stencil/original/products/2233/13272/3625-0202-0104-Product-Insight-2__24748__37151.1725633319.png?c=1

    }

    public void positionRunningMode(){
        //This will make all the motors set their goal to be moving in the direction necessary to hit the target number of ticks.
        //This is the mode you use in autonomous to drive to a certain position.
        //Example usage: robot.positionRunningMode();
        frontleftwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void powerSet(double speed) {
        //This is a versatile function to set all of the motors to the same speed.
        //Example usage: robot.powerSet(0.75);
        //That will set all of the motors to 75% power.
        frontleftwheel.setPower(speed);
        frontrightwheel.setPower(speed);
        backleftwheel.setPower(speed);
        backrightwheel.setPower(speed);

    }

    public void encoderRunningMode(){
        //This makes all the motors go back to trying to move at velocity based on provided power
        //This is the mode you run the drivetrain motors on 85% of the time
        //Example usage: robot.encoderRunningMode();
        frontleftwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderReset(){
        //This sets the current encoder position to zero and stops providing power to the motors.
        //This can be really helpful if your motors seem to be making up random number of ticks and not moving as intended.
        //Example usage: robot.encoderReset();
        frontleftwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    @SuppressLint("DefaultLocale")
    public void tellMotorOutput(){
        //Each line prints out the power, encoder location, and encoder target location of the motor
        //Example usage: robot.tellMotorOutput();
        telemetry.addData("Motors", String.format("FL Power(%.2f) FL Location (%d) FL Target (%d)", frontleftwheel.getPower(), frontleftwheel.getCurrentPosition(), frontleftwheel.getTargetPosition()));
        telemetry.addData("Motors", String.format("FR Power(%.2f) FR Location (%d) FR Target (%d)", frontrightwheel.getPower(), frontrightwheel.getCurrentPosition(), frontrightwheel.getTargetPosition()));
        telemetry.addData("Motors", String.format("BL Power(%.2f) BL Location (%d) BL Target (%d)", backleftwheel.getPower(), backleftwheel.getCurrentPosition(), backleftwheel.getTargetPosition()));
        telemetry.addData("Motors", String.format("BR Power(%.2f) BR Location (%d) BR Target (%d)", backrightwheel.getPower(), backrightwheel.getCurrentPosition(), backrightwheel.getTargetPosition()));
        telemetry.update();
    }

}
