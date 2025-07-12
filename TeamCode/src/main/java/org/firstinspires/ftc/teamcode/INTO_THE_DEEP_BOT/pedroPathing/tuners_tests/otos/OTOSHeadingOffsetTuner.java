package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.LConstants;

/**
 * This is a heading offset tuner for the SparkFun OTOS, which is a method of localization supported by PedroPathing.
 * It measures the change in the x and y values of the pose as the robot is pushed forward in a straight line
 * to return the true heading offset of the sensor.
 * <p></p>
 * This is adapted from the SparkFun OTOS tuner written by Jay of 12087 Capital City Dynamics for RoadRunner.
 *
 * @author Emily Claire Lorenzen - 13206 Wave Droids, modified from Jay - 12087 Capital City Dynamics
 * @version 1.0, 2/16/2025
 */

@TeleOp(name="OTOS Heading Offset Tuner", group="OTOS Tuning")
@Disabled
public class OTOSHeadingOffsetTuner extends LinearOpMode {

    private PoseUpdater poseUpdater;
    private double poseX;
    private double poseY;

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        poseUpdater = new PoseUpdater(hardwareMap);

        telemetry.addLine("OTOS Heading Offset Tuner");
        telemetry.addLine("Line the side of the robot against a wall and Press START.");
        telemetry.addLine("Then push the robot forward some distance.");
        telemetry.addLine("Finally, copy the heading offset into line 16 of LConstants");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            poseUpdater.update();

            poseX = poseUpdater.getPose().getX();
            poseY = poseUpdater.getPose().getY();

            telemetry.addData("Heading Offset (radians, enter this one into LConstants!)",Math.atan2(poseY,poseX));
            telemetry.addData("Heading Offset (degrees, just for fun)",Math.toDegrees(Math.atan2(poseY,poseX)));
            telemetry.update();
        }


    }
}


