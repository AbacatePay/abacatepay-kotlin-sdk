<div align="center">
    <img src="https://i.imgur.com/bnDP5Zx.png" width="300" align="center" alt="drawing"/>
</div>

# AbacatePay Kotlin SDK

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-blue.svg)](http://kotlinlang.org)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io)

The easiest way to integrate your kotlin base code to AbacatePay Gateway with support to coroutines and multiplatform

## Download

### For release version

```kotlin
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.abacatepay:abacatepay-kotlin-sdk:0.0.5'
}
```

## Quick Start

```kotlin
runBlocking {
    val client = AbacatePayClient(apiKey = "your_key")
    client.listBillings()
}

```

## Requirements

- If you are using Android, It needs to be Android 5+.
- Java 11+
- Kotlin 2+

## Credits

This sdk brought to you by [contributors](https://github.com/ssimiao/abacatepay-kotlin-sdk/graphs/contributors).
