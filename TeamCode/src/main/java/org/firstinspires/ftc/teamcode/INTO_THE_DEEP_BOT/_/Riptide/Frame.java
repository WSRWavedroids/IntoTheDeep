package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT._.Riptide;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public int frameNumber;
    public long timestamp; // Unix timestamp (ms)

    public List<MotorData> motors = new ArrayList<>();
    public List<ServoData> servos = new ArrayList<>();
    public List<CRServoData> crServos = new ArrayList<>();

    // Constructor
    public Frame(int frameNumber, long timestamp) {
        this.frameNumber = frameNumber;
        this.timestamp = timestamp;
    }

    public static class MotorData {
        public String name;
        public double velocity;

        public MotorData(String name, double velocity) {
            this.name = name;
            this.velocity = velocity;
        }
    }

    public static class ServoData {
        public String name;
        public double position;

        public ServoData(String name, double position) {
            this.name = name;
            this.position = position;
        }
    }

    public static class CRServoData {
        public String name;
        public String direction; // "FORWARD" or "REVERSE"
        public double power;

        public CRServoData(String name, String direction, double power) {
            this.name = name;
            this.direction = direction;
            this.power = power;
        }
    }
}
