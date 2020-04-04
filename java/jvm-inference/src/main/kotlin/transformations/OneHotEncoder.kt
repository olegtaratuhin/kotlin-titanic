package transformations

import krangl.DataFrame
import krangl.experimental.oneHot

public class OneHotEncoder(private val column: String) : Transformation {

    public override fun apply(dataFrame: DataFrame): DataFrame {
        return dataFrame.oneHot(column)
    }
}