<h1 align="center">The Guardian</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a></br>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="License" src="https://img.shields.io/badge/Kotlin-1.5.31-blue.svg"/></a>
  <a href="https://gradle.org"><img alt="License" src="https://img.shields.io/badge/Gradle-7-blue?style=flat"/></a>
</p>

<p align="center">  
The Guardian is a small application based on modern Android application tech-stacks and MVVM clean architecture.<br>This project is focusing especially on the new library Jetpack Compose.<br>
</p>
</br>


## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- [Jetpack](https://developer.android.com/jetpack)
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - dispose of observing data when lifecycle state changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - UI related data holder, lifecycle aware.
  - [Compose](https://developer.android.com/jetpack/compose) - accelerate UI development.
  - [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation.
- Modern Architecture
  - Clean Architecture
  - Single activity architecture using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
  - MVVM
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Coil](https://github.com/coil-kt/coil) - A modern image loading library.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components.

## Installation guide
To run the project you will need to use your own API key. If you don't have one yet, you can register [here](https://open-platform.theguardian.com/access/) to get one.

Then you just have to add your key on the Constants.kt file :

```kotlin
package com.afillionmaillet.theguardian.core.common

object Constants {
    const val API_KEY = "PUT_YOUR_API_KEY_HERE"
    ...
}
```

## Contributions
All contributions are welcome, please fork this repository and contribute using pull requests.

## License
```xml
Designed and developed by Anthony Fillion-Maillet in 2021

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
