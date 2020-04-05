import data.Titanic
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
import krangl.dataFrameOf
import krangl.writeCSV
import models.PreTrainedFactory
import transformations.Classifier
import java.io.File

fun main() {
    val titanic = Titanic().loadTest()!!

    titanic.sortedBy(passengerId)

    val model = PreTrainedFactory.getPretrainedCatboost(
        survived,
        listOf(age, fare),
        listOf(pClass, name, sex, sibSp, parch, ticket, cabin, embarked)
    )
    val classifier = Classifier(model)
    val features = titanic.selectIf { it.name != passengerId }
    val result = classifier.apply(features)

    val ids = titanic[passengerId]
    val ans = result[survived]

    val sub = dataFrameOf(ids, ans)
    println(sub)

    sub.writeCSV(File("catboost_kotlin.csv"))
}