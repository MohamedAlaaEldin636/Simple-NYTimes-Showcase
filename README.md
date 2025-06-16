# Simple NYTimes Showcase

- [How to Build The Project](#how-to-build-the-project)
- [Project Overview](#project-overview)

## How to Build The Project

- You will need to create a `local.properties` file, with below data replacing values with correct
  implementations of them.
  - API_SERVICE_NYTIMES_API_KEY={yourApiKey}
  - API_SERVICE_NYTIMES_API_BASE_URL=baseUrl
  - sdk.dir=yourDirPath

## Project Overview

- This Repo contains a simple android app just to showcase coding using **_NYTimes Rest API_** inshallah.

- Uses Clean Architecture in the project with modularization by feature and MVI design pattern.
  - Note since we will implement Clean architecture then we need dependencies to point inward.
  - will also modularize by feature meaning each feature is a self-contained module
  - Basically will contain 3 modules (domain, data & presentation), However actually will have more to be 
  scalable when we need to add more features inshallah.
  - Note we will use name of ui instead of presentation (same concept so a shorter name is easier)

- Modules (Note in this example there is only 1 feature)
  - Core
    - `:core:kotlin` -> contains utilities related to kotlin programming language only
      - Optional but important if needed
    - `:core:android` -> contains utilities related to android library module
      - Optional but important if needed
      - Not added in this project
  - Domain
    - `:domain:shared` -> for common models used among several features
      - Optional but important if needed
    - `:domain:articles` -> for models & repos interfaces for articles feature
  - Data
    - `:data:shared` -> for common code used among several features
      - Optional but important if needed
    - `:data:articles` -> implements repos signatures defined in the corresponding domain articles 
    feature by getting the data from the correct sources whether remote, local or both
  - UI (Presentation)
    - `:ui:shared` -> for common code used among several features
      - Optional but important if needed
    - `:ui:articles` -> implements screens representing articles feature
  - Feature
    - `:feature:shared` -> Android module, uses hilt to wire above dependencies together, shared 
    submodule specifically adds hilt dependencies needed by all features example core network 
    requirements like retrofit instance.
    - `:feature:articles` -> Android module, uses hilt to wire above dependencies together so that 
    it reduces work in `:app` modules by making the feature cleaner as it handles even DI for itself 
    and make `:app` dependencies become less, and exposes only the ui module of the feature, instead 
    of making `:app` module depend on all 3 ui, domain & data to correctly use Hilt inshallah.
      - Optional but important if needed
    - `:feature:combinedModules` -> implements all other `:feature:*` modules to add hilt injection
    for any dependency that need to have access for all other modules like Room Database instance.
  - App
    - `:app` -> entry point of the application and contains the navigation layer to tie all features 
    and even the screens in a single feature inshallah.

- Note approach could 've been like
  - `:domain`
  - `:data`
  - `:ui`
  - `:app`
  - But I wanted to apply the previous one to implement the self-contained feature 
  concept for the modularization by feature

- Additional Note, In a large app there even can be test module to test 2 or more related features
and not whole app by residing in `:test:scenario_name` module if needed

- Test Cases
  - Made only in `:domain:articles` as I had small amount of time in development due to being 
  busy in the previous days with personal stuff.
