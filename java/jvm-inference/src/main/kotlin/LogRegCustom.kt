import data.Titanic
import krangl.dataFrameOf
import krangl.writeCSV
import models.PreTrainedFactory
import org.apache.commons.csv.CSVFormat
import org.jetbrains.numkt.core.ExperimentalNumkt
import pipeline.InferenceClassificationPipeline
import transformations.*
import java.io.File

@ExperimentalNumkt
fun main(args: Array<String>) {
    val titanic = Titanic().loadTest()!!

    val passengerId = "PassengerId"
    val fare = "Fare"
    val pClass = "Pclass"
    val sex = "Sex"
    val age = "Age"
    val sibSp = "SibSp"
    val parch = "Parch"
    val survived = "Survived"

    titanic.sortedBy(passengerId)
    val model = PreTrainedFactory.getPreTrainedModel()
    val result = InferenceClassificationPipeline(
        FeatureSelector(fare, pClass, sex, age, sibSp, parch),
        FillNa(fare, FillNa.Method.Mean),
        FillNa(age, FillNa.Method.Mean),
        CategoricalMapper(sex, mapOf("male" to 0, "female" to 1)),
        Normalizer(fare, sibSp, parch, age, method = Normalizer.Method.MinMax(0.0, 1.0)),
        LogisticRegression(model, listOf(survived)),
        ToCategory(survived, mapOf(-1.0 to 0, 1.0 to 1, 0.0 to 1))
    ).run(titanic)

    val ids = titanic[passengerId]
    val ans = result[survived]

    val sub = dataFrameOf(ids, ans)
    println(sub)

    sub.writeCSV(
        File("Log_reg_kotlin.csv"),
        format = CSVFormat.DEFAULT.withHeader(passengerId, survived)
    )
}