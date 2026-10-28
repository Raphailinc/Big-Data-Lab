# Big Data Lab

[![CI](https://github.com/Raphailinc/Big-Data-Lab/actions/workflows/ci.yml/badge.svg)](https://github.com/Raphailinc/Big-Data-Lab/actions/workflows/ci.yml)

[English](README.md) · [Русский](README.ru.md)

A Scala learning project that generates a list of integers, computes its mean and counts values beyond a deviation threshold.

## What it does

- Set list size, value range, threshold and seed from the CLI.
- Compute the sample mean and deviation count.
- Run unit tests and scalafmt through sbt.

## Run locally

```bash
git clone https://github.com/Raphailinc/Big-Data-Lab.git
cd Big-Data-Lab
./sbtw run
```

## Checks

```bash
./sbtw test
./sbtw check
```

## Scope and implementation notes

Requires a JDK; the wrapper downloads the sbt launcher when needed. Despite the repository name, this exercise runs locally on an in-memory list and does not implement distributed Spark processing.
