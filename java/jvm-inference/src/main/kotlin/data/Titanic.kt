package data

import data.Titanic.Fields.age
import data.Titanic.Fields.cabin
import data.Titanic.Fields.embarked
import data.Titanic.Fields.fare
import data.Titanic.Fields.name
import data.Titanic.Fields.pClass
import data.Titanic.Fields.parch
import data.Titanic.Fields.passengerId
import data.Titanic.Fields.sex
import data.Titanic.Fields.sibSp
import data.Titanic.Fields.survived
import data.Titanic.Fields.ticket
import krangl.ColType
import krangl.DataFrame
import krangl.map
import krangl.readCSV
import org.apache.commons.csv.CSVFormat

public class Titanic : Dataset {

    public object Fields {
        public const val passengerId = "PassengerId"
        public const val name = "Name"
        public const val fare = "Fare"
        public const val pClass = "Pclass"
        public const val sex = "Sex"
        public const val age = "Age"
        public const val sibSp = "SibSp"
        public const val parch = "Parch"
        public const val ticket = "Ticket"
        public const val cabin = "Cabin"
        public const val survived = "Survived"
        public const val embarked = "Embarked"
    }

    private val pathTest = javaClass.classLoader.getResource("data/titanic/test.csv")
    private val typesTest = mapOf(
        passengerId to ColType.String,
        pClass to ColType.Int,
        name to ColType.String,
        sex to ColType.String,
        age to ColType.String,
        sibSp to ColType.Int,
        parch to ColType.Int,
        ticket to ColType.String,
        fare to ColType.String,
        cabin to ColType.String,
        embarked to ColType.String
    )

    private val pathTrain = javaClass.classLoader.getResource("data/titanic/train.csv")
    private val typesTrain = typesTest.plus(survived to ColType.Int)

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
        dataFrame.addColumn(age) {
            it[age].map<String> { it ->
                it.toDoubleOrNull()
            }
        }.addColumn(fare) {
            it[fare].map<String> { it ->
                it.toDoubleOrNull()
            }
        }
}