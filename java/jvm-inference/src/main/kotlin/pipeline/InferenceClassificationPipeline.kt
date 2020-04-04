package pipeline

import krangl.DataFrame
import org.jetbrains.numkt.core.ExperimentalNumkt
import transformations.Transformation
import transformations.apply

@ExperimentalNumkt
public class InferenceClassificationPipeline(
    private vararg val transformations: Transformation
) {

    public fun run(dataFrame: DataFrame): DataFrame {
        var df = dataFrame
        for (transformation in transformations) {
            df = df.apply(transformation)
        }
        return df
    }
}