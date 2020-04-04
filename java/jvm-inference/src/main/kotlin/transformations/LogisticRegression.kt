package transformations

import krangl.DataFrame
import models.LogisticRegressionModel
import org.jetbrains.numkt.core.ExperimentalNumkt
import pipeline.NumktConverter

@ExperimentalNumkt
class LogisticRegression(private val model: LogisticRegressionModel, private val resultHeader: List<String>) :
    Transformation {

    private val converter = NumktConverter

    override fun apply(dataFrame: DataFrame): DataFrame {
        val matrix = converter.convertToNumkt(dataFrame)
        val predictions = model.predict(matrix)
        return converter.convertFromNumkt(predictions, resultHeader)
    }
}