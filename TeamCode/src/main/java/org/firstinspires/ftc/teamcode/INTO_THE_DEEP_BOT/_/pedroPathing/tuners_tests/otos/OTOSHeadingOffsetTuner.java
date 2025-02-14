package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.LConstants;

@TeleOp(name="OTOS Heading Offset Tuner", group="OTOS Tuning")
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
            poseY = poseUpdater.getPose().getX();

            telemetry.addData("Heading Offset (radians, enter this one into LConstants!)",Math.atan2(poseY,poseX));
            telemetry.addData("Heading Offset (degrees)",Math.toDegrees(Math.atan2(poseY,poseX)));
            telemetry.update();
        }


    }
}


