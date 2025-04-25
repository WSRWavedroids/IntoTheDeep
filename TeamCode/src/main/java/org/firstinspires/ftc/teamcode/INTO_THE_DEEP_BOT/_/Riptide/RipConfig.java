package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class RipConfig {

    public Robot robot;
    public OpMode opmode;
    public Telemetry telemetry;
    public HardwareMap hardwareMap;

    public int motorCount = 6; //Max = 8
    public int servoCount = 5; //Max = 12- CRServoCount
    public int CRServoCount = 2; //Max = 12-servoCount

   public  DcMotorEx[] ripMotors;
   public Servo[] ripServos;
   public CRServo[] ripCRServos;

    //Constructor will get values when called so we know its safe
   public RipConfig(Robot robot)
   {
       this.robot = robot;



       ripMotors = new DcMotorEx[motorCount];
       ripServos = new Servo[servoCount];
       ripCRServos = new CRServo[CRServoCount];

       //Now Lets populate them with hardware

       //MOTORS
       ripMotors[0] = robot.frontLeftDrive;
       ripMotors[1] = robot.frontRightDrive;
       ripMotors[2] = robot.backLeftDrive;
       ripMotors[3] = robot.backRightDrive;
       ripMotors[4] = robot.liftyL;
       ripMotors[5] = robot.liftyR;

       //SERVOS
       ripServos[0] = robot.intakeFlipper;
       ripServos[1] = robot.grabbyOutakeServoL;
       ripServos[2] = robot.grabbyOutakeServoR;
       ripServos[3] = robot.leftSlide;
       ripServos[4] = robot.rightSlide;

       //CR Servos
       ripCRServos[0] = robot.leftIntake;
       ripCRServos[1] = robot.rightIntake;

   }

   public void SharedInit()
           //SharedInit is should run at the start of both auto and recording for consistency.
           //Copy and paste your autos init here and call this function in that script
   {
        robot.outakeclawOpenClose("CLOSED");

   }

   public String grabTime()
   {
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
       Date date = new Date(System.currentTimeMillis()); // or just `new Date()` works too
       return formatter.format(date);
   }








    //servos list

    //cr servos list


}
