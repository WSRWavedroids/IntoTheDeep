package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.Training;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT.Autonomous.AutonomousPLUS;
@Disabled
@Autonomous(group = "Basic", name = "DrivetrainTest")
    public class Examples_of_functions extends AutonomousPLUS {

        public String currentPosition;
        public String target;



            //Functions are great tools for acessing chunks of code without retyping them
            //To use a function Simply call it's name in where you want to use it and follow it with ()

            //To make a function, type:
            public void NameOfTheFunction()
            {
                //Then place the series of commands inside the {}
            }

            //If your function needs a value, pass the variable type into the (), then the function name
            public void functionWithValues(int num)
            {
                //Do stuff with that number here
            }

            //if your function only needs to do math and spit the value out type
            public int MathFunction()
            {
                //Do the math in here then return with the command below
                int exampleNumber = 1;
                return exampleNumber;
            }

            //Now lets go over calling our functions
            void Main()//
            {
                NameOfTheFunction(); //Does the stuff, nothing else

                functionWithValues(1); // Does the stuff using the number

                MathFunction(); // Does the math then subs the name call for the value
            }


        }

