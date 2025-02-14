package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.LConstants;

@TeleOp(name="OTOS Linear Scalar Tuner", group="OTOS Tuning")
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



