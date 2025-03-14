package com.example.ffcbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the TensorFlow Lite model
        try {
            Interpreter interpreter = new Interpreter(loadModelFile());

            // Prepare the input data (Example: image input)
            TensorBuffer inputBuffer = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            // Populate the inputBuffer with your preprocessed image data

            // Run inference
            TensorBuffer outputBuffer = TensorBuffer.createFixedSize(new int[]{1, 16}, DataType.FLOAT32);
            interpreter.run(inputBuffer.getBuffer(), outputBuffer.getBuffer());

            // Get the result and display it
            float[] result = outputBuffer.getFloatArray();
            int predictedClass = getMaxIndex(result);
            TextView resultText = findViewById(R.id.resultText);
            String resultText = getString(R.string.result_label, predictedClass, confidence);
            resultLabel.setText(resultText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load the .tflite model from the assets folder
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("model_with_flex_ops.tflite");
        FileInputStream inputStream = fileDescriptor.createInputStream();
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // Get the index of the maximum value in the output array
    private int getMaxIndex(float[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
