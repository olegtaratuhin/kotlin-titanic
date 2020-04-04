import krangl.ColType
import krangl.DataFrame
import krangl.printDataClassSchema
import krangl.readCSV
import org.apache.commons.csv.CSVFormat
import java.io.File

fun main(args: Array<String>) {
    // val dataset = data.Titanic()
    val dataset = DataFrame.readCSV(
        File("data-loader/src/transformations.data.main/resources/data/test.csv"),
        format = CSVFormat.DEFAULT,
        colTypes = mapOf(
            "PassangerId" to ColType.String,
            "Pclass" to ColType.Int,
            "Name" to ColType.String,
            "Sex" to ColType.String,
            "Age" to ColType.Double,
            "SibSp" to ColType.Int,
            "Parch" to ColType.Int,
            "Ticket" to ColType.Double,
            "Fare" to ColType.Double,
            "Cabin" to ColType.String,
            "Embarked" to ColType.String
        )
    )

    println(dataset.printDataClassSchema("data.Titanic"))
}