# App Cubicados

_Aplicación para cubicar superficies en el area de la construcción_

### Pre-requisitos 📋

_Primero es Importar las siguientes dependencias al Gradle del proyecto_

```
dependencies {

    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.google.android.material:material:1.4.0-alpha02'
    implementation 'me.relex:circleindicator:2.1.6'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

}
```
_Segundo es configurar el AndroidManifest.xml esto es para el acceso de Internet_

```
    <uses-permission android:name="android.permission.INTERNET" />
```
