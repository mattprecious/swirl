Swirl
======

Android's animated fingerprint icon provided in a simple, standalone library.

![](images/sample.gif)


Usage
-----

Since Swirl uses animated vector drawables, default colors cannot be provided and must be specified
in your theme.

```xml
<style name="Theme.YourApp" parent="@android:style/Theme.Material.Light">
  <item name="swirl_ridgeColor">?android:attr/textColorSecondary</item>
  <item name="swirl_errorColor">?android:attr/colorAccent</item>
</style>
```

Then, you may include `SwirlView` anywhere in your app.

```xml
<com.mattprecious.swirl.SwirlView
    android:layout_width="60dp"
    android:layout_height="60dp"
    />
```

Note that this example specifies a width and height and does not use `wrap_content`. Since the
drawables are vectors, there isn't an appropriate size to default to so you must specify one.

Switch between icons by calling `setState()` or by using the `app:swirl_state` attribute.

See the provided sample for a complete implementation.


Download
--------

Gradle:

```groovy
implementation 'com.mattprecious.swirl:swirl:1.3.0'
```


License
--------

    Copyright 2016 Matthew Precious

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

