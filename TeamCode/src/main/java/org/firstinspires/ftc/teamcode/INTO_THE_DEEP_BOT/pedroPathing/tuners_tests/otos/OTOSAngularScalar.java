package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants.LConstants;

/**
 * This is an angular scalar tuner for the SparkFun OTOS, which is a method of localization supported by PedroPathing.
 * It measures the change in pose heading from the robot over the course of 10 full rotations (3600 degrees)
 * to calculate the angular scalar value for the sensor.
 * <p></p>
 * This is adapted from the SparkFun OTOS tuner written by Jay of 12087 Capital City Dynamics for RoadRunner.
 *
 * @author Emily Claire Lorenzen - 13206 Wave Droids, modified from Jay - 12087 Capital City Dynamics
 * @version 1.0, 2/16/2025
 */

@TeleOp(name="OTOS Angular Scalar Tuner", group="OTOS Tuning")
@Disabled
public class OTOSAngularScalar extends LinearOpMode {

    private PoseUpdater poseUpdater;

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class, LConstants.class);
        poseUpdater = new PoseUpdater(hardwareMap);

        double degreesTurned = 0;

        telemetry.addLine("OTOS Angular Scalar Tuner");
        telemetry.addLine("Press START, then rotate the robot on the ground 10 times (3600 degrees).");
        telemetry.addLine("Then copy the scalar into LConstants.");
        telemetry.addLine("For best results, run multiple times then take the average.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            poseUpdater.update();

            degreesTurned = poseUpdater.getTotalHeading();

            telemetry.addData("Degrees Turned", Math.toDegrees(degreesTurned));
            telemetry.addData("Calculated Angular Scalar", 3600 / Math.toDegrees(degreesTurned));
            telemetry.update();
        }


    }
}
