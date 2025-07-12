package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LConstants {
    static {
        //TODO do some measurements
        PinpointConstants.forwardY = 1;
        //TODO do some measurements
        PinpointConstants.strafeX = -2.5;
        PinpointConstants.distanceUnit = DistanceUnit.INCH;
        PinpointConstants.hardwareMapName = "pinpoint";
        //TODO Decide whether or not to change
        PinpointConstants.useYawScalar = false;
        //TODO??
        PinpointConstants.yawScalar = 1.0;
        //TODO probably leave as is
        PinpointConstants.useCustomEncoderResolution = false;
        PinpointConstants.encoderResolution = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD;
        //TODO??
        PinpointConstants.customEncoderResolution = 13.26291192;
        //TODO set these to the correct reversal
        PinpointConstants.forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;
        PinpointConstants.strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
    }
}



