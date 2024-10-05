package com.example.BrainTumorDetectionBackEnd.Service;

import org.opencv.core.*;
import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.Tensors;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;
import java.io.InputStream;

@Service
public class BrainTumorDetectionService {

    private static final String MODEL_PATH = "models/best_model.h5";  // Path to the model file

    static {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    // Preprocess the image (resize, normalize, and apply contour detection)
    public float[][] preprocessImage(MultipartFile file) throws IOException {
        byte[] imageBytes = IOUtils.toByteArray(file.getInputStream());

        // Decode the image using OpenCV
        Mat originalImage = Imgcodecs.imdecode(new MatOfByte(imageBytes), Imgcodecs.IMREAD_COLOR);
        if (originalImage.empty()) {
            throw new IOException("Could not decode image");
        }

        // Resize the image to the target size (240x240)
        Mat resizedImage = new Mat();
        Imgproc.resize(originalImage, resizedImage, new Size(240, 240));

        // Normalize the image by dividing pixel values by 255
        resizedImage.convertTo(resizedImage, CvType.CV_32FC3, 1.0 / 255.0);

        // Convert OpenCV Mat to a 2D float array (expected input format for the model)
        float[][] imageData = new float[240][240 * 3];
        resizedImage.get(0, 0, imageData[0]);

        return imageData;
    }

    // Perform prediction using the loaded TensorFlow model
    public String predictTumor(MultipartFile file) throws IOException {
        float[][] inputImage = preprocessImage(file);

        // Load the model using TensorFlow
        try (SavedModelBundle model = SavedModelBundle.load(MODEL_PATH, "serve")) {
            // Create a tensor from the input image
            Tensor<Float> inputTensor = Tensors.create(inputImage);

            // Run the model and obtain the prediction
            try (Tensor<?> output = model.session().runner().feed("input_1", inputTensor).fetch("output_0").run().get(0)) {
                float[] probabilities = new float[1];
                output.copyTo(probabilities);

                float prob = probabilities[0];
                if (prob < 0.4) {
                    return "Low Risk";
                } else if (prob < 0.7) {
                    return "Medium Risk";
                } else {
                    return "High Risk";
                }
            }
        }
    }
}
