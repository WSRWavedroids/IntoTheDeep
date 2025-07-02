package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;

@Autonomous(group = "Basic", name = "DrivetrainTest")
public class DrivetrainTest extends AutonomousPLUS {

    public String currentPosition;
    public String target;

    public void runOpMode() {

        speed = .6;
        robot.powerSet(speed);
        int ticks = 3000;
        //Tests each drivetrain motor induvidually. All should move forward
        robot.frontLeftDrive.setTargetPosition(-ticks + robot.frontLeftDrive.getCurrentPosition());
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && robot.isWheelsBusy()) {
            robot.tellMotorOutput();
        }
        robot.stopAllMotors();
        prepareNextAction(1000);

        robot.frontRightDrive.setTargetPosition(-ticks + robot.frontRightDrive.getCurrentPosition());
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && robot.isWheelsBusy()) {
            robot.tellMotorOutput();
        }
        robot.stopAllMotors();
        prepareNextAction(1000);

        robot.backLeftDrive.setTargetPosition(-ticks - robot.backLeftDrive.getCurrentPosition());
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && robot.isWheelsBusy()) {
            robot.tellMotorOutput();
        }
        robot.stopAllMotors();
        prepareNextAction(1000);

        robot.backRightDrive.setTargetPosition(-ticks - robot.backRightDrive.getCurrentPosition());
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && robot.isWheelsBusy()) {
            robot.tellMotorOutput();
        }
        robot.stopAllMotors();
        prepareNextAction(1000);

        moveRobotForward(1000, 1000);
    }
}