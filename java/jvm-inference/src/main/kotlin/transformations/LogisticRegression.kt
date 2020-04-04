package transformations

import krangl.DataFrame
import models.Model
import org.jetbrains.numkt.core.ExperimentalNumkt
import pipeline.NumktConverter

@ExperimentalNumkt
class LogisticRegression(model: Model, private val resultHeader: List<String>) : Estimator(model) {

    private val converter = NumktConverter

    override fun apply(dataFrame: DataFrame): DataFrame {
        val matrix = converter.convertToNumkt(dataFrame)
        val predictions = model.predict(matrix)
        return converter.convertFromNumkt(predictions, resultHeader)
    }
}