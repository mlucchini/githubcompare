# GitHub Compare
[![Build Status](https://travis-ci.org/mlucchini/githubcompare.svg?branch=master)](https://travis-ci.org/mlucchini/githubcompare)
[![Coverage Status](https://coveralls.io/repos/mlucchini/githubcompare/badge.svg?branch=master&service=github)](https://coveralls.io/github/mlucchini/githubcompare?branch=master)

GitHub Compare lets you compare the popularity of multiple repositories.

##### Generate an assembly

```sh
	sbt assembly
```

##### Download GitHub events

```sh
	java -jar target/scala-2.11/githubcompare-assembly-0.1.jar download [options]
```

##### Generate GitHub stars time-series

```sh
	java -jar target/scala-2.11/githubcompare-assembly-0.1.jar aggregate [options]
```

Uses [Spark](http://spark.apache.org) for event processing.
The generation can be time-consuming, follow the Spark tasks in the [browser](http://localhost:4040).
