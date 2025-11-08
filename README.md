# OPSC6312-Part-2
# Doodle Note - Advanced Android Notes App

A feature-rich, modern note-taking application built with Kotlin and Jetpack Compose for Android.

## 🌟 Features

### Core Features
- ✅ **Create, Edit, Delete Notes** - Full CRUD operations with rich text editing
- ✅ **Auto-save** - Automatic saving while typing
- ✅ **Search & Filter** - Quick search by title, content, tags, or keywords
- ✅ **Categories & Tags** - Organize notes with custom tags and categories
- ✅ **Date & Time Stamps** - Track creation and modification times
- ✅ **Light/Dark Mode** - Beautiful theme switching with Material You support

### Storage & Sync
- ✅ **Local Storage** - Room database for offline storage
- ✅ **Cloud Sync** - Firebase Firestore for backup and synchronization
- ✅ **Offline Mode** - Full functionality without internet connection
- ✅ **Auto-sync** - Automatic synchronization when connection is restored

### Advanced Features
- ✅ **Rich Text Formatting** - Bold, italics, bullet lists, colors
- ✅ **Multi-language Support** - English, Afrikaans, and Zulu
- ✅ **Push Notifications** - Firebase Cloud Messaging for reminders
- ✅ **Single Sign-On (SSO)** - Google Sign-In integration
- ✅ **Guest Mode** - Use the app without creating an account
- ✅ **Note Locking** - PIN or biometric protection for sensitive notes
- ✅ **Voice Notes** - Record audio notes
- ✅ **Image Attachments** - Add images to your notes
- ✅ **Drawing/Doodle** - Handwriting and drawing support

### UI/UX Features
- ✅ **Material Design 3** - Modern, clean interface
- ✅ **Multiple View Layouts** - List, Grid, or Card view
- ✅ **Swipe Gestures** - Intuitive swipe actions
- ✅ **Floating Action Button** - Quick note creation
- ✅ **Bottom Navigation** - Easy navigation between sections
- ✅ **Responsive Design** - Optimized for phones and tablets

## 📱 Screenshots

The app features:
- Beautiful welcome screen with animations
- Clean authentication screens (Login/Signup)
- Organized home screen with categories
- Rich text editor with formatting options
- Comprehensive settings page

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Firebase project (for cloud features)

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/doodle-note.git
   cd doodle-note
   ```

2. **App Icon**
   - The app uses an adaptive icon with the foreground image from `ic_launcher_foreground.png`
   - The icon is already configured in `mipmap-anydpi-v26/ic_launcher.xml`
   - For custom icons, replace the PNG file in `drawable/ic_launcher_foreground.png`

3. **Firebase Setup**
   - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com)
   - Add an Android app with package name: `com.doodlenote.app`
   - Download the `google-services.json` file
   - Replace the placeholder `google-services.json` in the `app/` directory
   - Enable the following Firebase services:
     - Authentication (Email/Password and Google Sign-In)
     - Cloud Firestore
     - Cloud Storage
     - Cloud Messaging

3. **Google Sign-In Setup**
   - In Firebase Console, go to Authentication > Sign-in method
   - Enable Google Sign-In
   - Copy the Web Client ID
   - Update `AuthRepository.kt` with your Web Client ID

4. **Build and Run**
   - Open the project in Android Studio
   - Sync project with Gradle files
   - Run the app on an emulator or physical device

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture with:
- **Jetpack Compose** for UI
- **Hilt** for dependency injection
- **Room** for local database
- **Firebase** for cloud services
- **Coroutines & Flow** for asynchronous operations
- **Navigation Component** for navigation

### Project Structure
```
app/
├── data/
│   ├── database/     # Room database, DAOs, converters
│   ├── model/        # Data models
│   └── repository/   # Repository implementations
├── di/               # Dependency injection modules
├── presentation/
│   ├── navigation/   # Navigation setup
│   ├── screens/      # UI screens
│   ├── theme/        # Theme configuration
│   └── viewmodel/    # ViewModels
├── services/         # Background services
└── utils/           # Utility classes
```

## 🎨 Customization

### Themes
The app supports Material You dynamic theming. You can customize colors in:
- `presentation/theme/Theme.kt`
- `res/values/colors.xml`

### Languages
Add new languages by creating new string resources:
- Create `res/values-[language_code]/strings.xml`
- Translate all string resources

### Categories
Default categories can be modified in:
- `res/values/strings.xml` (Categories section)

## 📦 Dependencies

Key dependencies include:
- Jetpack Compose BOM
- Room Database
- Firebase BOM
- Hilt for DI
- Coroutines
- DataStore
- Biometric authentication
- WorkManager

See `app/build.gradle.kts` for the complete list.

## 🔒 Security Features

- **App Lock**: PIN or biometric authentication
- **Note Encryption**: Sensitive notes can be locked
- **Secure Storage**: Encrypted preferences using DataStore
- **Firebase Security Rules**: Proper authentication and authorization

## 🌐 Multi-language Support

Currently supported languages:
- 🇬🇧 English (Default)
- 🇿🇦 Afrikaans
- 🇿🇦 Zulu

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Contact

For support or queries, please contact: support@doodlenote.app

## 🙏 Acknowledgments

- Material Design 3 for design guidelines
- Firebase for backend services
- The Android community for inspiration

---

**Note**: Remember to replace placeholder values in `google-services.json` and update the Web Client ID in `AuthRepository.kt` with your actual Firebase configuration.

