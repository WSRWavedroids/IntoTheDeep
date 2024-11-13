package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Red Basic Net Zone")
public class RedBasicNetZone extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        telemetry.addData(currentPosition,"Start position");

        moveRobotForward(convertInchesToTicks(2), 2);
        telemetry.addData(currentPosition,"Booped forward");
        moveRobotLeft(convertInchesToTicks(18), 2);
        telemetry.addData(currentPosition,"Moved to net zone");
        moveRobotRight(convertInchesToTicks(96), 2);
        telemetry.addData(currentPosition,"Moved to parking spot");


    }
}