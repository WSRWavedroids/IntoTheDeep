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

package org.firstinspires.ftc.teamcode.Outreach;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file is our iterative (Non-Linear) "OpMode" for TeleOp.
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is selected on the Robot Controller and executed.
 * <p>
 * This OpMode controls the functions of the robot during the driver-controlled period.
 * <p>
 * If the "@Disabled" line is not commented out, the program will not show up on the driver hub.
 * If you ever have problems with the program not showing up on the driver hub, it's probably because of that.
 * <p>
 * Throughout this program, there are comments explaining what everything does. If there's anything you're confused about, don't hesitate to ask! :)
 */

//This sets this op mode as a TeleOp op mode, gives it a name, and gives it a group
@TeleOp(name="TeleOp For Starmont", group="Outreach")

//Because this extends the OpMode class, it automatically comes with a bunch of functions we can call.
public class Starmont_Bot_Tele_Op extends OpMode {

    // This creates a timer. We currently don't do anything with this, but it is an option.
    private ElapsedTime runtime = new ElapsedTime();
    //This sets the base speed of the drivetrain. You can change this at any time, either on this line or by a button press while running the program.
    double speed = 0.75;
    //This creates a special kind of variable called an instance of the class with all of the holding functions.
    //This allows you to keep a lot of messy stuff in a different file and make this one easier to read.
    public Starmont_Bot robot;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {

        //We initialized the variable above, so now we're giving it a value.
        robot = new Starmont_Bot(hardwareMap, telemetry, this);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    public void init_loop() {telemetry.addData("HYPE", "ARE! YOU! READY?!?!?!?!");}

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    public void start() {
        runtime.reset();
        telemetry.addData("HYPE", "Let's do this!!!");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {

        singleJoystickDrive();

        telemetry.addData("Status", "Run Time: " + runtime.toString());

        // Do stuff based on button input. You know what you're doing here :)

        if (gamepad2.left_stick_y >= 0.1){
            robot.arm.setPower(0.5);
        } else if (gamepad2.left_stick_y <= -0.1){
            robot.arm.setPower(-0.5);
        } else {
            robot.arm.setPower(0);
        }

        if (gamepad2.a){
            robot.leftjaw.setPosition(1);
            robot.rightjaw.setPosition(1);
            //meow THIS OPENS IT :3
        }
       else if (gamepad2.b){
            robot.leftjaw.setPosition(0);
            robot.rightjaw.setPosition(0);
           //CLOSES IT!!!!!!!!!!!
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop () { telemetry.addData("Status", "Robot Stopped"); }




    /*
     * The holding cell for some of the random functions we call above. You don't have to do anything with these.
     */

    //If the robot is not controlling as it should, your motors might be reversed.
    //The quick and easy fix is to try adding/removing negative signs from some of the following lines (which are noted).

    public void setIndividualPowers ( float[] motorPowers){
        // This function creates an array so that the function below works.
        // Don't mess with this function unless you know what you're doing.

        if (motorPowers.length != 4) {
            return;
        }
        robot.frontleftwheel.setPower(motorPowers[0]);      /* Can be reversed */
        robot.frontrightwheel.setPower(motorPowers[1]);      /* Can be reversed */
        robot.backleftwheel.setPower(motorPowers[2]);      /* Can be reversed */
        robot.backrightwheel.setPower(motorPowers[3]);      /* Can be reversed */
    }

    private void singleJoystickDrive () {
        // A good explanation of how this function works: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html

        float rightX = this.gamepad1.right_stick_x;      /* Can be reversed */
        float leftY = -this.gamepad1.left_stick_y;      /* Can be reversed */
        float leftX = this.gamepad1.left_stick_x;      /* Can be reversed */

        float[] motorPowers = new float[4];

            motorPowers[0] = (leftY + leftX + rightX);      /* Can be reversed */
            motorPowers[1] = (leftY - leftX - rightX);      /* Can be reversed */
            motorPowers[2] = (leftY - leftX + rightX);      /* Can be reversed */
            motorPowers[3] = (leftY + leftX - rightX);      /* Can be reversed */


        float max = getLargestAbsVal(motorPowers);
        if (max < 1) {
            max = 1;
        }

        for (int i = 0; i < motorPowers.length; i++) {
            motorPowers[i] *= (speed / max);

            float abs = Math.abs(motorPowers[i]);
            if (abs < 0.05) {
                motorPowers[i] = 0.0f;
            }
            if (abs > 1.0) {
                motorPowers[i] /= abs;
            }
        }

        setIndividualPowers(motorPowers);

    }

    private float getLargestAbsVal ( float[] values){
        // This function does some math!
        float max = 0;
        for (float val : values) {
            if (Math.abs(val) > max) {
                max = Math.abs(val);
            }
        }
        return max;
    }

}
