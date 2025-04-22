package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;

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

import java.util.Objects;

public class RipConfig {

    public Robot robot;
    public OpMode opmode;
    public HardwareMap hardwareMap;

   public  DcMotorEx[] ripMotors = {robot.frontLeftDrive, robot.frontRightDrive, robot.backLeftDrive, robot.backRightDrive};

   public Servo[] ripServos = {robot.intakeFlipper, robot.grabbyOutakeServoL, robot.grabbyOutakeServoR, robot.leftSlide, robot.rightSlide};

   public CRServo[] ripCRServos = {robot.leftIntake, robot.rightIntake}


    //





    //servos list

    //cr servos list


}
