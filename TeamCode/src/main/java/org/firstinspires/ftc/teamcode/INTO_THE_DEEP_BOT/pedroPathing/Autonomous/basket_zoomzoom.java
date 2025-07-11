package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.Autonomous;


import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPearl;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Robot;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.LConstants;


/**
 * This is an example auto that showcases movement and control of two servos autonomously.
 * It is a 0+4 (Specimen + Sample) bucket auto. It scores a neutral preload and then pickups 3 samples from the ground and scores them before parking.
 * There are examples of different ways to build paths.
 * A path progression method has been created and can advance based on time, position, or other factors.
 *
 * @author Baron Henderson - 20077 The Indubitables
 * @version 2.0, 11/28/2024
 */

@Autonomous(name = "Pedro's Incredible Basket Auto", group = "Pedro's Autos")
public class basket_zoomzoom extends OpMode {

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

    /** This is the variable where we store the state of our auto.
     * It is used by the pathUpdate method. */
    private int pathState;

    /* Create and Define Poses + Paths
     * Poses are built with three constructors: x, y, and heading (in Radians).
     * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
     * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
     * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
     * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
     * Lets assume our robot is 18 by 18 inches
     * Lets assume the Robot is facing the human player and we want to score in the bucket */

    /** Start Pose of our robot */
    private final Pose startPose = new Pose(9.5, 115.5, Math.toRadians(90));

    /** Scoring Pose of our robot */
    private final Pose scorePose = new Pose(16, 136.5, Math.toRadians(135));

    /** Lowest (First) Sample from the Spike Mark */
    private final Pose pickup1Pose = new Pose(22, 132, Math.toRadians(180));

    /** Middle (Second) Sample from the Spike Mark */
    private final Pose pickup2Pose = new Pose(22, 139.25, Math.toRadians(180));

    /** Highest (Third) Sample from the Spike Mark */
    private final Pose pickup3Pose = new Pose(30.5, 132, Math.toRadians(240));

    private final Pose preParkPose = new Pose(62, 105, Math.toRadians(270));
    /** Park Pose for our robot, after we do all of the scoring. */
    private final Pose parkPose = new Pose(62, 100.5, Math.toRadians(270));

    /** Park Control Pose for our robot, this is used to manipulate the bezier curve that we will create for the parking.
     * The Robot will not go to this pose, it is used a control point for our bezier curve. */
    private final Pose parkControlPose = new Pose(60, 150, Math.toRadians(270));

    /* These are our Paths and PathChains that we will define in buildPaths() */
    //private Path placeholder;
    private PathChain scorePreload, grabPickup1, grabPickup2, grabPickup3, scorePickup1, scorePickup2, scorePickup3, park;

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
        scorePreload = follower.pathBuilder()
                .addPath(new BezierLine(new Point(startPose), new Point(scorePose)))
                .setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading())
                .build();

        /* Here is an example for Constant Interpolation
        scorePreload.setConstantInterpolation(startPose.getHeading()); */

        /* This is our grabPickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        grabPickup1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(scorePose), new Point(pickup1Pose)))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup1Pose.getHeading())
                .build();

        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        scorePickup1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(pickup1Pose), new Point(scorePose)))
                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), scorePose.getHeading())
                .build();

        /* This is our grabPickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        grabPickup2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(scorePose), new Point(pickup2Pose)))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup2Pose.getHeading())
                .build();

        /* This is our scorePickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(pickup2Pose), new Point(scorePose)))
                .setLinearHeadingInterpolation(pickup2Pose.getHeading(), scorePose.getHeading())
                .build();

        /* This is our grabPickup3 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        grabPickup3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(scorePose), new Point(pickup3Pose)))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup3Pose.getHeading())
                .build();

        /* This is our scorePickup3 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        scorePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(pickup3Pose), new Point(scorePose)))
                .setLinearHeadingInterpolation(pickup3Pose.getHeading(), scorePose.getHeading())
                .build();

        /* This is our park path. We are using a BezierCurve with 3 points, which is a curved line that is curved based off of the control point */
        park = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(scorePose), new Point(parkControlPose), new Point(preParkPose)))
                .setLinearHeadingInterpolation(scorePose.getHeading(), preParkPose.getHeading())
                .addPath(new BezierLine(new Point(preParkPose), new Point(parkPose)))
                .setLinearHeadingInterpolation(preParkPose.getHeading(), parkPose.getHeading())
                .build();

        // This is the old code for the park path
        //park = new Path(new BezierCurve(new Point(scorePose), /* Control Point */ new Point(parkControlPose), new Point(parkPose)));
        //park.setLinearHeadingInterpolation(scorePose.getHeading(), parkPose.getHeading());
    }

    /** This switch is called continuously and runs the pathing, at certain points, it triggers the action state.
     * Everytime the switch changes case, it will reset the timer. (This is because of the setPathState() method)
     * The followPath() function sets the follower to run the specific path, but does NOT wait for it to finish before moving on. */
    public void autonomousPathUpdate() {
        int bottomHeight = 0;
        int basketHeight = 2600;
        double scoreTime = 1.5;
        double waitBeforeScoreTime = .5;
        int pickupTime = 1000;
        switch (pathState) {
            case 0:
                // We move to score the preloaded sample in the top basket
                plus.moveArmWhileSwoop(basketHeight,1,0);
                plus.autoSlides(.05);
                follower.followPath(scorePreload);
                actionTimer.resetTimer();
                setPathState(1);
                break;
            case 1:
                // Score preloaded sample
                if(!follower.isBusy() && pathTimer.getElapsedTimeSeconds() >= waitBeforeScoreTime) {
                    robot.tempOutakePos("UP");
                    actionTimer.resetTimer();
                    setPathState(2);
                }
                break;
            case 2:
                // Lower arm and go to pick up the first pickup
                if(!follower.isBusy() && actionTimer.getElapsedTimeSeconds() >= scoreTime) {
                    robot.tempOutakePos("DOWN");
                    follower.followPath(grabPickup1,true);
                    plus.moveArmWhileSwoop(bottomHeight,1,0);
                    setPathState(3);
                }
                break;
            case 3:
                // Pickup
                if(!follower.isBusy()) {
                    plus.autoSlides(.20);
                    plus.pickupSample(pickupTime,0);
                    robot.TransferSequence();
                    setPathState(4);
                }
                break;
            case 4:
                // Move to score
                if(!follower.isBusy()) {
                    follower.followPath(scorePickup1,true);
                    plus.moveArmWhileSwoop(basketHeight,1,0);
                    setPathState(5);
                }
                break;
            case 5:
                // Score
                if(!follower.isBusy() && pathTimer.getElapsedTimeSeconds() >= waitBeforeScoreTime) {
                    robot.tempOutakePos("UP");
                    actionTimer.resetTimer();
                    setPathState(6);
                }
                break;
            case 6:
                // Move to pickup
                if(!follower.isBusy() && actionTimer.getElapsedTimeSeconds() >= scoreTime) {
                    follower.followPath(grabPickup2,true);
                    robot.tempOutakePos("DOWN");
                    plus.moveArmWhileSwoop(bottomHeight,1,0);
                    setPathState(7);
                }
                break;
            case 7:
                // Pickup
                if(!follower.isBusy()) {
                    plus.autoSlides(.20);
                    plus.pickupSample(pickupTime,0);
                    robot.TransferSequence();
                    setPathState(8);
                }
                break;
            case 8:
                // Move to score
                if(!follower.isBusy()) {
                    follower.followPath(scorePickup2,true);
                    plus.moveArmWhileSwoop(basketHeight,1,0);
                    setPathState(9);
                }
                break;
            case 9:
                // Score
                if(!follower.isBusy() && pathTimer.getElapsedTimeSeconds() >= waitBeforeScoreTime) {
                    robot.tempOutakePos("UP");
                    actionTimer.resetTimer();
                    setPathState(10);
                }
                break;
            case 10:
                // Move to pickup
                if(!follower.isBusy() && actionTimer.getElapsedTimeSeconds() >= scoreTime) {
                    follower.followPath(grabPickup3,true);
                    robot.tempOutakePos("DOWN");
                    plus.moveArmWhileSwoop(bottomHeight,1,0);
                    setPathState(11);
                }
                break;
            case 11:
                // Pickup
                if(!follower.isBusy()) {
                    plus.autoSlides(.35);
                    plus.pickupSample(pickupTime + 1000,0);
                    robot.TransferSequence();
                    setPathState(12);
                }
                break;
            case 12:
                // Move to score
                if(!follower.isBusy()) {
                    follower.followPath(scorePickup3, true);
                    plus.moveArmWhileSwoop(basketHeight,1,0);
                    setPathState(13);
                }
                break;
            case 13:
                // Score
                if(!follower.isBusy() && pathTimer.getElapsedTimeSeconds() >= waitBeforeScoreTime) {
                    robot.tempOutakePos("UP");
                    actionTimer.resetTimer();
                    setPathState(14);
                }
                break;
            case 14:
                // Park
                if(!follower.isBusy() && actionTimer.getElapsedTimeSeconds() >= scoreTime) {
                    follower.followPath(park,true);
                    plus.moveArmWhileSwoop(75,1,0);
                    setPathState(15);
                }
                break;
            case 15:
                // Touch bar
                if(!follower.isBusy()) {
                    plus.autoSlides(.5);
                    setPathState(-1);
                }
                break;
        }
    }

    /** These change the states of the paths and actions
     * It will also reset the timers of the individual switches **/
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
    @Override
    public void loop() {

        // These loop the movements of the robot
        follower.update();
        autonomousPathUpdate();

        // Feedback to Driver Hub
        telemetry.addData("path state", pathState);
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
        follower.setMaxPower(0.8);
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
        setPathState(0);
    }

    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {
    }
}
