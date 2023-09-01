# Animemania

[![Maintainability](https://api.codeclimate.com/v1/badges/04b18b9e1d326f2b6c59/maintainability)](https://codeclimate.com/github/TheBlackBit/Animemania/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/04b18b9e1d326f2b6c59/test_coverage)](https://codeclimate.com/github/TheBlackBit/Animemania/test_coverage)
<br><br>
<img src="https://github.com/TheBlackBit/resources/blob/main/20230822_194658.gif?raw=true" alt="image" width="auto" height="400">
<br><br>
<a href="https://play.google.com/store/apps/details?id=com.theblackbit.animemania.android" target="_blank"><img src="https://github.com/TheBlackBit/resources/blob/main/play_store_button.png?raw=true" alt="image" width="175" height="auto"/><a/>
<br>
Android app that collect information about Anime and Manga using Kitsu API<br><br>
It is **work in progress** 🚧.<br><br>
Api docs: https://hummingbird-me.github.io/api-docs/
<br>

## Configuration
After cloning the repository you have to create the file `secrets.defaults.properties` in the root of the project with the next properties:

- KITSU_API_URL="https://kitsu.io/api/edge/" (In this case we expose the url because is public)
- ALIAS = Example
- KEY_PASS = Example
- KEY_ALIAS = Example

The ALIAS, KEY_PASS, KEY_ALIAS keys are if you want to run the app in build type release but you have to generate a keystore and store it in app module with the name `keystore.jks` and when you generate your keystore.jks add the keys that you store in `secrets.defaults.properties`

## Contributing
Follow [this](https://github.com/TheBlackBit/Animemania/blob/master/CONTRIBUTING.md) guidelines

## Architecture

It follows a clean architecture with modularization in order to have this principles:

- Single source of truth.
- Testability.
- Separation of concerns.

## Modularization

<img src="https://github.com/TheBlackBit/resources/blob/main/MODULES_ANIMEMANIA.jpg?raw=true" alt="image" width="500" height="auto">

Following the image we have the next modules:

- `:App`
- `:core:data`
- `:core:domain`
- `:core:util`
- `:core:model`
- `:core:testing`
- `:core:resources`
- `:feature:home`
- `:feature:detail`

And also it has a special module that hanldes all the gradle configuration it's the module [`:build-src`](buildSrc) (all the gradle files are made with kotlin dsl) in order to have a single source of truth in our gradle files module.

## Libraries

All the libraries are in versions catalog. Ref: [https://developer.android.com/build/migrate-to-catalogs](https://developer.android.com/build/migrate-to-catalogs)

## Anatomy
### Networking

For networking, we use retrofit. RxJava for reactivity.

### Pagination

For pagination we use [paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=es-419) library, and for handle cache and source of data to present we use [Room](https://developer.android.com/training/data-storage/room?hl=es-419)
#### Caching
<img src="https://github.com/TheBlackBit/resources/blob/main/cache.jpg?raw=true" alt="image" width="500" height="auto">

### Ui

For ui we use:
- XML.
- MotionLayout.
- Navigation component.
- Navigation transition animation.
- DataBinding.
- ViewBinding.
- Single Activity architecture.

#### Architectural pattern
The architectural pattern that we use is `MVVM`.

#### Dependency injection
For dependency injection library used is [`Koin`](https://insert-koin.io/)


## Testing

- Mockito
- Esspresso for ui test

## CI/CD

For CI/CD we use [Fastlane](https://fastlane.tools/) in order to create the lanes, pipes or actions. With fastlane we can run the pipeline locally but in this case we use Github Actions in order to execute the lanes.

### Pipeline for develop branch:

`Ktlint(Format, Verify)` - `Unit Test verify` - `Code Coverage(Jacoco)` - `Ui Test in develop(Firebase Test Lab Espresso)`

### Pipeline for master branch:
`Ktlint(Format, Verify)` - `Unit test verify` - `Code Coverage(CodeClimate)` - `Code analysis(CodeClimate)` - `Ui Test in develop(Firebase Test Lab Espresso, Robotest)`

### Pipeline Release App
For delivery we have to create a new Github Release when the tag is pushed in master with any of these version names:
- `v*.*.*-alpha*` in progress..
- `v*.*.*-beta*`
- `v*.*.*-production*` in progress..

`Unit test verify` - `Ui Test in develop(FirebaseTest Lab Espresso, Robotest)` - `Deliver to playstore`

## License

Animemania is created under the terms of the Apache License (Version 2.0). See the [license](license.txt) for more information.
