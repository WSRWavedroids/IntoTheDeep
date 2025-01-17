package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "Observation Zone High Chamber")
public class BLUE_OBSZONE_HighChamber extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();

        waitForStart();

        //First movements to line up under bar
        prepareAuto();
        speed = .65;
        moveDiagonalLeft(495, 0); //Trusting Pythagorus
        moveRobotForward(150, 0);

        //moveRobotForward(500, 0);
        //moveRobotLeft(350, 0);
        moveArm(1500, 1, 0);
        moveRobotForward(400, 0);

        moveArm(1850, 1, 0); //Places the starting specimen on the bar
        robot.outakeclawOpenClose("OPEN"); //Releases said specimen


        moveRobotBackward(500,500);//Back away

        //go push the sample to the obszone
        speed = 1;
        moveArm(143, 1, 2);
        moveRobotRight(900, 2);
        moveRobotForward(1000, 0);
        moveRobotRight(450, 0);
        moveRobotBackward(800, 0);
        moveRobotForward(200, 0);

        turnRobotRight(1530, 0);

        moveRobotForward(400, 0);
        robot.outakeclawOpenClose("CLOSE");
        /*robot.outakeclawOpenClose("CLOSED"); //This is the line that picks up the specimen from the field wall
        prepareNextAction(500);
        moveArm(230, 1, 2);
        speed = 1;
        moveRobotBackward(400, 2);
        moveRobotRight(1575, 2);
        turnRobotLeft(1525, 2);*/
    }
}