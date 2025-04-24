package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

public class Playback {
//This is the general record class

    public Robot robot;
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public RipConfig rip;
    public TeleOp_Recording recSource;

    //This constructor populates the null scripts once everything is initalized
    public Playback(Robot robot, RipConfig rip) {
        this.robot = robot;
        this.rip = rip;
        //this.recSource = recSource;

    }

    public void EmergencyStop() //If we miss frames lets maybe not kill the robot
    {
        for (DcMotorEx i : rip.ripMotors)
        {
            i.setPower(0);
        }
        for (CRServo i : rip.ripCRServos)
        {
            i.setPower(0);
        }
    }
    }







