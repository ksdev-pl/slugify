# slugify

[![Latest Version on Maven][ico-maven]][link-maven]
[![Software License][ico-license]](LICENSE)
[![Build Status][ico-travis]][link-travis]

Create a URL/filesystem-friendly version of a string.

## Install

Via Maven

```xml
<dependency>
  <groupId>pl.ksdev</groupId>
  <artifactId>slugify</artifactId>
  <version>0.2</version>
</dependency>
```

Via Gradle

```groovy
compile 'pl.ksdev:slugify:0.2'
```

## Usage

```java
SlugService slugService = new SimpleSlugService();
String result = slugService.slugify("Zażółć gęślą jaźń");
System.out.println(result);                                 // "zazolc-gesla-jazn"

SlugService slugService = new SimpleSlugService(5);         // Adds a max slug length (default = 200)
String result = slugService.slugify("1234567890");
System.out.println(result);                                 // "12345"
```

## License

The MIT License (MIT). Please see [License File](LICENSE) for more information.

[ico-license]: https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square
[ico-travis]: https://img.shields.io/travis/ksdev-pl/slugify/master.svg?style=flat-square
[ico-maven]: https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/pl/ksdev/slugify/maven-metadata.xml.svg?style=flat-square

[link-travis]: https://travis-ci.org/ksdev-pl/slugify
[link-maven]: http://search.maven.org/#artifactdetails%7Cpl.ksdev%7Cslugify%7C0.2%7Cjar