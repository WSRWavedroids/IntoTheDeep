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

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Robot;


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

@TeleOp(name=" STEEVE", group="CompBot")
public class Basic_TeleOp_NewBot extends OpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();
    private double speed = 0.75;
    //private double storedSpeed;
    public Robot robot = null;
    public IMU imu;

    public boolean teleopRunning = false; //Things for multithreading
    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {

        // Call the initialization protocol from the Robot class.
        robot = new Robot(hardwareMap, telemetry, this);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

        if (robot.controlMode=="Field Centric")
        {
              imu = hardwareMap.get(IMU.class, "imu");
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                    RevHubOrientationOnRobot.UsbFacingDirection.RIGHT)); //Forward = left fsr
            // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
            imu.initialize(parameters);
        }
        //if using field centric youl need this lolzeez

        //Multithreading: creates a thread for controller 2
        Thread controller2Thread = new Thread(() -> {
            while (opModeIsActive()) {

            }
        });

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
        gamepad1.setLedColor(0, 0, 255, 100000000);
        gamepad2.setLedColor(0, 0, 255, 100000000);
        robot.tempOutakePos("DOWN");
        robot.slidesIn();
        robot.intakePosition("UP");
        teleopRunning = true;
        OpMode.Basic_TeleOp_NewBot.init().controller2Thread.start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {

        singleJoystickDrive();
        // This little section updates the driver hub on the runtime and the motor powers.
        // It's mostly used for troubleshooting.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        robot.tellMotorOutput();

        float armStickY = this.gamepad2.left_stick_y;


        // This section checks what buttons on the Dpad are being pressed and changes the speed accordingly.
        //So Begins the input chain. At least try a bit to organise by driver

        //Driver 1
        if (gamepad1.back) {
            if (robot.controlMode == "Robot Centric"){
                robot.controlMode = "Field Centric";
                telemetry.addData("Control Mode", "Field Centric Controls");
            } else if (robot.controlMode == "Field Centric"){
                robot.controlMode = "Robot Centric";
                telemetry.addData("Control Mode", "Robot Centric Controls");
            }
        }

        if (gamepad1.dpad_up || gamepad1.right_trigger >= 0.5) {
            speed = 1;
        } else if (gamepad1.dpad_down) {
            speed = 0.25;
        } else if (gamepad1.dpad_left || gamepad1.left_trigger >0.5) {
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

        /*//Driver 2 Starts here
        //Lift
        if (gamepad2.left_stick_y < -0.5){
            robot.lifty.setPower(armStickY);
        } else if (gamepad2.left_stick_y > 0.5){
            robot.lifty.setPower(armStickY * 0.75);
        } else {
            robot.holdArm();
        }*/

        //int liftyTopLimit = 4100;//temp value
        //int liftyBottomLimit = -20;//temp value

        int liftyTopLimit = 4100;//temp value
        int liftyBottomLimit = -20;//temp value
            int liftyGoControlerVal = robot.liftyL.getCurrentPosition() - ((int) armStickY * 260);
            robot.liftyR.setPower(1);
            robot.liftyL.setPower(1);
            robot.liftyR.setTargetPosition(liftyGoControlerVal);
            robot.liftyL.setTargetPosition(liftyGoControlerVal);

            if (robot.liftyL.getCurrentPosition() > liftyTopLimit || liftyGoControlerVal > liftyTopLimit) {
                robot.liftyR.setTargetPosition(liftyTopLimit);
                robot.liftyL.setTargetPosition(liftyTopLimit);
            } else if (robot.liftyL.getCurrentPosition() < liftyBottomLimit || liftyGoControlerVal < liftyBottomLimit) {
                robot.liftyR.setTargetPosition(liftyBottomLimit);
                robot.liftyL.setTargetPosition(liftyBottomLimit);
            }



            robot.liftyR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.liftyL.setMode(DcMotor.RunMode.RUN_TO_POSITION);





        /*if(Math.abs(gamepad2.left_stick_y) > 0.1)
        {
            robot.teleopEncoderMode = false;
            robot.teleopPowerMode = true;
            double CadenVertSlideSense = .75;
            robot.liftyL.setPower(-gamepad2.left_stick_y * CadenVertSlideSense);
            robot.liftyR.setPower(-gamepad2.left_stick_y * CadenVertSlideSense);
        }


        if(Math.abs(gamepad2.left_stick_y) < .1 && robot.teleopPowerMode == true)
        {
            robot.holdArm();
        }*/

        if(gamepad2.left_bumper)
        {
            robot.tempOutakePos("UP");
        }
        else if(gamepad2.right_bumper)
        {
            robot.tempOutakePos("DOWN");
        }

        //intake
        if(gamepad2.dpad_down)
        {
            robot.intake_spin(-1);
        }
        else if(gamepad2.dpad_left)
        {
            robot.intake_spin(1);
        }
        else
        {
            robot.intake_spin(0);
        }


        if(gamepad2.x)
        {
            robot.intakePosition("UP");
            robot.slidesIn();
        }
        else if (gamepad2.y)
        {
            if(transferSequencePlsWork.getState() == transferSequencePlsWork.Wai)
        }

        if (robot.canWiggle == true && Math.abs(gamepad2.right_stick_y) > 0)
        {
            if(gamepad2.right_stick_y >= 0.05)
            {
                robot.intakeFlipper.setPosition(robot.intakeFlipper.getPosition() + 0.05 * -gamepad2.right_stick_y);
            }
            else if (gamepad2.right_stick_y <= 0.05)
            {
                robot.intakeFlipper.setPosition(robot.intakeFlipper.getPosition() - 0.05 * gamepad2.right_stick_y);
            }

        }

        float slideSum = gamepad2.right_trigger - gamepad2.left_trigger;
        double sensModifier = .045f;
        if(Math.abs(slideSum) > .1)
        {
            double left = (robot.leftSlide.getPosition() + ((double)slideSum * sensModifier));
            double right = (robot.rightSlide.getPosition() - ((double)slideSum * sensModifier));

            if(left < .25)
            {
                left = .25;
            }
            if (right > .75)
            {
                right = .75;
            }

            robot.rightSlide.setPosition(right);
            robot.leftSlide.setPosition(left);
        }


        //outake

        if(gamepad2.cross)
        {
            robot.outakeclawOpenClose("CLOSED");
        }
        else if (gamepad2.circle)
        {
            robot.outakeclawOpenClose("OPEN");
        }


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop () {
        telemetry.addData("Status", "Robot Stopped");
        Basic_TeleOp_NewBot.init().controller2Thread
    }


    /*
     * The holding cell for all of the random functions we call above.
     */

    public void setIndividualPowers ( float[] motorPowers){
        // This function creates an array so that the function below works.
        // Don't mess with this function unless you know what you're doing.

        if (motorPowers.length != 4) {
            return;
        }
        robot.frontLeftDrive.setPower(-motorPowers[0]);
        robot.frontRightDrive.setPower(-motorPowers[1]);
        robot.backLeftDrive.setPower(-motorPowers[2]);
        robot.backRightDrive.setPower(-motorPowers[3]);
    }

    private void singleJoystickDrive () {
        // We don't really know how this function works, but it makes the wheels drive, so we don't question it.
        // Don't mess with this function unless you REALLY know what you're doing.
        float leftY = -this.gamepad1.left_stick_y;
        float rightX = this.gamepad1.right_stick_x;
        float leftX = this.gamepad1.left_stick_x;

        double leftStickAngle = Math.atan2(leftY, leftX);
        double leftStickMagnitude = Math.sqrt(leftX * 2.0 + leftY * 2.0);
        //double robotAngle = robot.imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;

        if (leftStickMagnitude > 1){
            leftStickMagnitude = 1;
        }

        float[] motorPowers = new float[4];

        if (robot.controlMode == "Robot Centric") {

            motorPowers[0] = (leftY + leftX + rightX);//might need inverted back
            motorPowers[1] = (leftY - leftX - rightX);
            motorPowers[2] = (leftY - leftX + rightX);
            motorPowers[3] = (leftY + leftX - rightX);

        } else if (robot.controlMode == "Field Centric") {
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = leftX * Math.cos(-botHeading) - leftY * Math.sin(-botHeading);
            double rotY = leftX * Math.sin(-botHeading) + leftY * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rightX), 1);
            double frontLeftPower = (rotY + rotX + rightX) / denominator; //all of the right xs got inverted
            double backLeftPower = (rotY - rotX - rightX) / denominator;
            double frontRightPower = (rotY - rotX + rightX) / denominator;
            double backRightPower = (rotY + rotX - rightX) / denominator;



            motorPowers[0] = (float)frontLeftPower;
            motorPowers[1] = (float) backLeftPower;
            motorPowers[2] = (float)frontRightPower;
            motorPowers[3] = (float) backRightPower;
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

    /*Thread transferSequencePlsWork = new Thread(()->{
        robot.TransferSequence();
        Thread.currentThread().interrupt();


    });*/

}
