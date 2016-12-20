# font-utils

[![Download](https://api.bintray.com/packages/mattsilber/maven/font-utils/images/download.svg) ](https://bintray.com/mattsilber/maven/font-utils/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-font--utils-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4049)

The goal of this library is to allow your app to support multiple FontFamilies (e.g. lato, roboto, etc.) with their own styles (e.g. normal, bold, italic) in an easily-configurable way.  


### Installation

```groovy
repositories {
    jcenter()
}

dependencies {
    compile('com.guardanis:font-utils:1.0.2')
}
```

### Usage

First, make sure your fonts are located in your *assets* folder (in a /fonts/ subdirectory to be organized). Let's assume you have a roboto normal/bold font called *robo.ttf* and *robo_bold.ttf*, respectively. Now you need to override the *R.array.fu__font_family_resources* string-array resource to include your fonts:

```xml
<string-array name="fu__font_family_resources">
    <item>roboto--normal=fonts/robo.ttf,bold=fonts/robo_bold.ttf</item>
</string-array>
```

The format here is:

```xml
<item>FONT_FAMILY_NAME--STYLE_1=FONT1_PATH,STYLE_2=FONT2_PATH</item>
```

With the supported styles being

* NORMAL
* BOLD
* ITALICS
* BOLD_ITALICS

Values in the resources are not case-sensitive, and their comparing integers are static in the Typeface class (e.g. Typeface.BOLD or Typeface.ITALICS).

Now, we need to ensure we have a default FontFamily correctly set by overring *R.string.fu__default_font_family*:

```xml
<string name="fu__default_font_family">roboto</string>
```

Next, we have 3 choices for how to use this font. We can either

###### Use the already-overridden classes for you

```xml
<com.guardanis.fontutils.TextView 
    xmlns:app="http://schemas.android.com/apk/res-auto"
    app:textFontFamily="roboto"
    android:textStyle="bold" />

```

These convenience helper classes include
* com.guardanis.fontutils.TextView
* com.guardanis.fontutils.Button
* com.guardanis.fontutils.EditText

Check them out if you want to implement them for other types of TextViews.

###### Use the FontUtils attribute in your own custom classes so they support your fonts by default

Call the following from your custom TextView (or TextView subclass)'s constructor.
 
```java
FontUtils.getInstance(getContext())
                .load(this, attrs, defStyle);

```
###### Manually call one of the FontUntils' helper methods to set the font programmatically

```java
TextView view = (TextView) findViewById(R.id.some_id);
FontUtils.getInstance(getContext())
                .setTypeface(view, fontFamily, style);
```

### ToDos and Notes
* A NORMAL font is required for a typeface to be loaded. If the FontFamily is loaded without a normal font, it will throw a RuntimeException letting you know what went wrong.
* Trying to load any non-normal font style that doesn't exist will default to the normal style.
