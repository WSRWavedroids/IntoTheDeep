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
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Teleop.TeleOp_Recording;
import org.firstinspires.ftc.teamcode.OLD.ChonkRobot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.Objects;

public class Record extends LinearOpMode {


    public Robot robot;
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public RipConfig rip;
    public TeleOp_Recording recSource;


    @Override
    public void runOpMode() throws InterruptedException {robot = new Robot(hardwareMap, telemetry, this);}

    public void recordMotorTest()
    {
        //rip = new RipConfig();
        if (rip.ripMotors[0] != null)
        {
            for (DcMotorEx i: rip.ripMotors) //foreach motor make a line
            {
                recSource.reportToOther(i.getVelocity());
            }
        }
    }




}
