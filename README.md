# Clean Architecture with Jetpack Compose

This repository provides an implementation of the clean architecture pattern using Jetpack Compose for the UI layer. The project showcases how to implement a modularized architecture in Android using feature modules, Jetpack Compose Navigation, and Kotlin Coroutines.

# Project Overview
This project implements a simple product list and detail app. The app has a simple UI with a list of products and a details screen to show more information about each product.

The project is structured into several modules based on features:

* app: This module is the main entry point for the application.
* core: This module contains the core components and utilities that are used throughout the app.
* features: This module contains the feature-specific modules.
  * order: This module contains the order feature implementation.

# Architecture
This project follows the clean architecture pattern with the following layers:

* Presentation
* Domain
* Data

## Presentation Layer
The presentation layer contains the UI implementation using Jetpack Compose. It is responsible for displaying the data to the user and receiving the user input. The presentation layer interacts with the domain layer to get the data to display.

## Domain Layer
The domain layer contains the business logic of the application. It is responsible for handling the user actions and interacting with the data layer to fetch or update the data. The domain layer defines the use cases of the application.

## Data Layer
The data layer contains the implementation of the data sources. It is responsible for fetching the data from the server using Retrofit. The data layer provides an abstraction for the domain layer to access the data.

# Tech Stack
The following technologies are used in this project:

* Jetpack Compose
* Kotlin Coroutines
* Retrofit
* Jetpack Compose Navigation

# Getting Started
To run this project, clone the repository and open it in Android Studio. Then, run the app on an emulator or a physical device.

