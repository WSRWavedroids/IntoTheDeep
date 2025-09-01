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
 * This file is our "Build issue detector" it allows the build, drive, and programming teams to find issues with faulty controllers, unplugged wires, and incorrectly mounted wheels.
 * This script only looks at the drivetrain and controllers, so you may need to adapt it for your own robot
 *
 *
 * Using the face buttons the robot should move perfectly in one direction... if not its an issue with wheels or motors
 * In addition to this, telemetry allows us to see if servos and other hardware are being told to move but aren't (wiring issue)
 *
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
        telemetry.addData("Its ", "Never a code issue"); // Lol... it is sometimes
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

        //Controllers
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


        if (gamepad1.right_trigger >= 0.5) {

            speed = 1;

        } else if (gamepad1.left_trigger > 0.5) {
            speed = 0.50;
        }

        //By using buttons to move in a direction, we can look at movement without stick drift or human error while pushing the stick
        if (gamepad1.triangle)//forward
        {
            moveDirection("Forward");
        } else if (gamepad1.cross)
        {
            moveDirection("Backward");
        } else if (gamepad1.circle)
        {
            moveDirection("Left");
        } else if (gamepad1.square)
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


}
