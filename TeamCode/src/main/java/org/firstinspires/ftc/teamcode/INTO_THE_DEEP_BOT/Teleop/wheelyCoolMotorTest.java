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

package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.SummerTestBot.Basic_Strafer_Bot;


/**
 * This file is our iterative (Non-Linear) "OpMode" for TeleOp.
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is selected on the Robot Controller and executed.
 * This particular one is called "Lean Mean TeleOp Machine". I had a little too much fun with naming this.
 *
 * This OpMode controls the functions of the robot during the driver-controlled period.
 *
 * If the "@Disabled" line is not commented out, the program will not show up on the driver hub.
 * If you ever have problems with the program not showing up on the driver hub, it's probably because of that.
 *
 * Throughout this program, there are comments explaining what everything does because previous programmers
 * did a horrible job of doing that.
 */

@TeleOp(name="Build issue detector", group="CompBot")
public class wheelyCoolMotorTest extends OpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();
    private double speed = 0.75;
    //private double storedSpeed;
    public Basic_Strafer_Bot Bot = new Basic_Strafer_Bot();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {

        // Call the initialization protocol from the Robot class.
        Bot = new Basic_Strafer_Bot(hardwareMap, telemetry, this);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    public void init_loop() {
        telemetry.addData("Prepare to be: ", "WRONG!");
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    public void start() {
        runtime.reset();
        telemetry.addData("Its ", "Never a code issue");
        gamepad1.setLedColor(0, 0, 255, 100000000);
        gamepad2.setLedColor(0, 0, 255, 100000000);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {

        // This little section updates the driver hub on the runtime and the motor powers.
        // It's mostly used for troubleshooting.
        telemetry.addData("Status", "Run Time: " + runtime.toString());


        telemetry.addData("Gamepad 1 Left: ", "Value X: " + gamepad1.left_stick_x, "Value Y: " + gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Right: ", "Value X: " + gamepad1.right_stick_x, "Value Y: " + gamepad1.right_stick_y);
        telemetry.addData("Gamepad 2 Left: ", "Value X: " + gamepad2.left_stick_x, "Value Y: " + gamepad2.left_stick_y);
        telemetry.addData("Gamepad 2 Left: ", "Value X: " + gamepad2.right_stick_x, "Value Y: " + gamepad2.right_stick_y);
        Bot.tellMotorOutput();

        //Driver 1
        if (gamepad1.back) {
            if (Bot.controlMode == "Robot Centric") {
                Bot.controlMode = "Field Centric";
                telemetry.addData("Control Mode", "Field Centric Controls");
            } else if (Bot.controlMode == "Field Centric") {
                Bot.controlMode = "Robot Centric";
                telemetry.addData("Control Mode", "Robot Centric Controls");
            }
        }

        if (gamepad1.dpad_up) {
            speed = 1;
        } else if (gamepad1.dpad_down) {
            speed = 0.25;
        } else if (gamepad1.dpad_left) {
            speed = 0.5;
        } else if (gamepad1.dpad_right) {
            speed = 0.75;
        }

        if (speed == 1) {
            telemetry.addData("Speed", "Fast Boi");
        } else if (speed == 0.5) {
            telemetry.addData("Speed", "Slow Boi");
        } else if (speed == 0.25) {
            telemetry.addData("Speed", "Super Slow Boi");
        } else if (speed == 0.75) {
            telemetry.addData("Speed", "Normal Boi");
        }


        //Beginning of fast turn

        if (gamepad1.right_trigger >= 0.5) {
            //storedSpeed = speed;
            speed = 1;
            //Do something
            //speed = storedSpeed;

        } else if (gamepad1.left_trigger > 0.5) {
            //storedSpeed = speed;
            speed = 0.50;
            //Do something
            //speed = storedSpeed;
        }
        //

        if (gamepad1.triangle)//forward
        {
            moveDirection("Forward");
        } else if (gamepad1.cross)//backward
        {
            moveDirection("Backward");
        } else if (gamepad1.circle)//left
        {
            moveDirection("Left");
        } else if (gamepad1.square)//right
        {
            moveDirection("Right");
        } else if(gamepad1.right_bumper)
        {
            moveDirection("TurnRight");
        }
        else if(gamepad1.left_bumper)
        {
            moveDirection("TurnLeft");
        }


        else {
            stopPls();
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        telemetry.addData("Status", "Robot Stopped");
    }

    public void moveDirection(String Direction)
    {
       if(Direction == "Forward")
       {
           Bot.frontLeftDrive.setPower(speed);
           Bot.frontRightDrive.setPower(speed);
           Bot.backLeftDrive.setPower(speed);
           Bot.backRightDrive.setPower(speed);
       }else if(Direction == "Backward")
       {
           Bot.frontLeftDrive.setPower(-speed);
           Bot.frontRightDrive.setPower(-speed);
           Bot.backLeftDrive.setPower(-speed);
           Bot.backRightDrive.setPower(-speed);
       }
       else if(Direction == "Left")
       {
           Bot.frontLeftDrive.setPower(speed);
           Bot.frontRightDrive.setPower(-speed);
           Bot.backLeftDrive.setPower(-speed);
           Bot.backRightDrive.setPower(speed);
       }else if(Direction == "Right")
       {
           Bot.frontLeftDrive.setPower(-speed);
           Bot.frontRightDrive.setPower(speed);
           Bot.backLeftDrive.setPower(speed);
           Bot.backRightDrive.setPower(-speed);
       }
       else if(Direction == "TurnRight")
       {
           Bot.frontLeftDrive.setPower(speed);
           Bot.frontRightDrive.setPower(-speed);
           Bot.backLeftDrive.setPower(speed);
           Bot.backRightDrive.setPower(-speed);
       }
       else if(Direction == "TurnLeft")
       {
           Bot.frontLeftDrive.setPower(-speed);
           Bot.frontRightDrive.setPower(speed);
           Bot.backLeftDrive.setPower(-speed);
           Bot.backRightDrive.setPower(speed);
       }
    }

    public void stopPls()
    {
        Bot.frontLeftDrive.setPower(0);
        Bot.frontRightDrive.setPower(0);
        Bot.backLeftDrive.setPower(0);
        Bot.backRightDrive.setPower(0);
    }
    /*
     * The holding cell for all of the random functions we call above.
     */

}
