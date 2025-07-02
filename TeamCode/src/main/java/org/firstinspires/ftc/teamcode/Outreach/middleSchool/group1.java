package org.firstinspires.ftc.teamcode.Outreach.middleSchool;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Middle School Group 1")
public class group1 extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();

        waitForStart();
        prepareAuto();
        setMotorTolerance(10);

        speed = .6;
        sleep(200);

        // Code goes here

        robot.safeCollapse(); // Collapses the robot so the robot doesn't fall when auto ends
        prepareNextAction(2000);


    }
}