package data

import krangl.ColType
import krangl.DataFrame
import krangl.readCSV
import org.apache.commons.csv.CSVFormat

public class Iris : Dataset {

    private val pathTrain = javaClass.classLoader.getResource("data/iris/train.csv")
    private val schemaTrain = "schema/iris/train.json"

    public override fun loadTrain(): DataFrame? {
        return if (pathTrain == null)
            null
        else
            DataFrame.readCSV(
                pathTrain.file,
                format = CSVFormat.DEFAULT.withFirstRecordAsHeader(),
                colTypes = mapOf(
                    "Id" to ColType.Int,
                    "SepalLengthCm" to ColType.Double,
                    "SepalWidthCm" to ColType.Double,
                    "PetalLengthCm" to ColType.Double,
                    "PetalWidthCm" to ColType.Double,
                    "Species" to ColType.String
                )
            )
    }

    /**
     * Iris data set has no test
     */
    public override fun loadTest(): DataFrame? {
        return null
    }
}