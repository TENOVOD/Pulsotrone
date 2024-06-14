package com.example.pulselight.pulse_analyzer

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import kotlinx.coroutines.Job
import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import java.nio.ByteBuffer

class PulseAnalyzer(
    private var finalResult: Int,
    private var onFingerDetected: (Boolean) -> Unit,
    private val onPulseDetected: (Int) -> Unit
) : ImageAnalysis.Analyzer {
    private val redAverageQueue = mutableListOf<Double>()
    private val frameTimestamps = mutableListOf<Long>()
    private var isFingerOnCamera = false
    private val minRedPercentage = 0.9
    private val listOfMeasures = ArrayList<Int>()
    override fun analyze(image: ImageProxy) {
        val buffer = image.planes[0].buffer
        val data = buffer.toByteArray()
        val redAverage = calculateRedAverage(data)
        val redPercentage = calculateRedPercentage(data)


        if (redAverage < 150 && redPercentage >= minRedPercentage) {

            isFingerOnCamera = true
            onFingerDetected(isFingerOnCamera)
            val currentTime = System.currentTimeMillis()
            frameTimestamps.add(currentTime)
            redAverageQueue.add(redAverage)
            if (frameTimestamps.size == 50) {
                startMeasurement()
            }
            Log.d("WORKDredAv", "${listOfMeasures.size}")
            if (listOfMeasures.size > 4) {
                var counter = 0
                var finalResul = 0
                listOfMeasures.forEach {

                    ++counter
                    finalResul += it


                }
                finalResult = (finalResul.toFloat() / listOfMeasures.size).toInt()
                Log.d("FINALRESULT", "$finalResul")
            }
        } else {
            isFingerOnCamera = false
            onFingerDetected(isFingerOnCamera)
            stopMeasurement()
        }

        image.close()
    }

    private fun startMeasurement() {
        val smoothedRedAverageQueue = smoothData(redAverageQueue)
        val pulse = calculatePulse(smoothedRedAverageQueue, frameTimestamps)
        Log.d("RESDDDDDA", "PULSE $pulse")
        if (pulse != 0) {
            onPulseDetected(pulse)
            listOfMeasures.add(pulse)
            redAverageQueue.clear()
            frameTimestamps.clear()
        } else {
            redAverageQueue.clear()
            frameTimestamps.clear()
        }

    }

    private fun stopMeasurement() {
        redAverageQueue.clear()
        frameTimestamps.clear()
    }

    private fun calculateRedAverage(data: ByteArray): Double {
        var redSum = 0
        var pixelCount = 0

        for (i in data.indices step 4) {
            val red = data[i].toInt() and 0xFF
            redSum += red
            pixelCount++
        }

        return redSum.toDouble() / pixelCount
    }

    private fun calculateRedPercentage(data: ByteArray): Double {
        var redCount = 0
        var pixelCount = 0

        for (i in data.indices step 4) {
            val red = data[i].toInt() and 0xFF
            if (red > 30 && red < 150) {
                redCount++
            }
            pixelCount++
        }

        return redCount.toDouble() / pixelCount
    }

    private fun calculatePulse(redAverageQueue: List<Double>, frameTtimestamps: List<Long>): Int {
        val peaks = mutableListOf<Long>()
        val threshold = redAverageQueue.maxOrNull()?.times(0.90) ?: 0.0

        for (i in 1 until redAverageQueue.size - 1) {
            if (redAverageQueue[i] > threshold && redAverageQueue[i] > redAverageQueue[i - 1] && redAverageQueue[i] > redAverageQueue[i + 1]) {
                peaks.add(frameTtimestamps[i])
                // Log.d("WORKDS",frameTtimestamps[i].toString())
            }
        }

        if (peaks.size < 2) return 0

        val intervals = peaks.zipWithNext { a, b -> b - a }
        val averageInterval = intervals.average()
        return (60000 / averageInterval).toInt()
    }

    private fun smoothData(data: List<Double>): List<Double> {
        val smoothedData = mutableListOf<Double>()
        val windowSize = 5

        for (i in data.indices) {
            val window =
                data.subList(maxOf(0, i - windowSize), minOf(data.size, i + windowSize + 1))
            smoothedData.add(window.average())
        }

        return smoothedData
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        val data = ByteArray(remaining())
        get(data)
        return data
    }
}