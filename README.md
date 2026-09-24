# ✅ To Do List Mobile

📱 A simple to-do list app for Android, built with Kotlin and Jetpack Compose.

## ✨ Features

- ➕ Add tasks using the **Add** button or the keyboard's **Done** key
- ☑️ Mark tasks as completed by tapping the checkbox or anywhere on the task row
- 🧹 Remove all completed tasks at once with **Clear completed**
- 🛡️ Input validation: tasks must contain at least one letter, so entries made only of spaces or numbers are ignored
- 📜 Scrollable list that keeps the **Clear completed** button right below the tasks

## 🛠️ Tech stack

- Language: [Kotlin](https://kotlinlang.org/)
- UI: [Jetpack Compose](https://developer.android.com/jetpack/compose) with [Material 3](https://m3.material.io/)
- State: Compose state (remember / mutableStateOf)
- Build: Gradle with a version catalog (gradle/libs.versions.toml)

## 📋 Requirements

- A recent version of [Android Studio](https://developer.android.com/studio) that supports AGP 9.0
- Android SDK Platform 36, installed via **Tools → SDK Manager**
- Android Gradle Plugin 9.0.1
- An Android emulator or a physical device with USB/wireless debugging enabled

## 🚀 Getting started

1. Clone the repository:
   ```bash
   git clone https://github.com/guilhermeoliveirateo/ToDoListMobile
   ```
2. Open the project folder in Android Studio.
3. Wait for the Gradle sync to finish.
4. Select an emulator or a connected device and click **Run ▶**.

## 📁 Project structure

```
app/src/main/java/br/com/todolistmobile/
├── MainActivity.kt      # Activity, Task model and the ToDoList screen
└── ui/theme/            # Colors, typography and app theme
```

## ⚙️ How it works

Each task is represented by a data class:

```kotlin
data class Task(
    val id: Long,
    val name: String,
    val isCompleted: Boolean = false
)
```

The list is stored as an immutable `List<Task>` in Compose state. Every change (adding, checking or clearing tasks) creates a new list, which makes Compose redraw the screen. Task ids come from a separate counter, so they stay unique even after tasks are removed.

## 📦 Notes on dependencies

The project is compiled against SDK 36 with AGP 9.0.1. Some newer AndroidX library versions (such as Compose 1.12.x, core-ktx 1.19.x and lifecycle 2.11.x) require compileSdk 37 and AGP 9.1.0 or higher, so they are intentionally not used. If Android Studio suggests updating these libraries, upgrade AGP and compileSdk first.
