package transformations

import krangl.DataFrame
import krangl.asDoubles

public class Normalizer : Transformation {

    private val columns: Iterable<String>
    private val method: Method

    constructor(vararg columns: String, method: Method = Method.MinMax()) {
        this.columns = columns.toList()
        this.method = method
    }

    constructor(columns: List<String>, method: Method = Method.MinMax()) {
        this.columns = columns
        this.method = method
    }

    public sealed class Method {
        public data class MinMax(val a: Double = 0.0, val b: Double = 1.0) : Method()
        public object StdScore : Method()
    }

    private fun transform(col: String, dataframe: DataFrame): DataFrame {
        return when (method) {
            is Method.MinMax -> {
                val columnData = dataframe[col].asDoubles()
                val columnDataNonNull = columnData.filterNotNull()

                val min = columnDataNonNull.min()!!
                val max = columnDataNonNull.max()!!
                val mean = columnDataNonNull.average()
                require(min != max)

                for (i in columnData.indices) {
                    columnData[i] = when {
                        columnData[i] == null -> mean
                        else -> (columnData[i]!! - min) / (max - min)
                    }
                }

                dataframe
            }
            is Method.StdScore -> {
                TODO()
            }
        }
    }

    public override fun apply(dataFrame: DataFrame): DataFrame {
        for (column in columns) {
            transform(column, dataFrame)
        }

        return dataFrame
    }

}