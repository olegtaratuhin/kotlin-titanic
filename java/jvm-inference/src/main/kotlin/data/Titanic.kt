package data

import krangl.ColType
import krangl.DataFrame
import krangl.map
import krangl.readCSV
import org.apache.commons.csv.CSVFormat

public class Titanic : Dataset {

    private val pathTest = javaClass.classLoader.getResource("data/titanic/test.csv")
    private val schemaTest = "schema/titanic/test.json"
    private val typesTest = mapOf(
        "PassengerId" to ColType.String,
        "Pclass" to ColType.Int,
        "Name" to ColType.String,
        "Sex" to ColType.String,
        "Age" to ColType.String,
        "SibSp" to ColType.Int,
        "Parch" to ColType.Int,
        "Ticket" to ColType.String,
        "Fare" to ColType.String,
        "Cabin" to ColType.String,
        "Embarked" to ColType.String
    )

    private val pathTrain = javaClass.classLoader.getResource("data/titanic/train.csv")
    private val schemaTrain = "schema/titanic/train.json"
    private val typesTrain = typesTest.plus("Survived" to ColType.Int)

    public override fun loadTrain(): DataFrame? {
        return if (pathTrain == null)
            null
        else
            imputeMissingAsDoubles(
                DataFrame.readCSV(
                    pathTrain.file,
                    format = CSVFormat.DEFAULT.withFirstRecordAsHeader(),
                    colTypes = typesTrain
                )
            )
    }

    public override fun loadTest(): DataFrame? {
        return if (pathTest == null)
            null
        else {
            imputeMissingAsDoubles(
                DataFrame.readCSV(
                    pathTest.file,
                    format = CSVFormat.DEFAULT.withFirstRecordAsHeader(),
                    colTypes = typesTest
                )
            )
        }
    }

    /**
     * Missing values parsing with type other than String seems to fail in
     * krangl, todo: PR with parsing missing values, by default to be imputed with null
     */
    private fun imputeMissingAsDoubles(dataFrame: DataFrame): DataFrame =
        dataFrame.addColumn("Age") {
            it["Age"].map<String> { it ->
                it.toDoubleOrNull()
            }
        }.addColumn("Fare") {
            it["Fare"].map<String> { it ->
                it.toDoubleOrNull()
            }
        }
}