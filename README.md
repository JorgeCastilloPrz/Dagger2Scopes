[![Build Status](https://travis-ci.org/JorgeCastilloPrz/Dagger2Scopes.svg?branch=master)](https://travis-ci.org/JorgeCastilloPrz/Dagger2Scopes) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Dagger2Scopes-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/1717)

![Dagger2Scopes image](/art/dagger2scopes.png?raw=true)

Dagger2Scopes is a sample Android app to implement usual Dagger 1 multiple scoping logic with the brand new Dagger 2 Google lib. In this sample you will be able to find
the following features:

* Multiple scopes with Dagger 2 (Components and subcomponents).
* An approach to Clean Architecture.
* An approach to MVP pattern.

Dependency Injection
--------------------
Dagger 2 erases the dagger 1 graph concept by introducing the brand new components. The components available in this sample app are the following:
* `ApplicationComponent`: This component will be used to expose application context and generic dependencies to components depending on this one or
to child components. It will be used to inject `Dagger2ScopesApp` and activities/fragments by component composition.
* `AbstractActivityComponent`: This one is used as an abstract activity scoped component and exposes activity context and common dependencies
linked to activity lifecycle, like `Navigator` or `ToolbarAnimator`.
* `GameListActivityComponent`: Extends `AbstractActivityComponent` to provide game list related dependencies.
* `GameDetailsActivityComponent`: Extends `AbstractActivityComponent` to provide game details related dependencies.

All the activity scoped components are sharing the custom `@ActivityScope`.

Clean
-----
This sample is modeled using the [Uncle Bob's Clean Arquitecture approach][clean-arquitecture-post]. To reach the main goals of **Clean**, i am including the
 following modules:

 * **android**: This one contains the ui graphics and platform implementations for some dependencies defined in the domain layer, like the `Navigator`.
 Dependency injection is defined here too.
 * **presentation** (java): Presentation logic for the application is held here. It is the layer used to decouple the graphic view details from the model and the
 classes which work with it.
 * **domain** (java): The business logic of the app. Here, you will also find the use cases (interactors), threading logic, and some boundaries defined by interfaces to implement in other
 modules, like the `Navigator` or the `GameRepository`.
 * **repository** (java): My implementation for the repository is defined here.
 * **datasources** (java): Data source implementations.

Every dependency is provided by the dependency injection framework which maximizes the power of **Inversion of Control** principle. By this way,
the dependencies **always** point from the outer layers to the inner ones. There aren't any inner layer classes depending on outer ones.

Every layer has his very own entity mapper to provide the inner layer inmediately next to it with the entity forms most adequated to it. This is done by
that way to not violate de dependency rules. That is mentioned by **Uncle Bob**:

*"...That would violate The Dependency Rule because it would force an inner circle to know something about an outer circle. So when we pass data across
a boundary, it is always in the form that is most convenient for the inner circle."*


Attributions
------------
* Dagger 2 component and module structure based on [Dagger 2 Google Samples][dagger2-samples].
* Project inspired by this [blog entry][fernando-cejas-blogentry] from [@android10][fernando-cejas-github].
* Clean arquitecture composition inspired by [Clean-Contacts][panavtec-clean-contacts] project by [@PaNaVTEC][panavtec-github].

Developed By
------------
* Jorge Castillo Pérez - <jorge.castillo.prz@gmail.com>

<a href="https://www.linkedin.com/in/jorgecastilloprz">
  <img alt="Add me to Linkedin" src="https://github.com/JorgeCastilloPrz/EasyMVP/blob/master/art/linkedin.png" />
</a>

License
-------

    Copyright 2015 Jorge Castillo Pérez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[dagger2-samples]: https://github.com/google/dagger
[fernando-cejas-blogentry]: http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/
[fernando-cejas-github]: https://github.com/android10
[clean-arquitecture-post]: http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
[panavtec-github]: https://github.com/PaNaVTEC
[panavtec-clean-contacts]: https://github.com/PaNaVTEC/Clean-Contacts