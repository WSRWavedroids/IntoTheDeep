package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Red Basic Observation Zone")
public class RedBasicObservationZone extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        waitForStart();
        telemetry.addData(currentPosition,"Start position");

        moveRobotRight(convertInchesToTicks(42), 2);
        telemetry.addData(currentPosition,"Moved to parking spot");


    }
}