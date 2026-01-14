package example

import scala.util.Random

object Main {

  final case class Config(
      size: Int = 20,
      maxValue: Int = 100,
      diffThreshold: Double = 5.0,
      seed: Option[Long] = None
  )

  private val usage: String =
    """Usage: sbt "run --size 20 --max 100 --diff 5 --seed 42"
      |
      |Options:
      |  --size <int>   size of generated list (default: 20)
      |  --max <int>    max value (exclusive) (default: 100)
      |  --diff <num>   diff threshold from average (default: 5)
      |  --seed <long>  RNG seed for reproducible results
      |  --help         show this help
      |""".stripMargin

  def main(args: Array[String]): Unit = {
    if (args.contains("--help") || args.contains("-h")) {
      println(usage)
      return
    }

    parseArgs(args.toList) match {
      case Left(error) =>
        System.err.println(error)
        System.err.println(usage)
        System.exit(1)
      case Right(config) =>
        val rng = config.seed.fold(new Random())(new Random(_))
        val values = Stats.generateRandomList(config.size, config.maxValue, rng)
        val result = Stats.analyze(values, config.diffThreshold)

        val thresholdText =
          if (config.diffThreshold.isWhole) config.diffThreshold.toLong.toString
          else config.diffThreshold.toString

        println(s"Случайный список: ${result.values}")
        println(s"Среднее значение: ${result.average}")
        println(
          s"Количество элементов, отличающихся от среднего на $thresholdText: ${result.diffCount}"
        )
    }
  }

  private def parseArgs(args: List[String]): Either[String, Config] = {
    def parseInt(value: String, key: String): Either[String, Int] =
      value.toIntOption.toRight(s"Invalid value for $key: '$value'")

    def parseLong(value: String, key: String): Either[String, Long] =
      value.toLongOption.toRight(s"Invalid value for $key: '$value'")

    def parseDouble(value: String, key: String): Either[String, Double] =
      value.toDoubleOption.toRight(s"Invalid value for $key: '$value'")

    def validate(config: Config): Either[String, Config] =
      if (config.size <= 0) Left("--size must be > 0")
      else if (config.maxValue <= 0) Left("--max must be > 0")
      else if (config.diffThreshold < 0) Left("--diff must be >= 0")
      else Right(config)

    def loop(rest: List[String], config: Config): Either[String, Config] =
      rest match {
        case Nil => validate(config)
        case "--size" :: value :: tail =>
          parseInt(value, "--size").flatMap(v => loop(tail, config.copy(size = v)))
        case "--max" :: value :: tail =>
          parseInt(value, "--max").flatMap(v => loop(tail, config.copy(maxValue = v)))
        case "--diff" :: value :: tail =>
          parseDouble(value, "--diff").flatMap(v => loop(tail, config.copy(diffThreshold = v)))
        case "--seed" :: value :: tail =>
          parseLong(value, "--seed").flatMap(v => loop(tail, config.copy(seed = Some(v))))
        case unknown :: _ =>
          Left(s"Unknown argument: '$unknown'")
      }

    loop(args, Config())
  }
}
