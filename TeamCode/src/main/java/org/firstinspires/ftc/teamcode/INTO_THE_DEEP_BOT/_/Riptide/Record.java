package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Environment;


import org.firstinspires.ftc.ftccommon.internal.manualcontrol.commands.AnalogCommands;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

public class Record {
//This is the general record class

    public Robot robot;
    public OpMode opmode;
    public Telemetry telemetry;
    public HardwareMap hardwareMap;
    public RipConfig rip;
    public TeleOp_Recording recSource;

    public int framesIn = 1;
    public String fileName;
    public boolean acceptingFrames;

    public List<Frame> cachedFrames;

    public File File;

    //This constructor populates the null scripts once everything is initalized
    public Record(Robot robot, RipConfig rip) {
        this.robot = robot;
        this.rip = rip;
        this.telemetry = robot.telemetry;
        this.hardwareMap = robot.hardwareMap;
        this.opmode = robot.opmode;

        //this.recSource = recSource;

    }


        public void startRecording()
        {
            fileName = ("Recording: " + rip.grabTime());
            recordFrame();
        }

        public void recordFrame()
        {
            long timeStamp = System.currentTimeMillis();
            Frame frame = new Frame(framesIn, timeStamp);



            //Open File to new entry
            telemetry.addLine("Current Frame: " + framesIn);

            //Motors
            telemetry.addLine("Motors");
            for (DcMotorEx i: rip.ripMotors)
            {
                //String name = motor.getDeviceName();
                //
                telemetry.addLine(i.getDeviceName() + " Velocity is:" + i.getVelocity());
            }
            //Servos
            telemetry.addLine("Servos");
            for (Servo i: rip.ripServos)
            {
                telemetry.addLine(i.getDeviceName() + " Position is:"+ i.getPosition());
                //
                //
            }
            //CR Servo
            telemetry.addLine("CR Servos");
            for (CRServo i: rip.ripCRServos)
            {
                //
                //
                telemetry.addLine(i.getDeviceName() + " Direction is:"+ i.getDirection() + " Power is:"+i.getPower());
            }

            //*cachedFrames.add(frame);

            // Feedback
            //telemetry.addLine("Cached Frame: " + framesIn);
            telemetry.update();

            framesIn++;
        }

        public void dumpRecording(String fileName, String jsonString)
        {
            try {
                // Make sure the filename ends with .json
                if (!fileName.endsWith(".json")) {
                    fileName += ".json";
                }

                // Get external storage directory
                File path = new File(Environment.getExternalStorageDirectory(), "recordings");

                // Make sure the recordings folder exists
                if (!path.exists()) {
                    path.mkdirs();
                }

                // Create the file
                File file = new File(path, fileName);

                // Write to the file
                FileWriter writer = new FileWriter(file);
                writer.write(jsonString);
                writer.close();

                telemetry.addLine("Recording saved to: " + file.getAbsolutePath());

            } catch (IOException e) {
                telemetry.addLine("Failed to write file: " + e.getMessage());
            }

            telemetry.update();
        }
        }














