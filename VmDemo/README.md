# VmDemo — Ejemplo de ViewModel y Gestión de Estado en Jetpack Compose

Este proyecto es una aplicación de demostración interactiva diseñada para aprender y poner en práctica la **gestión de estado compartido (State Management)** y la arquitectura **ViewModel** en Android con **Jetpack Compose** y **Navigation Compose**.

---

## 📌 Contexto y Problema a Resolver

En el código inicial (*starter code*), cada pantalla (`LoginScreen`, `InicioScreen` y `PerfilScreen`) maneja su propio estado local mediante `remember { mutableStateOf(...) }`:

- **Pérdida de datos en navegación:** Al ingresar el nombre, rol y estancia en `LoginScreen`, los datos se pierden al navegar a `InicioScreen`.
- **Inconsistencia de estado:** Si el usuario modifica la estancia en `PerfilScreen`, `InicioScreen` nunca se entera del cambio.
- **Duplicación de lógica:** Las reglas condicionales (como verificar si el rol es `"Dirección"`) se reescriben en múltiples lugares.

---

## 🎯 Objetivo de Aprendizaje

Refactorizar la aplicación para introducir un **`SesionViewModel`** compartido a nivel de grafo de navegación (`AppNavigation`), logrando:

1. **Unificar el estado de la sesión** en una fuente única de verdad (*Single Source of Truth*).
2. **Elevación de estado (*State Hoisting*):** Hacer que las pantallas sean composables sin estado (*stateless*).
3. **Sincronización en tiempo real:** Los cambios realizados en una pantalla (como `PerfilScreen`) se reflejan inmediatamente en las demás pantallas (`InicioScreen`).

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Navegación:** Jetpack Navigation Compose (`NavHost`, `NavController`)
- **Arquitectura:** MVVM (Model-View-ViewModel) con `StateFlow` / `State`

---

## 📱 Estructura de Pantallas

| Pantalla | Descripción |
| :--- | :--- |
| **`LoginScreen`** | Captura nombre, rol (*Coordinadora*, *Dirección*) y estancia (*Centro*, *Norte*, *Sur*). |
| **`InicioScreen`** | Muestra el saludo personalizado, las métricas del día y opciones adicionales según el rol. |
| **`PerfilScreen`** | Permite actualizar la estancia del usuario y cerrar sesión. |

---

## 📂 Estructura del Proyecto

```text
com.example.vmdemo/
├── data/
│   └── Catalogos.kt           # Colecciones estáticas de Roles y Estancias
├── navigation/
│   ├── AppNavigation.kt       # Grafo de navegación principal
│   └── Rutas.kt               # Definición de rutas ("login", "inicio", "perfil")
├── ui/
│   ├── components/
│   │   └── OpcionesRadio.kt   # Componente reutilizable de selección
│   ├── screens/
│   │   ├── InicioScreen.kt    # Pantalla principal del usuario
│   │   ├── LoginScreen.kt     # Formulario de inicio de sesión
│   │   └── PerfilScreen.kt    # Configuración de perfil
│   └── theme/                 # Configuración de colores y tipografía Material 3
└── MainActivity.kt            # Punto de entrada de la aplicación
```

---

## 🚀 Cómo Ejecutar el Proyecto

1. Clona o abre la carpeta del proyecto en **Android Studio** (versión Ladybug o superior recomendada).
2. Asegúrate de tener instalado el SDK de Android (API 30 o superior).
3. Sincroniza el proyecto con Gradle (**Sync Project with Gradle Files**).
4. Ejecuta la aplicación en un emulador o dispositivo físico con Android 11+ (API 30+).

---

## 💡 Pasos Sugeridos para Completar la Demo

1. Crear una clase `SesionUiState` que contenga `nombre`, `rol` y `estancia`.
2. Crear `SesionViewModel : ViewModel()` con un `StateFlow<SesionUiState>`.
3. Instanciar `SesionViewModel` en `AppNavigation`.
4. Conectar el `ViewModel` a `LoginScreen`, `InicioScreen` y `PerfilScreen`.
