package models

import org.jetbrains.numkt.core.ExperimentalNumkt
import org.jetbrains.numkt.core.KtNDArray

@ExperimentalNumkt
interface Model {

    public fun fit(matrix: KtNDArray<Double>, target: KtNDArray<Double>)

    public fun predict(matrix: KtNDArray<Double>): KtNDArray<Double>

    public fun predictProba(matrix: KtNDArray<Double>): KtNDArray<Double>
}