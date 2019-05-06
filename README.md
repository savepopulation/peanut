# peanut
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-peanut-green.svg?style=flat )]( https://android-arsenal.com/details/1/7652 )
<br>A minimal Android library to handle heavy works in worker threads easily.

## How to use?
It's really easy to use.<br>

Create your peanut.
```
val peanut = object : Peanut<String>() {
            override fun call() {
                var count = 0
                for (i in 0 until 100000) {
                    count += count
                }
                onSuccess(count.toString())
            }
}
```
and Schedule it
```
peanut.runOn(Schedulers.io())
                .receiveOn(Schedulers.main())
                .run(object : Peanut.Subscriber<String> {

                    override fun onComplete(data: String) {
                        count.text = data
                    }

                    override fun onError(e: Error) {
                        e.printStackTrace()
                    }
})
```
## Dependency
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```
dependencies {
	 implementation 'com.github.savepopulation:peanut:v1.0.0'
}
```

