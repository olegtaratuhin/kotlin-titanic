
import data.Iris
import data.Titanic
import org.junit.Assert
import org.junit.Test

class LoadingTest {

    @Test
    fun testTitanicLoading() {
        val titanic = Titanic()
        Assert.assertNotNull(titanic.loadTest())
        Assert.assertNotNull(titanic.loadTrain())
    }

    @Test
    fun testIrisLoading() {
        val iris = Iris()

        // iris doesn't have test
        Assert.assertNull(iris.loadTest())
        Assert.assertNotNull(iris.loadTrain())
    }
}