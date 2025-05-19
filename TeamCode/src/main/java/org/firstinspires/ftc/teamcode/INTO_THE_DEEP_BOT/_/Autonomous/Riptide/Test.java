package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Riptide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.io.File;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide.Playback;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide.RipConfig;

@Autonomous(group = "Basic", name = "Riptide-Test")
public class Test extends AutonomousPLUS {

    public String currentPosition;
    public String target;
    public RipConfig rip;
    public Playback playback;
    public File file = null;// need to get path to working file


    public void runOpMode() {

        super.runOpMode();

        //Initalize the scripts, then set the file via this script
        rip = new RipConfig(robot);
        playback = new Playback(robot,rip, file);
        playback.getRecordingFile(" 2025-05-13_20-46-40.json");
        playback.cacheFile();

        waitForStart();
        playback.runFrames(1, 327);//lol lets see if it works


    }
}