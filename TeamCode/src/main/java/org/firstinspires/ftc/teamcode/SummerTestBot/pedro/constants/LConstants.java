package org.firstinspires.ftc.teamcode.SummerTestBot.pedro.constants;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LConstants {
    static {
        OTOSConstants.useCorrectedOTOSClass = false;
        OTOSConstants.hardwareMapName = "sparkFunSparkJoy";
        OTOSConstants.linearUnit = DistanceUnit.INCH;
        OTOSConstants.angleUnit = AngleUnit.RADIANS;
       //        OTOSConstants.offset = new SparkFunOTOS.Pose2D(-5.23, -0.4866, 4.69769); // Heading isn't 0? y = -0.4866, x=-5.23
        OTOSConstants.offset = new SparkFunOTOS.Pose2D(-6.38, -.2043, 4.69769); // Heading isn't 0? y = -0.4866, x=-5.23
        OTOSConstants.linearScalar = 0.9573793193;
        OTOSConstants.angularScalar = 1;
}}



