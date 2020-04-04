package transformations

import krangl.DataFrame

public class FeatureSelector : Transformation {

    val featuresToSelect: Iterable<String>

    constructor(vararg featuresToSelect: String) {
        this.featuresToSelect = featuresToSelect.toList()
    }

    constructor(featuresToSelect: List<String>) {
        this.featuresToSelect = featuresToSelect
    }

    public override fun apply(dataFrame: DataFrame) = dataFrame.select(featuresToSelect.toList())
}