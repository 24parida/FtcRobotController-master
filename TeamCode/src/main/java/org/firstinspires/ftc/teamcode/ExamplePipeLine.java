package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

class ExamplePipeLine extends OpenCvPipeline {

    private Mat matYCrCb = new Mat();
    private Mat matCb_left = new Mat();
    private Mat matCb_Right = new Mat();
    private Mat left_block = new Mat();
    private Mat right_block = new Mat();

    public double left;
    public double right;

    int[] left_rect = {
            1,
            2,
            3,
            4
    };

    int[] right_rect = {
            1,
            2,
            3,
            4
    };

    public Mat drawRectangle(Mat frame,int[] points,Scalar color,int thickness){

        Imgproc.rectangle(
                frame,
                new Point(
                        points[0],
                        points[1]),

                new Point(
                        points[2],
                        points[3]),
                color, thickness);

        //submat simply put is cropping the mat
        return frame.submat(points[1], points[3], points[0], points[2]);

    }

    @Override
    public Mat processFrame(Mat input) {
        /**
         *input which is in RGB is the frame the camera gives
         *We convert the input frame to the color space matYCrCb
         *Then we store this converted color space in the mat matYCrCb
         *For all the color spaces go to
         *https://docs.opencv.org/3.4/d8/d01/group__imgproc__color__conversions.html
         */
        Imgproc.cvtColor(input, matYCrCb, Imgproc.COLOR_RGB2YCrCb);

        left_block = drawRectangle(matYCrCb,left_rect,new Scalar(0,255,0),1);
        right_block = drawRectangle(matYCrCb,right_rect, new Scalar(0,0,255),1);

        /**
         *This will extract the value of the CB channel in both rectangles
         *0 is the Y channel, 1 is the Cr, 2 is Cb
         */
        Core.extractChannel(left_block, matCb_left, 2);
        Core.extractChannel(right_block, matCb_Right, 2);

        /**
         *We now average value and extract it
         * so now left is the Cb value of the left rectangle and
         * right is the Cb value of the right rectangle
         */
        Scalar left_mean = Core.mean(matCb_left);
        Scalar right_mean = Core.mean(matCb_Right);
        left=left_mean.val[0];
        right=right_mean.val[0];

        return input;
    }
}
