# Dependency generator

[![Build Status](https://travis-ci.org/sidvi1/dependency-generator.svg?branch=master)](https://travis-ci.org/sidvi1/dependency-generator)
[![Coverage Status](https://coveralls.io/repos/github/sidvi1/dependency-generator/badge.svg?branch=)](https://coveralls.io/github/sidvi1/dependency-generator?branch=)
[![codebeat badge](https://codebeat.co/badges/636bc418-d43c-439a-8310-2856c9f87f22)](https://codebeat.co/projects/github-com-sidvi1-dependency-generator)
[![Dependency Status](https://www.versioneye.com/user/projects/57af7ebbd6720e004522ab08/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/57af7ebbd6720e004522ab08)
[![License](http://img.shields.io/:license-mit-blue.svg)](http://badges.mit-license.org)

This utility helps you transfer old project on a Maven build system or another.
The one extracts information about the jar library version from various sources:
- MANIFEST.MF
-- Implementation-Version
-- Specification-Version
- pom.xml
-- group, artifact, version from parent tag
-- group, artifact, version itself

Usage
-----
[Download the jar](https://github.com/sidvi1/dependency-generator/releases)
and place the file in any place.

Example usage: 
```
java -jar dg.jar -csv <path_to_jars_directory>
```

Copiright
------
Copyright (c) 2014 Vitaly Sidorov. See LICENSE for further details.