package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.Autonomous;



import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.localization.constants.OTOSConstants;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

//import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPearl;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPlatinum;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.*;


/**
 * This is an example auto that showcases movement and control of two servos autonomously.
 * It is a 0+4 (Specimen + Sample) bucket auto. It scores a neutral preload and then pickups 3 samples from the ground and scores them before parking.
 * There are examples of different ways to build paths.
 * A path progression method has been created and can advance based on time, position, or other factors.
 *
 * @author Baron Henderson - 20077 The Indubitables
 * @version 2.0, 11/28/2024
 */

@Autonomous(name = "Pedro go obzone", group = "Pedro's Autos")
public class obzone_zoomzoom extends OpMode {

    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    public AutonomousPearl plus = new AutonomousPearl() {
        @Override
        public void init() {

        }

        @Override
        public void loop() {

        }
    };
    public Robot robot;
    public int pathStateNumber;

    /** This is the variable where we store the state of our auto.
     * It is used by the pathUpdate method. */
    public enum PathState {
        PRELOAD_SCORE_SETUP, PRELOAD_SCORE_CLICK, PRELOAD_SCORE_RELEASE, SAMPLE_PUSH,
        CYCLE_1_SETUP, CYCLE_1_GRAB, CYCLE_1_RAISE, CYCLE_1_TO_BAR, CYCLE_1_CLICK, CYCLE_1_RELEASE,
        CYCLE_2_SETUP, CYCLE_2_GRAB, CYCLE_2_RAISE, CYCLE_2_TO_BAR, CYCLE_2_CLICK, CYCLE_2_RELEASE,
        CYCLE_3_SETUP, CYCLE_3_GRAB, CYCLE_3_RAISE, CYCLE_3_TO_BAR, CYCLE_3_CLICK, CYCLE_3_RELEASE,
        PARK
    }

    /* Create and Define Poses + Paths
     * Poses are built with three constructors: x, y, and heading (in Radians).
     * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
     * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
     * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
     * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
     * Lets assume our robot is 18 by 18 inches
     * Lets assume the Robot is facing the human player and we want to score in the bucket */

    //Start Pos and go score
    private final Pose startPose = new Pose(9, 55, Math.toRadians(0)); // Basket parking is x = 2.75, y=109
    private final Pose scorePreloadPos = new Pose(37, 60.26, Math.toRadians(0));


    //Make this a pathset to push first sample Go in front of the first sample
    private final Pose sample1Pos = new Pose(63.9, 24, Math.toRadians(0));
    private final Pose sample1control1 = new Pose(23.70, 55.83, Math.toRadians(0));
    private final Pose sample1control2 = new Pose(28.36, 2, Math.toRadians(0));
    private final Pose Sample1control3 = new Pose(64.25, 54.94, Math.toRadians(0));
    private final Pose pushSample1Pos = new Pose(25, 24, Math.toRadians(0));

    private final Pose sample2Pos = new Pose(64.48, 15.5, Math.toRadians(0));
    private final Pose sample2control1 = new Pose(63.8, 29.46, Math.toRadians(0));
    private final Pose pushSample2Pos = new Pose(25, 15.5, Math.toRadians(0));

    private final Pose controlToGrabPos1 = new Pose(53.39, 15.06, Math.toRadians(0));
    private final Pose controlToGrabPos2 = new Pose(34.2, 32.78, Math.toRadians(180));
    private final Pose preWallGrabPosition = new Pose(18, 24, Math.toRadians(180));
    private final Pose cycleGrabPosition = new Pose(13, 24, Math.toRadians(180));

    private final Pose cycleSwoopControl = new Pose (29.5,30,Math.toRadians(180));

    private final Pose cycleWallControl1 = new Pose(32,24);

    //use these to help cycle
    private final Pose score1Pos = new Pose(39, 62, Math.toRadians(0));
    private final Pose score2Pos = new Pose(39, 64, Math.toRadians(0));
    private final Pose score3Pos = new Pose(39, 66, Math.toRadians(0));

    private final Pose park = new Pose(25, 13, Math.toRadians(0));

    /* These are our Paths and PathChains that we will define in buildPaths() */
    private Path scorePreload;
    private PathChain pushBoth, Cycle1, Cycle2, Cycle3, Return1, Return2, Park;

    /** Build the paths for the auto (adds, for example, constant/linear headings while doing paths)
     * It is necessary to do this so that all the paths are built before the auto starts. **/
    public void buildPaths() {

        /* There are two major types of paths components: BezierCurves and BezierLines.
         *    * BezierCurves are curved, and require >= 3 points. There are the start and end points, and the control points.
         *    - Control points manipulate the curve between the start and end points.
         *    - A good visualizer for this is [this](https://pedro-path-generator.vercel.app/).
         *    * BezierLines are straight, and require 2 points. There are the start and end points.
         * Paths have can have heading interpolation: Constant, Linear, or Tangential
         *    * Linear heading interpolation:
         *    - Pedro will slowly change the heading of the robot from the startHeading to the endHeading over the course of the entire path.
         *    * Constant Heading Interpolation:
         *    - Pedro will maintain one heading throughout the entire path.
         *    * Tangential Heading Interpolation:
         *    - Pedro will follows the angle of the path such that the robot is always driving forward when it follows the path.
         * PathChains hold Path(s) within it and are able to hold their end point, meaning that they will holdPoint until another path is followed.
         * Here is a explanation of the difference between Paths and PathChains <https://pedropathing.com/commonissues/pathtopathchain.html> */

        /* This is our scorePreload path. We are using a BezierLine, which is a straight line. */
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePreloadPos)));
        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePreloadPos.getHeading());


        /* Here is an example for Constant Interpolation
        scorePreload.setConstantInterpolation(startPose.getHeading()); */

        /* This is our grabPickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        pushBoth = follower.pathBuilder()
                //get in front of first sample
                .addPath(new BezierCurve(new Point(scorePreloadPos), new Point(sample1control1), new Point(sample1control2), new Point(Sample1control3), new Point(sample1Pos)))
                .setLinearHeadingInterpolation(scorePreloadPos.getHeading(), sample1Pos.getHeading())
                //push it back to obzone
                .addPath(new BezierLine(new Point(sample1Pos), new Point(pushSample1Pos)))
                .setLinearHeadingInterpolation(sample1Pos.getHeading(), pushSample1Pos.getHeading())
                //go back for second sample with bez curve
                .addPath(new BezierCurve(new Point(pushSample1Pos),new Point(sample2control1), new Point(sample2Pos)))
                .setLinearHeadingInterpolation(pushSample1Pos.getHeading(), sample2Pos.getHeading())
                //Push 2
                .addPath(new BezierLine(new Point(sample2Pos), new Point(pushSample2Pos)))
                .setLinearHeadingInterpolation(sample2Pos.getHeading(), pushSample2Pos.getHeading())
                //Go to grab position with bez curve
                .addPath(new BezierCurve(new Point(pushSample2Pos), new Point(controlToGrabPos1), new Point(controlToGrabPos2), new Point(cycleGrabPosition)))
                .setLinearHeadingInterpolation(pushSample2Pos.getHeading(), preWallGrabPosition.getHeading())
                .addPath(new BezierLine(new Point(preWallGrabPosition), new Point(cycleGrabPosition)))
                .setLinearHeadingInterpolation(preWallGrabPosition.getHeading(), cycleGrabPosition.getHeading())
                .build();
        //finished...yay... mabe

        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        Cycle1 = follower.pathBuilder()

                .addPath(new BezierCurve(new Point(cycleGrabPosition), new Point(cycleSwoopControl), new Point(score1Pos)))
                .setLinearHeadingInterpolation(cycleGrabPosition.getHeading(), score1Pos.getHeading())
                .build();

        Return1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(score1Pos), new Point (cycleSwoopControl), new Point(preWallGrabPosition)))
                .setLinearHeadingInterpolation(score1Pos.getHeading(), preWallGrabPosition.getHeading())
                .addPath(new BezierLine( new Point (preWallGrabPosition), new Point(cycleGrabPosition)))
                .setLinearHeadingInterpolation(preWallGrabPosition.getHeading(), cycleGrabPosition.getHeading())
                .build();


        Cycle2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(cycleGrabPosition), new Point(cycleSwoopControl), new Point(score2Pos)))
                .setLinearHeadingInterpolation(cycleGrabPosition.getHeading(), score2Pos.getHeading())
                .build();

        Return2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(score2Pos), new Point (cycleSwoopControl), new Point(preWallGrabPosition)))
                .setLinearHeadingInterpolation(score2Pos.getHeading(), preWallGrabPosition.getHeading())
                .addPath(new BezierLine( new Point (preWallGrabPosition), new Point(cycleGrabPosition)))
                .setLinearHeadingInterpolation(preWallGrabPosition.getHeading(), cycleGrabPosition.getHeading())
                .build();


        Cycle3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(cycleGrabPosition), new Point(cycleSwoopControl), new Point(score3Pos)))
                .setLinearHeadingInterpolation(cycleGrabPosition.getHeading(), score3Pos.getHeading())
                .build();

        Park = follower.pathBuilder()
                .addPath(new BezierLine(new Point(score3Pos), new Point(park)))
                .setLinearHeadingInterpolation(score3Pos.getHeading(), park.getHeading())
                .build();



    }

    /** This switch is called continuously and runs the pathing, at certain points, it triggers the action state.
     * Everytime the switch changes case, it will reset the timer. (This is because of the setPathState() method)
     * The followPath() function sets the follower to run the specific path, but does NOT wait for it to finish before moving on. */
    public void autonomousPathUpdate() {
        int aboveBarHeight = 1700;
        int snapSpecimenHeight = 1185;
        int wallHeight = 143;
        switch (pathStateNumber) {
            case 0: //PRELOAD_SCORE_SETUP
                follower.followPath(scorePreload);
                plus.moveArmWhileSwoop(aboveBarHeight,1,0);
                setPathState(1);
                break;
            case 1: //PRELOAD_SCORE_CLICK and _RELEASE
                if(!follower.isBusy()) {
                    plus.moveArm(snapSpecimenHeight,1,0);
                    robot.outakeclawOpenClose("OPEN");
                    setPathState(2);
                }
                break;
            case 2:

                /* You could check for
                - Follower State: "if(!follower.isBusy() {}"
                - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
                - Robot Position: "if(follower.getPose().getX() > 36) {}"
                */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(pushBoth,true);
                    plus.moveArmWhileSwoop(wallHeight,1,0);
                    setPathState(3);
                }
                break;
            case 3:
                if(!follower.isBusy()) {
                    robot.outakeclawOpenClose("CLOSED");
                    actionTimer.resetTimer();
                    setPathState(4);
                }
                break;
            case 4:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy() && actionTimer.getElapsedTimeSeconds() >= 0.5) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.turnToDegrees(180);
                    follower.followPath(Cycle1,true);
                    plus.moveArmWhileSwoop(aboveBarHeight,1,0);
                    setPathState(5);
                }
                break;
            case 5:
                if(!follower.isBusy()) {
                    plus.moveArm(snapSpecimenHeight,1,0);
                    robot.outakeclawOpenClose("OPEN");
                    setPathState(6);
                }
                break;
            case 6:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    plus.moveArmWhileSwoop(wallHeight,1,0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(Return1,true);
                    setPathState(7);
                }
                break;
            case 7:
                if(!follower.isBusy()) {
                    robot.outakeclawOpenClose("CLOSED");
                    actionTimer.resetTimer();
                    setPathState(8);
                }
                break;
            case 8:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
                if(!follower.isBusy()  && actionTimer.getElapsedTimeSeconds() >= 0.5) {
                    plus.moveArm(aboveBarHeight,1,0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(Cycle2,true);
                    setPathState(9);
                }
                break;
            case 9:
                if(!follower.isBusy()) {
                    plus.moveArm(snapSpecimenHeight,1,0);
                    robot.outakeclawOpenClose("OPEN");
                    setPathState(10);
                }
                break;
            case 10:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    plus.moveArmWhileSwoop(wallHeight,1,0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(Return2,true);
                    setPathState(11);
                }
                break;
            case 11:
                if(!follower.isBusy()) {
                    robot.outakeclawOpenClose("CLOSED");
                    actionTimer.resetTimer();
                    setPathState(12);
                }
                break;
            case 12:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy() && actionTimer.getElapsedTimeSeconds() >= 0.5) {
                    /* Grab Sample */
                    plus.moveArmWhileSwoop(aboveBarHeight,1,0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(Cycle3, true);
                    setPathState(13);
                }
                break;
            case 13:
                if(!follower.isBusy()) {
                    plus.moveArm(snapSpecimenHeight,1,0);
                    robot.outakeclawOpenClose("OPEN");
                    setPathState(14);
                }
                break;
            case 14:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    plus.moveArmWhileSwoop(0,1,0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are parked */
                    follower.followPath(Park,true);
                    setPathState(15);
                }
                break;
            case 15:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Level 1 Ascent */

                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
                    setPathState(-1);
                }
                break;
        }
    }

    /** These change the states of the paths and actions
     * It will also reset the timers of the individual switches **/
    public void setPathState(int pState) {
        pathStateNumber = pState;
        pathTimer.resetTimer();
    }

    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
    @Override
    public void loop() {

        // These loop the movements of the robot
        follower.update();
        autonomousPathUpdate();

        // Feedback to Driver Hub
        telemetry.addData("path state", pathStateNumber);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

    /** This method is called once at the init of the OpMode. **/
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        actionTimer = new Timer();
        opmodeTimer.resetTimer();

        robot = new Robot(hardwareMap, telemetry, this);
        plus.runOpMode(robot);

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        follower.setMaxPower(1.0);
        robot.prepareAuto();
        buildPaths();
    }

    /** This method is called continuously after Init while waiting for "play". **/
    @Override
    public void init_loop() {}

    /** This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system **/
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        robot.slidesIn();
        setPathState(0);
    }

    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {
    }
}