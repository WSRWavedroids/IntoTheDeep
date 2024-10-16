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

package org.firstinspires.ftc.teamcode.OLD.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OLD.ChonkRobot;


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

@TeleOp(name="EZ DRIVE CHONK", group="CompBot")
public class REEEEEEEALLLY_Basic_TeleOp_CHONK extends OpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();
    private double speed = 0.75;
    //private double storedSpeed;
    public ChonkRobot chonkRobot = null;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {

        // Call the initialization protocol from the Robot class.
        chonkRobot = new ChonkRobot(hardwareMap, telemetry, this);

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
        gamepad1.setLedColor(0, 0, 255, 100000000);
        gamepad2.setLedColor(0, 0, 255, 100000000);
        // This little section updates the driver hub on the runtime and the motor powers.
        // It's mostly used for troubleshooting.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        chonkRobot.tellMotorOutput();

        float armStickY = this.gamepad2.left_stick_y;

        if (gamepad2.left_stick_y < -0.5){
            chonkRobot.slideL.setPower(-armStickY * 0.75);
            chonkRobot.slideR.setPower(-armStickY * 0.75);
        } else if (gamepad2.left_stick_y > 0.5){
            chonkRobot.slideL.setPower(-armStickY * 0.75);
            chonkRobot.slideR.setPower(-armStickY * 0.75);
        } else {
            chonkRobot.holdArm();
        }

        if(gamepad2.dpad_up)
        {
            gamepad2.rumble(800);
            gamepad2.setLedColor(255, 0, 0, 10000);
            gamepad1.rumble(800);
            gamepad1.setLedColor(255, 0, 0, 10000);
            chonkRobot.firePlane(400);
        }
        gamepad1.setLedColor(0, 0, 255, 100000000);
        gamepad2.setLedColor(0, 0, 255, 100000000);

        //windshield wiper motion... hopefully
        double idealPosition;
        double rightClosedPosition = .6;
        double leftClosedPosition = .4;
        if (chonkRobot.primaryClawClosed == true)
        {
            idealPosition = gamepad2.right_stick_x * 0.135;
            chonkRobot.openAndCloseRightClaw(rightClosedPosition -= idealPosition);
            chonkRobot.openAndCloseLeftClaw(leftClosedPosition -= idealPosition);
        }

        //Moves the turntable based on the x-coordinate of the right joystick
        //We need to switch out these motor functions for servo stuff... Idk the position we need
        if (gamepad2.y){ // up
            //robot.armL.setPosition(1);
            //robot.rotateLeftArm(0.59); // good no change
            chonkRobot.rotateArmUp();

        } else if (gamepad2.x) { //lower
            //robot.rotateLeftArm(0.85); // GOOD NO CHANGE
            //robot.rotateLeftArm(0.6);
            chonkRobot.rotateArmDown();
        }
        /*else {
            robot.armR.setPosition(0);
            robot.armL.setPosition(0);
        }
        */


        //other possible code is this without this
        if (this.gamepad2.b || this.gamepad2.left_trigger > 0.5) { // open
            chonkRobot.openClaw();
        } else if (this.gamepad2.a || this.gamepad2.right_trigger > 0.5) {//close
            chonkRobot.closeClaw();
        }



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop () { telemetry.addData("Status", "Robot Stopped"); }


    /*
     * The holding cell for all of the random functions we call above.
     */

    public void setIndividualPowers ( float[] motorPowers){
        // This function creates an array so that the function below works.
        // Don't mess with this function unless you know what you're doing.

        if (motorPowers.length != 4) {
            return;
        }
        chonkRobot.frontLeftDrive.setPower(-motorPowers[0]);
        chonkRobot.frontRightDrive.setPower(-motorPowers[1]);
        chonkRobot.backLeftDrive.setPower(-motorPowers[2]);
        chonkRobot.backRightDrive.setPower(-motorPowers[3]);
    }

    private void singleJoystickDrive () {
        // We don't really know how this function works, but it makes the wheels drive, so we don't question it.
        // Don't mess with this function unless you REALLY know what you're doing.

        float rightX = -this.gamepad1.right_stick_x;
        float leftY = this.gamepad1.left_stick_y;
        float leftX = -this.gamepad1.left_stick_x;

        double leftStickAngle = Math.atan2(leftY, leftX);
        double leftStickMagnitude = Math.sqrt(leftX * 2.0 + leftY * 2.0);
        //double robotAngle = robot.imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;

        if (leftStickMagnitude > 1){
            leftStickMagnitude = 1;
        }

        float[] motorPowers = new float[4];

        if (chonkRobot.controlMode == "Robot Centric") {

            motorPowers[0] = (leftY + leftX + rightX);
            motorPowers[1] = (leftY - leftX - rightX);
            motorPowers[2] = (leftY - leftX + rightX);
            motorPowers[3] = (leftY + leftX - rightX);

        } else if (chonkRobot.controlMode == "Field Centric") {
            /*
            motorPowers[0] = (float) (Math.sin(leftStickAngle + 45 - robotAngle) * leftStickMagnitude + rightX);
            motorPowers[1] = (float) (Math.sin(leftStickAngle - 45 - robotAngle) * leftStickMagnitude + rightX);
            motorPowers[2] = (float) (Math.sin(leftStickAngle - 45 - robotAngle) * leftStickMagnitude + rightX);
            motorPowers[3] = (float) (Math.sin(leftStickAngle + 45 - robotAngle) * leftStickMagnitude + rightX);
            */
        }

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
