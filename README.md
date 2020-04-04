# java titanic

This repository contains two modules:

* python - has training pipeline for simple well-known titanic dataset
* java - has inference on saved model, that is prepared in python module

Python models are not so easy to transfer to the JVM, GraalVM is not ready yet for python and sometimes setting a separate
microservice for python to do machine learning might not an option.

I used [krangle](https://github.com/holgerbrandl/krangl) library to perform data wrangling using Kotlin. I've tried to replicate sklearn pipelines using little wrappers around krangl and wrote simplest model to do the classification using another great library for Kotlin that is [kotlin-numpy](https://github.com/Kotlin/kotlin-numpy). Also, I found a fun tool [sklearn-porter](https://github.com/nok/sklearn-porter) that takes sklearn estimators and code-gen Java/C/Go/PHP/Ruby code that implements the same classifier.

Ideally sklearn pipelines cound be serialized to some common format (like PMML), and then just deserialized in Kotlin. But comming up with something as generic and with such performance requirements is not an easy task. Also, there are some tools like Weka, Smile or even Spark-ML and ND4J, that might be total overkill for a project with titanic dataset, but might be a easonable choice for the real world
