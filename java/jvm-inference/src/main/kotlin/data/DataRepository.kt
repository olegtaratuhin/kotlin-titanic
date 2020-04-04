package data

import java.net.URL

interface DataRepository {
    public fun load(dataset: String): Dataset
}

abstract class Local : DataRepository {
}

abstract class Remote : DataRepository {
    override fun load(dataset: String): Dataset {
        return load(url = URL(dataset))
    }

    protected abstract fun load(url: URL): Dataset
}