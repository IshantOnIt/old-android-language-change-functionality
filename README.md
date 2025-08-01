# Language Test App

This is a simple, manually configured Android project designed to demonstrate how to handle runtime language changes using native Android resources. The app features a basic layout with two buttons that allow the user to switch the app's display language between English and French.

### How to Run the Project

This project does not rely on an integrated development environment (IDE) like Android Studio. Instead, it is configured to be built and installed from the command line using Gradle and ADB.

#### Prerequisites
* Android SDK installed and configured in your system's PATH.
* A connected Android device or a running emulator.

#### Build and Install
1.  Open a terminal or command prompt and navigate to the root directory of the project (`LanguageTestApp`).
2.  Run the following command to build the debug APK:
    ```bash
    ./gradlew assembleDebug
    ```
    (On Windows, use `gradlew.bat assembleDebug`)
3.  Once the build is successful, install the app on your device using ADB:
    ```bash
    adb install -r app/build/outputs/apk/debug/app-debug.apk
    ```

### How to Add More Languages

The app's architecture is designed to make adding new languages straightforward. The core logic in `MainActivity.java` handles locale changes by looking for the appropriate resource files.

To add a new language (e.g., Spanish):

1.  **Create a New Resource Directory:**
    * In the `app/src/main/res/` folder, create a new directory with the `values-[language_code]` format. For Spanish, this would be `values-es`.

2.  **Create a `strings.xml` File:**
    * Inside the new `values-es` directory, create a `strings.xml` file.

3.  **Add Translations:**
    * Add the Spanish translations for the app's strings, including `app_name` and `welcome_message`, to the `strings.xml` file.

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <string name="app_name">Aplicación de prueba manual</string>
        <string name="welcome_message">¡Bienvenido a la aplicación manual!</string>
    </resources>
    ```

4.  **Update the UI and Logic (if necessary):**
    * If you want a new button for the language, add a new `Button` in `app/src/main/res/layout/activity_main.xml`.
    * In `MainActivity.java`, you would add a new `OnClickListener` for the new button and call `setLocale("es")`.

After these changes, rebuild and reinstall the app, and you will see the new language option.
