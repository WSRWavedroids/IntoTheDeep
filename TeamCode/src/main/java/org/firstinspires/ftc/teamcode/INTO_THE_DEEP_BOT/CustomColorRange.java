package org.firstinspires.ftc.teamcode.INTO_THE_DEEP_BOT;

import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ColorSpace;
import org.opencv.core.Scalar;

public class CustomColorRange extends ColorRange {
    protected final ColorSpace colorSpace;
    protected final Scalar min;
    protected final Scalar max;

    // -----------------------------------------------------------------------------
    // DEFAULT OPTIONS
    // -----------------------------------------------------------------------------

    public static final ColorRange ARTIFACT_PURPLE = new ColorRange(
            ColorSpace.HSV,
            new Scalar(134,  70,  70),
            new Scalar(154, 255, 255)
    );

    public static final ColorRange ARTIFACT_GREEN = new ColorRange(
            ColorSpace.HSV,
            new Scalar(55,  70,  70),
            new Scalar(85, 255, 255)
    );

    public static final ColorRange EVERYTHING = new ColorRange(
            ColorSpace.RGB,
            new Scalar (0,0,0),
            new Scalar (255,255,255)
    );

    public CustomColorRange(ColorSpace colorSpace, Scalar min, Scalar max) {
        super(colorSpace,min,max);
        this.colorSpace = colorSpace;
        this.min = min;
        this.max = max;
    }
}
