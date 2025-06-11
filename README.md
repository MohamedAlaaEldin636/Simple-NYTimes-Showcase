# Simple NYTimes Showcase

- This Repo contains a simple android app just to showcase coding using NYTimes Rest API inshallah.

- (Currently under development) it will eventually show the project by implementing clean 
architecture to the project with modularization by feature and MVI design pattern and it will 
contain several modules, Note since we will implement Clean architecture then we need dependencies
to point inward, and we will also modularize by feature meaning each feature is a self-contained 
module, (in this example we have only 1 feature which is articles),
So expected modules will be as follows
  - :feature:articles:domain
    - Pure kotlin module, contains entities(models), repos interfaces and optional use cases.
    - has no dependencies
  - :feature:articles:data
    - Android module, contains implementation of the repos and handle data sources whether local 
    or remote.
    - depends on :domain:articles
  - :feature:articles:ui (same as :presentation:articles)
    - Android module, contains screens related to this feature.
    - depends on :domain:articles
  - :feature:articles
    - Android module, uses hilt to wire above dependencies together so that it reduces work in :app 
    modules by making the feature cleaner as it handles even DI for itself and make :app dependencies
    become less.
    - depends on :domain:articles, :data:articles, :ui:articles
    - exposes :ui:article -> to be used by :app
  - :app -> Entry point for the application and also contains the navigation layer
    - depends on all feature modules to perform navigation on them as intended inshallah 
    - depends on :feature:articles

- Note approach could 've been like
  - :domain
  - :data
  - :ui
  - :app
  - But I wanted to apply the previous one to implement the self-contained feature 
  concept for the modularization by feature

- Additional Note about above modules, in a full application there can be more modules like
  - :core:kotlin -> for common pure kotlin code utilities
  - :core:ui -> for common business agnostic composables
  - :shared:domain -> for shared models among several features
  - :shared:data -> for shared remote or local fetching ex. common safe function of `safeApiCall`
