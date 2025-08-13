package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "moveXYTest")
public class moveXYTest extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode(); // Robot constructor

        waitForStart();
        prepareAuto();
        //setMotorTolerance(10);

        moveXY(0,900,true,1000);
        moveXY(900,0,true,1000);
        moveXY(-900,0,true,1000);
        moveXY(0,-900,true,1000);
        moveXY(-900,900,true,1000);
        moveXY(900,-900,true,1000);
        moveXY(-900,300,true,1000);
        moveXY(900,-300,true,1000);
        moveXY(0,0,1000,true,1000);
        moveXY(0,0,-1000,true,1000);

        prepareNextAction(2000);

    }
}