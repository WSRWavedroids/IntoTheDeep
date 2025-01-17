package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.OpModes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import static com.qualcomm.robotcore.util.ElapsedTime.Resolution.SECONDS;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPlatinum;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.MecanumDrive;
//import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner.SparkFunDrive;


@Autonomous(group = "Basic", name = "RoadRunner Nonsense", preselectTeleOp = "STEEVE")
public class NetZoneRR extends AutonomousPlatinum {

    public static String TEAM_NAME = "Wave Droids";
    public static int TEAM_NUMBER = 13206;

    //Define and declare Robot Starting Locations
    public enum START_POSITION{
        LEFT,
        RIGHT
    }

    public static START_POSITION startPosition;

    public void runOpMode() throws InterruptedException {

        super.runOpMode();

        telemetry.setAutoClear(true);
        telemetry.clearAll();

        while(!isStopRequested()){
            telemetry.addData("Initializing FTC Wires (ftcwires.org) Autonomous adopted for Team:",
                    TEAM_NAME, " ", TEAM_NUMBER);
            telemetry.addData("---------------------------------------","");
            telemetry.addData("Select Starting Position using XYAB on Logitech (or ▢ΔOX on Playstayion) on gamepad 1:","");
            telemetry.addData("    Left   ", "(X / ▢)");
            telemetry.addData("    Right ", "(Y / Δ)");

            if(gamepad1.x){
                startPosition = START_POSITION.LEFT;
                break;
            }
            if(gamepad1.y){
                startPosition = START_POSITION.RIGHT;
                break;
            }
            telemetry.update();
        }
        telemetry.setAutoClear(false);
        telemetry.clearAll();

        telemetry.addData("Selected Starting Position", startPosition);
        telemetry.update();

        waitForStart();

        //Game Play Button  is pressed
        if (opModeIsActive() && !isStopRequested()) {
            //Build parking trajectory based on last detected target by vision
            runAutonomousMode();
        }
    }   // end runOpMode()

    public void runAutonomousMode() {

        //Auto Left Positions - Samples
        Pose2d initialPose = new Pose2d(9, 63, Math.toRadians(270)); // Starting Pose
        Pose2d submersibleSpecimen = new Pose2d(28,-1,Math.toRadians(0) );
        Pose2d netZone = new Pose2d(9  ,15,Math.toRadians(-45));
        Pose2d yellowSampleOne = new Pose2d(18,12,Math.toRadians(-14));
        Pose2d yellowSampleTwo = new Pose2d(18,18,Math.toRadians(1));
        Pose2d preSubmersiblePark = new Pose2d(58,11,Math.toRadians(0));
        Pose2d submersiblePark = new Pose2d(59,-15,Math.toRadians(90));

        //Auto Right Positions - Specimens
        Pose2d observationZone = new Pose2d(8,-37,Math.toRadians(0));
        Pose2d specimenPickup = new Pose2d(3,-30,Math.toRadians(0));
        Pose2d preColorSampleOne = new Pose2d(28,-27,Math.toRadians(0));
        Pose2d prePushColorSampleOne = new Pose2d(51,-27,Math.toRadians(0));
        Pose2d colorSampleOne = new Pose2d(51,-40,Math.toRadians(0));
        Pose2d observationPark = new Pose2d(5,-30,Math.toRadians(0));

        double waitSecondsBeforeDrop = 0;
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Slides slides = new Slides();

        //Move robot to netZone with preloaded sample ready to drop in basket
        Actions.runBlocking(
                drive.actionBuilder(initialPose)
                        .strafeToLinearHeading(netZone.position, netZone.heading)
                        .build());
        safeWaitSeconds(1);
        telemetry.addLine("Move robot to netZone");
        telemetry.update();


        /*
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

         */

    }

    //method to wait safely with stop button working if needed. Use this instead of sleep
    public void safeWaitSeconds(double time) {
        ElapsedTime timer = new ElapsedTime(SECONDS);
        timer.reset();
        while (!isStopRequested() && timer.time() < time) {
        }
    }
}