package example

import scala.util.Random

object Stats {

  final case class Result(values: List[Int], average: Double, diffCount: Int)

  def generateRandomList(size: Int, maxValueExclusive: Int, rng: Random): List[Int] = {
    require(size > 0, "size must be > 0")
    require(maxValueExclusive > 0, "maxValueExclusive must be > 0")
    List.fill(size)(rng.nextInt(maxValueExclusive))
  }

  def calculateAverage(nums: List[Int]): Double = {
    require(nums.nonEmpty, "nums must not be empty")
    nums.sum.toDouble / nums.size
  }

  def elementsDiffFromAverage(nums: List[Int], avg: Double, threshold: Double): List[Int] = {
    require(threshold >= 0, "threshold must be >= 0")
    nums.filter(num => math.abs(num - avg) >= threshold)
  }

  def countElementsDiffFromAverage(nums: List[Int], avg: Double, threshold: Double): Int =
    elementsDiffFromAverage(nums, avg, threshold).size

  def analyze(nums: List[Int], threshold: Double): Result = {
    val avg = calculateAverage(nums)
    val diffCount = countElementsDiffFromAverage(nums, avg, threshold)
    Result(nums, avg, diffCount)
  }
}
