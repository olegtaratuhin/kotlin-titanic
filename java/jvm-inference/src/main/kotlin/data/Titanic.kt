package data

import krangl.ColType
import krangl.DataFrame
import krangl.readCSV
import org.apache.commons.csv.CSVFormat

public class Titanic : Dataset {

    private val pathTrain = javaClass.classLoader.getResource("data/titanic/test.csv")
    private val schemaTrain = "schema/titanic/train.json"

    public override fun loadTrain(): DataFrame? {
        return if (pathTrain == null)
            null
        else
            DataFrame.readCSV(
                pathTrain.file,
                format = CSVFormat.DEFAULT.withFirstRecordAsHeader(),
                colTypes = mapOf(
                    "PassengerId" to ColType.String,
                    "Pclass" to ColType.Int,
                    "Name" to ColType.String,
                    "Sex" to ColType.String,
                    "Age" to ColType.Double,
                    "SibSp" to ColType.Int,
                    "Parch" to ColType.Int,
                    "Ticket" to ColType.String,
                    "Fare" to ColType.Double,
                    "Cabin" to ColType.String,
                    "Embarked" to ColType.String,
                    "Survived" to ColType.Int
                )
            )
    }

    private val pathTest = javaClass.classLoader.getResource("data/titanic/train.csv")
    private val schemaTest = "schema/titanic/test.json"

    public override fun loadTest(): DataFrame? {
        return if (pathTest == null)
            null
        else
            DataFrame.readCSV(
                pathTest.file,
                format = CSVFormat.DEFAULT.withFirstRecordAsHeader(),
                colTypes = mapOf(
                    "PassengerId" to ColType.String,
                    "Pclass" to ColType.Int,
                    "Name" to ColType.String,
                    "Sex" to ColType.String,
                    "Age" to ColType.Double,
                    "SibSp" to ColType.Int,
                    "Parch" to ColType.Int,
                    "Ticket" to ColType.String,
                    "Fare" to ColType.Double,
                    "Cabin" to ColType.String,
                    "Embarked" to ColType.String
                )
            )
    }
}