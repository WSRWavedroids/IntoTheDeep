package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

public class AutonomousPlatinum extends LinearOpMode {

    //DO NOT DELETE THIS LINE! CAPITALIZATION IS VERY IMPORTANT!!!
    public Robot robot = null;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    /*******************************************************************************
     * ROADRUNNER ACTIONS STUFF
     *******************************************************************************/

    public class Drivetrain implements Action {
        public Action followTrajectory(Trajectory t) {
            Action inst = null;
            return inst;
        }

        public Action turn(double angle) {
            Action inst = null;
            return inst;
        }

        public Action moveToPoint(double x, double y) {
            Action inst = null;
            return inst;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            return false;
        }
    }

    public class Slides implements Action {

        public class slideUp implements Action {
            // checks if the lift motor has been powered on
            private boolean initialized = false;

            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on
                if (!initialized) {
                    robot.lifty.setPower(0.8);
                    initialized = true;
                }

                // checks lift's current position
                double pos = robot.lifty.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 3000.0) {
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.lifty.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 3000 encoder ticks, then powers it off
            }
        }


        public Action slideUp(double distance){
            Action inst = null;
            return new slideUp;
        }

        public Action slideDown(double distance){
            Action inst = null;
            return inst;
        }

        public Action openCloseClaw(double position){
            Action inst = null;
            return inst;
        }

        public Action moveWrist(double position){
            Action inst = null;
            return inst;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            return false;
        }
    }

    public class GroundIntake implements Action {

        public Action spinIntake(){
            Action inst = null;
            return inst;
        }

        public Action spinOuttake(){
            Action inst = null;
            return inst;
        }

        public Action flipWrist(double position){
            Action inst = null;
            return inst;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            return false;
        }
    }

}
