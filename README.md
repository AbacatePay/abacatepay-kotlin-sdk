<div align="center">

# AbacatePay Kotlin SDK

The easiest way to integrate your Kotlin code to **AbacatePay** Payment Gateway with support to coroutines and multiplatform

<img src="https://res.cloudinary.com/dkok1obj5/image/upload/v1767631413/avo_clhmaf.png" width="100%" alt="AbacatePay Open Source Image"/>

Você pode ver documentação completa do SDK [aqui](https://docs.abacatepay.com/pages/sdk/kotlin).

## Download

### For release version

</div>

```kotlin
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.abacatepay:abacatepay-kotlin-sdk:0.0.5'
}
```

<div align="center">

## Quick Start

</div>

```kotlin
runBlocking {
    val client = AbacatePayClient(apiKey = "YOUR_API_KEY")

    client.billing.list()
}
```

<div align="center">

## Requirements

</div>

- Java 17+
- Kotlin 2+
- If you are using Android, It needs to be Android 5+.

<div align="center">
    Feito com 🥑 pela equipe AbacatePay</br>
Open source, de verdade.
</div>
