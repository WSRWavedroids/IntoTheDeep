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
     * SLIDES ACTIONS (INCLUDES SPECIMEN CLAW)
     *******************************************************************************/

    public class Slides {

        public class slideUpToMidPos implements Action {
            // checks if the lift motor has been powered on
            private boolean initialized = false;

            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on
                if (!initialized) {
                    robot.liftyL.setPower(0.8);
                    robot.liftyR.setPower(0.8);
                    initialized = true;
                }

                // checks lift's current position
                double pos = robot.liftyL.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 1500) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.liftyL.setPower(0);
                    robot.liftyR.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 1500 encoder ticks, then powers it off
            }
        }

        public class slideUpToHighPos implements Action {
            // checks if the lift motor has been powered on
            private boolean initialized = false;

            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on
                if (!initialized) {
                    robot.liftyL.setPower(0.8);
                    robot.liftyR.setPower(0.8);
                    initialized = true;
                }

                // checks lift's current position
                double pos = robot.liftyL.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 2500) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.liftyL.setPower(0);
                    robot.liftyR.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 2500 encoder ticks, then powers it off
            }
        }

        public class slideUpToWallPos implements Action {
            // checks if the lift motor has been powered on
            private boolean initialized = false;

            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on
                if (!initialized) {
                    robot.liftyR.setPower(0.8);
                    robot.liftyL.setPower(0.8);
                    initialized = true;
                }

                // checks lift's current position
                double pos = robot.liftyL.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 230) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.liftyL.setPower(0);
                    robot.liftyR.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 230 encoder ticks, then powers it off
            }
        }

        public class slideDownToNoPos implements Action {
            // checks if the lift motor has been powered on
            private boolean initialized = false;

            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on
                if (!initialized) {
                    robot.liftyR.setPower(0.8);
                    robot.liftyL.setPower(0.8);
                    initialized = true;
                }

                // checks lift's current position
                double pos = robot.liftyL.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > 5) { //todo Find a way to make this customizable because this is annoying af
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    robot.liftyL.setPower(0);
                    robot.liftyR.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it goes down to
                // 5 encoder ticks, then powers it off
            }
        }

        public class closeClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.outakeclawOpenClose("CLOSED");
                return false;
            }
        }

        public class openClaw implements Action{
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.outakeclawOpenClose("OPEN");
                return false;
            }
        }

        public Action slideUpToMidPos(double distance){
            return new slideUpToMidPos();
        }

        public Action slideUpToHighPos(double distance){
            return new slideUpToHighPos();
        }

        public Action slideUpToWallPos(double distance){
            return new slideUpToWallPos();
        }

        public Action slideDownToNoPos(double distance){
            return new slideDownToNoPos();
        }

        public Action closeClaw(double position){
            return new closeClaw();
        }

        public Action openClaw(double position){
            return new openClaw();
        }

    }

    /*******************************************************************************
     * GROUND INTAKE ACTIONS
     *******************************************************************************/

    public class GroundIntake {

        public class spinHawk implements Action{
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.intake_spin(1);
                return false;
            }
        }

        public class spinTuah implements Action{
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                robot.intake_spin(-1);
                return false;
            }
        }

        public Action spinIntake(){
            return new spinHawk();
        }

        public Action spinOuttake(){
            return new spinTuah();
        }

        public Action flipWrist(double position){
            Action inst = null;
            return inst;

            //Also dealing with this nonsense later...
        }

    }

}
