# eoconfig

Lightweight, immutable and composable configuration library for Java.

`eoconfig` helps you read and compose configuration in a clean and testable way.

[![Build Status](https://github.com/RoRoche/eoconfig/actions/workflows/build-java.yml/badge.svg)](https://github.com/RoRoche/eoconfig/actions)
[![YAML Lint](https://github.com/RoRoche/eoconfig/actions/workflows/yamllint.yml/badge.svg)](https://github.com/RoRoche/eoconfig/actions/workflows/yamllint.yml)
![Nodes.js CI](https://github.com/RoRoche/eoconfig/actions/workflows/build-npm.yml/badge.svg)

![EO principles respected here](https://www.elegantobjects.org/badge.svg)
[![DevOps By Rultor.com](https://www.rultor.com/b/RoRoche/eoconfig)](https://www.rultor.com/p/RoRoche/eoconfig)
![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)

[![PDD status](https://www.0pdd.com/svg?name=RoRoche/eoconfig)](https://www.0pdd.com/p?name=RoRoche/eoconfig)

[![codecov](https://codecov.io/gh/RoRoche/eoconfig/branch/main/graph/badge.svg)](https://codecov.io/gh/RoRoche/eoconfig)

[![Hits-of-Code](https://hitsofcode.com/github/RoRoche/eoconfig?branch=main)](https://hitsofcode.com/github/RoRoche/eoconfig/view?branch=main)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=bugs)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=RoRoche_eoconfig&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=RoRoche_eoconfig)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.roroche/eoconfig.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.github.roroche/eoconfig)
[![Javadoc](https://javadoc.io/badge2/com.github.roroche/eoconfig/javadoc.svg)](https://javadoc.io/doc/com.github.roroche/eoconfig)

## ✨ Features

- Immutable configuration objects
- Fluent API
- Type-safe accessors
- Composable configuration sources
- Easy unit testing

## 📥 Installation

Add the dependency to your project:

```xml
<dependency>
    <groupId>com.github.roroche</groupId>
    <artifactId>eoconfig</artifactId>
    <version>${latest.version}</version>
</dependency>
```

## 🚀 Usage

- `Configuration` is the main interface for accessing configuration values.
- `ConfigurationOf` is for creating `Configuration` instances from `Properties`.
- `EnvironmentConfiguration` reads configuration from environment variables.
- `FileConfiguration` reads configuration from a file.
- `HoconConfiguration` reads configuration from a HOCON file.
- `MapConfiguration` reads configuration from a `Map`.
- `OverlayConfiguration` composes two `Configuration` instances, giving precedence to the first one.
- `YamlConfiguration` reads configuration from a YAML file.

## 🤝 Contributing

Contributions are welcome!

If you'd like to report a bug, suggest a feature, or submit a pull request, please read our
👉 **[Contributing Guide](CONTRIBUTING.md)**

It contains everything you need to know about:

- Development setup
- Coding standards
- Commit conventions
- Pull request process
- Quality requirements

Thank you for helping improve `eoconfig` 🚀

## 📄 License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
