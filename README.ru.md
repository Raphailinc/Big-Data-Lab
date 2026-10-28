# Big Data Lab

[![CI](https://github.com/Raphailinc/Big-Data-Lab/actions/workflows/ci.yml/badge.svg)](https://github.com/Raphailinc/Big-Data-Lab/actions/workflows/ci.yml)

[English](README.md) · [Русский](README.ru.md)

Учебный проект на Scala: генерация списка целых чисел, среднее значение и число отклонений от порога.

## Возможности

- Параметры размера списка, диапазона, порога и seed через CLI.
- Расчёт среднего и числа отклонений.
- Модульные тесты и scalafmt через sbt.

## Локальный запуск

```bash
git clone https://github.com/Raphailinc/Big-Data-Lab.git
cd Big-Data-Lab
./sbtw run
```

## Проверки

```bash
./sbtw test
./sbtw check
```

## Условия запуска и границы проекта

Нужен JDK; обёртка скачивает sbt launcher при первом запуске. Несмотря на название репозитория, обработка выполняется в памяти одного процесса: распределённого Spark здесь нет.
