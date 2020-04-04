package transformations

import krangl.DataFrame

public class FeatureSelector(private val featuresToSelect: Iterable<String>) : Transformation {
    public override fun apply(dataFrame: DataFrame) = dataFrame.select(featuresToSelect)
}