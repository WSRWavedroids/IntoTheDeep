package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.LConstants;

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