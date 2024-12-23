package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

public class AutonomousPlatinum extends LinearOpMode {

    //DO NOT DELETE THIS LINE! CAPITALIZATION IS VERY IMPORTANT!!!
    public static Robot robot = null;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    /*******************************************************************************
     * SLIDES ACTIONS (INCLUDES SPECIMEN CLAW)
     *******************************************************************************/

    public static class Slides {

        public static class slideUpToMidPos implements Action {
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
                if (pos < 1500) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.lifty.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 1500 encoder ticks, then powers it off
            }
        }

        public static class slideUpToHighPos implements Action {
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
                if (pos < 2500) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.lifty.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 2500 encoder ticks, then powers it off
            }
        }

        public static class slideUpToWallPos implements Action {
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
                if (pos < 230) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.lifty.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 230 encoder ticks, then powers it off
            }
        }

        public static class slideDownToNoPos implements Action {
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
                if (pos > 5) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.lifty.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it goes down to
                // 5 encoder ticks, then powers it off
            }
        }

        public static class closeClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.outakeclawOpenClose("CLOSED");
                return false;
            }
        }

        public static class openClaw implements Action{
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.outakeclawOpenClose("OPEN");
                return false;
            }
        }

        public static Action slideUpToMidPos(double distance){
            return new slideUpToMidPos();
        }

        public static Action slideUpToHighPos(double distance){
            return new slideUpToHighPos();
        }

        public static Action slideUpToWallPos(double distance){
            return new slideUpToWallPos();
        }

        public static Action slideDownToNoPos(double distance){
            return new slideDownToNoPos();
        }

        public static Action closeClaw(double position){
            return new closeClaw();
        }

        public static Action openClaw(double position){
            return new openClaw();
        }

        public static Action moveWrist(double position){
            Action inst = null;
            return inst;

            //I'm dealing with this nonsense later...
        }

    }

    /*******************************************************************************
     * GROUND INTAKE ACTIONS
     *******************************************************************************/

    public static class GroundIntake {

        public static class spinHawk implements Action{
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.intake_spin(1);
                return false;
            }
        }

        public static class spinTuah implements Action{
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.intake_spin(-1);
                return false;
            }
        }

        public static Action spinIntake(){
            return new spinHawk();
        }

        public static Action spinOuttake(){
            return new spinTuah();
        }

        public static Action flipWrist(double position){
            Action inst = null;
            return inst;

            //Also dealing with this nonsense later...
        }

    }

}
