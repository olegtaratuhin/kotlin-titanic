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
import models.RandomForestModel
import pipeline.InferenceClassificationPipeline
import transformations.*
import java.io.File

fun main(args: Array<String>) {
    val titanic = Titanic().loadTest()!!

    titanic.sortedBy(passengerId)
    val model = RandomForestModel(survived)
    val result = InferenceClassificationPipeline(
        FeatureSelector(fare, pClass, sex, age, sibSp, parch),
        FillNa(fare, FillNa.Method.Mean),
        FillNa(age, FillNa.Method.Mean),
        CategoricalMapper(sex, mapOf("male" to 0, "female" to 1)),
        Normalizer(fare, sibSp, parch, age, method = Normalizer.Method.MinMax(0.0, 1.0)),
        Classifier(model)
    ).run(titanic)

    val ids = titanic[passengerId]
    val ans = result[survived]

    val sub = dataFrameOf(ids, ans)
    println(sub)

    sub.writeCSV(File("random_forest_kotlin.csv"))
}