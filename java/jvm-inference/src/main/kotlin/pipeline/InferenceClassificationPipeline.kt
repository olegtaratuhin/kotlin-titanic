package pipeline

import krangl.DataFrame
import models.Model
import org.jetbrains.numkt.core.ExperimentalNumkt
import transformations.Transformation
import transformations.apply

@ExperimentalNumkt
public class InferenceClassificationPipeline(
    private val model: Model,
    private vararg val transformations: Transformation
) {

    private val converter = NumktConverter

    public fun run(dataFrame: DataFrame, resultHeader: List<String>): DataFrame {
        for (transformation in transformations) {
            dataFrame.apply(transformation)
        }

        val matrix = converter.convertToNumkt(dataFrame)
        val predictions = model.predict(matrix)

        return converter.convertFromNumkt(predictions, resultHeader)
    }
}