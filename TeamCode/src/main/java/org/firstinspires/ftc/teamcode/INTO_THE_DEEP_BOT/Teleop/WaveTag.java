package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Teleop;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class WaveTag {
    //WaveTag is a custom class for moving the selected tag's 3d info between scripts

    int tagID;

    boolean currentlyDetected;

    double distanceZ;
    double distanceY;
    double distanceX;

    double angleX;
    double angleY;

    Pose3D data;


}
