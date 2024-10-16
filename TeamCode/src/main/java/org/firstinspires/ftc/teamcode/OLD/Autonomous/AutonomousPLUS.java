package org.firstinspires.ftc.teamcode.OLD.Autonomous;
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OLD.ChonkRobot;
//import org.firstinspires.ftc.teamcode.OLD.Autonomous.AprilTags.MayFlowers;

/**
 * This is the autonomous mode. It moves the robot without us having to touch the controller.
 * Previous programmers really sucked at explaining what any of this meant, so we're trying to do better.
 * This is our third year now of using this file. It's kind of poetic and also adorable.
 */

public class AutonomousPLUS extends LinearOpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();


    public double speed = 0.6;
    public int sleepTime;
    public boolean inMarker;
    public double power;
    public double slidePos;

    //DO NOT DELETE THIS LINE! CAPITALIZATION IS VERY IMPORTANT!!!
    public ChonkRobot chonkRobot = null;

    @Override
    public void runOpMode() {
        chonkRobot = new ChonkRobot(hardwareMap, telemetry, this);
    }

    //These are the basic functions for mechnum movement during auto... Don't mess with these unless something is inverted
    // Remember Without ODO pods there will be some inconsistency due to mechnum slippage
    public void moveRobotForward(int ticks, long pause) {
        if (opModeIsActive()) {
            chonkRobot.setTargets("Backward", ticks); // Inverted... Lol
            chonkRobot.positionRunningMode();
        }
        chonkRobot.powerSet(speed);

        while (opModeIsActive() && chonkRobot.isWheelsBusy()) {
            chonkRobot.tellMotorOutput();
        }

        chonkRobot.stopAllMotors();
        chonkRobot.encoderRunningMode();
        chonkRobot.stopAllMotors();
        sleep(pause);
        chonkRobot.encoderReset();
    }

    public void moveRobotBackward(int ticks, long pause) {
        if (opModeIsActive()) {
            chonkRobot.setTargets("Forward", ticks);
            chonkRobot.positionRunningMode();
            chonkRobot.powerSet(speed);

            while (opModeIsActive() && chonkRobot.isWheelsBusy()) {
                chonkRobot.tellMotorOutput();
            }

            chonkRobot.stopAllMotors();
            chonkRobot.encoderRunningMode();
            sleep(pause);
            chonkRobot.encoderReset();
        }

    }

    public void moveRobotLeft(int ticks, long pause) {

        if (opModeIsActive()) {
            chonkRobot.setTargets("Right", ticks);
            chonkRobot.positionRunningMode();
            chonkRobot.powerSet(speed);

            while (opModeIsActive() && chonkRobot.isWheelsBusy()) {
                chonkRobot.tellMotorOutput();
            }

            chonkRobot.stopAllMotors();
            chonkRobot.encoderRunningMode();
            sleep(pause);
            chonkRobot.encoderReset();
        }
    }

    public void moveRobotRight(int ticks, long pause) {

        if (opModeIsActive()) {
            chonkRobot.setTargets("Left", ticks);
            chonkRobot.positionRunningMode();
            chonkRobot.powerSet(speed);

            while (opModeIsActive() && chonkRobot.isWheelsBusy()) {
                chonkRobot.tellMotorOutput();
            }

            chonkRobot.stopAllMotors();
            chonkRobot.encoderRunningMode();
            sleep(pause);
            chonkRobot.encoderReset();
        }
    }

    public void turnRobotRight(int ticks, long pause) {

        if (opModeIsActive()) {
            chonkRobot.setTargets("Turn Right", ticks);
            chonkRobot.positionRunningMode();
            chonkRobot.powerSet(speed);

            while (opModeIsActive() && chonkRobot.isWheelsBusy()) {
                chonkRobot.tellMotorOutput();
            }

            chonkRobot.stopAllMotors();
            chonkRobot.encoderRunningMode();
            sleep(pause);
            chonkRobot.encoderReset();
        }
    }

    public void turnRobotLeft(int ticks, long pause) {

        if (opModeIsActive()) {
            chonkRobot.setTargets("Turn Left", ticks);
            chonkRobot.positionRunningMode();
            chonkRobot.powerSet(speed);

            while (opModeIsActive() && chonkRobot.isWheelsBusy()) {
                chonkRobot.tellMotorOutput();
            }

            chonkRobot.stopAllMotors();
            chonkRobot.encoderRunningMode();
            sleep(pause);
            chonkRobot.encoderReset();

        }
    }

    public void prepareNextAction(long pause) {
        sleep(pause);
        chonkRobot.encoderReset();
    }

    public void moveLift(String direction, double power) {
        if (direction == "Up") {
            chonkRobot.slideL.setDirection(DcMotor.Direction.FORWARD);
            chonkRobot.slideL.setPower(0.75);

            chonkRobot.slideR.setDirection(DcMotor.Direction.REVERSE);
            chonkRobot.slideR.setPower(0.75);
            sleep(sleepTime);
            chonkRobot.slideL.setPower(0.1);
            chonkRobot.slideR.setPower(0.1);
            sleep(500);
        } else if (direction == "Down") {
            chonkRobot.slideL.setDirection(DcMotor.Direction.REVERSE);
            chonkRobot.slideL.setPower(0.5);
            chonkRobot.slideR.setDirection(DcMotor.Direction.FORWARD);
            chonkRobot.slideR.setPower(0.5);
        }
    }

    public void armPID(){

        double Kp = 5;
        double Ki = 0;
        double Kd = 0.2;

        double reference = slidePos;
        float encoderPositionL = chonkRobot.slideL.getCurrentPosition();
        float encoderPositionR = chonkRobot.slideR.getCurrentPosition();
        double integralSumL = 0;
        double integralSumR = 0;
        double lastErrorL = 0;
        double lastErrorR = 0;

        ElapsedTime timer = new ElapsedTime();

        while (encoderPositionL != slidePos) {

            // calculate the error
            double errorL = reference - encoderPositionL;
            double errorR = reference - encoderPositionR;

            // rate of change of the error
            double derivativeL = (errorL - lastErrorL) / timer.seconds();
            double derivativeR = (errorR - lastErrorR) / timer.seconds();

            // sum of all error over time
            integralSumL = integralSumL + (errorL * timer.seconds());

            double outL = (Kp * errorL) + (Ki * integralSumL) + (Kd * derivativeL);
            double outR = (Kp * errorR) + (Ki * integralSumR) + (Kd * derivativeR);

            chonkRobot.slideL.setPower(outL);
            chonkRobot.slideR.setPower(outR);

            lastErrorL = errorL;
            lastErrorR = errorR;

            // reset the timer for next time
            timer.reset();

        }
    }
}
