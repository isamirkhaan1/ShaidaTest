package com.example.shaidatest

import android.content.Context
import android.util.Log
import com.google.android.gms.tflite.client.TfLiteInitializationOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import org.tensorflow.lite.task.gms.vision.TfLiteVision
import org.tensorflow.lite.task.gms.vision.detector.ObjectDetector
import javax.inject.Inject


class TensorFlowLite @Inject constructor(
    @ApplicationContext val context: Context
) {

    companion object {
        const val TAG = "FSL"
    }

    fun init() {
        val options = TfLiteInitializationOptions.builder()
            .setEnableGpuDelegateSupport(true)
            .build()

        TfLiteVision.initialize(context, options).addOnSuccessListener {
            Log.e(
                TAG, "TensorFlow successfully initialized"
            )
        }.addOnFailureListener {
            Log.e(
                TAG, "TfLiteVision failed to initialize: "
                    + it.message
            )
        }
    }

    fun train(
        samplesFilePath: String,
        modelPath: String,
    ) {
        val objectDetector =
            ObjectDetector.createFromFileAndOptions(
                context, modelPath, ObjectDetector.ObjectDetectorOptions.builder().build()
            )


    }
}