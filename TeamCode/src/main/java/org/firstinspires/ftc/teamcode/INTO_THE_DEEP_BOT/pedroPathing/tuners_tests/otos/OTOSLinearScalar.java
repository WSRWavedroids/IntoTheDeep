package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.LConstants;

/**
 * This is a linear scalar tuner for the SparkFun OTOS, which is a method of localization supported by PedroPathing.
 * It measures the change in the x value of the pose as the robot is pushed forward 100 inches
 * to calculate the linear scalar for the sensor.
 * If the value for "inches moved" does not seem to change, make sure your robot is being pushed along the positive x-axis
 * (blue audience side to red audience side).
 * <p></p>
 * This is inspired by the SparkFun OTOS tuners written by Jay of 12087 Capital City Dynamics for RoadRunner.
 *
 * @author Emily Claire Lorenzen - 13206 Wave Droids
 * @version 1.0, 2/16/2025
 */

@TeleOp(name="OTOS Linear Scalar Tuner", group="OTOS Tuning")
@Disabled
public class OTOSLinearScalar extends LinearOpMode{

    private PoseUpdater poseUpdater;
    private double poseX;

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        poseUpdater = new PoseUpdater(hardwareMap);

        telemetry.addLine("OTOS Linear Scalar Tuner");
        telemetry.addLine("Press START, then push the robot on the ground 100 inches directly forward.");
        telemetry.addLine("Then copy the scalar into line 17 of LConstants.");
        telemetry.addLine("For best results, run multiple times then take the average.");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            poseUpdater.update();

            poseX = poseUpdater.getPose().getX();

            telemetry.addData("Inches Moved", poseX);
            telemetry.addData("Calculated Linear Scalar", 100 / poseX);
            telemetry.update();
        }


    }

}



