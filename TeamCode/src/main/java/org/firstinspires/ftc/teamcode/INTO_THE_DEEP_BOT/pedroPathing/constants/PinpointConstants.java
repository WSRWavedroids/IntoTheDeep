package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.pedroPathing.constants;

import static com.pedropathing.localization.GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This is the PinpointConstants class. It holds many constants and parameters for the Pinpoint Localizer.
 * @author Baron Henderson - 20077 The Indubitables
 * @version 1.0, 12/24/2024
 */

@Config
public class PinpointConstants {

    /*
    VERY IMPORTANT NOTE (of Great Importance)
    The variables in this file are set in LConstants.java
    This is changed from the original version of this file
    */

    /** The Y Offset of the Forward Encoder (Deadwheel) from the center of the robot in DistanceUnit
     * @see #distanceUnit
     * Default Value: 1 */
    public static double forwardY;

    /** The X Offset of the Strafe Encoder (Deadwheel) from the center of the robot in DistanceUnit
     * @see #distanceUnit
     * Default Value: -2.5 */
    public static double strafeX;

    /** The Unit of Distance that the Pinpoint uses to measure distance
     * Default Value: DistanceUnit.INCH */
    public static DistanceUnit distanceUnit;

    /** The name of the Pinpoint in the hardware map (name of the I2C port it is plugged into)
     * Default Value: "pinpoint" */
    public static String hardwareMapName;

    /** Use custom yaw scalar for the Pinpoint (overrides the calibration of the Pinpoint
     * Default Value: false */
    public static boolean useYawScalar;

    /** Custom Yaw Scalar for the Pinpoint (overrides the calibration of the Pinpoint
     * @see #useYawScalar
     * Default Value: 1.0 */
    public static double yawScalar;

    /** Use custom encoder resolution for the Pinpoint
     * Default Value: false */
    public static boolean useCustomEncoderResolution;

    /** The Encoder Resolution for the Pinpoint. Used if useCustomEncoderResolution is false
     * @see #useCustomEncoderResolution
     * Default Value: GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD */
    public static GoBildaPinpointDriver.GoBildaOdometryPods encoderResolution = goBILDA_SWINGARM_POD;

    /** The Encoder Resolution for the Pinpoint. Only used if useCustomEncoderResolution is true
     * @see #useCustomEncoderResolution
     * Default Value: 13.26291192 */
    public static double customEncoderResolution;

    /** The Encoder Direction for the Forward Encoder (Deadwheel)
     * Default Value: GoBildaPinpointDriver.EncoderDirection.REVERSED */
    public static GoBildaPinpointDriver.EncoderDirection forwardEncoderDirection;

    /** The Encoder Direction for the Strafe Encoder (Deadwheel)
     * Default Value: GoBildaPinpointDriver.EncoderDirection.FORWARD */
    public static GoBildaPinpointDriver.EncoderDirection strafeEncoderDirection;
}