[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![CI](https://github.com/namshi/NamshiCreditCardInput/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/namshi/NamshiCreditCardInput/actions/workflows/build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.namshi.cardinput/cardinput.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.namshi.cardinput%22%20AND%20a:%22cardinput%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


# NamshiCreditCardInput
## Introduction
UI library to capture credit card numbers, Optimized for GCC region
## Import as dependency

`build.gradle` (it's available on [Maven Central](https://search.maven.org/artifact/com.namshi.cardinput/cardinput)):

#### Gradle Groovy DSL

```gradle
dependencies {
    implementation 'com.namshi.cardinput:cardinput:1.1.0'
}
```

#### Gradle Kotlin DSL

```gradle
dependencies {
    implementation("com.namshi.cardinput:cardinput:1.1.0")
}
```

## Sample code

```xml
        <com.namshi.cardinput.view.CardInputWidget
            android:id="@+id/card_input_widget"
            android:layout_width="match_parent"
            android:layout_height="48dp"
            android:layout_marginTop="-48dp"
            android:layoutDirection="ltr"
            android:paddingStart="10dp"
            android:paddingEnd="10dp" />
```

## Screenshots

<img width="360" src="https://user-images.githubusercontent.com/51527050/118616292-9d990b00-b7d2-11eb-865e-4733021cf5d4.gif"/>



