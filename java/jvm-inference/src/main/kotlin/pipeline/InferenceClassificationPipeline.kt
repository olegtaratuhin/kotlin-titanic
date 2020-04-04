package pipeline

import krangl.DataFrame
import transformations.Transformation
import transformations.apply

public class InferenceClassificationPipeline(private vararg val transformations: Transformation) {

    public fun run(dataFrame: DataFrame): DataFrame {
        var df = dataFrame
        for (transformation in transformations) {
            df = df.apply(transformation)
        }
        return df
    }
}