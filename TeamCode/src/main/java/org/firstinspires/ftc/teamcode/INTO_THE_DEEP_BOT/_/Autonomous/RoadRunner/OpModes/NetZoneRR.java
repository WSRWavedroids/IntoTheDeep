package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.OpModes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
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
            Pose2d initialPose = new Pose2d(69, 69, Math.toRadians(420)); //todo Make this a more serious answer
            MecanumDrive drive = new SparkFunDrive(hardwareMap, initialPose);
            // make a Slides instance
            Slides slides = new Slides();
            // make a Ground Intake instance
            GroundIntake steve = new GroundIntake();

        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder move = drive.actionBuilder(initialPose)
                .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(48)
                .setTangent(Math.toRadians(0))
                .lineToX(32)
                .strafeTo(new Vector2d(44.5, 30))
                .turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3)
                .endTrajectory();
        Action halt = move.endTrajectory().fresh()
                .build();

        Action groove = move.build();

        waitForStart();

        if (isStopRequested()) return;

        //This is the part where stuff actually starts happening! Everything else was just setup
        Actions.runBlocking(
                new SequentialAction( //This can be changed to a sequential or parallel action based on when you need stuff to happen!
                        groove,
                        Slides.slideUpToHighPos(2),
                        halt
                )
        );

    }
}