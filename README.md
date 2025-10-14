# OPSC6312-Part-2
Doodle Notes App 🎨
A beautiful and intuitive drawing application for Android that lets you create, save, and share your digital doodles and sketches.

Features ✨
Smooth Drawing: Natural and responsive drawing experience with customizable brush sizes

Multiple Colors: Choose from various colors for your creative expressions

Adjustable Brush Size: Control your brush thickness from fine lines to bold strokes

Save & Load: Save your masterpieces and load them later for editing

Clear Canvas: Start fresh with a clean slate

Material Design: Modern, user-friendly interface following Material Design guidelines

Installation 🚀
Prerequisites
Android Studio Arctic Fox or later

Android SDK 21 or higher

Kotlin plugin installed

Steps
Clone or download this project

Open Android Studio

Select "Open an existing project"

Navigate to the downloaded folder and open it

Wait for Gradle sync to complete

Build and run the app on your device or emulator

Usage 🎯
Drawing: Simply touch and drag on the canvas to draw

Changing Colors: Tap on any color circle at the top

Adjusting Brush Size: Use the slider to make your brush thicker or thinner

Saving: Tap "Save" to store your drawing

Loading: Tap "Load" to browse and open previously saved drawings

Clearing: Tap "Clear" to start a new drawing

Technical Details 🔧
Built With
Kotlin - Primary programming language

Android SDK - Native Android development

Material Components - Modern UI components

Custom Views - Optimized drawing canvas

Architecture
Single Activity architecture

Custom View for drawing surface

Internal storage for data persistence

Material Design components

Key Components
DrawingView.kt - Custom drawing canvas

MainActivity.kt - Main application logic

activity_main.xml - User interface layout

File Structure 📁
text
app/
├── src/main/
│   ├── java/com/example/doodlenotes/
│   │   ├── MainActivity.kt
│   │   └── DrawingView.kt
│   ├── res/
│   │   ├── layout/activity_main.xml
│   │   ├── drawable/color_selector.xml
│   │   └── values/
│   └── AndroidManifest.xml
Permissions 🔒
The app requires:

READ_EXTERNAL_STORAGE - For loading saved drawings

WRITE_EXTERNAL_STORAGE - For saving drawings to device storage

Contributing 🤝
We welcome contributions! Please feel free to submit pull requests or open issues for bugs and feature requests.

How to Contribute
Fork the project

Create your feature branch (git checkout -b feature/AmazingFeature)

Commit your changes (git commit -m 'Add some AmazingFeature')

Push to the branch (git push origin feature/AmazingFeature)

Open a Pull Request

Future Enhancements 🚧
Planned features for future versions:

Undo/Redo functionality

Eraser tool

Different brush styles

Background patterns

Share drawings directly

Cloud synchronization

Drawing galleries

Export to PDF/PNG

Layers support

Custom color picker

Troubleshooting 🔧
Common Issues
App crashes on save: Ensure storage permissions are granted

Drawing lag: Try reducing brush size or closing background apps

Can't load drawings: Check if drawings exist in internal storage

Debugging
Enable developer options on your device

Check Logcat for error messages

Ensure minimum SDK requirements are met
