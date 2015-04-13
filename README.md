Dagger2Scopes [![Build Status](https://travis-ci.org/JorgeCastilloPrz/Dagger2Scopes.svg?branch=master)](https://travis-ci.org/JorgeCastilloPrz/Dagger2Scopes)
=============
Dagger2Scopes is a sample Android app to implement usual Dagger 1 scoping logic with the brand new Dagger 2 Google lib. In this sample you will be able to find
the following features:

* Multiple scopes with Dagger 2 (Including Application and Activity scopes to provide both contexts).
* An approach to Clean Architecture.
* An approach to MVP pattern.

DI structure
------------
Dagger 2 erases the dagger 1 graph concept by introducing the brand new components. The components available in this sample app are the following:
* `ApplicationComponent`: Like in the Google Dagger 2 samples, this component will be used to expose application context and generic dependencies
to components depending on this one or to child components. This component will be used to inject `Dagger2ScopesApp` and activities/fragments by
component composition.
* `AbstractActivityComponent`: This one is used as an abstract activity scoped component and exposes activity context and common dependencies
linked to activity lifecycle, like `Navigator` or `ToolbarAnimator`.
* `GameListActivityComponent`: Extends `AbstractActivityComponent` to provide game list related dependencies.
* `GameDetailsActivityComponent`: Extends `AbstractActivityComponent` to provide game details related dependencies.

All the activity scoped components are sharing the custom `@ActivityScope`.

Attributions
------------
* Dagger 2 component and module structure based on [Dagger 2 Google Samples][dagger2-samples].
* Project inspired by this [blog entry][fernando-cejas-blogentry] from [@android10][fernando-cejas-github].

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