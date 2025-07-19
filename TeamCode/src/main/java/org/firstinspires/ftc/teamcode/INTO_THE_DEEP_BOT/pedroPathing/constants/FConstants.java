package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = "frontLeftDrive";
        FollowerConstants.leftRearMotorName = "backLeftDrive";
        FollowerConstants.rightFrontMotorName = "frontRightDrive";
        FollowerConstants.rightRearMotorName = "backRightDrive";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 3.40194;
//TODO
        FollowerConstants.xMovement = 0;
        FollowerConstants.yMovement = 0;
//TODO
        FollowerConstants.forwardZeroPowerAcceleration = 0;
        FollowerConstants.lateralZeroPowerAcceleration = 0;
//TODO
        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0,0,0,0);
        FollowerConstants.useSecondaryTranslationalPID = false;
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0,0,0,0);
//TODO
        FollowerConstants.headingPIDFCoefficients.setCoefficients(0,0,0,0);
        FollowerConstants.useSecondaryHeadingPID = false;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(0,0,0,0);
//TODO
        FollowerConstants.drivePIDFCoefficients.setCoefficients(0,0,0,0,0);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0,0,0,0,0);
//TODO
        FollowerConstants.zeroPowerAccelerationMultiplier = 0;
        FollowerConstants.centripetalScaling = 0;
//TODO
        FollowerConstants.maxPower = 1;
//TODO
        FollowerConstants.pathEndTimeoutConstraint = 0;
        FollowerConstants.pathEndTValueConstraint = 0;
        FollowerConstants.pathEndVelocityConstraint = 0;
        FollowerConstants.pathEndTranslationalConstraint = 0;
        FollowerConstants.pathEndHeadingConstraint = 0;
    }
}
