# Android Project

This Android project is built using the **MVI (Model-View-Intent)** pattern and follows a **clean architecture**. The application is structured into multiple modules to ensure scalability and maintainability.

## Features
- **MVI Architecture**: Ensures a unidirectional data flow and clear separation of concerns.
- **Multi-Module Architecture**: Decouples features and layers for better maintainability.
- **Modern Android Development**: Utilizes Jetpack Compose, Navigation Component, Material Design 3, and Kotlin for a modern development experience.
- **Dependency Injection**: Powered by Dagger for modular and testable code.
- **Networking**: Retrofit and OkHttp are used for API communication.

## Technologies Used

### UI Layer
- **Jetpack Compose**: For declarative UI development.
- **Material Design 3**: For consistent and modern design principles.
- **Navigation Component**: For in-app navigation.

### State Management
- **StateFlow**: For reactive state handling.
- **ViewModel**: To manage UI-related data lifecycle-aware.

### Dependency Injection
- **Dagger**: For managing dependencies across modules.

### Networking
- **Retrofit**: For API communication.
- **OkHttp**: For HTTP client operations.

### Core Libraries
- **AndroidX Libraries**: Core components for building modern Android applications, including lifecycle, fragment, and appcompat.
- **Kotlin Extensions (KTX)**: For concise and idiomatic Kotlin code.

### Domain Layer
- Encapsulates business logic and use cases.

## Project Modules
The project is divided into the following modules:

1. **App Module**: Contains the application-level configuration and entry point.
2. **Domain Module**: Contains business logic and use cases.
3. **Other Feature Modules**: Each feature is encapsulated in its own module to promote modularity.

## Architecture Overview

### Clean Architecture Layers
1. **Presentation Layer**:
    - Implements the UI with Jetpack Compose.
    - Uses `StateFlow` and `ViewModel` to manage state.
    - Follows the MVI pattern to handle user interactions.

2. **Domain Layer**:
    - Contains use cases and business logic.
    - This layer is completely independent of the platform.

3. **Data Layer**:
    - Manages data sources such as APIs, databases, and shared preferences.
    - Uses Retrofit for API communication and Dagger for dependency injection.

### MVI Components
The project implements a robust MVI pattern with the following components:

- **UiState**: Represents the current state of the UI.
- **Event**: Represents events triggered within the system, including:
    - **User Events**: Actions initiated by the user, such as clicks or selections.
    - **Domain Events**: Internal events representing changes in the system or results of asynchronous operations.
- **SideEffect**: Represents side effects triggered by the application, such as navigation or API calls.
- **UiCommand**: Represents commands sent to the UI layer for specific actions.
- **Reducer**: Contains the logic for transitioning from one `UiState` to another based on `Event` and generates `SideEffect` or `UiCommand` if needed.
- **MviViewModel**:
    - Manages the state and handles `Event` processing using the `Reducer`.
    - Provides a `StateFlow` to observe the current state.
    - Interacts with `SideEffectHandler` to manage side effects and UI commands.

### MviViewModel Workflow
1. **Initialization**:
    - Sets up the `StateFlow` with the initial state from the `Reducer`.
    - Processes initial events defined by the `Reducer`.

2. **State Updates**:
    - Receives `Event` and reduces it into a new `UiState` using the `Reducer`.
    - Updates the `StateFlow` with the new state.

3. **Side Effects**:
    - Uses a `SideEffectHandler` to handle asynchronous operations or UI-specific actions.