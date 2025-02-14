package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.tuners_tests.otos;

import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.pedroPathing.constants.LConstants;

@TeleOp(name="OTOS Angular Scalar Tuner", group="OTOS Tuning")

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
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            poseUpdater.update();
            degreesTurned = poseUpdater.getPose().getHeading(); //TODO: Fix this from being an infinite adding thing
            //radsTurned += drive.pose.heading.minus(lastHeading);
            //lastHeading = drive.pose.heading;
            telemetry.addData("Degrees Turned", Math.toDegrees(degreesTurned));
            telemetry.addData("Calculated Angular Scalar", 3600 / Math.toDegrees(degreesTurned));
            telemetry.update();
        }


    }
}
