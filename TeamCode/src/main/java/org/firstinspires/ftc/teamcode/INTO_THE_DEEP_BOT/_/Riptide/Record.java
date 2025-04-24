package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

public class Record {
//This is the general record class

    public Robot robot;
    public OpMode opmode;
    public Telemetry telemetry;
    public HardwareMap hardwareMap;
    public RipConfig rip;
    public TeleOp_Recording recSource;

    //This constructor populates the null scripts once everything is initalized
    public Record(Robot robot, RipConfig rip) {
        this.robot = robot;
        this.rip = rip;
        //this.recSource = recSource;

    }

        /*public void recordMotorTest() {
        if (rip.ripMotors[0] != null)
        {
            for (DcMotorEx i: rip.ripMotors)
            {
                recSource.reportToOther("Motor", i.getDeviceName() , i.getVelocity());
            }
        }
        }*/

        public void recordFrame()
        {
            //Open File to new entry

            //enter date


            //Motors
            telemetry.addLine("Motors");
            for (DcMotorEx i: rip.ripMotors)
            {
                telemetry.addLine(i.getDeviceName() + " Velocity is:" + i.getVelocity());
            }
            //Servos
            telemetry.addLine("Servos");
            for (Servo i: rip.ripServos)
            {
                telemetry.addLine(i.getDeviceName() + " Position is:"+ i.getPosition());

            }
            //CR Servo
            telemetry.addLine("CR Servos");
            for (CRServo i: rip.ripCRServos)
            {
                telemetry.addLine(i.getDeviceName() + " Direction is:"+ i.getDirection() + " Power is:"+i.getPower());
            }
        }

    }







