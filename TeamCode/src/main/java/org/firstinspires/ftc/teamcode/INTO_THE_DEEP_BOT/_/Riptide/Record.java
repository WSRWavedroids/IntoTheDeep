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

public class Record {
//This is the general record class

    public Robot robot;
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public RipConfig rip;
    public TeleOp_Recording recSource;

    //This constructor populates the null scripts once everything is initalized
    public Record(Robot robot, RipConfig rip, TeleOp_Recording recSource) {
        this.robot = robot;
        this.rip = rip;
        this.recSource = recSource;

    }

        public void recordMotorTest() {
        if (rip.ripMotors[0] != null)
        {
            for (DcMotorEx i: rip.ripMotors)
            {
                recSource.reportToOther(i.getVelocity());
            }
        }
        }

    }







