package models

import org.jetbrains.numkt.core.ExperimentalNumkt
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.linalg.dot
import org.jetbrains.numkt.loadtxt
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.zeros

@ExperimentalNumkt
public abstract class LogisticRegressionModel : Model {

    abstract val weights: KtNDArray<Double>
    abstract val intercept: KtNDArray<Double>

    abstract override fun fit(matrix: KtNDArray<Double>, target: KtNDArray<Double>)

    public override fun predict(matrix: KtNDArray<Double>): KtNDArray<Double> {
        return predict(matrix, 0.5)
    }

    public fun predict(matrix: KtNDArray<Double>, threshold: Double): KtNDArray<Double> {
        return sign(predictProba(matrix) - threshold)
    }

    public override fun predictProba(matrix: KtNDArray<Double>): KtNDArray<Double> {
        return sigmoid(dot(matrix, weights).minus(intercept))
    }
}

@ExperimentalNumkt
fun sigmoid(x: KtNDArray<Double>): KtNDArray<Double> {
    return 1 / (1 + exp(-x))
}

@ExperimentalNumkt
public class PreTrainedLogisiticRegressionModel : LogisticRegressionModel {

    public constructor(weights: KtNDArray<Double>, intercept: KtNDArray<Double>) {
        this.weights = weights
        this.intercept = intercept
    }

    public constructor(weights: KtNDArray<Double>) {
        this.weights = weights
        this.intercept = zeros(1)
    }

    public constructor(weightsFile: String, interceptFile: String) {
        this.weights = loadtxt(weightsFile)
        // for some reason extracting single scalar fails here,
        // so instead type is KtNDArray, when manually creating it
        // we should create it so it behaves as scalar (zeroes(1))
        this.intercept = loadtxt(interceptFile)
    }

    public override val weights: KtNDArray<Double>
    override val intercept: KtNDArray<Double>

    public override fun fit(matrix: KtNDArray<Double>, target: KtNDArray<Double>) {
    }
}