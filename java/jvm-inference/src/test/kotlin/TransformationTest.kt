import data.Iris
import krangl.asDoubles
import krangl.asStrings
import krangl.dataFrameOf
import krangl.head
import org.junit.Assert
import org.junit.Test
import transformations.*

class TransformationTest {

    private val iris = Iris().loadTrain()!!

    @Test
    fun testFeatureSelection() {
        println(iris.head(5))
        val columnSelection = listOf("SepalLengthCm", "SepalWidthCm")
        val selector = FeatureSelector(columnSelection)

        val selectedColumns = iris.apply(selector)

        Assert.assertEquals(columnSelection.size, selectedColumns.ncol)
    }

    private val dataWithNulls = dataFrameOf("col1", "col2", "colNull")(
        "a1", "b1", null,
        "a2", "b2", 3.0,
        "a3", "b3", 2.0,
        "a4", "b4", null,
        "a5", "b5", 1.0,
        "a6", "b6", 3.0
    )

    @Test
    fun testFillNaConst() {
        val fillNa = FillNa("colNull", with = FillNa.Method.Const(10.0))
        Assert.assertTrue(dataWithNulls["colNull"].asDoubles().any { it == null })
        dataWithNulls.apply(fillNa)
        Assert.assertFalse(dataWithNulls["colNull"].asDoubles().any { it == null })
    }

    @Test
    fun testFillNaMean() {
        val mean = dataWithNulls["colNull"].asDoubles().filterNotNull().average()
        Assert.assertTrue(dataWithNulls["colNull"].asDoubles().any { it == null })
        dataWithNulls.apply(FillNa("colNull"))
        Assert.assertFalse(dataWithNulls["colNull"].asDoubles().any { it == null })
        val newMean = dataWithNulls["colNull"].asDoubles().filterNotNull().average()

        Assert.assertEquals(mean, newMean, 0.01)
    }

    private val dataWithCategorical = dataFrameOf("col1")(
        "a1",
        "a2",
        "a3",
        "a4",
        "a5",
        "a6"
    )

    @Test
    fun testOneHotEncoding() {
        val encoder = OneHotEncoder("col1")
        val distinctValues = dataWithCategorical["col1"].asStrings().filterNotNull().distinct().size
        val encoded = dataWithCategorical.apply(encoder)

        Assert.assertEquals(distinctValues, encoded.ncol)
    }

    private val dataToNormalize = dataFrameOf("col1")(
        1.0,
        2.0,
        3.0,
        4.0,
        5.0,
        100.0
    )

    @Test
    fun testNormalization() {
        val column = dataToNormalize["col1"].asDoubles().filterNotNull()
        val min = column.min()
        val max = column.max()

        Assert.assertFalse(min == 0.0)
        Assert.assertFalse(max == 1.0)

        val normalizer = Normalizer(listOf("col1"))
        dataToNormalize.apply(normalizer)

        val newColumn = dataToNormalize["col1"].asDoubles().filterNotNull()
        val newMin = newColumn.min()
        val newMax = newColumn.max()

        Assert.assertTrue(newMin == 0.0)
        Assert.assertTrue(newMax == 1.0)
    }
}