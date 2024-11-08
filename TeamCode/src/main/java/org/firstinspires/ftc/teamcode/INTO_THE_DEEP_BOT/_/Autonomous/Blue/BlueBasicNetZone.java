package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Blue Basic Net Zone")
public class BlueBasicNetZone extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        telemetry.addData(currentPosition,"Start position");

        moveRobotLeft(convertInchesToTicks(22), 2);
        telemetry.addData(currentPosition,"Moved to net zone");
        moveRobotRight(convertInchesToTicks(114), 2);
        telemetry.addData(currentPosition,"Moved to parking spot");


    }
}