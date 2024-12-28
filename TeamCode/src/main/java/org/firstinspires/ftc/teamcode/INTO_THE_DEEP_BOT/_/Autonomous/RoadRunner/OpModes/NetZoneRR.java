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
            Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(0)); //todo Make this a more serious answer
            MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder move = drive.actionBuilder(initialPose)
                .turn(90)
                .waitSeconds(2)
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
                        groove,
                        halt
                )
        );

    }
}