package example

import munit.FunSuite

import scala.util.Random

class StatsSuite extends FunSuite {

  test("calculateAverage computes arithmetic mean") {
    assertEqualsDouble(Stats.calculateAverage(List(10, 20, 30)), 20.0, 1e-9)
  }

  test("calculateAverage throws on empty list") {
    intercept[IllegalArgumentException] {
      Stats.calculateAverage(Nil)
    }
  }

  test("elementsDiffFromAverage filters by threshold") {
    val nums = List(10, 20, 30)
    val avg = Stats.calculateAverage(nums) // 20
    assertEquals(Stats.elementsDiffFromAverage(nums, avg, threshold = 10), List(10, 30))
    assertEquals(Stats.elementsDiffFromAverage(nums, avg, threshold = 5), List(10, 30))
    assertEquals(Stats.elementsDiffFromAverage(nums, avg, threshold = 0), nums)
  }

  test("countElementsDiffFromAverage returns correct count") {
    val nums = List(10, 20, 30)
    val avg = Stats.calculateAverage(nums)
    assertEquals(Stats.countElementsDiffFromAverage(nums, avg, threshold = 10), 2)
  }

  test("generateRandomList returns correct size and bounds") {
    val rng = new Random(42)
    val nums = Stats.generateRandomList(size = 100, maxValueExclusive = 10, rng = rng)
    assertEquals(nums.size, 100)
    assert(nums.forall(n => n >= 0 && n < 10))
  }

  test("analyze returns average and diff count") {
    val result = Stats.analyze(List(10, 20, 30), threshold = 10)
    assertEqualsDouble(result.average, 20.0, 1e-9)
    assertEquals(result.diffCount, 2)
  }
}
