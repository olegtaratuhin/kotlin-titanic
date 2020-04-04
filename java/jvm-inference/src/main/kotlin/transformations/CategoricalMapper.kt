package transformations

import krangl.DataFrame
import krangl.map

class CategoricalMapper(private val column: String, private val map: Map<String, Int>) : Transformation {
    override fun apply(dataFrame: DataFrame): DataFrame {
        return dataFrame.addColumn(column) { it[column].map<String> { map[it] } }
    }
}