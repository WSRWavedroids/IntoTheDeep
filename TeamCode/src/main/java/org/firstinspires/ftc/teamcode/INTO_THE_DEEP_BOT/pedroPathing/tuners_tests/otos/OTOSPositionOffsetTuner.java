package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.LConstants;

/**
 * This is a pose offset tuner for the SparkFun OTOS, which is a method of localization supported by PedroPathing.
 * It measures the change in the x and y values of the pose as the robot is rotated 180 degrees
 * to return the true offset of the sensor from the dead center of the robot.
 * <p></p>
 * This is adapted from the SparkFun OTOS tuner written by Jay of 12087 Capital City Dynamics for RoadRunner.
 *
 * @author Emily Claire Lorenzen - 13206 Wave Droids, modified from Jay - 12087 Capital City Dynamics
 * @version 1.0, 2/16/2025
 */


@TeleOp(name="OTOS Pose Offset Tuner", group="OTOS Tuning")
public class OTOSPositionOffsetTuner extends LinearOpMode {

    private PoseUpdater poseUpdater;
    private double poseX;
    private double poseY;
    private double poseHeading;

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        poseUpdater = new PoseUpdater(hardwareMap);
        telemetry.addLine("OTOS Pose Offset Tuner");
        telemetry.addLine("Line the robot against the corner of two walls facing forward and Press START.");
        telemetry.addLine("Then rotate the robot exactly 180 degrees and press it back into the corner.");
        telemetry.addLine("Finally, copy the pose offset into line 16 of LConstants.");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            poseUpdater.update();

            poseX = poseUpdater.getPose().getX();
            poseY = poseUpdater.getPose().getY();
            poseHeading = poseUpdater.getPose().getHeading();

            telemetry.addData("Heading (deg)",Math.toDegrees(poseHeading));
            if (Math.abs(Math.toDegrees(poseHeading)) > 175) {
                telemetry.addLine("Add these values to line 16 of LConstants!");
                telemetry.addData("X Offset", poseX / 2);
                telemetry.addData("Y Offset", poseY / 2);
            } else {
                telemetry.addLine("Rotate the robot 180 degrees and align it to the corner again.");
            }
            telemetry.update();
        }


    }
}