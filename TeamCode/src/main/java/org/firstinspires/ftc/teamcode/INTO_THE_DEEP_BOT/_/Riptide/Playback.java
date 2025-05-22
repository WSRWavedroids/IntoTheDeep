package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.json.JSONObject;

import java.util.Formattable;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.File;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;

import android.os.Environment;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Robot;

public class Playback {
//This is the general record class

    public Robot robot;
    public OpMode opmode;
    public Telemetry telemetry;
    public HardwareMap hardwareMap;
    public RipConfig rip;
    public TeleOp_Recording recSource;
    public File file;

    public ElapsedTime frameTimer;

    double lastFrameTime = 0;
    public int currentFrame = 1;
    public boolean isPlaying = false;
    public List<Frame> cachedFrames;

    //This constructor populates the null scripts once everything is initalized
    public Playback(Robot robot, RipConfig rip) {
        this.robot = robot;
        this.rip = rip;
        this.telemetry = robot.telemetry;
        this.hardwareMap = robot.hardwareMap;
        this.opmode = robot.opmode;
        //this.file = file;
        this.cachedFrames = new ArrayList<>();
        this.frameTimer = new ElapsedTime();
    }

    public void cacheFile() //We need to save the entire file to memory during init to help processing time
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Gson gson = new Gson();
            Frame[] framesArray = gson.fromJson(reader, Frame[].class);
            cachedFrames = new ArrayList<>(Arrays.asList(framesArray));
            reader.close();
            telemetry.addLine("Done loading");
            telemetry.update();
        } catch (IOException e) {
            telemetry.addLine("Failed to load recording: " + e.getMessage());
            telemetry.update();
        }
    }

    public void runFrames(int start, int end) //Times frames and sends data to commander
    {
        isPlaying = true;
        currentFrame = start;
        frameTimer.reset();

        double currentTime = frameTimer.seconds();

        while (isPlaying && currentFrame<=end)
        {
            if(currentTime-lastFrameTime >= 0.02)
            {
                commandHardware(cachedFrames.get(currentFrame));
                lastFrameTime = currentTime;
                currentFrame++;
            }
        }

    }

    void commandHardware(Frame current) // Receives data and commands hardware for frame
    {
        if (current == null)
        {
            EmergencyStop();
        }
        else
        {
            for (Frame.MotorData motorData : current.motors) {
                for (DcMotorEx motor : rip.ripMotors) {
                    if (motor.getDeviceName().equals(motorData.name)) {
                        // You probably want to use velocity control, but this example sets power
                        motor.setVelocity(motorData.velocity);
                        break;
                    }
                }
            }

            for (Frame.ServoData servoData : current.servos) {
                for (Servo servo : rip.ripServos) {
                    if (servo.getDeviceName().equals(servoData.name)) {
                        servo.setPosition(servoData.position);
                        break;
                    }
                }
            }

            for (Frame.CRServoData crServoData : current.crServos) {
                for (CRServo crServo : rip.ripCRServos) {
                    if (crServo.getDeviceName().equals(crServoData.name)) {
                        if (crServoData.direction == "FORWARD")
                        {
                            crServo.setDirection(DcMotorSimple.Direction.FORWARD);
                        }
                        else
                        {
                            crServo.setDirection(DcMotorSimple.Direction.FORWARD);
                        }
                        crServo.setPower(crServoData.power);
                        break;

                    }
                }
            }
        }

    }

    public void EmergencyStop() //If we miss frames lets maybe not kill the robot
    {
        for (DcMotorEx i : rip.ripMotors)
        {
            i.setPower(0);
        }
        for (CRServo i : rip.ripCRServos)
        {
            i.setPower(0);
        }
    }

    public void setRecordingFile(String fileName) {
        File dir = new File(Environment.getExternalStorageDirectory(), "/FIRST/recordings");
        if (!dir.exists()) dir.mkdirs();
        this.file = new File(dir, fileName);
    }

    }







