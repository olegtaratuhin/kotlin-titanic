package transformations

import krangl.DataFrame
import krangl.asDoubles

public class FillNa(private val column: String, private val with: Method = Method.Mean) :
    Transformation {
    public sealed class Method {
        public data class Const(val number: Number) : Method()
        public object Mean : Method()
    }

    public override fun apply(dataFrame: DataFrame): DataFrame = when (with) {
        is Method.Const -> {
            val column = dataFrame[column].asDoubles()
            for (i in column.indices) if (column[i] == null) column[i] = with.number as Double
            dataFrame
        }
        is Method.Mean -> {
            val column = dataFrame[column].asDoubles()
            val mean = column.filterNotNull().average()
            for (i in column.indices) if (column[i] == null) column[i] = mean
            dataFrame
        }
    }
}