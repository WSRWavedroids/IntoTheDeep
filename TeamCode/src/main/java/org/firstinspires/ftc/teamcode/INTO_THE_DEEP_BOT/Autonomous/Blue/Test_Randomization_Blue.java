package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Limelight_Randomization_Scanner;

@Autonomous(group = "Basic", name = "Decode Auto")
public class Test_Randomization_Blue extends AutonomousPLUS {

    public Limelight_Randomization_Scanner Limelight = new Limelight_Randomization_Scanner();
    public String currentPosition;
    public String pattern;

    public void runOpMode() {

        super.runOpMode();

        if(opModeInInit())
        {
            Limelight.InitLimeLight(0, robot.hardwareMap);
            while(opModeInInit())
            {
                pattern = Limelight.GetRandomization();
                telemetry.addData(pattern, " Works!");
                telemetry.update();

            }
        }

        waitForStart();
        telemetry.addData("Our pattern is: ", pattern, " ...yay");
        prepareAuto();
        if(pattern == "PPG")
        {
            telemetry.addData("We doin", " PPG now");
        }
        else if(pattern == "GPP")
        {
            telemetry.addData("We doin", " GPP now");
        } else if (pattern  == "PGP")
        {
            telemetry.addData("We doin", " PGP now");
        }
        else
        {
            telemetry.addData("It failed: ", "Cry Time");
        }
        telemetry.update();

        sleep(1000000000);

    }
}