package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.RoadRunner;

import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;

public interface Localizer {
    Twist2dDual<Time> update();
}