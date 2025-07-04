package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;
@Disabled
@Autonomous(group = "Basic", name = "Stupid Test")
public class stupid_test extends AutonomousPLUS {

    /*public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();


        Follower follower;
        Timer pathTimer;
        Timer actionTimer;
        Timer opmodeTimer;

        /** This is the variable where we store the state of our auto.
         * It is used by the pathUpdate method. */
        int pathState;

        /* Create and Define Poses + Paths
         * Poses are built with three constructors: x, y, and heading (in Radians).
         * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
         * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
         * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
         * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
         * Lets assume our robot is 18 by 18 inches
         * Lets assume the Robot is facing the human player and we want to score in the bucket

        /** Start Pose of our robot
        final Pose startPose = new Pose(2.75, 58, Math.toRadians(0)); // Basket parking is x = 2.75, y=109

        /** Scoring Pose of our robot. It is facing the submersible at a -45 degree (315 degree) angle.
        final Pose scorePose = new Pose(14, 129, Math.toRadians(315));

        /** Lowest (First) Sample from the Spike Mark
        final Pose pickup1Pose = new Pose(37, 121, Math.toRadians(0));

        /** Middle (Second) Sample from the Spike Mark
        final Pose pickup2Pose = new Pose(43, 130, Math.toRadians(0));

        /** Highest (Third) Sample from the Spike Mark
        final Pose pickup3Pose = new Pose(49, 135, Math.toRadians(0));

        /** Park Pose for our robot, after we do all of the scoring.
        final Pose parkPose = new Pose(60, 98, Math.toRadians(90));

        /** Park Control Pose for our robot, this is used to manipulate the bezier curve that we will create for the parking.
         * The Robot will not go to this pose, it is used a control point for our bezier curve.
        final Pose parkControlPose = new Pose(60, 98, Math.toRadians(90));

        /* These are our Paths and PathChains that we will define in buildPaths()
        Path scorePreload;
        Path park;
        PathChain grabPickup1, grabPickup2, grabPickup3, scorePickup1, scorePickup2, scorePickup3;

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

            /* This is our scorePreload path. We are using a BezierLine, which is a straight line.
            scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
            scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading());

        /* Here is an example for Constant Interpolation
        scorePreload.setConstantInterpolation(startPose.getHeading()); */

            /* This is our grabPickup1 PathChain. We are using a single path with a BezierLine, which is a straight line.
            grabPickup1 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(scorePose), new Point(pickup1Pose)))
                    .setLinearHeadingInterpolation(scorePose.getHeading(), pickup1Pose.getHeading())
                    .build();

            /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line.
            scorePickup1 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(pickup1Pose), new Point(scorePose)))
                    .setLinearHeadingInterpolation(pickup1Pose.getHeading(), scorePose.getHeading())
                    .build();

            /* This is our grabPickup2 PathChain. We are using a single path with a BezierLine, which is a straight line.
            grabPickup2 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(scorePose), new Point(pickup2Pose)))
                    .setLinearHeadingInterpolation(scorePose.getHeading(), pickup2Pose.getHeading())
                    .build();

            /* This is our scorePickup2 PathChain. We are using a single path with a BezierLine, which is a straight line.
            scorePickup2 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(pickup2Pose), new Point(scorePose)))
                    .setLinearHeadingInterpolation(pickup2Pose.getHeading(), scorePose.getHeading())
                    .build();

            /* This is our grabPickup3 PathChain. We are using a single path with a BezierLine, which is a straight line.
            grabPickup3 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(scorePose), new Point(pickup3Pose)))
                    .setLinearHeadingInterpolation(scorePose.getHeading(), pickup3Pose.getHeading())
                    .build();

            /* This is our scorePickup3 PathChain. We are using a single path with a BezierLine, which is a straight line.
            scorePickup3 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(pickup3Pose), new Point(scorePose)))
                    .setLinearHeadingInterpolation(pickup3Pose.getHeading(), scorePose.getHeading())
                    .build();

            /* This is our park path. We are using a BezierCurve with 3 points, which is a curved line that is curved based off of the control point
            park = new Path(new BezierCurve(new Point(scorePose), /* Control Point  new Point(parkControlPose), new Point(parkPose)));
            park.setLinearHeadingInterpolation(scorePose.getHeading(), parkPose.getHeading());
        };

        waitForStart();





    }
    */
}}