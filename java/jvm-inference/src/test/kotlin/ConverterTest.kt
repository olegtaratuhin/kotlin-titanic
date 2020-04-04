import krangl.dataFrameOf
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.ExperimentalNumkt
import org.junit.Assert
import org.junit.Test
import pipeline.NumktConverter

@ExperimentalNumkt
class ConverterTest {

    private val converter = NumktConverter

    private val singleColumnDf = dataFrameOf("Value")(
        2.0,
        3.0,
        1.0
    )

    @Test
    fun testConvertion1dToKtNdArray() {
        val expectedShape = IntArray(2)
        expectedShape[0] = 3
        expectedShape[1] = 1
        Assert.assertArrayEquals(expectedShape, converter.convertToNumkt(singleColumnDf).shape)
    }

    val twoColumnDf = dataFrameOf("Value1", "Value2")(
        1.0, 2.0,
        3.0, 4.0,
        5.0, 6.0
    )

    @Test
    fun testConvertion2dToKtNdArray() {
        val expectedShape = IntArray(2)
        expectedShape[0] = 3
        expectedShape[1] = 2

        Assert.assertArrayEquals(expectedShape, converter.convertToNumkt(twoColumnDf).shape)
    }

    val vector = array(arrayOf(1.0, 2.0, 3.0, 4.0, 5.0))
    val vectorDf = dataFrameOf("Value")(1.0, 2.0, 3.0, 4.0, 5.0)

    @Test
    fun testConvertion1dFromKtNdArray() {
        val dataframe = converter.convertFromNumkt(vector, listOf("Value"))

        Assert.assertTrue(dataframe == vectorDf)
    }

    val matrix = array<Double>(listOf(listOf(1.0, 2.0, 3.0, 4.0, 5.0), listOf(6.0, 7.0, 8.0, 9.0, 10.0)))
    val matrixDf = dataFrameOf("Value1", "Value2")(
        1.0, 6.0,
        2.0, 7.0,
        3.0, 8.0,
        4.0, 9.0,
        5.0, 10.0
    )

    @Test
    fun testConvertion2dFromKtNdArray() {
        val dataframe = converter.convertFromNumkt(matrix, listOf("Value1", "Value2"))

        Assert.assertTrue(dataframe == matrixDf)
    }
}