package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Autonomous.Riptide;

import android.os.Environment;

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
    ///public File file = null;// need to get path to working file


    public void runOpMode() {




        super.runOpMode();

        //file = new File(Environment.getExternalStorageDirectory(), "FIRST/recordings/2025-05-20_19-20-21.json");
        //Initalize the scripts, then set the file via this script
        rip = new RipConfig(robot);
        playback = new Playback(robot,rip);
        //playback.setRecordingFile("2025-05-20_19-20-21.json");
        playback.setAbsoluteFilePath("/storage/emulated/0/recordings/Recording: 2025-06-10_20-12-18.json");
        playback.cacheFile();

        waitForStart();
        playback.runFrames(0, 327);//lol lets see if it works


    }
}