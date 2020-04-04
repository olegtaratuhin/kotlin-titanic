package models

import org.jetbrains.numkt.core.ExperimentalNumkt
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.linalg.dot
import org.jetbrains.numkt.loadtxt
import org.jetbrains.numkt.math.*

@ExperimentalNumkt
public abstract class LogisticRegressionModel : Model {

    abstract val weights: KtNDArray<Double>

    abstract override fun fit(matrix: KtNDArray<Double>, target: KtNDArray<Double>)

    public override fun predict(matrix: KtNDArray<Double>): KtNDArray<Double> {
        return predict(matrix, 0.5)
    }

    public fun predict(matrix: KtNDArray<Double>, threshold: Double): KtNDArray<Double> {
        return sign(predictProba(matrix) - threshold)
    }

    public override fun predictProba(matrix: KtNDArray<Double>): KtNDArray<Double> {
        return sigmoid(dot(matrix, weights))
    }
}

@ExperimentalNumkt
fun sigmoid(x: KtNDArray<Double>): KtNDArray<Double> {
    return 1 / (1 + exp(x * -1))
}

@ExperimentalNumkt
public class PretrainedLogisiticRegressionModel : LogisticRegressionModel {

    public constructor(weights: KtNDArray<Double>) {
        this.weights = weights
    }

    public constructor(weightsFile: String) {
        this.weights = loadtxt(weightsFile)
    }

    public override val weights: KtNDArray<Double>

    public override fun fit(matrix: KtNDArray<Double>, target: KtNDArray<Double>) {
    }
}