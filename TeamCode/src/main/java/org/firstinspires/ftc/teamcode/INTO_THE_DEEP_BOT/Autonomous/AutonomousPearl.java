package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous;
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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Robot;

/**
 * This is the autonomous mode. It moves the robot without us having to touch the controller.
 * Previous programmers really sucked at explaining what any of this meant, so we're trying to do better.
 * This is our third year now of using this file. It's kind of poetic and also adorable.
 */

public abstract class AutonomousPearl extends OpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();

    public double speed = 0.6;
    public int sleepTime;
    public boolean inMarker;
    public double power;
    public double slidePos;

    //DO NOT DELETE THIS LINE! CAPITALIZATION IS VERY IMPORTANT!!!
    public Robot robot = null;

    public void runOpMode(Robot ribbit) {
        robot = ribbit;
    }

    public void moveArm(int ticks, double power, long pause) {
        robot.liftyL.setPower(power);
        robot.liftyR.setPower(power);
        robot.liftyL.setTargetPosition(ticks);
        robot.liftyR.setTargetPosition(ticks);
        robot.liftyL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftyR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (!(robot.liftyR.getCurrentPosition() > (ticks - 10) && robot.liftyR.getCurrentPosition() < (ticks + 10)))
        {
            robot.tellMotorOutput();
        }
        //sleep(pause);
    }

    public void moveArmWhileSwoop(int ticks, double power, long pause) {
        robot.liftyL.setPower(power);
        robot.liftyR.setPower(power);
        robot.liftyL.setTargetPosition(ticks);
        robot.liftyR.setTargetPosition(ticks);
        robot.liftyL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftyR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void prepareNextAction(double stallTime) {
        double priorRuntime = getRuntime();
        while(getRuntime() < priorRuntime + stallTime)
        {
            telemetry.update();//lol stallin
        }
        return;
    }

    public void autoSlides(double change)
    {
        robot.leftSlide.setPosition(1-change);
        robot.rightSlide.setPosition(0+change);
        while(robot.leftSlide.getPosition() != 1-change) {
            robot.leftSlide.setPosition(1 - change);
            robot.rightSlide.setPosition(0 + change);
            robot.tellMotorOutput();
        }
    }
    public void pickupSample(int pickupTime, long pause) {
        // REQUIREMENTS TO USE FUNCTION (plz don't ignore):
        // Needs a wait before the function if the most recent movement was the slides
        // Function can't run until the servos get over their start-of-auto crisis (about 2.5 seconds). This is really obscure, but it might be a problem for somebody someday.
        double distance = robot.rightSlide.getPosition() + .20;
        robot.intake_spin(.75);
        prepareNextAction(.1);
        //robot.leftSlide.setPosition(1-(robot.leftSlide.getPosition()+.10));
        //robot.rightSlide.setPosition(0+(robot.leftSlide.getPosition()+.10));
        robot.intakeFlipper.setPosition(.15);
        prepareNextAction(0);
        autoSlides(distance);
        prepareNextAction((double) pickupTime / 1000);
        robot.intake_spin(0);
        prepareNextAction((double) pause / 1000);
    }
}
