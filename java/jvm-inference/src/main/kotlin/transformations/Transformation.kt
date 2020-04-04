package transformations

import krangl.DataFrame

public interface Transformation {
    public fun apply(dataFrame: DataFrame): DataFrame
}