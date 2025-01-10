package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.OpModes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPlatinum;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.SparkFunDrive;


@Autonomous(group = "Basic", name = "RoadRunner Nonsense")
public class NetZoneRR extends AutonomousPlatinum {

    public void runOpMode() throws InterruptedException {

        super.runOpMode();

            // instantiate your MecanumDrive at a particular pose.
            Pose2d initialPose = new Pose2d(9, 63, Math.toRadians(270)); //todo Make this a more serious answer
            MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
            Slides slides = new Slides();

        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder initialToBar = drive.actionBuilder(initialPose)
                //Drives from the starting position against the wall to the bar
                .turn(90)
                .waitSeconds(2)
                .strafeTo(new Vector2d(6,33))
                //Insert a whole bunch of arm nonsense in here
                .endTrajectory();

        TrajectoryActionBuilder toBlock1 = drive.actionBuilder(new Pose2d(6,33, Math.toRadians(270)))
                //Drives from the bar to the first sample on the ground (closest to the submersible)
                .strafeTo(new Vector2d(6,40))
                .strafeToLinearHeading(new Vector2d(48,40), Math.PI * 1/2)
                //Insert claw nonsense in here
                .endTrajectory();

        TrajectoryActionBuilder toBar = drive.actionBuilder(new Pose2d(48,40, Math.toRadians(270)))
                //Drives from the sample pickup spot to the bar
                .strafeToLinearHeading(new Vector2d(6,40), Math.PI * 3/2)
                .endTrajectory();

        //Action halt = move.endTrajectory().fresh().build();

        Action initialBar = initialToBar.build();

        Action block = toBlock1.build();

        Action bar = toBar.build();

        waitForStart();

        if (isStopRequested()) return;

        //This is the part where stuff actually starts happening! Everything else was just setup
        Actions.runBlocking(
                new SequentialAction( //This can be changed to a sequential or parallel action based on when you need stuff to happen!
                        initialBar,
                        slides.slideUpToHighPos(2),
                        block,
                        bar
                )
        );

    }
}