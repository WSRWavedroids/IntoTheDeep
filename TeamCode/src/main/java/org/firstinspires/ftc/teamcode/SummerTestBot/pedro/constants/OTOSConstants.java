package org.firstinspires.ftc.teamcode.SummerTestBot.pedro.constants;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class OTOSConstants {
    public static boolean useCorrectedOTOSClass = false;
    public static String hardwareMapName = "sensor_otos";
    public static DistanceUnit linearUnit;
    public static AngleUnit angleUnit;
    public static SparkFunOTOS.Pose2D offset;
    public static double linearScalar;
    public static double angularScalar;
    public static double lateralScalar;

    public OTOSConstants() {
    }

    static {
        linearUnit = DistanceUnit.INCH;
        angleUnit = AngleUnit.RADIANS;
        offset = new SparkFunOTOS.Pose2D(0.0, 0.0, 1.5707963267948966);
        linearScalar = 1.0;
        angularScalar = 1.0;
        lateralScalar = 1.0;
    }
}
