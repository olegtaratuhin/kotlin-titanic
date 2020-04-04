import data.Titanic
import data.Titanic.Fields.age
import data.Titanic.Fields.fare
import data.Titanic.Fields.pClass
import data.Titanic.Fields.parch
import data.Titanic.Fields.passengerId
import data.Titanic.Fields.sex
import data.Titanic.Fields.sibSp
import data.Titanic.Fields.survived
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
        File("logreg_kotlin.csv"),
        format = CSVFormat.DEFAULT.withHeader(passengerId, survived)
    )
}