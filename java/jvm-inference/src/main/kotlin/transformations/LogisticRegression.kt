package transformations

import krangl.DataFrame
import models.LogisticRegressionModel
import org.jetbrains.numkt.core.ExperimentalNumkt
import pipeline.NumktConverter

@ExperimentalNumkt
class LogisticRegression(model: LogisticRegressionModel, private val resultHeader: List<String>) : Estimator(model) {

    private val converter = NumktConverter

    override fun apply(dataFrame: DataFrame): DataFrame {
        val matrix = converter.convertToNumkt(dataFrame)
        val predictions = (model as LogisticRegressionModel).predict(matrix)
        return converter.convertFromNumkt(predictions, resultHeader)
    }
}